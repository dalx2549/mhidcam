package com.mhidcamsa.admin.controllers;

import com.mhidcamsa.admin.config.DataSource;

import com.mhidcamsa.admin.models.*;

import org.apache.commons.dbcp2.BasicDataSource;

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



}
