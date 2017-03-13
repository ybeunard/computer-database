package com.cdb.model.mappers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.views.controllers.validation.Parse;
import com.cdb.model.dto.DashboardDto;
import com.cdb.model.dto.DashboardDto.DashboardDtoBuilder;
import com.cdb.model.dto.PageDto;

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

        LOGGER.info("Mapping DashboardDto depuis RequestServlet");
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
    
    public static List<Long> recuperationListSuppresionRequestPost(HttpServletRequest request) {
        
        String selection = request.getParameter("selection");
        String[] aSupprimer = selection.split(",");
        List<Long> identifiants = new ArrayList<Long>();

        for (String supprimeStr : aSupprimer) {

            long supprime = 0;

            if (!supprimeStr.equals("")) {

                supprime = Long.parseLong(supprimeStr);
                identifiants.add(supprime);

            }
            
        }
        
        return identifiants;
        
    }

}
