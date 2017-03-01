/*
 * 
 */
package com.cdb.dao.Impl;

import java.util.Optional;

import com.cdb.exception.ConnexionDatabaseException;
import com.mysql.jdbc.Connection;

import junit.framework.TestCase;

public class TestConnexionDatabase extends TestCase {

    ConnexionDatabase connexion;

    protected void setUp() throws Exception {

        this.connexion = ConnexionDatabase.getInstanceConnexionDatabase();

    }

    protected void tearDown() throws Exception {

        this.connexion = null;

    }

    public void testConnexionDatabase() throws ConnexionDatabaseException {

        Optional<Connection> connection = this.connexion.connectDatabase();
        assertNotNull(connection);
        assertTrue(connection.isPresent());

        this.connexion.closeConnexionDatabase(connection.get());
        assertNotNull(connection);
        assertTrue(connection.isPresent());

        this.connexion.closeConnexionDatabase(connection.get());
        assertNotNull(connection);
        assertTrue(connection.isPresent());

    }

}
