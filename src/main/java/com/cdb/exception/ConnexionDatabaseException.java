package com.cdb.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ConnexionDatabaseException.
 */
public class ConnexionDatabaseException extends RuntimeException {

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

}
