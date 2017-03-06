package com.cdb.controllers.validation;

import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.cdb.entities.Entreprise;
import com.cdb.services.Impl.GestionEntreprise;

/**
 * The Class Parse.
 */
public class Parse {

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
     * @param request
     *            the request
     * @return the optional
     */
    public static Optional<LocalDate> parseDate(String date,
            HttpServletRequest request) {

        if (date != null && !date.equals("")) {

            Optional<LocalDate> dateOptional = DateValidation.parseDate(date);

            if (!dateOptional.isPresent()) {

                return null;

            }

            return dateOptional;

        }

        return Optional.empty();

    }

    /**
     * Parses the factory.
     *
     * @param entreprise
     *            the entreprise
     * @return the optional
     */
    public static Optional<Entreprise> parseFactory(String entreprise) {

        Optional<Entreprise> factory = Optional.empty();

        if (entreprise != null && !entreprise.equals("")) {

            long idCompany = Long.parseLong(entreprise);
            factory = GestionEntreprise.INSTANCE_GESTION_ENTREPRISE
                    .findEntrepriseById(idCompany);

        }

        return factory;

    }

}
