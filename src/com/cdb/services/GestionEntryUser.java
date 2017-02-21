package com.cdb.services;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.cdb.dao.EntrepriseDao;
import com.cdb.dao.OrdinateurDao;
import com.cdb.persistance.Ordinateur;
import com.cdb.persistance.Entreprise;

public class GestionEntryUser {

	public static void lectureEntryUser(String arg){
		
		String[] splitArray = arg.split(" ", 2);
		
		switch(splitArray[0]){
		
		case "liste":
			
		case "afficher":
			
		case "create":
			if(splitArray.length != 2){
				System.out.println("Nombre d'argument incorecte");
				return;
			}
			Ordinateur ordinateur = createOrdinateur(splitArray[1]);
			if(ordinateur != null)OrdinateurDao.getInstanceOrdinateurDao().createOrdinateur(ordinateur);
			break;
		case "update":
			
		case "delete":
			
		case "help":
			
		default:
			System.out.println("Argument : " + splitArray[0] + " incorrecte");
		}
	}
	
	public static Ordinateur createOrdinateur(String args){
		
		String[] argArray = args.split("'", 3);
		Ordinateur ordinateur = new Ordinateur(argArray[1]);
		if(argArray[2].isEmpty()){
			System.out.println("Ordinateur : " +ordinateur.getName() + "\t" + ordinateur.getDateIntroduit() + "\t" + ordinateur.getDateInterrompu());
	        if(ordinateur.getFabricant() != null){
	        	System.out.println(ordinateur.getFabricant().getName() + "\n");
	        }
			return ordinateur;
		}
		argArray = argArray[2].split(" ", 2);
		args = argArray[1];
		while(argArray.length > 1){
			argArray = args.split(" ", 2);
			if(argArray.length != 2){
				System.out.println("Nombre d'argument incorecte " + argArray[0] + argArray.length);
				return null;
			}
			switch(argArray[0]){
			
			case "introduction":
				try {
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			        Date parsed = format.parse(argArray[0]);
					ordinateur.setDateIntroduit(new java.sql.Date(parsed.getTime()));
				} catch (ParseException e) {
					System.out.println("Format de date incorecte marci de respecter la syntaxe suivante : yyyy/MM/dd");
					return null;
				}
				break;
			case "interruption":
				try {
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			        Date parsed = format.parse(argArray[0]);
					ordinateur.setDateInterrompu(new java.sql.Date(parsed.getTime()));
				} catch (ParseException e) {
					System.out.println("Format de date incorecte marci de respecter la syntaxe suivante : yyyy/MM/dd");
					return null;
				}
				break;
			case "fabricant":
				try{
					argArray = argArray[1].split(" ", 2);
					int i = Integer.parseInt(argArray[0]);
					Entreprise fabricant = EntrepriseDao.getInstanceEntrepriseDao().findEntrepriseByID(i);
					if(fabricant != null){
						ordinateur.setFabricant(fabricant);
					}
					else{
						System.out.println("Veuillez donner un id d'entreprise correct");
						return null;
					}
				}catch(NumberFormatException e){
					System.out.println("Veuillez donner un id d'entreprise correct");
					return null;
				}
				break;
			default:
				System.out.println("Argument : " + argArray[1] + " inconnue");
				return null;
			}
			if(argArray.length < 2){
				System.out.println("Ordinateur : " +ordinateur.getName() + "\t" + ordinateur.getDateIntroduit() + "\t" + ordinateur.getDateInterrompu());
		        if(ordinateur.getFabricant() != null){
		        	System.out.println(ordinateur.getFabricant().getName() + "\n");
		        }
				return ordinateur;
			}
			args = argArray[1];
		}
		return null;
	}
}
