package com.mhidcamsa.admin.config;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSource {

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mhidcam_db?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final int POOL_SIZE = 20;

    private BasicDataSource bds = new BasicDataSource();

    private DataSource(){

        bds.setDriverClassName(DRIVER_NAME);
        bds.setUrl(DB_URL);
        bds.setUsername(USER);
        bds.setPassword(PASSWORD);
        bds.setInitialSize(POOL_SIZE);

    }

    private static class DataSourceHolder{

        private static final DataSource INSTANCE = new DataSource();

    }

    public static DataSource getInstance(){

        return DataSourceHolder.INSTANCE;

    }

    public BasicDataSource getBds(){

        return bds;

    }

    public void setBds(BasicDataSource bds){

        this.bds = bds;

    }

}
