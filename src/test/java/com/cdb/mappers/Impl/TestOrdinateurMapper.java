package com.cdb.mappers.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.mockito.Mockito;

import com.cdb.entities.Ordinateur;
import com.cdb.exception.RequeteQueryException;

import junit.framework.TestCase;

public class TestOrdinateurMapper extends TestCase {
    
    private OrdinateurMapper mapper;
    private ResultSet res;
    private SimpleDateFormat format;
    
    protected void setUp() {
        
        mapper = OrdinateurMapper.getInstanceOrdinateurMapper();
        res = Mockito.mock(ResultSet.class);
        format = new SimpleDateFormat("yyyy/MM/dd");
        
    }
    
    protected void tearDown() {
        
        mapper = null;
        
    }
    
    @Test
    public void testRecuperationResultatRequeteVide() throws SQLException, RequeteQueryException {
        
        Mockito.when(res.next()).thenReturn(false);
        Optional<Ordinateur> o = mapper.recuperationResultatRequete(res);
        
        assertFalse(o.isPresent());
        
    }
    
    @Test
    public void testRecuperationResultatRequeteNull() throws RequeteQueryException, SQLException {
        
        Mockito.when(res.next()).thenReturn(true);
        Mockito.when(res.getLong("id")).thenReturn(0l);
        Mockito.when(res.getString("name")).thenReturn(null);
        Mockito.when(res.getDate("introduced")).thenReturn(null);
        Mockito.when(res.getDate("discontinued")).thenReturn(null);
        Mockito.when(res.getLong("company_id")).thenReturn(0l);
        Mockito.when(res.getString("company_name")).thenReturn(null);
        Optional<Ordinateur> o = mapper.recuperationResultatRequete(res);
        
        assertTrue(o.isPresent());
        assertEquals(o.get().getId(), 0);
        assertEquals(o.get().getName(), "");
        assertEquals(o.get().getDateIntroduit(), null);
        assertEquals(o.get().getDateInterrompu(), null);
        assertFalse(o.get().getFabricant().isPresent());
        
    }
    
    @Test
    public void testRecuperationResultatRequeteAttributeNull() throws RequeteQueryException, SQLException {
        
        Mockito.when(res.next()).thenReturn(true);
        Mockito.when(res.getLong("id")).thenReturn(1l);
        Mockito.when(res.getString("name")).thenReturn("");
        Mockito.when(res.getDate("introduced")).thenReturn(null);
        Mockito.when(res.getDate("discontinued")).thenReturn(null);
        Mockito.when(res.getLong("company_id")).thenReturn(0l);
        Mockito.when(res.getString("company_name")).thenReturn(null);
        Optional<Ordinateur> o = mapper.recuperationResultatRequete(res);
        
        assertTrue(o.isPresent());
        assertEquals(o.get().getId(), 1l);
        assertEquals(o.get().getName(), "");
        assertEquals(o.get().getDateIntroduit(), null);
        assertEquals(o.get().getDateInterrompu(), null);
        assertFalse(o.get().getFabricant().isPresent());
        
        
    }
    
    @Test
    public void testRecuperationResultatRequeteDateFalse() throws RequeteQueryException, SQLException, ParseException {
        
        Mockito.when(res.next()).thenReturn(true);
        Mockito.when(res.getLong("id")).thenReturn(1l);
        Mockito.when(res.getString("name")).thenReturn("");
        Mockito.when(res.getDate("introduced")).thenReturn(new java.sql.Date(format.parse("0000/00/00").getTime()));
        Mockito.when(res.getDate("discontinued")).thenReturn(new java.sql.Date(format.parse("0000/00/00").getTime()));
        Mockito.when(res.getLong("company_id")).thenReturn(0l);
        Mockito.when(res.getString("company_name")).thenReturn(null);
        Optional<Ordinateur> o = mapper.recuperationResultatRequete(res);
        LocalDate date = new java.sql.Date(format.parse("0000/00/00").getTime()).toLocalDate();
        
        assertTrue(o.isPresent());
        assertEquals(o.get().getId(), 1l);
        assertEquals(o.get().getName(), "");
        assertEquals(o.get().getDateIntroduit(), date);
        assertEquals(o.get().getDateInterrompu(), date);
        assertFalse(o.get().getFabricant().isPresent());
        
    }
    
