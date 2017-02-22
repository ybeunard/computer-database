package com.cdb.persistance;

public class Entreprise {

	private int id;
	private String name;

	public Entreprise(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId(){
		return id;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString(){
		String chaine= this.id + "\t" + this.name;
		return chaine;
	}
}
