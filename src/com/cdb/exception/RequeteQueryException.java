package com.cdb.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequeteQueryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static Logger logger = LoggerFactory.getLogger(RequeteQueryException.class);

	public RequeteQueryException(String s) {
		
		super(s);
		logger.error(s);
		
	}
	
}
