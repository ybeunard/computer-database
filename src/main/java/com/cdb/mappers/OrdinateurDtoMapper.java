package com.cdb.mappers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cdb.controllers.validation.Parse;
import com.cdb.dto.OrdinateurDto;
import com.cdb.dto.OrdinateurDto.OrdinateurDtoBuilder;
import com.cdb.entities.Ordinateur;

/**
 * The Class OrdinateurDtoMapper.
 */
public class OrdinateurDtoMapper {

    /**
     * Instantiates a new ordinateur dto mapper.
     */
    private OrdinateurDtoMapper() {

    }

    /**
     * Recuperation list ordinateur dto.
     *
     * @param ordinateurs
     *            the ordinateurs
     * @return the list
     */
    public static List<OrdinateurDto> recuperationListOrdinateurDto(
            List<Ordinateur> ordinateurs) {

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

        OrdinateurDtoBuilder builder = new OrdinateurDto.OrdinateurDtoBuilder(
                ordinateur.getName());
        builder.id(ordinateur.getId());

        if (ordinateur.getDateIntroduit() != null) {

            builder.dateIntroduit(ordinateur.getDateIntroduit().toString());

        }

        if (ordinateur.getDateInterrompu() != null) {

            builder.dateInterrompu(ordinateur.getDateInterrompu().toString());

        }

        if (ordinateur.getFabricant().isPresent()) {

            builder.idFactory(ordinateur.getFabricant().get().getId());
            builder.factory(ordinateur.getFabricant().get().getName());

        }

        return builder.build();

    }

    /**
     * Recuperation ordinateur dto.
     *
     * @param request
     *            the request
     * @return the ordinateur dto
     */
    public static OrdinateurDto recuperationOrdinateurDto(
            HttpServletRequest request) {

        OrdinateurDtoBuilder builder = new OrdinateurDtoBuilder(
                request.getParameter("computerName"));
        builder.id(Parse.parseLong(request.getParameter("ordinateur"), 0));
        builder.dateIntroduit(request.getParameter("introduced"));
        builder.dateInterrompu(request.getParameter("discontinued"));
        builder.idFactory(Parse.parseLong(request.getParameter("company"), 0));
        builder.factory("");
        return builder.build();

    }

}
