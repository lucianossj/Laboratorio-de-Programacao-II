package br.com.senacrs.labii.pet.view;

import br.com.senacrs.labii.pet.view.Main;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.senacrs.labii.pet.view.AnimalsRegister;
import br.com.senacrs.labii.pet.util.Data;

public class Reports {

	static Data data = new Data();
	
	public static void menu() throws ParseException{
		
		data.message("___________________________________\n\n"
				+ " - Relat�rios - \n\n"
				+ "1 - Animais cadastrados\n"
				+ "2 - Procedimentos realizados\n"
				+ "3 - Procedimentos do dia\n"
				+ "4 - Total recebido\n"
				+ "5 - Voltar");
		
		String op = data.readString("\n\nEscolha uma op��o: ");
				
		switch(op){
		
			case "1":
				
				AnimalsRegister.listAnimals();
					
				menu();
				
				break;
				
			case "2":
				realizedProcedures();
				
				menu();
				
				break;
				
			case "3":
				proceduresDay();
				
				menu();
				
				break;
				
			case "4":
				totalEarnings();
				
				menu();
				
				break;
				
			case "5":
				Main.mainMenu();
		
			default:
				data.message("\n\nERRO!!! Alternativa inv�lida! Tente novamente.\n\n");
				menu();
				
		}
		
	}
	
	static void realizedProcedures(){
		
		Date today 	= new Date(System.currentTimeMillis());
		
		if(Main.scheds.isEmpty()){
			
			data.message("\n\nERRO!!! N�o h� nenhum procedimento realizado!\n");
			
		} else {
		
			data.message("\n\n - Procedimentos realizados - \n\n");
			
			for(int i = 0; i < Main.scheds.size(); i++){
				
				if(Main.scheds.get(i).getDate().before(today)){
					
					Date date = Main.scheds.get(i).getDate();			
					SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
					String dateString = sdfDate.format(date);
					
					Date schedule = Main.scheds.get(i).getSchedule();			
					SimpleDateFormat sdfSchedule = new SimpleDateFormat("hh:mm");
					String scheduleString = sdfSchedule.format(schedule);
			
					data.message(Main.scheds.get(i).getCod() + " | " + Main.scheds.get(i).getAnimal().getName() + " | " + Main.scheds.get(i).getAnimal().getOwner().getName() + " | " + dateString + " | " + scheduleString + " | ");
	
					for (int j = 0; j < Main.scheds.get(i).getProcedures().size(); j++){
						
						data.message(Main.scheds.get(i).getProcedures().get(j).getProc() + " - ");
						
					}

					Double d = Main.scheds.get(i).getPrice();
					Locale ptBr = new Locale("pt", "BR");
					String priceString = NumberFormat.getCurrencyInstance(ptBr).format(d);
				
					data.message(" | "+priceString+"\n");
					
				}
			
			}
			
		}
		
	}
	
	static void proceduresDay(){
		
		Date today 	= new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String	stringToday = format.format(today);
		 
		if(Main.scheds.isEmpty()){
			
			data.message("\n\nERRO!!! N�o h� nenhum procedimento realizado!\n");
			
		} else {
		
			data.message("\n\n - Procedimentos de hoje - \n\n");
			
			for(int i = 0; i < Main.scheds.size(); i++){
				
				Date verifyDate	= new Date(System.currentTimeMillis());
				String	stringVD = format.format(verifyDate);
				
				if(stringVD.equals(stringToday)){
					
					Date date = Main.scheds.get(i).getDate();			
					String dateString = format.format(date);
					
					Date schedule = Main.scheds.get(i).getSchedule();			
					SimpleDateFormat sdfSchedule = new SimpleDateFormat("hh:mm");
					String scheduleString = sdfSchedule.format(schedule);
			
					data.message(Main.scheds.get(i).getCod() + " | " + Main.scheds.get(i).getAnimal().getName() + " | " + Main.scheds.get(i).getAnimal().getOwner().getName() + " | " + dateString + " | " + scheduleString + " | ");
	
					for (int j = 0; j < Main.scheds.get(i).getProcedures().size(); j++){
						
						data.message(Main.scheds.get(i).getProcedures().get(j).getProc() + " - ");
						
					}
					
					Double d = Main.scheds.get(i).getPrice();
					Locale ptBr = new Locale("pt", "BR");
					String priceString = NumberFormat.getCurrencyInstance(ptBr).format(d);
				
					data.message(" | "+priceString+"\n");
					
				}
			
			}
			
		}
		
	}
	
	static void totalEarnings(){
		
		Double d = Main.totalMoney;
		Locale ptBr = new Locale("pt", "BR");
		String priceString = NumberFormat.getCurrencyInstance(ptBr).format(d);
		
		data.message("\n\n - Total Ganho - \n\n");
		data.message(priceString+"\n");
		
	}
	
}
