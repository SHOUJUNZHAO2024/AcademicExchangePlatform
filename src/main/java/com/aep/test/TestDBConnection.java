package com.aep.test;
import java.sql.Connection;

import com.aep.dao.DBConnection;

public class TestDBConnection {
    public static void main(String[] args) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            if (connection != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
