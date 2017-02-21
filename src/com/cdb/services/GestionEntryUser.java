package com.cdb.services;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.cdb.dao.EntrepriseDao;
import com.cdb.dao.OrdinateurDao;
import com.cdb.persistance.Ordinateur;
import com.cdb.persistance.Entreprise;

public class GestionEntryUser {

	public static boolean lectureEntryUser(String arg){
		
		String[] splitArray = arg.split(" ", 2);
		
		switch(splitArray[0]){
		
		case "list":
			if(splitArray.length != 2){
				System.out.println("Nombre d'argument incorecte");
				return true;
			}
			affichageListe(splitArray[1]);
			break;
		case "affiche":
			if(splitArray.length != 2){
				System.out.println("Nombre d'argument incorecte");
				return true;
			}
			affichageOrdinateur(splitArray[1]);
			break;
		case "create":
			if(splitArray.length != 2){
				System.out.println("Nombre d'argument incorecte");
				return true;
			}
			Ordinateur ordinateurCreated = createOrdinateur(splitArray[1]);
			if(ordinateurCreated != null){
				OrdinateurDao.getInstanceOrdinateurDao().createOrdinateur(ordinateurCreated);
			}
			break;
		case "update":
			if(splitArray.length != 2){
				System.out.println("Nombre d'argument incorecte");
				return true;
			}
			Ordinateur ordinateurUpdated = updateOrdinateur(splitArray[1]);
			if(ordinateurUpdated != null){
				OrdinateurDao.getInstanceOrdinateurDao().updateOrdinateur(ordinateurUpdated);
			}
			break;
		case "delete":
			if(splitArray.length != 2){
				System.out.println("Nombre d'argument incorecte");
				return true;
			}
			deleteOrdinateur(splitArray[1]);
			break;
		case "help":
			if(splitArray.length == 2){
				help(splitArray[1]);
			}
			else if(splitArray.length == 1){
				System.out.println("Liste des commande :\nliste\naffiche\ncreate\nupdate\ndelete\nhelp\nexit\nPour une description détaillé de chaque commande taper: help [commande]");
			}
			else System.out.println("Nombre d'argument incorecte");
			break;
		case "exit":
			return false;
		default:
			System.out.println("Argument : " + splitArray[0] + " incorrecte, tapez help pour la liste des commandes");
		}
		return true;
	}

	private static void affichageListe(String arg) {
		String[] argArray = arg.split(" ");
		GestionPagination pagination;
		if(argArray.length == 2){
			try{
				pagination = new GestionPagination(Integer.parseInt(argArray[1]));
			}catch(NumberFormatException e){
					System.out.println("Pagination impossible");
					return;
			}
		}
		else{
			pagination = new GestionPagination();
		}
		switch(argArray[0]){
		case "company":
			pagination.pagination(2);
			break;
		case "computer":
			pagination.pagination(1);
			break;
		default:
			System.out.println("Argument : " + arg + " inconnue");
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
	
	//permet de recuperer les arguments et supprimer un ordinateur précis.
		private static void deleteOrdinateur(String arg) {
			
			try{
				int id = Integer.parseInt(arg);
				OrdinateurDao.getInstanceOrdinateurDao().suppressionOrdinateur(id);
			}catch(NumberFormatException e){
				System.out.println("Veuillez donner un id d'ordinateur correct");
				return;
			}
		}
		
		private static void help(String arg){
			switch(arg){
			
			case "liste":
				System.out.println("Syntaxe: list computer/company [nombre de ligne par page");
				System.out.println("Les crochets signifie que l'argument est optionnel.");
				break;
			case "affiche":
				System.out.println("Syntaxe: affiche id_ordinateur");
				break;
			case "create":
				System.out.println("Syntaxe: create 'nom ordinateur' [introduction date_introduction] [interruption date_interruption] [fabricant id_fabricant]");
				System.out.println("Les crochets signifie que l'argument est optionnel.");
				break;
			case "update":
				System.out.println("Syntaxe: update id_ordinateur [name 'nom ordinateur'] [introduction date_introduction] [interruption date_interruption] [fabricant id_fabricant]");
				System.out.println("Les crochets signifie que l'argument est optionnel, il faut au moins un argument optionnel pour modifier.");
				break;
			case "delete":
				System.out.println("Syntaxe: delete id_ordinateur");
				break;
			default:
				System.out.println("Argument : " + arg + " incorrecte, tapez help pour la liste des commandes");
			}
		}
}
