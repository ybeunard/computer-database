package com.cdb.services.Impl;

import java.util.ArrayList;
import java.util.List;
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

}
