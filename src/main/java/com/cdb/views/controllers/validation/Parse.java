package com.cdb.views.controllers.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.cdb.model.entities.Entreprise;

/**
 * The Class Parse.
 */
public class Parse {

    /**
     * Instantiates a new parses the.
     */
    private Parse() {

    }

    /**
     * Parses the entier.
     *
     * @param entier
     *            the entier
     * @param defaultValeur
     *            the default valeur
     * @return the int
     */
    public static int parseEntier(String entier, int defaultValeur) {

        if (entier != null && !entier.equals("")) {

            defaultValeur = Integer.parseInt(entier);

        }

        return defaultValeur;

    }

    /**
     * Parses the string.
     *
     * @param chaine
     *            the chaine
     * @param defaultValeur
     *            the default valeur
     * @return the string
     */
    public static String parseString(String chaine, String defaultValeur) {

        if (chaine != null && !chaine.equals("")) {

            defaultValeur = chaine;

        }

        return defaultValeur;

    }

    /**
     * Parses the long.
     *
     * @param longEntier
     *            the long entier
     * @param defaultValeur
     *            the default valeur
     * @return the long
     */
    public static long parseLong(String longEntier, long defaultValeur) {

        if (longEntier != null && !longEntier.equals("")) {

            defaultValeur = Long.parseLong(longEntier);

        }

        return defaultValeur;

    }

    /**
     * Parses the date.
     *
     * @param date
     *            the date
     * @return the optional
     */
    public static LocalDate parseDate(String date) {

        LocalDate value = null;

        if (date != null && !date.equals("")) {

            value = LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        }

        return value;

    }

    /**
     * Parses the factory.
     *
     * @param entrepriseId
     *            the entreprise id
     * @param entrepriseName
     *            the entreprise name
     * @return the optional
     */
    public static Optional<Entreprise> parseFactory(long entrepriseId,
            String entrepriseName) {

        Optional<Entreprise> factory = Optional.empty();

        if (entrepriseId > 0) {

            factory = Optional
                    .ofNullable(new Entreprise.EntrepriseBuilder(entrepriseName)
                            .id(entrepriseId).build());

        }

        return factory;

    }

}
