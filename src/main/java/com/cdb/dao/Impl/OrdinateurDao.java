
package com.cdb.dao.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.mysql.jdbc.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.InterfaceOrdinateurDao;
import com.cdb.dao.Impl.mappers.OrdinateurDaoMapper;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

/**
 * The Enum OrdinateurDao.
 */
public enum OrdinateurDao implements InterfaceOrdinateurDao {

    /** The instance ordinateur dao. */
    INSTANCE_ORDINATEUR_DAO;

    /**
     * Instantiates a new ordinateur dao.
     */
    OrdinateurDao() {

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

        LOGGER.info("Création d'un " + ordinateur);

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement(
                        prop.getProperty("QUERY_INSERT_ORDINATEUR"))) {

            stmt.setString(1, ordinateur.getName());

            if (ordinateur.getDateIntroduit() != null) {

                stmt.setDate(2, Date.valueOf(ordinateur.getDateIntroduit()));

            } else {

                stmt.setDate(2, null);

            }
            if (ordinateur.getDateInterrompu() != null) {

                stmt.setDate(3, Date.valueOf(ordinateur.getDateInterrompu()));

            } else {

                stmt.setDate(3, null);

            }
            if (ordinateur.getFabricant().isPresent()) {

                stmt.setLong(4, ordinateur.getFabricant().get().getId());

            } else {

                stmt.setString(4, null);

            }

            stmt.executeUpdate();
            LOGGER.info("Creation d'un ordinateur effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de creation de l'" + ordinateur);

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
    public List<Ordinateur> findOrdinateur()
            throws ConnexionDatabaseException, RequeteQueryException {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        LOGGER.info("recherche de la liste d'ordinateur");

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement(
                        prop.getProperty("QUERY_FIND_ORDINATEURS"))) {

            ResultSet rset = stmt.executeQuery();
            ordinateurs = OrdinateurDaoMapper.INSTANCE_ORDINATEUR_DAO_MAPPER
                    .recuperationListOrdinateur(rset);
            LOGGER.info("recherche de la liste d'ordinateur effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche d'ordinateur");

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
    public List<Ordinateur> findOrdinateurByPage(int numeroPage,
            int ligneParPage)
            throws ConnexionDatabaseException, RequeteQueryException {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        int limit = ligneParPage;
        int offset = (numeroPage - 1) * ligneParPage;

        if (offset < 0) {

            return ordinateurs;

        }

        LOGGER.info("recherche de la liste d'ordinateur par page "+limit + " " + offset);

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement(
                        prop.getProperty("QUERY_FIND_ORDINATEURS_BY_PAGE"))) {

            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            ResultSet res = stmt.executeQuery();
            ordinateurs = OrdinateurDaoMapper.INSTANCE_ORDINATEUR_DAO_MAPPER
                    .recuperationListOrdinateur(res);
            LOGGER.info(
                    "recherche de la liste d'ordinateur par page effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche par page d'ordinateur");

        }

        return ordinateurs;

    }

    /**
     * Find ordinateur by Name.
     *
     * @param name
     *            le nom de l'ordinateur recherché
     * @return une liste ordinateur
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public List<Ordinateur> findOrdinateurByName(String name)
            throws ConnexionDatabaseException, RequeteQueryException {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        LOGGER.info("recherche de la liste d'ordinateur par nom");

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement(
                        prop.getProperty("QUERY_FIND_ORDINATEURS_BY_NAME"))) {

            stmt.setString(1, "%" + name + "%");
            ResultSet res = stmt.executeQuery();
            ordinateurs = OrdinateurDaoMapper.INSTANCE_ORDINATEUR_DAO_MAPPER
                    .recuperationListOrdinateur(res);
            LOGGER.info("recherche de la liste d'ordinateur par nom effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche par nom d'ordinateur");

        }

        return ordinateurs;

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

        LOGGER.info("update d'un " + ordinateur);

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement(
                        prop.getProperty("QUERY_UPDATE_ORDINATEUR"))) {

            stmt.setString(1, ordinateur.getName());

            if (ordinateur.getDateIntroduit() != null) {

                stmt.setDate(2, Date.valueOf(ordinateur.getDateIntroduit()));

            } else {

                stmt.setDate(2, null);

            }
            if (ordinateur.getDateInterrompu() != null) {

                stmt.setDate(3, Date.valueOf(ordinateur.getDateInterrompu()));

            } else {

                stmt.setDate(3, null);

            }
            if (ordinateur.getFabricant().isPresent()) {

                stmt.setLong(4, ordinateur.getFabricant().get().getId());

            } else {

                stmt.setString(4, null);

            }

            stmt.setLong(5, ordinateur.getId());
            stmt.executeUpdate();
            LOGGER.info("update d'un ordinateur effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de mise à jour de l'" + ordinateur);

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

        LOGGER.info("suppression de l'ordinateur numero " + id);

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement(
                        prop.getProperty("QUERY_DELETE_ORDINATEUR"))) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
            LOGGER.info("suppression d'un ordinateur effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de suppression de l'ordinateur: "
                            + id);

        }

    }

    /**
     * @return le nombre d'ordinateur total
     *
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public int countOrdinateur()
            throws ConnexionDatabaseException, RequeteQueryException {

        int count = 0;
        LOGGER.info("Comptage du nombre d'ordinateur");

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement(
                        prop.getProperty("QUERY_COUNT_ORDINATEUR"))) {

            ResultSet res = stmt.executeQuery();
            count = OrdinateurDaoMapper.INSTANCE_ORDINATEUR_DAO_MAPPER
                    .recuperationInt(res);
            LOGGER.info("Comptage du nombre d'ordinateur effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException("Echec de la requete count");

        }

        return count;

    }

}
