package com.cdb.exception;

import com.cdb.dao.Impl.ConnexionDatabase;
import com.mysql.jdbc.Connection;

public class ConnexionDatabaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnexionDatabaseException(String s) {
		
		super(s);
		
	}
	
	public ConnexionDatabaseException(String s, Connection con) throws ConnexionDatabaseException {
		
		super(s);
		ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
		
	}
}
