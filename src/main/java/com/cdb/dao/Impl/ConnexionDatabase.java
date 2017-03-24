package com.cdb.dao.Impl;
import com.cdb.dao.InterfaceConnexionDatabase;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * The Enum ConnexionDatabase.
 *
 * @author excilys
 */
public class ConnexionDatabase implements InterfaceConnexionDatabase {

    /** The Constant LOGGER. */
    public final Logger LOGGER = LoggerFactory
            .getLogger(ConnexionDatabase.class);
    
    /** The jdbc template. */
    private JdbcTemplate jdbcTemplate;

    /**
     * Sets the data source.
     *
     * @param dataSource the new data source
     */
    @Autowired
    public void setDataSource(DataSource dataSource) {
        
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        LOGGER.info("jdbcTemplate instancié");
        
    }

    /**
     * Instantiates a new connexion database.
     */
    ConnexionDatabase() {

        LOGGER.info("ConnexionDatabase instancié");

    }
    
    /**
     * Connect database.
     *
     * @return Connection
     */
    public JdbcTemplate getJdbcTemplate() {

        LOGGER.info("JDBC: recuperation du template JDBC");
        return jdbcTemplate;

    }

}
