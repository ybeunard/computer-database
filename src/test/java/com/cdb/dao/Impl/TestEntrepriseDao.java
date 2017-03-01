package com.cdb.dao.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import com.cdb.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import junit.framework.TestCase;

public class TestEntrepriseDao extends TestCase {

    private EntrepriseDao dao;
    private String URL_base;
    private static Properties prop = new Properties();

    protected void setUp() throws Exception {

        FileInputStream stream = new FileInputStream(
                "/home/excilys/eclipse_workspace/computerDatabase/src/main/resources/connexion.properties");
        prop.load(stream);
        URL_base = prop.getProperty("URL");
        prop.setProperty("URL", URL_base + "-test");
        FileOutputStream streamOut = new FileOutputStream(
                "/home/excilys/eclipse_workspace/computerDatabase/src/main/resources/connexion.properties");
        prop.store(streamOut, null);
        IDataSet dataSet = readDataSet();
        cleanlyInsertDataset(dataSet);
        dao = EntrepriseDao.getInstanceEntrepriseDao();

    }

    protected void tearDown() throws IOException {

        FileInputStream stream = new FileInputStream(
                "/home/excilys/eclipse_workspace/computerDatabase/src/main/resources/connexion.properties");
        FileOutputStream streamOut = new FileOutputStream(
                "/home/excilys/eclipse_workspace/computerDatabase/src/main/resources/connexion.properties");
        prop.load(stream);
        prop.setProperty("URL", URL_base);
        prop.store(streamOut, null);
        dao = null;

    }

    private IDataSet readDataSet() throws Exception {

        return new FlatXmlDataSetBuilder().build(new File(
                "/home/excilys/eclipse_workspace/computerDatabase/src/test/resources/dataTestEntrepriseDao.xml"));

    }

    private void cleanlyInsertDataset(IDataSet dataSet) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(
                com.mysql.jdbc.Driver.class.getName(), prop.getProperty("URL"),
                prop.getProperty("LOGIN"), prop.getProperty("PASSWORD"));
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Test
    public void testFindEntreprise() throws ConnexionDatabaseException,
            RequeteQueryException, InstantiationException,
            IllegalAccessException, ClassNotFoundException, SQLException {

        Optional<List<Optional<Entreprise>>> e = dao.findEntreprise();

        assertTrue(e.isPresent());
        assertFalse(e.get().isEmpty());

        for (Optional<Entreprise> en : e.get()) {

            assertTrue(en.isPresent());
            assertTrue(en.get().getName() != null);
            assertTrue(en.get().getId() > 0);

        }

    }

    @Test
    public void testFindEntrepriseByPageZero()
            throws ConnexionDatabaseException, RequeteQueryException {

        int numPage = 0;
        int ligneParPage = 0;
        Optional<List<Optional<Entreprise>>> e = dao
                .findEntrepriseByPage(numPage, ligneParPage);

        assertFalse(e.isPresent());

    }

    @Test
    public void testFindEntrepriseByPageNegatif()
            throws ConnexionDatabaseException, RequeteQueryException {

        int numPage = -1;
        int ligneParPage = -1;
        Optional<List<Optional<Entreprise>>> e = dao
                .findEntrepriseByPage(numPage, ligneParPage);

        assertFalse(e.isPresent());

    }

    @Test
    public void testFindEntrepriseByPageNegatifNumPage()
            throws ConnexionDatabaseException, RequeteQueryException {

        int numPage = -1;
        int ligneParPage = 1;
        Optional<List<Optional<Entreprise>>> e = dao
                .findEntrepriseByPage(numPage, ligneParPage);

        assertFalse(e.isPresent());

    }

    @Test
    public void testFindEntrepriseByPageZeroNumPage()
            throws ConnexionDatabaseException, RequeteQueryException {

        int numPage = 0;
        int ligneParPage = 1;
        Optional<List<Optional<Entreprise>>> e = dao
                .findEntrepriseByPage(numPage, ligneParPage);

        assertFalse(e.isPresent());

    }

    @Test
    public void testFindEntrepriseByPageCorrect()
            throws ConnexionDatabaseException, RequeteQueryException {

        int numPage = 1;
        int ligneParPage = 1;
        Optional<List<Optional<Entreprise>>> e = dao
                .findEntrepriseByPage(numPage, ligneParPage);

        assertTrue(e.isPresent());
        assertFalse(e.get().isEmpty());

        for (Optional<Entreprise> en : e.get()) {

            assertTrue(en.isPresent());
            assertTrue(en.get().getName() != null);
            assertTrue(en.get().getId() > 0);

        }

    }

