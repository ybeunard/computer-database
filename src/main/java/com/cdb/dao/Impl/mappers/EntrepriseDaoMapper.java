package com.cdb.dao.Impl.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.entities.Entreprise;
import com.cdb.exception.RequeteQueryException;

public enum EntrepriseDaoMapper {

    /** The instance entreprise mapper. */
    INSTANCE_ENTREPRISE_DAO_MAPPER;

    /**
     * Instantiates a new entreprise mapper.
     */
    EntrepriseDaoMapper() {

    }

    /** The Constant logger. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(OrdinateurDao.class);

    /**
     * @param res
     *            le resultat de la requete à traiter
     * @return une entreprise
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public Optional<Entreprise> recupertationEntreprise(ResultSet res)
            throws RequeteQueryException {

        Optional<Entreprise> entreprise = Optional.empty();

        try {

            if (res.next()) {

                long id = res.getLong("id");
                String name = res.getString("name");
                entreprise = Optional.ofNullable(
                        new Entreprise.EntrepriseBuilder(name).id(id).build());

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return entreprise;

    }

    /**
     * Recuperation resultat requete.
     *
     * @param res
     *            resultat d'une requete
     * @return une liste d'entreprise
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public List<Entreprise> recuperationListEntreprise(ResultSet res)
            throws RequeteQueryException {

        List<Entreprise> entreprises = new ArrayList<Entreprise>();

        try {

            while (res.next()) {

                long id = res.getLong("id");
                String name = res.getString("name");
                entreprises.add(
                        new Entreprise.EntrepriseBuilder(name).id(id).build());

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "L'extraction des données du résultat de la requete ne s'est pas déroulé correctement");

        }

        return entreprises;

    }
}
