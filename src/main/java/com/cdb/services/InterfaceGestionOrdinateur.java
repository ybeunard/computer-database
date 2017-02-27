package com.cdb.services;

import com.cdb.entities.Ordinateur;

public interface InterfaceGestionOrdinateur {
	
	void createOrdinateur(Ordinateur ordinateur);

	Ordinateur findOrdinateurByID(long id);
	
	void updateOrdinateur(Ordinateur ordinateur);

	void suppressionOrdinateur(long id);

}
