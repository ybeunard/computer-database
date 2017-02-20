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
        
	}
}
