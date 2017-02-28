package com.cdb.dao.Impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.InterfaceEntrepriseDao;
import com.cdb.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
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
                entreprises = recuperationResultatRequete(rset);
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
                entreprises = recuperationResultatRequete(res);
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

                if (res.next()) {

                    long id = res.getLong("id");
                    String name = res.getString("name");
                    entreprise = Optional.ofNullable(new Entreprise(id, name));

                }

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
     * Recuperation resultat requete.
     *
     * @param res
     *            resultat d'une requete
     * @return une liste d'entreprise
     * @throws RequeteQueryException
     *             if there is an issue
     */
    private Optional<List<Optional<Entreprise>>> recuperationResultatRequete(
            ResultSet res) throws RequeteQueryException {

        List<Optional<Entreprise>> entreprises = new ArrayList<Optional<Entreprise>>();

        try {

            while (res.next()) {

                long id = res.getLong("id");
                String name = res.getString("name");
                entreprises.add(Optional.ofNullable(new Entreprise(id, name)));

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "L'extraction des données du résultat de la requete ne s'est pas déroulé correctement");

        }

        return Optional.ofNullable(entreprises);

    }

}
