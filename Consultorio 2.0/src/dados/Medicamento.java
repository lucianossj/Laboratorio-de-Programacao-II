package dados;

public class Medicamento {

	private String 	nome;
	private int		cod;
	private String 	descricao;
	
	public Medicamento (String nome, int cod, String descricao){
		
		this.nome 		= nome;
		this.cod 		= cod;
		this.descricao	= descricao;
		
	}
	
	public String getNome() {
		
		return nome;
		
	}
	
	public void setNome(String nome) {
		
		this.nome = nome;
		
	}
	
	public int getCod() {
		
		return cod;
		
	}
	
	public void setCod(int cod) {
		
		this.cod = cod;
		
	}
	
	public String getDescricao() {
		
		return descricao;
		
	}
	
	public void setDescricao(String descricao) {
		
		this.descricao = descricao;
		
	}
	
}
