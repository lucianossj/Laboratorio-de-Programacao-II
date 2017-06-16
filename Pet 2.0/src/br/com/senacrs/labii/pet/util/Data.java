package br.com.senacrs.labii.pet.util;

import br.com.senacrs.labii.pet.util.Console;
import br.com.senacrs.labii.pet.util.DateUtil;
import java.text.ParseException;
import java.util.Date;

public class Data {

    Console console = new Console();
    DateUtil dateUtil = new DateUtil();
    
    public void message(String message) {

        System.out.print(message);

    }

    public int readInt(String message) {

        message(message);

        return console.scanInt(message);

    }

    public String readString(String message) {

        message(message);

        return console.scanString(message);

    }

    public double readDouble(String message) {

        message(message);

        return console.scanDouble(message);
        
    }

    public Date readDate(String message) throws ParseException {

        String date = Console.scanString(message);
        return dateUtil.stringToDate(date);

    }

    public Date readSchedule(String message) throws ParseException {

        String schedule = Console.scanString(message);
        return dateUtil.stringToHour(schedule);

    }

}
