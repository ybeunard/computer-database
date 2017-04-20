package com.cdb.rest.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.ComputerDto;
import com.cdb.utils.Parse;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDtoValidation.
 */
public class ComputerDtoValidation {

    /** The Constant logger. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(ComputerDtoValidation.class);

    /**
     * Validate.
     *
     * @param target
     *            the target
     * @return the boolean
     */
    public static Boolean validate(Object target) {

        LOGGER.info("Validation ComputerDTO");
        ComputerDto computerDto = (ComputerDto) target;

        if (computerDto.getName() == null || computerDto.getName().equals("")) {

            return false;

        }

        if (computerDto.getId() < 0) {

            LOGGER.info("Incorrect ID " + computerDto.getId());
            return false;

        }

        if (!validationDate(computerDto.getIntroduced())) {

            LOGGER.info(
                    "Incorrect introduced Date " + computerDto.getIntroduced());
            return false;

        }

        if (!validationDate(computerDto.getDiscontinued())) {

            LOGGER.info("Incorrect discontinued Date "
                    + computerDto.getDiscontinued());
            return false;

        }

        if (!isValid(computerDto.getIntroduced(),
                computerDto.getDiscontinued())) {

            LOGGER.info("Incoherent Date");
            return false;

        }

        if (computerDto.getIdCompany() < 0) {

            LOGGER.info("Incorrect Id Company " + computerDto.getIdCompany());
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

        if (date == null || date.equals("")) {

            return true;

        }

        if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {

            try {

                Parse.parseDate(date);
                return true;

            } catch (DateTimeParseException e) {

                return false;

            }

        } else {

            return false;

        }

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

        if (introduced != null && discontinued != null && !introduced.equals("")
                && !discontinued.equals("") && validationDate(introduced)
                && validationDate(discontinued)) {

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
