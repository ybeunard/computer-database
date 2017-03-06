package com.cdb.dao.Impl;

import java.io.File;
import java.io.InputStream;
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
    
    private Properties prop = new Properties();
    
    @Override
    protected void setUp() throws Exception {
        
        InputStream stream = ConnexionDatabase.class.getClassLoader()
                .getResourceAsStream("connexion.properties");
        prop.load(stream);
        dao = EntrepriseDao.INSTANCE_ENTREPRISE_DAO;
        IDataSet dataSet = readDataSet();
        cleanlyInsertDataset(dataSet);
        
    }
    
    @Override
    protected void tearDown() throws Exception {
        
        dao = null;
        
    }
    
    private void cleanlyInsertDataset(IDataSet dataSet) throws Exception {
        
        IDatabaseTester databaseTester = new JdbcDatabaseTester(
            prop.getProperty("nameDriver"), prop.getProperty("URL"), prop.getProperty("LOGIN"), prop.getProperty("PASSWORD"));
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        
    }
    
    private IDataSet readDataSet() throws Exception {
        
        return new FlatXmlDataSetBuilder().build(new File("src/test/resources/dataTestEntrepriseDao.xml"));
        
    }
    
    @Test
    public void testFindEntreprise() throws ConnexionDatabaseException, RequeteQueryException {
        
        List<Entreprise> entreprises = dao.findEntreprise();
        assertFalse(entreprises.isEmpty());
        
        for(Entreprise entreprise : entreprises) {
            
            assertFalse(entreprise == null);
            assertFalse(entreprise.getName() == null);
            assertFalse(entreprise.getName().equals(""));
            assertFalse(entreprise.getId() <= 0);
            
        }
        
    }
    
    @Test
    public void testFindEntrepriseByIDZero() throws ConnexionDatabaseException, RequeteQueryException {
        
        Optional<Entreprise> entreprise = dao.findEntrepriseByID(0);
        assertFalse(entreprise.isPresent());
        
    }
    
    @Test
    public void testFindEntrepriseByIDNegatif() throws ConnexionDatabaseException, RequeteQueryException {
        
        Optional<Entreprise> entreprise = dao.findEntrepriseByID(-1);
        assertFalse(entreprise.isPresent());
        
    }
    
    @Test
    public void testFindEntrepriseByIDCorrect() throws ConnexionDatabaseException, RequeteQueryException {
        
        Optional<Entreprise> entreprise = dao.findEntrepriseByID(22);
        assertTrue(entreprise.isPresent());
        assertEquals(entreprise.get().getName(), "Bob");
        assertEquals(entreprise.get().getId(), 22);
        
    }
    
}
