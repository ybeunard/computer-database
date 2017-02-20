package com.cdb.test;

import java.util.List;

import com.cdb.dao.OrdinateurDao;
import com.cdb.persistance.Ordinateur;

public class TestOrdinateurDao {

	public static void main(String args[]){
		OrdinateurDao ordinateurDao = OrdinateurDao.getInstanceOrdinateurDao();
        List<Ordinateur> ordinateurs = ordinateurDao.findOrdinateur();

        System.out.println("Liste des ordinateurs");
        for (Ordinateur ordinateur : ordinateurs) {
            System.out.println(ordinateur.getName() + "\t" + ordinateur.getDateIntroduit() + "\t" + ordinateur.getDateInterrompu());
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
        
	}
}
