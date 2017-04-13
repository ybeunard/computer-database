package com.cdb.utils.mappers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.PageDto;
import com.cdb.model.dto.PageDto.PageDtoBuilder;
import com.cdb.model.entities.Computer;

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
    public static PageDto recoveryPage(List<Computer> computers,
            long nbComputer, int numPage, int rowByPage, long pageMax,
            String filter, String sort, boolean desc) {

        LOGGER.info("Mapping PageDto");
        PageDtoBuilder page = new PageDto.PageDtoBuilder();
        page.content(ComputerDtoMapper.recoveryListComputerDto(computers));
        page.numPage(numPage);
        page.rowByPage(rowByPage);
        page.nbComputer(nbComputer);
        page.paging(count(numPage, pageMax));

        if (numPage <= 1) {

            page.precPage(numPage);

        } else {

            page.precPage(numPage - 1);

        }

        if (numPage >= pageMax) {

            page.nextPage(numPage);

        } else {

            page.nextPage(numPage + 1);

        }

        if (filter != null && !filter.equals("")) {

            page.filter(filter);

        }

        if (sort != null && !sort.equals("")) {

            page.sort(sort).desc(desc);

        }

        return page.build();

    }

    /**
     * Count.
     *
     * @param currentPage
     *            the current page
     * @param pageMax
     *            the page max
     * @return the list
     */
    private static List<Integer> count(int currentPage, long pageMax) {

        List<Integer> entiers = new ArrayList<Integer>();

        if (pageMax > 7 && currentPage > pageMax - 4) {

            for (int i = (int) (pageMax - 6); i < pageMax + 1; i++) {

                entiers.add(i);

            }

        } else if (pageMax > 7 && currentPage > 4) {

            for (int i = currentPage - 3; i < currentPage + 4; i++) {

                entiers.add(i);

            }

        } else if (pageMax > 7) {

            for (int i = 1; i < 8; i++) {

                entiers.add(i);

            }

        } else {

            for (int i = 1; i < pageMax + 1; i++) {

                entiers.add(i);

            }

        }

        return entiers;

    }

}
