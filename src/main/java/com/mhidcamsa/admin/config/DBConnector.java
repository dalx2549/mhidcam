package com.mhidcamsa.admin.config;

import org.javalite.activejdbc.Base;


public class DBConnector {

    public void openConnection() throws Exception{

        Base.open("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/mhidcam_db?autoReconnect=true&useSSL=false", "root", "");

    }

    public void closeConnection(){

        try{

            Base.close();

        }
        catch (Exception e){

            e.printStackTrace();

        }


    }


}
