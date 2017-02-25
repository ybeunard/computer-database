package com.cdb.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.Impl.ConnexionDatabase;
import com.mysql.jdbc.Connection;

public class ConnexionDatabaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static Logger logger = LoggerFactory.getLogger(ConnexionDatabaseException.class);

	public ConnexionDatabaseException(String s) {
		
		super(s);
		logger.error(s);
		
	}
	
	public ConnexionDatabaseException(String s, Connection con) throws ConnexionDatabaseException {
		
		super(s);
		logger.error(s);
		ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
		
	}
}
