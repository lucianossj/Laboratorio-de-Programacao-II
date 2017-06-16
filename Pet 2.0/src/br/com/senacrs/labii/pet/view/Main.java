package br.com.senacrs.labii.pet.view;

import java.text.ParseException;
import java.util.ArrayList;

import br.com.senacrs.labii.pet.model.Animal;
import br.com.senacrs.labii.pet.model.Owner;
import oo.Procedure;
import oo.Scheduling;
import procedures.ProceduresScheduling;
import br.com.senacrs.labii.pet.util.Data;
import java.sql.SQLException;

public class Main {

    static Data data = new Data();

    public static ArrayList<Owner> owners = new ArrayList<Owner>();
    public static int countRegOwners = 0;

    public static ArrayList<Animal> animals = new ArrayList<Animal>();
    public static int countRegAnimals = 0;

    public static ArrayList<Procedure> procedures = new ArrayList<Procedure>();
    public static int countRegProcs = 0;

    public static ArrayList<Scheduling> scheds = new ArrayList<Scheduling>();
    public static int countRegScheds = 0;

    public static double totalMoney;

    public static void main(String[] args) throws ParseException, SQLException {

        mainMenu();

    }

    public static void mainMenu() throws ParseException, SQLException {

        data.message("___________________________________\n\n"
                + ".:: Menu Principal ::.\n\n"
                + "1 - Cadastro de Clientes\n"
                + "2 - Cadastro de Procedimentos\n"
                + "3 - Agendamento de Procedimentos\n"
                + "4 - Relatórios\n"
                + "5 - Encerrar o sistema");

        String op = data.readString("\n\nEscolha uma opção: ");

        switch (op) {

            case "1":
                clientRegister();
                break;

            case "2":
                proceduresRegister();
                break;

            case "3":
                proceduresScheduling();

            case "4":
                reports();

            case "5":
                data.message("\n___________________________________\n\n.:: SISTEMA FINALIZADO!!! ::.");
                System.exit(0);

            default:
                data.message("\n\nERRO!!! Alternativa inválida! Tente novamente.\n\n");
                mainMenu();

        }

    }

    static void clientRegister() throws ParseException, SQLException {

        ClientRegister.menu();

    }

    static void proceduresRegister() throws ParseException {

        ProceduresRegister.menu();

    }

    static void proceduresScheduling() throws ParseException {

        ProceduresScheduling.menu();

    }

    static void reports() throws ParseException {

        Reports.menu();

    }

}
