package com.cdb.test;

import java.util.List;

import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.entities.Entreprise;

public class TestEntrepriseDao {

	public static void main(String args[]){
		
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
