package com.cdb.dao.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.mysql.jdbc.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.InstanceOrdinateurDao;
import com.cdb.entities.Entreprise;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

public enum OrdinateurDao implements InstanceOrdinateurDao {
	
	INSTANCE_ORDINATEUR_DAO;
    
    //Constructeur private
    private OrdinateurDao() {
    	
    }
  
    //methode pour recuperer l'instance OrdinateurDao
    public final static OrdinateurDao getInstanceOrdinateurDao() {
        
        return INSTANCE_ORDINATEUR_DAO;
        
    }
    
    private static Properties prop = new Properties();
	
	//Chargement du fichier query_entreprises.properties
	static {
		
		File fProp = new File("computer-database/properties/query_ordinateurs.properties");
		 
		// Charge le contenu de ton fichier properties dans un objet Properties
		FileInputStream stream = null;
		
		try {
			
			stream = new FileInputStream(fProp);
			prop.load(stream) ;
			
		} catch(IOException e) {

			e.printStackTrace();
			
		}
		
	}
	
	public final static Logger logger = LoggerFactory.getLogger(OrdinateurDao.class);
    
    //fonction qui cree un ordinateur dans la BDD
	public void createOrdinateur(Ordinateur ordinateur) throws ConnexionDatabaseException, RequeteQueryException {
		
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase();; 
		PreparedStatement requete = null;
		logger.info("Création d'un " + ordinateur);
		
		try {
            
            //Formation de la requete QUERY
            requete = con.prepareStatement(prop.getProperty("QUERY_INSERT_ORDINATEUR"));
            requete.setString(1, ordinateur.getName());
            
            if(ordinateur.getDateIntroduit() != null) {
            	
            	requete.setDate(2, Date.valueOf(ordinateur.getDateIntroduit()));
            	
            } else {
            	
            	requete.setDate(2, null);
            	
            } if(ordinateur.getDateInterrompu() != null) {
            	
            	requete.setDate(3, Date.valueOf(ordinateur.getDateInterrompu()));
            	
            } else {
            	
            	requete.setDate(3, null);
            	
            } if(ordinateur.getFabricant() != null) {
            	
            	requete.setLong(4, ordinateur.getFabricant().getId());
            	
            } else {
            	
            	requete.setString(4, null);
            	
            }
            
            requete.executeUpdate();
            logger.info("Creation d'un ordinateur effectuée");
            
        } catch (SQLException e) {
        	
            throw new RequeteQueryException("Echec de la requete de creation de l'" + ordinateur);
            
        } finally {
        	
            if(requete != null) {
            	
                try {
                	
                    requete.close();
                    
                } catch(SQLException e) {
                	
                	throw new RequeteQueryException("Fermeture de la requete de creation d'ordinateur impossible");
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
            
        }
		
    }
    
    // Fonction qui recupere la liste de tous les ordinateurs
	public List<Ordinateur> findOrdinateur() throws ConnexionDatabaseException, RequeteQueryException {
		
		List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
		
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase(); 
		Statement stmt = null;
		logger.info("recherche de la liste d'ordinateur");
		
		try {
            
            //Formation de la requete QUERY
            stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(prop.getProperty("QUERY_FIND_ORDINATEURS"));
            
            //recuperation des resultats
            ordinateurs = recuperationResultatRequete(rset);
            logger.info("recherche de la liste d'ordinateur effectuée");
            

        } catch(SQLException e) {
        	
        	throw new RequeteQueryException("Echec de la requete de recherche d'ordinateur");
            
        } finally {
        	
            if(stmt != null) {
            	
                try {
                	
                    stmt.close();
                    
                } catch(SQLException e) {
                	
                	throw new RequeteQueryException("Fermeture de la requete de recherche d'ordinateur impossible");
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);;
            
        }
		
		return ordinateurs;
		
	}
	
	// Fonction qui recupere la liste de tous les ordinateurs d'une page
	public List<Ordinateur> findOrdinateurByPage(int numeroPage, int ligneParPage) throws ConnexionDatabaseException, RequeteQueryException {
		
		List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
		
		//initialisation des bornes pour la requete QUERY
		int limit = ligneParPage;
		int offset = (numeroPage-1)*ligneParPage;
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase(); 
		PreparedStatement requete = null;
		logger.info("recherche de la liste d'ordinateur par page");
		
		try {
            
            //Formation de la requete QUERY
            requete = con.prepareStatement(prop.getProperty("QUERY_FIND_ORDINATEURS_BY_PAGE"));
            requete.setInt(1, limit);
            requete.setInt(2, offset);
            ResultSet res = requete.executeQuery();
            
          //recuperation des resultats
            ordinateurs = recuperationResultatRequete(res);
            logger.info("recherche de la liste d'ordinateur par page effectuée");
            
        } catch(SQLException e) {
        	
        	throw new RequeteQueryException("Echec de la requete de recherche par page d'ordinateur");
            
        } finally {
        	
            if(requete != null) {
            	
                try {
                	
                	requete.close();
                	
                } catch(SQLException e) {
                	
                	throw new RequeteQueryException("Fermeture de la requete de recherche d'ordinateur par page impossible");
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
            
        }
		
		return ordinateurs;
		
	}
	
	// Fonction qui recupere un ordinateur via son ID
	public Ordinateur findOrdinateurByID(long index) throws ConnexionDatabaseException, RequeteQueryException {
		
		Ordinateur ordinateur = null;
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase(); 
		PreparedStatement requete = null;
		logger.info("recherche d'un ordinateur par id: " + index);
		
		try {
            
            //Formation de la requete QUERY
            requete = con.prepareStatement(prop.getProperty("QUERY_FIND_ORDINATEURS_BY_ID"));
            requete.setLong(1, index);
            ResultSet res = requete.executeQuery();
            
            //Traitement du resultat si il existe pour recuperer un objet de type Ordinateur
            if(res.next()){
            	
            	//Initialisation des variable
            	long id = res.getInt("id");
            	String name = res.getString("name");
				LocalDate dateIntroduit = null;
				LocalDate dateInterrompu = null;
				Integer fabricantID = res.getInt("company_id");
				String fabricantName = res.getString("company_name");
				Date date = null;
				
				//Recuperation des variables dates si elles existent
				//Recuperation des variables dates si elles existent
				try {
					
					date = res.getDate("introduced");
					
					if(date != null) {
						
						dateIntroduit = date.toLocalDate();
						
					}
					
				} catch(SQLException e) {
					
					throw new RequeteQueryException("Recuperation de la date d'introduction impossible");
					
				} try {
					
					date = res.getDate("discontinued");
					
					if(date != null) {
						
						dateInterrompu = date.toLocalDate();
						
					}
					
				} catch(SQLException e) {
					
					throw new RequeteQueryException("Recuperation de la date d'interruption impossible");
					
				}
				
				//Construction de l'ordinateur final
				if(fabricantID == 0) {
					
					ordinateur = new Ordinateur(id, name, dateIntroduit, dateInterrompu, null);
					
				} else {
					
					ordinateur = new Ordinateur(id, name, dateIntroduit, dateInterrompu, new Entreprise(fabricantID, fabricantName));
				
				}   
				
            }
            
            logger.info("recherche d'un ordinateur par id effectuée");
            
        } catch(SQLException e) {
        	
        	throw new RequeteQueryException("Echec de la requete de recherche de l'ordinateur numero:" + index);
            
        } finally {
        	
            if(requete != null) {
            	
                try {
                	
                    requete.close();
                    
                } catch(SQLException e) {
                	
                	throw new RequeteQueryException("Fermeture de la requete de recherche d'ordinateur par ID impossible");
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
            
        }
		
		return ordinateur;
		
	}
	
	//Fonction de mise à jour d'un ordinateur
	public void updateOrdinateur(Ordinateur ordinateur) throws ConnexionDatabaseException, RequeteQueryException {
		
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase(); 
		PreparedStatement requete = null;
		logger.info("update d'un " + ordinateur);
		
		try {
            
            //Formation de la requete QUERY
            requete = con.prepareStatement(prop.getProperty("QUERY_UPDATE_ORDINATEUR"));
            requete.setString(1, ordinateur.getName());
            
            if(ordinateur.getDateIntroduit() != null) {
            	
            	requete.setDate(2, Date.valueOf(ordinateur.getDateIntroduit()));
            	
            } else {
            	
            	requete.setDate(2, null);
            	
            } if(ordinateur.getDateInterrompu() != null) {
            	
            	requete.setDate(3, Date.valueOf(ordinateur.getDateInterrompu()));
            	
            } else {
            	
            	requete.setDate(3, null);
            	
            } if(ordinateur.getFabricant() != null) {
            	
            	requete.setLong(4, ordinateur.getFabricant().getId());
            	
            } else {
            	
            	requete.setString(4, null);
            	
            }
            
            requete.setLong(5, ordinateur.getId());
            requete.executeUpdate();
            
            logger.info("update d'un ordinateur effectuée");
            
		} catch(SQLException e) {
			
			throw new RequeteQueryException("Echec de la requete de mise à jour de l'" + ordinateur);
			
		} finally {
			
            if(requete != null) {
            	
                try {
                	
                    requete.close();
                    
                } catch(SQLException e) {
                	
                	throw new RequeteQueryException("Fermeture de la requete de mise à jour d'ordinateur impossible");
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
            
        }
		
	}
	
	//fonction qui supprime un ordinateur
	public void suppressionOrdinateur(long id) throws ConnexionDatabaseException, RequeteQueryException {
		
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase(); 
		PreparedStatement requete = null;
		logger.info("suppression de l'ordinateur numero " + id);
		
		try {
            
            //Formation de la requete QUERY
            requete = con.prepareStatement(prop.getProperty("QUERY_DELETE_ORDINATEUR"));
            requete.setLong(1, id);
            requete.executeUpdate();
            logger.info("suppression d'un ordinateur effectuée");
            
		} catch(SQLException e) {
			
			throw new RequeteQueryException("Echec de la requete de suppression de l'ordinateur: " + id);
			
		} finally {
			
            if(requete != null) {
            	
                try {
                	
                    requete.close();
                    
                } catch(SQLException e) {
                	
                	throw new RequeteQueryException("Fermeture de la requete de suppression d'ordinateur impossible");
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
            
        }
	}
	
	// Fonction de recuperation de resultat de la requete en format List Ordinateur pour qu'elle soit traitable par l'application.
	private List<Ordinateur> recuperationResultatRequete(ResultSet res) throws RequeteQueryException {
		
		List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
		Ordinateur ordinateur;
		
		try {
			
			while(res.next()) {
				
				//Initialisation des variable
            	long id = res.getLong("id");
            	String name = res.getString("name");
				LocalDate dateIntroduit = null;
				LocalDate dateInterrompu = null;
				long fabricantID = res.getLong("company_id");
				String fabricantName = res.getString("company_name");
				Date date = null;
				
				//Recuperation des variables dates si elles existent
				try {
					
					date = (java.sql.Date) res.getDate("introduced");
					
					if(date != null) {
						
						dateIntroduit = date.toLocalDate();
						
					}
					
				} catch(SQLException e) {
					
					throw new RequeteQueryException("Recuperation de la date d'introduction impossible");
					
				} try {
					
					date = res.getDate("discontinued");
					
					if(date != null) {
						
						dateIntroduit = date.toLocalDate();
						
					}
					
				} catch(SQLException e) {
					
					throw new RequeteQueryException("Recuperation de la date d'interruption impossible");
					
				}
				
				//Construction de l'ordinateur final
				if(fabricantID == 0) {
					
					ordinateur = new Ordinateur(id, name, dateIntroduit, dateInterrompu, null);
					
				} else {
					
					ordinateur = new Ordinateur(id, name, dateIntroduit, dateInterrompu, new Entreprise(fabricantID, fabricantName));
				
				}
			    
				//ajout de l'ordinateur à la liste
			    ordinateurs.add(ordinateur);
			    
			}
			
		} catch(SQLException e) {
			
			throw new RequeteQueryException("L'extraction des données du résultat de la requete ne s'est pas déroulé correctement");
			
		}
		
		return ordinateurs;
		
	}
	
}
