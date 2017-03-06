package com.cdb.dao.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.cdb.dao.InterfaceConnexionDatabase;
import com.cdb.exception.ConnexionDatabaseException;
import com.mysql.jdbc.Connection;

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

    /**
     * Instantiates a new connexion database.
     */
    ConnexionDatabase() {

    }

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(ConnexionDatabase.class);

    /** The prop. */
    private static Properties prop = new Properties();

    static {

        String file = "connexion.properties";

        try (InputStream stream = ConnexionDatabase.class.getClassLoader()
                .getResourceAsStream(file);) {

            prop.load(stream);

        } catch (IOException e) {

            LOGGER.error("Fichier introuvable : " + file);

        } catch (NullPointerException e) {

            LOGGER.error("Fichier introuvable : " + file);

        }

    }

    /**
     * Connect database.
     *
     * @return Connection
     * @throws ConnexionDatabaseException
     *             if there is an issue
     */
    public Connection connectDatabase() throws ConnexionDatabaseException {

        try {

            Class.forName(prop.getProperty("nameDriver")).newInstance();

        } catch (InstantiationException e) {

            throw new ConnexionDatabaseException(
                    "Impossible de charger le Driver "
                            + prop.getProperty("nameDriver"));

        } catch (IllegalAccessException e) {

            throw new ConnexionDatabaseException(
                    "Impossible de charger le Driver "
                            + prop.getProperty("nameDriver"));

        } catch (ClassNotFoundException e) {

            throw new ConnexionDatabaseException(
                    "Impossible de charger le Driver "
                            + prop.getProperty("nameDriver"));

        }

        /**
         *
         */
        LOGGER.info("Tentative de connexion");
        Connection con = null;

        try {

            con = (Connection) DriverManager.getConnection(
                    prop.getProperty("URL"), prop.getProperty("LOGIN"),
                    prop.getProperty("PASSWORD"));

        } catch (SQLException e) {

            throw new ConnexionDatabaseException("Connexion au serveur "
                    + prop.getProperty("URL") + " impossible", con);

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

}
