package com.cdb.dao.Impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cdb.dao.InterfaceConnexionDatabase;
import com.cdb.exception.ConnexionDatabaseException;
import com.mysql.jdbc.Connection;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author excilys
 *
 */
public enum ConnexionDatabase implements InterfaceConnexionDatabase {

    /**
     *
     */
    INSTANCE_CONNEXION_DATABASE;

    /**
     *
     */
    private ConnexionDatabase() {

    }

    /**
     *
     * @return
     */
    public final static ConnexionDatabase getInstanceConnexionDatabase() {

        return INSTANCE_CONNEXION_DATABASE;

    }
	
	private static Properties prop = new Properties();
	
	//Chargement du fichier connexion.properties
	static {
		 
		// Charge le contenu de ton fichier properties dans un objet Properties
		FileInputStream stream = null;
		
		try {
			
			stream = new FileInputStream("/home/excilys/eclipse_workspace/computerDatabase/src/main/resources/connexion.properties");
			prop.load(stream) ;
			
		} catch(IOException e) {

			e.printStackTrace();
			
		}
		
	}
	
	public final static Logger logger = LoggerFactory.getLogger(ConnexionDatabase.class);
	
	public Connection connectDatabase() throws ConnexionDatabaseException {
		
		try {
			
			Class.forName(prop.getProperty("nameDriver")).newInstance();
			
		} catch(InstantiationException e) {
			
			throw new ConnexionDatabaseException("Impossible de charger le Driver " + prop.getProperty("nameDriver"));

		} catch(IllegalAccessException e) {
			
			throw new ConnexionDatabaseException("Impossible de charger le Driver " + prop.getProperty("nameDriver"));
			
		} catch(ClassNotFoundException e) {
			
			throw new ConnexionDatabaseException("Impossible de charger le Driver " + prop.getProperty("nameDriver"));
			
		}
		
		logger.info("Tentative de connexion");
		Connection con = null;
		
		try {
			
           con = (Connection) DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("LOGIN"), prop.getProperty("PASSWORD"));
		
		} catch(SQLException e) {
					
			throw new ConnexionDatabaseException("Connexion au serveur " + prop.getProperty("URL") + " impossible", con);
			
		}
		
		logger.info("connexion effectuée");
		return con;
		
	}
	
	public void closeConnexionDatabase(Connection con) throws ConnexionDatabaseException {
		
		if(con != null) {
        	
			logger.info("tentative de deconnexion");
			
            try {
            	
                con.close();
                
            } catch(SQLException e) {
            	
            	throw new ConnexionDatabaseException("Deconnexion au serveur impossible");
                
            }
            
            logger.info("deconnexion effectuée");
            
        }
		
	}
	
}
