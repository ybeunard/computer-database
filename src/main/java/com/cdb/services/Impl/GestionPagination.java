package com.cdb.services.Impl;

import java.util.List;
import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.entities.Entreprise;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.services.InterfaceGestionPagination;
import com.cdb.ui.UserInterpreter;

public class GestionPagination implements InterfaceGestionPagination {

	//Variable pour la pagination
	private int numeroPage;
	private int ligneParPage;
	private List<Ordinateur> pageOrdinateur;
	private List<Entreprise> pageEntreprise;
	
	//constructeur standard
	public GestionPagination() {
		
		this.numeroPage = 1;
		this.ligneParPage = 100;
		
	}
	
	//constructeur pour spécifier le nombre de ligne par page
	public GestionPagination(int ligneParPage) {
		
		this.numeroPage = 1;
		
		if(ligneParPage > 0) {
			
			this.ligneParPage = ligneParPage;
			
		} else {
			
			this.ligneParPage = 100;
			
		}
		
	}
	
	//Fonction principal qui gere la pagination de la liste specifier
	public void pagination(int typePage) {
		
		do {
			
			try {
				
				switch(typePage) {
				
				//Liste computer
				case 1:
					
					//On recupere la liste avec le numero de page et le nombre de ligne par page
					pageOrdinateur = OrdinateurDao.getInstanceOrdinateurDao().findOrdinateurByPage(numeroPage, ligneParPage);
					
					//Si la liste est vide on revient à la page précédente
					if(pageOrdinateur.isEmpty()) {
						
						numeroPage--;
						pageOrdinateur = OrdinateurDao.getInstanceOrdinateurDao().findOrdinateurByPage(numeroPage, ligneParPage);
					
					}
					
					//On affiche la liste
					affichagePage(typePage);
					break;
					
				//liste company
				case 2:
					
					//On recupere la liste avec le numero de page et le nombre de ligne par page
					pageEntreprise = EntrepriseDao.getInstanceEntrepriseDao().findEntrepriseByPage(numeroPage, ligneParPage);
					
					//Si la liste est vide on revient à la page précédente
					if(pageEntreprise.isEmpty()) {
						
						numeroPage--;
						pageEntreprise = EntrepriseDao.getInstanceEntrepriseDao().findEntrepriseByPage(numeroPage, ligneParPage);
					
					}
					
					//On affiche la liste
					affichagePage(typePage);
					break;
				
					// liste incconu
				default:
					System.out.println("Type de page inconnu");
					return;
					
				}
				
			} catch(ConnexionDatabaseException e) {
	    		
	    		e.printStackTrace();
	    		
	    	} catch(RequeteQueryException e) {
	    		
	    		e.printStackTrace();
	    		
	    	}
			
		//On test si l'utilisateur veut continuer de consulter la liste ou sortir
		} while(changementPage());
		
	}
	
	//Fonction qui interroge l'utilisateur si il a envie de changer de page ou quitter la lecture de page.
	private boolean changementPage() {
		
		//Lecture
		String arg = UserInterpreter.sc.nextLine();
		
		//Page precedente
		if(arg=="b") {
			
			if(numeroPage>1) {
				
				numeroPage--;
				
			}
			
			return true;
			
		//Page suivante
		} else if(arg=="n") {
			
			numeroPage++;
			return true;
			
		}
		
		return false;
		
	}

	//Fonction qui permet l'affichage de la page selon la liste
	private void affichagePage(int typePage) {
		
		//Test le type de liste à afficher
		switch(typePage) {
		
		//Affiche la liste d'ordinateur
		case 1:
			
			for(Ordinateur ordinateur : pageOrdinateur) {
				
				System.out.println(ordinateur);
				
			}
			
			break;
			
		//affiche la liste d'entreprise
		case 2:
			
			for(Entreprise entreprise : pageEntreprise) {
				
				System.out.println(entreprise);
				
			}
			
			break;
			
		//Liste incconu
		default:
			
			System.out.println("Type de page inconnu");
			return;
			
		}
		
		//Affichage des options à l'utilisateur
		System.out.println("PRECENT taper b\tNEXT taper n\tEXIT taper n'importe qu'elle touche");
		
	}
	
}
