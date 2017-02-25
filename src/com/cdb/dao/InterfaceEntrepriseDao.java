package com.cdb.dao;

import java.util.List;

import com.cdb.entities.Entreprise;

public interface InterfaceEntrepriseDao {

	List<Entreprise> findEntreprise();

	List<Entreprise> findEntrepriseByPage(int numeroPage, int ligneParPage);

	Entreprise findEntrepriseByID(long index);

}
