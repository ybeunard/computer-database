package com.cdb.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdb.model.dto.CompanyDto;
import com.cdb.model.dto.ComputerDto;
import com.cdb.model.dto.PageDto;
import com.cdb.model.entities.Company;
import com.cdb.services.Impl.CompanyService;

@RestController
@RequestMapping("/companies")
@Produces("application/json")
@Consumes("application/json")
public class CompanyController {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(CompanyController.class);

    /** The company's service. */
    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    /**
     * Find a company by id.
     * @param id : id of company to find
     * @return response
     */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<CompanyDto> findCompany(@PathVariable Long id) {

        LOGGER.info("WebService: find company");

        CompanyDto companyDto = companyService.findCompanyById(id);
        
        return new ResponseEntity<CompanyDto>(companyDto,HttpStatus.OK);
    }


    /**
     * FInd all companies.
     * @return response
     */
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<CompanyDto>> findCompanies() {
        
        LOGGER.info("WebService: get all companies");
        
        List<CompanyDto> companiesDto = companyService.findCompanies();
        
        return new ResponseEntity<List<CompanyDto>>(companiesDto,HttpStatus.OK);
    }

    /**
     * Find page of computer.
     * @param pageDto : page
     * @return response
     */
    @RequestMapping(value="/{numPage}/{rowByPage}", method=RequestMethod.GET)
    public ResponseEntity<?> findCompaniesPaginated(@PathVariable int numPage, @PathVariable int rowByPage) {

        LOGGER.info("WebService: find all companies");
        String filter = "";
        try {

            List<CompanyDto> companies = companyService.findCompanyByPage(numPage, rowByPage, filter);
            
            return new ResponseEntity<List<CompanyDto>>(companies, HttpStatus.OK);
        
        } catch (Exception persistenceException) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Find page of computer.
     * @param pageDto : page
     * @return response
     */
    @RequestMapping(value="/filter/{filter}", method=RequestMethod.GET)
    public ResponseEntity<?> findCompaniesFiltered(@PathVariable String filter) {

        LOGGER.info("WebService: find all computer");

        try {

            List<Company> companies = companyService.findCompanyByFilter(filter);
            
            return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
        
        } catch (Exception persistenceException) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a company.
     * @param id : id of company to delete
     * @return response
     */
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Long> deleteCompany(@PathVariable Long id) {

        LOGGER.info("WebService: delete company");

        companyService.deleteOneCompany(id);

        return new ResponseEntity<Long>( id, HttpStatus.OK);
    }

}
