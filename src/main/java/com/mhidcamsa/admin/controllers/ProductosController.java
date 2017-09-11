package com.mhidcamsa.admin.controllers;

import com.mhidcamsa.admin.config.DataSource;

import com.mhidcamsa.admin.models.*;

import org.apache.commons.dbcp2.BasicDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductosController {

    public static Connection connection;
    public static PreparedStatement pstm;
    public static ResultSet rs;

    //Close the connections from the pool
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

    public static void deleteProducto( String id, String tipo ){

        String identifier = "id" + tipo;

        try{

            BasicDataSource bds = DataSource.getInstance().getBds();
            connection = bds.getConnection();

            pstm = connection.prepareStatement("DELETE FROM "  + tipo + " WHERE " + identifier + " = ?");
            pstm.setString(1 ,id);

            System.out.println(pstm.toString());

            pstm.execute();

            System.out.println("Entry deleted");

        }
        catch (SQLException e){

            e.printStackTrace();

        }
        finally {

            releaseConnections();

        }


    }

    public Object[][] getAllProducto(String tipo){

        int rows = 0;
        int cols = 0;

        try{

            BasicDataSource bds = DataSource.getInstance().getBds();
            connection = bds.getConnection();

            pstm = connection.prepareStatement("SELECT count(1) as total FROM " + tipo);
            System.out.println(pstm.toString());
            rs = pstm.executeQuery();

            rs.next();

            rows = rs.getInt("total");

            if (rs != null){
                rs.close();
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        try {

            pstm = connection.prepareStatement("SELECT " +
                    " * " +
                    " FROM balanceado");

            System.out.println(pstm.toString());

            rs = pstm.executeQuery();

            cols = rs.getMetaData().getColumnCount();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        Object[][] data = new String[rows][cols];

        int i = 0;

        try {

            while (rs.next()) {

                data[i][0] = rs.getString("id" + tipo);
                data[i][1] = rs.getString("marca");
                data[i][2] = rs.getString("tipo");
                data[i][3] = rs.getString("volumen");
                data[i][4] = rs.getString("precio");
                data[i][5] = rs.getString("liquido");

                i++;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                if (rs != null) {
                    rs.close();
                    System.out.println("Closing ResultSet...");
                }
                if (connection != null) {
                    connection.close();
                    System.out.println("Closing connection...");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return data;

    }

    public BigDecimal getStock(String tipo, String id, BigDecimal cantidad){

        BigDecimal stock = new BigDecimal(0);
        String idTipo = "id" + tipo;

        try {

            BasicDataSource bds = DataSource.getInstance().getBds();
            connection = bds.getConnection();

            pstm = connection.prepareStatement("SELECT stock FROM " + tipo + " WHERE " + idTipo + " = ? ");
            rs = pstm.executeQuery();

            stock = rs.getBigDecimal("stock");


        }
        catch (SQLException e){

            e.printStackTrace();

        }
        finally {

            releaseConnections();

        }

        return stock;
    }

    public static void takeOffStock(String tipo, String id, BigDecimal cantidad ){

        BigDecimal stockActual;
        String idTipo = "id" + tipo;

        try{

            BasicDataSource bds = DataSource.getInstance().getBds();
            connection = bds.getConnection();

            pstm = connection.prepareStatement("SELECT stock FROM " + tipo + " WHERE " + idTipo + " = ? ");
            pstm.setString(1, id);

            rs = pstm.executeQuery();

            stockActual = rs.getBigDecimal("stock");

            System.out.println("Stock actual: " + stockActual);

            pstm.close();

            int compare = stockActual.compareTo(cantidad);

                if (compare == 1){

                    stockActual = stockActual.subtract(cantidad);

                    System.out.println(stockActual.toString());

                    pstm = connection.prepareStatement("UPDATE " + tipo + " SET stock = ?" +
                            " WHERE " + idTipo + " = ?");
                    pstm.setBigDecimal(1, stockActual);

                    System.out.println(pstm.toString());

                    pstm.execute();

                    System.out.println("Stock updated");

                }

        }
        catch (SQLException e){

            e.printStackTrace();

        }
        finally {

            releaseConnections();

        }

    }

}
