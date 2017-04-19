package com.cdb.ui;

import java.util.Scanner;

/**
 * The Class UserInterpreter.
 */
public class UserInterpreter {

    /** The Constant sc. */
    public static final Scanner SCANNER = new Scanner(System.in);
    protected static String uri = "http://localhost:8080/rest/";
    protected static String uriComputers = "computers/";
    protected static String uriCompanies = "companies/";
    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {

        String entry;

        do {

            System.out
                    .println("\nVeuillez saisir computer, company ou exit :\n");
            entry = SCANNER.nextLine();

        } while (GestionEntryUser.lectureEntryUser(entry));

        SCANNER.close();

    }

}
