
package com.cdb.dao.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cdb.dao.InterfaceOrdinateurDao;
import com.cdb.dao.Impl.mappers.OrdinateurDaoMapper;
import com.cdb.model.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

/**
 * The Enum OrdinateurDao.
 */
public class OrdinateurDao implements InterfaceOrdinateurDao {

    /** The Constant logger. */
    public final Logger LOGGER = LoggerFactory.getLogger(OrdinateurDao.class);

    @Autowired
    private ConnexionDatabase connexionDatabase;
    
    public ConnexionDatabase getConnexionDatabase() {
        
        return connexionDatabase;
        
    }
    
    /** The prop. */
    private final Properties prop = new Properties();

    /**
     * Instantiates a new ordinateur dao.
     */
    public OrdinateurDao() {

        String file = "query.properties";

        try (InputStream stream = ConnexionDatabase.class.getClassLoader()
                .getResourceAsStream(file);) {

            prop.load(stream);

        } catch (IOException e) {

            LOGGER.error("Fichier introuvable : " + file);

        } catch (NullPointerException e) {

            LOGGER.error("Fichier introuvable : " + file);

        }
        
        LOGGER.info("OrdinateurDao instancié");

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
        JdbcTemplate jdbcTemplate = connexionDatabase.getJdbcTemplate();
        ordinateurs = jdbcTemplate.query(prop.getProperty("QUERY_FIND_ORDINATEURS"), new OrdinateurDaoMapper());
        return ordinateurs;

    }

