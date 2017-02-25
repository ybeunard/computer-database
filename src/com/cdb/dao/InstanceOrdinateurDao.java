package com.cdb.dao;

import java.util.List;

import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

public interface InstanceOrdinateurDao {

	void createOrdinateur(Ordinateur ordinateur) throws ConnexionDatabaseException, RequeteQueryException;

	List<Ordinateur> findOrdinateur() throws ConnexionDatabaseException, RequeteQueryException;

	List<Ordinateur> findOrdinateurByPage(int numeroPage, int ligneParPage) throws ConnexionDatabaseException, RequeteQueryException;

	Ordinateur findOrdinateurByID(int index) throws ConnexionDatabaseException, RequeteQueryException;

	void updateOrdinateur(Ordinateur ordinateur) throws ConnexionDatabaseException, RequeteQueryException;

	void suppressionOrdinateur(int index) throws ConnexionDatabaseException, RequeteQueryException;

}
