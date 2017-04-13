package com.cdb.ui;

import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import com.cdb.model.dto.CompanyDto;
import com.cdb.model.dto.ComputerDto;

/**
 * The Class GestionEntryUser.
 */
public class GestionEntryUser {
    
    private final static GestionPagination PAGING = new GestionPagination();

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
                    
                    System.out.println("\nVeuillez saisir l'option list, search, delete ou exit :\n");
                    entry = UserInterpreter.SCANNER.nextLine();

                } while (lectureEntryCompany(entry));
                
                break;
                
            case "computer":
                
                do {
                    
                    System.out.println("\nVeuillez saisir l'option list, search, create, update, delete ou exit :\n");
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
                
                System.out.println("Entrez l'Id de la company recherchée :");
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
    
    private static void lectureEntryComputerSearch(String entry) {
        
        switch (entry) {
        
            case "id":
            
                System.out.println("Entrez l'Id du computer recherchée :");
                entry = UserInterpreter.SCANNER.nextLine();
                displayById("computer", entry);
                break;
            
            case "name":
            
                System.out.println("Entrez le nom du computer recherchée :");
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
     * @param arg
     *            the arg
     */
    private static void displayById(String type, String id) {

        WebTarget target;
        
        switch (type) {
        
            case "company":
                
                target = UserInterpreter.CLIENT.target(UserInterpreter.BASE_URL).path("company/find").queryParam("id", id);
                List<CompanyDto> companies = target.request().get().readEntity(new GenericType<List<CompanyDto>>() { });
                
                for (CompanyDto company: companies) {
                    
                    System.out.println(company + "\n\n");
                    
                }
                
                break;
                
            case "computer":
                
                target = UserInterpreter.CLIENT.target(UserInterpreter.BASE_URL).path("computer/find").queryParam("id", id);
                List<ComputerDto> computers = target.request().get().readEntity(new GenericType<List<ComputerDto>>() { });
                
                for (ComputerDto computer: computers) {
                    
                    System.out.println(computer + "\n\n");
                    
                }
                
                break;
                
            default:
                
                System.out.println("command_incorrect");
                
        }

    }
    
    /**
     * Affichage ordinateur.
     *
     * @param arg
     *            the arg
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
     *
     * @param args
     *            the args
     */
    private static void createComputer() {

        WebTarget target = UserInterpreter.CLIENT.target(UserInterpreter.BASE_URL).path("computer/create");
        System.out.println("\nVeuillez saisir le nom du computer (champ obligatoire):\n");
        String entry = UserInterpreter.SCANNER.nextLine();
        
        if(entry.equals("")) {
            
            System.out.println("Le nom est obligatoire");
            return;
            
        }
        
        target = target.queryParam("name", entry);

        System.out.println("\nVeuillez saisir la date d'introduction du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();
        
        if(!entry.equals("")) {
            
            target = target.queryParam("introduced", entry);
            
        }
        
        System.out.println("\nVeuillez saisir la date d'interruption du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();
        
        if(!entry.equals("")) {
            
            target = target.queryParam("discontinued", entry);
            
        }
        
        System.out.println("\nVeuillez saisir l'id de la company du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();
        
        if(!entry.equals("")) {
            
            
            target = target.queryParam("idCompany", entry);
            
        }
        
        String returnMessage = target.request().get().readEntity(new GenericType<String>() { });
        System.out.println(returnMessage);

    }

    /**
     * Update ordinateur.
     *
     * @param args
     *            the args
     */
    private static void updateComputer() {
        
        WebTarget target = UserInterpreter.CLIENT.target(UserInterpreter.BASE_URL).path("computer/update");
        System.out.println("\nVeuillez saisir l'id du computer à update(champ obligatoire):\n");
        String entry = UserInterpreter.SCANNER.nextLine();
        
        if (entry.equals("")) {
            
            System.out.println("L'Id est obligatoire");
            return;
            
        }
        
        target = target.queryParam("name", entry);
        System.out.println("\nVeuillez saisir le nom du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();

        if(!entry.equals("")) {
                    
            target = target.queryParam("name", entry);
            
        }

        System.out.println("\nVeuillez saisir la date d'introduction du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();
        
        if(!entry.equals("")) {
            
            target = target.queryParam("introduced", entry);
            
        }
        
        System.out.println("\nVeuillez saisir la date d'interruption du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();
        
        if(!entry.equals("")) {
            
            target = target.queryParam("discontinued", entry);
            
        }
        
        System.out.println("\nVeuillez saisir l'id de la company du computer :\n");
        entry = UserInterpreter.SCANNER.nextLine();
        
        if(!entry.equals("")) {
            
            
            target = target.queryParam("idCompany", entry);
            
        }
        
        String returnMessage = target.request().get().readEntity(new GenericType<String>() { });
        System.out.println(returnMessage);

    }

    /**
     * Delete ordinateur.
     *
     * @param arg
     *            the arg
     */
    private static void delete(String type, String id) {
        
        WebTarget target;
        String returnMessage;
        
        switch (type) {
        
            case "company":
                
                target = UserInterpreter.CLIENT.target(UserInterpreter.BASE_URL).path("company/delete").queryParam("id", id);
                returnMessage = target.request().get().readEntity(new GenericType<String>() { });
                System.out.println(returnMessage);
                break;
                
            case "computer":
                
                target = UserInterpreter.CLIENT.target(UserInterpreter.BASE_URL).path("computer/delete").queryParam("id", id);
                returnMessage = target.request().get().readEntity(new GenericType<String>() { });
                System.out.println(returnMessage);
                break;
                
            default:
                
                System.out.println("command_incorrect");
        
        }

    }

}
