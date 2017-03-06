package com.cdb.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.dto.EntrepriseDto;
import com.cdb.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.mappers.EntrepriseDtoMapper;
import com.cdb.services.InterfaceGestionEntreprise;

/**
 * The Enum GestionEntreprise.
 */
public enum GestionEntreprise implements InterfaceGestionEntreprise {

    /** The instance gestion entreprise. */
    INSTANCE_GESTION_ENTREPRISE;

    /**
     * Instantiates a new gestion entreprise.
     */
    GestionEntreprise() {

    }

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(GestionEntreprise.class);

    /**
     * Find entreprise.
     *
     * @return the list
     */
    public List<EntrepriseDto> findEntreprise() {

        List<Entreprise> entreprises = new ArrayList<Entreprise>();

        try {

            entreprises = EntrepriseDao.INSTANCE_ENTREPRISE_DAO
                    .findEntreprise();

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        } catch (RequeteQueryException e) {

            e.printStackTrace();

        }

        return EntrepriseDtoMapper.INSTANCE_ENTREPRISE_DTO_MAPPER
                .recuperationListEntreprise(entreprises);

    }

    /**
     * Find entreprise by id.
     *
     * @param id
     *            the id
     * @return the optional
     */
    public Optional<Entreprise> findEntrepriseById(long id) {

        try {

            return EntrepriseDao.INSTANCE_ENTREPRISE_DAO.findEntrepriseByID(id);

        } catch (ConnexionDatabaseException e) {

            LOGGER.error("Entreprise : " + id + " introuvable");

        } catch (RequeteQueryException e) {

            LOGGER.error("Entreprise : " + id + " introuvable");

        }

        return Optional.empty();

    }

    /**
     * Find entreprise by Name.
     *
     * @param factory
     *            the name factory
     * @param entreprises
     *            the list entreprise to compare
     * @return the id
     */
    public long findIdEntrepriseByName(String factory,
            List<EntrepriseDto> entreprises) {

        long id = 0;

        for (EntrepriseDto entreprise : entreprises) {

            if (entreprise.getName().equals(factory)) {

                id = entreprise.getId();
                break;

            }

        }

        return id;

    }

}
