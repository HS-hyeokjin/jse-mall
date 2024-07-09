package com.hshyeokjin.jsemall.common.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.SQLException;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class DatabaseUtilTest {

    @Test
    @Order(1)
    @DisplayName("dbcp2 의 인스턴스")
    void connection_close() throws SQLException {
        Assertions.assertInstanceOf(BasicDataSource.class, DatabaseUtil.getDataSource());
    }

    @Test
    @Order(2)
    @DisplayName("마리아DB 드라이버로드")
    void mariadb_driver_load(){
        String driverClassName="org.mariadb.jdbc.Driver";
        try {
            Class<?> driver = Class.forName(driverClassName);
            Assertions.assertEquals(driver.getName(),driverClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(3)
    @DisplayName("mariadb connect")
    void my_connect() throws SQLException {
        Connection connection = DatabaseUtil.getDataSource().getConnection();
        Assertions.assertTrue(connection.isValid(2));
    }

    @Test
    @Order(4)
    @DisplayName("테스트를 위한 pool-size:5 설정, maxIdle, maxTotal, initialSize, minIdle")
    void connection_pool_size(){
        BasicDataSource basicDataSource = (BasicDataSource) DatabaseUtil.getDataSource();
        Assertions.assertAll(
                ()->Assertions.assertEquals(5,basicDataSource.getMaxIdle()),
                ()->Assertions.assertEquals(5,basicDataSource.getMaxTotal()),
                ()->Assertions.assertEquals(5,basicDataSource.getInitialSize()),
                ()->Assertions.assertEquals(5,basicDataSource.getMinIdle())
        );
    }
}