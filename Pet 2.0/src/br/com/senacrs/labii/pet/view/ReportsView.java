package br.com.senacrs.labii.pet.view;

import br.com.senacrs.labii.pet.view.Main;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.senacrs.labii.pet.controller.AnimalsRegister;
import br.com.senacrs.labii.pet.controller.ReportsControll;
import br.com.senacrs.labii.pet.util.Data;
import java.sql.SQLException;

public class ReportsView {

    static Data data = new Data();

    ReportsControll rc = new ReportsControll();
    
    public void menu() throws ParseException, SQLException {

        data.message("___________________________________\n\n"
                + " - Relatórios - \n\n"
                + "1 - Animais cadastrados\n"
                + "2 - Procedimentos realizados\n"
                + "3 - Procedimentos do dia\n"
                + "4 - Total recebido\n"
                + "5 - Voltar");

        String op = data.readString("\n\nEscolha uma opção: ");

        switch (op) {

            case "1":

                AnimalsRegister ar = new AnimalsRegister();

                ar.listAnimals();

                menu();

                break;

            case "2":

                rc.realizedProcedures();

                menu();

                break;

            case "3":
                rc.proceduresDay();

                menu();

                break;

            case "4":
                rc.totalEarnings();

                menu();

                break;

            case "5":
                Main.mainMenu();

            default:
                data.message("\n\nERRO!!! Alternativa inválida! Tente novamente.\n\n");
                menu();

        }

    }

}
