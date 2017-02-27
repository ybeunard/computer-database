package com.cdb.ui;

import java.util.Date;
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

public class GestionEntryUser {
	
	private static Properties prop = new Properties();
	
	//Chargement du fichier connexion.properties
	static {
		
		File fProp = new File("computer-database/properties/error_message_client.properties");
		 
		// Charge le contenu de ton fichier properties dans un objet Properties
		FileInputStream stream = null;
		
		try {
			
			stream = new FileInputStream(fProp);
			prop.load(stream) ;
			
		} catch(IOException e) {

			e.printStackTrace();
			
		}
		
	}

	//Lit le premier argument de la commande utilisateur et le renvoi vers la fonction qui la traite
	public static boolean lectureEntryUser(String arg) {
		
		//recuperation de l'argument principal
		String[] splitArray = arg.split(" ", 2);
		
		//commande list qui affiche la liste paginer
		if(splitArray[0]=="list") {
			
			//Verification si l'utilisateur à bien donner des arguments nessecaire à la commande
			if(splitArray.length != 2) {
				
				System.out.println(prop.getProperty("nombre_arg"));
				return true;
				
			}
			
			affichageListe(splitArray[1]);
			
		//commande affiche qui affiche l'ordinateur demandé
		} else if(splitArray[0]=="affiche") {
			
			//Verification si l'utilisateur à bien donner des arguments nessecaire à la commande
			if(splitArray.length != 2) {
				
				System.out.println(prop.getProperty("nombre_arg"));
				return true;
				
			}
			
			affichageOrdinateur(splitArray[1]);
			
		//commande create qui creer un nouvel ordinateur
		} else if(splitArray[0]=="create") {
			
			//Verification si l'utilisateur à bien donner des arguments nessecaire à la commande
			if(splitArray.length != 2) {
				
				System.out.println(prop.getProperty("nombre_arg"));
				return true;
				
			}
			
			createOrdinateur(splitArray[1]);
			
		//commande update qui modifie un ordinateur existant
		} else if(splitArray[0]=="update") {
			
			//Verification si l'utilisateur à bien donner des arguments nessecaire à la commande
			if(splitArray.length != 2) {
				
				System.out.println(prop.getProperty("nombre_arg"));
				return true;
				
			}
			
			updateOrdinateur(splitArray[1]);
			
		//commande delete qui supprime un ordinateur existant
		} else if(splitArray[0]=="delete") {
			
			//Verification si l'utilisateur à bien donner des arguments nessecaire à la commande
			if(splitArray.length != 2) {
				
				System.out.println(prop.getProperty("nombre_arg"));
				return true;
				
			}
			
			deleteOrdinateur(splitArray[1]);
			
		//commande help qui aide l'utilisateur dans l'utilisation des commandes
		} else if(splitArray[0]=="help") {
			
			//Verification si l'utilisateur à bien donner des arguments supplementaires
			if(splitArray.length == 2) {
				
				help(splitArray[1]);
				
			} else if(splitArray.length == 1) { //affichage des commandes
				
				System.out.println("Liste des commande :\nlist\naffiche\ncreate\nupdate\ndelete\nhelp\nexit\nPour une description détaillé de chaque commande taper: help [commande]");
			
			} else {
				
				System.out.println(prop.getProperty("nombre_arg"));
				
			}
			
		//commande exit qui stop le programme
		} else if(splitArray[0]=="exit") {
			
			return false;
			
		} else {
			
			System.out.println(splitArray[0] + prop.getProperty("command_incorrect"));
		
		}
		
		return true;
		
	}

	//Fonction qui affiche la liste paginer
	private static void affichageListe(String arg) {
		
		String[] argArray = arg.split(" ");
		
		//Creation de la classe qui permet la pagination
		GestionPagination pagination;
		
		//Verification si l'utilisateur à specifier un nombre de ligne par page + initialisation de la classe qui permet la pagination
		if(argArray.length == 2) {
			
			try {
				
				pagination = new GestionPagination(Integer.parseInt(argArray[1]));
				
			} catch(NumberFormatException e) {
				
					System.out.println(prop.getProperty("pagination"));
					return;
					
			}
			
		} else {
			
			pagination = new GestionPagination();
			
		}
		
		//Verification de la liste à afficher specifier par l'utilisateur
		//Affichage de la liste des entreprises
		if(argArray[0]=="company") {
			
			pagination.pagination(2);
			
		//affichage de la liste des ordinateurs
		} else if(argArray[0]=="computer") {
			
			pagination.pagination(1);
		
		} else {
			
			System.out.println(argArray[0] + prop.getProperty("argument_incorrect"));
			
		}
		
	}
	
