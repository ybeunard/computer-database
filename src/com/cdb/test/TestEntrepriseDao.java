package com.cdb.test;

import java.util.List;

<<<<<<< HEAD
import com.cdb.dao.EntrepriseDao;
import com.cdb.entities.Entreprise;
=======
import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
>>>>>>> d91cb8ed6581f2395b388995871dc39a4f142843

public class TestEntrepriseDao {

	public static void main(String args[]) throws ConnexionDatabaseException, RequeteQueryException{
		
		EntrepriseDao dao = EntrepriseDao.getInstanceEntrepriseDao();
		
		List<Entreprise> entreprises = dao.findEntreprise();
		
		System.out.println("Liste des entreprises");
		
	    for(Entreprise entreprise : entreprises) {
	    	
	        System.out.println(entreprise);
	        
	    }
	    
	    System.out.println(dao.findEntrepriseByID(30));
	    
	    for(Entreprise entreprise : dao.findEntrepriseByPage(1, 30)) {
	    	
	        System.out.println(entreprise);
	        
	    }
	    
	}
	
}
