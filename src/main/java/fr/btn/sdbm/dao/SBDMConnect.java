package fr.btn.sdbm.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SBDMConnect {
    private static Connection connection;

    private SBDMConnect() {

    }

    public static Connection getInstance() {
        if(connection == null) {
            try {
                String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=sdbm;encrypt=false";
                String user = "dev";
                String password = "abcd@1234";
                connection = DriverManager.getConnection(url, user, password);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

}

//server + port + db name + security + encrypt + user + password
