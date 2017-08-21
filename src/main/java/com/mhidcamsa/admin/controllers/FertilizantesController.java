package com.mhidcamsa.admin.controllers;

import com.mhidcamsa.admin.config.DataSource;

import com.mhidcamsa.admin.models.Fertilizante;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FertilizantesController {

    public static Connection connection;
    public static PreparedStatement pstm;
    public static ResultSet rs;


    private static void releaseConnections(){

        try {
            if (pstm != null) {
                pstm.close();
                System.out.println("Closing statement...");
            }
            if (connection != null) {
                connection.close();
                System.out.println("Closing connection...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Save Fertilizante data to DB
    public static void insertFertilizante(Fertilizante fertilizante){

        try {

            BasicDataSource bds = DataSource.getInstance().getBds();
            connection = bds.getConnection();

            pstm = connection.prepareStatement("INSERT INTO" +
                    " fertilizante(idfertilizante, marca, tipo, volumen, precio, liquido) " +
                    "VALUES(?,?,?,?,?,?)");

            pstm.setString(1, fertilizante.getId());
            pstm.setString(2, fertilizante.getMarca());
            pstm.setString(3, fertilizante.getTipo());
            pstm.setDouble(4, fertilizante.getVolumen());
            pstm.setBigDecimal(5, fertilizante.getPrecio());
            pstm.setBoolean(6, fertilizante.isLiquido());

            System.out.println(pstm.toString());

            pstm.execute();

            System.out.println("Fertilizante Saved");
        }
        catch (SQLException e){

            e.printStackTrace();

        }
        finally {

            releaseConnections();

        }

    }

}
