package com.cdb.model.dto;

/**
 * The Class DashboardDto.
 */
public class DashboardDto {

    /** The num page. */
    private final int numPage;

    /** The nb par page. */
    private final int rowByPage;

    /** The filtre. */
    private final String filter;

    /** The trie. */
    private final String sort;

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
        this.rowByPage = builder.rowByPage;
        this.filter = builder.filter;
        this.sort = builder.sort;
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
    public int getRowByPage() {
        
        return rowByPage;
        
    }

    /**
     * Gets the filtre.
     *
     * @return the filtre
     */
    public String getFilter() {
        
        return filter;
        
    }

    /**
     * Gets the trie.
     *
     * @return the trie
     */
    public String getSort() {
        
        return sort;
        
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
        private int rowByPage;

        /** The filtre. */
        private String filter;

        /** The trie. */
        private String sort;

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
        public DashboardDtoBuilder rowByPage(int rowByPage) {

            this.rowByPage = rowByPage;
            return this;

        }

        /**
         * Filtre.
         *
         * @param filtre
         *            the filtre
         * @return the dashboard dto builder
         */
        public DashboardDtoBuilder filter(String filter) {

            this.filter = filter;
            return this;

        }

        /**
         * Trie.
         *
         * @param trie
         *            the trie
         * @return the dashboard dto builder
         */
        public DashboardDtoBuilder sort(String sort) {

            this.sort = sort;
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
        result = prime * result + ((filter == null) ? 0 : filter.hashCode());
        result = prime * result + rowByPage;
        result = prime * result + numPage;
        result = prime * result + ((sort == null) ? 0 : sort.hashCode());
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

        if (filter == null) {

            if (other.filter != null) {

                return false;

            }

        } else if (!filter.equals(other.filter)) {

            return false;

        }

        if (rowByPage != other.rowByPage) {

            return false;

        }

        if (numPage != other.numPage) {

            return false;

        }

        if (sort == null) {

            if (other.sort != null) {

                return false;

            }

        } else if (!sort.equals(other.sort)) {

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

        return "DashboardDto [numPage=" + numPage + ", nbParPage=" + rowByPage
                + ", filter=" + filter + ", sort=" + sort + ", desc=" + desc
                + "]";

    }

}
