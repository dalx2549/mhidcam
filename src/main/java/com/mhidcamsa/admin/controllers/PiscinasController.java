package com.mhidcamsa.admin.controllers;

import com.mhidcamsa.admin.config.DataSource;

import com.mhidcamsa.admin.models.*;

import org.apache.commons.dbcp2.BasicDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PiscinasController {

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

    public static String[] getEstados() throws SQLException{

        BasicDataSource bds = DataSource.getInstance().getBds();
        connection = bds.getConnection();

        pstm = connection.prepareStatement("SELECT estado FROM piscinas ORDER BY idpiscina");
        rs = pstm.executeQuery();

        int rows = 0;

        if (rs.last()){
            rows = rs.getRow();
            rs.beforeFirst();
        }

        String[] estados = new String[rows];

        int i = 0;

        while (rs.next()) {

            estados[i] = rs.getString("estado");

            i++;
        }

        for (int j = 0; j < estados.length; j++){

            if (estados[j] == "1"){
                estados[j] = "Sembrada";
            }
            else {
                estados[j] = "Inactiva";
            }
        }

        return estados;

    }

    public static String[] getTamanos() throws SQLException{

        BasicDataSource bds = DataSource.getInstance().getBds();
        connection = bds.getConnection();

        pstm = connection.prepareStatement("SELECT hectareas FROM piscinas ORDER BY idpiscina");
        rs = pstm.executeQuery();

        int rows = 0;

        if (rs.last()){
            rows = rs.getRow();
            rs.beforeFirst();
        }

        String[] tamanos = new String[rows];

        int i = 0;

        while (rs.next()) {

            tamanos[i] = rs.getString("hectareas");
            System.out.println(tamanos[i]);
            i++;
        }

        return tamanos;
    }

    public static BigDecimal getTamanoPiscina(int id) throws SQLException{

        BigDecimal hectareas = new BigDecimal(0);

        BasicDataSource bds = DataSource.getInstance().getBds();
        connection = bds.getConnection();

        pstm = connection.prepareStatement("SELECT hectareas FROM piscinas " + "WHERE  idpiscina = " + id + " LIMIT 1");
        System.out.println(pstm.toString());
        rs = pstm.executeQuery();
        rs.next();

        hectareas = rs.getBigDecimal("hectareas");

        releaseConnections();

        return hectareas;
    }

    public Piscina getPiscina(String id) throws SQLException{

        BasicDataSource bds = DataSource.getInstance().getBds();
        connection = bds.getConnection();

        pstm = connection.prepareStatement("SELECT * FROM piscinas " + " WHERE idpiscina = " + id  + " LIMIT 1");
        System.out.println(pstm.toString());

        rs = pstm.executeQuery();

        rs.next();

        Piscina piscina = new Piscina(
                rs.getString("id"),
                rs.getString("numero"),
                rs.getBigDecimal("hectareas"),
                rs.getBoolean("estado")
        );

        return piscina;

    }

}
