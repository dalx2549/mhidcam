package com.mhidcamsa.admin.controllers;

import com.mhidcamsa.admin.config.DataSource;
import com.mhidcamsa.admin.models.Periodo;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeriodosController {

    private static Connection conn;
    private static ResultSet rs;
    private static PreparedStatement pstm;

    private static void releaseConnections(){

        try {
            if (pstm != null) {
                pstm.close();
                System.out.println("Closing statement...");
            }
            if (conn != null) {
                conn.close();
                System.out.println("Closing connection...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertPeriodo(Periodo periodo){

        try{

            BasicDataSource bds = DataSource.getInstance().getBds();
            conn = bds.getConnection();

            pstm = conn.prepareStatement("INSERT INTO "
                    + " periodos(idperiodos, fecha_inicio, fecha_fin, total_balanceado, total_costo, corrida) "
                    + "VALUES (?,?,?,?,?,?)");

            pstm.setString(1, periodo.getId());
            pstm.setDate(2, periodo.getFechaInicio());
            pstm.setDate(3, periodo.getFechaFin());
            pstm.setBigDecimal(4, periodo.getTotalBalanceado());
            pstm.setBigDecimal(5, periodo.getTotalCosto());
            pstm.setString(6, periodo.getCorrida());

            System.out.println(pstm.toString());

            pstm.execute();


        }catch (SQLException e){

            e.printStackTrace();

        }
        finally {

            releaseConnections();

        }

    }

    public static void deletePeriodo(String id){

        try{



        }
        catch (SQLException e){



        }
        finally {



        }

    }

}
