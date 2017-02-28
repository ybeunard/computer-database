package com.cdb.entities;

import java.time.LocalDate;
import java.util.Optional;

/**
 * The Class Ordinateur.
 */
public class Ordinateur {

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
     * Instantiates a new ordinateur.
     *
     * @param name
     *            the name
     */
    public Ordinateur(String name) {

        super();
        this.name = name;
        this.fabricant = Optional.empty();

    }

    /**
     * Instantiates a new ordinateur.
     *
     * @param id
     *            the id
     * @param name
     *            the name
     * @param dateIntroduit
     *            the date introduit
     * @param dateInterrompu
     *            the date interrompu
     * @param fabricant
     *            the fabricant
     */
    public Ordinateur(long id, String name, LocalDate dateIntroduit,
            LocalDate dateInterrompu, Entreprise fabricant) {

        super();
        this.id = id;
        this.name = name;
        this.dateIntroduit = dateIntroduit;
        this.dateInterrompu = dateInterrompu;
        this.fabricant = Optional.ofNullable(fabricant);

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
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(String name) {

        this.name = name;

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
     * Sets the date introduit.
     *
     * @param dateIntroduit
     *            the new date introduit
     */
    public void setDateIntroduit(LocalDate dateIntroduit) {

        this.dateIntroduit = dateIntroduit;

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
     * Sets the date interrompu.
     *
     * @param dateInterrompu
     *            the new date interrompu
     */
    public void setDateInterrompu(LocalDate dateInterrompu) {

        this.dateInterrompu = dateInterrompu;

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
     * Sets the fabricant.
     *
     * @param fabricant
     *            the new fabricant
     */
    public void setFabricant(Entreprise fabricant) {

        this.fabricant = Optional.ofNullable(fabricant);

    }

    /**
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
     * @param obj de comparaison
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
