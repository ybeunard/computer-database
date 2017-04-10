package com.cdb.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdb.dao.Impl.CompanyDao;
import com.cdb.model.dto.CompanyDto;
import com.cdb.model.entities.Company;
import com.cdb.services.InterfaceCompanyService;
import com.cdb.utils.mappers.CompanyDtoMapper;

public class CompanyService implements InterfaceCompanyService {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(CompanyService.class);

    /** The entreprise dao. */
    @Autowired
    private CompanyDao companyDao;

    /**
     * Gets the entreprise dao.
     *
     * @return the entreprise dao
     */
    public CompanyDao getCompanyDao() {

        return companyDao;

    }

    /**
     * Instantiates a new gestion entreprise.
     */
    CompanyService() {

        LOGGER.info("CompanyService Instantiated");

    }
    
    @Override
    public List<CompanyDto> findCompanies() {
        
        LOGGER.info("Service: search all companies");
        List<Company> companies = new ArrayList<Company>();
        companies = companyDao.findCompanies();
        return CompanyDtoMapper.recoveryListCompany(companies);
        
    }

    @Override
    public Company findCompanyById(long id) {
        
        LOGGER.info("Service: search company by id");
        return companyDao.findCompanyByID(id);
        
    }

}
