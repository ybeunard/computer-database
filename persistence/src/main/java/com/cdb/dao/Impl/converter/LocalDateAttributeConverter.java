package com.cdb.dao.Impl.converter;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Timestamp> {
    
    /** The Constant logger. */
    public final Logger LOGGER = LoggerFactory.getLogger(LocalDateAttributeConverter.class);
    
    public LocalDateAttributeConverter() {
        
        LOGGER.info("instancié");
        
    }
    
    @Override
    public Timestamp convertToDatabaseColumn(LocalDate locDate) {
        
        if(locDate == null){
            
            return null;
            
        }

        return Timestamp.valueOf(locDate.atStartOfDay());
        
    }

    @Override
    public LocalDate convertToEntityAttribute(Timestamp sqlTimestamp) {
        
        if(sqlTimestamp==null) {
            
            return null;
            
        }
        
        return sqlTimestamp.toLocalDateTime().toLocalDate();
    }

}
