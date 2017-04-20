package com.cdb.dao.Impl.Exception;

/**
 * The Class IdException.
 *
 * @author Gajovski Maxime
 */
public class IdException extends IllegalArgumentException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new id exception.
     */
    public IdException() {
        super();
    }

    /**
     * Constructor with message and error field.
     *
     * @param message
     *            : the error message.
     * @param error
     *            : the error.
     */
    public IdException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor with message field.
     *
     * @param message
     *            : the error message.
     */
    public IdException(String message) {
        super(message);
    }

    /**
     * Constructor with error field.
     *
     * @param error
     *            : the error.
     */
    public IdException(Throwable error) {
        super(error);
    }
}
