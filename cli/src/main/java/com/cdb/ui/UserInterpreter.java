package com.cdb.ui;

import java.util.Scanner;

/**
 * The Class UserInterpreter.
 */
public class UserInterpreter {

    /** The Constant sc. */
    protected static final Scanner SCANNER = new Scanner(System.in);

    /** The Constant SLASH. */
    protected static final String SLASH = "/";

    /** The ip. */
    protected static String ip = "localhost";

    /** The port. */
    protected static String port = "8080";

    /** The api. */
    protected static String api = "rest";

    /** The uri. */
    protected static String uri = "http://" + ip + ":" + port + SLASH + api
            + SLASH;

    /** The uri computers. */
    protected static String uriComputers = "computers/";

    /** The uri companies. */
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
