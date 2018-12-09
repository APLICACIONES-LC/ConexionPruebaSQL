/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * totalplay-E544
clave: 1831E544
 */
package CONEXION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class CONEXION {
   public static Connection conectar(){
      Connection con=null;
       try{
       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-0F2T5LU:1433;databaseName=Base_admision","sa","msjerry0");
      
       
       } catch (ClassNotFoundException | SQLException e){
          System.err.println(e.getMessage());
       }
       return con;
   }
}
