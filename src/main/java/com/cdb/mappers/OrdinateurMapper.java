package com.cdb.mappers;

import com.cdb.controllers.validation.Parse;
import com.cdb.dto.OrdinateurDto;
import com.cdb.entities.Ordinateur;
import com.cdb.entities.Ordinateur.OrdinateurBuilder;

public class OrdinateurMapper {
    
    private OrdinateurMapper() {
        
    }
    
    public static Ordinateur recuperationOrdinateur(OrdinateurDto ordinateur) {
        
        OrdinateurBuilder builder = new OrdinateurBuilder(ordinateur.getName());
        builder.id(ordinateur.getId());
        builder.dateIntroduit(Parse.parseDate(ordinateur.getDateIntroduit()));
        builder.dateInterrompu(Parse.parseDate(ordinateur.getDateInterrompu()));
        builder.fabricant(Parse.parseFactory(ordinateur.getId(),ordinateur.getFactory()));
        return builder.build();
        
    }

}
