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

    /** The trie. */
    private final String trie;

    /** The desc. */
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

        /** The trie. */
        private String trie;

        /** The desc. */
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

    /**
     * Hash code.
     *
     * @return entier du ascode
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((desc == null) ? 0 : desc.hashCode());
        result = prime * result + ((filtre == null) ? 0 : filtre.hashCode());
        result = prime * result + nbParPage;
        result = prime * result + numPage;
        result = prime * result + ((trie == null) ? 0 : trie.hashCode());
        return result;

    }

    /**
     * Equals.
     *
     * @param obj
     *            de comparaison
     * @return vrai ou faux
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {

            return true;

        }

        if (obj == null) {

            return false;

        }

        if (getClass() != obj.getClass()) {

            return false;

        }

        DashboardDto other = (DashboardDto) obj;

        if (desc == null) {

            if (other.desc != null) {

                return false;

            }

        } else if (!desc.equals(other.desc)) {

            return false;

        }

        if (filtre == null) {

            if (other.filtre != null) {

                return false;

            }

        } else if (!filtre.equals(other.filtre)) {

            return false;

        }

        if (nbParPage != other.nbParPage) {

            return false;

        }

        if (numPage != other.numPage) {

            return false;

        }

        if (trie == null) {

            if (other.trie != null) {

                return false;

            }

        } else if (!trie.equals(other.trie)) {

            return false;

        }

        return true;
    }

    /**
     * To string.
     *
     * @return la chaine de caractere a afficher
     */
    @Override
    public String toString() {

        return "DashboardDto [numPage=" + numPage + ", nbParPage=" + nbParPage
                + ", filtre=" + filtre + ", trie=" + trie + ", desc=" + desc
                + "]";

    }

}
