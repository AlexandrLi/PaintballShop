package com.epam.alexandrli.paintballshop.connectionpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Runner {
    public static final Logger logger = LoggerFactory.getLogger(Runner.class);
    public static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws ConnectionPoolException, InterruptedException, ClassNotFoundException, SQLException, ExecutionException {
        DataSource connectionPool = ConnectionPool.getInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        LocalTime timeIn = LocalTime.now();
        for (int i = 0; i < 100; i++) {
            executorService.submit((Runnable) () -> {
                counter.incrementAndGet();
                try (Connection connection = connectionPool.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery("SELECT * FROM product")) {
                    Thread.sleep(10L);
                } catch (SQLException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        LocalTime timeOut = null;
        if (executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)) {
            timeOut = LocalTime.now();
        }
        logger.debug("Number of tasks: {}. ExecuteTime: {} seconds", counter, Duration.between(timeIn, timeOut).toMillis() / 1000);
    }
}
