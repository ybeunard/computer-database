package com.cdb.dao.Impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.InterfaceEntrepriseDao;
import com.cdb.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.mappers.Impl.EntrepriseMapper;
import com.mysql.jdbc.Connection;

/**
 * The Enum EntrepriseDao.
 *
 * @author excilys
 */
public enum EntrepriseDao implements InterfaceEntrepriseDao {

    /** The instance entreprise dao. */
    INSTANCE_ENTREPRISE_DAO;

    /**
     * Instantiates a new entreprise dao.
     */
    EntrepriseDao() {

    }

    /**
     * Gets the instance entreprise dao.
     *
     * @return INSTANCE_ENTREPRISE_DAO
     */
    public static final EntrepriseDao getInstanceEntrepriseDao() {

        return INSTANCE_ENTREPRISE_DAO;

    }

    /** The prop. */
    private static Properties prop = new Properties();

    static {

        FileInputStream stream = null;

        try {

            stream = new FileInputStream(
                    "/home/excilys/eclipse_workspace/computerDatabase/src/main/resources/query.properties");
            prop.load(stream);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(EntrepriseDao.class);

    /**
     * Find entreprise.
     *
     * @return une liste d'entreprise
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public Optional<List<Optional<Entreprise>>> findEntreprise()
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<List<Optional<Entreprise>>> entreprises = Optional.empty();
        Optional<Connection> con = ConnexionDatabase
                .getInstanceConnexionDatabase().connectDatabase();
        Statement stmt = null;
        LOGGER.info("recherche de la liste d'entreprise");

        try {

            if (con.isPresent()) {

                stmt = con.get().createStatement();
                ResultSet rset = stmt.executeQuery(
                        prop.getProperty("QUERY_FIND_ENTREPRISES"));
                entreprises = EntrepriseMapper.getInstanceEntrepriseMapper()
                        .recuperationListResultatRequete(rset);
                LOGGER.info("recherche de la liste d'entreprise effectuée");

            } else {

                LOGGER.info("echec de la connexion");

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec" + " de la requete de recherche d'entreprise");

        } finally {

            if (stmt != null) {

                try {

                    stmt.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture de la requete de recherche d'entreprise impossible");

                }

            }
            if (con.isPresent()) {

                ConnexionDatabase.getInstanceConnexionDatabase()
                        .closeConnexionDatabase(con.get());

            }

        }

        return entreprises;

    }

    /**
     * Find entreprise by page.
     *
     * @param numeroPage
     *            le numero de page
     * @param ligneParPage
     *            le nombre de ligne par page
     * @return une liste d'entreprise
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public Optional<List<Optional<Entreprise>>> findEntrepriseByPage(
            int numeroPage, int ligneParPage)
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<List<Optional<Entreprise>>> entreprises = Optional.empty();
        int limit = ligneParPage;
        int offset = (numeroPage - 1) * ligneParPage;

        if (offset < 0) {

            return entreprises;

        }

        Optional<Connection> con = ConnexionDatabase
                .getInstanceConnexionDatabase().connectDatabase();
        PreparedStatement requete = null;
        LOGGER.info("recherche de la liste d'entreprise par page");

        try {

            if (con.isPresent()) {

                requete = con.get().prepareStatement(
                        prop.getProperty("QUERY_FIND_ENTREPRISES_BY_PAGE"));
                requete.setInt(1, limit);
                requete.setInt(2, offset);
                ResultSet res = requete.executeQuery();
                entreprises = EntrepriseMapper.getInstanceEntrepriseMapper()
                        .recuperationListResultatRequete(res);
                LOGGER.info(
                        "recherche de la liste d'entreprise par page effectuée");

            } else {

                LOGGER.info("echec de la connexion");

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche par page d'entreprise");

        } finally {

            if (requete != null) {

                try {

                    requete.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture de la requete de recherche d'entreprise par page impossible");

                }

            }
            if (con.isPresent()) {

                ConnexionDatabase.getInstanceConnexionDatabase()
                        .closeConnexionDatabase(con.get());

            }

        }

        return entreprises;

    }

    /**
     * Find entreprise by ID.
     *
     * @param index
     *            l'id de l'entreprise à rechercher
     * @return une entreprise
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public Optional<Entreprise> findEntrepriseByID(long index)
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<Entreprise> entreprise = Optional.empty();
        Optional<Connection> con = ConnexionDatabase
                .getInstanceConnexionDatabase().connectDatabase();
        PreparedStatement stmt = null;
        LOGGER.info("recherche d'une entreprise par id: " + index);

        try {

            if (con.isPresent()) {

                stmt = con.get().prepareStatement(
                        prop.getProperty("QUERY_FIND_ENTREPRISES_BY_ID"));
                stmt.setLong(1, index);
                ResultSet res = stmt.executeQuery();
                entreprise = EntrepriseMapper.getInstanceEntrepriseMapper()
                        .recupertationResultatRequete(res);
                LOGGER.info("recherche d'une entreprise par id effectuée");

            } else {

                LOGGER.info("echec de la connexion");

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche de l'entreprise numero: "
                            + index);

        } finally {

            if (stmt != null) {

                try {

                    stmt.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture de la requete de recherche d'entreprise par ID impossible");

                }

            }
            if (con.isPresent()) {

                ConnexionDatabase.getInstanceConnexionDatabase()
                        .closeConnexionDatabase(con.get());

            }

        }

        return entreprise;

    }

    /**
     * Find entreprise by name.
     *
     * @param name
     *            le nom de l'entreprise à rechercher
     * @return une entreprise
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public Optional<Entreprise> findEntrepriseByName(String name)
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<Entreprise> entreprise = Optional.empty();
        Optional<Connection> con = ConnexionDatabase
                .getInstanceConnexionDatabase().connectDatabase();
        PreparedStatement stmt = null;
        LOGGER.info("recherche d'une entreprise par nom: " + name);

        try {

            if (con.isPresent()) {

                stmt = con.get().prepareStatement(
                        prop.getProperty("QUERY_FIND_ENTREPRISES_BY_NAME"));
                stmt.setString(1, name);
                ResultSet res = stmt.executeQuery();
                entreprise = EntrepriseMapper.getInstanceEntrepriseMapper()
                        .recupertationResultatRequete(res);
                LOGGER.info("recherche d'une entreprise par nom effectuée");

            } else {

                LOGGER.info("echec de la connexion");

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche de l'entreprise: "
                            + name);

        } finally {

            if (stmt != null) {

                try {

                    stmt.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture de la requete de recherche d'entreprise par nom impossible");

                }

            }
            if (con.isPresent()) {

                ConnexionDatabase.getInstanceConnexionDatabase()
                        .closeConnexionDatabase(con.get());

            }

        }

        return entreprise;

    }

}
