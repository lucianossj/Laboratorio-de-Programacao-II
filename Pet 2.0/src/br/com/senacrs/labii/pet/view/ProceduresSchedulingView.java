package br.com.senacrs.labii.pet.view;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import br.com.senacrs.labii.pet.model.Procedure;
import br.com.senacrs.labii.pet.model.Scheduling;
import br.com.senacrs.labii.pet.util.Data;

public class ProceduresSchedulingView {

	static Data data = new Data();
	
	public static void menu() throws ParseException{
		
		data.message("___________________________________\n\n"
				+ " - Agendamento de Procedimentos - \n\n"
				+ "1 - Agendar Procedimento\n"
				+ "2 - Editar agendamento\n"
				+ "3 - Cancelar agendamento\n"
				+ "4 - Listar todos os agendamentos\n"
				+ "5 - Voltar");
		
		String op = data.readString("\n\nEscolha uma opção: ");
				
		switch(op){
		
			case "1":
				
				scheduleProcedure();
				
				break;
				
			case "2":
				
				if(Main.scheds.isEmpty()){
					
					data.message("\n\nERRO!!! Não existem procedimentos agendados! Tente novamente. \n\n");
					
					menu();
					
				} else {
				
					updateScheduling();
				
				}
				
				break;
				
			case "3":
				
				if(Main.scheds.isEmpty()){
					
					data.message("\n\nERRO!!! N�o existem procedimentos agendados! Tente novamente. \n\n");
					
					menu();
					
				} else {
				
					cancelScheduling();
				
				}
				
				break;
				
			case "4":
				
				if(Main.scheds.isEmpty()){
					
					data.message("\n\nERRO!!! N�o existem procedimentos agendados! Tente novamente. \n\n");
					
					menu();
					
				} else {
				
					listAllSchedules();
				
				}
				
				break;
				
			case "5":
				Main.mainMenu();
				break;
				
			default:
				data.message("\n\nERRO!!! Alternativa inv�lida! Tente novamente.\n\n");
				menu();
				break;
				
		}
		
	}
	
	static void listAllSchedules() throws ParseException{
		
		data.message("\n\n - Todos os Agendamentos (GERAL) -\n\n"
				+ "C�d | Animal | Dono | Data | Hor�rio | Procedimentos\n");
		
		Scheduling.listAllSchedules();
		
		menu();
		
	}
	
}
