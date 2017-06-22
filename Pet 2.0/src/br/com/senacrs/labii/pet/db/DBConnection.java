/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senacrs.labii.pet.db;

import br.com.senacrs.labii.pet.model.Animal;
import br.com.senacrs.labii.pet.model.Owner;
import br.com.senacrs.labii.pet.model.Procedure;
import br.com.senacrs.labii.pet.util.Data;
import br.com.senacrs.labii.pet.view.ProceduresSchedulingView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author Luciano Junior
 */
public class DBConnection {

    Data data = new Data();

    public Connection connect() {

        Connection con = null;

        String path = "jdbc:postgresql://localhost:5432/petshop";
        String user = "postgres";
        String password = "123456";

        try {

            con = DriverManager.getConnection(path, user, password);

        } catch (SQLException e) {

            data.message("Falha ao conectar ao Banco de Dados!!!");

        }

        return con;

    }

    public void insertOwners(Connection con, Owner owner) throws SQLException {

        String cpf = owner.getCpf();
        String ownerName = owner.getName();
        String email = owner.getEmail();

        String sql = "INSERT INTO owners (cpf, nameOwner, email) VALUES ('" + cpf + "','" + ownerName + "','" + email + "');";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.execute();

        stmt.close();
        con.close();

    }

    public void insertAnimals(Connection con, Animal animal) throws SQLException {

        String type = animal.getType();
        String race = animal.getRace();
        String name = animal.getName();
        int codOwner = animal.getCodOwner();

        String sql = "INSERT INTO animals (typeAnimal, race, nameAnimal, codOwner) VALUES ('" + type + "',"
                + "'" + race + "', '" + name + "', '" + codOwner + "');";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.execute();

        stmt.close();
        con.close();

    }

    public boolean verifyHasClients(Connection con) throws SQLException {

        boolean hasClients = false;

        String sql = "SELECT * FROM owners";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        rs.next();

        if (rs.getInt("codOwner") == 0) {

            hasClients = false;

        } else {

            hasClients = true;

        }

        return hasClients;

    }

    public boolean verifyOwner(Connection con, int search) throws SQLException {

        boolean ownerIsOk;

        String sql = "SELECT * FROM animals WHERE codOwner = '" + search + "'";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        rs.next();

        int test = 0;
        test = rs.getInt("codOwner");

        if (test != 0) {

            ownerIsOk = true;

        } else {

            ownerIsOk = false;

        }

        return ownerIsOk;

    }

    public boolean verifyHasAnimals(Connection con) throws SQLException {

        boolean hasAnimals = false;

        String sql = "SELECT * FROM animals";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        rs.next();

        if (rs.getInt("codAnimal") == 0) {

            hasAnimals = false;

        } else {

            hasAnimals = true;

        }

        return hasAnimals;

    }

    public Owner searchClients(Connection con, String search) throws SQLException {

        String sql = "SELECT * FROM owners WHERE codOwner ='" + search + "' OR cpf = '" + search + "'";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        Owner owner = new Owner();

        while (rs.next()) {

            owner.setCod(rs.getInt("codOwner"));
            owner.setCpf(rs.getString("cpf"));
            owner.setName(rs.getString("nameOwner"));
            owner.setEmail(rs.getString("email"));

        }

        return owner;

    }

    public Animal searchAnimals(Connection con, String search) throws SQLException {

        String sql = "SELECT * FROM animals WHERE codAnimal ='" + search + "' OR nameAnimal = '" + search + "'";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        Animal animal = new Animal();

        while (rs.next()) {

            animal.setCod(rs.getInt("codAnimal"));
            animal.setType(rs.getString("type"));
            animal.setRace(rs.getString("race"));
            animal.setName(rs.getString("nameAnimal"));

        }

        return animal;

    }

    public void updateOwners(Connection con, Owner owner) throws SQLException {

        String sql = "UPDATE owners SET cpf = '" + owner.getCpf() + "', nameOwner = '" + owner.getName() + "', email = '" + owner.getEmail() + "' WHERE codOwner = '" + owner.getCod() + "'";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.executeUpdate();

        stmt.close();
        con.close();

    }

