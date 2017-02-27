package com.cdb.dao.Impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

            stream = new FileInputStream("/home/excilys/eclipse_workspace/computerDatabase/src/main/resources/query.properties");
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
    public List<Entreprise> findEntreprise()
            throws ConnexionDatabaseException, RequeteQueryException {

        List<Entreprise> entreprises = new ArrayList<Entreprise>();
        Connection con = ConnexionDatabase.getInstanceConnexionDatabase()
                .connectDatabase();
        Statement stmt = null;
        LOGGER.info("recherche de la liste d'entreprise");

        try {

            stmt = con.createStatement();
            ResultSet rset = stmt
                    .executeQuery(prop.getProperty("QUERY_FIND_ENTREPRISES"));
            entreprises = recuperationResultatRequete(rset);
            LOGGER.info("recherche de la liste d'entreprise effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec" + " de la requete de recherche d'entreprise");

        } finally {

            if (stmt != null) {

                try {

                    stmt.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture" + " de la requete de recherche"
                                    + " d'entreprise impossible");

                }

            }

            ConnexionDatabase.getInstanceConnexionDatabase()
                    .closeConnexionDatabase(con);

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
    public List<Entreprise> findEntrepriseByPage(final int numeroPage,
            final int ligneParPage)
            throws ConnexionDatabaseException, RequeteQueryException {

        List<Entreprise> entreprises = new ArrayList<Entreprise>();
        int limit = ligneParPage;
        int offset = (numeroPage - 1) * ligneParPage;
        Connection con = ConnexionDatabase.getInstanceConnexionDatabase()
                .connectDatabase();
        PreparedStatement requete = null;
        LOGGER.info("recherche de la liste d'entreprise par page");

        try {

            requete = con.prepareStatement(
                    prop.getProperty("QUERY_FIND_ENTREPRISES_BY_PAGE"));
            requete.setInt(1, limit);
            requete.setInt(2, offset);
            ResultSet res = requete.executeQuery();
            entreprises = recuperationResultatRequete(res);
            LOGGER.info("recherche de la liste"
                    + " d'entreprise par page effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException("Echec"
                    + " de la requete de recherche" + " par page d'entreprise");

        } finally {

            if (requete != null) {

                try {

                    requete.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture" + " de la requete de recherche "
                                    + "d'entreprise par page impossible");

                }

            }

            ConnexionDatabase.getInstanceConnexionDatabase()
                    .closeConnexionDatabase(con);

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
    public Entreprise findEntrepriseByID(final long index)
            throws ConnexionDatabaseException, RequeteQueryException {

        Entreprise entreprise = null;
        Connection con = ConnexionDatabase.getInstanceConnexionDatabase()
                .connectDatabase();
        PreparedStatement stmt = null;
        LOGGER.info("recherche d'une entreprise par id: " + index);

        try {

            stmt = con.prepareStatement(
                    prop.getProperty("QUERY_FIND_ENTREPRISES_BY_ID"));
            stmt.setLong(1, index);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {

                entreprise = new Entreprise(res.getInt("id"),
                        res.getString("name"));

            }

            LOGGER.info("recherche d'une entreprise par id effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec" + " de la requete de recherche"
                            + " de l'entreprise numero:" + index);

        } finally {

            if (stmt != null) {

                try {

                    stmt.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture" + " de la requete de recherche"
                                    + " d'entreprise par ID impossible");

                }

            }

            ConnexionDatabase.getInstanceConnexionDatabase()
                    .closeConnexionDatabase(con);

        }

        return entreprise;

    }

    /**
     * Recuperation resultat requete.
     *
     * @param rset
     *            resultat d'une requete
     * @return une liste d'entreprise
     * @throws RequeteQueryException
     *             if there is an issue
     */
    private List<Entreprise> recuperationResultatRequete(final ResultSet rset)
            throws RequeteQueryException {

        List<Entreprise> entreprises = new ArrayList<Entreprise>();

        try {

            while (rset.next()) {

                entreprises.add(new Entreprise(rset.getLong("id"),
                        rset.getString("name")));

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "L'extraction des données " + "du résultat de la requete ne"
                            + " s'est pas déroulé correctement");

        }

        return entreprises;

    }

}
