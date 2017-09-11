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
                    + " periodos(idperiodos, fecha_inicio, fecha_fin, total_costo, corrida) "
                    + "VALUES (?,?,?,?,?,?)");

            pstm.setString(1, periodo.getId());
            pstm.setDate(2, periodo.getFechaInicio());
            pstm.setDate(3, periodo.getFechaFin());
            pstm.setBigDecimal(4, periodo.getTotalCosto());
            pstm.setString(5, periodo.getCorrida());

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

            BasicDataSource bds = DataSource.getInstance().getBds();
            conn = bds.getConnection();

            pstm = conn.prepareStatement("DELETE FROM periodos " + " WHERE idperiodos = ?");
            pstm.setString(1, id);

            System.out.println(pstm.toString());

            pstm.execute();


        }
        catch (SQLException e){

            e.printStackTrace();

        }
        finally {

            releaseConnections();

        }

    }

    public Object [][] getPeriodos(){

        int rows = 0;
        int cols = 0;

        try{

            BasicDataSource bds = DataSource.getInstance().getBds();
            conn = bds.getConnection();

            pstm = conn.prepareStatement("SELECT COUNT(1) as total FROM periodos");
            System.out.println(pstm.toString());

            rs = pstm.executeQuery();

            rs.next();

            rows = rs.getInt("total");

            if (rs != null){
                rs.close();
            }

        }
        catch(SQLException e){

            e.printStackTrace();

        }

        try{

            pstm = conn.prepareStatement("SELECT * FROM periodos");
            pstm.toString();

            rs = pstm.executeQuery();

            cols = rs.getMetaData().getColumnCount();


        }
        catch (SQLException e){

            e.printStackTrace();

        }

        Object [][] data = new String[rows][cols];
        int i = 0;

        try{

            while (rs.next()){

                data[i][0] = rs.getString("idperiodos");
                data[i][1] = rs.getString("fecha_inicio");
                data[i][2] = rs.getString("fecha_fin");
                data[i][3] = rs.getString("total_costo");
                data[i][4] = rs.getString("corrida");

                i++;
            }

        }
        catch (SQLException e){

            e.printStackTrace();

        }finally {

            try {

                if (rs != null) {
                    rs.close();
                    System.out.println("Closing ResultSet...");
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Closing connection...");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return  data;

    }

}
