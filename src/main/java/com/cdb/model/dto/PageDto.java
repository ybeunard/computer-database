package com.cdb.model.dto;

import java.util.List;

/**
 * The Class PageDto.
 */
public class PageDto {

    /** The contenue. */
    private final List<OrdinateurDto> contenue;

    /** The page suiv. */
    private final int pageSuiv;

    /** The page prec. */
    private final int pagePrec;

    /** The pagination. */
    private final List<Integer> pagination;

    /** The num page. */
    private final int numPage;

    /** The nb par page. */
    private final int nbParPage;

    /** The nb computer. */
    private final int nbComputer;

    /** The filtre. */
    private final String filtre;

    /** The trie. */
    private final String trie;

    /** The desc. */
    private final boolean desc;

    /**
     * Instantiates a new page dto.
     *
     * @param builder
     *            the builder
     */
    private PageDto(PageDtoBuilder builder) {

        this.contenue = builder.contenue;

        this.pageSuiv = builder.pageSuiv;

        this.pagePrec = builder.pagePrec;

        this.pagination = builder.pagination;

        this.numPage = builder.numPage;

        this.nbParPage = builder.nbParPage;

        this.nbComputer = builder.nbComputer;

        this.filtre = builder.filtre;

        this.trie = builder.trie;

        this.desc = builder.desc;

    }

    /**
     * Gets the trie.
     *
     * @return the trie
     */
    public String getTrie() {
        return trie;
    }

    /**
     * Gets the desc.
     *
     * @return the desc
     */
    public boolean getDesc() {
        return desc;
    }

    /**
     * Gets the filtre.
     *
     * @return the filtre
     */
    public String getFiltre() {
        return filtre;
    }

    /**
     * Gets the nb computer.
     *
     * @return the nb computer
     */
    public int getNbComputer() {
        return nbComputer;
    }

    /**
     * Gets the contenue.
     *
     * @return the contenue
     */
    public List<OrdinateurDto> getContenue() {
        return contenue;
    }

    /**
     * Gets the page suiv.
     *
     * @return the page suiv
     */
    public int getPageSuiv() {
        return pageSuiv;
    }

    /**
     * Gets the page prec.
     *
     * @return the page prec
     */
    public int getPagePrec() {
        return pagePrec;
    }

    /**
     * Gets the pagination.
     *
     * @return the pagination
     */
    public List<Integer> getPagination() {
        return pagination;
    }

    /**
     * Gets the num page.
     *
     * @return the num page
     */
    public int getNumPage() {
        return numPage;
    }

    /**
     * Gets the nb par page.
     *
     * @return the nb par page
     */
    public int getNbParPage() {
        return nbParPage;
    }

    /**
     * The Class PageDtoBuilder.
     */
    public static class PageDtoBuilder {

        /** The contenue. */
        private List<OrdinateurDto> contenue;

        /** The page suiv. */
        private int pageSuiv;

        /** The page prec. */
        private int pagePrec;

        /** The pagination. */
        private List<Integer> pagination;

        /** The num page. */
        private int numPage;

        /** The nb par page. */
        private int nbParPage;

        /** The nb computer. */
        private int nbComputer;

        /** The filtre. */
        private String filtre;

        /** The trie. */
        private String trie;

        /** The desc. */
        private boolean desc;

        /**
         * Contenue.
         *
         * @param contenue
         *            the contenue
         * @return the page dto builder
         */
        public PageDtoBuilder contenue(List<OrdinateurDto> contenue) {

            this.contenue = contenue;
            return this;

        }

        /**
         * Page suiv.
         *
         * @param pageSuiv
         *            the page suiv
         * @return the page dto builder
         */
        public PageDtoBuilder pageSuiv(int pageSuiv) {

            this.pageSuiv = pageSuiv;
            return this;

        }

        /**
         * Page prec.
         *
         * @param pagePrec
         *            the page prec
         * @return the page dto builder
         */
        public PageDtoBuilder pagePrec(int pagePrec) {

            this.pagePrec = pagePrec;
            return this;

        }

        /**
         * Pagination.
         *
         * @param pagination
         *            the pagination
         * @return the page dto builder
         */
        public PageDtoBuilder pagination(List<Integer> pagination) {

            this.pagination = pagination;
            return this;

        }

        /**
         * Num page.
         *
         * @param numPage
         *            the num page
         * @return the page dto builder
         */
        public PageDtoBuilder numPage(int numPage) {

            this.numPage = numPage;
            return this;

        }

        /**
         * Nb par page.
         *
         * @param nbParPage
         *            the nb par page
         * @return the page dto builder
         */
        public PageDtoBuilder nbParPage(int nbParPage) {

            this.nbParPage = nbParPage;
            return this;

        }

        /**
         * Nb computer.
         *
         * @param nbComputer
         *            the nb computer
         * @return the page dto builder
         */
        public PageDtoBuilder nbComputer(int nbComputer) {

            this.nbComputer = nbComputer;
            return this;

        }

        /**
         * Filtre.
         *
         * @param filtre
         *            the filtre
         * @return the page dto builder
         */
        public PageDtoBuilder filtre(String filtre) {

            this.filtre = filtre;
            return this;

        }

        /**
         * Trie.
         *
         * @param trie
         *            the trie
         * @return the page dto builder
         */
        public PageDtoBuilder trie(String trie) {

            this.trie = trie;
            return this;

        }

        /**
         * Desc.
         *
         * @param desc
         *            the desc
         * @return the page dto builder
         */
        public PageDtoBuilder desc(boolean desc) {

            this.desc = desc;
            return this;

        }

        /**
         * Builds the.
         *
         * @return the page dto
         */
        public PageDto build() {

            return new PageDto(this);

        }

    }

}
