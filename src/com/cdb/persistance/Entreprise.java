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
}
