package com.cdb.test;

import com.cdb.services.GestionEntryUser;

public class TestGestionEntryUser {

	public static void main(String args[]){
		GestionEntryUser.lectureEntryUser("create 'newOrdinateur' introduction 2021/11/12 interruption 2000/07/24");
		GestionEntryUser.lectureEntryUser("create 'newOrdinateur'");
		GestionEntryUser.lectureEntryUser("create 'newOrdinateur' fabricant 3");
		GestionEntryUser.lectureEntryUser("create 'newOrdinateur' fabricant tj");
	}
}
