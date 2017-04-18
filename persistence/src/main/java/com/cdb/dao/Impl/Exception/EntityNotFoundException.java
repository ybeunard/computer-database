package com.cdb.dao.Impl.Exception;

public class EntityNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
   
    public EntityNotFoundException() {
        super();
    }

    /**
     * Constructor with message and error field.
     * @param message : the error message.
     * @param error : the error.
     */
    public EntityNotFoundException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor with message field.
     * @param message : the error message.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor with error field.
     * @param error : the error.
     */
    public EntityNotFoundException(Throwable error) {
        super(error);
    }
}
