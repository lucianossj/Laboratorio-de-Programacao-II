package structure;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Data {

	Scanner	scan = new Scanner(System.in);
		
	public void message(String message){
		
		System.out.print(message);
		
	}
	
	public int readInt(String message){
	
		message(message);
		
		return scan.nextInt();
		
	}

	public String readString(String message){
		
		message(message);
		
		return scan.next();
		
	}
	
	public double readDouble(String message){
		
		message(message);
		
		return scan.nextDouble();
		
	}
	
	public Date readDate(String message) throws ParseException{
		
		message(message);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		return sdf.parse(scan.next());
		
	}
	
	public Date readSchedule(String message) throws ParseException{
		
		message(message);
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		
		return sdf.parse(scan.next());
		
	}
		
}
