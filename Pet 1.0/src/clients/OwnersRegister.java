package clients;

import java.text.ParseException;

import main.Main;
import oo.Owner;
import structure.Data;

public class OwnersRegister {

	static Data data = new Data();
	
	public void menu() throws ParseException{
		
		data.message("___________________________________\n\n"
				+ " - Cadastro de Donos - \n\n"
				+ "1 - Cadastrar dono\n"
				+ "2 - Alterar dono\n"
				+ "3 - Excluir dono\n"
				+ "4 - Listar todos\n"
				+ "5 - Voltar");
		
		int op = data.readInt("\n\nEscolha uma opção: ");
		
		switch(op){
		
			case 1:
				registerOwner();
				break;
				
			case 2:
				updateOwner();
				break;
				
			case 3:
				removeOwner();
				break;
		
			case 4:
				listOwners();
				break;
				
			case 5:
				ClientRegister.menu();
		
		}
		
	}
	
	void registerOwner() throws ParseException{
		
		data.message("\n\n - Cadastrar dono -\n\n");
		
		Owner owner = new Owner();
		
		boolean cpfOk = false, nameOk = false, emailOk = false;
		
		while (cpfOk == false){
			
			String cpf = data.readString("CPF: ");
		
			if(cpfIsOk(cpf) == true){
				
				owner.setCpf(cpf);
				cpfOk	= true;
				
				while (nameOk == false){
					
					String name = data.readString("Nome: ");
					
					if(nameIsOk(name) == true){
						
						owner.setName(name);
						nameOk	= true;
						
						while (emailOk == false){
							
							String email = data.readString("E-mail: ");
											
							if(emailIsOk(email) == true){
								
								owner.setEmail(email);
								emailOk = true;
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
		owner.setCod	(Main.countRegOwners + 1);
				
		Owner.addToArrayList(owner);
		
		data.message("\n\n .:: CADASTRADO COM SUCESSO!!!\n\n");
		
		menu();
		
	}
	
	void updateOwner() throws ParseException{
		
		if(Main.owners.isEmpty()){
			
			data.message("\n\nERRO!!! Não há clientes cadastrados!\n\n");
			
			menu();
			
		} else {
		
			data.message("\n\n - Alterar dados do Cliente -\n\n");
			
			while(Owner.searchToUpdate(data.readString("Digite o Código, CPF ou nome do cliente: ")) == false){
			
				data.message("\n\n .:: ERRO!!! Cliente não encontrado!\n .:: Tente novamente.\n\n");
				
			} 
			
			data.message("\n\n .:: ALTERADO COM SUCESSO!!!\n\n");
			
			menu();
			
		}
		
	}
	
	void removeOwner() throws ParseException{
		
		if(Main.owners.isEmpty()){
			
			data.message("\n\nERRO!!! Não há clientes cadastrados!\n\n");
			
			menu();
			
		} else {
		
			data.message("\n\n - Remover Cliente -\n\n");
			
			while(Owner.searchToRemove(data.readString("Digite o Código, CPF ou nome do cliente: ")) == false){
			
				data.message("\n\n .:: ERRO!!! Cliente não encontrado!\n .:: Tente novamente.\n\n");
				
			} 
			
			data.message("\n\n .:: REMOVIDO COM SUCESSO!!!\n\n");
			
			menu();
			
		}
		
	}
	
	void listOwners() throws ParseException{
		
		if(Main.owners.isEmpty()){
			
			data.message("\n\nERRO!!! Não há clientes cadastrados!\n\n");
			
			menu();
			
		} else {
		
			data.message("\n\n - Clientes - DONOS - \n\n"
					+ "Cód. | CPF           | Nome | E-mail\n");
			
			Owner.listAll();
			
			menu();
		
		}
		
	}
	
	public static boolean cpfIsOk(String cpf){
		 
		boolean hasLetters = false;
			
		int contChars = cpf.length();
			
		for (int i = 0; i < cpf.length(); i++){
			
			contChars--;
				
			if(Character.isLetter(cpf.charAt(contChars))){
					
				hasLetters = true;
					
			}
				
		}
					 
		if (cpf.length() < 14 || !cpf.contains(".") || !cpf.contains("-") || hasLetters == true || cpf.isEmpty()){
				
			data.message("\n.:: ERRO!!! \nDigite o CPF corretamente. Formato (xxx.xxx.xxx-xx)\n\n");
			
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
		
		if(hasNumbers == true || name.isEmpty()){
			
			data.message("\n.:: ERRO!!! \nDigite o nome corretamente.\n\n");
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
	public static boolean emailIsOk(String email){
		
		if(!email.contains("@") || !email.contains(".") || email.isEmpty()){
			
			data.message("\n.:: ERRO!!! \nDigite o e-mail corretamente.\n\n");
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
}
