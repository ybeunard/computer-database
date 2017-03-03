package com.cdb.dto;

import java.time.LocalDate;

public class OrdinateurDto {

    /** The name. */
    private final String name;

    /** The date introduit. */
    private final LocalDate dateIntroduit;

    /** The date interrompu. */
    private final LocalDate dateInterrompu;

    /** The factory. */
    private final String factory;

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
    public LocalDate getDateIntroduit() {

        return dateIntroduit;

    }

    /**
     * Gets the date interrompu.
     *
     * @return the date interrompu
     */
    public LocalDate getDateInterrompu() {

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
        private LocalDate dateIntroduit;

        /** The date interrompu. */
        private LocalDate dateInterrompu;

        /** The factory. */
        private String factory;

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
        public OrdinateurDtoBuilder dateIntroduit(LocalDate dateIntroduit) {

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
        public OrdinateurDtoBuilder dateInterrompu(LocalDate dateInterrompu) {

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
         * Builds the.
         *
         * @return the ordinateur DTO
         */
        public OrdinateurDto build() {

            return new OrdinateurDto(this);

        }

    }

}
