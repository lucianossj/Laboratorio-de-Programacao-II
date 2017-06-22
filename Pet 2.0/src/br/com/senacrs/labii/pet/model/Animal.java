package br.com.senacrs.labii.pet.model;

import br.com.senacrs.labii.pet.util.Data;

public class Animal {

	private int 			cod;
	private String 			type;
	private String 			race;
	private String 			name;
	private int			codOwner;
	
	static Data data = new Data();
	
	public String getType() {
	
		return type;
		
	}

	public void setType(String type) {
		
		this.type = type;
		
	}
	
	public int getCod(){
		
		return cod;
		
	}

	public void setCod(int cod){
		
		this.cod = cod;
		
	}
	
	public String getRace() {
		
		return race;
		
	}
	
	public void setRace(String race) {
		
		this.race = race;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public int getCodOwner() {
		
		return codOwner;
		
	}
	
	public void setCodOwner(int codOwner) {
		
		this.codOwner = codOwner;
		
	}
	
	/*
		
	
	
	
	
	public static void listAll(){
		
		for(int i = 0; i < Main.animals.size(); i++){
			
			data.message(Main.animals.get(i).getCod() + " | " + Main.animals.get(i).getType() + " | " + Main.animals.get(i).getRace() + " | " + Main.animals.get(i).getName() + " | " + Main.animals.get(i).getOwner().getName()+"\n");
				
		}
		
	}*/
	
}
