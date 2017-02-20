package com.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    private final static String QUERY_FIND_ORDINATEURS_BY_ID = "SELECT * FROM computer where id=?";
    private final static String QUERY_DELETE_ORDINATEURS_BY_ID = "DELETE FROM computer where id=?";

    private static volatile OrdinateurDao instance = null;
    private OrdinateurDao() {
        super();
        entrepriseDao = EntrepriseDao.getInstanceEntrepriseDao();
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
	
	public Ordinateur findOrdinateurByID(int index){
		Ordinateur ordinateur = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null; 
		PreparedStatement requete = null;
		
		try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            requete = con.prepareStatement(QUERY_FIND_ORDINATEURS_BY_ID);
            requete.setInt(1, index);
            ResultSet res = requete.executeQuery();
            if(res.next()){
            	String name = res.getString("name");
				Date dateIntroduit = null;
				Date dateInterrompu = null;
				try {
					dateIntroduit = res.getDate("introduced");
				}catch(SQLException e){}
				try {
					dateInterrompu = res.getDate("discontinued");
				}catch(SQLException e){}
				Integer fabricant = res.getInt("company_id");				
				
				if(fabricant == 0){
					ordinateur = new Ordinateur(name, dateIntroduit, dateInterrompu, null);
				}
				else{
					ordinateur = new Ordinateur(name, dateIntroduit, dateInterrompu, entrepriseDao.findEntrepriseByID(fabricant));
				}    
            }
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
		
		return ordinateur;
	}
	
	public void suppressionOrdinateur(int index){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null; 
		PreparedStatement requete = null;
		try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            requete = con.prepareStatement(QUERY_DELETE_ORDINATEURS_BY_ID);
            requete.setInt(1, index);
            requete.executeUpdate();
		}catch(SQLException e){
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
				
				if(fabricant == 0){
					ordinateur = new Ordinateur(name, dateIntroduit, dateInterrompu, null);
				}
				else{
					ordinateur = new Ordinateur(name, dateIntroduit, dateInterrompu, entrepriseDao.findEntrepriseByID(fabricant));
				}    
			    
			    ordinateurs.add(ordinateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ordinateurs;
	}
}
