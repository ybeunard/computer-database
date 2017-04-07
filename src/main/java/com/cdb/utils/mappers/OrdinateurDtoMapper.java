package com.cdb.utils.mappers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.OrdinateurDto;
import com.cdb.model.dto.OrdinateurDto.OrdinateurDtoBuilder;
import com.cdb.model.entities.Ordinateur;

/**
 * The Class OrdinateurDtoMapper.
 */
public class OrdinateurDtoMapper {

    /**
     * Instantiates a new ordinateur dto mapper.
     */
    private OrdinateurDtoMapper() {

    }

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(OrdinateurDtoMapper.class);

    /**
     * Recuperation list ordinateur dto.
     *
     * @param ordinateurs
     *            the ordinateurs
     * @return the list
     */
    public static List<OrdinateurDto> recuperationListOrdinateurDto(
            List<Ordinateur> ordinateurs) {

        LOGGER.info("Mapping List OrdinateurDto depuis List Ordinateur");
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
    public static OrdinateurDto recuperationOrdinateurDto(
            Ordinateur ordinateur) {

        LOGGER.info("Mapping OrdinateurDto depuis ordinateur");
        OrdinateurDtoBuilder builder = new OrdinateurDto.OrdinateurDtoBuilder(
                ordinateur.getName());
        builder.id(ordinateur.getId());

        if (ordinateur.getIntroduced() != null) {

            builder.dateIntroduit(ordinateur.getIntroduced().toString());

        }

        if (ordinateur.getDiscontinued() != null) {

            builder.dateInterrompu(ordinateur.getDiscontinued().toString());

        }

        if (ordinateur.getFabricant() != null) {

            builder.idFactory(ordinateur.getFabricant().getId());
            builder.factory(ordinateur.getFabricant().getName());

        }

        return builder.build();

    }

}
