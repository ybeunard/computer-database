package com.cdb.services;

import java.util.List;

import com.cdb.persistance.Entreprise;
import com.cdb.persistance.Ordinateur;

public class GestionPagination {

	List<Object> page;
	
	private void affichagePage(){
		for(Object objet : page){
			if(objet instanceof Ordinateur){
				Ordinateur ordinateur = (Ordinateur)objet;
				System.out.print("Ordinateur numero "+ ordinateur.getId() + " : " + ordinateur.getName() + "\t" + ordinateur.getDateIntroduit() + "\t" + ordinateur.getDateInterrompu() + "\t");
		        if(ordinateur.getFabricant() != null){
		        	System.out.println(ordinateur.getFabricant().getName() + "\n");
		        }
		        else{
		        	System.out.println("NULL");
		        }
			}
			if(objet instanceof Entreprise){
				Entreprise entreprise = (Entreprise)objet;
				System.out.println(entreprise.getId() + "\t" + entreprise.getName());
			}
		}
	}
}
