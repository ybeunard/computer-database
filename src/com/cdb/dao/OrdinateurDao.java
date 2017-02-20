package com.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.cdb.persistance.Ordinateur;

public final class OrdinateurDao {
	
	private static EntrepriseDao entrepriseDao;
	private static String URL = "jdbc:mysql://localhost:3306/computer-database-db";
    private static String LOGIN = "admincdb";
    private static String PASSWORD = "qwerty1234";
    private final static String QUERY_FIND_ORDINATEURS = "SELECT * FROM computer ";

    private static volatile OrdinateurDao instance = null;
    private OrdinateurDao() {
        super();
        entrepriseDao = new EntrepriseDao();
    }
  
    public final static OrdinateurDao getInstanceOrdinateurDao() {
        if (OrdinateurDao.instance == null) {
           synchronized(OrdinateurDao.class) {
             if (OrdinateurDao.instance == null) {
            	 OrdinateurDao.instance = new OrdinateurDao();
             }
           }
        }
        return OrdinateurDao.instance;
    }
    
    // Fonction qui recupere la liste de tous les ordinateurs
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
            ResultSet rset = stmt.executeQuery(QUERY_FIND_ORDINATEURS);
            ordinateurs = recuperationResultatRequete(rset);
            

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
	
	// Fonction de recuperation de resultat de la requete en format List Ordinateur pour qu'elle soit traitable par l'application.
	private List<Ordinateur> recuperationResultatRequete(ResultSet rset){
		
		List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
		
		try {
			while (rset.next()) {
				String name = rset.getString("name");
				Date dateIntroduit = null;
				Date dateInterrompu = null;
				try {
					dateIntroduit = rset.getDate("introduced");
				}catch(SQLException e){}
				try {
					dateInterrompu = rset.getDate("discontinued");
				}catch(SQLException e){}
				Integer fabricant = rset.getInt("company_id");				
				
				Ordinateur ordinateur;
				
				if(dateIntroduit == null){
					if(dateInterrompu == null){
						if(fabricant == 0){
							ordinateur = new Ordinateur(name);
						}
						else{
							ordinateur = new Ordinateur(name, entrepriseDao.findEntrepriseByID(fabricant));
						}
					}
					else{
						if(fabricant == 0){
							ordinateur = new Ordinateur(name, dateInterrompu, "interrompu");
						}
						else{
							ordinateur = new Ordinateur(name, dateInterrompu, entrepriseDao.findEntrepriseByID(fabricant), "interrompu");
						}
					}
				}
				else{
					if(dateInterrompu == null){
						if(fabricant == 0){
							ordinateur = new Ordinateur(name, dateInterrompu, "introduit");
						}
						else{
							ordinateur = new Ordinateur(name, dateInterrompu, entrepriseDao.findEntrepriseByID(fabricant), "introduit");
						}
					}
					else{
						if(fabricant == 0){
							ordinateur = new Ordinateur(name, dateIntroduit, dateInterrompu);
						}
						else{
							ordinateur = new Ordinateur(name, dateIntroduit, dateInterrompu, entrepriseDao.findEntrepriseByID(fabricant));
						}
					}
				}
			    
			    
			    ordinateurs.add(ordinateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ordinateurs;
	}
}
