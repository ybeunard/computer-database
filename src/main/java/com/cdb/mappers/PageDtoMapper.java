package com.cdb.mappers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dto.PageDto;
import com.cdb.dto.PageDto.PageDtoBuilder;
import com.cdb.entities.Ordinateur;

/**
 * The Enum PageDtoMapper.
 */
public enum PageDtoMapper {

    /** The instance page dto mapper. */
    INSTANCE_PAGE_DTO_MAPPER;

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(PageDtoMapper.class);

    /**
     * Recuperation page.
     *
     * @param ordinateurs
     *            the ordinateurs
     * @param nombreTotal
     *            the nombre total
     * @param numeroPage
     *            the numero page
     * @param ligneParPage
     *            the ligne par page
     * @param pageMax
     *            the page max
     * @param filtre
     *            the filtre
     * @return the page dto
     */
    public PageDto recuperationPage(List<Ordinateur> ordinateurs,
            int nombreTotal, int numeroPage, int ligneParPage, int pageMax,
            String filtre) {

        PageDtoBuilder page = new PageDto.PageDtoBuilder();
        page.contenue(OrdinateurDtoMapper.INSTANCE_ORDINATEUR_DTO_MAPPER
                .recuperationListOrdinateurDto(ordinateurs));
        page.numPage(numeroPage);
        page.nbParPage(ligneParPage);
        page.nbComputer(nombreTotal);
        page.pagination(count(numeroPage, pageMax));

        if (numeroPage <= 1) {

            page.pagePrec(numeroPage);

        } else {

            page.pagePrec(numeroPage - 1);

        }

        if (numeroPage >= pageMax) {

            page.pageSuiv(numeroPage);

        } else {

            page.pageSuiv(numeroPage + 1);

        }

        if (filtre != null && !filtre.equals("")) {

            page.filtre(filtre);

        }

        return page.build();

    }

    /**
     * Count.
     *
     * @param pageActuelle
     *            the page actuelle
     * @param nombreTotal
     *            the nombre total
     * @return the list
     */
    private List<Integer> count(int pageActuelle, int nombreTotal) {

        List<Integer> entiers = new ArrayList<Integer>();

        if (nombreTotal > 7 && pageActuelle > nombreTotal - 4) {

            for (int i = nombreTotal - 6; i < nombreTotal + 1; i++) {

                entiers.add(i);

            }

        } else if (nombreTotal > 7 && pageActuelle > 4) {

            for (int i = pageActuelle - 3; i < pageActuelle + 4; i++) {

                entiers.add(i);

            }

        } else if (nombreTotal > 7) {

            for (int i = 1; i < 8; i++) {

                entiers.add(i);

            }

        } else {

            for (int i = 1; i < nombreTotal + 1; i++) {

                entiers.add(i);

            }

        }

        return entiers;

    }

}
