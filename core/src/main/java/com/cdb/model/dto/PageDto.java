package com.cdb.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class PageDto.
 */
public class PageDto implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** The contenue. */
    private final List<ComputerDto> content;

    /** The num page. */
    private final int numPage;

    /** The nb par page. */
    private final int rowByPage;

    /** The nb computer. */
    private final long nbComputer;

    /** The filtre. */
    private final String filter;

    /** The trie. */
    private final String sort;

    /** The desc. */
    private final boolean desc;
    
    public PageDto() {
        
        this.content = new ArrayList<ComputerDto>();
        this.numPage = 1;
        this.rowByPage = 10;
        this.nbComputer = 0;
        this.filter = "";
        this.sort = "";
        this.desc = false;
        
    }
    
    /**
     * Instantiates a new page dto.
     *
     * @param builder
     *            the builder
     */
    private PageDto(PageDtoBuilder builder) {

        this.content = builder.content;

        this.numPage = builder.numPage;

        this.rowByPage = builder.rowByPage;

        this.nbComputer = builder.nbComputer;

        this.filter = builder.filter;

        this.sort = builder.sort;

        this.desc = builder.desc;

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
    public boolean getDesc() {

        return desc;

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
     * Gets the nb computer.
     *
     * @return the nb computer
     */
    public long getNbComputer() {

        return nbComputer;

    }

    /**
     * Gets the contenue.
     *
     * @return the contenue
     */
    public List<ComputerDto> getContent() {

        return content;

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
     * The Class PageDtoBuilder.
     */
    public static class PageDtoBuilder {

        /** The contenue. */
        private List<ComputerDto> content;

        /** The num page. */
        private int numPage;

        /** The nb par page. */
        private int rowByPage;

        /** The nb computer. */
        private long nbComputer;

        /** The filtre. */
        private String filter;

        /** The trie. */
        private String sort;

        /** The desc. */
        private boolean desc;

        /**
         * Contenue.
         *
         * @param content
         *            the content
         * @return the page dto builder
         */
        public PageDtoBuilder content(List<ComputerDto> content) {

            this.content = content;
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
         * @param rowByPage
         *            the row by page
         * @return the page dto builder
         */
        public PageDtoBuilder rowByPage(int rowByPage) {

            this.rowByPage = rowByPage;
            return this;

        }

        /**
         * Nb computer.
         *
         * @param nbComputer
         *            the nb computer
         * @return the page dto builder
         */
        public PageDtoBuilder nbComputer(long nbComputer) {

            this.nbComputer = nbComputer;
            return this;

        }

        /**
         * Filtre.
         *
         * @param filter
         *            the filter
         * @return the page dto builder
         */
        public PageDtoBuilder filter(String filter) {

            this.filter = filter;
            return this;

        }

        /**
         * Trie.
         *
         * @param sort
         *            the sort
         * @return the page dto builder
         */
        public PageDtoBuilder sort(String sort) {

            this.sort = sort;
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

    /**
     * Hash code.
     *
     * @return entier du ascode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + (desc ? 1231 : 1237);
        result = prime * result + ((filter == null) ? 0 : filter.hashCode());
        result = prime * result + (int) (nbComputer ^ (nbComputer >>> 32));
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

        PageDto other = (PageDto) obj;

        if (content == null) {

            if (other.content != null) {

                return false;

            }

        } else if (!content.equals(other.content)) {

            return false;

        }

        if (desc != other.desc) {

            return false;

        }

        if (filter == null) {

            if (other.filter != null) {

                return false;

            }

        } else if (!filter.equals(other.filter)) {

            return false;

        }

        if (nbComputer != other.nbComputer) {

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

        return "PageDto [content=" + content + ", numPage="
                + numPage + ", rowByPage=" + rowByPage + ", nbComputer="
                + nbComputer + ", filter=" + filter + ", sort=" + sort
                + ", desc=" + desc + "]";

    }

}
