package com.cdb.utils.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.ComputerDto;
import com.cdb.model.entities.Computer;
import com.cdb.utils.Parse;

/**
 * The Class OrdinateurMapper.
 */
public class OrdinateurMapper {

    /**
     * Instantiates a new ordinateur mapper.
     */
    private OrdinateurMapper() {

    }

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(OrdinateurMapper.class);

    /**
     * Recuperation ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     * @return the ordinateur
     */
    public static Computer recuperationOrdinateur(ComputerDto ordinateur) {

        Computer builder = new Computer();
        builder.setId(ordinateur.getId());
        builder.setName(ordinateur.getName());
        builder.setIntroduced(Parse.parseDate(ordinateur.getIntroduced()));
        builder.setDiscontinued(Parse.parseDate(ordinateur.getDiscontinued()));
        builder.setCompany(Parse.parseFactory(ordinateur.getIdCompany(),
                ordinateur.getCompany()));
        return builder;

    }

}
