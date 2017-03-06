package com.cdb.dto;

/**
 * The Class EntrepriseDto.
 */
public class EntrepriseDto {

    /** The id. */
    private final long id;

    /** The name. */
    private final String name;

    /**
     * Instantiates a new entreprise dto.
     *
     * @param builder
     *            the builder
     */
    private EntrepriseDto(EntrepriseDtoBuilder builder) {

        this.id = builder.id;
        this.name = builder.name;

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
     * The Class EntrepriseDtoBuilder.
     */
    public static class EntrepriseDtoBuilder {

        /** The id. */
        private final long id;

        /** The name. */
        private final String name;

        /**
         * Instantiates a new entreprise dto builder.
         *
         * @param id
         *            the id
         * @param name
         *            the name
         */
        public EntrepriseDtoBuilder(long id, String name) {

            this.id = id;
            this.name = name;

        }

        /**
         * Builds the.
         *
         * @return the entreprise dto
         */
        public EntrepriseDto build() {

            return new EntrepriseDto(this);

        }

    }

}
