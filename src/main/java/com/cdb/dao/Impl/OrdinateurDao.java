package com.cdb.dao.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.mysql.jdbc.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.InstanceOrdinateurDao;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.mappers.Impl.OrdinateurMapper;

/**
 * The Enum OrdinateurDao.
 */
public enum OrdinateurDao implements InstanceOrdinateurDao {

    /** The instance ordinateur dao. */
    INSTANCE_ORDINATEUR_DAO;

    /**
     * Instantiates a new ordinateur dao.
     */
    OrdinateurDao() {

    }

    /**
     * Gets the instance ordinateur dao.
     *
     * @return the instance ordinateur dao
     */
    public static final OrdinateurDao getInstanceOrdinateurDao() {

        return INSTANCE_ORDINATEUR_DAO;

    }

    /** The prop. */
    private static Properties prop = new Properties();

    static {

        File fProp = new File(
                "/home/excilys/eclipse_workspace/computerDatabase/src/main/resources/query.properties");

        FileInputStream stream = null;

        try {

            stream = new FileInputStream(fProp);
            prop.load(stream);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    /** The Constant logger. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(OrdinateurDao.class);

    /**
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            à créer
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public void createOrdinateur(Ordinateur ordinateur)
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<Connection> con = ConnexionDatabase
                .getInstanceConnexionDatabase().connectDatabase();
        PreparedStatement requete = null;
        LOGGER.info("Création d'un " + ordinateur);

        try {

            if (con.isPresent()) {

                requete = con.get().prepareStatement(
                        prop.getProperty("QUERY_INSERT_ORDINATEUR"));
                requete.setString(1, ordinateur.getName());

                if (ordinateur.getDateIntroduit() != null) {

                    requete.setDate(2,
                            Date.valueOf(ordinateur.getDateIntroduit()));

                } else {

                    requete.setDate(2, null);

                }
                if (ordinateur.getDateInterrompu() != null) {

                    requete.setDate(3,
                            Date.valueOf(ordinateur.getDateInterrompu()));

                } else {

                    requete.setDate(3, null);

                }
                if (ordinateur.getFabricant().isPresent()) {

                    requete.setLong(4, ordinateur.getFabricant().get().getId());

                } else {

                    requete.setString(4, null);

                }

                requete.executeUpdate();
                LOGGER.info("Creation d'un ordinateur effectuée");

            } else {

                LOGGER.info("echec de la connexion");

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de creation de l'" + ordinateur);

        } finally {

            if (requete != null) {

                try {

                    requete.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture de la requete de creation d'ordinateur impossible");

                }

            }
            if (con.isPresent()) {

                ConnexionDatabase.getInstanceConnexionDatabase()
                        .closeConnexionDatabase(con.get());

            }

        }

    }

    /**
     * Find ordinateur.
     *
     * @return une liste d'ordinateur
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public Optional<List<Optional<Ordinateur>>> findOrdinateur()
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<List<Optional<Ordinateur>>> ordinateurs = Optional.empty();

        Optional<Connection> con = ConnexionDatabase
                .getInstanceConnexionDatabase().connectDatabase();
        Statement stmt = null;
        LOGGER.info("recherche de la liste d'ordinateur");

        try {

            if (con.isPresent()) {

                stmt = con.get().createStatement();
                ResultSet rset = stmt.executeQuery(
                        prop.getProperty("QUERY_FIND_ORDINATEURS"));
                ordinateurs = OrdinateurMapper.getInstanceOrdinateurMapper()
                        .recuperationListResultatRequete(rset);
                LOGGER.info("recherche de la liste d'ordinateur effectuée");

            } else {

                LOGGER.info("echec de la connexion");

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche d'ordinateur");

        } finally {

            if (stmt != null) {

                try {

                    stmt.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture de la requete de recherche d'ordinateur impossible");

                }

            }
            if (con.isPresent()) {

                ConnexionDatabase.getInstanceConnexionDatabase()
                        .closeConnexionDatabase(con.get());

            }

        }

        return ordinateurs;

    }

    /**
     * Find ordinateur by page.
     *
     * @param numeroPage
     *            le numero de la page
     * @param ligneParPage
     *            le nombre de ligne par page
     * @return une liste d'ordinateur
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public Optional<List<Optional<Ordinateur>>> findOrdinateurByPage(
            int numeroPage, int ligneParPage)
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<List<Optional<Ordinateur>>> ordinateurs = Optional.empty();
        int limit = ligneParPage;
        int offset = (numeroPage - 1) * ligneParPage;

        if (offset < 0) {

            return ordinateurs;

        }

        Optional<Connection> con = ConnexionDatabase
                .getInstanceConnexionDatabase().connectDatabase();
        PreparedStatement requete = null;
        LOGGER.info("recherche de la liste d'ordinateur par page");

        try {

            if (con.isPresent()) {
                requete = con.get().prepareStatement(
                        prop.getProperty("QUERY_FIND_ORDINATEURS_BY_PAGE"));
                requete.setInt(1, limit);
                requete.setInt(2, offset);
                ResultSet res = requete.executeQuery();
                ordinateurs = OrdinateurMapper.getInstanceOrdinateurMapper()
                        .recuperationListResultatRequete(res);
                LOGGER.info(
                        "recherche de la liste d'ordinateur par page effectuée");

            } else {

                LOGGER.info("echec de la connexion");

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche par page d'ordinateur");

        } finally {

            if (requete != null) {

                try {

                    requete.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture de la requete de recherche d'ordinateur par page impossible");

                }

            }
            if (con.isPresent()) {

                ConnexionDatabase.getInstanceConnexionDatabase()
                        .closeConnexionDatabase(con.get());

            }

        }

        return ordinateurs;

    }

    /**
     * Find ordinateur by ID.
     *
     * @param index
     *            l'identifiant de l'ordinateur recherché
     * @return un ordinateur
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public Optional<Ordinateur> findOrdinateurByID(long index)
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<Ordinateur> ordinateur = Optional.empty();
        Optional<Connection> con = ConnexionDatabase
                .getInstanceConnexionDatabase().connectDatabase();
        PreparedStatement requete = null;
        LOGGER.info("recherche d'un ordinateur par id: " + index);

        try {

            if (con.isPresent()) {
                requete = con.get().prepareStatement(
                        prop.getProperty("QUERY_FIND_ORDINATEURS_BY_ID"));
                requete.setLong(1, index);
                ResultSet res = requete.executeQuery();
                ordinateur = OrdinateurMapper.getInstanceOrdinateurMapper()
                        .recuperationResultatRequete(res);
                LOGGER.info("recherche d'un ordinateur par id effectuée");

            } else {

                LOGGER.info("echec de la connexion");

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche de l'ordinateur numero:"
                            + index);

        } finally {

            if (requete != null) {

                try {

                    requete.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture de la requete de recherche d'ordinateur par ID impossible");

                }

            }
            if (con.isPresent()) {

                ConnexionDatabase.getInstanceConnexionDatabase()
                        .closeConnexionDatabase(con.get());

            }

        }

        return ordinateur;

    }

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            à update
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public void updateOrdinateur(Ordinateur ordinateur)
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<Connection> con = ConnexionDatabase
                .getInstanceConnexionDatabase().connectDatabase();
        PreparedStatement requete = null;
        LOGGER.info("update d'un " + ordinateur);

        try {

            if (con.isPresent()) {

                requete = con.get().prepareStatement(
                        prop.getProperty("QUERY_UPDATE_ORDINATEUR"));
                requete.setString(1, ordinateur.getName());

                if (ordinateur.getDateIntroduit() != null) {

                    requete.setDate(2,
                            Date.valueOf(ordinateur.getDateIntroduit()));

                } else {

                    requete.setDate(2, null);

                }
                if (ordinateur.getDateInterrompu() != null) {

                    requete.setDate(3,
                            Date.valueOf(ordinateur.getDateInterrompu()));

                } else {

                    requete.setDate(3, null);

                }
                if (ordinateur.getFabricant().isPresent()) {

                    requete.setLong(4, ordinateur.getFabricant().get().getId());

                } else {

                    requete.setString(4, null);

                }

                requete.setLong(5, ordinateur.getId());
                requete.executeUpdate();

                LOGGER.info("update d'un ordinateur effectuée");

            } else {

                LOGGER.info("echec de la connexion");

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de mise à jour de l'" + ordinateur);

        } finally {

            if (requete != null) {

                try {

                    requete.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture de la requete de mise à jour d'ordinateur impossible");

                }

            }
            if (con.isPresent()) {

                ConnexionDatabase.getInstanceConnexionDatabase()
                        .closeConnexionDatabase(con.get());

            }

        }

    }

    /**
     * Suppression ordinateur.
     *
     * @param id
     *            Identifiant de l'ordinateur à supprimer
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public void suppressionOrdinateur(long id)
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<Connection> con = ConnexionDatabase
                .getInstanceConnexionDatabase().connectDatabase();
        PreparedStatement requete = null;
        LOGGER.info("suppression de l'ordinateur numero " + id);

        try {

            if (con.isPresent()) {
                requete = con.get().prepareStatement(
                        prop.getProperty("QUERY_DELETE_ORDINATEUR"));
                requete.setLong(1, id);
                requete.executeUpdate();
                LOGGER.info("suppression d'un ordinateur effectuée");

            } else {

                LOGGER.info("echec de la connexion");

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de suppression de l'ordinateur: "
                            + id);

        } finally {

            if (requete != null) {

                try {

                    requete.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture de la requete de suppression d'ordinateur impossible");

                }

            }
            if (con.isPresent()) {

                ConnexionDatabase.getInstanceConnexionDatabase()
                        .closeConnexionDatabase(con.get());

            }

        }

    }

}
