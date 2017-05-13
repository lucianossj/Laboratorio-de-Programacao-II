package main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import clients.ClientRegister;
import oo.Animal;
import oo.Owner;
import oo.Procedure;
import oo.Scheduling;
import procedures.ProceduresRegister;
import procedures.ProceduresScheduling;
import structure.Data;

public class Main {
	
	static Data data = new Data();
	
	public static ArrayList<Owner> 	owners 			= new ArrayList<Owner>();  
	public static int countRegOwners 	= 0;
	
	public static ArrayList<Animal> animals 		= new ArrayList<Animal>();
	public static int countRegAnimals	= 0;
	
	public static ArrayList<Procedure> procedures 	= new ArrayList<Procedure>();
	public static int countRegProcs		= 0;
	
	public static ArrayList<Scheduling> scheds 		= new ArrayList<Scheduling>();
	public static int countRegScheds	= 0;
	
	public static double totalMoney;
	
	public static void main(String[] args) throws ParseException {
		
		//tests();
		mainMenu();

	}

	/*static void tests(){
		
		//Método criado com dados pré-definidos somente para a realização de testes
		
		oo.Owner owner = new oo.Owner();
		
		owner.setCod(1);
		owner.setCpf("111.222.333-33");
		owner.setEmail("luciano@senac.com");
		owner.setName("Luciano");
		
		owners.add(owner);
		
		oo.Animal animal = new oo.Animal();
		
		animal.setCod(1);
		animal.setOwner(owner);
		animal.setName("Bob");
		animal.setRace("Pastor");
		animal.setType("Cachorro");
		
		animals.add(animal);
		
		oo.Procedure p1 = new oo.Procedure();
		oo.Procedure p2 = new oo.Procedure();
		oo.Procedure p3 = new oo.Procedure();
		oo.Procedure p4 = new oo.Procedure();
		
		p1.setCod(1);
		p2.setCod(2);
		p3.setCod(3);
		p4.setCod(4);
		
		p1.setProc("Tosa");
		p2.setProc("Banho");
		p3.setProc("Consulta");
		p4.setProc("Vacina");
		
		p1.setPrice(15);
		p2.setPrice(25);
		p3.setPrice(60);
		p4.setPrice(100);
		
		procedures.add(p1);
		procedures.add(p2);
		procedures.add(p3);
		procedures.add(p4);
		
		oo.Scheduling s1 = new oo.Scheduling();
		
		Date today 	= new Date(System.currentTimeMillis());
		ArrayList <Procedure> ap = new ArrayList <Procedure>();
		ap.add(p1);
		ap.add(p2);
		
		s1.setCod(1);
		s1.setAnimal(animal);
		s1.setDate(today);
		s1.setSchedule(today);
		s1.setOwner(owner);
		s1.setProcedures(ap);
		s1.setPrice(p1.getPrice() + p2.getPrice());
		
		scheds.add(s1);
		
		totalMoney = totalMoney + s1.getPrice();
		
	}*/
	
	public static void mainMenu() throws ParseException{
		
		data.message("___________________________________\n\n"
				+ ".:: Menu Principal ::.\n\n"
				+ "1 - Cadastro de Clientes\n"
				+ "2 - Cadastro de Procedimentos\n"
				+ "3 - Agendamento de Procedimentos\n"
				+ "4 - Relatórios\n"
				+ "5 - Encerrar o sistema");
		
		String op = data.readString("\n\nEscolha uma opção: ");
				
		switch(op){
		
			case "1":
				clientRegister();
				break;
				
			case "2":
				proceduresRegister();
				break;
				
			case "3":
				proceduresScheduling();
				
			case "4":
				reports();
				
			case "5":
				data.message("\n___________________________________\n\n.:: SISTEMA FINALIZADO!!! ::.");
				System.exit(0);
		
			default:
				data.message("\n\nERRO!!! Alternativa inválida! Tente novamente.\n\n");
				mainMenu();
				
		}
		
	}
	
	static void clientRegister() throws ParseException{
		
		ClientRegister.menu();
		
	}
	
	static void proceduresRegister() throws ParseException{
		
		ProceduresRegister.menu();
		
	}
	
	static void proceduresScheduling() throws ParseException{
		
		ProceduresScheduling.menu();
		
	}
	
	static void reports() throws ParseException{
		
		Reports.menu();
		
	}
	
}
