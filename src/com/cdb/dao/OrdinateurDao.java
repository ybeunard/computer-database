package com.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.cdb.persistance.Ordinateur;

public class OrdinateurDao {
	
	private static String URL = "jdbc:mysql://localhost:3306/computer-database-db";
    private static String LOGIN = "root";
    private static String PASSWORD = "";
    private final static String QUERY_FIND_ELEVES = "SELECT * FROM computer ";

	public List<Ordinateur> findOrdinateur(){
		
		List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null; 
		Statement stmt = null;
		try {
			
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(QUERY_FIND_ELEVES);

            while (rset.next()) {
            	String name = rset.getString("name");
                Ordinateur ordinateur = new Ordinateur(name);
                ordinateurs.add(ordinateur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		return ordinateurs;
	}
}
