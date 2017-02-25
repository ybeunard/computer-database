package com.cdb.services.Impl;

import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.services.InterfaceGestionOrdinateur;

public enum GestionOrdinateur implements InterfaceGestionOrdinateur {

	INSTANCE_GESTION_ORDINATEUR;
	
	//Constructeur private
	private GestionOrdinateur() {
		
	}
	
	//methode pour recuperer l'instance GestionOrdinateur
    public final static GestionOrdinateur getInstanceGestionOrdinateur() {
        
        return INSTANCE_GESTION_ORDINATEUR;
        
    }
    
    //Fonction qui creer un nouvel ordinateur
    @Override
    public void createOrdinateur(Ordinateur ordinateur) {
    	
    	try {
    		
    	    OrdinateurDao.getInstanceOrdinateurDao().createOrdinateur(ordinateur);
   		 
	   	} catch(ConnexionDatabaseException | RequeteQueryException e) {
	   		
	   		e.printStackTrace();
	   		
	   	}
    	
    }
    
    //Fonction qui renvoie l'ordinateur demandé
    @Override
    public Ordinateur findOrdinateurByID(long id){
    	
    	Ordinateur ordinateur = null;
    	
    	try {
    		
    		 ordinateur =OrdinateurDao.getInstanceOrdinateurDao().findOrdinateurByID(id);
    		 
    	} catch(ConnexionDatabaseException | RequeteQueryException e) {
    		
    		e.printStackTrace();
    		
    	}
    	
    	return ordinateur;
    	
    }
    
    //Fonction qui modifie un ordinateur existant
    @Override
    public void updateOrdinateur(Ordinateur ordinateur) {
    	
    	try {
    		
    		OrdinateurDao.getInstanceOrdinateurDao().updateOrdinateur(ordinateur);
   		 
	   	} catch(ConnexionDatabaseException | RequeteQueryException e) {
	   		
	   		e.printStackTrace();
	   		
	   	}

    }
    
    //Fonction qui supprime un ordinateur existant
    @Override
    public void suppressionOrdinateur(long id) {
    	
    	try {
    		
    		OrdinateurDao.getInstanceOrdinateurDao().suppressionOrdinateur(id);
   		 
	   	} catch(ConnexionDatabaseException | RequeteQueryException e) {
	   		
	   		e.printStackTrace();
	   		
	   	}
    	
    }
    
    
    
}
