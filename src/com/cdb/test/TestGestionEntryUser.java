package com.cdb.test;

import com.cdb.services.GestionEntryUser;

public class TestGestionEntryUser {

	public static void main(String args[]){
		GestionEntryUser.lectureEntryUser("create 'newOrdinateur' introduction 2021/11/12 interruption 2000/07/24");
		GestionEntryUser.lectureEntryUser("create 'newOrdinateur'");
		GestionEntryUser.lectureEntryUser("create 'newOrdinateur' fabricant 3");
		GestionEntryUser.lectureEntryUser("create 'newOrdinateur' fabricant tj");
		
		GestionEntryUser.lectureEntryUser("update 75 name 'newOrdinateur'");
		GestionEntryUser.lectureEntryUser("update 76 introduction 2012/12/12 name 'newOrdinateur' fabricant 9");
		GestionEntryUser.lectureEntryUser("update 77 introduction 2022/12/12 fabricant 9");
		GestionEntryUser.lectureEntryUser("update 79 introduction 2002/12/12 interruption 2002/12/15");
		GestionEntryUser.lectureEntryUser("affiche 75");
		GestionEntryUser.lectureEntryUser("affiche 76");
		GestionEntryUser.lectureEntryUser("affiche 77");
		GestionEntryUser.lectureEntryUser("affiche 79");
	}
}
