package com.example.pharmacysystem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String POSTGRES_URL = "jdbc:postgresql://ep-spring-tooth-a4dpq94j-pooler.us-east-1.aws.neon.tech:5432/verceldb?sslmode=require";
    private static final String POSTGRES_USER = "default";
    private static final String POSTGRES_PASSWORD = "5urhocdU0eOK";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASSWORD);
    }
}
