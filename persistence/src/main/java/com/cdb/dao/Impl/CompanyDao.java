package com.cdb.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdb.dao.InterfaceCompanyDao;
import com.cdb.dao.Impl.Exception.EntityNotFoundException;
import com.cdb.dao.Impl.Exception.IdException;
import com.cdb.dao.Impl.Exception.PageException;
import com.cdb.model.entities.Company;
import com.cdb.model.entities.QCompany;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

/**
 * The Class CompanyDao.
 */
public class CompanyDao implements InterfaceCompanyDao {

  /** The Constant LOGGER. */
  public final Logger LOGGER = LoggerFactory.getLogger(CompanyDao.class);

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

  /** The entreprise. */
  QCompany qCompany;

  /**
   * Instantiates a new entreprise dao.
   */
  CompanyDao() {

    qCompany = QCompany.company;
    LOGGER.info("CompanyDao Instantiated");

  }

  /**
   * Find companies.
   *
   * @return the list
   */
  @Override
  public List<Company> findCompanies() {

    LOGGER.info("Dao: search all companies");
    List<Company> companies = new ArrayList<Company>();
    HibernateQueryFactory query = new HibernateQueryFactory(sessionFactory.openSession());
    companies = query.select(qCompany).from(qCompany).fetch();
    
    if(companies.size() == 0){
      throw new EntityNotFoundException("Company table seems to be empty.");
    }
    
    return companies;

  }

  @Override
  public List<Company> findCompanyByPage(int numPage, int rowByPage) {

    if(numPage < 0){
      LOGGER.error("Current page = " + numPage);
      throw new PageException("Current page can't be a negative value.");
    }
    
    if(rowByPage < 0){
      LOGGER.error("Elements per page = " + rowByPage);
      throw new PageException("Elements per page can't be a negative value.");
    }
    
    LOGGER.info("Dao: search page of company");
    List<Company> companies = new ArrayList<Company>();
    int limit = rowByPage;
    int offset = (numPage - 1) * rowByPage;

    if (offset < 0) {
      return companies;
    }

    if (limit <= 0) {
      return companies;
    }

    companies = new HibernateQueryFactory(sessionFactory.openSession()).select(qCompany)
        .from(qCompany).limit(limit).offset(offset).fetch();
    
    if(companies.size() == 0){
      throw new EntityNotFoundException("Companies not found for current page = " + numPage + ", "
          + "elements per page = " + rowByPage + ".");
    }
    
    return companies;

  }

  @Override
  public List<Company> findCompanyByName(int numPage, int rowByPage, String name) {
    
    if(numPage < 0){
      LOGGER.error("Current page = " + numPage);
      throw new PageException("Current page can't be a negative value.");
    }
    
    if(rowByPage < 0){
      LOGGER.error("Elements per page = " + rowByPage);
      throw new PageException("Elements per page can't be a negative value.");
    }
    
    LOGGER.info("Dao: search page of company sorted by name");
    List<Company> companies = new ArrayList<Company>();
    int limit = rowByPage;
    int offset = (numPage - 1) * rowByPage;

    if (offset < 0) {

      return companies;

    }

    companies = new HibernateQueryFactory(sessionFactory.openSession()).select(qCompany)
        .from(qCompany).limit(limit).offset(offset).where(qCompany.name.like("%" + name + "%"))
        .fetch();
    
    if(companies.size() == 0){
      throw new EntityNotFoundException("No Companies found for current page = " + numPage + ", "
          + "elements per page = " + rowByPage + ", filter = " + name + ".");
    }
    
    return companies;

  }
  
    @Override
    public List<Company> findCompanyByFilter(String name) {

        LOGGER.info("Dao: search company sorted by name");
        List<Company> companies = new ArrayList<Company>();

        companies = new HibernateQueryFactory(sessionFactory.openSession())
                .select(qCompany).from(qCompany)
                .where(qCompany.name.like("%" + name + "%")).fetch();
        return companies;

    }

    
    
    /**
     * Find company by ID.
     *
     * @param id
     *            the id
     * @return the company
     */
    @Override
    public Company findCompanyByID(long id) {

        LOGGER.info("Dao: search company by id");
        if (id <= 0) {
            throw new IdException("Id seems to be negative or equals to zero.");
          }
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        Company company = query.select(qCompany).from(qCompany).where(qCompany.id.eq(id))
                .fetchOne();

    if (company == null) {

      throw new EntityNotFoundException("No company found for id " + id + ".");

    }

    return company;

  }

  @Override
  public void deleteCompany(long id) {
    
    if (id <= 0) {
      throw new IdException("Id seems to be negative or equals to zero.");
    }

    LOGGER.info("Dao: delete company");
    LOGGER.debug("" + id);
    HibernateQueryFactory query = new HibernateQueryFactory(sessionFactory.openSession());
    query.delete(qCompany).where(qCompany.id.eq(id)).execute();
    LOGGER.info("Dao: delete company succeed");

  }

  @Override
  public long countCompany() {

    LOGGER.info("Dao: count company");
    long count = 0;
    HibernateQueryFactory query = new HibernateQueryFactory(sessionFactory.openSession());
    count = query.from(qCompany).fetchCount();
    return count;

  }

  @Override
  public long countCompanyByName(String filter) {

    LOGGER.info("Dao: count company sorted by name");
    long count = 0;
    HibernateQueryFactory query = new HibernateQueryFactory(sessionFactory.openSession());
    count = query.from(qCompany).where(qCompany.name.like("%" + filter + "%")).fetchCount();
    return count;

  }

}
