package com.cdb.dao.Impl;

import java.util.List;
import java.util.Optional;

import com.cdb.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

import junit.framework.TestCase;

public class TestEntrepriseDao extends TestCase {

    EntrepriseDao dao;

    protected void setUp() throws Exception {

        this.dao = EntrepriseDao.getInstanceEntrepriseDao();

    }

    protected void tearDown() throws Exception {

        this.dao = null;

    }

    public void testEntrepriseDao()
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<List<Optional<Entreprise>>> entreprises = this.dao
                .findEntreprise();
        assertTrue(entreprises.isPresent());
        for (Optional<Entreprise> e : entreprises.get()) {

            assertTrue(e.isPresent());
            assertNotNull(e.get().getId());
            assertNotNull(e.get().getName());

        }

        Optional<Entreprise> entreprise = this.dao.findEntrepriseByID(30);
        assertTrue(entreprise.isPresent());
        assertNotNull(entreprise.get().getId());
        assertNotNull(entreprise.get().getName());
        assertTrue(entreprises.get().contains(entreprise));

        Optional<Entreprise> entreprise2 = this.dao.findEntrepriseByID(-50);
        assertFalse(entreprise2.isPresent());

        Optional<Entreprise> entreprise3 = this.dao.findEntrepriseByID(5000);
        assertFalse(entreprise3.isPresent());

        Optional<List<Optional<Entreprise>>> entreprises2 = this.dao
                .findEntrepriseByPage(1, 30);
        assertTrue(entreprises2.isPresent());

        for (Optional<Entreprise> e : entreprises.get()) {

            assertTrue(e.isPresent());
            assertNotNull(e.get().getId());
            assertNotNull(e.get().getName());

        }

        Optional<List<Optional<Entreprise>>> entreprises3 = this.dao
                .findEntrepriseByPage(0, 30);
        assertFalse(entreprises3.isPresent());

        Optional<List<Optional<Entreprise>>> entreprises4 = this.dao
                .findEntrepriseByPage(50, 30);
        assertTrue(entreprises4.isPresent());

    }

}
