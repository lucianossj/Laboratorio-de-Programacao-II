
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dados.Consulta;
import dados.LeituraDados;
import dados.Paciente;

public class CadastroConsultas {

	static LeituraDados dados = new LeituraDados();
	
	public void menu() throws ParseException{
		
		dados.mensagem("\n___________________________________");
		
		dados.mensagem("\n\n .:: Cadastro de Consultas :.. \n\n"
				+ "1 - Marcar consulta\n"
				+ "2 - Visualizar consultas\n"
				+ "3 - Desmarcar consulta\n"
				+ "4 - Voltar\n\n");
	
		int op = dados.lerInteiro("Escolha uma opção: ");
		
		switch(op){
		
		case 1:
			marcarConsulta();
			break;
			
		case 2:
			visualizarConsultas();
			break;
			
		case 3:
			desmarcarConsulta();
			break;
			
		case 4:
			Main.menuPrincipal();
			break;
		}
		
	}
	
	void marcarConsulta() throws ParseException{
		
		dados.mensagem("___________________________________\n");
		
		Date 		data;
		Date 		horario;
		Paciente	paciente = null;
			
		data = dataOk();
		horario		= dados.lerDateHorario	("Horário da consulta: ");
		paciente = pacienteEncontrado();
		
		Consulta consulta = new Consulta(data, horario, paciente);
		
		adicionarConsultaArray(consulta);
		
		dados.mensagem("\n\nConsulta marcada com sucesso!\n");
		
		this.menu();
		
	}
	
	Date dataOk() throws ParseException{
		
		boolean dataOk = false;
		
		Date data		= null;
		Date dataAtual 	= new Date(System.currentTimeMillis());
		
		while (dataOk == false){
		
			data		= dados.lerDate("\nData da consulta: ");
			
			if (dataAtual.before(data)){
				
				dataOk = true;
				
			} else {
				
				dados.mensagem("ERRO!!! A data solicitada já passou.\nTente novamente!\n");
				
			}
			
		}
		
		return data;
	}
	
	Paciente pacienteEncontrado(){
				
		boolean encontrado = false;
		
		String dadoPaciente;
		
		Paciente pacienteEncontrado = null;
		
		while (encontrado == false){
			
			dadoPaciente = dados.lerString("RG ou nome do Paciente: ");
			
			for (int i = 0; i < Main.pacientes.size(); i++){
				
				if(dadoPaciente.equals(Main.pacientes.get(i).getNome()) || dadoPaciente.equals(Integer.toString(Main.pacientes.get(i).getRg()))){
						
					String nome = Main.pacientes.get(i).getNome();
					int rg	 	= Main.pacientes.get(i).getRg();
					Date nasc 	= Main.pacientes.get(i).getNasc();
					
					pacienteEncontrado = new Paciente(nome, rg, nasc);
					
					encontrado = true;
					
				} 
		
			}
			
			if (encontrado == false) {
				
					
					dados.mensagem("\n\nERRO!!! Paciente não encontrado! Tente novamente!!!\n\n");
									
			}
			
		}

		return pacienteEncontrado;
		
	}
	
	void adicionarConsultaArray (Consulta consulta){
		
		Main.consultas.add(consulta);
		
		Main.indConsulta++;
		
	}
	
	DateFormat dfData = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat dfHora = new SimpleDateFormat("hh:mm");
	
	public void visualizarConsultas() throws ParseException{
		
		dados.mensagem("___________________________________");
		
		dados.mensagem("\nData | Horário | Paciente\n\n");
		
		for(int i = 0; i < Main.consultas.size(); i++){
			
			String data		= dfData.format(Main.consultas.get(i).getData());
			String horario 	= dfHora.format(Main.consultas.get(i).getHorario());
			
			dados.mensagem(data + " | " + horario + " | " + Main.consultas.get(i).getPaciente().getNome()+"\n");
			
		}

		this.menu();
		
	}
	
	public void desmarcarConsulta() throws ParseException{
		
		String 	pesquisaNome;
		Date 	pesquisaData;
		
		dados.mensagem("___________________________________\n\nDesmarcar Consulta\n\n");
		
		boolean encontrado = false;
		int index;
		
		while (encontrado == false){
		
		pesquisaNome = dados.lerString("\nDigite o nome Paciente: ");
		pesquisaData = dados.lerDate("Digita a data desejada: ");
			
			for(index = 0; index < Main.consultas.size() ; index++){
			
				if(pesquisaNome.equals(Main.consultas.get(index).getPaciente()) && pesquisaData.equals(Main.consultas.get(index).getData())){
				
					Main.consultas.remove(index);
				
					encontrado = true;
						
					dados.mensagem("Consulta desmarcada com sucesso!!!\n");
					
				} 
			
			}
			
			if (encontrado == false){
				
				dados.mensagem("\n\nConsulta não encontrada!!!\nTente novamente!!!\n\n");
				
			}
				
		}
		
		this.menu();
		
	}
	
}
