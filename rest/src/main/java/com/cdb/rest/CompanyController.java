package com.cdb.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdb.model.dto.CompanyDto;
import com.cdb.services.Impl.CompanyService;

@RestController
public class CompanyController {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(CompanyController.class);

    /** The gestion ordinateur. */
    @Autowired
    CompanyService companyService;

    /**
     * Gets the gestion ordinateur.
     *
     * @return the gestion ordinateur
     */
    public CompanyService getCompanyService() {

        return companyService;

    }

    /**
     * Instantiates a new dashboard controller.
     */
    public CompanyController() {

        LOGGER.info("CompanyController Instantiated");

    }
    
    @RequestMapping("/company")
    public List<CompanyDto> findCompany(@RequestParam(value="id", defaultValue="0") Long id) {
        
        LOGGER.info("WebService: find company");

        List<CompanyDto> companies = new ArrayList<CompanyDto>();
        
        if (id<=0) {
            
            companies = companyService.findCompanies();
            
        } else {
            
            companies.add(companyService.findCompanyById(id));
            
        }
        
        return companies;
        
    }
    
}
