package com.cdb.mappers.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;

import com.cdb.entities.Entreprise;
import com.cdb.exception.RequeteQueryException;

import junit.framework.TestCase;

public class TestEntrepriseMapper extends TestCase {
    
    private EntrepriseMapper mapper;
    private ResultSet res;

    protected void setUp() {
        
        mapper = EntrepriseMapper.getInstanceEntrepriseMapper();
        res = Mockito.mock(ResultSet.class);
        
    }
    
    protected void tearDown() {
        
        mapper = null;
        
    }
    
    @Test
    public void testRecuperationResultatRequeteVide() throws SQLException, RequeteQueryException {
        
        Mockito.when(res.next()).thenReturn(false);
        Optional<Entreprise> e = mapper.recupertationResultatRequete(res);
        
        assertFalse(e.isPresent());
        
    }
    
    @Test
    public void testRecuperationResultatRequeteNull() throws SQLException, RequeteQueryException {
        
        Mockito.when(res.next()).thenReturn(true);
        Mockito.when(res.getLong("id")).thenReturn(0l);
        Mockito.when(res.getString("name")).thenReturn(null);
        Optional<Entreprise> e = mapper.recupertationResultatRequete(res);
        
        assertTrue(e.isPresent());
        assertEquals(e.get().getName(), "");
        assertEquals(e.get().getId(), 0);
        
    }
    
    @Test
    public void testRecuperationResultatRequeteNonVide() throws SQLException, RequeteQueryException {
        
        Mockito.when(res.next()).thenReturn(true);
        Mockito.when(res.getLong("id")).thenReturn(1l);
        Mockito.when(res.getString("name")).thenReturn("");
        Optional<Entreprise> e = mapper.recupertationResultatRequete(res);
        
        assertTrue(e.isPresent());
        assertEquals(e.get().getName(), "");
        assertEquals(e.get().getId(), 1l);
        
    }
    
    @Test
    public void testRecuperationListResultatRequeteVide() throws SQLException, RequeteQueryException {
        
        Mockito.when(res.next()).thenReturn(false);
        Optional<List<Optional<Entreprise>>> e = mapper.recuperationListResultatRequete(res);
        
        assertTrue(e.isPresent());
        assertTrue(e.get().isEmpty());
        
    }
    
    @Test
    public void testRecuperationListResultatRequeteNonVide() throws SQLException, RequeteQueryException {
        
        Mockito.when(res.next()).thenReturn(true, true, true, false);
        Mockito.when(res.getLong("id")).thenReturn(1l);
        Mockito.when(res.getString("name")).thenReturn("");
        Optional<List<Optional<Entreprise>>> e = mapper.recuperationListResultatRequete(res);
        
        assertTrue(e.isPresent());
        assertFalse(e.get().isEmpty());
        
        for(Optional<Entreprise> en : e.get()) {
            
            assertTrue(en.isPresent());
            assertEquals(en.get().getName(), "");
            assertEquals(en.get().getId(), 1l);
            
        }
        
    }
    
}
