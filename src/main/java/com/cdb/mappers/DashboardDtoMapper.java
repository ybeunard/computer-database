package com.cdb.mappers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.controllers.validation.Parse;
import com.cdb.dto.DashboardDto;
import com.cdb.dto.DashboardDto.DashboardDtoBuilder;
import com.cdb.dto.PageDto;

/**
 * The Class DashboardDtoMapper.
 */
public class DashboardDtoMapper {

    /**
     * Instantiates a new dashboard dto mapper.
     */
    private DashboardDtoMapper() {

    }

    /** The Constant logger. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(DashboardDtoMapper.class);

    /**
     * Recuperation dashboard request get.
     *
     * @param request
     *            the request
     * @return the dashboard dto
     */
    public static DashboardDto recuperationDashboardRequestGet(
            HttpServletRequest request) {

        DashboardDtoBuilder builder = new DashboardDto.DashboardDtoBuilder();
        PageDto pageCourante = (PageDto) request.getSession()
                .getAttribute("page");
        int numPage = 1;
        int nbParPage = 10;
        String filtre = "";

        if (pageCourante != null) {

            numPage = pageCourante.getNumPage();
            nbParPage = pageCourante.getNbParPage();
            filtre = pageCourante.getFiltre();

        }

        builder.numPage(
                Parse.parseEntier(request.getParameter("numPage"), numPage));
        builder.nbParPage(Parse.parseEntier(request.getParameter("nbParPage"),
                nbParPage));
        builder.filtre(
                Parse.parseString(request.getParameter("search"), filtre));
        String resetFiltre = request.getParameter("resetFiltre");

        if (resetFiltre != null && resetFiltre.equals("OK")) {

            builder.filtre("");
            builder.numPage(1);

        }

        return builder.build();

    }

}
