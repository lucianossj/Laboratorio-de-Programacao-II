package oo;

import br.com.senacrs.labii.pet.model.Animal;
import br.com.senacrs.labii.pet.model.Owner;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import br.com.senacrs.labii.pet.view.Main;
import procedures.ProceduresScheduling;
import br.com.senacrs.labii.pet.util.Data;

public class Scheduling {

	private int 					cod;
	private Animal 					animal;
	private Owner					owner;
	private Date					date;
	private Date					schedule;
	private ArrayList<Procedure> 	procedures;
	private double					price;			
	
	static Data data = new Data();
	
	public int getCod() {
		
		return cod;
		
	}
	
	public void setCod(int cod) {
	
		this.cod = cod;
		
	}
	
	public Animal getAnimal() {
	
		return animal;
		
	}
	
	public void setAnimal(Animal animal) {
	
		this.animal = animal;
		
	}
	
	public Owner getOwner() {
		
		return owner;
		
	}
	
	public void setOwner(Owner owner) {
	
		this.owner = owner;
		
	}
	
	public Date getDate() {
		
		return date;
		
	}
	
	public void setDate(Date date) {
		
		this.date = date;
		
	}
	
	public Date getSchedule() {
		
		return schedule;
		
	}
	
	public void setSchedule(Date schedule) {
		
		this.schedule = schedule;
		
	}
	
	public ArrayList<Procedure> getProcedures() {
		
		return procedures;
		
	}

	public void setProcedures(ArrayList<Procedure> procedures) {
		
		this.procedures = procedures;
		
	}
	
	public double getPrice() {
		
		return price;
		
	}

	public void setPrice(double price) {
		
		this.price = price;
		
	}

	public static void addToArrayList(Scheduling sched){
		
		Main.scheds.add(sched);
		Main.countRegScheds++;
		
	}
	
	public static boolean searchToUpdate(int search) throws ParseException{
		
		boolean found = false;
		int schedCod;
		
		for(int i = 0; i < Main.scheds.size(); i++){
			
			schedCod = Main.scheds.get(i).getCod();
			
			if(search == schedCod){
				
				data.message("\n\n - Dados do Agendamento - \n\n"
						+ "1. Animal: "		+ Main.scheds.get(i).getAnimal().getName() 
						+ "\n2. Data: "		+ Main.scheds.get(i).getDate() 
						+ "\n3. Hor�rio: "	+ Main.scheds.get(i).getSchedule());
				
				String op = data.readString("\n\nQue informa��o deseja alterar? ");
				
				switch(op){
				
				case "1":
					
					boolean animalOk = false;
					
					while (animalOk == false){
						
						String newAnimal = data.readString("Digite o nome do animal: ");
						
						if(ProceduresScheduling.verifyAnimal(newAnimal) == true){
							
							Animal animal = new Animal();
							
							for(int j = 0; j < Main.animals.size(); j++){
								
								if(newAnimal.equals(Main.animals.get(j).getName())){
									
									animal = Main.animals.get(j);
									
								}
								
							}
							
							Main.scheds.get(i).setAnimal(animal);
							Main.scheds.get(i).setOwner(Main.scheds.get(i).getAnimal().getOwner());
							animalOk = true;
							
						}
						
					}
					
					break;
					
				case "2":
					
					boolean dateOk = false;
					
					while (dateOk == false){
						
						Date newDate = data.readDate("Digite a data: ");
						
						if(ProceduresScheduling.dateIsOk(newDate) == true){
							
							Main.scheds.get(i).setDate(newDate);
							dateOk = true;
						}
						
					}
										
					break;
					
				case "3":
					
					boolean scheduleOk = false;
					
					while (scheduleOk == false){
						
						Date newSchedule = data.readDate("Digite o hor�rio: ");
						
						if(ProceduresScheduling.scheduleIsOk(newSchedule) == true){
							
							Main.scheds.get(i).setSchedule(newSchedule);
							scheduleOk = true;
							
						}
						
					}
					
					break;
					
				default:
					data.message("\n\nERRO!!! Alternativa inv�lida! Tente novamente.\n\n");
					ProceduresScheduling.updateScheduling();
							
				}
				
				i = Main.scheds.size();
				
				found = true;
				
			} else {
				
				found = false;
				
			}
			
		}
		
		return found;
		
	}
	
	public static boolean searchToRemove(int search){
		
		boolean found = false;
		int schedCod;
		
		for(int i = 0; i < Main.scheds.size(); i++){
			
			schedCod = Main.scheds.get(i).getCod();
			
			if(search == schedCod){
		
				Main.scheds.remove(i);
				found = true;
		
			} else {
				
				found = false;
				
			}
			
		}
		
		return found;
		
	}
	
	public static void listAllSchedules() throws ParseException{
		
		for(int i = 0; i < Main.scheds.size(); i++){
						
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
