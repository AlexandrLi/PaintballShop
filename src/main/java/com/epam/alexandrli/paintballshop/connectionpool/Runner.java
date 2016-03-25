package com.epam.alexandrli.paintballshop.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;

public class Runner {
    static Connection connection1;
    static Connection connection2;
    static Connection connection3;

    public static void main(String[] args) throws ConnectionPoolException, InterruptedException, ClassNotFoundException, SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
    }
}
