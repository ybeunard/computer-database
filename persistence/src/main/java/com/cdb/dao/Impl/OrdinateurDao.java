package com.cdb.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.cdb.dao.InterfaceOrdinateurDao;
import com.cdb.model.entities.Ordinateur;
import com.cdb.model.entities.QEntreprise;
import com.cdb.model.entities.QOrdinateur;
import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

/**
 * The Enum OrdinateurDao.
 */
public class OrdinateurDao implements InterfaceOrdinateurDao {

    /** The Constant logger. */
    public final Logger LOGGER = LoggerFactory.getLogger(OrdinateurDao.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        LOGGER.info("session factory instancié");
    }

    QOrdinateur ordinateur;
    QEntreprise entreprise;

    /**
     * Instantiates a new ordinateur dao.
     */
    public OrdinateurDao() {

        ordinateur = QOrdinateur.ordinateur;
        entreprise = QEntreprise.entreprise;
        LOGGER.info("OrdinateurDao instancié");

    }

    /**
     * Find ordinateur.
     *
     * @return une liste d'ordinateur
     * @throws DataAccessException
     *             the data access exception
     */
    public List<Ordinateur> findOrdinateur() {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        ordinateurs = query.select(ordinateur).from(ordinateur).fetch();
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
     * @throws DataAccessException
     *             the data access exception
     */
    public List<Ordinateur> findOrdinateurByPage(int numeroPage,
            int ligneParPage, String trie, boolean desc) {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        int limit = ligneParPage;
        int offset = (numeroPage - 1) * ligneParPage;

        if (offset < 0) {

            return ordinateurs;

        }

        HibernateQuery<Ordinateur> query = new HibernateQueryFactory(
                sessionFactory.openSession()).select(ordinateur)
                        .from(ordinateur).limit(limit).offset(offset);
        query = orderQuery(trie, desc, query);
        ordinateurs = query.fetch();
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
     * @throws DataAccessException
     *             the data access exception
     */
    public List<Ordinateur> findOrdinateurByName(int numeroPage,
            int ligneParPage, String name, String trie, boolean desc) {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        int limit = ligneParPage;
        int offset = (numeroPage - 1) * ligneParPage;

        if (offset < 0) {

            return ordinateurs;

        }

        HibernateQuery<Ordinateur> query = new HibernateQueryFactory(
                sessionFactory.openSession()).select(ordinateur)
                        .from(ordinateur)
                        .leftJoin(ordinateur.fabricant, entreprise).limit(limit)
                        .offset(offset);
        query = orderQuery(trie, desc, query);
        ordinateurs = query.from(ordinateur)
                .where(ordinateur.name.like("%" + name + "%")
                        .or(entreprise.name.like("%" + name + "%")))
                .fetch();
        return ordinateurs;

    }

    /**
     * Find ordinateur by id.
     *
     * @param id
     *            the id
     * @return the optional
     * @throws DataAccessException
     *             the data access exception
     * @throws EmptyResultDataAccessException
     *             the empty result data access exception
     */
    public Ordinateur findOrdinateurById(long id) {

        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        return query.select(ordinateur)
                .from(ordinateur).where(ordinateur.id.eq(id)).fetchOne();

    }

    /**
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            à créer
     * @param jdbcTemplate
     *            the jdbc template
     * @throws DataAccessException
     *             the data access exception
     */
    public void createOrdinateur(Ordinateur ordinateur)
            throws DataAccessException {

        LOGGER.info("Dao: Création d'un ordinateur");
        LOGGER.debug("" + ordinateur);
        Session session = this.sessionFactory.openSession();
        session.save(ordinateur);
        session.close();
        LOGGER.info("Dao: Creation d'un ordinateur effectuée");

    }

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            à update
     * @param jdbcTemplate
     *            the jdbc template
     * @throws DataAccessException
     *             the data access exception
     */
    public void updateOrdinateur(Ordinateur ordinateurUpdate) {

        LOGGER.info("Dao: update d'un ordinateur");
        LOGGER.debug("" + ordinateur);
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        query.update(ordinateur)
                .where(ordinateur.id.eq(ordinateurUpdate.getId()))
                .set(ordinateur.name, ordinateurUpdate.getName())
                .set(ordinateur.introduced, ordinateurUpdate.getIntroduced())
                .set(ordinateur.discontinued,
                        ordinateurUpdate.getDiscontinued())
                .set(ordinateur.fabricant, ordinateurUpdate.getFabricant()).execute();
        LOGGER.info("Dao: update d'un ordinateur effectuée");

    }

    /**
     * Suppression ordinateur.
     *
     * @param id
     *            Identifiant de l'ordinateur à supprimer
     * @param jdbcTemplate
     *            the jdbc template
     * @throws DataAccessException
     *             the data access exception
     */
    public void suppressionOrdinateur(long id) {

        LOGGER.info("suppression de l'ordinateur");
        LOGGER.debug("" + id);
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        query.delete(ordinateur).where(ordinateur.id.eq(id)).execute();

    }

    /**
     * Count ordinateur.
     *
     * @return le nombre d'ordinateur total
     * @throws DataAccessException
     *             the data access exception
     */
    public long countOrdinateur() throws DataAccessException {

        long count = 0;
        LOGGER.info("Dao: Comptage du nombre d'ordinateur");
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        count = query.from(ordinateur).fetchCount();
        return count;

    }

    /**
     * Count ordinateur by name.
     *
     * @param filtre
     *            the filtre
     * @return the int
     * @throws DataAccessException
     *             the data access exception
     */
    public long countOrdinateurByName(String filtre) throws DataAccessException {

        long count = 0;
        LOGGER.info("Dao: Comptage du nombre d'ordinateur");
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        count = query.from(ordinateur).leftJoin(ordinateur.fabricant, entreprise).where(ordinateur.name.like("%" + filtre + "%")
                .or(entreprise.name.like("%" + filtre + "%"))).fetchCount();
        return count;

    }

    private HibernateQuery<Ordinateur> orderQuery(String trie, boolean desc,
            HibernateQuery<Ordinateur> query) {

        if (trie != null) {

            switch (trie) {

            case "company_name":

                if (desc) {

                    query = query.leftJoin(ordinateur.fabricant, entreprise)
                            .orderBy(entreprise.name.desc());

                } else {

                    query = query.leftJoin(ordinateur.fabricant, entreprise)
                            .orderBy(entreprise.name.asc());

                }
                break;

            default:

                if (desc) {

                    query = query.orderBy(ordinateur.name.desc());

                } else {

                    query = query.orderBy(ordinateur.name.asc());

                }

            }

        } else {

            query = query.orderBy(ordinateur.name.asc());

        }

        return query;
    }

}
