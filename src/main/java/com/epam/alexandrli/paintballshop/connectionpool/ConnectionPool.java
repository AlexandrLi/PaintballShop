package com.epam.alexandrli.paintballshop.connectionpool;

import com.epam.alexandrli.paintballshop.PropertyManager;
import com.epam.alexandrli.paintballshop.PropertyManagerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool implements DataSource {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);
    private String driver;
    private String url;
    private String username;
    private String password;
    private int connectionsLimit;
    private BlockingQueue<PooledConnection> freeConnections;
    private BlockingQueue<PooledConnection> usedConnections;

    private ConnectionPool() {
        Properties properties = null;
        try {
            properties = PropertyManager.getProperties("database.properties");
        } catch (PropertyManagerException e) {
            e.printStackTrace();
        }
        if (properties != null) {
            this.driver = properties.getProperty("driver");
            this.url = properties.getProperty("url");
            this.username = properties.getProperty("username");
            this.password = properties.getProperty("password");
            this.connectionsLimit = Integer.parseInt(properties.getProperty("connections.limit"));
        }
        initConnections(connectionsLimit);
    }

    public static ConnectionPool getInstance() {
        return InstanceHolder.instance;
    }

    public void initConnections(int count) throws ConnectionPoolException {
        if (freeConnections == null) {
            freeConnections = new ArrayBlockingQueue<>(connectionsLimit);
        }
        try {
            Class.forName(driver);
            for (int i = 0; i < count; i++) {
                Connection pooledConnection = getPooledConnection();
                freeConnections.put((PooledConnection) pooledConnection);
            }
        } catch (ClassNotFoundException | InterruptedException e) {
            throw new ConnectionPoolException("Couldn't init connection", e);
        }
        logger.debug("Connections has been initialized. Connection count - {}", freeConnections.size());
    }

    private Connection getPooledConnection() throws ConnectionPoolException {
        Connection pooledConnection;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            pooledConnection = new PooledConnection(connection);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Couldn't get PooledConnection", e);
        }
        return pooledConnection;
    }

    public Connection getConnection() throws ConnectionPoolException {
        if (usedConnections == null) {
            usedConnections = new ArrayBlockingQueue<>(connectionsLimit);
        }
        PooledConnection pooledConnection;
        try {
            pooledConnection = freeConnections.take();
            usedConnections.put(pooledConnection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Couldn't get connection", e);
        }
        return pooledConnection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    public void shutdown() throws ConnectionPoolException {
        closeAllConnectionsInQueue(freeConnections);
        closeAllConnectionsInQueue(usedConnections);
    }

    private void closeAllConnectionsInQueue(BlockingQueue<PooledConnection> connections) throws ConnectionPoolException {
        for (PooledConnection connection : connections) {
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
                throw new ConnectionPoolException("Couldn't close connection", e);
            }
        }
    }

    public void releaseConnection(PooledConnection connection) throws ConnectionPoolException {
        try {
            connection.setAutoCommit(true);
            usedConnections.remove(connection);
            if (connection.isClosed()) {
                initConnections(1);
            } else {
                freeConnections.put(connection);
            }
        } catch (SQLException | InterruptedException e) {
            throw new ConnectionPoolException("Couldn't release current connection", e);
        }
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    private static class InstanceHolder {
        static final ConnectionPool instance = new ConnectionPool();
    }

}
