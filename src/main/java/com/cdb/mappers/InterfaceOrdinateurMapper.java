/*
 * 
 */
package com.cdb.mappers;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import com.cdb.entities.Ordinateur;
import com.cdb.exception.RequeteQueryException;

/**
 * The Interface InterfaceOrdinateurMapper.
 */
public interface InterfaceOrdinateurMapper {

    /**
     * Recuperation resultat requete.
     *
     * @param res
     *            the res
     * @return the optional
     * @throws RequeteQueryException
     *             the requete query exception
     */
    Optional<Ordinateur> recuperationResultatRequete(ResultSet res)
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
    Optional<List<Optional<Ordinateur>>> recuperationListResultatRequete(
            ResultSet res) throws RequeteQueryException;

    /**
     * Recuperation int resultat requete.
     *
     * @param res
     *            the res
     * @return the int
     * @throws RequeteQueryException
     *             the requete query exception
     */
    int recuperationIntResultatRequete(ResultSet res)
            throws RequeteQueryException;

}
