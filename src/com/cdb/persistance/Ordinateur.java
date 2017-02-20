package com.cdb.persistance;

import java.util.Date;

public class Ordinateur {

	private int id;
	private String name;
	private Date dateIntroduit;
	private Date dateInterrompu;
	private Entreprise fabricant;
	
	public Ordinateur(String name) {
		super();
		this.name = name;
	}
	
	public Ordinateur(int id, String name, Date dateIntroduit, Date dateInterrompu, Entreprise fabricant) {
		super();
		this.id = id;
		this.name = name;
		this.dateIntroduit = dateIntroduit;
		this.dateInterrompu = dateInterrompu;
		this.fabricant = fabricant;
	}

	public int getId() {
		return id;
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
