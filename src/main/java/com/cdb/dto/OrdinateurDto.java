package com.cdb.dto;

/**
 * The Class OrdinateurDto.
 */
public class OrdinateurDto {

    /** The name. */
    private final String name;

    /** The date introduit. */
    private final String dateIntroduit;

    /** The date interrompu. */
    private final String dateInterrompu;

    /** The factory. */
    private final String factory;

    /** The id factory. */
    private final long idFactory;

    /** The id. */
    private final long id;

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

}
