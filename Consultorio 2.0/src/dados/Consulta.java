package dados;

import java.util.Date;

public class Consulta {
	
	private Date data;
	private Date horario;
	private Paciente paciente;
	
	public Consulta(Date data, Date horario, Paciente paciente){
		
		this.data 		= data;
		this.horario	= horario;
		this.paciente	= paciente;
		
	}	
	
	public Date getData() {
		
		return data;
		
	}
	
	public void setData(Date data) {
		
		this.data = data;
		
	}
	
	public Date getHorario() {
		
		return horario;
		
	}
	
	public void setHorario(Date horario) {
		
		this.horario = horario;
		
	}
	
	public Paciente getPaciente() {
		
		return paciente;
		
	}
	
	public void setPaciente(Paciente paciente) {
		
		this.paciente = paciente;
		
	}
	
}
