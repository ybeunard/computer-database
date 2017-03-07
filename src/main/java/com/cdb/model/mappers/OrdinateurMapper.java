package com.cdb.model.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.views.controllers.validation.Parse;
import com.cdb.model.dto.OrdinateurDto;
import com.cdb.model.entities.Ordinateur;
import com.cdb.model.entities.Ordinateur.OrdinateurBuilder;

public class OrdinateurMapper {
    
    private OrdinateurMapper() {
        
    }
    
    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(OrdinateurMapper.class);

    
    public static Ordinateur recuperationOrdinateur(OrdinateurDto ordinateur) {
        
        LOGGER.info("Mapping Ordinateur depuis OrdinateurDto");
        OrdinateurBuilder builder = new OrdinateurBuilder(ordinateur.getName());
        builder.id(ordinateur.getId());
        builder.dateIntroduit(Parse.parseDate(ordinateur.getDateIntroduit()));
        builder.dateInterrompu(Parse.parseDate(ordinateur.getDateInterrompu()));
        builder.fabricant(Parse.parseFactory(ordinateur.getIdFactory(),ordinateur.getFactory()));
        LOGGER.info("Mapping terminé");
        return builder.build();
        
    }

}
