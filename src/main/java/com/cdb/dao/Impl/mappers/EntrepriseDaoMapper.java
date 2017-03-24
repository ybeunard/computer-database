package com.cdb.dao.Impl.mappers;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.model.entities.Entreprise;

/**
 * The Class EntrepriseDaoMapper.
 */
public class EntrepriseDaoMapper
        implements RowMapper<Entreprise>, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /** The Constant logger. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(OrdinateurDao.class);

    /**
     * Map Entreprise.
     *
     * @param res
     *            the result set
     * @param arg
     *            the arg
     * @return entreprise
     * @throws SQLException
     *             if there is an issue
     */
    @Override
    public Entreprise mapRow(ResultSet res, int arg) throws SQLException {

        LOGGER.debug("Mapping entreprise depuis resultSet");
        long id = res.getLong("id");
        String name = res.getString("name");
        return new Entreprise.EntrepriseBuilder(name).id(id).build();

    }

}
