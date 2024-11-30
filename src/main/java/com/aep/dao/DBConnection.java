/**
 * Purpose: Double Checked Locking Singleton class to manage a single database connection instance.
 */

package com.aep.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *@author SHOUJUN ZHAO
 *@version 1.0, November 2024
 *@since javac 17.0.10
 *
 * This class ensures that only one instance of a database connection
 * is created and reused throughout the application.
 */

public class DBConnection {

	/**
	 * Singleton instance of the DBConnection class.
	 */
    private static volatile DBConnection dbConnectionSingleton;
    
    /**
     * The actual database connection instance. 
     */
    private Connection connection = null;

    /**
     * Database credentials 
     */
    /**
     * Database Name
     */
    private String serverUrl = "jdbc:mysql://localhost:3306/aep";
    
    /**
     * Database username
     */
    private String userString = "root";
    
    /**
     * Database Password
     */
    private String passwordString = "zsj5152100";  
    
    /**
     * Database driver
     */
    private String driverString = "com.mysql.cj.jdbc.Driver";

    /**
     * Private constructor to prevent multiple instances.
     * Loads the JDBC driver and establishes the connection.
     * 
     * @throws SQLException if a database access error occurs
     */
    private DBConnection() throws SQLException {
        try {
            Class.forName(driverString);
            this.connection = DriverManager.getConnection(serverUrl, userString, passwordString);
        } catch (ClassNotFoundException ex) {
            System.out.println("Connection was not successful: " + ex.getMessage());
        }
    }

    /**
     * Returns the singleton instance of DBConnection.
     *
     * @return the singleton instance
     * @throws SQLException if a database access error occurs
     */
    public static DBConnection getInstance() throws SQLException {
        if (dbConnectionSingleton == null) {
        	
        	synchronized (DBConnection.class) {
        		
        		if (dbConnectionSingleton == null) {
        			dbConnectionSingleton = new DBConnection();
        		}
        		
        	}
            
        }
        return dbConnectionSingleton;
    }

    /**
     * Returns the connection instance.
     *
     * @return the Connection instance
     */
    public Connection getConnection() {
        return connection;
    }
}

