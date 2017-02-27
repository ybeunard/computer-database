package com.cdb.dao;

import java.util.List;
import java.util.Optional;

import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

public class TestEntrepriseDao {

    public static void main(String args[])
            throws ConnexionDatabaseException, RequeteQueryException {

        EntrepriseDao dao = EntrepriseDao.getInstanceEntrepriseDao();

        Optional<List<Optional<Entreprise>>> entreprises = dao.findEntreprise();

        System.out.println("Liste des entreprises");

        if (entreprises.isPresent()) {

            for (Optional<Entreprise> entreprise : entreprises.get()) {

                if (entreprise.isPresent()) {

                    System.out.println(entreprise.get());

                }

            }

        }

        Optional<Entreprise> entrepris = dao.findEntrepriseByID(30);

        if (entrepris.isPresent()) {

            System.out.println(entrepris.get());

        }

        Optional<List<Optional<Entreprise>>> entreprises2 = dao
                .findEntrepriseByPage(1, 30);

        if (entreprises2.isPresent()) {

            for (Optional<Entreprise> entreprise : entreprises2.get()) {

                if (entreprise.isPresent()) {

                    System.out.println(entreprise.get());

                }

            }

        }

    }

}
