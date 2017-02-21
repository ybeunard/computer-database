package com.cdb.services;

import java.util.List;
import java.util.Scanner;

import com.cdb.dao.EntrepriseDao;
import com.cdb.dao.OrdinateurDao;
import com.cdb.persistance.Entreprise;
import com.cdb.persistance.Ordinateur;

public class GestionPagination {

	private int numeroPage;
	private int ligneParPage;
	private List<Ordinateur> pageOrdinateur;
	private List<Entreprise> pageEntreprise;
	
	public GestionPagination(){
		this.numeroPage = 1;
		this.ligneParPage = 20;
	}
	
	public void pagination(int typePage){
		do{
			switch(typePage){
			case 1:
				pageOrdinateur = OrdinateurDao.getInstanceOrdinateurDao().findOrdinateurByPage(numeroPage, ligneParPage);
				affichagePage(typePage);
				break;
			case 2:
				pageEntreprise = EntrepriseDao.getInstanceEntrepriseDao().findEntrepriseByPage(numeroPage, ligneParPage);
				affichagePage(typePage);
				break;
			default:
				System.out.println("Type de page inconnu");
				return;
			}
		}while(changementPage());
	}
	
	private boolean changementPage() {
		Scanner sc = new Scanner(System.in);
		String arg = sc.next();
		sc.close();
		switch(arg){
		case "b":
			numeroPage--;
			return true;
		case "n":
			numeroPage++;
			return true;
		default:
		}
		return false;
	}

	private void affichagePage(int typePage){
		switch(typePage){
		case 1:
			for(Ordinateur ordinateur : pageOrdinateur){
				System.out.print("Ordinateur numero "+ ordinateur.getId() + " : " + ordinateur.getName() + "\t" + ordinateur.getDateIntroduit() + "\t" + ordinateur.getDateInterrompu() + "\t");
		        if(ordinateur.getFabricant() != null){
		        	System.out.println(ordinateur.getFabricant().getName() + "\n");
		        }
		        else{
		        	System.out.println("NULL");
		        }
			}
			break;
		case 2:
			for(Entreprise entreprise : pageEntreprise){
				System.out.println(entreprise.getId() + "\t" + entreprise.getName());
			}
			break;
		default:
			System.out.println("Type de page inconnu");
			return;
		}
		System.out.println("PRECENT taper b\tNEXT taper n\tEXIT taper n'importe qu'elle touche");
	}
}
