package com.aep.junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import com.aep.dao.DBConnection;

/**
 *
 * Ensures the Singleton instance and connection functionality of DBConnection.
 */
class DBConnectionInvalidTest {

    /**
     * Tests if DBConnection returns the different instance each time,
     * NOT following the Singleton pattern.
     *
     * @throws SQLException if a database access error occurs
     */
    @Test
    void testSingletonInstanceInvalid() throws SQLException {
        DBConnection instance1 = DBConnection.getInstance();
        DBConnection instance2 = DBConnection.getInstance();
        assertNotSame(instance1, instance2, "DBConnection should not return the same instance"); // both instances should be same
    }

    /**
     * Tests if the database connection is null, which is an incorrect expectation.
     *
     * @throws SQLException if a database access error occurs
     */
    @Test
    void testConnectionIsNull() throws SQLException {
        DBConnection instance = DBConnection.getInstance();
        Connection conn = instance.getConnection();
        assertNull(conn, "Connection should be null"); // This will fail as the connection is not null
    }
}

