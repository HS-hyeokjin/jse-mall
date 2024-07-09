package com.hshyeokjin.jsemall.common.util;

import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.time.Duration;

public class DatabaseUtil {
    public DatabaseUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final DataSource DATASOURCE;

    static {
        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setDriver(new org.mariadb.jdbc.Driver());

        basicDataSource.setUrl("jdbc:mariadb://127.0.0.1:3307/JSEMALL");
        basicDataSource.setUsername("scott");
        basicDataSource.setPassword("tiger");

        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxTotal(5);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setMinIdle(5);

        basicDataSource.setMaxWait(Duration.ofSeconds(2));
        basicDataSource.setValidationQuery("select 1");
        basicDataSource.setTestOnBorrow(true);

        DATASOURCE = basicDataSource;

    }

    public static DataSource getDataSource() {
        return DATASOURCE;
    }

}


