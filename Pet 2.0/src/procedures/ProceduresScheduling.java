package procedures;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import br.com.senacrs.labii.pet.view.Main;
import oo.Procedure;
import oo.Scheduling;
import br.com.senacrs.labii.pet.util.Data;

public class ProceduresScheduling {

	static Data data = new Data();
	
	public static void menu() throws ParseException{
		
		data.message("___________________________________\n\n"
				+ " - Agendamento de Procedimentos - \n\n"
				+ "1 - Agendar Procedimento\n"
				+ "2 - Editar agendamento\n"
				+ "3 - Cancelar agendamento\n"
				+ "4 - Listar todos os agendamentos\n"
				+ "5 - Voltar");
		
		String op = data.readString("\n\nEscolha uma op��o: ");
				
		switch(op){
		
			case "1":
				
				if(Main.owners.isEmpty()){
					
					data.message("\n\nERRO!!! N�o existem donos cadastrados! Tente novamente. \n\n");
					
					menu();
					
				} else if(Main.animals.isEmpty()){
					
					data.message("\n\nERRO!!! N�o existem animais cadastrados! Tente novamente. \n\n");
					
					menu();
					
				} else if(Main.procedures.isEmpty()){
					
					data.message("\n\nERRO!!! N�o existem procedimentos cadastrados! Tente novamente. \n\n");
					
					menu();
					
				} else {
					
					scheduleProcedure();
					
				}
				
				break;
				
			case "2":
				
				if(Main.scheds.isEmpty()){
					
					data.message("\n\nERRO!!! N�o existem procedimentos agendados! Tente novamente. \n\n");
					
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
	
	static void scheduleProcedure() throws ParseException{
		
		data.message("\n\n - Agendar Procedimento -\n\n");
		
		Scheduling sched = new Scheduling();
		
		boolean animalOk = false, dateOk = false, scheduleOk = false, procsOk = false;
		
		while (animalOk == false){
			
			String animal = data.readString("Digite o c�digo ou nome do Animal: ");
		
			if(verifyAnimal(animal) == true){
				
				for(int i = 0; i < Main.animals.size(); i++){
					
					if(animal.equals(Integer.toString(Main.animals.get(i).getCod())) || animal.equals(Main.animals.get(i).getName())){
						
						sched.setAnimal(Main.animals.get(i));
						animalOk = true;
						
					}
					
				}
						
				while (dateOk == false){
							
					Date date = data.readDate("Data: ");
											
					if(dateIsOk(date) == true){
								
						sched.setDate(date);
						dateOk = true;
								
						while (scheduleOk == false){
									
							Date schedule = data.readSchedule("Hor�rio: ");
									
							if(scheduleIsOk(schedule) == true){ 
										
								sched.setSchedule(schedule);
								scheduleOk = true;
								
								ArrayList <Procedure> procs = new ArrayList <Procedure> ();
								
								double countPrice = 0;
								
								while (procsOk == false){
									
									data.message("Procedimentos:\n\n"
											+ "C�d. | Procedimento\n\n");
									
									for(int i = 0; i < Main.procedures.size(); i++){
										
										data.message(Main.procedures.get(i).getCod()+". "+Main.procedures.get(i).getProc()+"\n");
										
									}
									
									int cod = data.readInt("Digite o c�digo do procedimento solicitado: ");
									
									if(verifyProc(cod) == true){
										
										for(int i = 0; i < Main.procedures.size(); i++){
											
											if(cod == Main.procedures.get(i).getCod()){
										
												procs.add(Main.procedures.get(i));
												
												countPrice = countPrice + Main.procedures.get(i).getPrice();
		
												Double d = countPrice;
												Locale ptBr = new Locale("pt", "BR");
												String priceString = NumberFormat.getCurrencyInstance(ptBr).format(d);
											
												data.message("\nValor total: R$ "+priceString+"\n\n");
												
											}
											
										}
									
										String continueOp = "";
										
										while(!continueOp.equals("1") && !continueOp.equals("2")){
										
											continueOp = data.readString("Deseja adicionar outro procedimento? (Sim - 1 | N�o - 2): ");
											
										}
										
										if(continueOp.equals("2")){
											
											sched.setProcedures(procs);
											sched.setPrice(countPrice);
											procsOk = true;                                                                                                   
											
											Main.totalMoney = Main.totalMoney + countPrice;
											
										}
										
									}
									
								}
								
							}
								
						} 
							
					}
					
				}
				
			}
			
		}
		
		sched.setCod	(Main.countRegScheds + 1);
				
		Scheduling.addToArrayList(sched);
		
		data.message("\n\n .:: AGENDADO COM SUCESSO!!!\n\n");
		
		menu();
		
	}
	
	public static void updateScheduling() throws ParseException{
		
		if(Main.scheds.isEmpty()){
			
			data.message("\n\nERRO!!! N�o h� agendamentos cadastrados!\n\n");
			
			menu();
			
		} else {
		
			data.message("\n\n - Alterar dados do Agendamento -\n\n");
			
			while(Scheduling.searchToUpdate(data.readInt("Digite o C�digo do Agendamento: ")) == false){
			
				data.message("\n\n .:: ERRO!!! Agendamento n�o encontrado!\n .:: Tente novamente.\n\n");
				
			} 
			
			data.message("\n\n .:: ALTERADO COM SUCESSO!!!\n\n");
			
			menu();
			
		}
		
	}
	
	static void cancelScheduling() throws ParseException{
		
		if(Main.scheds.isEmpty()){
			
			data.message("\n\nERRO!!! N�o h� agendamentos cadastrados!\n\n");
			
			menu();
			
		} else {
			
			data.message("\n\n - Cancelar Agendamento -\n\n");
			
			while(Scheduling.searchToRemove(data.readInt("Digite o C�digo do Agendamento: ")) == false){
				
				data.message("\n\n .:: ERRO!!! Agendamento n�o encontrado!\n .:: Tente novamente.\n\n");
				
			}
			
			data.message("\n\n .:: REMOVIDO COM SUCESSO!!!\n\n");
			
			menu();
			
		}
		
	}
	
	static void listAllSchedules() throws ParseException{
		
		data.message("\n\n - Todos os Agendamentos (GERAL) -\n\n"
				+ "C�d | Animal | Dono | Data | Hor�rio | Procedimentos\n");
		
		Scheduling.listAllSchedules();
		
		menu();
		
	}
	
	public static boolean verifyAnimal(String searchAnimal){
		
		boolean found = false;
		
		for(int i  = 0; i < Main.animals.size(); i++){
		
			if(searchAnimal.equals(Integer.toString(Main.animals.get(i).getCod())) || searchAnimal.equals(Main.animals.get(i).getName())){
			
				found = true;
				
				i = Main.animals.size();
				
			} else {
			
				found = false;
								
			}
			
		}
		
		if(found == false){
		
			data.message("\nAnimal n�o encontrado!!! Tente novamente.\n");
			
		}
		
		return found;
		
	}
		
	public static boolean dateIsOk(Date date){
		
		boolean isOk = false;
		
		Date dataAtual 	= new Date(System.currentTimeMillis());
		
		if(date.before(dataAtual)){
			
			data.message("\nA data solicitada j� passou! \nTente novamente!\n");
			
		}  else {
			
			isOk = true;
			
		}
		
		return isOk;
		
	}
	
	public static boolean scheduleIsOk(Date date){
		
		boolean isOk = false;
		
		if(Main.scheds.isEmpty()){
			
			isOk = true;
			
		} else {
		
			for(int i = 0; i < Main.scheds.size(); i++){
				
				if(date.equals(Main.scheds.get(i).getDate())){
					
					data.message("\nJ� existe um procedimento agendado para esse hor�rio!\n"
							+ "Favor alterar o hor�rio e tentar novamente!\n");
					
				} else {
					
					isOk = true;
					
				}
				
			}
			
		}
		
		return isOk;
		
	}
	
	public static boolean verifyProc(int cod){
		
		boolean isOk = false;
		
		for(int i = 0; i < Main.procedures.size(); i++){
			
			if(cod == Main.procedures.get(i).getCod()){
				
				isOk = true;
				
			}
			
		}
		
		return isOk;
		
	}
	
}
