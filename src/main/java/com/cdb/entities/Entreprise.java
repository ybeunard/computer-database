/*
 * 
 */
package com.cdb.entities;

/**
 * The Class Entreprise.
 */
public class Entreprise {

    /** The id. */
    private final long id;

    /** The name. */
    private final String name;

    /**
     * Instantiates a new entreprise.
     *
     * @param builder
     *            the builder
     */
    private Entreprise(EntrepriseBuilder builder) {

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
     * The Class EntrepriseBuilder.
     */
    public static class EntrepriseBuilder {

        /** The id. */
        private long id;

        /** The name. */
        private final String name;

        /**
         * Instantiates a new entreprise builder.
         *
         * @param name
         *            the name
         */
        public EntrepriseBuilder(String name) {

            this.name = name;

        }

        /**
         * Id.
         *
         * @param id
         *            the id
         * @return the entreprise builder
         */
        public EntrepriseBuilder id(long id) {

            this.id = id;
            return this;

        }

        /**
         * Builds the.
         *
         * @return the entreprise
         */
        public Entreprise build() {

            return new Entreprise(this);

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

        Entreprise other = (Entreprise) obj;

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

        String chaine = this.id + "\t" + this.name;
        return chaine;

    }

}
