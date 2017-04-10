package com.cdb.model.dto;

/**
 * The Class ComputerDto.
 */
public class ComputerDto {

    /** The id. */
    private long id;

    /** The name. */
    private String name;

    /** The date introduit. */
    private String introduced;

    /** The date interrompu. */
    private String discontinued;

    /** The factory. */
    private String company;

    /** The id factory. */
    private long idCompany;

    /**
     * Instantiates a new ordinateur dto.
     */
    public ComputerDto() {
    }

    /**
     * Instantiates a new ordinateur DTO.
     *
     * @param builder
     *            the builder
     */
    private ComputerDto(ComputerDtoBuilder builder) {

        this.id = builder.id;
        this.name = builder.name;
        this.introduced = builder.introduced;
        this.discontinued = builder.discontinued;
        this.company = builder.company;
        this.idCompany = builder.idCompany;

    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(long id) {

        this.id = id;

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
     * Sets the introduced.
     *
     * @param introduced
     *            the new introduced
     */
    public void setIntroduced(String introduced) {

        this.introduced = introduced;

    }

    /**
     * Sets the discontinued.
     *
     * @param discontinued
     *            the new discontinued
     */
    public void setDiscontinued(String discontinued) {

        this.discontinued = discontinued;

    }

    /**
     * Sets the company.
     *
     * @param company
     *            the new company
     */
    public void setCompany(String company) {

        this.company = company;

    }

    /**
     * Sets the id company.
     *
     * @param idCompany
     *            the new id company
     */
    public void setIdCompany(long idCompany) {

        this.idCompany = idCompany;

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
    public String getIntroduced() {

        return introduced;

    }

    /**
     * Gets the date interrompu.
     *
     * @return the date interrompu
     */
    public String getDiscontinued() {

        return discontinued;

    }

    /**
     * Gets the factory.
     *
     * @return the factory
     */
    public String getCompany() {

        return company;

    }

    /**
     * Gets the id factory.
     *
     * @return the id factory
     */
    public long getIdCompany() {

        return idCompany;

    }

    /**
     * The Class OrdinateurDTOBuilder.
     */
    public static class ComputerDtoBuilder {

        /** The id. */
        private long id;

        /** The name. */
        private final String name;

        /** The date introduit. */
        private String introduced;

        /** The date interrompu. */
        private String discontinued;

        /** The factory. */
        private String company;

        /** The id factory. */
        private long idCompany;

        /**
         * Instantiates a new ordinateur DTO builder.
         *
         * @param name
         *            the name
         */
        public ComputerDtoBuilder(String name) {

            this.name = name;

        }

        /**
         * Id.
         *
         * @param id
         *            the id
         * @return the ordinateur dto builder
         */
        public ComputerDtoBuilder id(long id) {

            this.id = id;
            return this;

        }

        /**
         * Date introduit.
         *
         * @param introduced
         *            the date introduit
         * @return the ordinateur DTO builder
         */
        public ComputerDtoBuilder introduced(String introduced) {

            this.introduced = introduced;
            return this;

        }

        /**
         * Date interrompu.
         *
         * @param discontinued
         *            the date interrompu
         * @return the ordinateur DTO builder
         */
        public ComputerDtoBuilder discontinued(String discontinued) {

            this.discontinued = discontinued;
            return this;

        }

        /**
         * Factory.
         *
         * @param company
         *            the company
         * @return the ordinateur DTO builder
         */
        public ComputerDtoBuilder company(String company) {

            this.company = company;
            return this;

        }

        /**
         * Id factory.
         *
         * @param idCompany
         *            the id company
         * @return the ordinateur dto builder
         */
        public ComputerDtoBuilder idCompany(long idCompany) {

            this.idCompany = idCompany;
            return this;

        }

        /**
         * Builds the.
         *
         * @return the ordinateur DTO
         */
        public ComputerDto build() {

            return new ComputerDto(this);

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
                + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result
                + ((introduced == null) ? 0 : introduced.hashCode());
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (idCompany ^ (idCompany >>> 32));
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

        ComputerDto other = (ComputerDto) obj;

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

        if (idCompany != other.idCompany) {

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

        return "ComputerDto [id=" + id + ", name=" + name + ", introduced="
                + introduced + ", discontinued=" + discontinued + ", company="
                + company + ", idCompany=" + idCompany + "]";

    }

}
