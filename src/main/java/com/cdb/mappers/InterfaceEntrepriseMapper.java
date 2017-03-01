/*
 * 
 */
package com.cdb.mappers;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import com.cdb.entities.Entreprise;
import com.cdb.exception.RequeteQueryException;

/**
 * The Interface InterfaceEntrepriseMapper.
 */
public interface InterfaceEntrepriseMapper {

    /**
     * Recupertation resultat requete.
     *
     * @param res
     *            the res
     * @return the optional
     * @throws RequeteQueryException
     *             the requete query exception
     */
    Optional<Entreprise> recupertationResultatRequete(ResultSet res)
            throws RequeteQueryException;

    /**
     * Recuperation list resultat requete.
     *
     * @param res
     *            the res
     * @return the optional
     * @throws RequeteQueryException
     *             the requete query exception
     */
    Optional<List<Optional<Entreprise>>> recuperationListResultatRequete(
            ResultSet res) throws RequeteQueryException;

}