    /**
     * Find ordinateur by page.
     *
     * @param numeroPage
     *            le numero de la page
     * @param ligneParPage
     *            le nombre de ligne par page
     * @param trie
     *            the trie
     * @param desc
     *            the desc
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
        
        JdbcTemplate jdbcTemplate = connexionDatabase.getJdbcTemplate();
        ordinateurs = jdbcTemplate.query(requete, new Object[]{limit, offset}, new OrdinateurDaoMapper());
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
     * @param trie
     *            the trie
     * @param desc
     *            the desc
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
        JdbcTemplate jdbcTemplate = connexionDatabase.getJdbcTemplate();
        ordinateurs = jdbcTemplate.query(requete, new Object[]{"%" + name + "%","%" + name + "%",limit, offset}, new OrdinateurDaoMapper());
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
            throws ConnexionDatabaseException, EmptyResultDataAccessException {

        Optional<Ordinateur> ordinateur = Optional.empty();
        LOGGER.info("recherche d'ordinateur par id");
        JdbcTemplate jdbcTemplate = connexionDatabase.getJdbcTemplate();
        ordinateur = Optional.ofNullable(jdbcTemplate.queryForObject(prop.getProperty("QUERY_FIND_ORDINATEURS_BY_ID"), new Object[]{id}, new OrdinateurDaoMapper()));
        return ordinateur;

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
    public void createOrdinateur(Ordinateur ordinateur, JdbcTemplate jdbcTemplate)
            throws ConnexionDatabaseException, RequeteQueryException {

        LOGGER.info("Création d'un ordinateur");
        LOGGER.debug("" + ordinateur);
        
        if (ordinateur.getDateIntroduit() != null) {

            if(ordinateur.getDateInterrompu() != null) {
                
                if(ordinateur.getFabricant().isPresent()) {
                    
                    jdbcTemplate.update(prop.getProperty("QUERY_INSERT_ORDINATEUR"), ordinateur.getName(), Date.valueOf(ordinateur.getDateIntroduit()), Date.valueOf(ordinateur.getDateInterrompu()), ordinateur.getFabricant().get().getId());
                    
                } else {
                    
                    jdbcTemplate.update(prop.getProperty("QUERY_INSERT_ORDINATEUR"), ordinateur.getName(), Date.valueOf(ordinateur.getDateIntroduit()), Date.valueOf(ordinateur.getDateInterrompu()), null);
                    
                }
                
            } else {
                
                if(ordinateur.getFabricant().isPresent()) {
                
                    jdbcTemplate.update(prop.getProperty("QUERY_INSERT_ORDINATEUR"), ordinateur.getName(), Date.valueOf(ordinateur.getDateIntroduit()), null, ordinateur.getFabricant().get().getId());
                
                } else {
                    
                    jdbcTemplate.update(prop.getProperty("QUERY_INSERT_ORDINATEUR"), ordinateur.getName(), Date.valueOf(ordinateur.getDateIntroduit()), null, null);
                
                }
                
            }

        } else {
            
            if(ordinateur.getDateInterrompu() != null) {
                
                if(ordinateur.getFabricant().isPresent()) {
            
                    jdbcTemplate.update(prop.getProperty("QUERY_INSERT_ORDINATEUR"), ordinateur.getName(), null, Date.valueOf(ordinateur.getDateInterrompu()), ordinateur.getFabricant().get().getId());

                } else {
                    
                    jdbcTemplate.update(prop.getProperty("QUERY_INSERT_ORDINATEUR"), ordinateur.getName(), null, Date.valueOf(ordinateur.getDateInterrompu()), null);
                    
                }
                
            } else {
                
                if(ordinateur.getFabricant().isPresent()) {
                    
                    jdbcTemplate.update(prop.getProperty("QUERY_INSERT_ORDINATEUR"), ordinateur.getName(), null, null, ordinateur.getFabricant().get().getId());

                } else {
                    
                    jdbcTemplate.update(prop.getProperty("QUERY_INSERT_ORDINATEUR"), ordinateur.getName(), null, null, null);
                    
                }
                
            }

        }

        LOGGER.info("Creation d'un ordinateur effectuée");

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
    public void updateOrdinateur(Ordinateur ordinateur, JdbcTemplate jdbcTemplate)
            throws ConnexionDatabaseException, RequeteQueryException {

        LOGGER.info("update d'un ordinateur");
        LOGGER.debug("" + ordinateur);

        if (ordinateur.getDateIntroduit() != null) {

            if(ordinateur.getDateInterrompu() != null) {
                
                if(ordinateur.getFabricant().isPresent()) {
                    
                    jdbcTemplate.update(prop.getProperty("QUERY_UPDATE_ORDINATEUR"), ordinateur.getName(), Date.valueOf(ordinateur.getDateIntroduit()), Date.valueOf(ordinateur.getDateInterrompu()), ordinateur.getFabricant().get().getId(), ordinateur.getId());
                    
                } else {
                    
                    jdbcTemplate.update(prop.getProperty("QUERY_UPDATE_ORDINATEUR"), ordinateur.getName(), Date.valueOf(ordinateur.getDateIntroduit()), Date.valueOf(ordinateur.getDateInterrompu()), null, ordinateur.getId());
                    
                }
                
            } else {
                
                if(ordinateur.getFabricant().isPresent()) {
                
                    jdbcTemplate.update(prop.getProperty("QUERY_UPDATE_ORDINATEUR"), ordinateur.getName(), Date.valueOf(ordinateur.getDateIntroduit()), null, ordinateur.getFabricant().get().getId(), ordinateur.getId());
                
                } else {
                    
                    jdbcTemplate.update(prop.getProperty("QUERY_UPDATE_ORDINATEUR"), ordinateur.getName(), Date.valueOf(ordinateur.getDateIntroduit()), null, null, ordinateur.getId());
                
                }
                
            }

        } else {
            
            if(ordinateur.getDateInterrompu() != null) {
                
                if(ordinateur.getFabricant().isPresent()) {
            
                    jdbcTemplate.update(prop.getProperty("QUERY_UPDATE_ORDINATEUR"), ordinateur.getName(), null, Date.valueOf(ordinateur.getDateInterrompu()), ordinateur.getFabricant().get().getId(), ordinateur.getId());

                } else {
                    
                    jdbcTemplate.update(prop.getProperty("QUERY_UPDATE_ORDINATEUR"), ordinateur.getName(), null, Date.valueOf(ordinateur.getDateInterrompu()), null, ordinateur.getId());
                    
                }
                
            } else {
                
                if(ordinateur.getFabricant().isPresent()) {
                    
                    jdbcTemplate.update(prop.getProperty("QUERY_UPDATE_ORDINATEUR"), ordinateur.getName(), null, null, ordinateur.getFabricant().get().getId(), ordinateur.getId());

                } else {
                    
                    jdbcTemplate.update(prop.getProperty("QUERY_UPDATE_ORDINATEUR"), ordinateur.getName(), null, null, null, ordinateur.getId());
                    
                }
                
            }

        }
        
        LOGGER.info("update d'un ordinateur effectuée");

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
    public void suppressionOrdinateur(long id, JdbcTemplate jdbcTemplate)
            throws ConnexionDatabaseException, RequeteQueryException {

        LOGGER.info("suppression de l'ordinateur");
        LOGGER.debug("" + id);
        jdbcTemplate.update(prop.getProperty("QUERY_DELETE_ORDINATEUR"), id);

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
        JdbcTemplate jdbcTemplate = connexionDatabase.getJdbcTemplate();
        count = jdbcTemplate.queryForObject(prop.getProperty("QUERY_COUNT_ORDINATEUR"), Integer.class);
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
        JdbcTemplate jdbcTemplate = connexionDatabase.getJdbcTemplate();
        count = jdbcTemplate.queryForObject(prop.getProperty("QUERY_COUNT_ORDINATEUR_BY_NAME"), new Object[]{"%" + filtre + "%", "%" + filtre + "%"}, Integer.class);
        return count;

    }

}
