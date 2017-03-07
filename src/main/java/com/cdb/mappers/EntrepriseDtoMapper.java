package com.cdb.mappers;

import java.util.ArrayList;
import java.util.List;

import com.cdb.dto.EntrepriseDto;
import com.cdb.dto.EntrepriseDto.EntrepriseDtoBuilder;
import com.cdb.entities.Entreprise;

/**
 * The Class EntrepriseDtoMapper.
 */
public class EntrepriseDtoMapper {

    /**
     * Instantiates a new entreprise dto mapper.
     */
    private EntrepriseDtoMapper() {

    }

    /**
     * Recuperation list entreprise.
     *
     * @param entreprises
     *            the entreprises
     * @return the list
     */
    public static List<EntrepriseDto> recuperationListEntreprise(
            List<Entreprise> entreprises) {

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

        EntrepriseDtoBuilder builder = new EntrepriseDto.EntrepriseDtoBuilder(
                entreprise.getId(), entreprise.getName());
        return builder.build();

    }

}
