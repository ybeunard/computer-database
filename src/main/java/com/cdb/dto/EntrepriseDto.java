package com.cdb.dto;

public class EntrepriseDto {

    private final long id;

    private final String name;

    private EntrepriseDto(EntrepriseDtoBuilder builder) {

        this.id = builder.id;
        this.name = builder.name;

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class EntrepriseDtoBuilder {

        private final long id;

        private final String name;

        public EntrepriseDtoBuilder(long id, String name) {

            this.id = id;
            this.name = name;

        }

        public EntrepriseDto build() {

            return new EntrepriseDto(this);

        }

    }

}
