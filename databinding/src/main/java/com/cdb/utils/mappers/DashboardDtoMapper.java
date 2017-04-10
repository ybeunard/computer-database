package com.cdb.utils.mappers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.DashboardDto;
import com.cdb.model.dto.DashboardDto.DashboardDtoBuilder;
import com.cdb.model.dto.PageDto;
import com.cdb.utils.Parse;

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
    public static DashboardDto recoveryDashboardRequestGet(
            HttpServletRequest request) {

        LOGGER.info("Mapping RequestServlet in DashboardDto");
        DashboardDtoBuilder builder = new DashboardDto.DashboardDtoBuilder();
        PageDto currentPage = (PageDto) request.getSession()
                .getAttribute("page");
        int numPage = 1;
        int rowByPage = 10;
        String filter = "";
        String sort = "";
        boolean desc = false;

        if (currentPage != null) {

            numPage = currentPage.getNumPage();
            rowByPage = currentPage.getRowByPage();
            filter = currentPage.getFilter();
            desc = currentPage.getDesc();
            sort = currentPage.getSort();

        }

        builder.numPage(
                Parse.parseEntier(request.getParameter("numPage"), numPage));
        builder.rowByPage(Parse.parseEntier(request.getParameter("rowByPage"),
                rowByPage));
        builder.filter(
                Parse.parseString(request.getParameter("search"), filter));
        String resetFilter = request.getParameter("resetFilter");

        if (resetFilter != null && resetFilter.equals("OK")) {

            builder.filter("");
            builder.numPage(1);

        }

        String newSort = Parse.parseString(request.getParameter("sort"), "");

        if (newSort != null && !newSort.equals("")) {

            if (newSort.equals(sort)) {

                desc = Boolean.logicalXor(desc, true);

            }

            sort = newSort;

        }

        builder.sort(sort);
        builder.desc(desc);
        return builder.build();

    }

    /**
     * Recuperation list suppresion request post.
     *
     * @param request
     *            the request
     * @return the list
     */
    public static List<Long> recoveryListDeleteRequestPost(
            HttpServletRequest request) {

        String selection = request.getParameter("selection");
        String[] deleted = selection.split(",");
        List<Long> idDelete = new ArrayList<Long>();

        for (String deleteStr : deleted) {

            long delete = 0;

            if (!deleteStr.equals("")) {

                delete = Long.parseLong(deleteStr);
                idDelete.add(delete);

            }

        }

        return idDelete;

    }

}
