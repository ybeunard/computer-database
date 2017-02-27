package com.cdb.dao.Impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.cdb.dao.InterfaceConnexionDatabase;
import com.cdb.exception.ConnexionDatabaseException;
import com.mysql.jdbc.Connection;

import java.util.Optional;
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

    /**
     * Gets the instance connexion database.
     *
     * @return INSTANCE_CONNEXION_DATABASE
     */
    public static final ConnexionDatabase getInstanceConnexionDatabase() {

        return INSTANCE_CONNEXION_DATABASE;

    }

    /** The prop. */
    private static Properties prop = new Properties();

    static {

        FileInputStream stream = null;

        try {

            stream = new FileInputStream(
                    "/home/excilys/eclipse_workspace/computerDatabase/src/main/resources/connexion.properties");
            prop.load(stream);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(ConnexionDatabase.class);

    /**
     * Connect database.
     *
     * @return Connection
     * @throws ConnexionDatabaseException
     *             if there is an issue
     */
    public Optional<Connection> connectDatabase()
            throws ConnexionDatabaseException {

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
        Optional<Connection> con = Optional.empty();

        try {

            con = Optional.ofNullable((Connection) DriverManager.getConnection(
                    prop.getProperty("URL"), prop.getProperty("LOGIN"),
                    prop.getProperty("PASSWORD")));

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