	//Fonction qui affiche l'ordinateur demandé
	private static void affichageOrdinateur(String arg) {
		
		Ordinateur ordinateur;
		
		try {
			
			//Recuperation de l'ordinateur demandé
			long id = Integer.parseInt(arg);
			ordinateur = GestionOrdinateur.getInstanceGestionOrdinateur().findOrdinateurByID(id);
			
			if(ordinateur == null) {
				
				System.out.println(prop.getProperty("id_incorrect"));
				return;
				
			}
		
		} catch(NumberFormatException e) {
			
			System.out.println(prop.getProperty("id_incorrect"));
			return;
			
		}
		
		//Affichage de l'ordinateur si il a été trouvé
		System.out.println(ordinateur);
		
	}

	//Fonction qui creer un nouvel ordinateur
	private static void createOrdinateur(String args) {
		
		String[] argArray = args.split("'", 3);
		Ordinateur ordinateur = new Ordinateur(argArray[1]);
		
		//Si juste le nom à été  spécifier on créer directement l'ordinateur
		if(argArray[2].isEmpty()) {
			
			GestionOrdinateur.getInstanceGestionOrdinateur().createOrdinateur(ordinateur);
			return;
			
		}
		
		//Sinon on recupere les arguments suivants pour les traités
		argArray = argArray[2].split(" ", 2);
		args = argArray[1];
		
		while(argArray.length > 1) {
			
			//On recupere l'option spécifier
			argArray = args.split(" ", 2);
			
			if(argArray.length != 2) {
				
				System.out.println(prop.getProperty("nombre_arg"));
				return;
				
			}
			
			//Traitement de l'option
			//Traitement de l'option: dateIntroduit
			if(argArray[0]=="introduction") {
				
				try {
					
					argArray = argArray[1].split(" ", 2);
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			        Date parsed = format.parse(argArray[0]);
					ordinateur.setDateIntroduit(new java.sql.Date(parsed.getTime()).toLocalDate());
					
				} catch (ParseException e) {
					
					System.out.println("Format de date incorecte merci de respecter la syntaxe suivante : yyyy/MM/dd");
					return;
					
				}
				
			//Traitement de l'option:  dateInterrompu
			} else if(argArray[0]=="interruption") {
				
				try {
					
					argArray = argArray[1].split(" ", 2);
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			        Date parsed = format.parse(argArray[0]);
					ordinateur.setDateInterrompu(new java.sql.Date(parsed.getTime()).toLocalDate());
					
				} catch (ParseException e) {
					
					System.out.println("Format de date incorecte marci de respecter la syntaxe suivante : yyyy/MM/dd");
					return;
					
				}
				
			//Traitement de l'option: fabricant
			} else if(argArray[0]=="fabricant") {
				
				try {
					
					argArray = argArray[1].split(" ", 2);
					int id = Integer.parseInt(argArray[0]);
					Entreprise fabricant = GestionEntreprise.getInstanceGestionEntreprise().findEntrepriseByID(id);
					
					if(fabricant != null) {
						
						ordinateur.setFabricant(fabricant);
						
					} else {
						
						System.out.println("Veuillez donner un id d'entreprise correct");
						return;
						
					}
					
				} catch(NumberFormatException e) {
					
					System.out.println("Veuillez donner un id d'entreprise correct");
					return;
					
				}
				
			//Option inconnu
			} else {
				
				System.out.println(argArray[0] + prop.getProperty("argument_incorrect"));
				return;
				
			}
			
			//Si pas d'autre option on creer l'ordinateur
			if(argArray.length == 1) {
				
				GestionOrdinateur.getInstanceGestionOrdinateur().createOrdinateur(ordinateur);
				return;
				
			}
			
			//Sinon on boucle
			args = argArray[1];
			
		}
		
		System.out.println(prop.getProperty("nombre_arg"));
		return;
		
	}
	
	
	
