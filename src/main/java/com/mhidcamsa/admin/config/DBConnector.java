package com.mhidcamsa.admin.config;

import org.javalite.activejdbc.Base;


public class DBConnector {

    public void openConnection(){

        Base.open("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/mhidcam_db", "root", "");

    }

    public void closeConnection(){

        Base.close();

    }


}
