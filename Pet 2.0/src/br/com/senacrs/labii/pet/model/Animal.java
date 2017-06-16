package br.com.senacrs.labii.pet.model;

import br.com.senacrs.labii.pet.util.Data;

public class Animal {

	private int 			cod;
	private String 			type;
	private String 			race;
	private String 			name;
	private Owner			owner;
	
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
	
	public Owner getOwner() {
		
		return owner;
		
	}
	
	public void setOwner(Owner owner) {
		
		this.owner = owner;
		
	}
	
	/*public static void addToArrayList(Animal animal){
		
		Main.animals.add(animal);
		Main.countRegAnimals++;
		
	}
		
	public static boolean searchToUpdate(String search){
		
		boolean found = false;
		String animalCod;
		
		for(int i = 0; i < Main.owners.size(); i++){
			
			animalCod = Integer.toString(Main.animals.get(i).getCod());
			
			if(search.equals(animalCod) || search.equals(Main.animals.get(i).getName())){
				
				data.message("\n\n - Dados do Animal - \n\n"
						+ "1. Type: "		+ Main.animals.get(i).getType() 
						+ "\n2. Ra�a: "		+ Main.animals.get(i).getRace() 
						+ "\n3. Nome: "		+ Main.animals.get(i).getName());
				
				int op = data.readInt("\n\nQue informa��o deseja alterar? ");
				
				switch(op){
				
				case 1:
					
					boolean typeOk = false;
					
					while (typeOk == false){
						
						data.message("- Type -\n\n1. Cachorro\n2. Gato\n3. Outros\n");
						
						int opType = data.readInt("Escolha um op��o: ");
						
						if(AnimalsRegister.verifyType(opType) != null){
							
							Main.animals.get(i).setType(AnimalsRegister.verifyType(opType));
							typeOk = true;
							
						}
						
					}
	 				
					break;
				
				case 2:
					
					boolean raceOk = false;
					
					while (raceOk == false){
						
						String newRace = data.readString("Digite a Ra�a: ");
						
						if(AnimalsRegister.raceIsOk(newRace) == true){
							
							Main.animals.get(i).setRace(newRace);
							raceOk = true;
						}
						
					}
										
					break;
					
				case 3:
					
					boolean nameOk = false;
					
					while (nameOk == false){
						
						String newName = data.readString("Digite o Nome: ");
						
						if(AnimalsRegister.nameIsOk(newName) == true){
							
							Main.animals.get(i).setName(newName);
							nameOk = true;
						}
						
					}
										
					break;
					
				}
				
				i = Main.animals.size();
				
				found = true;
				
			} else {
				
				found = false;
				
			}
			
		}
		
		return found;
		
	}
	
	public static boolean searchToRemove(String search){
		
		boolean found = false;
		String animalCod;
		
		for(int i = 0; i < Main.animals.size(); i++){
			
			animalCod = Integer.toString(Main.animals.get(i).getCod());
			
			if(search.equals(animalCod) || search.equals(Main.animals.get(i).getName())){
				
				Main.animals.remove(i);
								
				found =  true;
				
			} else {
				
				found = false;
				
			}
			
		}
		
		return found;
			
	}
	
	public static void listAll(){
		
		for(int i = 0; i < Main.animals.size(); i++){
			
			data.message(Main.animals.get(i).getCod() + " | " + Main.animals.get(i).getType() + " | " + Main.animals.get(i).getRace() + " | " + Main.animals.get(i).getName() + " | " + Main.animals.get(i).getOwner().getName()+"\n");
				
		}
		
	}*/
	
}
