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
			
		case "affiche":
			if(splitArray.length != 2){
				System.out.println("Nombre d'argument incorecte");
				return;
			}
			affichageOrdinateur(splitArray[1]);
			break;
		case "create":
			if(splitArray.length != 2){
				System.out.println("Nombre d'argument incorecte");
				return;
			}
			Ordinateur ordinateurCreated = createOrdinateur(splitArray[1]);
			if(ordinateurCreated != null){
				OrdinateurDao.getInstanceOrdinateurDao().createOrdinateur(ordinateurCreated);
			}
			break;
		case "update":
			if(splitArray.length != 2){
				System.out.println("Nombre d'argument incorecte");
				return;
			}
			Ordinateur ordinateurUpdated = updateOrdinateur(splitArray[1]);
			if(ordinateurUpdated != null){
				OrdinateurDao.getInstanceOrdinateurDao().updateOrdinateur(ordinateurUpdated);
			}
			break;
		case "delete":
			
		case "help":
			
		default:
			System.out.println("Argument : " + splitArray[0] + " incorrecte, tapez help pour la liste des commandes");
		}
	}

	//permet de recuperer les arguments et de creer un ordinateur.
	private static Ordinateur createOrdinateur(String args){
		
		String[] argArray = args.split("'", 3);
		Ordinateur ordinateur = new Ordinateur(argArray[1]);
		if(argArray[2].isEmpty()){
			return ordinateur;
		}
		argArray = argArray[2].split(" ", 2);
		args = argArray[1];
		while(argArray.length > 1){
			argArray = args.split(" ", 2);
			if(argArray.length != 2){
				System.out.println("Nombre d'argument incorecte ");
				return null;
			}
			switch(argArray[0]){
			
			case "introduction":
				try {
					argArray = argArray[1].split(" ", 2);
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
					argArray = argArray[1].split(" ", 2);
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
					int id = Integer.parseInt(argArray[0]);
					Entreprise fabricant = EntrepriseDao.getInstanceEntrepriseDao().findEntrepriseByID(id);
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
				return ordinateur;
			}
			args = argArray[1];
		}
		System.out.println("Nombre d'argument incorecte ");
		return null;
	}
	
	//permet de recuperer les arguments et d'afficher un ordinateur précis en détails.
	private static void affichageOrdinateur(String arg) {
		
		Ordinateur ordinateur;
		try{
			int id = Integer.parseInt(arg);
			ordinateur = OrdinateurDao.getInstanceOrdinateurDao().findOrdinateurByID(id);
			if(ordinateur == null){
				System.out.println("Veuillez donner un id d'ordinateur correct");
				return;
			}
		}catch(NumberFormatException e){
			System.out.println("Veuillez donner un id d'ordinateur correct");
			return;
		}
		System.out.print("Ordinateur numero "+ ordinateur.getId() + " : " + ordinateur.getName() + "\t" + ordinateur.getDateIntroduit() + "\t" + ordinateur.getDateInterrompu() + "\t");
        if(ordinateur.getFabricant() != null){
        	System.out.println(ordinateur.getFabricant().getName() + "\n");
        }
        else{
        	System.out.println("NULL");
        }
	}
	
	//permet de recuperer les arguments et de update un ordinateur.
	private static Ordinateur updateOrdinateur(String args) {
		String[] argArray = args.split(" ", 2);
		if(argArray.length != 2){
			System.out.println("Nombre d'argument incorecte ");
			return null;
		}
		Ordinateur ordinateur;
		try{
			int id = Integer.parseInt(argArray[0]);
			ordinateur = OrdinateurDao.getInstanceOrdinateurDao().findOrdinateurByID(id);
			if(ordinateur == null){
				System.out.println("Veuillez donner un id d'ordinateur correct");
				return null;
			}
		}catch(NumberFormatException e){
			System.out.println("Veuillez donner un id d'ordinateur correct");
			return null;
		}
		args = argArray[1];
		while(argArray.length > 1){
			argArray = args.split(" ", 2);
			if(argArray.length != 2){
				System.out.println("Nombre d'argument incorecte " + argArray.length);
				return null;
			}
			switch(argArray[0]){
			
			case "name":
				argArray = argArray[1].split(" ", 2);
				argArray = argArray[0].split("'", 3);
				ordinateur.setName(argArray[1]);
				if(argArray[2].isEmpty()){
					return ordinateur;
				}
				break;
			case "introduction":
				try {
					argArray = argArray[1].split(" ", 2);
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
					argArray = argArray[1].split(" ", 2);
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
					int id = Integer.parseInt(argArray[0]);
					Entreprise fabricant = EntrepriseDao.getInstanceEntrepriseDao().findEntrepriseByID(id);
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
				return ordinateur;
			}
			args = argArray[1];
		}
		System.out.println("Nombre d'argument incorecte ");
		return null;
	}
}
