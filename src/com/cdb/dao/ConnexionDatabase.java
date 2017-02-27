package com.cdb.dao;

import java.io.FileInputStream;
import java.util.Properties;

public enum ConnexionDatabase {
	
	private final static Properties properties;

	static {
		
		properties = new Properties();

	      FileInputStream input = new FileInputStream(" ");
	      
	      try{
	    	  
	         properties.load(input);
	         
	      }

	              finally{

	         input.close();

	      }
	      
	}
	
	private static ConnexionDatabase instance = null;
	
	private ConnexionDatabase() {

	}
	
	public final static ConnexionDatabase getInstanceConnexionDatabase() {
		
        if (ConnexionDatabase.instance == null) {
        	
           synchronized(ConnexionDatabase.class) {
        	   
             if (ConnexionDatabase.instance == null) {
            	 
            	 ConnexionDatabase.instance = new ConnexionDatabase();
            	 
             }
             
           }
           
        }
        
        return ConnexionDatabase.instance;
    }
	
	
	
}
