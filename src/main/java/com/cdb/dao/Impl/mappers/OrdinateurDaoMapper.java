package com.cdb.dao.Impl.mappers;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.cdb.model.entities.Entreprise;
import com.cdb.model.entities.Ordinateur;
import com.cdb.model.entities.Ordinateur.OrdinateurBuilder;

/**
 * The Class OrdinateurDaoMapper.
 */
public class OrdinateurDaoMapper
        implements RowMapper<Ordinateur>, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** The Constant logger. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(OrdinateurDaoMapper.class);

    /**
     * Map Ordinateur.
     *
     * @param res
     *            the result set
     * @param arg
     *            the arg
     * @return ordinateur
     * @throws SQLException
     *             if there is an issue
     */
    @Override
    public Ordinateur mapRow(ResultSet res, int arg) throws SQLException {

        Ordinateur ordinateur = null;
        LOGGER.debug("Mapping ordinateur depuis resultSet");
        String name = res.getString("name");
        OrdinateurBuilder builder = new Ordinateur.OrdinateurBuilder(name);
        long id = res.getLong("id");
        builder.id(id);
        Date date = null;
        date = (java.sql.Date) res.getDate("introduced");

        if (date != null) {

            builder.dateIntroduit(date.toLocalDate());

        }

        date = (java.sql.Date) res.getDate("discontinued");

        if (date != null) {

            builder.dateInterrompu(date.toLocalDate());

        }

        long fabricantID = res.getLong("company_id");

        if (fabricantID > 0) {

            String fabricantName = res.getString("company_name");
            builder.fabricant(Optional
                    .ofNullable(new Entreprise.EntrepriseBuilder(fabricantName)
                            .id(fabricantID).build()));

        }

        ordinateur = builder.build();
        return ordinateur;

    }

}
