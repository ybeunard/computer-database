package com.cdb.dao.Impl.Exception;

/**
 * The Class PageException.
 *
 * @author Gajovski Maxime
 */
public class PageException extends IllegalArgumentException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new page exception.
     */
    public PageException() {
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
    public PageException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor with message field.
     *
     * @param message
     *            : the error message.
     */
    public PageException(String message) {
        super(message);
    }

    /**
     * Constructor with error field.
     *
     * @param error
     *            : the error.
     */
    public PageException(Throwable error) {
        super(error);
    }
}
