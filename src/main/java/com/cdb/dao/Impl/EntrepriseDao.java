package com.cdb.dao.Impl;

import java.io.IOException;
import java.io.InputStream;
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
import com.cdb.model.entities.Entreprise;
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

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(EntrepriseDao.class);

    /** The prop. */
    private static Properties prop = new Properties();

    static {

        String file = "query.properties";

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
            entreprises = EntrepriseDaoMapper
                    .recuperationListEntreprise(rset);
            LOGGER.info("recherche de la liste d'entreprise effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec" + " de la requete de recherche d'entreprise");

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
                PreparedStatement stmt = con.prepareStatement(
                        prop.getProperty("QUERY_FIND_ENTREPRISES_BY_ID"))) {

            stmt.setLong(1, index);
            ResultSet res = stmt.executeQuery();
            entreprise = EntrepriseDaoMapper
                    .recupertationEntreprise(res);
            LOGGER.info("recherche d'une entreprise par id effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche de l'entreprise numero: "
                            + index);

        }

        return entreprise;

    }

}
