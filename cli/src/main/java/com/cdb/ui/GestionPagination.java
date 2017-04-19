package com.cdb.ui;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cdb.model.dto.CompanyDto;
import com.cdb.model.dto.ComputerDto;
import com.cdb.model.dto.PageDto;
import com.cdb.utils.Parse;

/**
 * The Class GestionPagination.
 */
public class GestionPagination {

    private static RestTemplate restTemplate = new RestTemplate();
    
    /** The numero page. */
    private int numPage;

    /** The ligne par page. */
    private int rowByPage;

    /** The page ordinateur. */
    private List<ComputerDto> pageComputer;

    /** The page entreprise. */
    private List<CompanyDto> pageCompany;

    /**
     * Instantiates a new gestion pagination.
     */
    public GestionPagination() {

        this.numPage = 1;
        this.rowByPage = 100;

    }

    /**
     * Pagination.
     *
     * @param typePage
     *            indique si la page est une page entreprise ou ordinateur
     */
    public void paging(String typePage) {

        numPage = 1;

        do {

            switch (typePage) {

            case "computer":

                try {
                    System.out.println(UserInterpreter.uri + UserInterpreter.uriComputers);

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<PageDto> entity = new HttpEntity<PageDto>(new PageDto.PageDtoBuilder().build(), headers);
                    
                    ResponseEntity<List<ComputerDto>> computers = 
                            restTemplate
                            .exchange(UserInterpreter.uri + UserInterpreter.uriComputers,
                                    HttpMethod.GET,
                                    entity,
                                    new ParameterizedTypeReference<List<ComputerDto>>() {}
                            );
                    

                } catch (RestClientException restClientException) {
                    System.out.println("Problem occured when getting company with page");
                    restClientException.printStackTrace();
                    
                }
                displayPage(typePage);
                break;

            case "company":

                try {
                    
                    ResponseEntity<CompanyDto> company = restTemplate.getForEntity(UserInterpreter.uri + UserInterpreter.uriCompanies, CompanyDto.class);
                    if (company.getStatusCode() == HttpStatus.OK) {
                        System.out.println(company.getBody().toString() + "\n\n");
                    }

                } catch (RestClientException restClientException) {
                    System.out.println("Problem occured when getting company with page ");
                }                displayPage(typePage);
                break;

            default:

                System.out.println("Type de page inconnu");
                return;

            }

        } while (changePage());

    }

    /**
     * Pagination.
     *
     * @param typePage
     *            indique si la page est une page entreprise ou ordinateur
     * @param name
     *            the name filter
     */
    public void pagingByName(String typePage, String name) {

        numPage = 1;
//        WebTarget target;

        do {

            switch (typePage) {

            case "computer":

//                target = UserInterpreter.CLIENT.target(UserInterpreter.BASE_URL)
//                        .path("computers/");
//                pageComputer = target.request().get()
//                        .readEntity(new GenericType<List<ComputerDto>>() {
//                        });
//                displayPage(typePage);
//                break;

            case "company":
//
//                target = UserInterpreter.CLIENT.target(UserInterpreter.BASE_URL)
//                        .path("companies/");
//                pageCompany = target.request().get()
//                        .readEntity(new GenericType<List<CompanyDto>>() {
//                        });
//                displayPage(typePage);
                break;

            default:

                System.out.println("Type de page inconnue");
                return;

            }

        } while (changePage());

    }

    /**
     * Changement page.
     *
     * @return true, if successful
     */
    private boolean changePage() {

        String entry = UserInterpreter.SCANNER.nextLine();

        switch (entry) {

        case "b":

            if (numPage > 1) {

                numPage--;

            }

            return true;

        case "n":

            numPage++;
            return true;

        case "p":

            System.out.println("Entrez le nombre de lignes souhaitées :");
            entry = UserInterpreter.SCANNER.nextLine();
            rowByPage = Parse.parseEntier(entry, 100);
            return true;

        default:

            return false;

        }

    }

    /**
     * Affichage page.
     *
     * @param typePage
     *            the type page
     */
    private void displayPage(String typePage) {

        switch (typePage) {

        case "computer":

            for (ComputerDto ordinateur : pageComputer) {

                System.out.println(ordinateur);

            }

            break;

        case "company":

            for (CompanyDto entreprise : pageCompany) {

                System.out.println(entreprise);

            }

            break;

        default:

            System.out.println("Type de page inconnue");
            return;

        }

        System.out.println("\npage numero : " + numPage + " , " + rowByPage
                + " ligne par page \nprecedent taper b\nsuivant taper n\nchanger le nombre de ligne par page tapez p\nsortir tapez n'importe quelle autre touche");

    }

}