    public void updateAnimals(Connection con, Animal animal) throws SQLException {

        String sql = "UPDATE animals SET typeAnimal = '" + animal.getType() + "' race = '" + animal.getRace() + "' "
                + "nameAnimal = '" + animal.getName() + "' WHERE codAnimal = '" + animal.getCod() + "'";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.executeUpdate();

        stmt.close();
        con.close();

    }

    public void removeOwner(Connection con, Owner owner) throws SQLException {

        String sql = "DELETE FROM owners WHERE codOwner = '" + owner.getCod() + "'";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.executeUpdate();

        stmt.close();
        con.close();

    }

    public void removeAnimal(Connection con, Animal animal) throws SQLException {

        String sql = "DELETE FROM animals WHERE codAnimal = '" + animal.getCod() + "'";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.executeUpdate();

        stmt.close();
        con.close();

    }

    public void listAllOwners(Connection con) throws SQLException {

        String sql = "SELECT * FROM owners;";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            data.message(rs.getInt("codOwner") + " | " + rs.getString("cpf") + " | " + rs.getString("nameOwner") + " | "
                    + rs.getString("email") + "\n");

        }

    }

    public void listAllAnimals(Connection con) throws SQLException {

        String sql = "SELECT * FROM animals;";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            data.message(rs.getInt("codAnimal") + " | " + rs.getString("typeAnimal") + " | " + rs.getString("race") + " | "
                    + rs.getString("nameAnimal") + " | " + rs.getInt("coodOwner") + "\n");

        }

    }

    public void insertProcedures(Connection con, Procedure procedure) throws SQLException {

        String proc = procedure.getProc();
        double price = procedure.getPrice();

        String sql = "INSERT INTO procedures (proc, price) VALUES ('" + proc + "', '" + price + "');";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.execute();

        stmt.close();
        con.close();

    }

    public boolean verifyHasProcedures(Connection con) throws SQLException {

        boolean hasProcedures = false;

        String sql = "SELECT * FROM procedures";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        rs.next();

        if (rs.getInt("codProcedure") == 0) {

            hasProcedures = false;

        } else {

            hasProcedures = true;

        }

        return hasProcedures;

    }
    
    public void removeProcedure(Connection con, Procedure procedure) throws SQLException {

        String sql = "DELETE FROM procedures WHERE codProcedure = '" + procedure.getCod() + "'";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.executeUpdate();

        stmt.close();
        con.close();

    }
    
    public Procedure searchProcedures(Connection con, String search) throws SQLException {

        String sql = "SELECT * FROM procedures WHERE codProcedure ='" + search + "' OR proc = '" + search + "'";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        Procedure procedure = new Procedure();

        while (rs.next()) {

            procedure.setCod(rs.getInt("codProcedure"));
            procedure.setProc(rs.getString("proc"));
            procedure.setPrice(rs.getDouble("price"));

        }

        return procedure;

        stmt.close();
        rs.close();
        con.close();

    }
    
    public void listAllProcedures(Connection con) throws SQLException {

        String sql = "SELECT * FROM procedures;";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Double d = rs.getDouble("price");
            Locale ptBr = new Locale("pt", "BR");
            String priceString = NumberFormat.getCurrencyInstance(ptBr).format(d);

            data.message(rs.getInt("codProcedure") + " | " + rs.getString("proc") + " | " + priceString + "\n");

        }

        stmt.close();
        rs.close();
        con.close();

    }
    
    public boolean verifyAllData(Connection con) throws SQLException, ParseException{
            
        boolean allIsOk = true;
        
        String sql = "SELECT * FROM Owners";
        String sql2 = "SELECT * FROM Animals";
        String sql3 = "SELECT * FROM Procedures";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        PreparedStatement stmt2 = con.prepareStatement(sql2);
        PreparedStatement stmt3 = con.prepareStatement(sql3);
        
        ResultSet rs = stmt.executeQuery();
        ResultSet rs2 = stmt2.executeQuery();
        ResultSet rs3 = stmt3.executeQuery();
        
        rs.next();
        rs2.next();
        rs3.next();
        
        if(rs.getInt("codOwner") == 0){
        
            data.message("\n\nERRO!!! Não existem donos cadastrados! Tente novamente. \n\n");
            
            allIsOk = false;
            
            ProceduresSchedulingView.menu();
            
            
        } else if (rs2.getInt("codAnimal") == 0){
        
            data.message("\n\nERRO!!! Não existem animais cadastrados! Tente novamente. \n\n");
            
            allIsOk = false;
            
            ProceduresSchedulingView.menu();
            
        } else if (rs3.getInt("codProcedure") == 0){
        
            data.message("\n\nERRO!!! N�o existem procedimentos cadastrados! Tente novamente. \n\n");
            
            allIsOk = false;
            
            ProceduresSchedulingView.menu();
            
        }
        
        return allIsOk;
        
        stmt.close();
        rs.close();

        stmt2.close();
        rs2.close();

        stmt3.close();
        rs3.close();

        con.close();

    }

    public boolean verifySchedule(Connection con, Date schedule, Date date){

        boolean found = false;

        String sql = "SELECT * FROM schedules WHERE schedule = '"+ schedule +"' AND date = '"+ date +"'";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        rs.next();

        if(rs.getInt("codSched") == 0){

            found = false;

        } else {

            found = true;

        }

        return found;

        stmt.close();
        rs.close();
        con.close();

    }
    
    public Procedure verifyProc(Connection con, int cod){

        Procedure proc;

        String sql = "SELECT * FROM Procedures WHERE codProcedure = '"+ cod +"'";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        rs.next();

        if(rs.getInt("codProcedure") == 0){

            proc = null;

        } else {

            proc.setProc(rs.getString("procedure"));
            proc.setPrice(rs.getDouble("price"));

        }

        return proc;

        stmt.close();
        rs.close();
        con.close();

    }

    public void insertSchedules(Connection con, Schedule sched){

        int     animalCod   = sched.getAnimal().getCod();
        int     ownerCod    = sched.getAnimal().getCodOwner();
        Date    date        = sched.getDate();
        Date    schedule    = sched.getSchedule();
        Double  price       = sched.getPrice();

        String sql = "INSERT INTO Schedules (codAnimal, ownerCod, date, schedule, price) VALUES 
                        ('"+ animalCod +"', '"+ ownerCod +"', '"+ date +"', '"+ schedule +"', '"+ price +"');";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.executeUpdate();

        stmt.close();
        con.close();

    }

    public void insertSchedProcs(Connection con, ArrayList<Procedure> procs){

        String sql = "SELECT * FROM schedules DESC";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        rs.next();

        int codSched = rs.getInt("codSched");

        rs.close();

        for(int i = 0; i < procs.size(); i++){

            sql = "INSERT INTO SchedProcs (codProc, codSched) VALUES ('"+procs.get(i).getCod()+"', '"+codSched+"');";

            stmt = con.prepareStatement(sql);

            stmt.executeUpdate();

        }

        stmt.close();

        con.close();

    }

    public Scheduling searchScheds(Connection con, String search) throws SQLException {

        String sql = "SELECT * FROM Schedules WHERE codSched ='" + search + "';";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        Scheduling sched = new Scheduling();

        while (rs.next()) {

            sched.setCod(rs.getInt("codSched"));
            sched.setAnimal().setCod(rs.getInt("codAnimal"));
            sched.setOwner().setCod(rs.getInt("codOwner"));
            sched.setDate(rs.getDate("date"));
            sched.setSchedule(rs.getDate("schedule"));
            sched.setPrice(rs.getDouble("price"));

        }

        return sched;

    }

    public void removeSched(Connection con, Scheduling sched) throws SQLException {

        String sql = "DELETE FROM Schedules WHERE codSched = '" + sched.getCod() + "'";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.executeUpdate();

        stmt.close();
        con.close();

    }    

    public void listAllScheds(Connection con) throws SQLException {

        String sql = "SELECT * FROM Schedules;";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Double d = rs.getDouble("price");
            Locale ptBr = new Locale("pt", "BR");
            String priceString = NumberFormat.getCurrencyInstance(ptBr).format(d);

            DateUtil dateUtil = new DateUtil();

            String dateSched        = dateUtil.dateToString(rs.getDate("date"));
            String scheduleSched    = dateUtil.hourToString(rs.getDate("schedule"));

            data.message(rs.getInt("codSched") + " | " + rs.getInt("codAnimal") + " | " + rs.getString("codOwner") 
                + " | " + dateSched + " | " + scheduleSched + " | " + priceString + "\n");

        }

        stmt.close();
        rs.close();
        con.close();

    }

}
