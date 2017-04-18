package com.cdb.dao.Impl.Exception;

/**
 * @author	Gajovski Maxime
 * @date	18 avr. 2017
 */
public class FilterException extends RuntimeException {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
 
  public FilterException() {
      super();
  }

  /**
   * Constructor with message and error field.
   * @param message : the error message.
   * @param error : the error.
   */
  public FilterException(String message, Throwable error) {
      super(message, error);
  }

  /**
   * Constructor with message field.
   * @param message : the error message.
   */
  public FilterException(String message) {
      super(message);
  }

  /**
   * Constructor with error field.
   * @param error : the error.
   */
  public FilterException(Throwable error) {
      super(error);
  }
}
