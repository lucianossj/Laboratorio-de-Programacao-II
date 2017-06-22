package br.com.senacrs.labii.pet.model;

import br.com.senacrs.labii.pet.util.Data;

public class Procedure {

	private int 			cod;
	private String 			proc;
	private double 			price;
	
	static Data data = new Data();
	
	public int getCod() {
		
		return cod;
		
	}

	public void setCod(int cod) {
		
		this.cod = cod;
		
	}

	public String getProc() {
		
		return proc;
		
	}
	
	public void setProc(String proc) {
		
		this.proc = proc;
		
	}
	
	public double getPrice() {
		
		return price;
		
	}
	
	public void setPrice(double price) {
		
		this.price = price;
		
	}
	
}
