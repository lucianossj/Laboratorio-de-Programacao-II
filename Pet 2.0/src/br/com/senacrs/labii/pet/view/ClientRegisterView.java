package br.com.senacrs.labii.pet.view;

import br.com.senacrs.labii.pet.controller.AnimalsRegister;
import br.com.senacrs.labii.pet.controller.OwnersRegister;
import java.text.ParseException;
import br.com.senacrs.labii.pet.util.Data;
import java.sql.SQLException;

public class ClientRegisterView {

	static Data data = new Data();
	
	public static void menu() throws ParseException, SQLException{
		
		data.message("___________________________________\n\n"
				+ " - Cadastro de Clientes - \n\n"
				+ "1 - Clientes (Donos)\n"
				+ "2 - Animais\n"
				+ "3 - Voltar");
		
		String op = data.readString("\n\nEscolha uma opção: ");
		
		switch(op){
		
			case "1":
				menuOwners();
				break;
				
			case "2":
				menuAnimals();
				break;
				
			case "3":
				Main.mainMenu();
				break;
		
		}
		
	}
		
        public static void menuOwners() throws ParseException, SQLException{
		
		data.message("___________________________________\n\n"
				+ " - Cadastro de Donos - \n\n"
				+ "1 - Cadastrar dono\n"
				+ "2 - Alterar dono\n"
				+ "3 - Excluir dono\n"
				+ "4 - Listar todos\n"
				+ "5 - Voltar");
		
		String op = data.readString("\n\nEscolha uma opção: ");
		
                OwnersRegister ownersRegister = new OwnersRegister();
                
		switch(op){
		
			case "1":
                            ownersRegister.registerOwner();
                            break;
				
			case "2":
                            ownersRegister.updateOwner();
                            break;
				
			case "3":
                            ownersRegister.removeOwner();
                            break;
		
			case "4":
                            ownersRegister.listOwners();
                            break;
				
			case "5":
                            menu();
		
		}
		
	}
        
    public static void menuAnimals() throws ParseException, SQLException {

        data.message("\n___________________________________\n\n"
                + " - Cadastro de Animais - \n\n"
                + "1 - Cadastrar animal\n"
                + "2 - Alterar animal\n"
                + "3 - Excluir animal\n"
                + "4 - Listar todos\n"
                + "5 - Voltar");

        String op = data.readString("\n\nEscolha uma opção: ");

        AnimalsRegister animalsRegister = new AnimalsRegister();
        
        switch (op) {

            case "1":
                animalsRegister.registerAnimal();
                break;

            case "2":
                animalsRegister.updateAnimal();
                break;

            case "3":
                animalsRegister.removeAnimal();
                break;

            case "4":
                animalsRegister.listAnimals();
                break;

            case "5":
                menu();

        }

    }

}