	//Fonction qui modifie un ordinateur existant
	private static void updateOrdinateur(String args) {
		
		//Recuperation de l'id et des autres arguments nessecaires
		String[] argArray = args.split(" ", 2);
		
		if(argArray.length != 2) {
			
			System.out.println(prop.getProperty("nombre_arg"));
			return;
			
		}
		
		Ordinateur ordinateur;
		
		try {
			
			//Recuperation de l'ordinateur à modifier via l'ID
			int id = Integer.parseInt(argArray[0]);
			ordinateur = GestionOrdinateur.getInstanceGestionOrdinateur().findOrdinateurByID(id);
			
			if(ordinateur == null) {
				
				System.out.println(prop.getProperty("id_incorrect"));
				return;
				
			}

		} catch(NumberFormatException e) {
			
			System.out.println(prop.getProperty("id_incorrect"));
			return;
			
		}
		
		//Recuperation des autres arguments si l'ordinateur existe bien.
		args = argArray[1];
		
		while(argArray.length > 1) {
			
			//On recupere l'option spécifier
			argArray = args.split(" ", 2);
			
			if(argArray.length != 2) {
				
				System.out.println(prop.getProperty("nombre_arg"));
				return;
				
			}
			
			//Traitement de l'option
			//Traitement de l'option : name	
			if(argArray[0]=="name") {
						
				argArray = argArray[1].split(" ", 2);
				argArray = argArray[0].split("'", 3);
				ordinateur.setName(argArray[1]);
				
				if(argArray[2].isEmpty()) {
					
					GestionOrdinateur.getInstanceGestionOrdinateur().updateOrdinateur(ordinateur);
					return;
					
				}
				
			//Traitement de l'option : dateIntroduit
			} else if(argArray[0]=="introduction") {
				
				try {
					
					argArray = argArray[1].split(" ", 2);
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			        Date parsed = format.parse(argArray[0]);
					ordinateur.setDateIntroduit(new java.sql.Date(parsed.getTime()).toLocalDate());
					
				} catch(ParseException e) {
					
					System.out.println(prop.getProperty("date_incorrect"));
					return;
					
				}
				
			//Traitement de l'option: dateInterrompu
			} else if(argArray[0]=="interruption") {
				
				try {
					
					argArray = argArray[1].split(" ", 2);
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			        Date parsed = format.parse(argArray[0]);
					ordinateur.setDateInterrompu(new java.sql.Date(parsed.getTime()).toLocalDate());
					
				} catch(ParseException e) {
					
					System.out.println(prop.getProperty("date_incorrect"));
					return;
					
				}
				
				break;
				
			//Traitement de l'option: fabricant
			} else if(argArray[0]=="fabricant") {
				
				try {
					
					argArray = argArray[1].split(" ", 2);
					int id = Integer.parseInt(argArray[0]);
					Entreprise fabricant = GestionEntreprise.getInstanceGestionEntreprise().findEntrepriseByID(id);
					
					if(fabricant != null) {
						
						ordinateur.setFabricant(fabricant);
						
					} else {
						
						System.out.println(prop.getProperty("id_entreprise_incorrect"));
						return;
						
					}
					
				} catch(NumberFormatException e) {
					
					System.out.println(prop.getProperty("id_entreprise_incorrect"));
					return;
					
				}
				
			//Option inconnu
			} else {
				
				System.out.println(argArray[0] + prop.getProperty("argument_incorrect"));
				return;
				
			}
			
			//Si pas d'autre option on update l'ordinateur
			if(argArray.length < 2) {
				
				GestionOrdinateur.getInstanceGestionOrdinateur().updateOrdinateur(ordinateur);
				return;
				
			}
			
			//Sinon on boucle
			args = argArray[1];
			
		}
		
		System.out.println(prop.getProperty("nombre_arg"));
		return;
		
	}
	
	//Fonction qui supprime un ordinateur existant
	private static void deleteOrdinateur(String arg) {
		
		try {
			
			long id = Integer.parseInt(arg);
			GestionOrdinateur.getInstanceGestionOrdinateur().suppressionOrdinateur(id);
			
		} catch(NumberFormatException e) {
			
			System.out.println(prop.getProperty("id_incorrect"));
			return;
			
		}
		
	}
		
	//Fonction qui aide l'utilisateur dans l'utilisation des commandes
	private static void help(String arg) {
		
		if(arg=="list") {
			
			System.out.println(prop.getProperty("syntaxe_list"));
			
		} else if(arg=="affiche") {
			
			System.out.println(prop.getProperty("syntaxe_affiche"));
			
		} else if(arg=="create") {
			
			System.out.println(prop.getProperty("syntaxe_create"));
			
		} else if(arg=="update") {
			
			System.out.println(prop.getProperty("syntaxe_update"));
			
		} else if(arg=="delete") {
			
			System.out.println(prop.getProperty("syntaxe_delete"));
			
		} else {
			
			System.out.println(arg + prop.getProperty("command_incorrect"));
			
		}
		
	}
	
}
