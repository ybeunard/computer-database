package com.cdb.views.ui;

import java.util.Scanner;

/**
 * The Class UserInterpreter.
 */
public class UserInterpreter {

    /** The Constant sc. */
    public static final Scanner SCANNER = new Scanner(System.in);

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {

        String arg;

        do {

            System.out.println("\nVeuillez saisir une commande :\n");
            arg = SCANNER.nextLine();

        } while (GestionEntryUser.lectureEntryUser(arg));

        SCANNER.close();

    }

}
