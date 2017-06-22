/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senacrs.labii.pet.controller;

import br.com.senacrs.labii.pet.db.DBConnection;
import static br.com.senacrs.labii.pet.view.ProceduresRegisterView.menu;
import java.text.ParseException;
import br.com.senacrs.labii.pet.model.Procedure;
import br.com.senacrs.labii.pet.util.Data;
import br.com.senacrs.labii.pet.view.ProceduresRegisterView;
import java.sql.SQLException;

/**
 *
 * @author Luciano Junior
 */
public class ProceduresRegister {

    static Data data = new Data();
    static DBConnection dbc = new DBConnection();

    public static void registerProcedure() throws ParseException, SQLException {

        data.message("\n\n - Cadastrar Procedimento -\n\n");

        Procedure procedure = new Procedure();

        boolean procOk = false, priceOk = false;

        while (procOk == false) {

            String proc = data.readString("Procedimento: ");

            if (procIsOk(proc) == true) {

                procedure.setProc(proc);
                procOk = true;

                while (priceOk == false) {

                    double price = data.readDouble("Valor (R$): ");

                    if (priceIsOk(price) == true) {

                        procedure.setPrice(price);
                        priceOk = true;

                    }

                }

            }

        }

        dbc.insertProcedures(dbc.connect(), procedure);

        data.message("\n\n .:: CADASTRADO COM SUCESSO!!!\n\n");

        menu();

    }

    public static void removeProcedure() throws ParseException, SQLException {

        if (dbc.verifyHasProcedures(dbc.connect()) == true) {

            data.message("\n\nERRO!!! Não existem procedimentos cadastrados! Tente novamente. \n\n");

            ProceduresRegisterView.menu();

        } else {

            data.message("\n\n - Remover Procedimento -\n\n");

            while (searchToRemove(data.readString("Digite o Código ou nome do procedimento: ")) == false) {

                data.message("\n\n .:: ERRO!!! Procedimento não encontrado!\n .:: Tente novamente.\n\n");

            }

            data.message("\n\n .:: REMOVIDO COM SUCESSO!!!\n\n");

            ProceduresRegisterView.menu();

        }

    }

    public static boolean searchToRemove(String search) throws SQLException {

        boolean found = false;

        Procedure procedure = new Procedure();

        procedure = dbc.searchProcedures(dbc.connect(), search);

        if (procedure != null) {

            dbc.removeProcedure(dbc.connect(), procedure);

            found = true;

        } else {

            found = false;

        }

        return found;

    }

    public static void listProcedures() throws SQLException, ParseException {

        if (dbc.verifyHasProcedures(dbc.connect()) == true) {

            data.message("\n\nERRO!!! Não existem procedimentos cadastrados! Tente novamente. \n\n");

        } else {

            data.message("\n\n - Procedimentos -\n\n"
                    + "Cód. | Procedimento | Preço\n");

            dbc.listAllProcedures(dbc.connect());
            
            ProceduresRegisterView.menu();

        }

    }
    
    public static boolean procIsOk(String proc) {

        boolean hasNumbers = false;

        int contChars = proc.length();

        for (int i = 0; i < proc.length(); i++) {

            contChars--;

            if (!Character.isLetter(proc.charAt(contChars))) {

                hasNumbers = true;

            }

        }

        if (hasNumbers == true) {

            data.message("\n.:: ERRO!!! \nDigite o procedimento corretamente.\n\n");

            return false;

        } else {

            return true;

        }

    }

    public static boolean priceIsOk(double price) {

        if (price <= 0) {

            data.message("\n.:: ERRO!!! \nDigite o valor corretamente.\n\n");

            return false;

        } else {

            return true;

        }

    }

}
