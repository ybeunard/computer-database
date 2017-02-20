package com.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cdb.persistance.Entreprise;
import com.cdb.persistance.Ordinateur;

public class EntrepriseDao {

	private static String URL = "jdbc:mysql://localhost:3306/computer-database-db";
    private static String LOGIN = "root";
    private static String PASSWORD = "";
    private final static String QUERY_FIND_ORDINATEURS = "SELECT * FROM computer ";

    // Fonction qui recupere la liste de tous les entreprises
	public List<Entreprise> findEntreprise(){
		
		List<Entreprise> entreprises = new ArrayList<Entreprise>();
		
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
            ResultSet rset = stmt.executeQuery(QUERY_FIND_ORDINATEURS);
            entreprises = recuperationResultatRequete(rset);
            

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
		return entreprises;
	}
}
