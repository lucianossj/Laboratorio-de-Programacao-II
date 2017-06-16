package br.com.senacrs.labii.pet.model;

import br.com.senacrs.labii.pet.controller.OwnersRegister;
import br.com.senacrs.labii.pet.view.Main;
import br.com.senacrs.labii.pet.util.Data;

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
		
 	public static void listAll(){
		
		for(int i = 0; i < Main.owners.size(); i++){
			
			data.message(Main.owners.get(i).getCod() + " | " + Main.owners.get(i).getCpf() + " | "+ Main.owners.get(i).getName() + " | " + Main.owners.get(i).getEmail()+"\n");
			
		}
		
	}
	
}
