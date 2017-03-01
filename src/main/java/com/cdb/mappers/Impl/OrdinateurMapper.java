package com.cdb.mappers.Impl;

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
import com.cdb.exception.RequeteQueryException;
import com.cdb.mappers.InterfaceOrdinateurMapper;

// TODO: Auto-generated Javadoc
/**
 * The Enum OrdinateurMapper.
 */
public enum OrdinateurMapper implements InterfaceOrdinateurMapper {

    /** The instance ordinateur mapper. */
    INSTANCE_ORDINATEUR_MAPPER;

    /**
     * Instantiates a new ordinateur mapper.
     */
    OrdinateurMapper() {

    }

    /**
     * Gets the instance ordinateur mapper.
     *
     * @return the instance ordinateur mapper
     */
    public static final OrdinateurMapper getInstanceOrdinateurMapper() {

        return INSTANCE_ORDINATEUR_MAPPER;

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
    public Optional<Ordinateur> recuperationResultatRequete(ResultSet res)
            throws RequeteQueryException {

        Optional<Ordinateur> ordinateur = Optional.empty();

        try {

            if (res.next()) {

                long id = res.getLong("id");
                String name = res.getString("name");
                LocalDate dateIntroduit = null;
                LocalDate dateInterrompu = null;
                long fabricantID = res.getLong("company_id");
                String fabricantName = res.getString("company_name");
                Date date = null;

                try {

                    date = res.getDate("introduced");

                    if (date != null) {

                        dateIntroduit = date.toLocalDate();

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
    public Optional<List<Optional<Ordinateur>>> recuperationListResultatRequete(
            ResultSet res) throws RequeteQueryException {

        List<Optional<Ordinateur>> ordinateurs = new ArrayList<Optional<Ordinateur>>();
        Optional<Ordinateur> ordinateur = Optional.empty();

        try {

            while (res.next()) {

                long id = res.getLong("id");
                String name = res.getString("name");
                LocalDate dateIntroduit = null;
                LocalDate dateInterrompu = null;
                long fabricantID = res.getLong("company_id");
                String fabricantName = res.getString("company_name");
                Date date = null;

                try {

                    date = (java.sql.Date) res.getDate("introduced");

                    if (date != null) {

                        dateIntroduit = date.toLocalDate();

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

                ordinateurs.add(ordinateur);

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "L'extraction des données du résultat de la requete ne s'est pas déroulé correctement");

        }

        return Optional.ofNullable(ordinateurs);

    }

    /**
     * @param res
     *            the res
     * @return un entier
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public int recuperationIntResultatRequete(ResultSet res)
            throws RequeteQueryException {

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
