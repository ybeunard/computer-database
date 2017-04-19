package com.cdb.dao.Impl.Exception;

/**
 * @author	Gajovski Maxime
 * @date	18 avr. 2017
 */
public class PageException extends IllegalArgumentException {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
 
  
  public PageException() {
      super();
  }

  /**
   * Constructor with message and error field.
   * @param message : the error message.
   * @param error : the error.
   */
  public PageException(String message, Throwable error) {
      super(message, error);
  }

  /**
   * Constructor with message field.
   * @param message : the error message.
   */
  public PageException(String message) {
      super(message);
  }

  /**
   * Constructor with error field.
   * @param error : the error.
   */
  public PageException(Throwable error) {
      super(error);
  }
}