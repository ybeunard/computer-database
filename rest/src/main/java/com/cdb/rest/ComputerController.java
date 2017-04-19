package com.cdb.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdb.model.dto.ComputerDto;
import com.cdb.model.dto.PageDto;
import com.cdb.model.entities.Computer;
import com.cdb.services.Impl.ComputerService;
import com.cdb.utils.mappers.ComputerMapper;

@RestController
@RequestMapping("/computers")
@Produces("application/json")
@Consumes("application/json")
public class ComputerController {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(ComputerController.class);

    /** The computer's service. */
    ComputerService computerService;

    /**
     * Constructor with injection.
     * @param computerService computerservice
     */
    public ComputerController (ComputerService computerService) {
        
        LOGGER.info("ComputerController Instantiated");

        this.computerService = computerService;
    }


    /**
     * Find a computer by id.
     * @param id : id of computer to find
     * @return response
     */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> findComputer(@PathVariable Long id) {

        LOGGER.info("WebService: find computer");

        ComputerDto computerDto = computerService.findComputerById(id);

        return new ResponseEntity<ComputerDto>(computerDto, HttpStatus.OK);
    }


    /**
     * Find page of computer.
     * @param pageDto : page
     * @return response
     */
    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<?> findComputers(@PathVariable Long id, @PathVariable Long id) {

        LOGGER.info("WebService: find all computer");

        try {

            pageDto = computerService.findComputerByPage(pageDto.getNumPage(), pageDto.getRowByPage(), pageDto.getFilter(), pageDto.getSort(), pageDto.getDesc());
            
            return new ResponseEntity<List<ComputerDto>>(pageDto.getContent(), HttpStatus.OK);
        
        } catch (Exception persistenceException) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Post a computer.
     * @param computerDto :  computer to post
     * @return response
     */
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<?> postComputer(@RequestBody ComputerDto computerDto) {

        LOGGER.info("WebService: post computer");

        LOGGER.info("WebService: post the computerDto" + computerDto.toString());

        
        Computer computer = ComputerMapper.recoveryComputer(computerDto);
        
        
        LOGGER.info("WebService: post the computer" + computer.toString());

        computerService.createComputer(computer);
        
        return new ResponseEntity<ComputerDto>(computerDto, HttpStatus.OK);
    }

    /**
     * Update a computer.
     * @param computerDto :  computer to post
     * @return response
     */
    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<?> putComputer(@RequestBody ComputerDto computerDto) {

        LOGGER.info("WebService: update computer");

        Computer computer = ComputerMapper.recoveryComputer(computerDto);
        
        computerService.updateComputer(computer);
        
        return new ResponseEntity<ComputerDto>(computerDto, HttpStatus.OK);
    }


    /**
     * Delete a comuter.
     * @param id : id of computer to delete
     * @return response
     */
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteComputer(@PathVariable Long id)   {

        LOGGER.info("WebService: delete computer");

        computerService.deleteOneComputer(id);
        
        return new ResponseEntity<Long>( id, HttpStatus.OK);
    }

}
