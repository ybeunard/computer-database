package com.cdb.controllers.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.Impl.EntrepriseDao;

public class DateValidation {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(EntrepriseDao.class);

    /**
     * Parses the date.
     *
     * @param date
     *            the date
     * @return the optional
     */
    public static Optional<LocalDate> parseDate(String date) {

        LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return Optional.ofNullable(d);

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
    public static boolean isValid(LocalDate introduced,
            LocalDate discontinued) {

        if (introduced.isBefore(discontinued)) {

            return true;

        }

        return false;

    }

}
