package com.example.stest2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyProperties {
    @Value("${db.driverClassName}")
    private String driverClassName;

    @Value("${db.dbConnectionString}")
    private String dbConnectionString;

    @Value("${db.dbUser}")
    private String dbUser;

    @Value("${db.dbPassword}")
    private String dbPassword;
}
