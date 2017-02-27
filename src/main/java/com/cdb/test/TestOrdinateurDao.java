package com.cdb.test;

import java.util.List;

import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

public class TestOrdinateurDao {

	public static void main(String args[]) throws ConnexionDatabaseException, RequeteQueryException{
		EntrepriseDao entrepriseDao = EntrepriseDao.getInstanceEntrepriseDao();
		OrdinateurDao ordinateurDao = OrdinateurDao.getInstanceOrdinateurDao();
        List<Ordinateur> ordinateurs = ordinateurDao.findOrdinateur();

        System.out.println("Liste des ordinateurs");
        
        for (Ordinateur ordinateur : ordinateurs) {
        	
            System.out.println(ordinateur.getId() + "\t" + ordinateur.getName() + "\t" + ordinateur.getDateIntroduit() + "\t" + ordinateur.getDateInterrompu());
            
            if(ordinateur.getFabricant() != null){
            	
            	System.out.println(ordinateur.getFabricant().getName() + "\n");
            }
            
        }
        
        Ordinateur ordinateur1 = ordinateurDao.findOrdinateurByID(4);
        System.out.println("Ordinateur numero 4 :" +ordinateur1.getName() + "\t" + ordinateur1.getDateIntroduit() + "\t" + ordinateur1.getDateInterrompu());
        
        if(ordinateur1.getFabricant() != null){
        	
        	System.out.println(ordinateur1.getFabricant().getName() + "\n");
        
        }
        
        Ordinateur ordinateur2 = ordinateurDao.findOrdinateurByID(15);
        System.out.println("Ordinateur numero 15 :" +ordinateur2.getName() + "\t" + ordinateur2.getDateIntroduit() + "\t" + ordinateur2.getDateInterrompu());
        
        if(ordinateur2.getFabricant() != null){
        	
        	System.out.println(ordinateur2.getFabricant().getName() + "\n");
        
        }
        
        ordinateurDao.suppressionOrdinateur(4);
        
        if(ordinateurDao.findOrdinateurByID(4) == null){
        	
        	System.out.println("suppression reussi");
        
        }
        
        ordinateurDao.suppressionOrdinateur(15);
        
        if(ordinateurDao.findOrdinateurByID(4) == null){
        	
        	System.out.println("suppression reussi");
        
        }
        
        ordinateur1.setFabricant(null);
        ordinateurDao.createOrdinateur(ordinateur1);
        ordinateurDao.createOrdinateur(ordinateur2);
        Ordinateur ordinateur3 = ordinateurDao.findOrdinateurByID(10);
        System.out.println("Ordinateur numero 10 :" +ordinateur3.getName() + "\t" + ordinateur3.getDateIntroduit() + "\t" + ordinateur3.getDateInterrompu());
        
        if(ordinateur3.getFabricant() != null){
        	
        	System.out.println(ordinateur3.getFabricant().getName() + "\n");
        
        }
        
        ordinateur3.setName("ASUS REPUBLIC OF GAMER");
        ordinateur3.setFabricant(entrepriseDao.findEntrepriseByID(5));
        ordinateurDao.updateOrdinateur(ordinateur3);
        ordinateur3 = ordinateurDao.findOrdinateurByID(10);
        System.out.println("Ordinateur numero 10 :" +ordinateur3.getName() + "\t" + ordinateur3.getDateIntroduit() + "\t" + ordinateur3.getDateInterrompu());
        
        if(ordinateur3.getFabricant() != null){
        	
        	System.out.println(ordinateur3.getFabricant().getName() + "\n");
        
        }
        
	}
	
}
