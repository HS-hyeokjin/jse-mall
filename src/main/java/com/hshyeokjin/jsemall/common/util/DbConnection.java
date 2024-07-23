package com.hshyeokjin.jsemall.common.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

import java.sql.Connection;

@Slf4j
public class DbConnection {
    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> sqlErrorThreadLocal = ThreadLocal.withInitial(() -> false);

    public static void initialize() {
        try {
            Connection connection = DatabaseUtil.getDataSource().getConnection();
            connectionThreadLocal.set(connection);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            connection.setAutoCommit(false);
        } catch (SQLException e) {
        }
    }

    public static Connection getConnection() {
        return connectionThreadLocal.get();
    }

    public static void setSqlError(boolean sqlError) {
        sqlErrorThreadLocal.set(sqlError);
    }

    public static boolean getSqlError() {
        return sqlErrorThreadLocal.get();
    }

    public static void reset() {
        try {
            Connection connection = connectionThreadLocal.get();
            if (connection != null) {
                if (getSqlError()) {
                    connection.rollback();
                } else {
                    connection.commit();
                }
                connection.close();
            }
        }catch (SQLException e) {
            log.error("DbConnectionThreadLocal.reset() : ", e);

        } finally {
            connectionThreadLocal.remove();
            sqlErrorThreadLocal.remove();
        }
    }
}

