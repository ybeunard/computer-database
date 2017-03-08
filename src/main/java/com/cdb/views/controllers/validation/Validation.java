package com.cdb.views.controllers.validation;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.OrdinateurDto;

/**
 * The Class Validation.
 */
public class Validation {

    /**
     * Instantiates a new validation.
     */
    private Validation() {

    }

    /** The Constant logger. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(Validation.class);

    /**
     * Validation ordinateur dto.
     *
     * @param request
     *            the request
     * @param ordinateur
     *            the ordinateur
     * @return true, if successful
     */
    public static boolean validationOrdinateurDto(HttpServletRequest request,
            OrdinateurDto ordinateur) {

        LOGGER.info("Validation d'un ordinateur DTO");

        if (ordinateur.getName() == null || ordinateur.getName().equals("")) {

            LOGGER.debug("Nom Incorrecte " + ordinateur.getName());
            request.setAttribute("error", 1);
            return false;

        }

        if (ordinateur.getId() < 0) {

            LOGGER.debug("ID Incorrecte " + ordinateur.getId());
            return false;

        }

        if (!validationDate(ordinateur.getDateIntroduit())) {

            LOGGER.debug("Date introduction Incorrecte "
                    + ordinateur.getDateIntroduit());
            request.setAttribute("error", 1);
            return false;

        }

        if (!validationDate(ordinateur.getDateInterrompu())) {

            LOGGER.debug("Date interruption Incorrecte "
                    + ordinateur.getDateInterrompu());
            request.setAttribute("error", 1);
            return false;

        }

        if (!isValid(ordinateur.getDateIntroduit(),
                ordinateur.getDateInterrompu())) {

            LOGGER.debug("Dates Inccohérente");
            request.setAttribute("error", 1);
            return false;

        }

        if (ordinateur.getIdFactory() < 0) {

            LOGGER.debug("Id Company Incorrecte " + ordinateur.getIdFactory());
            return false;

        }

        return true;

    }

    /**
     * Validation date.
     *
     * @param date
     *            the date
     * @return true, if successful
     */
    private static boolean validationDate(String date) {

        if (date != null) {

            if (date.equals("")) {

                return true;

            }

            return date.matches("\\d{4}-\\d{2}-\\d{2}");

        }

        return false;

    }

    /**
     * Checks if is valid.
     *
     * @param introduced
     *            the introduced
     * @param discontinued
     *            the discontinued
     * @return true, if is valid
     */
    private static boolean isValid(String introduced, String discontinued) {

        if (!introduced.equals("") && !discontinued.equals("")) {

            LocalDate before = Parse.parseDate(introduced);
            LocalDate after = Parse.parseDate(discontinued);

            if (before.isBefore(after)) {

                return true;

            }

            return false;

        }

        return true;

    }

}
