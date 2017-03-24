package com.cdb.utils.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.OrdinateurDto;
import com.cdb.model.entities.Ordinateur;
import com.cdb.model.entities.Ordinateur.OrdinateurBuilder;
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

        LOGGER.info("Mapping Ordinateur depuis OrdinateurDto");
        OrdinateurBuilder builder = new OrdinateurBuilder(ordinateur.getName());
        builder.id(ordinateur.getId());
        builder.dateIntroduit(Parse.parseDate(ordinateur.getDateIntroduit()));
        builder.dateInterrompu(Parse.parseDate(ordinateur.getDateInterrompu()));
        builder.fabricant(Parse.parseFactory(ordinateur.getIdFactory(),
                ordinateur.getFactory()));
        return builder.build();

    }

}
