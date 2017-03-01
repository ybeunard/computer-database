package com.cdb.dao.Impl;

import java.util.List;
import java.util.Optional;

import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

import junit.framework.TestCase;

public class TestOrdinateurDao extends TestCase {

    OrdinateurDao dao;

    protected void setUp() throws Exception {

        this.dao = OrdinateurDao.getInstanceOrdinateurDao();

    }

    protected void tearDown() throws Exception {

        this.dao = null;

    }

    public void testOrdinateurDao()
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<List<Optional<Ordinateur>>> ordinateurs = this.dao
                .findOrdinateur();
        assertTrue(ordinateurs.isPresent());

        for (Optional<Ordinateur> o : ordinateurs.get()) {

            assertTrue(o.isPresent());
            assertNotNull(o.get().getId());
            assertNotNull(o.get().getName());

        }

    }

}
