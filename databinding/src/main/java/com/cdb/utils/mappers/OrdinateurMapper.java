package com.cdb.utils.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.OrdinateurDto;
import com.cdb.model.entities.Ordinateur;
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
    public static Ordinateur recuperationOrdinateur(OrdinateurDto ordinateur) {

        Ordinateur builder = new Ordinateur();
        builder.setId(ordinateur.getId());
        builder.setName(ordinateur.getName());
        builder.setIntroduced(Parse.parseDate(ordinateur.getDateIntroduit()));
        builder.setDiscontinued(Parse.parseDate(ordinateur.getDateInterrompu()));
        builder.setFabricant(Parse.parseFactory(ordinateur.getIdFactory(),
                ordinateur.getFactory()));
        return builder;

    }

}
