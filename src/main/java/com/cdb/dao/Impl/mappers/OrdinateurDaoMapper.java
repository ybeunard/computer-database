package com.cdb.dao.Impl.mappers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            .getLogger(OrdinateurDaoMapper.class);

    /**
     * Recuperation resultat requete.
     *
     * @param res
     *            le resultat de la requete à traiter
     * @return un ordinateur
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public Ordinateur recuperationOrdinateur(ResultSet res)
            throws RequeteQueryException {

        Ordinateur ordinateur = null;

        try {

            if (res.next()) {

                String name = res.getString("name");
                OrdinateurBuilder builder = new Ordinateur.OrdinateurBuilder(
                        name);
                long id = res.getLong("id");
                builder.id(id);
                Date date = null;

                try {

                    date = (java.sql.Date) res.getDate("introduced");

                    if (date != null) {

                        builder.dateIntroduit(
                                Optional.ofNullable(date.toLocalDate()));

                    }

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Recuperation de la date d'introduction impossible");

                }

                try {

                    date = (java.sql.Date) res.getDate("discontinued");

                    if (date != null) {

                        builder.dateInterrompu(
                                Optional.ofNullable(date.toLocalDate()));

                    }

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Recuperation de la date d'interruption impossible");

                }

                long fabricantID = res.getLong("company_id");

                if (fabricantID > 0) {

                    String fabricantName = res.getString("company_name");
                    builder.fabricant(Optional.ofNullable(
                            new Entreprise.EntrepriseBuilder(fabricantName)
                                    .id(fabricantID).build()));

                }

                ordinateur = builder.build();

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

                        builder.dateIntroduit(
                                Optional.ofNullable(date.toLocalDate()));

                    }

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Recuperation de la date d'introduction impossible");

                }

                try {

                    date = (java.sql.Date) res.getDate("discontinued");

                    if (date != null) {

                        builder.dateInterrompu(
                                Optional.ofNullable(date.toLocalDate()));

                    }

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Recuperation de la date d'interruption impossible");

                }

                long fabricantID = res.getLong("company_id");

                if (fabricantID > 0) {

                    String fabricantName = res.getString("company_name");
                    builder.fabricant(Optional.ofNullable(
                            new Entreprise.EntrepriseBuilder(fabricantName)
                                    .id(fabricantID).build()));

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
