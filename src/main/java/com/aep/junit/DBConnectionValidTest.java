package com.aep.junit;

import org.junit.jupiter.api.Test;
import com.aep.dao.DBConnection;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Ensures the Singleton instance and connection functionality of DBConnection.
 */

class DBConnectionValidTest {

    /**
     * Tests that DBConnection returns the same instance each time,
     * following the Singleton pattern.
     *
     * @throws SQLException if a database access error occurs
     */
    @Test
    void testSingletonInstance() throws SQLException {
        DBConnection instance1 = DBConnection.getInstance();
        DBConnection instance2 = DBConnection.getInstance();
        assertSame(instance1, instance2, "DBConnection should return the same instance");
    }
    
    /**
     * Tests that the database connection is not null after calling getConnection().
     *
     * @throws SQLException if a database access error occurs
     */
    @Test
    void testConnectionNotNull() throws SQLException {
        DBConnection instance = DBConnection.getInstance();
        Connection conn = instance.getConnection();
        assertNotNull(conn, "Connection should not be null");
    }
}
