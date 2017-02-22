package com.cdb.test;

import java.util.List;

import com.cdb.dao.EntrepriseDao;
import com.cdb.persistance.Entreprise;

public class TestEntrepriseDao {

	public static void main(String args[]){
		EntrepriseDao entrepriseDao = EntrepriseDao.getInstanceEntrepriseDao();
		
		List<Entreprise> entreprises = entrepriseDao.findEntreprise();
		
		System.out.println("Liste des entreprises");
	    for(Entreprise entreprise : entreprises) {
	        System.out.println(entreprise.getId() + "\t" + entreprise.getName());
	    }
	}
}
