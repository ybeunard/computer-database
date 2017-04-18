package com.cdb.model.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The name. */
    private String name;

    /** The date introduit. */
    private LocalDate introduced;

    /** The date interrompu. */
    private LocalDate discontinued;

    /** The fabricant. */
    @ManyToOne    
    @JoinColumn(name = "company_id")
    private Company company;

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

    public LocalDate getIntroduced() {

        return introduced;

    }

    public LocalDate getDiscontinued() {

        return discontinued;

    }

    /**
     * Gets the fabricant.
     *
     * @return the fabricant
     */
    public Company getCompany() {

        return company;

    }

    public void setId(long id) {

        this.id = id;

    }

    public void setName(String name) {

        this.name = name;

    }

    public void setIntroduced(LocalDate introduced) {

        this.introduced = introduced;

    }

    public void setDiscontinued(LocalDate discontinued) {

        this.discontinued = discontinued;

    }

    public void setCompany(Company company) {

        this.company = company;

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
                + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result
                + ((introduced == null) ? 0 : introduced.hashCode());
        result = prime * result + ((company == null) ? 0 : company.hashCode());
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

        Computer other = (Computer) obj;

        if (discontinued == null) {

            if (other.discontinued != null) {

                return false;

            }

        } else if (!discontinued.equals(other.discontinued)) {

            return false;

        }
        if (introduced == null) {

            if (other.introduced != null) {

                return false;

            }

        } else if (!introduced.equals(other.introduced)) {

            return false;

        }
        if (company == null) {

            if (other.company != null) {

                return false;

            }

        } else if (!company.equals(other.company)) {

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
        return "Class : " + this.getClass().getSimpleName() + "\n" + "\t" + "id : " + getId() + "\n" + "\t" + "name : "
                + getName() + "\n" + "\t" + "introduced date : "
                + (getIntroduced() == null ? "null" : getIntroduced()) + "\n" + "\t"
                + "discontinued date : " + (getDiscontinued() == null ? "null" : getDiscontinued())
                + "\n" + "\t" + "company name : " + (getCompany() == null ? "null" : getCompany().toString())
                + "\n";
    }
}
