package com.cdb.dao;

import java.util.List;
import java.util.Optional;

import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.entities.Entreprise;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

public class TestOrdinateurDao {

    public static void main(String args[])
            throws ConnexionDatabaseException, RequeteQueryException {
        EntrepriseDao entrepriseDao = EntrepriseDao.getInstanceEntrepriseDao();
        OrdinateurDao ordinateurDao = OrdinateurDao.getInstanceOrdinateurDao();
        Optional<List<Optional<Ordinateur>>> ordinateurs = ordinateurDao
                .findOrdinateur();

        System.out.println("Liste des ordinateurs");

        if (ordinateurs.isPresent()) {

            for (Optional<Ordinateur> ordinateur : ordinateurs.get()) {

                if (ordinateur.isPresent()) {

                    System.out.println(ordinateur.get());

                }

            }

        }

        Optional<Ordinateur> ordinateur1 = ordinateurDao.findOrdinateurByID(4);

        if (ordinateur1.isPresent()) {

            System.out.println("Ordinateur numero 4 :" + ordinateur1.get());

        }

        Optional<Ordinateur> ordinateur2 = ordinateurDao.findOrdinateurByID(15);

        if (ordinateur2.isPresent()) {

            System.out.println("Ordinateur numero 15 :" + ordinateur2.get());

        }

        ordinateurDao.suppressionOrdinateur(20);

        if (!ordinateurDao.findOrdinateurByID(4).isPresent()) {

            System.out.println("suppression reussi");

        }

        ordinateurDao.suppressionOrdinateur(25);

        if (!ordinateurDao.findOrdinateurByID(15).isPresent()) {

            System.out.println("suppression reussi");

        }

        ordinateurDao.createOrdinateur(ordinateur1.get());
        ordinateurDao.createOrdinateur(ordinateur2.get());
        Optional<Ordinateur> ordinateur3 = ordinateurDao.findOrdinateurByID(10);

        if (ordinateur3.isPresent()) {

            System.out.println("Ordinateur numero 10 :" + ordinateur3.get());
            ordinateur3.get().setName("ASUS REPUBLIC OF GAMER");

            Optional<Entreprise> entreprise = entrepriseDao
                    .findEntrepriseByID(5);

            if (entreprise.isPresent()) {

                ordinateur3.get().setFabricant(entreprise.get());

            }

            ordinateurDao.updateOrdinateur(ordinateur3.get());

        }

        ordinateur3 = ordinateurDao.findOrdinateurByID(10);

        if (ordinateur3.isPresent()) {

            System.out.println("Ordinateur numero 10 :" + ordinateur3.get());

        }

    }

}
