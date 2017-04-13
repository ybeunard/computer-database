package com.cdb.ui;

import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * The Class UserInterpreter.
 */
public class UserInterpreter {

    /** The Constant sc. */
    public static final Scanner SCANNER = new Scanner(System.in);
    
    public static final Client CLIENT = ClientBuilder.newClient();
    
    public static final String BASE_URL = "http://localhost:8080/rest/";

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {

        String entry;

        do {

            System.out.println("\nVeuillez saisir computer, company ou exit :\n");
            entry = SCANNER.nextLine();

        } while (GestionEntryUser.lectureEntryUser(entry));

        SCANNER.close();

    }

}
