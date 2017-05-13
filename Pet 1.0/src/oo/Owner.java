package oo;

import clients.OwnersRegister;
import main.Main;
import structure.Data;

public class Owner {
	
	private int cod;
	private String cpf;
	private String name;
	private String email;

	static Data data = new Data();
	
	public int getCod(){
		
		return cod;
		
	}
	
	public void setCod(int cod){
		
		this.cod = cod;
		
	}
	
	public String getCpf() {
	
		return cpf;
		
	}
	
	public void setCpf(String cpf) {
		
		this.cpf = cpf;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public String getEmail() {
		
		return email;
		
	}
	
	public void setEmail(String email) {
		
		this.email = email;
		
	}
	
	public static void addToArrayList(Owner owner){
		
		Main.owners.add(owner);
		Main.countRegOwners++;
	}
	
	public static boolean searchToUpdate(String search){
		
		boolean found = false;
		String ownerCod;
		
		for(int i = 0; i < Main.owners.size(); i++){
			
			ownerCod = Integer.toString(Main.owners.get(i).getCod());
			
			if(search.equals(ownerCod) || search.equals(Main.owners.get(i).getCpf()) || search.equals(Main.owners.get(i).getName())){
				
				data.message("\n\n - Dados do cliente - \n\n"
						+ "1. CPF: "		+ Main.owners.get(i).getCpf() 
						+ "\n2. Nome: "		+ Main.owners.get(i).getName() 
						+ "\n3. E-mail: "	+ Main.owners.get(i).getEmail());
				
				int op = data.readInt("\n\nQue informação deseja alterar? ");
				
				switch(op){
				
				case 1:
					
					boolean cpfOk = false;
					
					while (cpfOk == false){
						
						String newCpf = data.readString("Digite o CPF: ");
						
						if(OwnersRegister.cpfIsOk(newCpf) == true){
							
							Main.owners.get(i).setCpf(newCpf);
							cpfOk = true;
							
						}
						
					}
					
					break;
					
				case 2:
					
					boolean nameOk = false;
					
					while (nameOk == false){
						
						String newName = data.readString("Digite o nome: ");
						
						if(OwnersRegister.nameIsOk(newName) == true){
							
							Main.owners.get(i).setName(newName);
							nameOk = true;
							
						}
						
					}
										
					break;
					
				case 3:
					
					boolean emailOk = false;
					
					while (emailOk == false){
						
						String newEmail =  data.readString("Digite o e-mail: ");
						
						if(OwnersRegister.emailIsOk(newEmail) == true){
							
							Main.owners.get(i).setEmail(newEmail);
							emailOk = true;
							
						}
						
					}
					
					break;
					
				}
				
				i = Main.owners.size();
				
				found = true;
				
			} else {
				
				found = false;
				
			}
			
		}
		
		return found;
		
	}
	
	public static boolean searchToRemove(String search){
		
		boolean found = false;
		String ownerCod;
		
		for(int i = 0; i < Main.owners.size(); i++){
			
			ownerCod = Integer.toString(Main.owners.get(i).getCod());
					
			if(search.equals(ownerCod) || search.equals(Main.owners.get(i).getCpf()) || search.equals(Main.owners.get(i).getName())){
				
				Main.owners.remove(i);
								
				found =  true;
				
			} else {
				
				found = false;
				
			}
			
		}
		
		return found;
			
	}
	
 	public static void listAll(){
		
		for(int i = 0; i < Main.owners.size(); i++){
			
			data.message(Main.owners.get(i).getCod() + " | " + Main.owners.get(i).getCpf() + " | "+ Main.owners.get(i).getName() + " | " + Main.owners.get(i).getEmail()+"\n");
			
		}
		
	}
	
}
