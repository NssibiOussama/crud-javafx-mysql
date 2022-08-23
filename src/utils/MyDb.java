/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mohamed
 */
public class MyDb {

    private final String URL = "jdbc:mysql://127.0.0.1:3306/dina";
    private final String USERNAME = "root";
    private final String PWD = "";
    private Connection c;
    private static MyDb instance;

    private MyDb() {
        try {
            c = DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("cnx etablie ....");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static MyDb getInstance() {
        if (instance == null) {
            instance = new MyDb();
        }

        return instance;
    }

    public Connection getCnx() {
        return c;
    }

}