    @Test
    public void testFindEntrepriseByPageOverLigneParPage()
            throws ConnexionDatabaseException, RequeteQueryException {

        int numPage = 1;
        int ligneParPage = 10000;
        Optional<List<Optional<Entreprise>>> e = dao
                .findEntrepriseByPage(numPage, ligneParPage);

        assertTrue(e.isPresent());
        assertFalse(e.get().isEmpty());

        for (Optional<Entreprise> en : e.get()) {

            assertTrue(en.isPresent());
            assertTrue(en.get().getName() != null);
            assertTrue(en.get().getId() > 0);

        }

    }

    @Test
    public void testFindEntrepriseByPageOverNumPage()
            throws ConnexionDatabaseException, RequeteQueryException {

        int numPage = 12000;
        int ligneParPage = 1;
        Optional<List<Optional<Entreprise>>> e = dao
                .findEntrepriseByPage(numPage, ligneParPage);

        assertTrue(e.isPresent());
        assertTrue(e.get().isEmpty());

    }

    @Test
    public void testFindEntrepriseByPageOver()
            throws ConnexionDatabaseException, RequeteQueryException {

        int numPage = 12000;
        int ligneParPage = 10000;
        Optional<List<Optional<Entreprise>>> e = dao
                .findEntrepriseByPage(numPage, ligneParPage);

        assertTrue(e.isPresent());
        assertTrue(e.get().isEmpty());

    }

    @Test
    public void testFindEntrepriseByIDZero()
            throws ConnexionDatabaseException, RequeteQueryException {

        long id = 0;
        Optional<Entreprise> e = dao.findEntrepriseByID(id);

        assertFalse(e.isPresent());

    }

    @Test
    public void testFindEntrepriseByIDNegatif()
            throws ConnexionDatabaseException, RequeteQueryException {

        long id = -20;
        Optional<Entreprise> e = dao.findEntrepriseByID(id);

        assertFalse(e.isPresent());

    }

    @Test
    public void testFindEntrepriseByIDCorrect()
            throws ConnexionDatabaseException, RequeteQueryException {

        long id = 18;
        Optional<Entreprise> e = dao.findEntrepriseByID(id);

        assertTrue(e.isPresent());
        assertEquals(e.get().getName(), "Bob");
        assertEquals(e.get().getId(), id);

    }

    @Test
    public void testFindEntrepriseByIDErrone()
            throws ConnexionDatabaseException, RequeteQueryException {

        long id = 100;
        Optional<Entreprise> e = dao.findEntrepriseByID(id);

        assertFalse(e.isPresent());

    }

    @Test
    public void testFindEntrepriseByNameNull()
            throws ConnexionDatabaseException, RequeteQueryException {

        String name = null;
        Optional<Entreprise> e = dao.findEntrepriseByName(name);

        assertFalse(e.isPresent());

    }

    @Test
    public void testFindEntrepriseByNameVide()
            throws ConnexionDatabaseException, RequeteQueryException {

        String name = "";
        Optional<Entreprise> e = dao.findEntrepriseByName(name);

        assertFalse(e.isPresent());

    }

    @Test
    public void testFindEntrepriseByNameErrone()
            throws ConnexionDatabaseException, RequeteQueryException {

        String name = "zfqzfqzfqs";
        Optional<Entreprise> e = dao.findEntrepriseByName(name);

        assertFalse(e.isPresent());

    }

    @Test
    public void testFindEntrepriseByNameMultipleMatching()
            throws ConnexionDatabaseException, RequeteQueryException {

        String name = "Bob";
        Optional<Entreprise> e = dao.findEntrepriseByName(name);

        assertTrue(e.isPresent());
        assertEquals(e.get().getName(), "Bob");
        assertEquals(e.get().getId(), 18);

    }

    @Test
    public void testFindEntrepriseByNameMatching()
            throws ConnexionDatabaseException, RequeteQueryException {

        String name = "Alice";
        Optional<Entreprise> e = dao.findEntrepriseByName(name);

        assertTrue(e.isPresent());
        assertEquals(e.get().getName(), "Alice");
        assertEquals(e.get().getId(), 23);

    }

}
