package com.cdb.dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * The Interface InterfaceConnexionDatabase.
 */
public interface InterfaceConnexionDatabase {

    public JdbcTemplate getJdbcTemplate();

}
