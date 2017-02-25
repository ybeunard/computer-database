package com.cdb.dao.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cdb.dao.InterfaceConnexionDatabase;
import com.mysql.jdbc.Connection;
import java.util.Properties;

public enum ConnexionDatabase implements InterfaceConnexionDatabase {

	INSTANCE_CONNEXION_DATABASE;
	
	//Constructeur private
	private ConnexionDatabase() {
		
	}
	
	//methode pour recuperer l'instance ConnexionDatabase
    public final static ConnexionDatabase getInstanceConnexionDatabase() {
        
        return INSTANCE_CONNEXION_DATABASE;
        
    }
	
	private static Properties prop = new Properties();
	
	//Chargement du fichier connexion.properties
	static {
		
		File fProp = new File("computer-database/properties/connexion.properties");
		 
		// Charge le contenu de ton fichier properties dans un objet Properties
		FileInputStream stream = null;
		
		try {
			
			stream = new FileInputStream(fProp);
			prop.load(stream) ;
			
		} catch(IOException e) {

			e.printStackTrace();
			
		}
		
	}
	
	@Override
	public Connection connectDatabase() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
		} catch(InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		Connection con = null;
		
		try {
			
           con = (Connection) DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("LOGIN"), prop.getProperty("PASSWORD"));
		
		} catch(SQLException e) {
			
			closeConnexionDatabase(con);			
			e.printStackTrace();
			
		}
		
		return con;
		
	}
	
	@Override
	public void closeConnexionDatabase(Connection con) {
		
		if(con != null) {
        	
            try {
            	
                con.close();
                
            } catch(SQLException e) {
            	
                e.printStackTrace();
                
            }
            
        }
		
	}
	
}
