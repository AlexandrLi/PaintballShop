package com.epam.alexandrli.paintballshop.connectionpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Runner {

    public static void main(String[] args) throws ConnectionPoolException, InterruptedException, ClassNotFoundException, SQLException, ExecutionException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 20; i++) {
            executorService.submit((Runnable) () -> {
                try (Connection connection = connectionPool.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") + " " + resultSet.getBigDecimal("price") + " " + resultSet.getString("description"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(4L, TimeUnit.SECONDS);
    }
}
