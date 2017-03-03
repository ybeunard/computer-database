package com.cdb.dao.Impl.mappers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.entities.Entreprise;
import com.cdb.entities.Ordinateur;
import com.cdb.entities.Ordinateur.OrdinateurBuilder;
import com.cdb.exception.RequeteQueryException;

public enum OrdinateurDaoMapper {

    /** The instance ordinateur mapper. */
    INSTANCE_ORDINATEUR_DAO_MAPPER;

    /**
     * Instantiates a new ordinateur mapper.
     */
    OrdinateurDaoMapper() {

    }

    /** The Constant logger. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(OrdinateurDao.class);

    /**
     * Recuperation resultat requete.
     *
     * @param res
     *            le resultat de la requete à traiter
     * @return un ordinateur
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public Optional<Ordinateur> recuperationOrdinateur(ResultSet res)
            throws RequeteQueryException {

        Optional<Ordinateur> ordinateur = Optional.empty();

        try {

            if (res.next()) {

                String name = res.getString("name");
                OrdinateurBuilder builder = new Ordinateur.OrdinateurBuilder(
                        name);
                long id = res.getLong("id");
                LocalDate dateIntroduit = null;
                LocalDate dateInterrompu = null;
                long fabricantID = res.getLong("company_id");
                String fabricantName = res.getString("company_name");
                Date date = null;

                try {

                    date = res.getDate("introduced");

                    if (date != null) {

                        builder.dateIntroduit(date.toLocalDate());

                    }

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Recuperation de la date d'introduction impossible");

                }

                try {

                    date = res.getDate("discontinued");

                    if (date != null) {

                        dateInterrompu = date.toLocalDate();

                    }

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Recuperation de la date d'interruption impossible");

                }

                if (fabricantID <= 0) {

                    ordinateur = Optional
                            .ofNullable(new Ordinateur.OrdinateurBuilder(name)
                                    .id(id).dateIntroduit(dateIntroduit)
                                    .dateInterrompu(dateInterrompu)
                                    .fabricant(null).build());

                } else {

                    ordinateur = Optional
                            .ofNullable(new Ordinateur.OrdinateurBuilder(name)
                                    .id(id).dateIntroduit(dateIntroduit)
                                    .dateInterrompu(dateInterrompu)
                                    .fabricant(new Entreprise.EntrepriseBuilder(
                                            fabricantName).id(fabricantID)
                                                    .build())
                                    .build());

                }

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "L'extraction des données du résultat de la requete ne s'est pas déroulé correctement");

        }

        return ordinateur;

    }

    /**
     * Recuperation resultat requete.
     *
     * @param res
     *            the res
     * @return the list
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public List<Ordinateur> recuperationListOrdinateur(ResultSet res)
            throws RequeteQueryException {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();

        try {

            while (res.next()) {

                String name = res.getString("name");
                OrdinateurBuilder builder = new Ordinateur.OrdinateurBuilder(
                        name);
                long id = res.getLong("id");
                builder.id(id);
                Date date = null;

                try {

                    date = (java.sql.Date) res.getDate("introduced");

                    if (date != null) {

                        builder.dateIntroduit(date.toLocalDate());

                    }

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Recuperation de la date d'introduction impossible");

                }

                try {

                    date = res.getDate("discontinued");

                    if (date != null) {

                        builder.dateInterrompu(date.toLocalDate());

                    }

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Recuperation de la date d'interruption impossible");

                }

                long fabricantID = res.getLong("company_id");

                if (fabricantID > 0) {

                    String fabricantName = res.getString("company_name");
                    builder.fabricant(
                            new Entreprise.EntrepriseBuilder(fabricantName)
                                    .id(fabricantID).build());

                }

                ordinateurs.add(builder.build());

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "L'extraction des données du résultat de la requete ne s'est pas déroulé correctement");

        }

        return ordinateurs;

    }

    /**
     * @param res
     *            the res
     * @return un entier
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public int recuperationInt(ResultSet res) throws RequeteQueryException {

        int count = 0;

        try {

            if (res.next()) {

                count = res.getInt("count");

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "L'extraction des données du résultat de la requete ne s'est pas déroulé correctement");

        }

        return count;

    }

}
