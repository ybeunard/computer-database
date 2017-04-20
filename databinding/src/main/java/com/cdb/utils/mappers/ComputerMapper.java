package com.cdb.utils.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.ComputerDto;
import com.cdb.model.entities.Computer;
import com.cdb.utils.Parse;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerMapper.
 */
public class ComputerMapper {

    /**
     * Instantiates a new ordinateur mapper.
     */
    private ComputerMapper() {

    }

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(ComputerMapper.class);

    /**
     * Recuperation ordinateur.
     *
     * @param computerDto
     *            the computer dto
     * @return the ordinateur
     */
    public static Computer recoveryComputer(ComputerDto computerDto) {

        LOGGER.info("Mapping ComputerDto in Computer");
        Computer computer = new Computer();
        computer.setId(computerDto.getId());
        computer.setName(computerDto.getName());
        computer.setIntroduced(Parse.parseDate(computerDto.getIntroduced()));
        computer.setDiscontinued(
                Parse.parseDate(computerDto.getDiscontinued()));
        computer.setCompany(Parse.parseFactory(computerDto.getIdCompany(),
                computerDto.getCompany()));
        return computer;

    }
}
