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

public class TestComputerDao extends TestCase {

    private ApplicationContext context;

    private ComputerDao dao;

    private Properties prop = new Properties();

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

    @Override
    protected void tearDown() throws Exception {

        dao = null;

    }

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

    private IDataSet readDataSet2() throws Exception {

        return new FlatXmlDataSetBuilder().build(
                new File("src/test/resources/dataTestComputerDao.xml"));

    }

    private IDataSet readDataSet1() throws Exception {

        return new FlatXmlDataSetBuilder().build(
                new File("src/test/resources/dataTestCompanyDao.xml"));

    }
    
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
    
    @Test
    public void testFindComputerByPageNumPageZero() {
        
        try {
            
            dao.findComputerByPage(0, 3, "", false);
        
        } catch (PageException e) {
            
            assertTrue(e.getMessage() != null);
            
        }
        
    }
    
    @Test
    public void testFindComputerByPageRowByPageZero() {
        
        try {
        
            dao.findComputerByPage(1, 0, "", false);
            
        } catch (PageException e) {
            
            assertTrue(e.getMessage() != null);
            
        }
        
    }
    
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
