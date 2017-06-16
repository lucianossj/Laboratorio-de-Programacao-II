package oo;

import java.text.NumberFormat;
import java.util.Locale;

import br.com.senacrs.labii.pet.view.Main;
import br.com.senacrs.labii.pet.view.ProceduresRegister;
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
	
	public static void addToArrayList(Procedure procedure){
		
		Main.procedures.add(procedure);
		Main.countRegProcs++;
		
	}
	
	public static boolean searchToUpdate(String search){
		
		boolean found = false;
		String procedureCod;
		
		for(int i = 0; i < Main.procedures.size(); i++){
			
			procedureCod = Integer.toString(Main.procedures.get(i).getCod());
			
			if(search.equals(procedureCod) || search.equals(Main.procedures.get(i).getProc())){
				
				data.message("\n\n - Dados do Procedimento - \n\n"
						+ "1. Procedimento: "	+ Main.procedures.get(i).getProc() 
						+ "\n2. Valor: "		+ Main.procedures.get(i).getPrice());
				
				String op = data.readString("\n\nQue informa��o deseja alterar? ");
				
				switch(op){
				
				case "1":
					
					boolean procOk = false;
					
					while (procOk == false){
						
						String proc = data.readString("Digite o nome do Procedimento: ");
						
						if(ProceduresRegister.procIsOk(proc) == true){
							
							Main.procedures.get(i).setProc(proc);
							procOk = true;
							
						}
						
					}
					
					break;
				
				case "2":
					
					boolean priceOk = false;
					
					while (priceOk == false){
						
						double newPrice = data.readDouble("Digite o valor: ");
						
						if(ProceduresRegister.priceIsOk(newPrice) == true){
							
							Main.procedures.get(i).setPrice(newPrice);
							priceOk = true;
						}
						
					}
										
					break;
					
				default:
					data.message("\n\nERRO!!! Alternativa inv�lida! Tente novamente.\n\n");
					searchToUpdate(search);
					
				}
				
				i = Main.procedures.size();
				
				found = true;
				
			} else {
				
				found = false;
				
			}
			
		}
		
		return found;
		
	}
	
	public static boolean searchToRemove(String search){
		
		boolean found = false;
		String procCod;
		
		for(int i = 0; i < Main.procedures.size(); i++){
			
			procCod = Integer.toString(Main.procedures.get(i).getCod());
			
			if(search.equals(procCod) || search.equals(Main.procedures.get(i).getPrice())){
				
				Main.procedures.remove(i);
								
				found =  true;
				
			} else {
				
				found = false;
				
			}
			
		}
		
		return found;
			
	}
	
	public static void listAll(){
		
		for(int i = 0; i < Main.procedures.size(); i++){
			
			data.message(Main.procedures.get(i).getCod() + " | " + Main.procedures.get(i).getProc());
			
			Double d = Main.procedures.get(i).getPrice();
			Locale ptBr = new Locale("pt", "BR");
			String priceString = NumberFormat.getCurrencyInstance(ptBr).format(d);
		
			data.message(" | "+priceString+"\n");
				
		}
		
	}
	
}
