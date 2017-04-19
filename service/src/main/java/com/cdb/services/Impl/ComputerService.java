package com.cdb.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cdb.dao.Impl.ComputerDao;
import com.cdb.model.dto.ComputerDto;
import com.cdb.model.dto.PageDto;
import com.cdb.model.entities.Computer;
import com.cdb.services.InterfaceComputerService;
import com.cdb.utils.mappers.ComputerDtoMapper;
import com.cdb.utils.mappers.PageDtoMapper;

/**
 * The Class ComputerService.
 */
public class ComputerService implements InterfaceComputerService {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(ComputerService.class);

    /** The ordinateur dao. */
    @Autowired
    private ComputerDao computerDao;

    /**
     * Gets the ordinateur dao.
     *
     * @return the ordinateur dao
     */
    public ComputerDao getComputerDao() {

        return computerDao;

    }

    /**
     * Instantiates a new gestion ordinateur.
     */
    public ComputerService() {

        LOGGER.info("ComputerService Instantiated");

    }

    /**
     * recovery PageDto.
     *
     * @param page
     *            the page
     * @return PageDto
     */
    @Override
    public PageDto findComputerByPage(PageDto page) {

        LOGGER.info("Service : search computer by page");
        long nbComputer = 0;
        List<Computer> computers = new ArrayList<Computer>();

        if (page.getFilter() == null || page.getFilter().equals("")) {

            nbComputer = computerDao.countComputer();
            computers = computerDao.findComputerByPage(page.getNumPage(),
                    page.getRowByPage(), page.getSort(), page.getDesc());

        } else {

            nbComputer = computerDao.countComputerByName(page.getFilter());
            computers = computerDao.findComputerByName(page.getNumPage(),
                    page.getRowByPage(), page.getFilter(), page.getSort(),
                    page.getDesc());

        }

        return PageDtoMapper.recoveryPage(computers, page, nbComputer);

    }

    /**
     * recovery the computer by id.
     *
     * @param id
     *            the id of computer
     *
     * @return the computerDto
     */
    @Override
    public ComputerDto findComputerById(long id) {

        LOGGER.info("Service: search computer by id");
        return ComputerDtoMapper
                .recoveryComputerDto(computerDao.findComputerById(id));

    }

    /**
     * Create a computer.
     *
     * @param computer
     *            the new computer
     */
    @Transactional
    @Override
    public void createComputer(Computer computer) {
        LOGGER.info("Service : create computer");
        computerDao.createComputer(computer);

    }

    /**
     * Update a computer.
     *
     * @param computer
     *            the computer update
     */
    @Transactional
    @Override
    public void updateComputer(Computer computer) {

        LOGGER.info("Service: Update computer");
        computerDao.updateComputer(computer);

    }

    /**
     * delete computers.
     *
     * @param id
     *            The list of computer delete
     */
    @Transactional
    @Override
    public void deleteComputer(List<Long> id) {

        LOGGER.info("Service: delete computer");

        for (long identifiant : id) {

            computerDao.deleteComputer(identifiant);

        }

    }

    /**
     * delete computers.
     *
     * @param id
     *            The list of computer delete
     */
    @Transactional
    @Override
    public void deleteOneComputer(Long id) {

        LOGGER.info("Service: delete computer");

        computerDao.deleteComputer(id);

    }

}
