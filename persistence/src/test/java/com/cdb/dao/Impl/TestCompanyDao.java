package com.cdb.dao.Impl;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cdb.dao.Impl.Exception.EntityNotFoundException;
import com.cdb.dao.Impl.Exception.IdException;
import com.cdb.dao.Impl.Exception.PageException;
import com.cdb.model.entities.Company;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class TestCompanyDao.
 */
public class TestCompanyDao extends TestCase {

    /** The context. */
    private ApplicationContext context;

    /** The dao. */
    private CompanyDao dao;

    /** The prop. */
    private Properties prop = new Properties();

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {

        InputStream stream = CompanyDao.class.getClassLoader()
                .getResourceAsStream("connexionDBUnit.properties");
        prop.load(stream);
        context = new ClassPathXmlApplicationContext("dispatcher-data.xml");
        dao = (CompanyDao) context.getBean("companyDao");
        ;
        IDataSet dataSet1 = readDataSet1();
        IDataSet dataSet2 = readDataSet2();
        cleanlyInsertDataset(dataSet1);
        cleanlyInsertDataset(dataSet2);

    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {

        dao = null;

    }

    /**
     * Cleanly insert dataset.
     *
     * @param dataSet the data set
     * @throws Exception the exception
     */
    private void cleanlyInsertDataset(IDataSet dataSet) throws Exception {

        IDatabaseTester databaseTester = new JdbcDatabaseTester(
                prop.getProperty("dataSourceClassName"),
                prop.getProperty("dataSource.url"),
                prop.getProperty("dataSource.user"),
                prop.getProperty("dataSource.password"));
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

    }

    /**
     * Read data set 2.
     *
     * @return the i data set
     * @throws Exception the exception
     */
    private IDataSet readDataSet2() throws Exception {

        return new FlatXmlDataSetBuilder().build(
                new File("src/test/resources/dataTestComputerDao.xml"));

    }

    /**
     * Read data set 1.
     *
     * @return the i data set
     * @throws Exception the exception
     */
    private IDataSet readDataSet1() throws Exception {

        return new FlatXmlDataSetBuilder().build(
                new File("src/test/resources/dataTestCompanyDao.xml"));

    }

    /**
     * Test find companies.
     */
    @Test
    public void testFindCompanies() {

        List<Company> companies = dao.findCompanies();
        assertFalse(companies.isEmpty());

        for (Company company : companies) {

            assertFalse(company == null);
            assertFalse(company.getName() == null);
            assertFalse(company.getName().equals(""));
            assertFalse(company.getId() <= 0);

        }

    }
    
    /**
     * Test find company by page.
     */
    @Test
    public void testFindCompanyByPage() {
        
        List<Company> companies = dao.findCompanyByPage(1, 2);
        assertFalse(companies.isEmpty());
        
        for (Company company : companies) {

            assertFalse(company == null);
            assertFalse(company.getName() == null);
            assertFalse(company.getName().equals(""));
            assertFalse(company.getId() <= 0);

        }
        
    }
    
    /**
     * Test find company by page num page zero.
     */
    @Test
    public void testFindCompanyByPageNumPageZero() {
        
        List<Company> companies = dao.findCompanyByPage(0, 2);
        assertTrue(companies.isEmpty());
        
    }
    
    /**
     * Test find company by page row by page zero.
     */
    @Test
    public void testFindCompanyByPageRowByPageZero() {
        
        try {
            
            dao.findCompanyByPage(1, 0);
            
        } catch (PageException e) {
            
            assertTrue(e.getMessage() != null);
            
        }
        
    }
    
    /**
     * Test find company by page num page over.
     */
    @Test
    public void testFindCompanyByPageNumPageOver() {
        
        try {
        
            dao.findCompanyByPage(10, 2);
            
        } catch (EntityNotFoundException e) {
            
            assertTrue(e.getMessage() != null);
            
        }
        
    }

    /**
     * Test find company by ID.
     */
    @Test
    public void testFindCompanyByID() {

        Company company = dao.findCompanyByID(22);
        assertTrue(company != null);
        assertEquals(company.getName(), "Bob");
        assertEquals(company.getId(), 22);

    }
    
    /** The logger. */
    public final Logger LOGGER = LoggerFactory.getLogger(CompanyDao.class);

    
    /**
     * Test find company by ID zero.
     */
    @Test
    public void testFindCompanyByIDZero() {

        try {
            
            dao.findCompanyByID(0);
            
        } catch (IdException e) {
            
            assertTrue(e.getMessage() != null);
            
        }

    }

    /**
     * Test find company by ID incorrecte.
     */
    @Test
    public void testFindCompanyByIDIncorrecte() {

        try {
        
            dao.findCompanyByID(100);
            
        } catch (EntityNotFoundException e) {
            
            assertTrue(e.getMessage() != null);
            
        }

    }
    
    /**
     * Test delete company.
     */
    @Test
    public void testDeleteCompany(){
        
        dao.deleteCompany(18);
        
        try {
            
            dao.findCompanyByID(18);
        
        } catch (EntityNotFoundException e) {
            
            assertTrue(e.getMessage() != null);
            
        }
        
    }
    
}
