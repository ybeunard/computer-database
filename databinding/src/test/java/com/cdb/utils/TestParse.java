package com.cdb.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.cdb.model.entities.Company;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class TestParse.
 */
public class TestParse extends TestCase {
    
    /**
     * Test parse entier.
     */
    @Test
    public void testParseEntier() {
        
        assertTrue(Parse.parseEntier(null, 10) == 10);
        assertTrue(Parse.parseEntier("", 10) == 10);
        assertTrue(Parse.parseEntier("qzdqzd", 10) == 10);
        assertTrue(Parse.parseEntier("20", 10) == 20);
        assertTrue(Parse.parseEntier("-2000", 10) == -2000);
        
    }
    
    /**
     * Test parse string.
     */
    @Test
    public void testParseString() {
        
        assertTrue(Parse.parseString(null, "aaa").equals("aaa"));
        assertTrue(Parse.parseString("", "aaa").equals("aaa"));
        assertTrue(Parse.parseString("aaa", null).equals("aaa"));
        assertTrue(Parse.parseString("", null) == null);
        assertTrue(Parse.parseString("aaa", "").equals("aaa"));
        
    }
    
    /**
     * Test parse long.
     */
    @Test
    public void testParseLong() {
        
        assertTrue(Parse.parseLong(null, 10) == 10);
        assertTrue(Parse.parseLong("", 10) == 10);
        assertTrue(Parse.parseLong("azzaer", 10) == 10);
        assertTrue(Parse.parseLong("500000", 10) == 500000);
        assertTrue(Parse.parseLong("-2000", 10) == -2000);
        
    }
    
    /**
     * Test parse date.
     */
    @Test
    public void testParseDate() {
        
        LocalDate date = LocalDate.parse("2017-12-20",
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));;
        assertTrue(Parse.parseDate(null) == null);
        assertTrue(Parse.parseDate("") == null);
        
        try {
            
            Parse.parseDate("zqdqzd");
            
        } catch (DateTimeException e) {
            
            assertTrue(e.getMessage() != null);
            
        }
        
        try {
            
            Parse.parseDate("9999-15-55");
            
        } catch (DateTimeException e) {
            
            assertTrue(e.getMessage() != null);
            
        }

        assertTrue(Parse.parseDate("2017-12-20").equals(date));
        
    }
    
    /**
     * Test parse factory.
     */
    @Test
    public void testParseFactory() {
        
        Company fred = new Company.CompanyBuilder("fred").id(20).build();
        Company alice = new Company.CompanyBuilder(null).id(25).build();
        Company bob = new Company.CompanyBuilder("").id(15).build();
        assertTrue(Parse.parseFactory(-20, null) == null);
        assertTrue(Parse.parseFactory(0, null) == null);
        assertTrue(Parse.parseFactory(25, null).equals(alice));
        assertTrue(Parse.parseFactory(15, "").equals(bob));
        assertTrue(Parse.parseFactory(20, "fred").equals(fred));
        
    }
    
    /**
     * Test parse boolean.
     */
    @Test
    public void testParseBoolean() {
        
        assertTrue(Parse.parseBoolean(null, true) == true);
        assertTrue(Parse.parseBoolean("", true) == true);
        assertTrue(Parse.parseBoolean("qzdqzd", true) == false);
        assertTrue(Parse.parseBoolean("true", false) == true);
        assertTrue(Parse.parseBoolean("false", true) == false);
        
    }
    
}
