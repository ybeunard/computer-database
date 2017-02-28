package com.cdb.entities;

import java.time.LocalDate;
import java.util.Optional;

/**
 * The Class Ordinateur.
 */
public class Ordinateur {

    /** The id. */
    private final long id;

    /** The name. */
    private final String name;

    /** The date introduit. */
    private final LocalDate dateIntroduit;

    /** The date interrompu. */
    private final LocalDate dateInterrompu;

    /** The fabricant. */
    private final Optional<Entreprise> fabricant;

    /**
     * Instantiates a new ordinateur.
     *
     * @param builder
     *            the builder
     */
    private Ordinateur(OrdinateurBuilder builder) {

        this.id = builder.id;
        this.name = builder.name;
        this.dateIntroduit = builder.dateIntroduit;
        this.dateInterrompu = builder.dateInterrompu;
        this.fabricant = builder.fabricant;

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
     * Gets the fabricant.
     *
     * @return the fabricant
     */
    public Optional<Entreprise> getFabricant() {

        return fabricant;

    }

    /**
     * The Class OrdinateurBuilder.
     */
    public static class OrdinateurBuilder {

        /** The id. */
        private long id;

        /** The name. */
        private String name;

        /** The date introduit. */
        private LocalDate dateIntroduit;

        /** The date interrompu. */
        private LocalDate dateInterrompu;

        /** The fabricant. */
        private Optional<Entreprise> fabricant;

        /**
         * Instantiates a new ordinateur builder.
         *
         * @param name
         *            the name
         */
        public OrdinateurBuilder(String name) {

            this.name = name;

        }

        /**
         * Id.
         *
         * @param id
         *            the id
         * @return the ordinateur builder
         */
        public OrdinateurBuilder id(long id) {

            this.id = id;
            return this;

        }

        /**
         * Name.
         *
         * @param name
         *            the name
         * @return the ordinateur builder
         */
        public OrdinateurBuilder name(String name) {

            this.name = name;
            return this;

        }

        /**
         * Date introduit.
         *
         * @param dateIntroduit
         *            the date introduit
         * @return the ordinateur builder
         */
        public OrdinateurBuilder dateIntroduit(LocalDate dateIntroduit) {

            this.dateIntroduit = dateIntroduit;
            return this;

        }

        /**
         * Date interrompu.
         *
         * @param dateInterrompu
         *            the date interrompu
         * @return the ordinateur builder
         */
        public OrdinateurBuilder dateInterrompu(LocalDate dateInterrompu) {

            this.dateInterrompu = dateInterrompu;
            return this;

        }

        /**
         * Fabricant.
         *
         * @param fabricant
         *            the fabricant
         * @return the ordinateur builder
         */
        public OrdinateurBuilder fabricant(Entreprise fabricant) {

            this.fabricant = Optional.ofNullable(fabricant);
            return this;

        }

        /**
         * Builds the.
         *
         * @return the ordinateur
         */
        public Ordinateur build() {

            return new Ordinateur(this);

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
        result = prime * result
                + ((dateInterrompu == null) ? 0 : dateInterrompu.hashCode());
        result = prime * result
                + ((dateIntroduit == null) ? 0 : dateIntroduit.hashCode());
        result = prime * result
                + ((fabricant == null) ? 0 : fabricant.hashCode());
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

        Ordinateur other = (Ordinateur) obj;

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
        if (fabricant == null) {

            if (other.fabricant != null) {

                return false;

            }

        } else if (!fabricant.equals(other.fabricant)) {

            return false;

        }
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

        String chaine = "Ordinateur numero ";
        chaine += this.id + " : " + this.name + "\t" + this.dateIntroduit + "\t"
                + this.dateInterrompu + "\t";

        if (this.fabricant.isPresent()) {

            chaine += this.fabricant.get().getName();

        } else {

            chaine += "NULL";

        }

        return chaine;

    }

}
