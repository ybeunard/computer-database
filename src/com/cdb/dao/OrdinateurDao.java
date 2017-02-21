package com.cdb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cdb.persistance.Ordinateur;

public final class OrdinateurDao {
	
	private static EntrepriseDao entrepriseDao;
	
	//Identifiant BDD
	private static String URL = "jdbc:mysql://localhost:3306/computer-database-db";
    private static String LOGIN = "admincdb";
    private static String PASSWORD = "qwerty1234";
    
    //Format standard des requetes QUERY
    private final static String QUERY_INSERT_ORDINATEUR = "INSERT INTO computer (name,introduced,discontinued,company_id) values ( ?, ?, ?, ?);";
    private final static String QUERY_FIND_ORDINATEURS = "SELECT * FROM computer ";
    private final static String QUERY_FIND_ORDINATEURS_BY_PAGE = "SELECT * FROM computer LIMIT ? OFFSET ?";
    private final static String QUERY_FIND_ORDINATEURS_BY_ID = "SELECT * FROM computer where id=?";
    private final static String QUERY_UPDATE_ORDINATEUR = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
    private final static String QUERY_DELETE_ORDINATEUR = "DELETE FROM computer where id=?";
    
    private static volatile OrdinateurDao instance = null;
    
    //Constructeur private (Singleton)
    private OrdinateurDao() {
        super();
        entrepriseDao = EntrepriseDao.getInstanceEntrepriseDao();
    }
  
    //methode pour recuperer l'instance OrdinateurDao, Créer l'instance si celle-ci n'existe pas.
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
    
    //fonction qui cree un ordinateur dans la BDD
    public void createOrdinateur(Ordinateur ordinateur){
    	
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
            requete = con.prepareStatement(QUERY_INSERT_ORDINATEUR);
            requete.setString(1, ordinateur.getName());
            requete.setDate(2, (java.sql.Date) ordinateur.getDateIntroduit());
            requete.setDate(3, (java.sql.Date) ordinateur.getDateInterrompu());
            if(ordinateur.getFabricant() != null){
            	requete.setInt(4, ordinateur.getFabricant().getId());
            }
            else{
            	requete.setString(4, null);
            }
            requete.executeUpdate();
            
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
    }
    
    // Fonction qui recupere la liste de tous les ordinateurs
	public List<Ordinateur> findOrdinateur(){
		
		List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
		
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
            ResultSet rset = stmt.executeQuery(QUERY_FIND_ORDINATEURS);
            
            //recuperation des resultats
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
	
	// Fonction qui recupere la liste de tous les ordinateurs d'une page
	public List<Ordinateur> findOrdinateurByPage(int numeroPage, int ligneParPage) {
		
		List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
		
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
            requete = con.prepareStatement(QUERY_FIND_ORDINATEURS_BY_PAGE);
            requete.setInt(1, limit);
            requete.setInt(2, offset);
            ResultSet res = requete.executeQuery();
            
          //recuperation des resultats
            ordinateurs = recuperationResultatRequete(res);
            
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
		
		return ordinateurs;
	}
	
	// Fonction qui recupere un ordinateur via son ID
	public Ordinateur findOrdinateurByID(int index){
		Ordinateur ordinateur = null;
		
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
            requete = con.prepareStatement(QUERY_FIND_ORDINATEURS_BY_ID);
            requete.setInt(1, index);
            ResultSet res = requete.executeQuery();
            
            //Traitement du resultat si il existe pour recuperer un objet de type Ordinateur
            if(res.next()){
            	
            	//Initialisation des variable
            	int id = res.getInt("id");
            	String name = res.getString("name");
				Date dateIntroduit = null;
				Date dateInterrompu = null;
				Integer fabricant = res.getInt("company_id");
				
				//Recuperation des variables dates si elles existent
				try {
					dateIntroduit = res.getDate("introduced");
				}catch(SQLException e){}
				try {
					dateInterrompu = res.getDate("discontinued");
				}catch(SQLException e){}
				
				//Construction de l'ordinateur final
				if(fabricant == 0){
					ordinateur = new Ordinateur(id, name, dateIntroduit, dateInterrompu, null);
				}
				else{
					ordinateur = new Ordinateur(id, name, dateIntroduit, dateInterrompu, entrepriseDao.findEntrepriseByID(fabricant));
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
	
	//Fonction de mise à jour d'un ordinateur
	public void updateOrdinateur(Ordinateur ordinateur){
		
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
            requete = con.prepareStatement(QUERY_UPDATE_ORDINATEUR);
            requete.setString(1, ordinateur.getName());
            requete.setDate(2, (java.sql.Date) ordinateur.getDateIntroduit());
            requete.setDate(3, (java.sql.Date) ordinateur.getDateInterrompu());
            if(ordinateur.getFabricant() != null){
            	requete.setInt(4, ordinateur.getFabricant().getId());
            }
            else{
            	requete.setString(4, null);
            }
            requete.setInt(5, ordinateur.getId());
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
	
	//fonction qui supprime un ordinateur
	public void suppressionOrdinateur(int index){
		
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
            requete = con.prepareStatement(QUERY_DELETE_ORDINATEUR);
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
	private List<Ordinateur> recuperationResultatRequete(ResultSet res){
		
		List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
		Ordinateur ordinateur;
		
		try {
			while (res.next()) {
				
				//Initialisation des variable
            	int id = res.getInt("id");
            	String name = res.getString("name");
				Date dateIntroduit = null;
				Date dateInterrompu = null;
				Integer fabricant = res.getInt("company_id");
				
				//Recuperation des variables dates si elles existent
				try {
					dateIntroduit = res.getDate("introduced");
				}catch(SQLException e){}
				try {
					dateInterrompu = res.getDate("discontinued");
				}catch(SQLException e){}
				
				//Construction de l'ordinateur final
				if(fabricant == 0){
					ordinateur = new Ordinateur(id, name, dateIntroduit, dateInterrompu, null);
				}
				else{
					ordinateur = new Ordinateur(id, name, dateIntroduit, dateInterrompu, entrepriseDao.findEntrepriseByID(fabricant));
				}    
			    
				//ajout de l'ordinateur à la liste
			    ordinateurs.add(ordinateur);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ordinateurs;
	}
}
