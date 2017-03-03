package com.cdb.dao;

import com.cdb.exception.ConnexionDatabaseException;
import com.mysql.jdbc.Connection;

/**
 * The Interface InterfaceConnexionDatabase.
 */
public interface InterfaceConnexionDatabase {

    /**
     * Connect database.
     *
     * @return the connection
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     */
    Connection connectDatabase() throws ConnexionDatabaseException;

    /**
     * Close connexion database.
     *
     * @param con
     *            the con
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     */
    void closeConnexionDatabase(Connection con)
            throws ConnexionDatabaseException;

}
