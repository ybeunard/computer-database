package com.cdb.dao;

import java.util.List;

import com.cdb.entities.Ordinateur;

public interface InstanceOrdinateurDao {

	void createOrdinateur(Ordinateur ordinateur);

	List<Ordinateur> findOrdinateur();

	List<Ordinateur> findOrdinateurByPage(int numeroPage, int ligneParPage);

	Ordinateur findOrdinateurByID(int index);

	void updateOrdinateur(Ordinateur ordinateur);

	void suppressionOrdinateur(int index);

}
