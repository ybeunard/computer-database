package com.cdb.model.dto;

/**
 * The Class OrdinateurDto.
 */
public class OrdinateurDto {

    /** The name. */
    private String name;

    /** The date introduit. */
    private String dateIntroduit;

    /** The date interrompu. */
    private String dateInterrompu;

    /** The factory. */
    private String factory;

    /** The id factory. */
    private long idFactory;

    /** The id. */
    private long id;

    /**
     * Instantiates a new ordinateur dto.
     */
    public OrdinateurDto() {

    }

    /**
     * Instantiates a new ordinateur DTO.
     *
     * @param builder
     *            the builder
     */
    private OrdinateurDto(OrdinateurDtoBuilder builder) {

        this.name = builder.name;
        this.dateIntroduit = builder.dateIntroduit;
        this.dateInterrompu = builder.dateInterrompu;
        this.factory = builder.factory;
        this.id = builder.id;
        this.idFactory = builder.idFactory;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateIntroduit(String dateIntroduit) {
        this.dateIntroduit = dateIntroduit;
    }

    public void setDateInterrompu(String dateInterrompu) {
        this.dateInterrompu = dateInterrompu;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public void setIdFactory(long idFactory) {
        this.idFactory = idFactory;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the id factory.
     *
     * @return the id factory
     */
    public long getIdFactory() {
        return idFactory;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {

        return name;

    }

    /**
     * Gets the date introduit.
     *
     * @return the date introduit
     */
    public String getDateIntroduit() {

        return dateIntroduit;

    }

    /**
     * Gets the date interrompu.
     *
     * @return the date interrompu
     */
    public String getDateInterrompu() {

        return dateInterrompu;

    }

    /**
     * Gets the factory.
     *
     * @return the factory
     */
    public String getFactory() {

        return factory;

    }

    /**
     * The Class OrdinateurDTOBuilder.
     */
    public static class OrdinateurDtoBuilder {

        /** The name. */
        private final String name;

        /** The date introduit. */
        private String dateIntroduit;

        /** The date interrompu. */
        private String dateInterrompu;

        /** The factory. */
        private String factory;

        /** The id factory. */
        private long idFactory;

        /** The id. */
        private long id;

        /**
         * Instantiates a new ordinateur DTO builder.
         *
         * @param name
         *            the name
         */
        public OrdinateurDtoBuilder(String name) {

            this.name = name;

        }

        /**
         * Date introduit.
         *
         * @param dateIntroduit
         *            the date introduit
         * @return the ordinateur DTO builder
         */
        public OrdinateurDtoBuilder dateIntroduit(String dateIntroduit) {

            this.dateIntroduit = dateIntroduit;
            return this;

        }

        /**
         * Date interrompu.
         *
         * @param dateInterrompu
         *            the date interrompu
         * @return the ordinateur DTO builder
         */
        public OrdinateurDtoBuilder dateInterrompu(String dateInterrompu) {

            this.dateInterrompu = dateInterrompu;
            return this;

        }

        /**
         * Factory.
         *
         * @param factory
         *            the factory
         * @return the ordinateur DTO builder
         */
        public OrdinateurDtoBuilder factory(String factory) {

            this.factory = factory;
            return this;

        }

        /**
         * Id.
         *
         * @param id
         *            the id
         * @return the ordinateur dto builder
         */
        public OrdinateurDtoBuilder id(long id) {

            this.id = id;
            return this;

        }

        /**
         * Id factory.
         *
         * @param idFactory
         *            the id factory
         * @return the ordinateur dto builder
         */
        public OrdinateurDtoBuilder idFactory(long idFactory) {

            this.idFactory = idFactory;
            return this;

        }

        /**
         * Builds the.
         *
         * @return the ordinateur DTO
         */
        public OrdinateurDto build() {

            return new OrdinateurDto(this);

        }

    }

    /**
     * Hash code.
     *
     * @return entier du hascode
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((dateInterrompu == null) ? 0 : dateInterrompu.hashCode());
        result = prime * result
                + ((dateIntroduit == null) ? 0 : dateIntroduit.hashCode());
        result = prime * result + ((factory == null) ? 0 : factory.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (idFactory ^ (idFactory >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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

        OrdinateurDto other = (OrdinateurDto) obj;

        if (dateInterrompu == null) {

            if (other.dateInterrompu != null) {

                return false;

            }

        } else if (!dateInterrompu.equals(other.dateInterrompu)) {

            return false;

        }

        if (dateIntroduit == null) {

            if (other.dateIntroduit != null) {

                return false;

            }

        } else if (!dateIntroduit.equals(other.dateIntroduit)) {

            return false;

        }

        if (factory == null) {

            if (other.factory != null) {

                return false;

            }

        } else if (!factory.equals(other.factory)) {

            return false;

        }

        if (id != other.id) {

            return false;

        }

        if (idFactory != other.idFactory) {

            return false;

        }

        if (name == null) {

            if (other.name != null) {

                return false;

            }

        } else if (!name.equals(other.name)) {

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

        return "OrdinateurDto [name=" + name + ", dateIntroduit="
                + dateIntroduit + ", dateInterrompu=" + dateInterrompu
                + ", factory=" + factory + ", idFactory=" + idFactory + ", id="
                + id + "]";

    }

}
