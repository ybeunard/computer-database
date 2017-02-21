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

	private static String URL = "jdbc:mysql://localhost:3306/computer-database-db";
    private static String LOGIN = "root";
    private static String PASSWORD = "";
    private final static String QUERY_FIND_ENTREPRISES = "SELECT * FROM company ";
    private final static String QUERY_FIND_ENTREPRISES_BY_PAGE = "SELECT * FROM company LIMIT ? OFFSET ?";
    private final static String QUERY_FIND_ENTREPRISES_BY_ID = "SELECT * FROM company where id=?";

    private static volatile EntrepriseDao instance = null;
    private EntrepriseDao() {
        super();
    }
  
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
            ResultSet rset = stmt.executeQuery(QUERY_FIND_ENTREPRISES);
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
	
	public Entreprise findEntrepriseByID(int index){
		
		Entreprise entreprise = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null; 
		Statement stmt = null;
		try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            PreparedStatement requete = con.prepareStatement(QUERY_FIND_ENTREPRISES_BY_ID);
            requete.setInt(1, index);
            ResultSet res = requete.executeQuery();
            if(res.next()){
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
				entreprises.add(new Entreprise(rset.getInt("id"), rset.getString("name")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return entreprises;
	}

	public List<Entreprise> findEntrepriseByPage(int numeroPage, int ligneParPage) {
		List<Entreprise> entreprises = new ArrayList<Entreprise>();
		int limit = ligneParPage;
		int offset = (numeroPage-1)*ligneParPage;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null; 
		PreparedStatement requete = null;
		
		try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            requete = con.prepareStatement(QUERY_FIND_ENTREPRISES_BY_PAGE);
            requete.setInt(1, limit);
            requete.setInt(2, offset);
            ResultSet res = requete.executeQuery();
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
}
