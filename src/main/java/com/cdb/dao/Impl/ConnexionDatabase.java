package com.cdb.dao.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import com.cdb.dao.InterfaceConnexionDatabase;
import com.cdb.exception.ConnexionDatabaseException;

import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The Enum ConnexionDatabase.
 *
 * @author excilys
 */
public class ConnexionDatabase implements InterfaceConnexionDatabase {

    /** The Constant LOGGER. */
    public final Logger LOGGER = LoggerFactory
            .getLogger(ConnexionDatabase.class);
    
    /** The prop. */
    private final Properties prop = new Properties();
    
    @Autowired
    private HikariDataSource hikariDataSource;
    
    public HikariDataSource getHikariDataSource() {
        
        return hikariDataSource;
        
    }

    private static final ThreadLocal<Connection> MYTHREADLOCAL = new ThreadLocal<Connection>();

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
     * @throws ConnexionDatabaseException
     *             if there is an issue
     */
    public Connection connectDatabase() throws ConnexionDatabaseException {

        /**
         *
         */
        LOGGER.info("Tentative de connexion");
        Connection con = null;

        try {

            con = getMyConnection();

        } catch (SQLException e) {

            throw new ConnexionDatabaseException("Connexion au serveur "
                    + prop.getProperty("dataSource.url") + " impossible", con);

        }

        LOGGER.info("connexion effectuée");
        return con;

    }

    /**
     * Close connexion database.
     *
     * @param con
     *            to close her
     * @throws ConnexionDatabaseException
     *             if there is an issue
     */
    public void closeConnexionDatabase(Connection con)
            throws ConnexionDatabaseException {

        if (con != null) {

            LOGGER.info("tentative de deconnexion");

            try {

                con.close();

            } catch (SQLException e) {

                throw new ConnexionDatabaseException(
                        "Deconnexion du serveur impossible");

            }

            LOGGER.info("deconnexion effectuée");

        }

    }

    /**
     * Gets the my connection.
     *
     * @return the my connection
     * @throws SQLException
     *             the SQL exception
     */
    private Connection getMyConnection() throws SQLException {

        Connection con = MYTHREADLOCAL.get();

        if (con == null || con.isClosed()) {

            MYTHREADLOCAL.set(con = hikariDataSource.getConnection());

        }

        return con;

    }

}
