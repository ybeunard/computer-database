package com.cdb.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.model.dto.CompanyDto;
import com.cdb.model.entities.Company;
import com.cdb.services.InterfaceGestionEntreprise;
import com.cdb.utils.mappers.CompanyDtoMapper;

/**
 * The Enum GestionEntreprise.
 */
@Component
public class GestionEntreprise implements InterfaceGestionEntreprise {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(GestionEntreprise.class);

    /** The entreprise dao. */
    @Autowired
    private EntrepriseDao entrepriseDao;

    /**
     * Gets the entreprise dao.
     *
     * @return the entreprise dao
     */
    public EntrepriseDao getEntrepriseDao() {

        return entrepriseDao;

    }

    /**
     * Instantiates a new gestion entreprise.
     */
    GestionEntreprise() {

        LOGGER.info("GestionEntreprise instancié");

    }

    /**
     * Find entreprise.
     *
     * @return the list
     * @throws DataAccessException
     *             the data access exception
     */
    public List<CompanyDto> findEntreprise() throws DataAccessException {

        LOGGER.info("Service: Recherche de toutes les entreprises");
        List<Company> entreprises = new ArrayList<Company>();
        entreprises = entrepriseDao.findEntreprise();
        return CompanyDtoMapper.recoveryListCompany(entreprises);

    }

    /**
     * Find entreprise by id.
     *
     * @param id
     *            the id
     * @return the optional
     * @throws DataAccessException
     *             the data access exception
     */
    public Optional<Company> findEntrepriseById(long id)
            throws DataAccessException {

        LOGGER.info("Service: Recherche d'une entreprise par id");
        return entrepriseDao.findEntrepriseByID(id);

    }

}
