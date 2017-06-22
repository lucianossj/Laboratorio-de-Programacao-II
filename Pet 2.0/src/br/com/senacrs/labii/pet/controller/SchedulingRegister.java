/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senacrs.labii.pet.controller;

import br.com.senacrs.labii.pet.db.DBConnection;
import br.com.senacrs.labii.pet.model.Animal;
import br.com.senacrs.labii.pet.model.Scheduling;
import br.com.senacrs.labii.pet.model.Procedure;
import br.com.senacrs.labii.pet.util.Data;
import br.com.senacrs.labii.pet.view.ProceduresRegisterView;
import br.com.senacrs.labii.pet.view.ProceduresSchedulingView;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Luciano Junior
 */
public class SchedulingRegister {

    Data data = new Data();
    DBConnection dbc = new DBConnection();

    public void scheduleProcedure() throws SQLException, ParseException {

        if (verifyAllData() == true) {

            data.message("\n\n - Agendar Procedimento -\n\n");

            Scheduling sched = new Scheduling();

            boolean animalOk = false, dateOk = false, scheduleOk = false, procsOk = false;

            while (animalOk == false) {

                String animalSearch = data.readString("Digite o código ou nome do Animal: ");

                Animal animal = new Animal();

                animal = verifyAnimal(animalSearch);

                if (animal != null) {

                    sched.setAnimal(animal);
                    animalOk = true;

                    while (dateOk == false) {

                        Date date = data.readDate("Data: ");

                        if (dateIsOk(date) == true) {

                            sched.setDate(date);
                            dateOk = true;

                            while (scheduleOk == false) {

                                Date schedule = data.readSchedule("Horário: ");

                                if (scheduleIsOk(schedule, date) == true) {

                                    sched.setSchedule(schedule);
                                    scheduleOk = true;

                                    ArrayList<Procedure> procs = new ArrayList<Procedure>();

                                    while (procsOk == false) {

                                        data.message("Procedimentos:\n\n"
                                                + "Cód. | Procedimento\n\n");

                                        dbc.listAllProcedures(dbc.connect());

                                        int cod = data.readInt("Digite o código do procedimento solicitado: ");

                                        Procedure p = verifyProc(cod);

                                        if (p != null) {

                                            procs.add(p);

                                        }

                                        String continueOp = "";

                                        while (!continueOp.equals("1") && !continueOp.equals("2")) {

                                            continueOp = data.readString("Deseja adicionar outro procedimento? (Sim - 1 | Não - 2): ");

                                        }

                                        if (continueOp.equals("2")) {

                                            Double totalPrice = 0.0;

                                            for(int i = 0; i < procs.size(); i++){

                                                totalPrice = totalPrice + procs.get(i).getPrice();

                                            }

                                            sched.setPrice(totalPrice);

                                            dbc.insertSchedules(dbc.connect(), sched);

                                            dbc.insertSchedProcs(dbc.connect(), procs);

                                            Locale ptBr = new Locale("pt", "BR");
                                            String priceString = NumberFormat.getCurrencyInstance(ptBr).format(totalPrice);

                                            data.message("\nValor total: R$ " + priceString + "\n\n");
                                            procsOk = true;

                                        }

                                    }

                                }

                            }

                        }

                    }

                }

            }

        }

        data.message("\n\n .:: AGENDADO COM SUCESSO!!!\n\n");

        ProceduresSchedulingView p = new ProceduresSchedulingView();
        
        p.menu();

    }

    public boolean verifyAllData() throws SQLException, ParseException {

        DBConnection dbc = new DBConnection();

        boolean allIsOk = dbc.verifyAllData(dbc.connect());

        return allIsOk;

    }

    public Animal verifyAnimal(String searchAnimal) throws SQLException {

        boolean found = false;

        Animal a = new Animal();

        a = dbc.searchAnimals(dbc.connect(), searchAnimal);

        if (a != null) {

            found = true;            

        } else {

            found = false;

        }

        if (found == false) {

            data.message("\nAnimal não encontrado!!! Tente novamente.\n");

        }

        return a;

    }

    public boolean dateIsOk(Date date) {

        boolean isOk = false;

        Date dataAtual = new Date(System.currentTimeMillis());

        if (date.before(dataAtual)) {

            data.message("\nA data solicitada já passou! \nTente novamente!\n");

        } else {

            isOk = true;

        }

        return isOk;

    }

    public boolean scheduleIsOk(Date schedule, Date date) throws SQLException {

        boolean isOk = dbc.verifySchedule(dbc.connect(), schedule, date);

        if (isOk == false) {

                data.message("\nJá existe um procedimento agendado para esse horário!\n"
                          + "Favor alterar o horário e tentar novamente!\n");

        }

        return isOk;

    }

