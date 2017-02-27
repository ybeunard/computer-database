package com.cdb.services.Impl;

import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.services.InterfaceGestionEntreprise;

public enum GestionEntreprise implements InterfaceGestionEntreprise {

	INSTANCE_GESTION_ENTREPRISE;
	
	//Constructeur private
	private GestionEntreprise() {
		
	}
	
	//methode pour recuperer l'instance GestionEntreprise
    public final static GestionEntreprise getInstanceGestionEntreprise() {
        
        return INSTANCE_GESTION_ENTREPRISE;
        
    }
    
    //Fonction qui renvoie l'entreprise demandé
    @Override
    public Entreprise findEntrepriseByID(long id){
    	
    	Entreprise entreprise = null;
    	
    	try {
    		
    		entreprise = EntrepriseDao.getInstanceEntrepriseDao().findEntrepriseByID(id);
    		 
    	} catch(ConnexionDatabaseException | RequeteQueryException e) {
    		
    		e.printStackTrace();
    		
    	}
    	
    	return entreprise;
    	
    }
    
}
