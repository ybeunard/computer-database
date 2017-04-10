package com.cdb.controllers.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cdb.model.dto.ComputerDto;
import com.cdb.utils.Parse;

@Component
public class ComputerDtoValidation implements Validator {

    /** The Constant logger. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(ComputerDtoValidation.class);

    /**
     * Instantiates a new ordinateur dto validation.
     */
    public ComputerDtoValidation() {

        LOGGER.info("ComputerDtoValidation Instantiated");

    }

    /**
     * @param clazz
     *            this clazz
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> clazz) {

        return ComputerDto.class.equals(clazz);

    }

    /**
     * @param target
     *            the target
     * @param errors
     *            the errors
     */
    @Override
    public void validate(Object target, Errors errors) {

        LOGGER.info("Validation ComputerDTO");
        ComputerDto computerDto = (ComputerDto) target;

        if (computerDto.getName() == null) {

            errors.rejectValue("name", "NotEmpty.computerDtoForm.name");

        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                "NotEmpty.computerDtoForm.name");

        if (computerDto.getId() < 0) {

            LOGGER.debug("Incorrect ID " + computerDto.getId());
            errors.reject("id");

        }

        if (!validationDate(computerDto.getIntroduced())) {

            LOGGER.debug(
                    "Incorrect introduced Date " + computerDto.getIntroduced());
            errors.rejectValue("introduced",
                    "NotValid.computerDtoForm.dateIntroduit");

        }

        if (!validationDate(computerDto.getDiscontinued())) {

            LOGGER.debug("Incorrect discontinued Date "
                    + computerDto.getDiscontinued());
            errors.rejectValue("discontinued",
                    "NotValid.computerDtoForm.dateInterrompu");

        }

        if (!isValid(computerDto.getIntroduced(),
                computerDto.getDiscontinued())) {

            LOGGER.debug("Incoherent Date");
            errors.rejectValue("introduced",
                    "Incoherence.computerDtoForm.dateIntroduit");
            errors.reject("discontinued",
                    "Incoherence.computerDtoForm.dateInterrompu");

        }

        if (computerDto.getIdCompany() < 0) {

            LOGGER.debug("Incorrect Id Company " + computerDto.getIdCompany());
            errors.rejectValue("idCompany",
                    "NotNegatif.computerDtoForm.idFactory");

        }

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

            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {

                try {

                    Parse.parseDate(date);
                    return true;

                } catch (DateTimeParseException e) {

                    return false;

                }

            }

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

        if (!introduced.equals("") && !discontinued.equals("")
                && validationDate(introduced) && validationDate(discontinued)) {

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
