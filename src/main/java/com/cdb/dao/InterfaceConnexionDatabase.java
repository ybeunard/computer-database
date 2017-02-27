package com.cdb.dao;

import com.cdb.exception.ConnexionDatabaseException;
import com.mysql.jdbc.Connection;

public interface InterfaceConnexionDatabase {

	Connection connectDatabase() throws ConnexionDatabaseException;

	void closeConnexionDatabase(Connection con) throws ConnexionDatabaseException;

}
