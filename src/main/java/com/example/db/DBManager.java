package com.example.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class DBManager {
    private static volatile BasicDataSource dataSource = null;
    private static volatile JdbcTemplate jdbcTemplate = null;
    private static volatile NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;

    private DBManager() {

    }

    private static void initDataSource() {
        if (dataSource == null) {
            synchronized (DBManager.class) {
                if (dataSource == null) {
                    dataSource = new BasicDataSource();
                    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                    dataSource.setUrl("jdbc:mysql://localhost:3306/actsystem?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false");
                    dataSource.setUsername("root");
                    dataSource.setPassword("rz37873@#");

                    // connection pool setting
                    dataSource.setMaxTotal(50);
                    dataSource.setMaxIdle(20);
                    dataSource.setMinIdle(10);
                    dataSource.setMaxWaitMillis(100000);
                    dataSource.setInitialSize(10);

//					dataSource.setRemoveAbandonedOnBorrow(true);
//					dataSource.setRemoveAbandonedOnMaintenance(true);
//					dataSource.setRemoveAbandonedTimeout(2*60); //seconds
//					dataSource.setMaxConnLifetimeMillis(10*60*1000);
//                  dataSource.setMinEvictableIdleTimeMillis(100*1000);

                    // test while idle & Validate configuration
                    // setNumTestsPerEvictionRun=config.dbPoolMinIdle
                    // to ensure every idle connection will be test in one check cycle
                    dataSource.setTestWhileIdle(true);
                    dataSource.setTestOnBorrow(true);

                    dataSource.setTimeBetweenEvictionRunsMillis(30 * 1000);
                    dataSource.setNumTestsPerEvictionRun(10);
                    dataSource.setValidationQuery("select 1");
                    dataSource.setValidationQueryTimeout(3);
                }
            }
        }
    }

    public static JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate != null) {
            return jdbcTemplate;
        }
        initDataSource();
        synchronized (DBManager.class) {
            if (jdbcTemplate == null) {
                jdbcTemplate = new JdbcTemplate(dataSource);
            }
        }
        return jdbcTemplate;
    }

    public static NamedParameterJdbcTemplate getNamedJdbcTemplate() {
        if (namedParameterJdbcTemplate != null) {
            return namedParameterJdbcTemplate;
        }
        initDataSource();
        synchronized (DBManager.class) {
            if (namedParameterJdbcTemplate == null) {
                namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
            }
        }
        return namedParameterJdbcTemplate;
    }
}
