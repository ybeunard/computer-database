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
	
<<<<<<< HEAD
	public Ordinateur(int id, String name, LocalDate dateIntroduit, LocalDate dateInterrompu, Entreprise fabricant) {
=======
	public Ordinateur(long id, String name, LocalDate dateIntroduit, LocalDate dateInterrompu, Entreprise fabricant) {
>>>>>>> d91cb8ed6581f2395b388995871dc39a4f142843
		
		super();
		this.id = id;
		this.name = name;
<<<<<<< HEAD
=======
		this.dateIntroduit = dateIntroduit;
		this.dateInterrompu = dateInterrompu;
>>>>>>> d91cb8ed6581f2395b388995871dc39a4f142843
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
<<<<<<< HEAD

		this.dateIntroduit = dateIntroduit;

=======
		
		this.dateIntroduit = dateIntroduit;
		
>>>>>>> d91cb8ed6581f2395b388995871dc39a4f142843
	}

	public LocalDate getDateInterrompu() {
		
		return dateInterrompu;
<<<<<<< HEAD

=======
		
>>>>>>> d91cb8ed6581f2395b388995871dc39a4f142843
	}

	public void setDateInterrompu(LocalDate dateInterrompu) {
		
		this.dateInterrompu = dateInterrompu;
<<<<<<< HEAD

=======
		
>>>>>>> d91cb8ed6581f2395b388995871dc39a4f142843
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
		
<<<<<<< HEAD
		if (this == obj) {
			
			return true;
			
		}
		
		if (obj == null) {
			
			return false;
			
		}
		
		if (getClass() != obj.getClass()) {
=======
		if(this == obj) {
			
			return true;
			
		} if(obj == null) {
			
			return false;
			
		} if(getClass() != obj.getClass()) {
>>>>>>> d91cb8ed6581f2395b388995871dc39a4f142843
			
			return false;
			
		}
		
		Ordinateur other = (Ordinateur) obj;
		
<<<<<<< HEAD
		if (dateInterrompu == null) {
			
			if (other.dateInterrompu != null) {
				
				return false;
			
			}
			
		} else if (!dateInterrompu.equals(other.dateInterrompu)) {
			
			return false;
			
		}
		
		if (dateIntroduit == null) {
			
			if (other.dateIntroduit != null) {
=======
		if(dateInterrompu == null) {
			
			if(other.dateInterrompu != null) {
				
				return false;
				
			}
			
		} else if(!dateInterrompu.equals(other.dateInterrompu)) {
			
			return false;
			
		} if(dateIntroduit == null) {
			
			if(other.dateIntroduit != null) {
>>>>>>> d91cb8ed6581f2395b388995871dc39a4f142843
				
				return false;
				
			}
			
<<<<<<< HEAD
		} else if (!dateIntroduit.equals(other.dateIntroduit)) {
			
			return false;
			
		}
		
		if (fabricant == null) {
			
			if (other.fabricant != null) {
=======
		} else if(!dateIntroduit.equals(other.dateIntroduit)) {
			
			return false;
			
		} if(fabricant == null) {
			
			if(other.fabricant != null) {
>>>>>>> d91cb8ed6581f2395b388995871dc39a4f142843
				
				return false;
				
			}
			
<<<<<<< HEAD
		} else if (!fabricant.equals(other.fabricant)) {
			
			return false;
			
		}
		
		if (id != other.id) {
			
			return false;
			
		}
		
		if (name == null) {
			
			if (other.name != null) {
=======
		} else if(!fabricant.equals(other.fabricant)) {
			
			return false;
			
		} if(id != other.id) {
			
			return false;
			
		} if(name == null) {
			
			if(other.name != null) {
>>>>>>> d91cb8ed6581f2395b388995871dc39a4f142843
				
				return false;
				
			}
			
<<<<<<< HEAD
		} else if (!name.equals(other.name)) {
=======
		} else if(!name.equals(other.name)) {
>>>>>>> d91cb8ed6581f2395b388995871dc39a4f142843
			
			return false;
			
		}
		
		return true;
		
	}

	@Override
<<<<<<< HEAD
	public String toString() {
=======
	public String toString(){
>>>>>>> d91cb8ed6581f2395b388995871dc39a4f142843
		
		String chaine = "Ordinateur numero ";
		chaine += this.id + " : " + this.name + "\t" + this.dateIntroduit + "\t" + this.dateInterrompu + "\t";
		
        if(this.fabricant != null) {
        	
        	chaine += this.fabricant.getName();
        	
<<<<<<< HEAD
        }
        
        else {
        	
        	chaine += "NULL";
=======
        } else {
        	
        	chaine += "NULL";
        	
>>>>>>> d91cb8ed6581f2395b388995871dc39a4f142843
        }
        
		return chaine;
		
	}
	
}
