package com.cdb.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.cdb.model.dto.OrdinateurDto;
import com.cdb.services.Impl.GestionEntreprise;
import com.cdb.services.Impl.GestionOrdinateur;

/**
 * The Class GestionEntryUser.
 */
public class GestionEntryUser {

    /** The prop. */
    private static Properties prop = new Properties();

    private static ApplicationContext context = new ClassPathXmlApplicationContext(
            "springConfig.xml");
    private static GestionOrdinateur gestionOrdinateur = (GestionOrdinateur) context
            .getBean("GestionOrdinateur");
    private static GestionEntreprise gestionEntreprise = (GestionEntreprise) context
            .getBean("GestionEntreprise");

    static {

        File fProp = new File(
                "/home/excilys/eclipse_workspace/computerDatabase/src/main/resources/error_message_client.properties");

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

            //createOrdinateur(splitArray[1]);

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

        OrdinateurDto ordinateur = null;

        try {

            long id = Long.parseLong(arg);
            ordinateur = gestionOrdinateur.findOrdinateurById(id);

        } catch (NumberFormatException e) {

            System.out.println(prop.getProperty("id_incorrect"));
            return;

        }

        System.out.println(ordinateur);

    }

    /**
     * Creates the ordinateur.
     *
     * @param args
     *            the args
     */
    /*private static void createOrdinateur(String args) {

        String[] argArray = args.split("'", 3);
        OrdinateurBuilder ordinateur = new Ordinateur.OrdinateurBuilder(
                argArray[1]);

        if (argArray[2].isEmpty()) {

            //gestionOrdinateur.createOrdinateur(ordinateur.build());

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

                argArray = argArray[1].split(" ", 2);
                ordinateur.dateIntroduit(LocalDate.parse(argArray[0],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            } else if (argArray[0] == "interruption") {

                argArray = argArray[1].split(" ", 2);
                ordinateur.dateInterrompu(LocalDate.parse(argArray[0],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            } else if (argArray[0] == "fabricant") {

                try {

                    argArray = argArray[1].split(" ", 2);
                    int id = Integer.parseInt(argArray[0]);
                    Optional<Entreprise> fabricant = gestionEntreprise
                            .findEntrepriseById(id);

                    if (fabricant.isPresent()) {

                        ordinateur.fabricant(fabricant);

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

                //gestionOrdinateur.createOrdinateur(ordinateur.build());

                return;

            }

            args = argArray[1];

        }

        System.out.println(prop.getProperty("nombre_arg"));
        return;

    }*/

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

        OrdinateurDto ordinateur = null;

        try {

            long id = Long.parseLong(argArray[0]);
            ordinateur = gestionOrdinateur.findOrdinateurById(id);

        } catch (NumberFormatException e) {

            System.out.println(prop.getProperty("id_incorrect"));
            return;

        }

        /*OrdinateurBuilder builder = new OrdinateurBuilder(ordinateur.getName())
                .id(ordinateur.getId());
        
         .dateIntroduit( Optional.ofNullable(ordinateur.getDateIntroduit()))
         .dateInterrompu(
         Optional.ofNullable(ordinateur.getDateInterrompu()));
         

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

                builder.name(argArray[1]);

                if (argArray[2].isEmpty()) {

                    //gestionOrdinateur.updateOrdinateur(builder.build());
                    return;

                }

            } else if (argArray[0] == "introduction") {

                argArray = argArray[1].split(" ", 2);
                builder.dateIntroduit(LocalDate.parse(argArray[0],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            } else if (argArray[0] == "interruption") {

                argArray = argArray[1].split(" ", 2);
                builder.dateInterrompu(LocalDate.parse(argArray[0],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                break;

            } else if (argArray[0] == "fabricant") {

                try {

                    argArray = argArray[1].split(" ", 2);
                    int id = Integer.parseInt(argArray[0]);
                    Optional<Entreprise> fabricant = gestionEntreprise
                            .findEntrepriseById(id);

                    if (fabricant.isPresent()) {

                        builder.fabricant(fabricant);

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

                //gestionOrdinateur.updateOrdinateur(builder.build());
                return;

            }

            args = argArray[1];

        }*/

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

        List<Long> identifiant = new ArrayList<Long>();

        try {

            identifiant.add(Long.parseLong(arg));
            gestionOrdinateur.suppressionOrdinateur(identifiant);

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
