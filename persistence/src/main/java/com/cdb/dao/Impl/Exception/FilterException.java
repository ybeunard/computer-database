package com.cdb.dao.Impl.Exception;

/**
 * The Class FilterException.
 *
 * @author Gajovski Maxime
 */
public class FilterException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new filter exception.
     */
    public FilterException() {
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
    public FilterException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor with message field.
     *
     * @param message
     *            : the error message.
     */
    public FilterException(String message) {
        super(message);
    }

    /**
     * Constructor with error field.
     *
     * @param error
     *            : the error.
     */
    public FilterException(Throwable error) {
        super(error);
    }
}
