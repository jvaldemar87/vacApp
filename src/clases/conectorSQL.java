/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.sql.*;

/**
 *
 * @author jvald
 */
public class conectorSQL {
    public static void conexion(){
        String server = "localhost";
        String puerto="3306";
        String bd="vacapp";
        String user="admin";
        String pwd = "admin123";
        String driver="com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://"+server+"/" +bd + "?autoReconnect=true&useSSL=false";
        
        System.out.println("Conectando...");
        
        try(Connection connection = DriverManager.getConnection(url, user,pwd)) {
            System.out.println("Conectado!!");
        } catch (SQLException e) {
            System.out.println("Falla en conexion!!!");
            System.out.println(e.getMessage());
        }
    }
}
