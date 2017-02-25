package com.cdb.dao;

import java.util.List;

import com.cdb.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

public interface InterfaceEntrepriseDao {

	List<Entreprise> findEntreprise() throws ConnexionDatabaseException, RequeteQueryException;

	List<Entreprise> findEntrepriseByPage(int numeroPage, int ligneParPage) throws ConnexionDatabaseException, RequeteQueryException;

	Entreprise findEntrepriseByID(long index) throws ConnexionDatabaseException, RequeteQueryException;

}
