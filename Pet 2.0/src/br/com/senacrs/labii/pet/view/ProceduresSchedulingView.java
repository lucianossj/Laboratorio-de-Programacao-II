package br.com.senacrs.labii.pet.view;

import br.com.senacrs.labii.pet.controller.SchedulingRegister;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import br.com.senacrs.labii.pet.model.Procedure;
import br.com.senacrs.labii.pet.model.Scheduling;
import br.com.senacrs.labii.pet.util.Data;
import java.sql.SQLException;

public class ProceduresSchedulingView {

	static Data data = new Data();
	SchedulingRegister schedulingRegister = new SchedulingRegister();
        
	public void menu() throws ParseException, SQLException{
		
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
				
                            schedulingRegister.scheduleProcedure();
				
                            break;
				
			case "2":
								
                            schedulingRegister.updateScheduling();
				
                            break;
				
			case "3":
				
                            schedulingRegister.cancelScheduling();
			
                            break;
				
			case "4":
			
                            schedulingRegister.listAllSchedules();
			
                            break;
				
			case "5":
				Main.mainMenu();
				break;
				
			default:
				data.message("\n\nERRO!!! Alternativa inválida! Tente novamente.\n\n");
				menu();
				break;
				
		}
		
	}
		
}
