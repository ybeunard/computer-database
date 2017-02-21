package com.cdb.ui;

import java.util.Scanner;

import com.cdb.services.GestionEntryUser;

public class UserInterpreter {

	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);
		String arg;
		do{
		System.out.println("\nVeuillez saisir une commande :\n");
		arg = sc.nextLine();
		}while(GestionEntryUser.lectureEntryUser(arg));
		sc.close();
	}
}
