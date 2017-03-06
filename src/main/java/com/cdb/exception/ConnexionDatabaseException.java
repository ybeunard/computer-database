package com.cdb.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.Connection;

/**
 * The Class ConnexionDatabaseException.
 */
public class ConnexionDatabaseException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The Constant logger. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(ConnexionDatabaseException.class);

    /**
     * Instantiates a new connexion database exception.
     *
     * @param s
     *            the s
     */
    public ConnexionDatabaseException(String s) {

        super(s);
        LOGGER.error(s);

    }

    /**
     * Instantiates a new connexion database exception.
     *
     * @param s
     *            the s
     * @param con
     *            the con
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     */
    public ConnexionDatabaseException(String s, Connection con)
            throws ConnexionDatabaseException {

        super(s);
        LOGGER.error(s);

    }

}
