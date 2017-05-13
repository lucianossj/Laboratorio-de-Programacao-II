
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dados.LeituraDados;
import dados.Paciente;

public class CadastroPacientes {
			
	String 	nome;
	int		rg;
	Date	nasc;
	
	int op;
	
	LeituraDados dados = new LeituraDados();
			
	public void menu() throws ParseException {
		
		dados.mensagem("\n___________________________________");
		
		dados.mensagem("\n\n .:: Cadastro de Pacientes ::. \n\n"
				+ "1 - Cadastrar Paciente\n"
				+ "2 - Visualizar cadastrados\n"
				+ "3 - Voltar\n\n");
	
		op = dados.escolhaOpcoes(dados.lerInteiro("Escolha uma opção: "));
		
		switch(op){
		
		case 1:
			cadastrarPaciente();
			break;
			
		case 2:
			visualizarPaciente();
			break;
			
		case 3:
			Main.menuPrincipal();
			break;
			
		}
		
	}
	
	public void adicionarPacienteArray(Paciente pac){
		
		Main.pacientes.add(Main.indPaciente, pac);
			
		Main.indPaciente++;
			
	}
		
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
	public void mostrarPacientes() throws ParseException{
		
		for(int i = 0; i < Main.pacientes.size(); i++){
						   
			String dataNasc = df.format(Main.pacientes.get(i).getNasc());
			
			dados.mensagem("\n"+Main.pacientes.get(i).getNome()+" | "+ Main.pacientes.get(i).getRg() +" | "+ dataNasc);
				
		}
	
	}
	
	public void cadastrarPaciente() throws ParseException {
				
		dados.mensagem("___________________________________\n");
		
		nome 	= dados.lerString("\nNome do paciente: ");
		rg		= dados.lerInteiro("RG: ");
		nasc	= dados.lerDate("Data de nascimento: ");
		
		Paciente paciente = new Paciente (nome, rg, nasc);
		
		adicionarPacienteArray(paciente);
		
		dados.mensagem("\n\n Cadastrado com sucesso!!!\n");
		
		this.menu();
		
	}
	
	public void visualizarPaciente() throws ParseException {
		
		dados.mensagem("___________________________________");
		
		dados.mensagem("\nNome | RG | Data de Nascimento\n");
		
		mostrarPacientes();
		
		this.menu();
		
	}

}
