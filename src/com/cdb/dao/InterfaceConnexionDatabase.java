package com.cdb.dao;

import com.mysql.jdbc.Connection;

public interface InterfaceConnexionDatabase {

	Connection connectDatabase();

	void closeConnexionDatabase(Connection con);

}
