package com.cdb.dao.Impl.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.model.entities.Entreprise;
import com.cdb.exception.RequeteQueryException;

/**
 * The Class EntrepriseDaoMapper.
 */
public class EntrepriseDaoMapper {

    /**
     * Instantiates a new entreprise dao mapper.
     */
    private EntrepriseDaoMapper() {

    }

    /** The Constant logger. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(OrdinateurDao.class);

    /**
     * Recupertation entreprise.
     *
     * @param res
     *            le resultat de la requete à traiter
     * @return une entreprise
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public static Optional<Entreprise> recupertationEntreprise(ResultSet res)
            throws RequeteQueryException {

        Optional<Entreprise> entreprise = Optional.empty();
        LOGGER.info("Mapping Optional Entreprise depuis resultSet");

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
    public static List<Entreprise> recuperationListEntreprise(ResultSet res)
            throws RequeteQueryException {

        List<Entreprise> entreprises = new ArrayList<Entreprise>();
        LOGGER.info("Mapping List Entreprise depuis resultSet");

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
