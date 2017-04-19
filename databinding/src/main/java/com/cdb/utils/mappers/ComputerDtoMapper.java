package com.cdb.utils.mappers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.ComputerDto;
import com.cdb.model.dto.ComputerDto.ComputerDtoBuilder;
import com.cdb.model.entities.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDtoMapper.
 */
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
     * @param computers
     *            the computers
     * @return the list
     */
    public static List<ComputerDto> recoveryListComputerDto(
            List<Computer> computers) {

        LOGGER.info("Mapping List Computer in List ComputerDto");
        List<ComputerDto> computersDto = new ArrayList<ComputerDto>();

        for (Computer computer : computers) {

            computersDto.add(recoveryComputerDto(computer));

        }

        return computersDto;

    }

    /**
     * Recuperation ordinateur dto.
     *
     * @param computer
     *            the computer
     * @return the ordinateur dto
     */
    public static ComputerDto recoveryComputerDto(Computer computer) {

        ComputerDtoBuilder builder;

        if (computer != null) {

            builder = new ComputerDto.ComputerDtoBuilder(computer.getName());

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

        } else {

            builder = new ComputerDto.ComputerDtoBuilder("");

        }

        return builder.build();

    }

}
