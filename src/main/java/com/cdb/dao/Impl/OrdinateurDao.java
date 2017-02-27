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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.InstanceOrdinateurDao;
import com.cdb.entities.Entreprise;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

// TODO: Auto-generated Javadoc
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
     * @param ordinateur            à créer
     * @throws ConnexionDatabaseException             if there is an issue
     * @throws RequeteQueryException             if there is an issue
     */
    public void createOrdinateur(Ordinateur ordinateur)
            throws ConnexionDatabaseException, RequeteQueryException {

        Connection con = ConnexionDatabase.getInstanceConnexionDatabase()
                .connectDatabase();
        PreparedStatement requete = null;
        LOGGER.info("Création d'un " + ordinateur);

        try {

            requete = con.prepareStatement(
                    prop.getProperty("QUERY_INSERT_ORDINATEUR"));
            requete.setString(1, ordinateur.getName());

            if (ordinateur.getDateIntroduit() != null) {

                requete.setDate(2, Date.valueOf(ordinateur.getDateIntroduit()));

            } else {

                requete.setDate(2, null);

            }
            if (ordinateur.getDateInterrompu() != null) {

                requete.setDate(3,
                        Date.valueOf(ordinateur.getDateInterrompu()));

            } else {

                requete.setDate(3, null);

            }
            if (ordinateur.getFabricant() != null) {

                requete.setLong(4, ordinateur.getFabricant().getId());

            } else {

                requete.setString(4, null);

            }

            requete.executeUpdate();
            LOGGER.info("Creation d'un ordinateur effectuée");

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

            ConnexionDatabase.getInstanceConnexionDatabase()
                    .closeConnexionDatabase(con);

        }

    }

    /**
     * Find ordinateur.
     *
     * @return une liste d'ordinateur
     * @throws ConnexionDatabaseException             if there is an issue
     * @throws RequeteQueryException             if there is an issue
     */
    public List<Ordinateur> findOrdinateur()
            throws ConnexionDatabaseException, RequeteQueryException {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();

        Connection con = ConnexionDatabase.getInstanceConnexionDatabase()
                .connectDatabase();
        Statement stmt = null;
        LOGGER.info("recherche de la liste d'ordinateur");

        try {

            stmt = con.createStatement();
            ResultSet rset = stmt
                    .executeQuery(prop.getProperty("QUERY_FIND_ORDINATEURS"));
            ordinateurs = recuperationResultatRequete(rset);
            LOGGER.info("recherche de la liste d'ordinateur effectuée");

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

            ConnexionDatabase.getInstanceConnexionDatabase()
                    .closeConnexionDatabase(con);

        }

        return ordinateurs;

    }

    /**
     * Find ordinateur by page.
     *
     * @param numeroPage            le numero de la page
     * @param ligneParPage            le nombre de ligne par page
     * @return une liste d'ordinateur
     * @throws ConnexionDatabaseException             if there is an issue
     * @throws RequeteQueryException             if there is an issue
     */
    public List<Ordinateur> findOrdinateurByPage(int numeroPage,
            int ligneParPage)
            throws ConnexionDatabaseException, RequeteQueryException {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        int limit = ligneParPage;
        int offset = (numeroPage - 1) * ligneParPage;
        Connection con = ConnexionDatabase.getInstanceConnexionDatabase()
                .connectDatabase();
        PreparedStatement requete = null;
        LOGGER.info("recherche de la liste d'ordinateur par page");

        try {

            requete = con.prepareStatement(
                    prop.getProperty("QUERY_FIND_ORDINATEURS_BY_PAGE"));
            requete.setInt(1, limit);
            requete.setInt(2, offset);
            ResultSet res = requete.executeQuery();
            ordinateurs = recuperationResultatRequete(res);
            LOGGER.info(
                    "recherche de la liste d'ordinateur par page effectuée");

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

            ConnexionDatabase.getInstanceConnexionDatabase()
                    .closeConnexionDatabase(con);

        }

        return ordinateurs;

    }

    /**
     * Find ordinateur by ID.
     *
     * @param index            l'identifiant de l'ordinateur recherché
     * @return un ordinateur
     * @throws ConnexionDatabaseException             if there is an issue
     * @throws RequeteQueryException             if there is an issue
     */
    public Ordinateur findOrdinateurByID(long index)
            throws ConnexionDatabaseException, RequeteQueryException {

        Ordinateur ordinateur = null;
        Connection con = ConnexionDatabase.getInstanceConnexionDatabase()
                .connectDatabase();
        PreparedStatement requete = null;
        LOGGER.info("recherche d'un ordinateur par id: " + index);

        try {

            requete = con.prepareStatement(
                    prop.getProperty("QUERY_FIND_ORDINATEURS_BY_ID"));
            requete.setLong(1, index);
            ResultSet res = requete.executeQuery();

            if (res.next()) {

                long id = res.getInt("id");
                String name = res.getString("name");
                LocalDate dateIntroduit = null;
                LocalDate dateInterrompu = null;
                Integer fabricantID = res.getInt("company_id");
                String fabricantName = res.getString("company_name");
                Date date = null;

                try {

                    date = res.getDate("introduced");

                    if (date != null) {

                        dateIntroduit = date.toLocalDate();

                    }

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Recuperation de la date d'introduction impossible");

                }
                try {

                    date = res.getDate("discontinued");

                    if (date != null) {

                        dateInterrompu = date.toLocalDate();

                    }

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Recuperation de la date d'interruption impossible");

                }

                if (fabricantID == 0) {

                    ordinateur = new Ordinateur(id, name, dateIntroduit,
                            dateInterrompu, null);

                } else {

                    ordinateur = new Ordinateur(id, name, dateIntroduit,
                            dateInterrompu,
                            new Entreprise(fabricantID, fabricantName));

                }

            }

            LOGGER.info("recherche d'un ordinateur par id effectuée");

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

            ConnexionDatabase.getInstanceConnexionDatabase()
                    .closeConnexionDatabase(con);

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
    public void updateOrdinateur(final Ordinateur ordinateur)
            throws ConnexionDatabaseException, RequeteQueryException {

        Connection con = ConnexionDatabase.getInstanceConnexionDatabase()
                .connectDatabase();
        PreparedStatement requete = null;
        LOGGER.info("update d'un " + ordinateur);

        try {

            requete = con.prepareStatement(
                    prop.getProperty("QUERY_UPDATE_ORDINATEUR"));
            requete.setString(1, ordinateur.getName());

            if (ordinateur.getDateIntroduit() != null) {

                requete.setDate(2, Date.valueOf(ordinateur.getDateIntroduit()));

            } else {

                requete.setDate(2, null);

            }
            if (ordinateur.getDateInterrompu() != null) {

                requete.setDate(3,
                        Date.valueOf(ordinateur.getDateInterrompu()));

            } else {

                requete.setDate(3, null);

            }
            if (ordinateur.getFabricant() != null) {

                requete.setLong(4, ordinateur.getFabricant().getId());

            } else {

                requete.setString(4, null);

            }

            requete.setLong(5, ordinateur.getId());
            requete.executeUpdate();

            LOGGER.info("update d'un ordinateur effectuée");

        } catch (SQLException e) {

            throw new RequeteQueryException("Echec de la requete de mise à "
                    + "jour de l'" + ordinateur);

        } finally {

            if (requete != null) {

                try {

                    requete.close();

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Fermeture de la requete de mise "
                                    + "à jour d'ordinateur impossible");

                }

            }

            ConnexionDatabase.getInstanceConnexionDatabase()
                    .closeConnexionDatabase(con);

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
    public void suppressionOrdinateur(final long id)
            throws ConnexionDatabaseException, RequeteQueryException {

        Connection con = ConnexionDatabase.getInstanceConnexionDatabase()
                .connectDatabase();
        PreparedStatement requete = null;
        LOGGER.info("suppression de l'ordinateur numero " + id);
        try {

            requete = con.prepareStatement(
                    prop.getProperty("QUERY_DELETE_ORDINATEUR"));
            requete.setLong(1, id);
            requete.executeUpdate();
            LOGGER.info("suppression d'un ordinateur effectuée");

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

            ConnexionDatabase.getInstanceConnexionDatabase()
                    .closeConnexionDatabase(con);

        }
    }

    /**
     * Recuperation resultat requete.
     *
     * @param res
     *            the res
     * @return the list
     * @throws RequeteQueryException
     *             the requete query exception
     */
    private List<Ordinateur> recuperationResultatRequete(final ResultSet res)
            throws RequeteQueryException {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        Ordinateur ordinateur;

        try {

            while (res.next()) {

                long id = res.getLong("id");
                String name = res.getString("name");
                LocalDate dateIntroduit = null;
                LocalDate dateInterrompu = null;
                long fabricantID = res.getLong("company_id");
                String fabricantName = res.getString("company_name");
                Date date = null;

                try {

                    date = (java.sql.Date) res.getDate("introduced");

                    if (date != null) {

                        dateIntroduit = date.toLocalDate();

                    }

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Recuperation de la date d'introduction impossible");

                }
                try {

                    date = res.getDate("discontinued");

                    if (date != null) {

                        dateIntroduit = date.toLocalDate();

                    }

                } catch (SQLException e) {

                    throw new RequeteQueryException(
                            "Recuperation de la date d'interruption impossible");

                }

                if (fabricantID == 0) {

                    ordinateur = new Ordinateur(id, name, dateIntroduit,
                            dateInterrompu, null);

                } else {

                    ordinateur = new Ordinateur(id, name, dateIntroduit,
                            dateInterrompu,
                            new Entreprise(fabricantID, fabricantName));

                }

                ordinateurs.add(ordinateur);

            }

        } catch (SQLException e) {

            throw new RequeteQueryException(
                    "L'extraction des données du résultat de la requete ne s'est pas déroulé correctement");

        }

        return ordinateurs;

    }

}
