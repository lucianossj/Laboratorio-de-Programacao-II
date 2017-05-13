
import java.text.ParseException;

import dados.LeituraDados;
import dados.Medicamento;

public class CadastroMedicamentos {

	static LeituraDados dados = new LeituraDados();
	
	String 	nome;
	int 	cod;
	String 	descricao;
	
	public void menu() throws ParseException{
		
		dados.mensagem("\n___________________________________");
		
		dados.mensagem(" \n\n.:: Cadastro de Medicamentos :.. \n\n"
				+ "1 - Cadastrar Medicamento\n"
				+ "2 - Visualizar medicamentos\n"
				+ "3 - Voltar\n\n");
	
	int op;
	
	op = dados.escolhaOpcoes(dados.lerInteiro("Escolha uma opção: "));
	
	switch(op){
	
		case 1:
			cadastrarMedicamento();
			break;
		
		case 2:
			visualizarMedicamentos();
			break;
		
		case 3:
			Main.menuPrincipal();
			break;
		
		}
	
	}
	
	public void adicionarMedicamentoArray(Medicamento med){
		
		Main.medicamentos.add(Main.indMedicamento, med);
		
		Main.indMedicamento++;
		
	}
	
	public void mostrarMedicamentos(){
				
		for(int i = 0; i < Main.medicamentos.size() ; i++) {
			
			 dados.mensagem("\n"+Main.medicamentos.get(i).getNome()+" | "+ Main.medicamentos.get(i).getCod()+ " | "+ Main.medicamentos.get(i).getDescricao());
						
		}
		
	}
	
	void cadastrarMedicamento() throws ParseException{
		
		dados.mensagem("___________________________________\n");
		
		nome 		= dados.lerString("\nMedicamento: ");
		cod			= dados.lerInteiro("Código: ");
		descricao	= dados.lerString("Descrição: ");
		
		Medicamento med = new Medicamento (nome, cod, descricao);
	
		adicionarMedicamentoArray(med);
		
		dados.mensagem("\n\n Cadastrado com sucesso!!!\n");
		
		this.menu();
		
	}
	
	void visualizarMedicamentos() throws ParseException{
		
		dados.mensagem("___________________________________");
		
		dados.mensagem("\nMedicamento | Cód. | Descrição\n");
		
		mostrarMedicamentos();
		
		this.menu();
		
	}
	
}
