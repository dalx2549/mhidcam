package com.mhidcamsa.admin.controllers;


import com.mhidcamsa.admin.config.DataSource;
import com.mhidcamsa.admin.models.gastos.GastosBalanceado;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GastosController {

    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;

    private static void releaseConnections(){

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
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

    public static void insertGastosBalanceados(GastosBalanceado gastosBalanceado) throws SQLException{

        BasicDataSource basicDataSource = DataSource.getInstance().getBds();
        connection = basicDataSource.getConnection();

        preparedStatement = connection.prepareStatement("INSERT INTO "
                + "gastos_balanceado(idperiodos_balanceado, periodo_id, balanceado_id, cantidad, precio_kg, subtotal) "
                + "VALUES (?,?,?,?,?,?)");
        preparedStatement.setString(1, gastosBalanceado.getId());
        preparedStatement.setString(2, gastosBalanceado.getIdPeriodo());
        preparedStatement.setString(3, gastosBalanceado.getIdBalanceado());
        preparedStatement.setBigDecimal(4, gastosBalanceado.getCantidad());
        preparedStatement.setBigDecimal(5, gastosBalanceado.getPrecioKilogramo());
        preparedStatement.setBigDecimal(6, gastosBalanceado.getSubtotal());

        System.out.println(preparedStatement.toString());

        preparedStatement.execute();

        releaseConnections();

    }

    public int getNroGastos(String idPeriodo) throws SQLException{

        int total = 0;

        BasicDataSource basicDataSource = DataSource.getInstance().getBds();
        connection = basicDataSource.getConnection();

        preparedStatement = connection.prepareStatement("SELECT COUNT (1) as total FROM gastos_balanceado"
                + " WHERE periodo_id = " + idPeriodo);
        System.out.println(preparedStatement.toString());

        resultSet = preparedStatement.executeQuery();
        resultSet.next();

        total = resultSet.getInt("total");

        return total;
    }

    public Object[][] getGastosBalanceado(String idPeriodo) throws SQLException{

        int rows = getNroGastos(idPeriodo);
        int cols = 0;

        BasicDataSource basicDataSource = DataSource.getInstance().getBds();
        connection = basicDataSource.getConnection();

        preparedStatement = connection.prepareStatement("SELECT * FROM gastos_balanceado WHERE "
                + "periodo_id = " + idPeriodo);

        System.out.println(preparedStatement.toString());

        resultSet = preparedStatement.executeQuery();

        cols = resultSet.getMetaData().getColumnCount();

        Object[][] data = new String[rows][cols];

        int i = 0;

        while (resultSet.next()){

            data[i][0] = resultSet.getString("idperiodos_balanceado");
            data[i][1] = resultSet.getString("periodo_id");
            data[i][2] = resultSet.getString("balanceado_id");
            data[i][3] = resultSet.getString("cantidad");
            data[i][4] = resultSet.getString("precio_kg");
            data[i][5] = resultSet.getString("subtotal");

            i++;

        }

        releaseConnections();

        return data;
    }

}
