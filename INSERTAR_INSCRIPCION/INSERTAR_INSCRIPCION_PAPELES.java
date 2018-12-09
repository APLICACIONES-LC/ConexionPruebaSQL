/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSERTAR_INSCRIPCION;

import static CONEXION.CONEXION.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hp
 */
public class INSERTAR_INSCRIPCION_PAPELES {
  Connection con=conectar();

  

    public int INSERTAR(byte[] foto_pago, byte[] foto_comprobante_domicilio, byte[] pdf_tesco, byte[] foto_recomendacion,String Matricula) {
     int r=0;
      
      try{
            PreparedStatement ps=null;
            ResultSet rs=null;
            ps=con.prepareStatement("insert into tbl_documentos_inscripcion(str_comprobante_recibo_pago" +
               ",str_comprobante_de_domicilio" +
               ",str_comprobante_aceptacion" +
               ",str_carta_recomendacion" +
               ",str_pre_matricula) values (?,?,?,?,?)");
            
            ps.setBytes(1, foto_pago);
            ps.setBytes(2, foto_comprobante_domicilio);
            ps.setBytes(3, pdf_tesco);
            ps.setBytes(4, foto_recomendacion);
            ps.setString(5, Matricula);
         
            
            r=ps.executeUpdate();
            if(r>=1){
                System.out.println("se inserto correctamente"); 
                r=1;
                con.close();
            }else{
                System.out.println("problema al insertar verifica bien los datos");
            }
            
        }catch(Exception e){
            System.err.println("error al ingresar datos: "+ e);   
        }
      
     return r;
    }
  
}
