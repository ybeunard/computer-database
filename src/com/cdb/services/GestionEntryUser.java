package com.cdb.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.cdb.persistance.Ordinateur;

public class GestionEntryUser {

	public static void lectureEntryUser(String arg){
		
		String[] splitArray = arg.split(" ");
		
		switch(splitArray[0]){
		
		case "liste":
			
		case "afficher":
			
		case "create":
			createOrdinateur(splitArray);
			break;
		case "update":
			
		case "delete":
			
		case "help":
			
		default:
			System.out.println("Argument : " + splitArray[0] + " incorrecte");
		}
	}
	
	public static void createOrdinateur(String args[]){
		if(args.length < 2 && args.length%2 == 0){
			System.out.println("Nombre d'argument incorecte");
			return;
		}
		Ordinateur ordinateur = new Ordinateur(args[1]);
		for(int i=2; i<args.length; i=i+2){
			switch(args[i]){
			
			case "introduction":
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
					ordinateur.setDateIntroduit(sdf.parse(args[i]));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			case "interruption":
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
					ordinateur.setDateInterrompu(sdf.parse(args[i]));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			case "fabricant":
				
			default:
				System.out.println("Argument : " + args[i] + " inconnue");
			}
		}
	}
}
