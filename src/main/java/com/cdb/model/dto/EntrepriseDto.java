package com.cdb.model.dto;

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

    /**
     * Hash code.
     *
     * @return entier du hascode
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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

        EntrepriseDto other = (EntrepriseDto) obj;

        if (id != other.id) {

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

        return "EntrepriseDto [id=" + id + ", name=" + name + "]";

    }

}
