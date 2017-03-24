package com.cdb.dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * The Interface InterfaceConnexionDatabase.
 */
public interface InterfaceConnexionDatabase {

    /**
     * Gets the jdbc template.
     *
     * @return the jdbc template
     */
    JdbcTemplate getJdbcTemplate();

}
