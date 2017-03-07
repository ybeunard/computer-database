package com.cdb.controllers.validation;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import com.cdb.dto.OrdinateurDto;

/**
 * The Class Validation.
 */
public class Validation {

    /**
     * Instantiates a new validation.
     */
    private Validation() {

    }

    /**
     * Validation ordinateur dto.
     * @param request 
     *
     * @param ordinateur
     *            the ordinateur
     * @return true, if successful
     */
    public static boolean validationOrdinateurDto(HttpServletRequest request, OrdinateurDto ordinateur) {

        if (ordinateur.getName() == null || ordinateur.getName().equals("")) {

            request.setAttribute("error", 1);
            return false;

        }

        if (ordinateur.getId() <= 0) {

            return false;

        }

        if (!validationDate(ordinateur.getDateIntroduit())) {

            request.setAttribute("error", 1);
            return false;

        }

        if (validationDate(ordinateur.getDateInterrompu())) {

            request.setAttribute("error", 1);
            return false;

        }

        if (!isValid(ordinateur.getDateIntroduit(),
                ordinateur.getDateInterrompu())) {

            request.setAttribute("error", 1);
            return false;

        }

        if (ordinateur.getIdFactory() < 0) {

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
    public static boolean validationDate(String date) {

        if (date != null) {

            if (date.equals("")) {

                return true;

            }

            return date.matches("yyyy-MM-dd");

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

        if (introduced.equals("") && !discontinued.equals("")) {

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
