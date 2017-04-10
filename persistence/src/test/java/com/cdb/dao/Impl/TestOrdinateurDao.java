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

import com.cdb.model.entities.Computer;

import junit.framework.TestCase;

public class TestOrdinateurDao extends TestCase {

    private ApplicationContext context;

    private OrdinateurDao dao;

    private Properties prop = new Properties();

    @Override
    protected void setUp() throws Exception {

        InputStream stream = OrdinateurDao.class.getClassLoader()
                .getResourceAsStream("connexionDBUnit.properties");
        prop.load(stream);
        context = new ClassPathXmlApplicationContext("dispatcher-data.xml");
        dao = (OrdinateurDao) context.getBean("ordinateurDao");
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
                new File("src/test/resources/dataTestOrdinateurDao.xml"));

    }

    private IDataSet readDataSet1() throws Exception {

        return new FlatXmlDataSetBuilder().build(
                new File("src/test/resources/dataTestEntrepriseDao.xml"));

    }

    @Test
    public void testFindOrdinateur() {

        List<Computer> ordinateurs = dao.findOrdinateur();
        assertFalse(ordinateurs.isEmpty());

        for (Computer ordinateur : ordinateurs) {

            assertFalse(ordinateur.getName() == null);
            assertFalse(ordinateur.getId() <= 0);

            if (ordinateur.getName().equals("Bob")) {

                assertFalse(ordinateur.getDiscontinued() == null);

            }

            if (ordinateur.getName().equals("Alice")) {

                assertFalse(ordinateur.getIntroduced() == null);

            }

        }

    }

}
