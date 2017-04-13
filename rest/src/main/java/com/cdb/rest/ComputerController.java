package com.cdb.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdb.model.dto.ComputerDto;
import com.cdb.model.dto.ComputerDto.ComputerDtoBuilder;
import com.cdb.rest.validation.ComputerDtoValidation;
import com.cdb.services.Impl.ComputerService;
import com.cdb.utils.Parse;
import com.cdb.utils.mappers.ComputerMapper;

@RestController
@RequestMapping("/computer")
public class ComputerController {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(ComputerController.class);

    /** The gestion ordinateur. */
    @Autowired
    ComputerService computerService;

    /**
     * Gets the gestion ordinateur.
     *
     * @return the gestion ordinateur
     */
    public ComputerService getComputerService() {

        return computerService;

    }

    /**
     * Instantiates a new dashboard controller.
     */
    public ComputerController() {

        LOGGER.info("ComputerController Instantiated");

    }
    
    @RequestMapping("/find")
    public List<ComputerDto> findComputer(@RequestParam(value="id", defaultValue="") String idStr, @RequestParam(value="name", defaultValue="") String name, @RequestParam(value="numPage", defaultValue="") String numPageStr, @RequestParam(value="rowByPage", defaultValue="") String rowByPageStr) {
        
        LOGGER.info("WebService: find computer");
        List<ComputerDto> computers = new ArrayList<ComputerDto>();
        
        if (!idStr.equals("")) {
            
            Long id = Parse.parseLong(idStr, 0);
            
            if (id == 0) {
                
                return computers;
                
            }

            ComputerDto computer = computerService.findComputerById(id);

            if (computer.getId() != 0) {
                
                computers.add(computer);
                
            }
            return computers;
            
        }
        
        if (!numPageStr.equals("") && !rowByPageStr.equals("")) {
            
            int numPage = Parse.parseEntier(numPageStr, 1);
            int rowByPage = Parse.parseEntier(rowByPageStr, 100);
            return computerService.findComputerByPage(numPage, rowByPage, name, "", false).getContent();
            
        }
        
        return computers;

    }
    
    @RequestMapping("/create")
    public String createComputer(@RequestParam(value="name", defaultValue="") String name, @RequestParam(value="introduced", defaultValue="") String introduced, @RequestParam(value="discontinued", defaultValue="") String discontinued, @RequestParam(value="idCompany", defaultValue="") String idCompanyStr) {
        
        LOGGER.info("WebService: create computer");
        
        if(name.equals("")) {
            
            return "Le champ name est obligatoire";
            
        }
        
        ComputerDtoBuilder builder = new ComputerDto.ComputerDtoBuilder(name);
        
        if(!introduced.equals("")) {
            
            builder.introduced(introduced);
            
        }
        
        if(!discontinued.equals("")) {
            
            builder.discontinued(discontinued);
            
        }
        
        if(!idCompanyStr.equals("")) {
            
            Long idCompany = Parse.parseLong(idCompanyStr, 0);
            
            if (idCompany == 0) {
                
                return "L'id de company doit être un nombre entier";
                
            }
            
            builder.idCompany(idCompany);
            
        }
        
        ComputerDto computerDto = builder.build();
        
        if (ComputerDtoValidation.validate(computerDto)) {
        
            computerService.createComputer(ComputerMapper.recoveryComputer(computerDto));
            return "Creation effectuée";
            
        } else {
            
            return "L'un des arguments donnée est incorrecte";
            
        }
        
    }
    
    @RequestMapping("/update")
    public String updateComputer(@RequestParam(value="id", defaultValue="") String idStr, @RequestParam(value="name", defaultValue="") String name, @RequestParam(value="introduced", defaultValue="") String introduced, @RequestParam(value="discontinued", defaultValue="") String discontinued, @RequestParam(value="idCompany", defaultValue="") String idCompanyStr) {
        
        LOGGER.info("WebService: update computer");
            
        Long id = Parse.parseLong(idStr, 0);
            
        if (id == 0) {
            
            return "L'id de computer doit être un entier";
            
        }
        
        ComputerDto computer = computerService.findComputerById(id);
        
        if (computer.getName().equals("")) {
            
            return "Computer inexistant, id incorrecte";
            
        }
        
        ComputerDtoBuilder builder;
        
        if(name.equals("")) {
            
            builder = new ComputerDto.ComputerDtoBuilder(computer.getName());
            
        } else {
            
            builder = new ComputerDto.ComputerDtoBuilder(name);
            
        }
        
        if(!introduced.equals("")) {
            
            builder.introduced(introduced);
            
        } else {
            
            builder.introduced(computer.getIntroduced());
            
        }
        
        if(!discontinued.equals("")) {
            
            builder.discontinued(discontinued);
            
        } else {
            
            builder.discontinued(computer.getDiscontinued());
            
        }
        
        if(!idCompanyStr.equals("")) {
            
            Long idCompany = Parse.parseLong(idCompanyStr, 0);
            
            if (idCompany == 0) {
                
                return "L'id de company doit être un nombre entier";
                
            }
            
            builder.idCompany(idCompany);
            
        } else {
            
            builder.idCompany(computer.getIdCompany());
            
        }
        
        builder.id(computer.getId());
        ComputerDto computerDto = builder.build();

        if (ComputerDtoValidation.validate(computerDto)) {
        
            computerService.updateComputer(ComputerMapper.recoveryComputer(computerDto));
            return "Update effectuée";
            
        } else {
            
            return "L'un des arguments donnée est incorrecte";
            
        }
        
    }
    
    @RequestMapping("/delete")
    public String deleteComputer(@RequestParam(value="id", defaultValue="") String idStr) {
        
        LOGGER.info("WebService: delete computer");
        List<Long> ids = new ArrayList<Long>();
        Long id = Parse.parseLong(idStr, 0);
            
        if (id == 0) {
            
            return "L'id de computer doit être un entier";
            
        }
        
        ids.add(id);
        computerService.deleteComputer(ids);
        return "delete effectué";
        
    }
    
}
