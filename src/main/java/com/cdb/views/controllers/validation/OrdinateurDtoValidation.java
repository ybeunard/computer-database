package com.cdb.views.controllers.validation;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cdb.model.dto.OrdinateurDto;
import com.cdb.utils.Parse;

@Component
public class OrdinateurDtoValidation implements Validator {

    /** The Constant logger. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(OrdinateurDtoValidation.class);
    
    public OrdinateurDtoValidation() {
    
        LOGGER.info("OrdinateurDtoValidation instancié");
        
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        
        return OrdinateurDto.class.equals(clazz);
        
    }

    @Override
    public void validate(Object target, Errors errors) {

        LOGGER.info("Validation d'un ordinateur DTO");
        OrdinateurDto ordinateurDto = (OrdinateurDto) target;
        
        if(ordinateurDto.getName()==null){
            
            errors.rejectValue("name", "NotEmpty.ordinateurDtoForm.name");
            
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.ordinateurDtoForm.name");
        
        if (ordinateurDto.getId() < 0) {

            LOGGER.debug("ID Incorrecte " + ordinateurDto.getId());
            errors.reject("id");

        }

        if (!validationDate(ordinateurDto.getDateIntroduit())) {

            LOGGER.debug("Date introduction Incorrecte "
                    + ordinateurDto.getDateIntroduit());
            errors.rejectValue("dateIntroduit", "NotValid.ordinateurDtoForm.dateIntroduit");

        }

        if (!validationDate(ordinateurDto.getDateInterrompu())) {

            LOGGER.debug("Date interruption Incorrecte "
                    + ordinateurDto.getDateInterrompu());
            errors.rejectValue("dateInterrompu", "NotValid.ordinateurDtoForm.dateInterrompu");

        }

        if (!isValid(ordinateurDto.getDateIntroduit(),
                ordinateurDto.getDateInterrompu())) {

            LOGGER.debug("Dates Inccohérente");
            errors.rejectValue("dateIntroduit", "Incoherence.ordinateurDtoForm.dateIntroduit");
            errors.reject("dateInterrompu", "Incoherence.ordinateurDtoForm.dateInterrompu");

        }

        if (ordinateurDto.getIdFactory() < 0) {

            LOGGER.debug("Id Company Incorrecte " + ordinateurDto.getIdFactory());
            errors.rejectValue("idFactory", "NotNegatif.ordinateurDtoForm.idFactory");

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

        if (!introduced.equals("") && !discontinued.equals("") && validationDate(introduced) && validationDate(discontinued)) {

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