    @Test
    public void testRecuperationResultatRequeteCompanyFalse() throws RequeteQueryException, SQLException {
        
        Mockito.when(res.next()).thenReturn(true);
        Mockito.when(res.getLong("id")).thenReturn(1l);
        Mockito.when(res.getString("name")).thenReturn("");
        Mockito.when(res.getDate("introduced")).thenReturn(null);
        Mockito.when(res.getDate("discontinued")).thenReturn(null);
        Mockito.when(res.getLong("company_id")).thenReturn(-1l);
        Mockito.when(res.getString("company_name")).thenReturn(null);
        Optional<Ordinateur> o = mapper.recuperationResultatRequete(res);
        
        assertTrue(o.isPresent());
        assertEquals(o.get().getId(), 1l);
        assertEquals(o.get().getName(), "");
        assertEquals(o.get().getDateIntroduit(), null);
        assertEquals(o.get().getDateInterrompu(), null);
        assertFalse(o.get().getFabricant().isPresent());
        
        
    }
    
    @Test
    public void testRecuperationResultatRequeteNonVide() throws RequeteQueryException, SQLException, ParseException {
        
        Mockito.when(res.next()).thenReturn(true);
        Mockito.when(res.getLong("id")).thenReturn(1l);
        Mockito.when(res.getString("name")).thenReturn("");
        Mockito.when(res.getDate("introduced")).thenReturn(new java.sql.Date(format.parse("2012/02/08").getTime()));
        Mockito.when(res.getDate("discontinued")).thenReturn(new java.sql.Date(format.parse("2012/02/08").getTime()));
        Mockito.when(res.getLong("company_id")).thenReturn(3l);
        Mockito.when(res.getString("company_name")).thenReturn("");
        Optional<Ordinateur> o = mapper.recuperationResultatRequete(res);
        LocalDate date = new java.sql.Date(format.parse("2012/02/08").getTime()).toLocalDate();
        
        assertTrue(o.isPresent());
        assertEquals(o.get().getId(), 1l);
        assertEquals(o.get().getName(), "");
        assertEquals(o.get().getDateIntroduit(), date);
        assertEquals(o.get().getDateInterrompu(), date);
        assertTrue(o.get().getFabricant().isPresent());
        assertEquals(o.get().getFabricant().get().getId(), 3);
        assertEquals(o.get().getFabricant().get().getName(), "");
        
        
    }
    
    @Test
    public void testRecuperationListResultatRequeteVide() throws RequeteQueryException, SQLException {
        
        Mockito.when(res.next()).thenReturn(false);
        Optional<List<Optional<Ordinateur>>> o = mapper.recuperationListResultatRequete(res);
        
        assertTrue(o.isPresent());
        assertTrue(o.get().isEmpty());
        
    }
    
    @Test
    public void testRecuperationListResultatRequeteNull() throws RequeteQueryException, SQLException {
        
        Mockito.when(res.next()).thenReturn(true, true, true, false);
        Mockito.when(res.getLong("id")).thenReturn(0l);
        Mockito.when(res.getString("name")).thenReturn(null);
        Mockito.when(res.getDate("introduced")).thenReturn(null);
        Mockito.when(res.getDate("discontinued")).thenReturn(null);
        Mockito.when(res.getLong("company_id")).thenReturn(0l);
        Mockito.when(res.getString("company_name")).thenReturn(null);
        Optional<List<Optional<Ordinateur>>> o = mapper.recuperationListResultatRequete(res);
        
        assertTrue(o.isPresent());
        assertFalse(o.get().isEmpty());
        
        for(Optional<Ordinateur> or : o.get()) {
            
            assertTrue(o.isPresent());
            assertEquals(or.get().getId(), 0);
            assertEquals(or.get().getName(), "");
            assertEquals(or.get().getDateIntroduit(), null);
            assertEquals(or.get().getDateInterrompu(), null);
            assertFalse(or.get().getFabricant().isPresent());
            
        }
        
    }
    
    @Test
    public void testRecuperationListResultatRequeteAttributeNull() throws RequeteQueryException, SQLException {
        
        Mockito.when(res.next()).thenReturn(true, true, true, false);
        Mockito.when(res.getLong("id")).thenReturn(1l);
        Mockito.when(res.getString("name")).thenReturn("");
        Mockito.when(res.getDate("introduced")).thenReturn(null);
        Mockito.when(res.getDate("discontinued")).thenReturn(null);
        Mockito.when(res.getLong("company_id")).thenReturn(0l);
        Mockito.when(res.getString("company_name")).thenReturn(null);
        Optional<List<Optional<Ordinateur>>> o = mapper.recuperationListResultatRequete(res);
        
        assertTrue(o.isPresent());
        assertFalse(o.get().isEmpty());
        
        for(Optional<Ordinateur> or : o.get()) {
            
            assertTrue(o.isPresent());
            assertEquals(or.get().getId(), 1);
            assertEquals(or.get().getName(), "");
            assertEquals(or.get().getDateIntroduit(), null);
            assertEquals(or.get().getDateInterrompu(), null);
            assertFalse(or.get().getFabricant().isPresent());
            
        }
        
    }
    
