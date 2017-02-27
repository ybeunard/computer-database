package com.cdb.entities;

import java.time.LocalDate;

public class Ordinateur {

	private long id;
	private String name;
	private LocalDate dateIntroduit;
	private LocalDate dateInterrompu;
	private Entreprise fabricant;
	
	public Ordinateur(String name) {
		
		super();
		this.name = name;
		
	}
	
	public Ordinateur(int id, String name, LocalDate dateIntroduit, LocalDate dateInterrompu, Entreprise fabricant) {
		
		super();
		this.id = id;
		this.name = name;
		this.fabricant = fabricant;
		
	}

	public long getId() {
		
		return id;
		
	}

	public String getName() {
		
		return name;
		
	}

	public void setName(String name) {
		
		this.name = name;
		
	}

	public LocalDate getDateIntroduit() {
		
		return dateIntroduit;
		
	}

	public void setDateIntroduit(LocalDate dateIntroduit) {

		this.dateIntroduit = dateIntroduit;

	}

	public LocalDate getDateInterrompu() {
		
		return dateInterrompu;

	}

	public void setDateInterrompu(LocalDate dateInterrompu) {
		
		this.dateInterrompu = dateInterrompu;

	}

	public Entreprise getFabricant() {
		
		return fabricant;
		
	}

	public void setFabricant(Entreprise fabricant) {
		
		this.fabricant = fabricant;
		
	}
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateInterrompu == null) ? 0 : dateInterrompu.hashCode());
		result = prime * result + ((dateIntroduit == null) ? 0 : dateIntroduit.hashCode());
		result = prime * result + ((fabricant == null) ? 0 : fabricant.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
		
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			
			return true;
			
		}
		
		if (obj == null) {
			
			return false;
			
		}
		
		if (getClass() != obj.getClass()) {
			
			return false;
			
		}
		
		Ordinateur other = (Ordinateur) obj;
		
		if (dateInterrompu == null) {
			
			if (other.dateInterrompu != null) {
				
				return false;
			
			}
			
		} else if (!dateInterrompu.equals(other.dateInterrompu)) {
			
			return false;
			
		}
		
		if (dateIntroduit == null) {
			
			if (other.dateIntroduit != null) {
				
				return false;
				
			}
			
		} else if (!dateIntroduit.equals(other.dateIntroduit)) {
			
			return false;
			
		}
		
		if (fabricant == null) {
			
			if (other.fabricant != null) {
				
				return false;
				
			}
			
		} else if (!fabricant.equals(other.fabricant)) {
			
			return false;
			
		}
		
		if (id != other.id) {
			
			return false;
			
		}
		
		if (name == null) {
			
			if (other.name != null) {
				
				return false;
				
			}
			
		} else if (!name.equals(other.name)) {
			
			return false;
			
		}
		
		return true;
		
	}

	@Override
	public String toString() {
		
		String chaine = "Ordinateur numero ";
		chaine += this.id + " : " + this.name + "\t" + this.dateIntroduit + "\t" + this.dateInterrompu + "\t";
		
        if(this.fabricant != null) {
        	
        	chaine += this.fabricant.getName();
        	
        }
        
        else {
        	
        	chaine += "NULL";
        }
        
		return chaine;
		
	}
	
}
