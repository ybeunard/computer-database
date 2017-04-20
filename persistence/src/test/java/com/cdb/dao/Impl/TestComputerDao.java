package com.cdb.dao.Impl;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
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

import com.cdb.dao.Impl.Exception.PageException;
import com.cdb.model.entities.Company;
import com.cdb.model.entities.Computer;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class TestComputerDao.
 */
public class TestComputerDao extends TestCase {

    /** The context. */
    private ApplicationContext context;

    /** The dao. */
    private ComputerDao dao;

    /** The prop. */
    private Properties prop = new Properties();

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {

        InputStream stream = ComputerDao.class.getClassLoader()
                .getResourceAsStream("connexionDBUnit.properties");
        prop.load(stream);
        context = new ClassPathXmlApplicationContext("dispatcher-data.xml");
        dao = (ComputerDao) context.getBean("computerDao");
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
     * Test find computer by page.
     */
    @Test
    public void testFindComputerByPage() {
        
        List<Computer> computers = dao.findComputerByPage(1, 3, "", false);
        assertFalse(computers.isEmpty());
        
        for (Computer computer : computers) {

            assertFalse(computer.getName() == null);
            assertFalse(computer.getId() <= 0);

            if (computer.getName().equals("Bob")) {

                assertFalse(computer.getDiscontinued() == null);

            }

            if (computer.getName().equals("Alice")) {

                assertFalse(computer.getIntroduced() == null);

            }

        }
        
    }
    
    /**
     * Test find computer by page num page zero.
     */
    @Test
    public void testFindComputerByPageNumPageZero() {
        
        try {
            
            dao.findComputerByPage(0, 3, "", false);
        
        } catch (PageException e) {
            
            assertTrue(e.getMessage() != null);
            
        }
        
    }
    
    /**
     * Test find computer by page row by page zero.
     */
    @Test
    public void testFindComputerByPageRowByPageZero() {
        
        try {
        
            dao.findComputerByPage(1, 0, "", false);
            
        } catch (PageException e) {
            
            assertTrue(e.getMessage() != null);
            
        }
        
    }
    
    /**
     * Test find computer by page sort name desc.
     */
    @Test
    public void testFindComputerByPageSortNameDesc() {
        
        List<Computer> computers = dao.findComputerByPage(1, 3, "", true);
        assertFalse(computers.isEmpty());
        Computer computerCharlie = new Computer();
        computerCharlie.setId(57);
        computerCharlie.setName("Charlie");
        assertTrue(computers.contains(computerCharlie));
        
        for (Computer computer : computers) {

            assertFalse(computer.getName() == null);
            assertFalse(computer.getId() <= 0);

            if (computer.getName().equals("Bob")) {

                assertFalse(computer.getDiscontinued() == null);

            }
            
        }
        
    }
    
    /**
     * Test find computer by page sort company asc.
     */
    @Test
    public void testFindComputerByPageSortCompanyAsc() {
        
        List<Computer> computers = dao.findComputerByPage(1, 4, "company_name", false);
        assertFalse(computers.isEmpty());
        Computer computerCharlie = new Computer();
        computerCharlie.setId(57);
        computerCharlie.setName("Charlie");
        assertTrue(computers.contains(computerCharlie));
        
        for (Computer computer : computers) {

            assertFalse(computer.getName() == null);
            assertFalse(computer.getId() <= 0);
            

            if (computer.getName().equals("Bob")) {

                assertFalse(computer.getDiscontinued() == null);

            }
            
        }
        
    }
    
    /**
     * Test find computer by page sort company desc.
     */
    @Test
    public void testFindComputerByPageSortCompanyDesc() {
        
        List<Computer> computers = dao.findComputerByPage(1, 3, "company_name", true);
        assertFalse(computers.isEmpty());
        Computer computerAlice = new Computer();
        computerAlice.setId(20);
        computerAlice.setName("Alice");
        computerAlice.setIntroduced(LocalDate.parse("2006-01-10"));
        Company companyAlice = new Company();
        companyAlice.setId(42);
        companyAlice.setName("Charlie");
        computerAlice.setCompany(companyAlice);
        assertTrue(computers.contains(computerAlice));
        
        for (Computer computer : computers) {

            assertFalse(computer.getName() == null);
            assertFalse(computer.getId() <= 0);
            

            if (computer.getName().equals("Bob")) {

                assertFalse(computer.getDiscontinued() == null);

            }
            
        }
        
    }
    
    /** The Constant logger. */
    public final Logger LOGGER = LoggerFactory.getLogger(ComputerDao.class);
    
    /**
     * Test find computer by page sort introduced asc.
     */
    @Test
    public void testFindComputerByPageSortIntroducedAsc() {
        
        List<Computer> computers = dao.findComputerByPage(1, 4, "introduced", false);
        assertFalse(computers.isEmpty());
        
        for (Computer computer : computers) {

            assertFalse(computer.getName() == null);
            assertFalse(computer.getId() <= 0);
            assertTrue(computer.getIntroduced() == null);
            
        }
        
    }
    
    /**
     * Test find computer by page sort introduced desc.
     */
    @Test
    public void testFindComputerByPageSortIntroducedDesc() {
        
        List<Computer> computers = dao.findComputerByPage(1, 3, "introduced", true);
        assertFalse(computers.isEmpty());
        Computer computerAlice = new Computer();
        computerAlice.setId(20);
        computerAlice.setName("Alice");
        computerAlice.setIntroduced(LocalDate.parse("2006-01-10"));
        Company companyAlice = new Company();
        companyAlice.setId(42);
        companyAlice.setName("Charlie");
        computerAlice.setCompany(companyAlice);
        assertTrue(computers.contains(computerAlice));
        
        for (Computer computer : computers) {

            assertFalse(computer.getName() == null);
            assertFalse(computer.getId() <= 0);
            

            if (computer.getName().equals("Alice")) {

                assertTrue(computer.getDiscontinued() == null);

            }
            
        }
        
    }

}
