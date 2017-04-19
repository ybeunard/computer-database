package com.cdb.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdb.dao.InterfaceComputerDao;
import com.cdb.dao.Impl.Exception.EntityNotFoundException;
import com.cdb.dao.Impl.Exception.FilterException;
import com.cdb.dao.Impl.Exception.IdException;
import com.cdb.dao.Impl.Exception.PageException;
import com.cdb.model.entities.Computer;
import com.cdb.model.entities.QCompany;
import com.cdb.model.entities.QComputer;
import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

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
   *          the new session factory
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
   * Find computer by page.
   *
   * @param numPage
   *          the num page
   * @param rowByPage
   *          the row by page
   * @param sort
   *          the sort
   * @param desc
   *          the desc
   * @return the list
   */
  @Override
  public List<Computer> findComputerByPage(int numPage, int rowByPage, String sort, boolean desc) {

    if (numPage <= 0) {
      LOGGER.error("Current page = " + numPage);
      throw new PageException("Current page can't be a negative value.");
    }
    if (rowByPage <= 0) {
      LOGGER.error("Elements per page = " + rowByPage);
      throw new PageException("Elements per page can't be a negative value.");
    }

    LOGGER.info("Dao: search page of computer");
    List<Computer> computers = new ArrayList<Computer>();

    HibernateQuery<Computer> query = new HibernateQueryFactory(sessionFactory.openSession())
        .select(qComputer).from(qComputer).limit(rowByPage).offset((numPage - 1) * rowByPage);
    query = orderQuery(sort, desc, query);
    computers = query.fetch();

    if (computers.size() == 0) {
      throw new EntityNotFoundException("No computers found for current page = " + numPage + ", "
          + "elements per page = " + rowByPage + ".");
    }
    return computers;
  }

  /**
   * Find computer by name.
   *
   * @param numPage
   *          the num page
   * @param rowByPage
   *          the row by page
   * @param name
   *          the name
   * @param sort
   *          the sort
   * @param desc
   *          the desc
   * @return the list
   */
  @Override
  public List<Computer> findComputerByName(int numPage, int rowByPage, String name, String sort,
      boolean desc) {
    
    if(name == null){
      LOGGER.error("Filter seems to be null.");
      throw new FilterException("Filter seems to be null.");

    }
    
    if (numPage < 0) {
      LOGGER.error("Current page = " + numPage);
      throw new PageException("Current page can't be a negative value.");
    }
    if (rowByPage <= 0) {
      LOGGER.error("Elements per page = " + rowByPage);
      throw new PageException("Elements per page can't be a negative value.");
    }
    

    LOGGER.info("Dao: search page of computer sorted by name");
    List<Computer> computers = new ArrayList<Computer>();

    HibernateQuery<Computer> query = new HibernateQueryFactory(sessionFactory.openSession())
        .select(qComputer).from(qComputer).leftJoin(qComputer.company, qCompany).limit(rowByPage)
        .offset((numPage - 1) * rowByPage);
    query = orderQuery(sort, desc, query);
    computers = query.from(qComputer)
        .where(qComputer.name.like("%" + name + "%").or(qCompany.name.like("%" + name + "%")))
        .fetch();

    if (computers.size() == 0) {
      throw new EntityNotFoundException("No computers found for current page = " + numPage + ", "
          + "elements per page = " + rowByPage + ", filter = " + name + ".");
    }

    return computers;

  }

  /**
   * Find computer by id.
   *
   * @param id
   *          the id
   * @return the computer
   */
  @Override
  public Computer findComputerById(long id) {

    if (id <= 0) {
      throw new IdException("Id seems to be negative or equals to zero.");
    }

    LOGGER.info("Dao: search computer by id");
    HibernateQueryFactory query = new HibernateQueryFactory(sessionFactory.openSession());
    Computer computer = query.select(qComputer).from(qComputer).where(qComputer.id.eq(id))
        .fetchOne();

    if (computer == null) {
      throw new EntityNotFoundException("No computer found for id " + id + ".");
    }

    return computer;

  }

  /**
   * Creates the computer.
   *
   * @param computer
   *          the computer
   */
  @Override
  public void createComputer(Computer computer) {

    LOGGER.info("Dao: Create computer");
    LOGGER.error("" + computer.toString());
    Session session = this.sessionFactory.openSession();
    
    session.persist(computer);
    session.flush();
    session.close();
    LOGGER.info("Dao: create computer succeed");

  }

  /**
   * Update computer.
   *
   * @param computer
   *          the computer
   */
  @Override
  public void updateComputer(Computer computer) {

    LOGGER.info("Dao: update computer");
    LOGGER.debug("" + computer);
    HibernateQueryFactory query = new HibernateQueryFactory(sessionFactory.openSession());

    query.update(qComputer).where(qComputer.id.eq(computer.getId()))
        .set(qComputer.name, computer.getName()).set(qComputer.introduced, computer.getIntroduced())
        .set(qComputer.discontinued, computer.getDiscontinued())
        .set(qComputer.company, computer.getCompany()).execute();
    LOGGER.info("Dao: update computer succeed");

  }

  @Override
  public void deleteComputer(long id) {

    if (id <= 0) {
      throw new IdException("Id seems to be negative or equals to zero.");
    }
    LOGGER.info("Dao: delete computer");
    LOGGER.debug("" + id);
    HibernateQueryFactory query = new HibernateQueryFactory(sessionFactory.openSession());
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
    HibernateQueryFactory query = new HibernateQueryFactory(sessionFactory.openSession());
    count = query.from(qComputer).fetchCount();
    return count;

  }

  /**
   * Count computer by name.
   *
   * @param filter
   *          the filter
   * @return the long
   */
  @Override
  public long countComputerByName(String filter) {

    LOGGER.info("Dao: count computer sorted by name");
    long count = 0;
    HibernateQueryFactory query = new HibernateQueryFactory(sessionFactory.openSession());
    count = query.from(qComputer).leftJoin(qComputer.company, qCompany)
        .where(qComputer.name.like("%" + filter + "%").or(qCompany.name.like("%" + filter + "%")))
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

          case "introduced":
              
              if (desc) {

                  query = query.leftJoin(qComputer.company, qCompany)
                          .orderBy(qComputer.introduced.desc());

              } else {

                  query = query.leftJoin(qComputer.company, qCompany)
                          .orderBy(qComputer.introduced.asc());

              }
              break;
              
          case "discontinued":
              
              if (desc) {

                  query = query.leftJoin(qComputer.company, qCompany)
                          .orderBy(qComputer.discontinued.desc());

              } else {

                  query = query.leftJoin(qComputer.company, qCompany)
                          .orderBy(qComputer.discontinued.asc());

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
