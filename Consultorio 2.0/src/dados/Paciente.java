package dados;

import java.util.Date;

public class Paciente {

	private String 	nome;
	private int 	rg;
	private Date	nasc;
	
	public Paciente(String nome, int rg, Date nasc){
		
		this.nome 	= nome;
		this.rg		= rg;
		this.nasc	= nasc;
		
	}
	
	public String getNome() {
		
		return nome;
		
	}
	
	public void setNome(String nome) {
		
		this.nome = nome;
		
	}
	
	public int getRg() {
		
		return rg;
		
	}
	
	public void setRg(int rg) {
		
		this.rg = rg;
		
	}
	
	public Date getNasc() {
		
		return nasc;
		
	}
	
	public void setNasc(Date nasc) {
		
		this.nasc = nasc;
		
	}
	
}
