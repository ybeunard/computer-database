package com.cdb.utils.mappers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;

import com.cdb.model.dto.PageDto;
import com.cdb.model.dto.PageDto.PageDtoBuilder;
import com.cdb.model.entities.Computer;
import com.cdb.utils.Parse;

/**
 * The Class PageDtoMapper.
 */
public class PageDtoMapper {

    /**
     * Instantiates a new page dto mapper.
     */
    private PageDtoMapper() {

    }

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(PageDtoMapper.class);

    /**
     * Recuperation page.
     *
     * @param computers
     *            the computers
     * @param nbComputer
     *            the nb computer
     * @param numPage
     *            the num page
     * @param rowByPage
     *            the row by page
     * @param pageMax
     *            the page max
     * @param filter
     *            the filter
     * @param sort
     *            the sort
     * @param desc
     *            the desc
     * @return the page dto
     */
    public static PageDto recoveryPage(List<Computer> computers, PageDto page,
            long nbComputer) {

        LOGGER.info("Mapping PageDto");
        PageDtoBuilder builder = new PageDto.PageDtoBuilder();
        builder.content(ComputerDtoMapper.recoveryListComputerDto(computers));
        builder.numPage(page.getNumPage());
        builder.rowByPage(page.getRowByPage());
        builder.nbComputer(nbComputer);
        builder.filter(page.getFilter());
        builder.sort(page.getSort());
        builder.desc(page.getDesc());
        return builder.build();

    }

    /**
     * Recuperation dashboard request get.
     *
     * @param request
     *            the request
     * @return the dashboard dto
     */
    public static PageDto recoveryPageDtoRequestGet(
            MultiValueMap<String, String> parameters, PageDto currentPage) {

        LOGGER.info("Mapping RequestServlet in PageDto");

        LOGGER.info(currentPage.toString());
        PageDtoBuilder builder = new PageDtoBuilder();
        int numPage = currentPage.getNumPage();
        int rowByPage = currentPage.getRowByPage();
        String filter = currentPage.getFilter();
        String sort = currentPage.getSort();
        boolean desc = currentPage.getDesc();

        if (parameters.get("numPage") == null) {

            builder.numPage(numPage);

        } else {

            builder.numPage(
                    Parse.parseEntier(parameters.getFirst("numPage"), numPage));

        }

        if (parameters.get("rowByPage") == null) {

            builder.rowByPage(rowByPage);

        } else {

            int newRowByPage = Parse
                    .parseEntier(parameters.getFirst("rowByPage"), rowByPage);
            
            if (newRowByPage != rowByPage) {
                
                if (newRowByPage == 10 || newRowByPage == 50 || newRowByPage == 100) {
                    
                    rowByPage = newRowByPage;
                    builder.numPage(1);
                    
                }
                
            }
                
            builder.rowByPage(rowByPage);
            
        }

        if (parameters.get("search") == null) {

            builder.filter(filter);

        } else {

            builder.filter(parameters.getFirst("search"));
            builder.numPage(numPage);

        }

        String newSort = Parse.parseString(parameters.getFirst("sort"), "");
        Boolean newDesc = Parse.parseBoolean(parameters.getFirst("desc"), desc);

        if (newSort != null && !newSort.equals("")) {

            if (newSort.equals(sort) && newDesc.equals(desc)) {

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
            MultiValueMap<String, String> parameters) {

        String selection = parameters.getFirst("selection");
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
