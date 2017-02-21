package com.cdb.persistance;

import java.sql.Date;

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
	
	public Ordinateur(int id, String name, Date dateIntroduit, Date dateInterrompu, Entreprise fabricant){
		super();
		this.id = id;
		this.name = name;
		if(dateIntroduit != null && dateInterrompu != null){
			if(dateIntroduit.before(dateInterrompu)){
				this.dateIntroduit = dateIntroduit;
				this.dateInterrompu = dateInterrompu;
			}
			else{
				System.out.println("Date Introduit n'est pas compatible avec dateInterrompu");
				this.dateIntroduit = null;
				this.dateInterrompu = null;
			}
		}
		else{
			this.dateIntroduit = dateIntroduit;
			this.dateInterrompu = dateInterrompu;
		}
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
	
	@Override
	public String toString(){
		String chaine = "Ordinateur numero ";
		chaine += this.id + " : " + this.name + "\t" + this.dateIntroduit + "\t" + this.dateInterrompu + "\t";
        if(this.fabricant != null){
        	chaine += this.fabricant.getName();
        }
        else{
        	chaine += "NULL";
        }
		return chaine;
	}
}
