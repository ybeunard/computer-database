package com.cdb.dao.Impl.converter;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class LocalDateAttributeConverter.
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter
        implements AttributeConverter<LocalDate, Timestamp> {

    /** The Constant logger. */
    public final Logger LOGGER = LoggerFactory
            .getLogger(LocalDateAttributeConverter.class);

    /**
     * Instantiates a new local date attribute converter.
     */
    public LocalDateAttributeConverter() {

        LOGGER.info("instancié");

    }

    /**
     * @param locDate
     *            the local date
     * @return un timestamp
     **/
    @Override
    public Timestamp convertToDatabaseColumn(LocalDate locDate) {

        if (locDate == null) {

            return null;

        }

        return Timestamp.valueOf(locDate.atStartOfDay());

    }

    /**
     * @param sqlTimestamp
     *            The timestamp
     * @return une local date
     **/
    @Override
    public LocalDate convertToEntityAttribute(Timestamp sqlTimestamp) {

        if (sqlTimestamp == null) {

            return null;

        }

        return sqlTimestamp.toLocalDateTime().toLocalDate();
    }

}
