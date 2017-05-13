package procedures;

import java.text.ParseException;

import main.Main;
import oo.Owner;
import oo.Procedure;
import structure.Data;

public class ProceduresRegister {

	static Data data = new Data();
	
	public static void menu() throws ParseException{
		
		data.message("___________________________________\n\n"
				+ "- Cadastro de Procedimentos -\n\n"
				+ "1 - Cadastrar Procedimento\n"
				+ "2 - Editar Procedimento\n"
				+ "3 - Excluir Procedimento\n"
				+ "4 - Listar todos os procedimentos\n"
				+ "5 - Voltar");
		
		String op = data.readString("\n\nEscolha uma opção: ");
				
		switch(op){
		
			case "1":
				
				registerProcedure();
				break;
				
			case "2":
				
				if(Main.procedures.isEmpty()){
					
					data.message("\n\nERRO!!! Não existem procedimentos cadastrados! Tente novamente. \n\n");
					
					menu();
					
				} else {
					
					updateProcedure();
					
				}
				
				break;
				
			case "3":
				
				if(Main.procedures.isEmpty()){
					
					data.message("\n\nERRO!!! Não existem procedimentos cadastrados! Tente novamente. \n\n");
					
					menu();
					
				} else {
					
					removeProcedure();
					
				}
				
				break;
				
			case "4":
				
				if(Main.procedures.isEmpty()){
					
					data.message("\n\nERRO!!! Não existem procedimentos cadastrados! Tente novamente. \n\n");
					
					menu();
					
				} else {
					
					listProcedures();
					
					menu();
					
				}
				
				break;
				
			case "5":
				Main.mainMenu();
				break;
				
			default:
				data.message("\n\nERRO!!! Alternativa inválida! Tente novamente.\n\n");
				menu();
		}
		
	}
	
	static void registerProcedure() throws ParseException{
		
		data.message("\n\n - Cadastrar Procedimento -\n\n");
		
		Procedure procedure = new Procedure();
		
		boolean procOk = false, priceOk = false;
		
		while (procOk == false){
			
			String proc = data.readString("Procedimento: ");
		
			if(procIsOk(proc) == true){
				
				procedure.setProc(proc);
				procOk	= true;
				
				while (priceOk == false){
					
					double price = data.readDouble("Valor (R$): ");
					
					if(priceIsOk(price) == true){
						
						procedure.setPrice(price);
						priceOk	= true;
						
					}
					
				}
				
			}
			
		}
		
		procedure.setCod	(Main.countRegProcs + 1);
				
		Procedure.addToArrayList(procedure);
		
		data.message("\n\n .:: CADASTRADO COM SUCESSO!!!\n\n");
		
		menu();
		
	}
	static void updateProcedure() throws ParseException{
		
		if(Main.procedures.isEmpty()){
			
			data.message("\n\nERRO!!! Não há procedimentoss cadastrados!\n\n");
			
			menu();
			
		} else {
		
			data.message("\n\n - Alterar dados do Procedimento -\n\n");
			
			while(Procedure.searchToUpdate(data.readString("Digite o Código ou nome do Procedimento: ")) == false){
			
				data.message("\n\n .:: ERRO!!! Procedimento não encontrado!\n .:: Tente novamente.\n\n");
				
			} 
			
			data.message("\n\n .:: ALTERADO COM SUCESSO!!!\n\n");
			
			menu();
			
		}
		
	}
	
	static void removeProcedure() throws ParseException{
		
		if(Main.procedures.isEmpty()){
			
			data.message("\n\nERRO!!! Não há procedimentos cadastrados!\n\n");
			
			menu();
			
		} else {
		
			data.message("\n\n - Remover Procedimento -\n\n");
			
			while(Procedure.searchToRemove(data.readString("Digite o Código ou nome do Procedimento: ")) == false){
			
				data.message("\n\n .:: ERRO!!! Procedimento não encontrado!\n .:: Tente novamente.\n\n");
				
			} 
			
			data.message("\n\n .:: REMOVIDO COM SUCESSO!!!\n\n");
			
			menu();
			
		}
		
	}

	static void listProcedures() throws ParseException{
		
		if(Main.procedures.isEmpty()){
			
			data.message("\n\nERRO!!! Não há procedimentos cadastrados!\n\n");
			
			menu();
			
		} else {
		
			data.message("\n\n - Procedimentos - \n\n"
					+ "Cód. | Procedimento | Valor\n");
			
			Procedure.listAll();
			
			menu();
		
		}
		
	}
	
	public static boolean procIsOk(String proc){
		
		boolean hasNumbers = false;
		
		int contChars = proc.length();
		
		for(int i = 0; i < proc.length(); i++){
			
			contChars--;
			
			if(!Character.isLetter(proc.charAt(contChars))){
				
				hasNumbers = true;
				
			}
			
		}
		
		if(hasNumbers == true){
			
			data.message("\n.:: ERRO!!! \nDigite o procedimento corretamente.\n\n");
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}

	public static boolean priceIsOk(double price){
		
		if(price <= 0){
			
			data.message("\n.:: ERRO!!! \nDigite o valor corretamente.\n\n");
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
}
