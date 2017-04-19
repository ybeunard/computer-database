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
     * @param numPage
     *            the current page
     * @param rowByPage
     *            the number of row by page
     * @param filter
     *            filter by name
     * @param sort
     *            sort by name or company
     * @param desc
     *            sort desc or asc
     *
     * @return PageDto
     *
     */
    @Override
    public PageDto findComputerByPage(int numPage, int rowByPage, String filter,
            String sort, boolean desc) {

        List<Computer> computers = new ArrayList<Computer>();
        long nbComputer = 0;
        long pageMax = 1;
        LOGGER.info("Service : search computer by page");

        if (filter == null || filter.equals("")) {

            nbComputer = computerDao.countComputer();
            pageMax = pageMax(rowByPage, nbComputer);
            numPage = verifNumPage(numPage, pageMax);
            computers = computerDao.findComputerByPage(numPage, rowByPage,
                    sort, desc);

        } else {

            nbComputer = computerDao.countComputerByName(filter);
            pageMax = pageMax(rowByPage, nbComputer);
            numPage = verifNumPage(numPage, pageMax);
            computers = computerDao.findComputerByName(numPage, rowByPage,
                    filter, sort, desc);

        }

        return PageDtoMapper.recoveryPage(computers, nbComputer,
                numPage, rowByPage, pageMax, filter, sort, desc);

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
    
    /**
     * Page max.
     *
     * @param rowByPage
     *            the row by page
     * @param nbComputer
     *            the nb computer
     * @return the long
     */
    private long pageMax(int rowByPage, long nbComputer) {

        long pageMax = 1;

        if (rowByPage == 0) {

            LOGGER.error("row by page equals 0, Impossible");
            pageMax = 1;

        } else {

            if (nbComputer % rowByPage == 0) {

                pageMax = nbComputer / rowByPage;

            } else {

                pageMax = nbComputer / rowByPage + 1;

            }

        }

        return pageMax;

    }

    /**
     * Verif num page.
     *
     * @param numPage
     *            the num page
     * @param pageMax
     *            the page max
     * @return the int
     */
    private int verifNumPage(int numPage, long pageMax) {

        if (numPage >= pageMax) {

            return (int) pageMax;

        } else if (numPage == 0) {

            return 1;

        } else {

            return numPage;

        }

    }

}
