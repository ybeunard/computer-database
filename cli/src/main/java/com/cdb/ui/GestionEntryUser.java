package com.cdb.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cdb.model.dto.CompanyDto;
import com.cdb.model.dto.ComputerDto;

/**
 * The Class GestionEntryUser.
 */
public class GestionEntryUser {

    /** The Constant PAGING. */
    private static final GestionPagination PAGING = new GestionPagination();    
    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * Lecture entry user.
     *
     * @param entry
     *            the entry
     * @return true, if successful
     */
    public static boolean lectureEntryUser(String entry) {

        switch (entry) {

        case "company":

            do {

                System.out.println(
                        "\nVeuillez saisir l'option list, search, delete ou exit :\n");
                entry = UserInterpreter.SCANNER.nextLine();

            } while (lectureEntryCompany(entry));

            break;

        case "computer":

            do {

                System.out.println(
                        "\nVeuillez saisir l'option list, search, create, update, delete ou exit :\n");
                entry = UserInterpreter.SCANNER.nextLine();

            } while (lectureEntryComputer(entry));

            break;

        case "exit":

            return false;

        default:

            System.out.println("command_incorrect");

        }

        return true;

    }

    /**
     * Lecture entry company.
     *
     * @param entry
     *            the entry
     * @return true, if successful
     */
    private static boolean lectureEntryCompany(String entry) {

        switch (entry) {

        case "list":

            PAGING.paging("company");
            break;

        case "search":

            System.out.println("\nVeuillez saisir l'option id ou name :\n");
            entry = UserInterpreter.SCANNER.nextLine();
            lectureEntryCompanySearch(entry);
            break;

        case "delete":

            System.out.println("Entrez l'Id de la company recherchée :");
            entry = UserInterpreter.SCANNER.nextLine();
            delete("company", entry);
            break;

        case "exit":

            return false;

        default:

            System.out.println("command_incorrect");

        }

        return true;

    }

    /**
     * Lecture entry company search.
     *
     * @param entry
     *            the entry
     */
    private static void lectureEntryCompanySearch(String entry) {

        switch (entry) {

        case "id":

            System.out.println("Entrez l'Id de la company recherchée :");
            entry = UserInterpreter.SCANNER.nextLine();
            displayById("company", entry);
            break;

        case "name":

            System.out.println("Entrez le nom de la company recherchée :");
            entry = UserInterpreter.SCANNER.nextLine();
            displayByName("company", entry);
            break;

        default:

            System.out.println("command_incorrect");

        }

    }

    /**
     * Lecture entry computer.
     *
     * @param entry
     *            the entry
     * @return true, if successful
     */
    private static boolean lectureEntryComputer(String entry) {

        switch (entry) {

        case "list":

            PAGING.paging("computer");
            break;

        case "search":

            System.out.println("\nVeuillez saisir l'option id ou name :\n");
            entry = UserInterpreter.SCANNER.nextLine();
            lectureEntryComputerSearch(entry);
            break;

        case "create":

            createComputer();
            break;

        case "update":

            updateComputer();
            break;

        case "delete":

            System.out.println("Entrez l'Id du computer a supprimer :");
            entry = UserInterpreter.SCANNER.nextLine();
            delete("computer", entry);
            break;

        case "exit":

            return false;

        default:

            System.out.println("command_incorrect");

        }

        return true;

    }

    /**
     * Lecture entry computer search.
     *
     * @param entry
     *            the entry
     */
    private static void lectureEntryComputerSearch(String entry) {

        switch (entry) {

        case "id":

            System.out.println("Entrez l'Id du computer recherché :");
            entry = UserInterpreter.SCANNER.nextLine();
            displayById("computer", entry);
            break;

        case "name":

            System.out.println("Entrez le nom du computer recherché :");
            entry = UserInterpreter.SCANNER.nextLine();
            displayByName("computer", entry);
            break;

        default:

            System.out.println("command_incorrect");

        }

    }