    public Procedure verifyProc(int cod) throws SQLException {

        Procedure proc = dbc.verifyProc(dbc.connect(), cod);

        if(proc == null){

            data.message("\nCódigo inválido!\n"
                +"Digite um código de procedimento existente!\n");

        }

        return proc;

    }

    public void updateScheduling() throws ParseException, SQLException{
        
        ProceduresSchedulingView p = new ProceduresSchedulingView();
        
        if(dbc.verifyHasScheds(dbc.connect()) == false){

            data.message("\n\nERRO!!! Não há agendamentos cadastrados!\n\n");

            p.menu();
            
        } else {
        
            data.message("\n\n - Alterar dados do Agendamento -\n\n");
            
            while(searchToUpdate(data.readInt("Digite o Código do Agendamento: ")) == false){
            
                data.message("\n\n .:: ERRO!!! Agendamento não encontrado!\n .:: Tente novamente.\n\n");
                
            } 
            
            data.message("\n\n .:: ALTERADO COM SUCESSO!!!\n\n");
            
            ProceduresRegisterView.menu();
            
        }
        
    }

    public boolean searchToUpdate(int search) throws ParseException, SQLException{
        
        boolean found = false;

        Scheduling sched = new Scheduling();

        sched = dbc.searchScheds(dbc.connect(), search);
        
        if(sched != null){

            data.message("\n\n - Dados do Agendamento - \n\n"
                        + "1. Animal: "     + sched.getAnimal().getName() 
                        + "\n2. Data: "     + sched.getDate() 
                        + "\n3. Horário: "  + sched.getSchedule());

            String op = data.readString("\n\nQue informação deseja alterar? ");
                
            switch(op){
                
            case "1":
                    
                boolean animalOk = false;
                 
                while (animalOk == false){
                        
                    String newAnimal = data.readString("Digite o nome do animal: ");
                     
                    Animal animal = verifyAnimal(newAnimal);

                    if(animal != null){
                            
                        animalOk = true;
                        sched.setAnimal(animal);

                    }
                        
                }
                    
                break;
                    
            case "2":
                    
                boolean dateOk = false;
                    
                while (dateOk == false){
                        
                    Date newDate = data.readDate("Digite a data: ");
                        
                    if(dateIsOk(newDate) == true){
                            
                        sched.setDate(newDate);
                        dateOk = true;

                    }
                        
                }
                                        
                break;
                    
            case "3":
                    
                boolean scheduleOk = false;
                    
                while (scheduleOk == false){
                        
                    Date newSchedule = data.readDate("Digite o horário: ");
                        
                    if(scheduleIsOk(newSchedule, sched.getDate()) == true){
                            
                        sched.setSchedule(newSchedule);
                        scheduleOk = true;
                            
                    }
                        
                }
                    
                break;
                    
            default:
                data.message("\n\nERRO!!! Alternativa inválida! Tente novamente.\n\n");
                updateScheduling();
                            
            }
                
            found = true;
                
        } else {
                
            found = false;
                
        }
        
        return found;

    }

    public void cancelScheduling() throws ParseException, SQLException{
        
        ProceduresSchedulingView p = new ProceduresSchedulingView();
        
        if(dbc.verifyHasScheds(dbc.connect()) == true){
            
            data.message("\n\nERRO!!! Não há agendamentos cadastrados!\n\n");
            
            p.menu();
            
        } else {
            
            data.message("\n\n - Cancelar Agendamento -\n\n");
            
            while(searchToRemove(data.readInt("Digite o Código do Agendamento: ")) == false){
                
                data.message("\n\n .:: ERRO!!! Agendamento não encontrado!\n .:: Tente novamente.\n\n");
                
            }
            
            data.message("\n\n .:: REMOVIDO COM SUCESSO!!!\n\n");
            
            p.menu();
            
        }
        
    }

    public boolean searchToRemove(int search) throws SQLException{
        
        boolean found = false;
        
        Scheduling sched = new Scheduling();

        sched = dbc.searchScheds(dbc.connect(), search);
        
        if(sched != null){

            dbc.removeSched(dbc.connect(), sched);

            found = true;

        } else {

            found = false;

        }
        
        return found;
        
    }

    public void listAllSchedules() throws ParseException, SQLException{
        
        ProceduresSchedulingView p = new ProceduresSchedulingView();
        
        if(dbc.verifyHasScheds(dbc.connect()) == true){

            data.message("\n\nERRO!!! Não há agendamentos cadastrados!\n\n");
            
            p.menu();

        } else {

            data.message("\n\n - Agendamentos -\n\n"
                    + "Cód. | Animal | Dono | Data | Horário | Preço Total\n");

            dbc.listAllScheds(dbc.connect());

            p.menu();

        }
        
    }

}
