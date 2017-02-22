package com.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cdb.persistance.Entreprise;

public final class EntrepriseDao {

	//Identifiant BDD
	private static String URL = "jdbc:mysql://localhost:3306/computer-database-db";
    private static String LOGIN = "root";
    private static String PASSWORD = "";
    
    //Format standard des requetes QUERY	
    private final static String QUERY_FIND_ENTREPRISES = "SELECT * FROM company ";
    private final static String QUERY_FIND_ENTREPRISES_BY_PAGE = "SELECT * FROM company LIMIT ? OFFSET ?";
    private final static String QUERY_FIND_ENTREPRISES_BY_ID = "SELECT * FROM company where id=?";

    private static volatile EntrepriseDao instance = null;
    
    //Constructeur private (Singleton)
    private EntrepriseDao() {
        super();
    }
  
    //methode pour recuperer l'instance EntrepriseDao, Créer l'instance si celle-ci n'existe pas.
    public final static EntrepriseDao getInstanceEntrepriseDao() {
        if (EntrepriseDao.instance == null) {
           synchronized(EntrepriseDao.class) {
             if (EntrepriseDao.instance == null) {
            	 EntrepriseDao.instance = new EntrepriseDao();
             }
           }
        }
        return EntrepriseDao.instance;
    }
    
    // Fonction qui recupere la liste de tous les entreprises
	public List<Entreprise> findEntreprise(){
		
		List<Entreprise> entreprises = new ArrayList<Entreprise>();
		
		//Connection à la BDD
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null; 
		Statement stmt = null;
		
		try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            
            //Formation de la requete QUERY
            stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(QUERY_FIND_ENTREPRISES);
            
            //recuperation des resultats
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
	
	// Fonction qui recupere la liste de toutes les entreprises d'une page
	public List<Entreprise> findEntrepriseByPage(int numeroPage, int ligneParPage) {
		
		List<Entreprise> entreprises = new ArrayList<Entreprise>();
		
		//initialisation des bornes pour la requete QUERY
		int limit = ligneParPage;
		int offset = (numeroPage-1)*ligneParPage;
		
		//Connection à la BDD
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null; 
		PreparedStatement requete = null;
		
		try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            
          //Formation de la requete QUERY
            requete = con.prepareStatement(QUERY_FIND_ENTREPRISES_BY_PAGE);
            requete.setInt(1, limit);
            requete.setInt(2, offset);
            ResultSet res = requete.executeQuery();
            
            //recuperation des resultats
            entreprises = recuperationResultatRequete(res);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (requete != null) {
                try {
                	requete.close();
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

	// Fonction qui recupere une entreprise via son ID
	public Entreprise findEntrepriseByID(int index){
		
		Entreprise entreprise = null;
		
		//Connection à la BDD
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null; 
		Statement stmt = null;
		
		try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            
            //Formation de la requete QUERY
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            PreparedStatement requete = con.prepareStatement(QUERY_FIND_ENTREPRISES_BY_ID);
            requete.setInt(1, index);
            ResultSet res = requete.executeQuery();
            
          //Traitement du resultat si il existe pour recuperer un objet de type Entreprise
            if(res.next()){
            	
            	//Construction de l'entreprise final
            	entreprise = new Entreprise(res.getInt("id"), res.getString("name"));
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
		
		return entreprise;
	}

	// Fonction de recuperation de resultat de la requete en format List Entreprise pour qu'elle soit traitable par l'application.
	private List<Entreprise> recuperationResultatRequete(ResultSet rset) {
		
		List<Entreprise> entreprises = new ArrayList<Entreprise>();
		
		
		try {
			while (rset.next()) {
				
				//Construction de l'entreprise final + ajout à la liste
				entreprises.add(new Entreprise(rset.getInt("id"), rset.getString("name")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return entreprises;
	}
}
