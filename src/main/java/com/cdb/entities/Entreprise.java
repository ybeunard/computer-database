package com.cdb.entities;

/**
 * The Class Entreprise.
 */
public class Entreprise {

    /** The id. */
    private long id;

    /** The name. */
    private String name;

    /**
     * Instantiates a new entreprise.
     *
     * @param id the id
     * @param name the name
     */
    public Entreprise(long id, String name) {

        super();
        this.id = id;
        this.name = name;

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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;

    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        String chaine = this.id + "\t" + this.name;
        return chaine;

    }

}
