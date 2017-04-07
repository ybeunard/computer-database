package com.cdb.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequeteQueryException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final Logger LOGGER = LoggerFactory
            .getLogger(RequeteQueryException.class);

    /**
     * Instantiates a new requete query exception.
     *
     * @param s
     *            the s
     */
    public RequeteQueryException(String s) {

        super(s);
        LOGGER.error(s);

    }

}
