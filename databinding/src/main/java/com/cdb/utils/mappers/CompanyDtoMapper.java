package com.cdb.utils.mappers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.model.dto.CompanyDto;
import com.cdb.model.dto.CompanyDto.CompanyDtoBuilder;
import com.cdb.model.entities.Company;

/**
 * The Class CompanyDtoMapper.
 */
public class CompanyDtoMapper {

    /**
     * Instantiates a new entreprise dto mapper.
     */
    private CompanyDtoMapper() {

    }

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(CompanyDtoMapper.class);

    /**
     * Recuperation list entreprise.
     *
     * @param companies
     *            the companies
     * @return the list
     */
    public static List<CompanyDto> recoveryListCompany(
            List<Company> companies) {

        LOGGER.info("Mapping List Company in List CompanyDto");
        List<CompanyDto> companiesDto = new ArrayList<CompanyDto>();

        for (Company company : companies) {

            companiesDto.add(recoveryCompany(company));

        }

        return companiesDto;
    }

    /**
     * Recuperation entreprise.
     *
     * @param company
     *            the company
     * @return the entreprise dto
     */
    private static CompanyDto recoveryCompany(Company company) {

        CompanyDtoBuilder builder = new CompanyDto.CompanyDtoBuilder(
                company.getId(), company.getName());
        return builder.build();

    }

}
