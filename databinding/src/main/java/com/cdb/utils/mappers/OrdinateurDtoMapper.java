package com.cdb.utils.mappers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.ComputerDto;
import com.cdb.model.dto.ComputerDto.ComputerDtoBuilder;
import com.cdb.model.entities.Computer;

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
    public static List<ComputerDto> recuperationListOrdinateurDto(
            List<Computer> ordinateurs) {

        LOGGER.info("Mapping List OrdinateurDto depuis List Ordinateur");
        List<ComputerDto> ordinateursDto = new ArrayList<ComputerDto>();

        for (Computer ordinateur : ordinateurs) {

            ordinateursDto.add(recuperationComputerDto(ordinateur));

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
    public static ComputerDto recuperationComputerDto(
            Computer ordinateur) {

        LOGGER.info("Mapping OrdinateurDto depuis ordinateur" + ordinateur);
        ComputerDtoBuilder builder = new ComputerDto.ComputerDtoBuilder(
                ordinateur.getName());
        builder.id(ordinateur.getId());

        if (ordinateur.getIntroduced() != null) {

            builder.introduced(ordinateur.getIntroduced().toString());

        }

        if (ordinateur.getDiscontinued() != null) {

            builder.discontinued(ordinateur.getDiscontinued().toString());

        }

        if (ordinateur.getCompany() != null) {

            builder.idCompany(ordinateur.getCompany().getId());
            builder.company(ordinateur.getCompany().getName());

        }

        return builder.build();

    }

}
