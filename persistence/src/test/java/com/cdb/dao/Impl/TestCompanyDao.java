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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cdb.model.entities.Company;

import junit.framework.TestCase;

public class TestCompanyDao extends TestCase {

    private ApplicationContext context;

    private CompanyDao dao;

    private Properties prop = new Properties();

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
    public void testFindEntreprise() {

        List<Company> entreprises = dao.findCompanies();
        assertFalse(entreprises.isEmpty());

        for (Company entreprise : entreprises) {

            assertFalse(entreprise == null);
            assertFalse(entreprise.getName() == null);
            assertFalse(entreprise.getName().equals(""));
            assertFalse(entreprise.getId() <= 0);

        }

    }

    @Test
    public void testFindEntrepriseByIDZero() {

        Company entreprise = dao.findCompanyByID(0);
        assertTrue(entreprise == null);
       

    }

    @Test
    public void testFindEntrepriseByIDNegatif() {

        Company entreprise = dao.findCompanyByID(-1);
        assertTrue(entreprise == null);

    }

    @Test
    public void testFindEntrepriseByIDCorrect() {

        Company entreprise = dao.findCompanyByID(22);
        assertTrue(entreprise != null);
        assertEquals(entreprise.getName(), "Bob");
        assertEquals(entreprise.getId(), 22);

    }
    
    @Test
    public void testDeleteCompany(){
        
        dao.deleteCompany(18);
        Company company = dao.findCompanyByID(18);
        assertEquals(company, null);
        
    }
    
}
