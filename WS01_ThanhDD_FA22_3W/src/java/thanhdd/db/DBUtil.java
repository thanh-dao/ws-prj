/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhdd.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kanek
 */
public class DBUtil implements Serializable{

    
    private static Connection conn;

    public static Connection makeConnection() {

        Connection conn = null;
        try{
            String url = "jdbc:sqlserver://localhost;databaseName=ThanhDD_FA223W;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, "sa", "12345");
        }catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        try {
            System.out.println(makeConnection().getClientInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
