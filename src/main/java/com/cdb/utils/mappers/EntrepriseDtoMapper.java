package com.cdb.utils.mappers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.EntrepriseDto;
import com.cdb.model.dto.EntrepriseDto.EntrepriseDtoBuilder;
import com.cdb.model.entities.Entreprise;

/**
 * The Class EntrepriseDtoMapper.
 */
public class EntrepriseDtoMapper {

    /**
     * Instantiates a new entreprise dto mapper.
     */
    private EntrepriseDtoMapper() {

    }

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(EntrepriseDtoMapper.class);

    /**
     * Recuperation list entreprise.
     *
     * @param entreprises
     *            the entreprises
     * @return the list
     */
    public static List<EntrepriseDto> recuperationListEntreprise(
            List<Entreprise> entreprises) {

        LOGGER.info("Mapping List EntrepriseDto depuis List Entreprise");
        List<EntrepriseDto> entreprisesDto = new ArrayList<EntrepriseDto>();

        for (Entreprise entreprise : entreprises) {

            entreprisesDto.add(recuperationEntreprise(entreprise));

        }

        return entreprisesDto;
    }

    /**
     * Recuperation entreprise.
     *
     * @param entreprise
     *            the entreprise
     * @return the entreprise dto
     */
    private static EntrepriseDto recuperationEntreprise(Entreprise entreprise) {

        LOGGER.info("Mapping EntrepriseDto depuis Entreprise");
        EntrepriseDtoBuilder builder = new EntrepriseDto.EntrepriseDtoBuilder(
                entreprise.getId(), entreprise.getName());
        return builder.build();

    }

}
