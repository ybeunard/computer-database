package com.cdb.utils.mappers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cdb.model.dto.CompanyDto;
import com.cdb.model.entities.Company;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class TestCompanyDtoMapper.
 */
public class TestCompanyDtoMapper extends TestCase {

    /**
     * Test recovery company.
     */
    @Test
    public void testRecoveryCompany() {
        
        Company vide = new Company.CompanyBuilder("").id(0).build();
        Company fred = new Company.CompanyBuilder("fred").id(15).build();
        Company importe = new Company.CompanyBuilder(null).id(-15).build();
        CompanyDto videDto = new CompanyDto.CompanyDtoBuilder(0, "").build();
        CompanyDto fredDto = new CompanyDto.CompanyDtoBuilder(15, "fred").build();
        CompanyDto importeDto = new CompanyDto.CompanyDtoBuilder(-15, "").build();
        assertTrue(CompanyDtoMapper.recoveryCompany(vide).equals(videDto));
        assertTrue(CompanyDtoMapper.recoveryCompany(null).equals(videDto));
        assertTrue(CompanyDtoMapper.recoveryCompany(fred).equals(fredDto));
        assertTrue(CompanyDtoMapper.recoveryCompany(importe).equals(importeDto));
        
    }
    
    /**
     * Test recovery list company.
     */
    @Test
    public void testRecoveryListCompany() {
        
        List<CompanyDto> companiesDto = new ArrayList<CompanyDto>();
        assertTrue(CompanyDtoMapper.recoveryListCompany(null).equals(companiesDto));
        
        List<Company> companies = new ArrayList<Company>();
        assertTrue(CompanyDtoMapper.recoveryListCompany(companies).equals(companiesDto));
        
        companies.add(new Company.CompanyBuilder("fred").id(15).build());
        companiesDto.add(new CompanyDto.CompanyDtoBuilder(15, "fred").build());
        assertTrue(CompanyDtoMapper.recoveryListCompany(companies).equals(companiesDto));
        
    }
    
}
