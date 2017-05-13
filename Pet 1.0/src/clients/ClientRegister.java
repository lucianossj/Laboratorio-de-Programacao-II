package clients;

import java.text.ParseException;

import main.Main;
import structure.Data;

public class ClientRegister {

	static Data data = new Data();
	
	public static void menu() throws ParseException{
		
		data.message("___________________________________\n\n"
				+ " - Cadastro de Clientes - \n\n"
				+ "1 - Clientes (Donos)\n"
				+ "2 - Animais\n"
				+ "3 - Voltar");
		
		int op = data.readInt("\n\nEscolha uma opção: ");
		
		switch(op){
		
			case 1:
				owners();
				break;
				
			case 2:
				animals();
				break;
				
			case 3:
				Main.mainMenu();
				break;
		
		}
		
	}
	
	static void owners() throws ParseException{
		
		OwnersRegister o = new OwnersRegister();
		
		o.menu();
		
	}
	
	static void animals() throws ParseException{
		
		AnimalsRegister.menu();
		
	}
	
}