    @Test
    public void testRecuperationListResultatRequeteDateFalse() throws RequeteQueryException, SQLException, ParseException {
        
        Mockito.when(res.next()).thenReturn(true, true, true, false);
        Mockito.when(res.getLong("id")).thenReturn(1l);
        Mockito.when(res.getString("name")).thenReturn("");
        Mockito.when(res.getDate("introduced")).thenReturn(new java.sql.Date(format.parse("0000/00/00").getTime()));
        Mockito.when(res.getDate("discontinued")).thenReturn(new java.sql.Date(format.parse("0000/00/00").getTime()));
        Mockito.when(res.getLong("company_id")).thenReturn(0l);
        Mockito.when(res.getString("company_name")).thenReturn(null);
        Optional<List<Optional<Ordinateur>>> o = mapper.recuperationListResultatRequete(res);
        LocalDate date = new java.sql.Date(format.parse("0000/00/00").getTime()).toLocalDate();
        
        assertTrue(o.isPresent());
        assertFalse(o.get().isEmpty());
        
        for(Optional<Ordinateur> or : o.get()) {
            
            assertTrue(o.isPresent());
            assertEquals(or.get().getId(), 1);
            assertEquals(or.get().getName(), "");
            assertEquals(or.get().getDateIntroduit(), date);
            assertEquals(or.get().getDateInterrompu(), date);
            assertFalse(or.get().getFabricant().isPresent());
            
        }
        
    }
    
    @Test
    public void testRecuperationListResultatRequeteCompanyFalse() throws RequeteQueryException, SQLException, ParseException {
        
        Mockito.when(res.next()).thenReturn(true, true, true, false);
        Mockito.when(res.getLong("id")).thenReturn(1l);
        Mockito.when(res.getString("name")).thenReturn("");
        Mockito.when(res.getDate("introduced")).thenReturn(null);
        Mockito.when(res.getDate("discontinued")).thenReturn(null);
        Mockito.when(res.getLong("company_id")).thenReturn(-1l);
        Mockito.when(res.getString("company_name")).thenReturn(null);
        Optional<List<Optional<Ordinateur>>> o = mapper.recuperationListResultatRequete(res);
        
        assertTrue(o.isPresent());
        assertFalse(o.get().isEmpty());
        
        for(Optional<Ordinateur> or : o.get()) {
            
            assertTrue(o.isPresent());
            assertEquals(or.get().getId(), 1);
            assertEquals(or.get().getName(), "");
            assertEquals(or.get().getDateIntroduit(), null);
            assertEquals(or.get().getDateInterrompu(), null);
            assertFalse(or.get().getFabricant().isPresent());
            
        }
        
    }
    
    @Test
    public void testRecuperationListResultatRequeteNonVide() throws RequeteQueryException, SQLException, ParseException {
        
        Mockito.when(res.next()).thenReturn(true, true, true, false);
        Mockito.when(res.getLong("id")).thenReturn(1l);
        Mockito.when(res.getString("name")).thenReturn("");
        Mockito.when(res.getDate("introduced")).thenReturn(new java.sql.Date(format.parse("2012/08/10").getTime()));
        Mockito.when(res.getDate("discontinued")).thenReturn(new java.sql.Date(format.parse("2012/08/10").getTime()));
        Mockito.when(res.getLong("company_id")).thenReturn(1l);
        Mockito.when(res.getString("company_name")).thenReturn("");
        Optional<List<Optional<Ordinateur>>> o = mapper.recuperationListResultatRequete(res);
        LocalDate date = new java.sql.Date(format.parse("2012/08/10").getTime()).toLocalDate();
        
        assertTrue(o.isPresent());
        assertFalse(o.get().isEmpty());
        
        for(Optional<Ordinateur> or : o.get()) {
            
            assertTrue(o.isPresent());
            assertEquals(or.get().getId(), 1);
            assertEquals(or.get().getName(), "");
            assertEquals(or.get().getDateIntroduit(), date);
            assertEquals(or.get().getDateInterrompu(), date);
            assertTrue(or.get().getFabricant().isPresent());
            assertEquals(or.get().getFabricant().get().getId(), 1);
            assertEquals(or.get().getFabricant().get().getName(), "");
            
        }
        
    }
    
}
