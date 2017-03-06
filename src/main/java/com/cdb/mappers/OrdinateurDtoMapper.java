package com.cdb.mappers;

import java.util.ArrayList;
import java.util.List;

import com.cdb.dto.OrdinateurDto;
import com.cdb.dto.OrdinateurDto.OrdinateurDtoBuilder;
import com.cdb.entities.Ordinateur;

/**
 * The Enum OrdinateurDtoMapper.
 */
public enum OrdinateurDtoMapper {

    /** The instance ordinateur dto mapper. */
    INSTANCE_ORDINATEUR_DTO_MAPPER;

    /**
     * Recuperation list ordinateur dto.
     *
     * @param ordinateurs
     *            the ordinateurs
     * @return the list
     */
    public List<OrdinateurDto> recuperationListOrdinateurDto(
            List<Ordinateur> ordinateurs) {

        List<OrdinateurDto> ordinateursDto = new ArrayList<OrdinateurDto>();

        for (Ordinateur ordinateur : ordinateurs) {

            ordinateursDto.add(recuperationOrdinateurDto(ordinateur));

        }

        return ordinateursDto;

    }

    /**
     * Recuperation ordinateur dto.
     *
     * @param ordinateur
     *            the ordinateur
     * @return the ordinateur dto
     */
    public OrdinateurDto recuperationOrdinateurDto(Ordinateur ordinateur) {

        OrdinateurDtoBuilder builder = new OrdinateurDto.OrdinateurDtoBuilder(
                ordinateur.getName());
        builder.id(ordinateur.getId());
        builder.dateIntroduit(ordinateur.getDateIntroduit());
        builder.dateInterrompu(ordinateur.getDateInterrompu());

        if (ordinateur.getFabricant().isPresent()) {

            builder.factory(ordinateur.getFabricant().get().getName());

        }

        return builder.build();

    }

}
