/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSERTAR;

import CONEXION.CONEXION;
import static CONEXION.CONEXION.conectar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class INSERTAR_DOCUMENTOS {
   Connection con=CONEXION.conectar();
    public void insertar_documentos(byte[] foto_comprobante_pago, byte[] foto_acta_nacimiento, byte[] foto_domicilio, 
            byte[] foto_certificado, byte[] foto_cedula, byte[] foto_comprobante_ingles, byte[] pdf_vitae, 
            byte[] foto_expomotivos,String matricula) throws  IOException{
     
        int r=0;
        try{
            PreparedStatement ps=null;
            ResultSet rs=null;
            ps=con.prepareStatement("INSERT INTO tbl_documentos (str_comprobante_pago" +
             ",str_acta_de_nacimiento" +
             ",str_comprobante_de_domicilio" +
             ",str_certificado" +
             ",str_titulo_cedula" +
             ",str_certificado_ingles" +
             ",str_curriculum" +
             ",str_carta_expo_motivos" +
             ",str_pre_matricula) values (?,?,?,?,?,?,?,?,?)");
            
 
            
            ps.setBytes(1, foto_comprobante_pago);
            ps.setBytes(2, foto_acta_nacimiento);
            ps.setBytes(3, foto_domicilio);
            ps.setBytes(4, foto_certificado);
            ps.setBytes(5, foto_cedula);
            ps.setBytes(6, foto_comprobante_ingles);
            ps.setBytes(7, pdf_vitae);
            ps.setBytes(8, foto_expomotivos);
            ps.setString(9,matricula);
            
            
            
            r=ps.executeUpdate();
            if(r>=1){
                System.out.println("se inserto correctamente"); 
                r=1;
                con.close();
            }else{
                System.out.println("problema al insertar verifica bien los datos");
            }
            
        }catch(SQLException ex){
            System.err.println("error al ingresar datos: "+ ex);   
        }
        
        
    }


   

   

    
}