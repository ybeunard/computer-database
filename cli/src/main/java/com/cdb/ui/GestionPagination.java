package com.cdb.ui;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cdb.model.dto.CompanyDto;
import com.cdb.model.dto.ComputerDto;
import com.cdb.model.dto.PageDto;
import com.cdb.services.Impl.CompanyService;
import com.cdb.services.Impl.ComputerService;

/**
 * The Class GestionPagination.
 */
public class GestionPagination {

    /** The numero page. */
    private int numeroPage;

    /** The ligne par page. */
    private int ligneParPage;

    /** The page ordinateur. */
    private PageDto pageOrdinateur;

    /** The page entreprise. */
    private List<CompanyDto> pageEntreprise;

    private ApplicationContext context = new ClassPathXmlApplicationContext(
            "springConfig.xml");
    private ComputerService computerService = (ComputerService) context
            .getBean("ComputerService");
    private CompanyService companyService = (CompanyService) context
            .getBean("CompanyService");

    /**
     * Instantiates a new gestion pagination.
     */
    public GestionPagination() {

        this.numeroPage = 1;
        this.ligneParPage = 100;

    }

    /**
     * Instantiates a new gestion pagination.
     *
     * @param ligneParPage
     *            the ligne par page
     */
    public GestionPagination(int ligneParPage) {

        this.numeroPage = 1;

        if (ligneParPage > 0) {

            this.ligneParPage = ligneParPage;

        } else {

            this.ligneParPage = 100;

        }

    }

    /**
     * Pagination.
     *
     * @param typePage
     *            indique si la page est une page entreprise ou ordinateur
     */
    public void pagination(int typePage) {

        do {

            switch (typePage) {

            case 1:

                pageOrdinateur = computerService.findComputerByPage(
                        numeroPage, ligneParPage, "", "", false);

                if (pageOrdinateur.getContent().isEmpty()) {

                    numeroPage--;
                    pageOrdinateur = computerService.findComputerByPage(
                            numeroPage, ligneParPage, "", "", false);

                }

                affichagePage(typePage);
                break;

            case 2:

                pageEntreprise = companyService.findCompanies();

                if (pageEntreprise.isEmpty()) {

                    numeroPage--;
                    pageEntreprise = companyService.findCompanies();

                }

                affichagePage(typePage);
                break;

            default:
                System.out.println("Type de page inconnu");
                return;

            }

        } while (changementPage());

    }

    /**
     * Changement page.
     *
     * @return true, if successful
     */
    private boolean changementPage() {

        String arg = UserInterpreter.SCANNER.nextLine();

        if (arg == "b") {

            if (numeroPage > 1) {

                numeroPage--;

            }

            return true;

        } else if (arg == "n") {

            numeroPage++;
            return true;

        }

        return false;

    }

    /**
     * Affichage page.
     *
     * @param typePage
     *            the type page
     */
    private void affichagePage(int typePage) {

        switch (typePage) {

        case 1:

            for (ComputerDto ordinateur : pageOrdinateur.getContent()) {

                System.out.println(ordinateur);

            }

            break;

        case 2:

            for (CompanyDto entreprise : pageEntreprise) {

                System.out.println(entreprise);

            }

            break;

        default:

            System.out.println("Type de page inconnu");
            return;

        }

        System.out.println(
                "PRECEDENT taper b\tNEXT taper n\tEXIT taper n'importe qu'elle touche");

    }

}
