
package com.cdb.dao.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.InterfaceOrdinateurDao;
import com.cdb.dao.Impl.mappers.OrdinateurDaoMapper;
import com.cdb.model.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

/**
 * The Enum OrdinateurDao.
 */
public enum OrdinateurDao implements InterfaceOrdinateurDao {

    /** The instance ordinateur dao. */
    INSTANCE_ORDINATEUR_DAO;

    /** The Constant logger. */
    public final Logger LOGGER = LoggerFactory.getLogger(OrdinateurDao.class);

    /** The prop. */
    private final Properties prop = new Properties();

    /**
     * Instantiates a new ordinateur dao.
     */
    OrdinateurDao() {

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
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            à créer
     * @param con
     *            the con
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public void createOrdinateur(Ordinateur ordinateur, Connection con)
            throws ConnexionDatabaseException, RequeteQueryException {

        LOGGER.info("Création d'un ordinateur");
        LOGGER.debug("" + ordinateur);

        try (PreparedStatement stmt = con.prepareStatement(
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
            ordinateurs = OrdinateurDaoMapper.recuperationListOrdinateur(rset);
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
            int ligneParPage, String trie, boolean desc)
            throws ConnexionDatabaseException, RequeteQueryException {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        int limit = ligneParPage;
        int offset = (numeroPage - 1) * ligneParPage;
        String requete = prop.getProperty("QUERY_FIND_ORDINATEURS_BY_PAGE");

        if (offset < 0) {

            LOGGER.error("Offset negatif");
            return ordinateurs;

        }

        LOGGER.info("recherche de la liste d'ordinateur par page ");
        LOGGER.debug("" + limit + " " + offset);
        
        if (trie != null && !trie.equals("")) {
            
            if (desc) {
                
                requete = String.format(requete, trie + " DESC");
                
            } else {
                
                requete = String.format(requete, trie + " ASC");
                
            }
            
        } else {
            
            requete = String.format(requete, "name");
            
        }

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement(requete)) {

            stmt.setInt(1, limit);
            stmt.setInt(2, offset); 
            ResultSet res = stmt.executeQuery();
            ordinateurs = OrdinateurDaoMapper.recuperationListOrdinateur(res);
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
     * @param numeroPage
     *            the numero page
     * @param ligneParPage
     *            the ligne par page
     * @param name
     *            le nom de l'ordinateur recherché
     * @return une liste ordinateur
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public List<Ordinateur> findOrdinateurByName(int numeroPage,
            int ligneParPage, String name, String trie, boolean desc)
            throws ConnexionDatabaseException, RequeteQueryException {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        int limit = ligneParPage;
        int offset = (numeroPage - 1) * ligneParPage;
        String requete = prop.getProperty("QUERY_FIND_ORDINATEURS_BY_NAME");

        if (offset < 0) {

            LOGGER.error("Offset negatif");
            return ordinateurs;

        }
        
        if (trie != null && !trie.equals("")) {
            
            if (desc) {
                
                requete = String.format(requete, trie + " DESC");
                
            } else {
                
                requete = String.format(requete, trie + " ASC");
                
            }
            
        } else {
            
            requete = String.format(requete, "name");
            
        }

        LOGGER.info("recherche de la liste d'ordinateur par nom");

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement(requete)) {

            stmt.setString(1, "%" + name + "%");
            stmt.setString(2, "%" + name + "%");
            stmt.setInt(3, limit);
            stmt.setInt(4, offset);
            ResultSet res = stmt.executeQuery();
            ordinateurs = OrdinateurDaoMapper.recuperationListOrdinateur(res);
            LOGGER.info("recherche de la liste d'ordinateur par nom effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche par nom d'ordinateur");

        }

        return ordinateurs;

    }

    /**
     * Find ordinateur by id.
     *
     * @param id
     *            the id
     * @return the optional
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public Optional<Ordinateur> findOrdinateurById(long id)
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<Ordinateur> ordinateur = Optional.empty();
        LOGGER.info("recherche d'ordinateur par id");

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement(
                        prop.getProperty("QUERY_FIND_ORDINATEURS_BY_ID"))) {

            stmt.setLong(1, id);
            ResultSet res = stmt.executeQuery();
            ordinateur = Optional.ofNullable(
                    OrdinateurDaoMapper.recuperationOrdinateur(res));
            LOGGER.info("recherche de l'ordinateur effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "Echec de la requete de recherche par id d'ordinateur");

        }

        return ordinateur;

    }

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            à update
     * @param con
     *            the con
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public void updateOrdinateur(Ordinateur ordinateur, Connection con)
            throws ConnexionDatabaseException, RequeteQueryException {

        LOGGER.info("update d'un ordinateur");
        LOGGER.debug("" + ordinateur);

        try (PreparedStatement stmt = con.prepareStatement(
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
     * @param con
     *            the con
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public void suppressionOrdinateur(long id, Connection con)
            throws ConnexionDatabaseException, RequeteQueryException {

        LOGGER.info("suppression de l'ordinateur");
        LOGGER.debug("" + id);

        try (PreparedStatement stmt = con.prepareStatement(
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
     * Count ordinateur.
     *
     * @return le nombre d'ordinateur total
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
            count = OrdinateurDaoMapper.recuperationInt(res);
            LOGGER.info("Comptage du nombre d'ordinateur effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException("Echec de la requete count");

        }

        return count;

    }

    /**
     * Count ordinateur by name.
     *
     * @param filtre
     *            the filtre
     * @return the int
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public int countOrdinateurByName(String filtre)
            throws ConnexionDatabaseException, RequeteQueryException {

        int count = 0;
        LOGGER.info("Comptage du nombre d'ordinateur");

        try (Connection con = ConnexionDatabase.INSTANCE_CONNEXION_DATABASE
                .connectDatabase();
                PreparedStatement stmt = con.prepareStatement(
                        prop.getProperty("QUERY_COUNT_ORDINATEUR_BY_NAME"))) {

            stmt.setString(1, "%" + filtre + "%");
            stmt.setString(2, "%" + filtre + "%");
            ResultSet res = stmt.executeQuery();
            count = OrdinateurDaoMapper.recuperationInt(res);
            LOGGER.info("Comptage du nombre d'ordinateur effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException("Echec de la requete count");

        }

        return count;

    }

}
