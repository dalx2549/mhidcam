package com.mhidcamsa.admin.controllers;

import com.mhidcamsa.admin.config.DataSource;
import com.mhidcamsa.admin.models.Balanceado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class BalanceadoController {

    public static Connection conn;
    public static PreparedStatement pstm;
    public static ResultSet rs;

    //Close DB connections
    private static void closeConnections() {

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

    public static void insertBalanceado(Balanceado balanceado) {

        try {

            BasicDataSource bds = DataSource.getInstance().getBds();
            conn = bds.getConnection();

            pstm = conn.prepareStatement("INSERT INTO" +
                    " balanceado(idbalanceado, marca, tipo, perc_prot, volumen, precio, liquido, stock) " +
                    "VALUES(?,?,?,?,?,?,?,?)");

            pstm.setString(1, balanceado.getId());
            pstm.setString(2, balanceado.getMarca());
            pstm.setString(3, balanceado.getTipo());
            pstm.setInt(4, balanceado.getPerc_prot());
            pstm.setDouble(5, balanceado.getVolumen());
            pstm.setBigDecimal(6, balanceado.getPrecio());
            pstm.setBoolean(7, balanceado.isLiquido());
            pstm.setBigDecimal(8, balanceado.getStockLibras());

            System.out.println(pstm.toString());

            pstm.execute();

            System.out.println("Data saved!");

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            closeConnections();

        }

    }

    // Retrieves all Balanceado entries from DB
    public Object[][] getAllData() {

        int rows = 0;
        int cols = 0;

        try {

            BasicDataSource bds = DataSource.getInstance().getBds();
            conn = bds.getConnection();

            pstm = conn.prepareStatement("SELECT count(1) as total FROM balanceado ");
            System.out.println(pstm.toString());
            rs = pstm.executeQuery();

            rs.next();

            rows = rs.getInt("total");

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            pstm = conn.prepareStatement("SELECT " +
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

                data[i][0] = rs.getString("idbalanceado");
                data[i][1] = rs.getString("marca");
                data[i][2] = rs.getString("tipo");
                data[i][3] = rs.getString("perc_prot");
                data[i][4] = rs.getString("volumen");
                data[i][5] = rs.getString("precio");
                data[i][6] = rs.getString("liquido");
                data[i][7] = rs.getString("stock");

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
                if (conn != null) {
                    conn.close();
                    System.out.println("Closing connection...");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return data;

    }

    public static void updateBalanceado(Balanceado balanceado) {

        BasicDataSource bds = DataSource.getInstance().getBds();

        try {

            conn = bds.getConnection();

            pstm = conn.prepareStatement("UPDATE  balanceado " +
                    "set marca = ? ," +
                    "tipo = ? ," +
                    "perc_prot = ? ," +
                    "volumen = ? ," +
                    "precio = ? ," +
                    "liquido = ? ," +
                    "stock = ? " +
                    "WHERE idbalanceado = ? ");

            pstm.setString(1, balanceado.getMarca());
            pstm.setString(2, balanceado.getTipo());
            pstm.setInt(3, balanceado.getPerc_prot());
            pstm.setDouble(4, balanceado.getVolumen());
            pstm.setBigDecimal(5, balanceado.getPrecio());
            pstm.setBoolean(6, balanceado.isLiquido());
            pstm.setBigDecimal(7, balanceado.getStockLibras());
            pstm.setString(8, balanceado.getId());

            System.out.println(pstm.toString());

            pstm.execute();

            System.out.println("Entry updated");

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            closeConnections();

        }

    }


}
