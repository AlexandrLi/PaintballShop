package com.epam.alexandrli.paintballshop.pool;

import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;


public class ConnectionPoolTest {

    public static final int NUMBER_OF_TASKS = 5000;
    public static final int NUMBER_OF_THREADS = 30;
    public static AtomicInteger completedTasksCount = new AtomicInteger(0);

    @Test(expected = SQLException.class)
    public void ConnectionPoolShouldThrowExceptionNoFreeConnectionsAfterTimeout() throws SQLException {
        ConnectionPool connectionPool = (ConnectionPool) ConnectionPool.getInstance();
        int poolSize = connectionPool.getConnectionsLimit();

        for (int i = 0; i < poolSize + 1; i++) {
            connectionPool.getConnection();
        }
    }

    @Test
    public void ConnectionPoolShouldCompleteAllTasks() throws InterruptedException {
        DataSource connectionPool = ConnectionPool.getInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        for (int i = 0; i < NUMBER_OF_TASKS; i++) {
            executorService.submit(() -> {
                try (Connection connection = connectionPool.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery("SELECT * FROM product")) {
                    completedTasksCount.incrementAndGet();
                    Thread.sleep(100L);
                } catch (SQLException | InterruptedException e) {
                    throw new ConnectionPoolException("Could not complete task");
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        assertEquals(NUMBER_OF_TASKS, completedTasksCount.intValue());
    }
}
