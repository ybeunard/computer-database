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
@RequestMapping("/company")
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
    
    @RequestMapping("/find")
    public List<CompanyDto> findCompany(@RequestParam(value="id", defaultValue="0") Long id, @RequestParam(value="name", defaultValue="") String name, @RequestParam(value="numPage", defaultValue="0") int numPage, @RequestParam(value="rowByPage", defaultValue="0") int rowByPage) {
        
        LOGGER.info("WebService: find company");

        List<CompanyDto> companies = new ArrayList<CompanyDto>();
        
        if (id>0) {
            
            CompanyDto company = companyService.findCompanyById(id);
            
            if (company.getId() != 0) {
                
                companies.add(company);
                
            }
            
            return companies;
            
        }
        
        if (numPage != 0 && rowByPage != 0) {
            
            return companyService.findCompanyByPage(numPage, rowByPage, name);
            
        }
        
        return companyService.findCompanies();
        
    }
    
    @RequestMapping("/delete")
    public String deleteCompany(@RequestParam(value="id", defaultValue="0") Long id) {
        
        LOGGER.info("WebService: delete company");
        
        if (id>0) {
            
            List<Long> ids = new ArrayList<Long>();
            ids.add(id);
            companyService.deleteCompany(ids);
            return "Suppression Effectué";
            
        }
        
        return "Suppression Impossible sans ID";
        
    }
    
}
