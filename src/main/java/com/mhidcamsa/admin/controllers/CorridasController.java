package com.mhidcamsa.admin.controllers;

import com.mhidcamsa.admin.config.DataSource;

import com.mhidcamsa.admin.models.*;

import org.apache.commons.dbcp2.BasicDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CorridasController {

    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement pstm;

    private static void releaseConnections() throws SQLException{

        if (pstm != null){
            pstm.close();
        }
        if (connection != null){
            connection.close();
        }

    }

    public static void newCorrida(Corrida corrida) throws SQLException {

        BasicDataSource bds = DataSource.getInstance().getBds();
        connection = bds.getConnection();

        pstm = connection.prepareStatement("INSERT INTO " + " corridas(idcorridas, fecha_inicio, fecha_fin, estado, piscina_id) "
                + " VALUES(?,?,?,?,?) ");
        pstm.setString(1, corrida.getId());
        pstm.setDate(2, corrida.getFecha_inicio());
        pstm.setDate(3, corrida.getFecha_fin());
        pstm.setBoolean(4, corrida.getActiva());
        pstm.setString(5, corrida.getId_piscina());

        System.out.println(pstm.toString());

        pstm.execute();

        releaseConnections();

    }

    public static void endCorrida(Boolean estado) {



    }

    public static void deleteCorrida(String id) throws SQLException {

        BasicDataSource bds = DataSource.getInstance().getBds();
        connection = bds.getConnection();

        pstm = connection.prepareStatement("DELETE FROM corridas WHERE " + id + " = ?");
        pstm.setString(1, id);

        System.out.println(pstm.toString());
        pstm.execute();

        releaseConnections();

    }

    public int getNroCorridas() throws SQLException{

        int total = 0;

        BasicDataSource bds = DataSource.getInstance().getBds();
        connection = bds.getConnection();

        pstm = connection.prepareStatement("SELECT COUNT(1) as total FROM corridas ");
        pstm.toString();

        resultSet = pstm.executeQuery();
        resultSet.next();

        total = resultSet.getInt("total");

        releaseConnections();

        return total;
    }

    public Object[][] getCorridas() throws SQLException {

        int rows = 0;
        int cols = 0;

        rows = getNroCorridas();

        System.out.println("Total: " + rows);

        pstm = connection.prepareStatement("SELECT * FROM corridas");

        resultSet = pstm.executeQuery();

        cols = resultSet.getMetaData().getColumnCount();

        Object[][] data = new String[rows][cols];

        int i = 0;

        while (resultSet.next()){

            data[i][0] = resultSet.getString("idcorridas");
            data[i][1] = resultSet.getString("fecha_inicio");
            data[i][2] = resultSet.getString("estado");
            data[i][3] = resultSet.getString("piscina_id");

            i++;
        }

        releaseConnections();

        return data;
    }

}
