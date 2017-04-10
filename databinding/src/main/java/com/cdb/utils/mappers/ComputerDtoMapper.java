package com.cdb.utils.mappers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.ComputerDto;
import com.cdb.model.dto.ComputerDto.ComputerDtoBuilder;
import com.cdb.model.entities.Computer;

public class ComputerDtoMapper {

    /**
     * Instantiates a new ordinateur dto mapper.
     */
    private ComputerDtoMapper() {

    }

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(ComputerDtoMapper.class);

    /**
     * Recuperation list ordinateur dto.
     *
     * @param ordinateurs
     *            the ordinateurs
     * @return the list
     */
    public static List<ComputerDto> recoveryListComputerDto(
            List<Computer> computers) {

        LOGGER.info("Mapping List Ordinateur in List OrdinateurDto");
        List<ComputerDto> computersDto = new ArrayList<ComputerDto>();

        for (Computer computer : computers) {

            computersDto.add(recoveryComputerDto(computer));

        }

        return computersDto;

    }

    /**
     * Recuperation ordinateur dto.
     *
     * @param ordinateur
     *            the ordinateur
     * @return the ordinateur dto
     */
    public static ComputerDto recoveryComputerDto(
            Computer computer) {

        ComputerDtoBuilder builder = new ComputerDto.ComputerDtoBuilder(
                computer.getName());
        builder.id(computer.getId());

        if (computer.getIntroduced() != null) {

            builder.introduced(computer.getIntroduced().toString());

        }

        if (computer.getDiscontinued() != null) {

            builder.discontinued(computer.getDiscontinued().toString());

        }

        if (computer.getCompany() != null) {

            builder.idCompany(computer.getCompany().getId());
            builder.company(computer.getCompany().getName());

        }

        return builder.build();

    }
    
}
