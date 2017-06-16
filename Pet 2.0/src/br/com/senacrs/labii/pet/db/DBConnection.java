/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senacrs.labii.pet.db;

import br.com.senacrs.labii.pet.model.Owner;
import br.com.senacrs.labii.pet.util.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Luciano Junior
 */
public class DBConnection {
    
    Data data = new Data();
    
    public Connection connect(){
    
        Connection con = null;
        
        String path = "jdbc:postgresql://localhost:5432/petshop";
        String user = "postgres";
        String password = "123456";
        
        try{
        
            con = DriverManager.getConnection(path, user, password);
            
        }catch(SQLException e){
        
            data.message("Falha ao conectar ao Banco de Dados!!!");
            
        
        }
        
        return con;
        
    }
    
    public void insertOwners(Owner owner, Connection con) throws SQLException{
    
        String cpf          = owner.getCpf();
        String ownerName    = owner.getName();
        String email        = owner.getEmail();
        
        String sql = "INSERT INTO owners (cpf, nameOwner, email) VALUES ('"+cpf+"','"+ownerName+"','"+email+"');";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.execute();
        
        stmt.close();
        con.close();
        
    }
    
    public boolean verifyHasClients(Connection con) throws SQLException{
    
        boolean hasClients = false;
        
        String sql = "SELECT * FROM owners";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
        
            hasClients = false;
            
        } else {
        
            hasClients = true;
            
        }
        
        return hasClients;
        
    }
    
    public Owner searchClients(Connection con, String search) throws SQLException{
    
        String sql = "SELECT * FROM owners WHERE codOwner ='"+search+"' OR cpf = '"+search+"'";

        PreparedStatement stmt = con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
    
        Owner owner = new Owner();
        
        while(rs.next()){
        
            owner.setCod(rs.getInt("codOwner"));
            owner.setCpf(rs.getString("cpf"));
            owner.setName(rs.getString("nameOwner"));
            owner.setEmail(rs.getString("email"));
            
        }
        
        return owner;
        
    }
    
    public void updateOwners(Connection con, Owner owner) throws SQLException{
    
        String sql = "UPDATE owners SET cpf = '"+owner.getCpf()+"', nameOwner = '"+owner.getName()+"', email = '"+owner.getEmail()+"' WHERE codOwner = '"+owner.getCod()+"'";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.executeUpdate();
        
        stmt.close();
        con.close();
        
    }
    
    public void removeOwner(Connection con, Owner owner) throws SQLException{
    
        String sql = "DELETE FROM owners WHERE codOwner = '"+owner.getCod()+"'";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.executeUpdate();
        
        stmt.close();
        con.close();
        
    }
    
    public void listAllOwners(Connection con) throws SQLException{
    
        String sql = "SELECT * FROM owners;";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
        
            data.message(rs.getInt("codOwner")+ " | " + rs.getString("cpf") + " | " + rs.getString("nameOwner") + " | " 
            + rs.getString("email") +"\n");
            
        }
        
    }
    
    public boolean verifyOwner(String search){
    
        String sql = "SELECT * FROM animals WHERE "
        
    }
    
}
