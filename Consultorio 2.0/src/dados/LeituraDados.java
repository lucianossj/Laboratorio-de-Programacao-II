package dados;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LeituraDados {
	
	Scanner scan 	= new Scanner(System.in);
	Dados	dados 	= new Dados();
	
	public void mensagem(String mensagem){
		
		System.out.print(mensagem);
		
	}
	
	public String lerString(String mensagem){
		
		mensagem(mensagem);
		
		dados.setDadoString(scan.next());
		
		return dados.getDadoString();
		
	}
	
	public int lerInteiro(String mensagem){
		
		mensagem(mensagem);
		
		dados.setDadoInt(scan.nextInt());
		
		return dados.getDadoInt();
		
	}
	
	public Date lerDate(String mensagem) throws ParseException{
		
		mensagem(mensagem);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		dados.setDadoDate(sdf.parse(scan.next()));
		
		return dados.getDadoDate();
		
	}
	
	public Date lerDateHorario(String mensagem) throws ParseException{
		
		mensagem(mensagem);
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		
		dados.setDadoDate(sdf.parse(scan.next()));
		
		return dados.getDadoDate();
		
	}
	
	public int escolhaOpcoes(int opcao){
		
		int op = opcao;
		
		return op;
		
	}
	
}
