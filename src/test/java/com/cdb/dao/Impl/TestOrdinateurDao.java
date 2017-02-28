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

        Optional<Ordinateur> ordinateur = this.dao.findOrdinateurByID(4);
        assertTrue(ordinateur.isPresent());
        assertNotNull(ordinateur.get().getId());
        assertNotNull(ordinateur.get().getName());

        Optional<Ordinateur> ordinateur2 = this.dao.findOrdinateurByID(-50);
        assertFalse(ordinateur2.isPresent());

        Optional<Ordinateur> ordinateur3 = this.dao.findOrdinateurByID(5000);
        assertFalse(ordinateur3.isPresent());

        this.dao.suppressionOrdinateur(20);
        Optional<Ordinateur> ordinateur4 = this.dao.findOrdinateurByID(20);
        assertFalse(ordinateur4.isPresent());

        this.dao.suppressionOrdinateur(-50);
        Optional<Ordinateur> ordinateur5 = this.dao.findOrdinateurByID(-50);
        assertFalse(ordinateur5.isPresent());

        this.dao.suppressionOrdinateur(5000);
        Optional<Ordinateur> ordinateur6 = this.dao.findOrdinateurByID(5000);
        assertFalse(ordinateur6.isPresent());

        Optional<Ordinateur> ordinateur7 = this.dao.findOrdinateurByID(60);
        assertTrue(ordinateur7.isPresent());
        assertNotNull(ordinateur7.get().getId());
        assertNotNull(ordinateur7.get().getName());

        Optional<Ordinateur> ordinateur8 = Optional
                .ofNullable(ordinateur7.get());
        ordinateur8.get().setName("ASUS REPUBLIC OF GAMER");
        ordinateur8.get().setFabricant(ordinateur.get().getFabricant().get());
        this.dao.updateOrdinateur(ordinateur8.get());

        Optional<Ordinateur> ordinateur9 = this.dao.findOrdinateurByID(60);
        assertTrue(ordinateur9.isPresent());
        assertNotNull(ordinateur9.get().getId());
        assertNotNull(ordinateur9.get().getName());
        assertEquals(ordinateur8, ordinateur9);

    }

}
