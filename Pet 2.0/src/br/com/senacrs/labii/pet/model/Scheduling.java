package br.com.senacrs.labii.pet.model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.senacrs.labii.pet.view.Main;
import br.com.senacrs.labii.pet.view.ProceduresSchedulingView;
import br.com.senacrs.labii.pet.util.Data;

public class Scheduling {

	private int 	cod;
	private Animal 	animal;
	private Owner	owner;
	private Date	date;
	private Date	schedule;
	private double	price;			
	
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
	
	/*public ArrayList<Procedure> getProcedures() {
		
		return procedures;
		
	}

	public void setProcedures(ArrayList<Procedure> procedures) {
		
		this.procedures = procedures;
		
	}*/
	
	public double getPrice() {
		
		return price;
		
	}

	public void setPrice(double price) {
		
		this.price = price;
		
	}
	
}
