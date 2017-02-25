package com.cdb.dao.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cdb.dao.InterfaceEntrepriseDao;
import com.cdb.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.mysql.jdbc.Connection;

public enum EntrepriseDao implements InterfaceEntrepriseDao {
	
	INSTANCE_ENTREPRISE_DAO;
	
	//Constructeur private
    private EntrepriseDao() {

    }
    
    //methode pour recuperer l'instance EntrepriseDao
    public final static EntrepriseDao getInstanceEntrepriseDao() {
    	
    	return INSTANCE_ENTREPRISE_DAO;
   
    }
    
    private static Properties prop = new Properties();
	
	//Chargement du fichier query_entreprises.properties
	static {
		
		File fProp = new File("computer-database/properties/query_entreprises.properties");
		 
		// Charge le contenu de ton fichier properties dans un objet Properties
		FileInputStream stream = null;
		
		try {
			
			stream = new FileInputStream(fProp);
			prop.load(stream) ;
			
		} catch(IOException e) {

			e.printStackTrace();
			
		}
		
	}   
    
    // Fonction qui recupere la liste de tous les entreprises
	@Override
	public List<Entreprise> findEntreprise() throws ConnexionDatabaseException, RequeteQueryException {
		
		List<Entreprise> entreprises = new ArrayList<Entreprise>();
		
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase(); 
		Statement stmt = null;
		
		try {
            
            //Formation de la requete QUERY
            stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(prop.getProperty("QUERY_FIND_ENTREPRISES"));
            
            //recuperation des resultats
            entreprises = recuperationResultatRequete(rset);
            
        } catch(SQLException e) {
        	
        	throw new RequeteQueryException("Echec de la requete de recherche d'entreprise");
            
        } finally {
        	
            if(stmt != null) {
            	
                try {
                	
                    stmt.close();
                    
                } catch(SQLException e) {
                	
                	throw new RequeteQueryException("Fermeture de la requete de recherche d'entreprise impossible");
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
            
        }
		
		return entreprises;
	}
	
	// Fonction qui recupere la liste de toutes les entreprises d'une page
	@Override
	public List<Entreprise> findEntrepriseByPage(int numeroPage, int ligneParPage) throws ConnexionDatabaseException, RequeteQueryException {
		
		List<Entreprise> entreprises = new ArrayList<Entreprise>();
		
		//initialisation des bornes pour la requete QUERY
		int limit = ligneParPage;
		int offset = (numeroPage-1)*ligneParPage;
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase(); 
		PreparedStatement requete = null;
		
		try {
            
          //Formation de la requete QUERY
            requete = con.prepareStatement(prop.getProperty("QUERY_FIND_ENTREPRISES_BY_PAGE"));
            requete.setInt(1, limit);
            requete.setInt(2, offset);
            ResultSet res = requete.executeQuery();
            
            //recuperation des resultats
            entreprises = recuperationResultatRequete(res);
            
        } catch(SQLException e) {
        	
        	throw new RequeteQueryException("Echec de la requete de recherche par page d'entreprise");
            
        } finally {
        	
            if(requete != null) {
            	
                try {
                	
                	requete.close();
                	
                } catch(SQLException e) {
                	
                	throw new RequeteQueryException("Fermeture de la requete de recherche d'entreprise par page impossible");
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
            
        }
		
		return entreprises;
		
	}

	// Fonction qui recupere une entreprise via son ID
	@Override
	public Entreprise findEntrepriseByID(long index) throws ConnexionDatabaseException, RequeteQueryException {
		
		Entreprise entreprise = null;
		Connection con = ConnexionDatabase.getInstanceConnexionDatabase().connectDatabase(); 
		PreparedStatement stmt = null;
		
		try {
            
            //Formation de la requete QUERY
            stmt = con.prepareStatement(prop.getProperty("QUERY_FIND_ENTREPRISES_BY_ID"));
            stmt.setLong(1, index);
            ResultSet res = stmt.executeQuery();
            
            //Traitement du resultat si il existe pour recuperer un objet de type Entreprise
            if(res.next()){
            	
            	//Construction de l'entreprise final
            	entreprise = new Entreprise(res.getInt("id"), res.getString("name"));
            
            }
            
        } catch(SQLException e) {
        	
        	throw new RequeteQueryException("Echec de la requete de recherche de l'entreprise numero:" + index);
            
        } finally {
        	
            if(stmt != null) {
            	
                try {
                	
                    stmt.close();
                    
                } catch(SQLException e) {
                	
                	throw new RequeteQueryException("Fermeture de la requete de recherche d'entreprise par ID impossible");
                    
                }
                
            }
            
            ConnexionDatabase.getInstanceConnexionDatabase().closeConnexionDatabase(con);
            
        }
		
		return entreprise;
		
	}

	// Fonction de recuperation de resultat de la requete en format List Entreprise pour qu'elle soit traitable par l'application.
	private List<Entreprise> recuperationResultatRequete(ResultSet rset) throws RequeteQueryException {
		
		List<Entreprise> entreprises = new ArrayList<Entreprise>();
		
		try {
			
			while(rset.next()) {
				
				//Construction de l'entreprise final + ajout à la liste
				entreprises.add(new Entreprise(rset.getLong("id"), rset.getString("name")));
			
			}
			
		}catch(SQLException e){
			
			throw new RequeteQueryException("L'extraction des données du résultat de la requete ne s'est pas déroulé correctement");
			
		}
		
		return entreprises;
		
	}
	
}
