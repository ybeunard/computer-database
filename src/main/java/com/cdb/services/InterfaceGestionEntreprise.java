package com.cdb.services;

import com.cdb.dto.EntrepriseDto;
import java.util.List;

/**
 * The Interface InterfaceGestionEntreprise.
 */
public interface InterfaceGestionEntreprise {

    /**
     * Find entreprise.
     *
     * @return the list
     */
    List<EntrepriseDto> findEntreprise();

}
