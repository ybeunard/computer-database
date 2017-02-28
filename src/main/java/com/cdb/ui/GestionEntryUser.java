package com.cdb.ui;

import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.cdb.entities.Ordinateur;
import com.cdb.services.Impl.GestionEntreprise;
import com.cdb.services.Impl.GestionOrdinateur;
import com.cdb.services.Impl.GestionPagination;
import com.cdb.entities.Entreprise;

/**
 * The Class GestionEntryUser.
 */
public class GestionEntryUser {

    /** The prop. */
    private static Properties prop = new Properties();

    static {

        File fProp = new File(
                "computer-database/properties/error_message_client.properties");

        FileInputStream stream = null;

        try {

            stream = new FileInputStream(fProp);
            prop.load(stream);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    /**
     * Lecture entry user.
     *
     * @param arg
     *            the arg
     * @return true, if successful
     */
    public static boolean lectureEntryUser(String arg) {

        String[] splitArray = arg.split(" ", 2);

        if (splitArray[0] == "list") {

            if (splitArray.length != 2) {

                System.out.println(prop.getProperty("nombre_arg"));
                return true;

            }

            affichageListe(splitArray[1]);

        } else if (splitArray[0] == "affiche") {

            if (splitArray.length != 2) {

                System.out.println(prop.getProperty("nombre_arg"));
                return true;

            }

            affichageOrdinateur(splitArray[1]);

        } else if (splitArray[0] == "create") {

            if (splitArray.length != 2) {

                System.out.println(prop.getProperty("nombre_arg"));
                return true;

            }

            createOrdinateur(splitArray[1]);

        } else if (splitArray[0] == "update") {

            if (splitArray.length != 2) {

                System.out.println(prop.getProperty("nombre_arg"));
                return true;

            }

            updateOrdinateur(splitArray[1]);

        } else if (splitArray[0] == "delete") {

            if (splitArray.length != 2) {

                System.out.println(prop.getProperty("nombre_arg"));
                return true;

            }

            deleteOrdinateur(splitArray[1]);

        } else if (splitArray[0] == "help") {

            if (splitArray.length == 2) {

                help(splitArray[1]);

            } else if (splitArray.length == 1) {

                System.out.println(
                        "Liste des commande :\nlist\naffiche\ncreate\nupdate\ndelete\nhelp\nexit\nPour une description détaillé de chaque commande taper: help [commande]");

            } else {

                System.out.println(prop.getProperty("nombre_arg"));

            }

        } else if (splitArray[0] == "exit") {

            return false;

        } else {

            System.out.println(
                    splitArray[0] + prop.getProperty("command_incorrect"));

        }

        return true;

    }

    /**
     * Affichage liste.
     *
     * @param arg
     *            the arg
     */
    private static void affichageListe(String arg) {

        String[] argArray = arg.split(" ");

        GestionPagination pagination;

        if (argArray.length == 2) {

            try {

                pagination = new GestionPagination(
                        Integer.parseInt(argArray[1]));

            } catch (NumberFormatException e) {

                System.out.println(prop.getProperty("pagination"));
                return;

            }

        } else {

            pagination = new GestionPagination();

        }

        if (argArray[0] == "company") {

            pagination.pagination(2);

        } else if (argArray[0] == "computer") {

            pagination.pagination(1);

        } else {

            System.out.println(
                    argArray[0] + prop.getProperty("argument_incorrect"));

        }

    }

    /**
     * Affichage ordinateur.
     *
     * @param arg
     *            the arg
     */
    private static void affichageOrdinateur(String arg) {

        Optional<Ordinateur> ordinateur = Optional.empty();

        try {

            long id = Integer.parseInt(arg);
            ordinateur = GestionOrdinateur.getInstanceGestionOrdinateur()
                    .findOrdinateurByID(id);

        } catch (NumberFormatException e) {

            System.out.println(prop.getProperty("id_incorrect"));
            return;

        }

        if (ordinateur.isPresent()) {

            System.out.println(ordinateur.get());
            return;

        }

        System.out.println(prop.getProperty("id_incorrect"));

    }

    /**
     * Creates the ordinateur.
     *
     * @param args
     *            the args
     */
    private static void createOrdinateur(String args) {

        String[] argArray = args.split("'", 3);
        Ordinateur ordinateur = new Ordinateur(argArray[1]);

        if (argArray[2].isEmpty()) {

            GestionOrdinateur.getInstanceGestionOrdinateur()
                    .createOrdinateur(ordinateur);
            return;

        }

        argArray = argArray[2].split(" ", 2);
        args = argArray[1];

        while (argArray.length > 1) {

            argArray = args.split(" ", 2);

            if (argArray.length != 2) {

                System.out.println(prop.getProperty("nombre_arg"));
                return;

            }

            if (argArray[0] == "introduction") {

                try {

                    argArray = argArray[1].split(" ", 2);
                    SimpleDateFormat format = new SimpleDateFormat(
                            "yyyy/MM/dd");
                    Date parsed = format.parse(argArray[0]);
                    ordinateur.setDateIntroduit(
                            new java.sql.Date(parsed.getTime()).toLocalDate());

                } catch (ParseException e) {

                    System.out.println(
                            "Format de date incorecte merci de respecter la syntaxe suivante : yyyy/MM/dd");
                    return;

                }

            } else if (argArray[0] == "interruption") {

                try {

                    argArray = argArray[1].split(" ", 2);
                    SimpleDateFormat format = new SimpleDateFormat(
                            "yyyy/MM/dd");
                    Date parsed = format.parse(argArray[0]);
                    ordinateur.setDateInterrompu(
                            new java.sql.Date(parsed.getTime()).toLocalDate());

                } catch (ParseException e) {

                    System.out.println(
                            "Format de date incorecte marci de respecter la syntaxe suivante : yyyy/MM/dd");
                    return;

                }

            } else if (argArray[0] == "fabricant") {

                try {

                    argArray = argArray[1].split(" ", 2);
                    int id = Integer.parseInt(argArray[0]);
                    Optional<Entreprise> fabricant = GestionEntreprise
                            .getInstanceGestionEntreprise()
                            .findEntrepriseByID(id);

                    if (fabricant.isPresent()) {

                        ordinateur.setFabricant(fabricant.get());

                    } else {

                        System.out.println(
                                "Veuillez donner un id d'entreprise correct");
                        return;

                    }

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Veuillez donner un id d'entreprise correct");
                    return;

                }

            } else {

                System.out.println(
                        argArray[0] + prop.getProperty("argument_incorrect"));
                return;

            }

            if (argArray.length == 1) {

                GestionOrdinateur.getInstanceGestionOrdinateur()
                        .createOrdinateur(ordinateur);
                return;

            }

            args = argArray[1];

        }

        System.out.println(prop.getProperty("nombre_arg"));
        return;

    }

    /**
     * Update ordinateur.
     *
     * @param args
     *            the args
     */
    private static void updateOrdinateur(String args) {

        String[] argArray = args.split(" ", 2);

        if (argArray.length != 2) {

            System.out.println(prop.getProperty("nombre_arg"));
            return;

        }

        Optional<Ordinateur> ordinateur;

        try {

            int id = Integer.parseInt(argArray[0]);
            ordinateur = GestionOrdinateur.getInstanceGestionOrdinateur()
                    .findOrdinateurByID(id);

            if (!ordinateur.isPresent()) {

                System.out.println(prop.getProperty("id_incorrect"));
                return;

            }

        } catch (NumberFormatException e) {

            System.out.println(prop.getProperty("id_incorrect"));
            return;

        }

        args = argArray[1];

        while (argArray.length > 1) {

            argArray = args.split(" ", 2);

            if (argArray.length != 2) {

                System.out.println(prop.getProperty("nombre_arg"));
                return;

            }

            if (argArray[0] == "name") {

                argArray = argArray[1].split(" ", 2);
                argArray = argArray[0].split("'", 3);

                if (ordinateur.isPresent()) {

                    ordinateur.get().setName(argArray[1]);

                    if (argArray[2].isEmpty()) {

                        GestionOrdinateur.getInstanceGestionOrdinateur()
                                .updateOrdinateur(ordinateur.get());
                        return;

                    }

                }

            } else if (argArray[0] == "introduction") {

                try {

                    argArray = argArray[1].split(" ", 2);
                    SimpleDateFormat format = new SimpleDateFormat(
                            "yyyy/MM/dd");
                    Date parsed = format.parse(argArray[0]);

                    if (ordinateur.isPresent()) {

                        ordinateur.get().setDateIntroduit(
                                new java.sql.Date(parsed.getTime())
                                        .toLocalDate());

                    }

                } catch (ParseException e) {

                    System.out.println(prop.getProperty("date_incorrect"));
                    return;

                }

            } else if (argArray[0] == "interruption") {

                try {

                    argArray = argArray[1].split(" ", 2);
                    SimpleDateFormat format = new SimpleDateFormat(
                            "yyyy/MM/dd");
                    Date parsed = format.parse(argArray[0]);

                    if (ordinateur.isPresent()) {

                        ordinateur.get().setDateInterrompu(
                                new java.sql.Date(parsed.getTime())
                                        .toLocalDate());

                    }

                } catch (ParseException e) {

                    System.out.println(prop.getProperty("date_incorrect"));
                    return;

                }

                break;

            } else if (argArray[0] == "fabricant") {

                try {

                    argArray = argArray[1].split(" ", 2);
                    int id = Integer.parseInt(argArray[0]);
                    Optional<Entreprise> fabricant = GestionEntreprise
                            .getInstanceGestionEntreprise()
                            .findEntrepriseByID(id);

                    if (fabricant.isPresent()) {

                        if (ordinateur.isPresent()) {

                            ordinateur.get().setFabricant(fabricant.get());

                        }

                    } else {

                        System.out.println(
                                prop.getProperty("id_entreprise_incorrect"));
                        return;

                    }

                } catch (NumberFormatException e) {

                    System.out.println(
                            prop.getProperty("id_entreprise_incorrect"));
                    return;

                }

            } else {

                System.out.println(
                        argArray[0] + prop.getProperty("argument_incorrect"));
                return;

            }

            if (argArray.length < 2) {

                if (ordinateur.isPresent()) {

                    GestionOrdinateur.getInstanceGestionOrdinateur()
                            .updateOrdinateur(ordinateur.get());

                }

                return;

            }

            args = argArray[1];

        }

        System.out.println(prop.getProperty("nombre_arg"));
        return;

    }

    /**
     * Delete ordinateur.
     *
     * @param arg
     *            the arg
     */
    private static void deleteOrdinateur(String arg) {

        try {

            long id = Integer.parseInt(arg);
            GestionOrdinateur.getInstanceGestionOrdinateur()
                    .suppressionOrdinateur(id);

        } catch (NumberFormatException e) {

            System.out.println(prop.getProperty("id_incorrect"));
            return;

        }

    }

    /**
     * Help.
     *
     * @param arg
     *            the arg
     */
    private static void help(String arg) {

        if (arg == "list") {

            System.out.println(prop.getProperty("syntaxe_list"));

        } else if (arg == "affiche") {

            System.out.println(prop.getProperty("syntaxe_affiche"));

        } else if (arg == "create") {

            System.out.println(prop.getProperty("syntaxe_create"));

        } else if (arg == "update") {

            System.out.println(prop.getProperty("syntaxe_update"));

        } else if (arg == "delete") {

            System.out.println(prop.getProperty("syntaxe_delete"));

        } else {

            System.out.println(arg + prop.getProperty("command_incorrect"));

        }

    }

}
