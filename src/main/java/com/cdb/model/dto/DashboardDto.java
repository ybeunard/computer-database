package com.cdb.model.dto;

/**
 * The Class DashboardDto.
 */
public class DashboardDto {

    /** The num page. */
    private final int numPage;

    /** The nb par page. */
    private final int nbParPage;

    /** The filtre. */
    private final String filtre;

    private final String trie;

    private final Boolean desc;

    /**
     * Instantiates a new dashboard dto.
     *
     * @param builder
     *            the builder
     */
    private DashboardDto(DashboardDtoBuilder builder) {

        this.numPage = builder.numPage;
        this.nbParPage = builder.nbParPage;
        this.filtre = builder.filtre;
        this.trie = builder.trie;
        this.desc = builder.desc;

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
     * Gets the filtre.
     *
     * @return the filtre
     */
    public String getFiltre() {
        return filtre;
    }

    public String getTrie() {
        return trie;
    }

    public Boolean getDesc() {
        return desc;
    }

    /**
     * The Class DashboardDtoBuilder.
     */
    public static class DashboardDtoBuilder {

        /** The num page. */
        private int numPage;

        /** The nb par page. */
        private int nbParPage;

        /** The filtre. */
        private String filtre;

        private String trie;

        private Boolean desc;

        /**
         * Num page.
         *
         * @param numPage
         *            the num page
         * @return the dashboard dto builder
         */
        public DashboardDtoBuilder numPage(int numPage) {

            this.numPage = numPage;
            return this;

        }

        /**
         * Nb par page.
         *
         * @param nbParPage
         *            the nb par page
         * @return the dashboard dto builder
         */
        public DashboardDtoBuilder nbParPage(int nbParPage) {

            this.nbParPage = nbParPage;
            return this;

        }

        /**
         * Filtre.
         *
         * @param filtre
         *            the filtre
         * @return the dashboard dto builder
         */
        public DashboardDtoBuilder filtre(String filtre) {

            this.filtre = filtre;
            return this;

        }

        /**
         * Trie.
         *
         * @param trie
         *            the trie
         * @return the dashboard dto builder
         */
        public DashboardDtoBuilder trie(String trie) {

            this.trie = trie;
            return this;

        }

        /**
         * Desc.
         *
         * @param desc
         *            the desc
         * @return the dashboard dto builder
         */
        public DashboardDtoBuilder desc(Boolean desc) {

            this.desc = desc;
            return this;

        }

        /**
         * Builds the.
         *
         * @return the dashboard dto
         */
        public DashboardDto build() {

            return new DashboardDto(this);

        }

    }

}
