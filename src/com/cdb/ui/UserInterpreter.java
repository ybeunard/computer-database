package com.cdb.ui;

import java.util.Scanner;

public class UserInterpreter {

	public final static Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]) {
		
		String arg;
		
		do {
			
		System.out.println("\nVeuillez saisir une commande :\n");
		arg = sc.nextLine();
		
		} while(GestionEntryUser.lectureEntryUser(arg));
		
		sc.close();
		
	}
	
}