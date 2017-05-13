
import java.text.ParseException;
import java.util.ArrayList;

import dados.*;

public class Main {

	static LeituraDados dados = new LeituraDados();
	
	static int op;
	
	static int indPaciente 		= 0;
	static int indMedicamento	= 0;
	static int indConsulta		= 0;
		
	public static ArrayList<Paciente> 		pacientes 		= new ArrayList<Paciente> ();
	public static ArrayList<Medicamento>            medicamentos            = new ArrayList<Medicamento> ();
	public static ArrayList<Consulta>		consultas		= new ArrayList<Consulta> ();
	
	public static void main(String[] args) throws ParseException{

		menuPrincipal();

	}

	static void menuPrincipal() throws ParseException {
		
		dados.mensagem("___________________________________\n");
		
		dados.mensagem("\n .:: Consult�rio :..\n\n"
				+ "1 - Cadastro de Pacientes\n"
				+ "2 - Cadastro de Medicamentos\n"
				+ "3 - Consultas\n"
				+ "4 - Sair\n\n");
		
		op = dados.escolhaOpcoes(dados.lerInteiro("Escolha uma op��o: "));
		
		switch(op){
		
		case 1:
			
			cadastroPacientes();
			break;
		
		case 2:
			cadastroMedicamentos();
			break;
			
		case 3:
			cadastroConsultas();
			break;
			
		case 4:
			dados.mensagem("\n\n.:: Encerrado. At� mais! ::.");
			System.exit(0);
			break;
		}
				
	}
	
	static void cadastroPacientes() throws ParseException{
		
		CadastroPacientes cPacientes = new CadastroPacientes();
		
		cPacientes.menu();
		
	}
	
	static void cadastroMedicamentos() throws ParseException{
		
		CadastroMedicamentos cMedicamentos = new CadastroMedicamentos();
		
		cMedicamentos.menu();
		
	}
	
	static void cadastroConsultas() throws ParseException{
		
		CadastroConsultas cConsultas = new CadastroConsultas();
		
		cConsultas.menu();
		
	}
	
}
