package com.cdb.dao.Impl;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cdb.model.entities.Company;

public class TestEntrepriseDao extends DBTestCase {

    private ApplicationContext context;

    private EntrepriseDao dao;

    private Properties prop = new Properties();

    @Override
    protected void setUp() throws Exception {

        InputStream stream = EntrepriseDao.class.getClassLoader()
                .getResourceAsStream("connexionDBUnit.properties");
        prop.load(stream);
        context = new ClassPathXmlApplicationContext("dispatcher-data.xml");
        dao = (EntrepriseDao) context.getBean("entrepriseDao");
        ;
        IDataSet dataSet = getDataSet();
        cleanlyInsertDataset(dataSet);

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

    @Override
    protected IDataSet getDataSet() throws Exception {

        return new FlatXmlDataSetBuilder().build(
                new File("src/test/resources/dataTestEntrepriseDao.xml"));

    }

    @Test
    public void testFindEntreprise() {

        List<Company> entreprises = dao.findEntreprise();
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

        Optional<Company> entreprise = dao.findEntrepriseByID(0);
        assertFalse(entreprise.isPresent());

    }

    @Test
    public void testFindEntrepriseByIDNegatif() {

        Optional<Company> entreprise = dao.findEntrepriseByID(-1);
        assertFalse(entreprise.isPresent());

    }

    @Test
    public void testFindEntrepriseByIDCorrect() {

        Optional<Company> entreprise = dao.findEntrepriseByID(22);
        assertTrue(entreprise.isPresent());
        assertEquals(entreprise.get().getName(), "Bob");
        assertEquals(entreprise.get().getId(), 22);

    }

}
