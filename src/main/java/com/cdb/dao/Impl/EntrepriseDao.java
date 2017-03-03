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
import com.cdb.dao.Impl.mappers.EntrepriseDaoMapper;
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
    public List<Entreprise> findEntreprise()
            throws ConnexionDatabaseException, RequeteQueryException {

        List<Entreprise> entreprises = new ArrayList<Entreprise>();
        LOGGER.info("recherche de la liste d'entreprise");

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase(); Statement stmt = con.createStatement()) {

            ResultSet rset = stmt
                    .executeQuery(prop.getProperty("QUERY_FIND_ENTREPRISES"));
            entreprises = EntrepriseDaoMapper.INSTANCE_ENTREPRISE_DAO_MAPPER
                    .recuperationListEntreprise(rset);
            LOGGER.info("recherche de la liste d'entreprise effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec" + " de la requete de recherche d'entreprise");

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
    public List<Entreprise> findEntrepriseByPage(int numeroPage,
            int ligneParPage)
            throws ConnexionDatabaseException, RequeteQueryException {

        List<Entreprise> entreprises = new ArrayList<Entreprise>();

        if (ligneParPage < 1) {

            return entreprises;

        }

        int limit = ligneParPage;
        int offset = (numeroPage - 1) * ligneParPage;

        if (offset < 0) {

            return entreprises;

        }

        LOGGER.info("recherche de la liste d'entreprise par page");

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement((prop.getProperty(
                        prop.getProperty("QUERY_FIND_ENTREPRISES_BY_PAGE"))))) {

            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            ResultSet res = stmt.executeQuery();
            entreprises = EntrepriseDaoMapper.INSTANCE_ENTREPRISE_DAO_MAPPER
                    .recuperationListEntreprise(res);
            LOGGER.info(
                    "recherche de la liste d'entreprise par page effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche par page d'entreprise");

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
        LOGGER.info("recherche d'une entreprise par id: " + index);

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement((prop.getProperty(
                        prop.getProperty("QUERY_FIND_ENTREPRISES_BY_ID"))))) {

            stmt.setLong(1, index);
            ResultSet res = stmt.executeQuery();
            entreprise = EntrepriseDaoMapper.INSTANCE_ENTREPRISE_DAO_MAPPER
                    .recupertationEntreprise(res);
            LOGGER.info("recherche d'une entreprise par id effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche de l'entreprise numero: "
                            + index);

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
        LOGGER.info("recherche d'une entreprise par nom: " + name);

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement((prop.getProperty(
                        prop.getProperty("QUERY_FIND_ENTREPRISES_BY_NAME"))))) {

            stmt.setString(1, name);
            ResultSet res = stmt.executeQuery();
            entreprise = EntrepriseDaoMapper.INSTANCE_ENTREPRISE_DAO_MAPPER
                    .recupertationEntreprise(res);
            LOGGER.info("recherche d'une entreprise par nom effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche de l'entreprise: "
                            + name);

        }

        return entreprise;

    }

}
