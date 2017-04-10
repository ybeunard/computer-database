package com.cdb.utils.mappers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.CompanyDto;
import com.cdb.model.dto.CompanyDto.CompanyDtoBuilder;
import com.cdb.model.entities.Company;

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
    public static List<CompanyDto> recuperationListEntreprise(
            List<Company> entreprises) {

        LOGGER.info("Mapping List EntrepriseDto depuis List Entreprise");
        List<CompanyDto> entreprisesDto = new ArrayList<CompanyDto>();

        for (Company entreprise : entreprises) {

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
    private static CompanyDto recuperationEntreprise(Company entreprise) {

        LOGGER.info("Mapping EntrepriseDto depuis Entreprise");
        CompanyDtoBuilder builder = new CompanyDto.CompanyDtoBuilder(
                entreprise.getId(), entreprise.getName());
        return builder.build();

    }

}
