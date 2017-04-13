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

/**
 * The Class CompanyService.
 */
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

    /**
     * Recovery list of company.
     *
     * @return the list of companyDto
     */
    @Override
    public List<CompanyDto> findCompanies() {

        LOGGER.info("Service: search all companies");
        List<Company> companies = new ArrayList<Company>();
        companies = companyDao.findCompanies();
        return CompanyDtoMapper.recoveryListCompany(companies);

    }

    @Override
    public List<CompanyDto> findCompanyByPage(int numPage, int rowByPage,
            String filter) {

        List<Company> companies = new ArrayList<Company>();
        long nbComputer = 0;
        long pageMax = 1;
        LOGGER.info("Service : search company by page");

        if (filter == null || filter.equals("")) {

            nbComputer = companyDao.countCompany();
            pageMax = pageMax(rowByPage, nbComputer);
            numPage = verifNumPage(numPage, pageMax);
            companies = companyDao.findCompanyByPage(numPage, rowByPage);

        } else {

            nbComputer = companyDao.countCompanyByName(filter);
            pageMax = pageMax(rowByPage, nbComputer);
            numPage = verifNumPage(numPage, pageMax);
            companies = companyDao.findCompanyByName(numPage, rowByPage,
                    filter);

        }

        return CompanyDtoMapper.recoveryListCompany(companies);

    }

    /**
     * The company by id.
     *
     * @param id
     *            the id of company
     *
     * @return the company
     */
    @Override
    public CompanyDto findCompanyById(long id) {

        LOGGER.info("Service: search company by id");
        return CompanyDtoMapper.recoveryCompany(companyDao.findCompanyByID(id));

    }

    @Override
    public void deleteCompany(List<Long> id) {
        // TODO Auto-generated method stub

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
