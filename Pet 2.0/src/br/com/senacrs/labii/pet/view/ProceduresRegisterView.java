package br.com.senacrs.labii.pet.view;

import br.com.senacrs.labii.pet.controller.ProceduresRegister;
import java.text.ParseException;

import br.com.senacrs.labii.pet.model.Procedure;
import br.com.senacrs.labii.pet.util.Data;
import java.sql.SQLException;

public class ProceduresRegisterView {

    static Data data = new Data();

    public static void menu() throws ParseException, SQLException {

        data.message("___________________________________\n\n"
                + "- Cadastro de Procedimentos -\n\n"
                + "1 - Cadastrar Procedimento\n"
                + "2 - Excluir Procedimento\n"
                + "3 - Listar todos os procedimentos\n"
                + "4 - Voltar");

        ProceduresRegister proceduresRegister = new ProceduresRegister();

        String op = data.readString("\n\nEscolha uma opção: ");

        switch (op) {

            case "1":

                proceduresRegister.registerProcedure();
                break;

            case "2":

                proceduresRegister.removeProcedure();
                break;

            case "3":

                proceduresRegister.listProcedures();
                break;
               
            case "4":
                Main.mainMenu();
                break;

            default:
                data.message("\n\nERRO!!! Alternativa inválida! Tente novamente.\n\n");
                menu();
        }

    }

}
