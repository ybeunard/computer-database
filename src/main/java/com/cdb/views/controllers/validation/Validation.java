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
     * @param request 
     *
     * @param ordinateur
     *            the ordinateur
     * @return true, if successful
     */
    public static boolean validationOrdinateurDto(HttpServletRequest request, OrdinateurDto ordinateur) {
        
        LOGGER.info("" + ordinateur);

        if (ordinateur.getName() == null || ordinateur.getName().equals("")) {
            
            LOGGER.info("Nom Incorrecte " + ordinateur.getName());
            request.setAttribute("error", 1);
            return false;

        }

        if (ordinateur.getId() < 0) {

            LOGGER.info("ID Incorrecte " + ordinateur.getId());
            return false;

        }

        if (!validationDate(ordinateur.getDateIntroduit())) {

            LOGGER.info("Date introduction Incorrecte " + ordinateur.getDateIntroduit());
            request.setAttribute("error", 1);
            return false;

        }

        if (!validationDate(ordinateur.getDateInterrompu())) {

            LOGGER.info("Date interruption Incorrecte " + ordinateur.getDateInterrompu());
            request.setAttribute("error", 1);
            return false;

        }

        if (!isValid(ordinateur.getDateIntroduit(),
                ordinateur.getDateInterrompu())) {

            LOGGER.info("Dates Inccohérente");
            request.setAttribute("error", 1);
            return false;

        }

        if (ordinateur.getIdFactory() < 0) {

            LOGGER.info("Id Company Incorrecte " + ordinateur.getIdFactory());
            return false;

        }

        LOGGER.info("Validation OK");
        return true;

    }

    /**
     * Validation date.
     *
     * @param date
     *            the date
     * @return true, if successful
     */
    public static boolean validationDate(String date) {

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
    public static boolean isValid(String introduced, String discontinued) {

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
