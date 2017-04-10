package com.cdb.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdb.dao.InterfaceComputerDao;
import com.cdb.model.entities.Computer;
import com.cdb.model.entities.QCompany;
import com.cdb.model.entities.QComputer;
import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDao.
 */
public class ComputerDao implements InterfaceComputerDao {

    /** The Constant logger. */
    public final Logger LOGGER = LoggerFactory.getLogger(ComputerDao.class);

    /** The session factory. */
    private SessionFactory sessionFactory;

    /**
     * Sets the session factory.
     *
     * @param sessionFactory
     *            the new session factory
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
        LOGGER.info("session factory Instantiated");

    }

    /** The ordinateur. */
    QComputer qComputer;

    /** The entreprise. */
    QCompany qCompany;

    /**
     * Instantiates a new ordinateur dao.
     */
    public ComputerDao() {

        qComputer = QComputer.computer;
        qCompany = QCompany.company;
        LOGGER.info("ComputerDao Instantiated");

    }

    /**
     * Find computers.
     *
     * @return the list
     */
    @Override
    public List<Computer> findComputers() {

        LOGGER.info("Dao: search all computers");
        List<Computer> computers = new ArrayList<Computer>();
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        computers = query.select(qComputer).from(qComputer).fetch();
        return computers;

    }

    /**
     * Find computer by page.
     *
     * @param numPage
     *            the num page
     * @param rowByPage
     *            the row by page
     * @param sort
     *            the sort
     * @param desc
     *            the desc
     * @return the list
     */
    @Override
    public List<Computer> findComputerByPage(int numPage, int rowByPage,
            String sort, boolean desc) {

        LOGGER.info("Dao: search page of computer");
        List<Computer> computers = new ArrayList<Computer>();
        int limit = rowByPage;
        int offset = (numPage - 1) * rowByPage;

        if (offset < 0) {

            return computers;

        }

        HibernateQuery<Computer> query = new HibernateQueryFactory(
                sessionFactory.openSession()).select(qComputer).from(qComputer)
                        .limit(limit).offset(offset);
        query = orderQuery(sort, desc, query);
        computers = query.fetch();
        return computers;

    }

    /**
     * Find computer by name.
     *
     * @param numPage
     *            the num page
     * @param rowByPage
     *            the row by page
     * @param name
     *            the name
     * @param sort
     *            the sort
     * @param desc
     *            the desc
     * @return the list
     */
    @Override
    public List<Computer> findComputerByName(int numPage, int rowByPage,
            String name, String sort, boolean desc) {

        LOGGER.info("Dao: search page of computer sorted by name");
        List<Computer> computers = new ArrayList<Computer>();
        int limit = rowByPage;
        int offset = (numPage - 1) * rowByPage;

        if (offset < 0) {

            return computers;

        }

        HibernateQuery<Computer> query = new HibernateQueryFactory(
                sessionFactory.openSession()).select(qComputer).from(qComputer)
                        .leftJoin(qComputer.company, qCompany).limit(limit)
                        .offset(offset);
        query = orderQuery(sort, desc, query);
        computers = query.from(qComputer)
                .where(qComputer.name.like("%" + name + "%")
                        .or(qCompany.name.like("%" + name + "%")))
                .fetch();
        return computers;

    }

    /**
     * Find computer by id.
     *
     * @param id
     *            the id
     * @return the computer
     */
    @Override
    public Computer findComputerById(long id) {

        LOGGER.info("Dao: search computer by id");
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        return query.select(qComputer).from(qComputer)
                .where(qComputer.id.eq(id)).fetchOne();

    }

    /**
     * Creates the computer.
     *
     * @param computer
     *            the computer
     */
    @Override
    public void createComputer(Computer computer) {

        LOGGER.info("Dao: Create computer");
        LOGGER.debug("" + computer);
        Session session = this.sessionFactory.openSession();
        session.save(computer);
        session.close();
        LOGGER.info("Dao: create computer succeed");

    }

    /**
     * Update computer.
     *
     * @param computer
     *            the computer
     */
    @Override
    public void updateComputer(Computer computer) {

        LOGGER.info("Dao: update computer");
        LOGGER.debug("" + computer);
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        query.update(qComputer).where(qComputer.id.eq(computer.getId()))
                .set(qComputer.name, computer.getName())
                .set(qComputer.introduced, computer.getIntroduced())
                .set(qComputer.discontinued, computer.getDiscontinued())
                .set(qComputer.company, computer.getCompany()).execute();
        LOGGER.info("Dao: update computer succeed");

    }

    /**
     * Delete computer.
     *
     * @param id
     *            the id
     */
    @Override
    public void deleteComputer(long id) {

        LOGGER.info("Dao: delete computer");
        LOGGER.debug("" + id);
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        query.delete(qComputer).where(qComputer.id.eq(id)).execute();
        LOGGER.info("Dao: delete computer succeed");

    }

    /**
     * Count computer.
     *
     * @return the long
     */
    @Override
    public long countComputer() {

        LOGGER.info("Dao: count computer");
        long count = 0;
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        count = query.from(qComputer).fetchCount();
        return count;

    }

    /**
     * Count computer by name.
     *
     * @param filter
     *            the filter
     * @return the long
     */
    @Override
    public long countComputerByName(String filter) {

        LOGGER.info("Dao: count computer sorted by name");
        long count = 0;
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        count = query.from(qComputer).leftJoin(qComputer.company, qCompany)
                .where(qComputer.name.like("%" + filter + "%")
                        .or(qCompany.name.like("%" + filter + "%")))
                .fetchCount();
        return count;

    }

    /**
     * Order query.
     *
     * @param trie
     *            the trie
     * @param desc
     *            the desc
     * @param query
     *            the query
     * @return the hibernate query
     */
    private HibernateQuery<Computer> orderQuery(String trie, boolean desc,
            HibernateQuery<Computer> query) {

        if (trie != null) {

            switch (trie) {

            case "company_name":

                if (desc) {

                    query = query.leftJoin(qComputer.company, qCompany)
                            .orderBy(qCompany.name.desc());

                } else {

                    query = query.leftJoin(qComputer.company, qCompany)
                            .orderBy(qCompany.name.asc());

                }
                break;

            default:

                if (desc) {

                    query = query.orderBy(qComputer.name.desc());

                } else {

                    query = query.orderBy(qComputer.name.asc());

                }

            }

        } else {

            query = query.orderBy(qComputer.name.asc());

        }

        return query;
    }

}
