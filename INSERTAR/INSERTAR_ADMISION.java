/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSERTAR;

import CONEXION.CONEXION;
//import static CONEXION.CONEXION.conectar;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class INSERTAR_ADMISION extends CONEXION{
    Connection con=conectar();
    public String año,matricula;
    public int insertar(byte[] foto_usuario,String nombres,String ap_p,String ap_m,String curp,String rfc,String ine,
            String sexo,String fecha_na,String telefono,String celular,String email,String est_civil,
            String trabaja,String giro,String domicilio,String estado,String municipio,String cp,String colonia,
            String año_terminacion,String carrera_profesional,String cedula,String promedio,String inst_procedencia,String posgrado){
        año=year();
        matricula=año+posgrado;
        matricula=ultima_matricula(matricula);
      
        //20171p001
        int r=0;
        try{
            PreparedStatement ps=null;
            ResultSet rs=null;
            ps=con.prepareStatement("insert into tbl_alumnos(str_pre_matricula" +
               ",str_nombres" +
               ",str_apellido_paterno" +
               ",str_apellido_materno" +
               ",str_curp" +
               ",str_rfc" +
               ",str_ine" +
               ",str_sexo" +
               ",str_fecha_nacimiento" +
               ",str_telefono" +
               ",str_celular" +
               ",str_email" +
               ",str_estado_civil" +
               ",str_trabaja" +
               ",str_giro"+ 
               ",str_alta"+
               ",str_calle_n_ext_int"+
               ",str_estado"+
               ",str_municipio"+
               ",str_codigo_postal"+
               ",str_colonia"+
               ",str_año_terminacion"+
               ",str_carrera_profesional"+
               ",str_cedula_profesional"+
               ",str_promedio"+
               ",str_institucion_procedencia"+
               ",str_id_posgrado"+
               ",str_foto_usuario ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,matricula );
            ps.setString(2, nombres);
            ps.setString(3, ap_p);
            ps.setString(4, ap_m);
            ps.setString(5, curp);
            ps.setString(6, rfc);
            ps.setString(7, ine);
            ps.setString(8, sexo);
            ps.setString(9, fecha_na);
            ps.setString(10, telefono);
            ps.setString(11, celular);
            ps.setString(12, email);
            ps.setString(13, est_civil);
            ps.setString(14, trabaja);
            ps.setString(15, giro);
            ps.setString(16, "A");
            ps.setString(17, domicilio);
            ps.setString(18, estado);
            ps.setString(19, municipio);
            ps.setString(20, cp);
            ps.setString(21, colonia);
            ps.setString(22, año_terminacion);
            ps.setString(23, carrera_profesional);
            ps.setString(24, cedula);
            ps.setString(25, promedio);
            ps.setString(26, inst_procedencia);
            ps.setString(27, posgrado);
            ps.setBytes(28, foto_usuario);
//            ps.setString(29, "null");
            
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
   private  String year() {
        Calendar fecha = new GregorianCalendar();
        int year = fecha.get(Calendar.YEAR);
        String año="";
        año=""+year;
        
        return año;
 }

   private String ultima_matricula( String matricula){
     String nueva_matricula="2017P000";
       PreparedStatement pstmt;
        try {
     
            pstmt = con.prepareStatement("SELECT * FROM tbl_alumnos WHERE str_pre_matricula  LIKE ? ORDER BY str_pre_matricula ASC");
        

pstmt.setString(1, matricula+"%");

//Obtengo el resultado de la consulta
ResultSet rs = pstmt.executeQuery();


//Si encuentra algo imprime el primer campo de los registro
while (rs.next()) {
    nueva_matricula=rs.getString("str_pre_matricula"); 
}
//AAAATNNN
//01234567
nueva_matricula=nueva_matricula.substring(5, 8);
int sumar=Integer.parseInt(nueva_matricula);
sumar=sumar+1;

if(sumar<10){
    nueva_matricula=matricula+"00"+sumar;
}else if(sumar<100&&sumar>9){
    nueva_matricula=matricula+"0"+sumar;
}else if(sumar>99){
  nueva_matricula=matricula+""+sumar;  
}

} catch (SQLException ex) {
            Logger.getLogger(INSERTAR_ADMISION.class.getName()).log(Level.SEVERE, null, ex);
        }
   return nueva_matricula;
   }
}
