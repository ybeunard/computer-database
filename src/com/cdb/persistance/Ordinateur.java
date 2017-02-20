package com.cdb.persistance;

import java.util.Date;

public class Ordinateur {

	private String name;
	private Date dateIntroduit;
	private Date dateInterrompu;
	private Entreprise fabricant;
	
	public Ordinateur(String name) {
		super();
		this.name = name;
	}
	
	public Ordinateur(String name, Date date, String typeDate) {
		super();
		this.name = name;
		switch(typeDate){
		case "introduit":
			this.dateIntroduit = date;
		case "interrompu":
			this.dateInterrompu = date;
		}
	}
	
	public Ordinateur(String name, Entreprise fabricant) {
		super();
		this.name = name;
		this.fabricant = fabricant;
	}
	
	public Ordinateur(String name, Date dateIntroduit, Date dateInterrompu) {
		super();
		this.name = name;
		this.dateIntroduit = dateIntroduit;
		this.dateInterrompu = dateInterrompu;
	}
	
	public Ordinateur(String name, Date date, Entreprise fabricant, String typeDate) {
		super();
		this.name = name;
		switch(typeDate){
		case "introduit":
			this.dateIntroduit = date;
		case "interrompu":
			this.dateInterrompu = date;
		}
		this.fabricant = fabricant;
	}
	
	public Ordinateur(String name, Date dateIntroduit, Date dateInterrompu, Entreprise fabricant) {
		super();
		this.name = name;
		this.dateIntroduit = dateIntroduit;
		this.dateInterrompu = dateInterrompu;
		this.fabricant = fabricant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateIntroduit() {
		return dateIntroduit;
	}

	public void setDateIntroduit(Date dateIntroduit) {
		this.dateIntroduit = dateIntroduit;
	}

	public Date getDateInterrompu() {
		return dateInterrompu;
	}

	public void setDateInterrompu(Date dateInterrompu) {
		this.dateInterrompu = dateInterrompu;
	}

	public Entreprise getFabricant() {
		return fabricant;
	}

	public void setFabricant(Entreprise fabricant) {
		this.fabricant = fabricant;
	}
}
