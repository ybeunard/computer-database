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

import com.cdb.dao.InstanceOrdinateurDao;
import com.cdb.entities.Entreprise;
import com.cdb.entities.Ordinateur;

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
    
    //fonction qui cree un ordinateur dans la BDD
	@Override
    public void createOrdinateur(Ordinateur ordinateur) {
		
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase();; 
		PreparedStatement requete = null;
		
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
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        } finally {
        	
            if(requete != null) {
            	
                try {
                	
                    requete.close();
                    
                } catch(SQLException e) {
                	
                    e.printStackTrace();
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
            
        }
		
    }
    
    // Fonction qui recupere la liste de tous les ordinateurs
	@Override
	public List<Ordinateur> findOrdinateur() {
		
		List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
		
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase(); 
		Statement stmt = null;
		
		try {
            
            //Formation de la requete QUERY
            stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(prop.getProperty("QUERY_FIND_ORDINATEURS"));
            
            //recuperation des resultats
            ordinateurs = recuperationResultatRequete(rset);
            

        } catch(SQLException e) {
        	
            e.printStackTrace();
            
        } finally {
        	
            if(stmt != null) {
            	
                try {
                	
                    stmt.close();
                    
                } catch(SQLException e) {
                	
                    e.printStackTrace();
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);;
            
        }
		
		return ordinateurs;
		
	}
	
	// Fonction qui recupere la liste de tous les ordinateurs d'une page
	@Override
	public List<Ordinateur> findOrdinateurByPage(int numeroPage, int ligneParPage) {
		
		List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
		
		//initialisation des bornes pour la requete QUERY
		int limit = ligneParPage;
		int offset = (numeroPage-1)*ligneParPage;
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase(); 
		PreparedStatement requete = null;
		
		try {
            
            //Formation de la requete QUERY
            requete = con.prepareStatement(prop.getProperty("QUERY_FIND_ORDINATEURS_BY_PAGE"));
            requete.setInt(1, limit);
            requete.setInt(2, offset);
            ResultSet res = requete.executeQuery();
            
          //recuperation des resultats
            ordinateurs = recuperationResultatRequete(res);
            
        } catch(SQLException e) {
        	
            e.printStackTrace();
            
        } finally {
        	
            if(requete != null) {
            	
                try {
                	
                	requete.close();
                	
                } catch(SQLException e) {
                	
                    e.printStackTrace();
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
            
        }
		
		return ordinateurs;
		
	}
	
	// Fonction qui recupere un ordinateur via son ID
	@Override
	public Ordinateur findOrdinateurByID(int index) {
		
		Ordinateur ordinateur = null;
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase(); 
		PreparedStatement requete = null;
		
		try {
            
            //Formation de la requete QUERY
            requete = con.prepareStatement(prop.getProperty("QUERY_FIND_ORDINATEURS_BY_ID"));
            requete.setInt(1, index);
            ResultSet res = requete.executeQuery();
            
            //Traitement du resultat si il existe pour recuperer un objet de type Ordinateur
            if(res.next()){
            	
            	//Initialisation des variable
            	int id = res.getInt("id");
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
					
					e.printStackTrace();
					
				} try {
					
					date = res.getDate("discontinued");
					
					if(date != null) {
						
						dateIntroduit = date.toLocalDate();
						
					}
					
				} catch(SQLException e) {
					
					e.printStackTrace();
					
				}
				
				//Construction de l'ordinateur final
				if(fabricantID == 0) {
					
					ordinateur = new Ordinateur(id, name, dateIntroduit, dateInterrompu, null);
					
				} else {
					
					ordinateur = new Ordinateur(id, name, dateIntroduit, dateInterrompu, new Entreprise(fabricantID, fabricantName));
				
				}   
				
            }
            
        } catch(SQLException e) {
        	
            e.printStackTrace();
            
        } finally {
        	
            if(requete != null) {
            	
                try {
                	
                    requete.close();
                    
                } catch(SQLException e) {
                	
                    e.printStackTrace();
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
            
        }
		
		return ordinateur;
		
	}
	
	//Fonction de mise à jour d'un ordinateur
	@Override
	public void updateOrdinateur(Ordinateur ordinateur) {
		
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase(); 
		PreparedStatement requete = null;
		
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
            
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
            if(requete != null) {
            	
                try {
                	
                    requete.close();
                    
                } catch(SQLException e) {
                	
                    e.printStackTrace();
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
            
        }
		
	}
	
	//fonction qui supprime un ordinateur
	@Override
	public void suppressionOrdinateur(int index) {
		
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase(); 
		PreparedStatement requete = null;
		
		try {
            
            //Formation de la requete QUERY
            requete = con.prepareStatement(prop.getProperty("QUERY_DELETE_ORDINATEUR"));
            requete.setInt(1, index);
            requete.executeUpdate();
            
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
            if(requete != null) {
            	
                try {
                	
                    requete.close();
                    
                } catch(SQLException e) {
                	
                    e.printStackTrace();
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
            
        }
	}
	
	// Fonction de recuperation de resultat de la requete en format List Ordinateur pour qu'elle soit traitable par l'application.
	private List<Ordinateur> recuperationResultatRequete(ResultSet res) {
		
		List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
		Ordinateur ordinateur;
		
		try {
			
			while(res.next()) {
				
				//Initialisation des variable
            	int id = res.getInt("id");
            	String name = res.getString("name");
				LocalDate dateIntroduit = null;
				LocalDate dateInterrompu = null;
				Integer fabricantID = res.getInt("company_id");
				String fabricantName = res.getString("company_name");
				Date date = null;
				
				//Recuperation des variables dates si elles existent
				try {
					
					date = res.getDate("introduced");
					
					if(date != null) {
						
						dateIntroduit = date.toLocalDate();
						
					}
					
				} catch(SQLException e) {
					
					e.printStackTrace();
					
				} try {
					
					date = res.getDate("discontinued");
					
					if(date != null) {
						
						dateIntroduit = date.toLocalDate();
						
					}
					
				} catch(SQLException e) {
					
					e.printStackTrace();
					
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
			
			e.printStackTrace();
			
		}
		
		return ordinateurs;
		
	}
	
}

