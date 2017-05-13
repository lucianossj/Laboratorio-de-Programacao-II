package clients;

import java.text.ParseException;

import main.Main;
import oo.Animal;
import oo.Owner;
import structure.Data;

public class AnimalsRegister {

	static Data data = new Data();
	
	public static void menu() throws ParseException{
		
		data.message("\n___________________________________\n\n"
				+ " - Cadastro de Animais - \n\n"
				+ "1 - Cadastrar animal\n"
				+ "2 - Alterar animal\n"
				+ "3 - Excluir animal\n"
				+ "4 - Listar todos\n"
				+ "5 - Voltar");
		
		int op = data.readInt("\n\nEscolha uma opção: ");
		
		switch(op){
		
			case 1:
				registerAnimal();
				break;
				
			case 2:
				updateAnimal();
				break;
				
			case 3:
				removeAnimal();
				break;
		
			case 4:
				listAnimals();
				
				menu();
				
				break;
				
			case 5:
				ClientRegister.menu();
		
		}
		
	}
	
	static void registerAnimal() throws ParseException{
		
		data.message("\n\n - Cadastrar animal -\n\n");
		
		Animal animal = new Animal();
		
		boolean tipoOk = false, racaOk = false, nameOk = false, donoOk = false;
		
		while (tipoOk == false){
			
			data.message("- Tipo -\n\n1. Cachorro\n2. Gato\n3. Outros\n");
			
			int op = data.readInt("Escolha um opção: ");
			
			if(verifyType(op) != null){
				
				animal.setType(verifyType(op));
				tipoOk = true;
				
				while (racaOk == false){
					
					String raca = data.readString("Raça: ");
					
					if(raceIsOk(raca) == true){
						
						animal.setRace(raca);
						racaOk	= true;
				
						while (nameOk == false){
							
							String name = data.readString("Nome: ");
							
							if(nameIsOk(name) == true){
								
								animal.setName(name);
								nameOk	= true;
						
							}
						
							while (donoOk == false){
							
								String dono = data.readString("Código ou Nome do Dono: ");
								
								if(verifyOwner(dono) == true){
									
									for(int i = 0; i < Main.owners.size(); i++){
										
										if(dono.equals(Main.owners.get(i).getName()) || dono.equals(Integer.toString(Main.owners.get(i).getCod()))){
											
											animal.setOwner(Main.owners.get(i));
											donoOk = true;
											
										}
										
									}
									 
								}
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
		animal.setCod	(Main.countRegAnimals + 1);
				
		Animal.addToArrayList(animal);
		
		data.message("\n\n .:: CADASTRADO COM SUCESSO!!!\n\n");
				
		menu();
		
	}
	
	static void updateAnimal() throws ParseException{
		
		if(Main.animals.isEmpty()){
			
			data.message("\n\nERRO!!! Não há animais cadastrados!\n\n");
			
			menu();
			
		} else {
		
			data.message("\n\n - Alterar dados do Animal -\n\n");
			
			while(Animal.searchToUpdate(data.readString("Digite o Código ou nome do animal: ")) == false){
			
				data.message("\n\n .:: ERRO!!! Animal não encontrado!\n .:: Tente novamente.\n\n");
				
			} 
			
			data.message("\n\n .:: ALTERADO COM SUCESSO!!!\n\n");
			
			menu();
			
		}
		
	}
	
	static void removeAnimal() throws ParseException{
		
		if(Main.animals.isEmpty()){
			
			data.message("\n\nERRO!!! Não há animais cadastrados!\n\n");
			
			menu();
			
		} else {
		
			data.message("\n\n - Remover Animal -\n\n");
			
			while(Animal.searchToRemove(data.readString("Digite o Código ou nome do animal: ")) == false){
			
				data.message("\n\n .:: ERRO!!! Animal não encontrado!\n .:: Tente novamente.\n\n");
				
			} 
			
			data.message("\n\n .:: REMOVIDO COM SUCESSO!!!\n\n");
			
			menu();
			
		}
		
	}
	
	public static void listAnimals() throws ParseException{
		
		if(Main.animals.isEmpty()){
			
			data.message("\n\nERRO!!! Não há animais cadastrados!\n\n");
			
			menu();
			
		} else {
		
			data.message("\n\n - Clientes - Animais - \n\n"
					+ "Cód. | Tipo | Raça | Nome | Dono\n");
			
			Animal.listAll();
			
		}
		
	}
	
	public static String verifyType(int type){
			
		String op = Integer.toString(type);
		
		String typeAnimal = null;
		
		switch(op){
		
			case "1":
				typeAnimal = "Cachorro";
				break;
				
			case "2":
				typeAnimal = "Gato";
				break;
				
			case "3":
				typeAnimal = "Outros";
				break;
				
			default:
				data.message("\n\nERRO!!!\nDigite uma opção válida!!\n");
				break;
				
		}
		 
		return typeAnimal;
		
	}
	
	public static boolean verifyOwner(String owner){
		
		boolean found = false;
		
		for(int i  = 0; i < Main.owners.size(); i++){
		
			if(owner.equals(Integer.toString(Main.owners.get(i).getCod())) || owner.equals(Main.owners.get(i).getName())){
			
				found = true;
				
				i = Main.owners.size();
				
			} else {
				
				found = false;
				
			}
			
		}
		
		if(found == false){
			
			data.message("\nCliente não encontrado!!! Tente novamente.\n");
			
		}
			
		return found;
	
	}
	
	public static boolean raceIsOk(String race){
		
		boolean hasNumbers = false;
		
		int contChars = race.length();
		
		for(int i = 0; i < race.length(); i++){
			
			contChars--;
			
			if(!Character.isLetter(race.charAt(contChars))){
				
				hasNumbers = true;
				
			}
			
		}
		
		if(hasNumbers == true){
			
			data.message("\n.:: ERRO!!! \nDigite a raça corretamente.\n\n");
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
	public static boolean nameIsOk(String name){
		
		boolean hasNumbers = false;
		
		int contChars = name.length();
		
		for(int i = 0; i < name.length(); i++){
			
			contChars--;
			
			if(!Character.isLetter(name.charAt(contChars))){
				
				hasNumbers = true;
				
			}
			
		}
		
		if(hasNumbers == true){
			
			data.message("\n.:: ERRO!!! \nDigite o nome corretamente.\n\n");
			
			return false;
			
		} else {
			
			return true;
			
		}
	
	}
	
}
