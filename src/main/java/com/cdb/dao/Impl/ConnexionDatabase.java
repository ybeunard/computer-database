package com.cdb.dao.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import com.cdb.dao.InterfaceConnexionDatabase;
import com.cdb.exception.ConnexionDatabaseException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Enum ConnexionDatabase.
 *
 * @author excilys
 */
public enum ConnexionDatabase implements InterfaceConnexionDatabase {

    /** The instance connexion database. */
    INSTANCE_CONNEXION_DATABASE;

    /** The prop. */
    private final Properties prop = new Properties();

    private final HikariDataSource ds;

    private static final ThreadLocal<Connection> MYTHREADLOCAL = new ThreadLocal<Connection>();

    /** The Constant LOGGER. */
    public final Logger LOGGER = LoggerFactory
            .getLogger(ConnexionDatabase.class);

    /**
     * Instantiates a new connexion database.
     */
    ConnexionDatabase() {

        String file = "connexion.properties";

        try (InputStream stream = ConnexionDatabase.class.getClassLoader()
                .getResourceAsStream("connexion.properties");) {

            prop.load(stream);

        } catch (IOException e) {

            LOGGER.error("Fichier introuvable : " + file);

        } catch (NullPointerException e) {

            LOGGER.error("Fichier introuvable : " + file);

        }

        HikariConfig config = new HikariConfig(prop);
        config.setMaximumPoolSize(10);
        config.setAutoCommit(false);
        ds = new HikariDataSource(config);

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

            MYTHREADLOCAL.set(con = ds.getConnection());

        }

        return con;

    }

}
