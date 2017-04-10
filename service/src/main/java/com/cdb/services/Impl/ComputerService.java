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
    
    @Override
    public PageDto findComputerByPage(int numPage, int rowByPage, String filter,
            String sort, boolean desc) {
        
        List<Computer> ordinateurs = new ArrayList<Computer>();
        long nbComputer = 0;
        long pageMax = 1;
        LOGGER.info("Service : search computer by page");

        if (filter == null || filter.equals("")) {

            nbComputer = computerDao.countComputer();
            LOGGER.debug("recuperation du nombre maximum d'ordinateur "
                    + nbComputer);
            pageMax = pageMax(rowByPage, nbComputer);
            LOGGER.debug("recuperation du nombre maximum de page " + pageMax);
            numPage = verifNumPage(numPage, pageMax);
            LOGGER.debug(
                    "Verification du numero de page effectuer " + numPage);
            ordinateurs = computerDao.findComputerByPage(numPage,
                    rowByPage, sort, desc);
            LOGGER.debug("Recuperation de la liste d'ordinateur "
                    + ordinateurs.size());

        } else {

            nbComputer = computerDao.countComputerByName(filter);
            LOGGER.debug("recuperation du nombre maximum d'ordinateur "
                    + nbComputer);
            pageMax = pageMax(rowByPage, nbComputer);
            LOGGER.debug("recuperation du nombre maximum de page " + pageMax);
            numPage = verifNumPage(numPage, pageMax);
            LOGGER.debug(
                    "Verification du numero de page effectuer " + numPage);
            ordinateurs = computerDao.findComputerByName(numPage,
                    rowByPage, filter, sort, desc);
            LOGGER.debug("Recuperation de la liste d'ordinateur "
                    + ordinateurs.size());

        }

        PageDto page = PageDtoMapper.recuperationPage(ordinateurs, nbComputer,
                numPage, rowByPage, pageMax, filter, sort, desc);
        return page;
        
    }
    
    @Override
    public ComputerDto findComputerById(long id) {
        
        LOGGER.info("Service: search computer by id");
        return ComputerDtoMapper
                    .recoveryComputerDto(computerDao
                            .findComputerById(id));
        
    }
    
    @Transactional
    @Override
    public void createComputer(Computer computer) {
        
        LOGGER.info("Service : create computer");
        computerDao.createComputer(computer);

    }

    @Transactional
    @Override
    public void updateComputer(Computer computer) {
        
        LOGGER.info("Service: Update computer");
        computerDao.updateComputer(computer);

    }

    @Transactional
    @Override
    public void deleteComputer(List<Long> id) {
        
        LOGGER.info("Service: delete computer");

        for (long identifiant : id) {

            computerDao.deleteComputer(identifiant);

        }

    }

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
     * @param numeroPage
     *            the numero page
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
