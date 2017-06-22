/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senacrs.labii.pet.controller;

import br.com.senacrs.labii.pet.db.DBConnection;
import br.com.senacrs.labii.pet.util.Data;
import br.com.senacrs.labii.pet.view.Main;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author 631610257
 */
public class ReportsControll {

    Data data = new Data();
    DBConnection dbc = new DBConnection();

    public void realizedProcedures() throws SQLException {

        Date today = new Date(System.currentTimeMillis());

        if (dbc.verifyHasScheds(dbc.connect()) == false) {

            data.message("\n\nERRO!!! Não há nenhum procedimento realizado!\n");

        } else {

            data.message("\n\n - Procedimentos realizados - \n\n");

            dbc.listAllRealizedProcedures(dbc.connect(), today);

        }

    }

    public void proceduresDay() throws SQLException {

        Date today = new Date(System.currentTimeMillis());

        if (dbc.verifyHasScheds(dbc.connect()) == false) {

            data.message("\n\nERRO!!! Não há nenhum procedimento realizado!\n");

        } else {

            data.message("\n\n - Procedimentos de hoje - \n\n");

            dbc.todayProcedures(dbc.connect(), today);

        }

    }

    public void totalEarnings() throws SQLException {

        Double d = dbc.totalEarnings(dbc.connect());
        Locale ptBr = new Locale("pt", "BR");
        String earningsString = NumberFormat.getCurrencyInstance(ptBr).format(d);

        data.message("\n\n - Total Ganho - \n\n");
        data.message(earningsString + "\n");

    }

}