    /**
     * Affichage ordinateur.
     *
     * @param type
     *            the type
     * @param id
     *            the id
     */
    private static void displayById(String type, String id) {

        switch (type) {

        case "company":
            
            try {
                
                ResponseEntity<CompanyDto> company = restTemplate.getForEntity(UserInterpreter.uri + UserInterpreter.uriCompanies + id, CompanyDto.class);
                if (company.getStatusCode() == HttpStatus.OK) {
                    System.out.println(company.getBody().toString() + "\n\n");
                }

            } catch (RestClientException restClientException) {
                System.out.println("Problem occured when getting company with id " + id);
            }

            break;

        case "computer":
            try {
                
                ResponseEntity<ComputerDto> computer = restTemplate.getForEntity(UserInterpreter.uri + UserInterpreter.uriComputers + id, ComputerDto.class);
                if (computer.getStatusCode() == HttpStatus.OK) {
                    System.out.println(computer.getBody().toString() + "\n\n");
                }

            } catch (RestClientException restClientException) {
                System.out.println("Problem occured when getting computer with id " + id);
            }

            break;

        default:

            System.out.println("command_incorrect");

        }

    }

    /**
     * Affichage ordinateur.
     *
     * @param type
     *            the type
     * @param name
     *            the name
     */
    private static void displayByName(String type, String name) {

        switch (type) {

        case "company":

            PAGING.pagingByName("company", name);
            break;

        case "computer":

            PAGING.pagingByName("computer", name);
            break;

        default:

            System.out.println("command_incorrect");

        }

    }

    /**
     * Creates the ordinateur.
     */
    private static void createComputer() {

        ComputerDto computerDto = new ComputerDto();
 
        System.out.println("\nVeuillez saisir le nom du computer :\n");
        String entry = UserInterpreter.SCANNER.nextLine();

        if (!entry.equals("")) {

           computerDto.setName(entry);

        }

        System.out.println("\nVeuillez saisir la date d'introduction du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();

        if (!entry.equals("")) {

            computerDto.setIntroduced(entry);

        }

        System.out.println("\nVeuillez saisir la date d'interruption du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();

        if (!entry.equals("")) {

            computerDto.setDiscontinued(entry);

        }

        System.out.println("\nVeuillez saisir l'id de la company du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();

        if (!entry.equals("")) {

            computerDto.setIdCompany(Integer.parseInt(entry));

        }

        try {
            HttpStatus status = restTemplate.postForEntity(UserInterpreter.uri + UserInterpreter.uriComputers, computerDto, ComputerDto.class).getStatusCode();
            
            if (status == HttpStatus.OK) {
                System.out.println("Computer posted");
            }

        } catch (RestClientException restClientException) {
            System.out.println("Problem occured when using post for computer.");
        }
    }

    /**
     * Update ordinateur.
     */
    private static void updateComputer() {

        System.out.println("\nVeuillez saisir l'id du computer à update(champ obligatoire):\n");
        String entry = UserInterpreter.SCANNER.nextLine();
        ComputerDto computerDto = new ComputerDto();
        
        int id = 0;
        
        if (entry.equals("")) {

            System.out.println("L'Id est obligatoire");
            return;

        } else {
            id = Integer.parseInt(entry);
        }

        System.out.println("\nVeuillez saisir le nom du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();

        if (!entry.equals("")) {
            computerDto.setId(id);
            computerDto.setName(entry);
        }

        System.out.println("\nVeuillez saisir la date d'introduction du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();

        if (!entry.equals("")) {

            computerDto.setIntroduced(entry);

        }

        System.out.println("\nVeuillez saisir la date d'interruption du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();

        if (!entry.equals("")) {

            computerDto.setDiscontinued(entry);

        }

        System.out.println("\nVeuillez saisir l'id de la company du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();

        if (!entry.equals("")) {

            computerDto.setIdCompany(Integer.parseInt(entry));

        }
        
        restTemplate.put(UserInterpreter.uri + UserInterpreter.uriComputers, computerDto, ComputerDto.class);
        
        System.out.println("Computer put");

    }

    /**
     * Delete ordinateur.
     *
     * @param type
     *            the type
     * @param id
     *            the id
     */
    private static void delete(String type, String id) {

        switch (type) {

        case "company":

            restTemplate.delete(UserInterpreter.uri + UserInterpreter.uriCompanies + id);
            
            System.out.println("Entity deleted");

            break;

        case "computer":

            restTemplate.delete(UserInterpreter.uri + UserInterpreter.uriComputers + id);

            System.out.println("Entity deleted");

            break;

        default:

            System.out.println("command_incorrect");

        }

    }

}
