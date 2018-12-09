/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REPORTES;

import CONEXION.CONEXION;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.ZapfDingbatsList;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class PDF_PRE_EXAMEN {
    String nombreLogoEstado = "/IMAGENES_DE_CLASES/estado.jpg";
    String nombreLogoTesco = "/IMAGENES_DE_CLASES/tesco.jpg";
    
    String S_fecha_solicitud="",S_cedula="",S_ap_pa="",S_ap_ma="",S_nombres="",S_calle="",S_colonia="",
        S_municipio="",S_entidad="",S_CP="",S_telefono="",S_fecha_nacimiento="",S_sexo="",S_CURP="",S_trabaja="",
        S_estado_civil="",S_inst_procedencia="",S_año_fin_lic="",S_promedio="",S_ent_fed_escuela="",S_email="",
        S_carrera_cursada="",S_programa_tomar=""; 
  public void PDF(String S_pre_matricula, String entidad_escuela,String posgrado) throws SQLException, IOException, BadElementException{
     S_ent_fed_escuela=entidad_escuela;
      
     consulta_posgrado(posgrado);
      
      imagen(S_pre_matricula);
      
      Document document = new Document(PageSize.A4, 30, 30, 20, 36);
 // Document document = new Document(PageSize.LETTER,lado izq,lado der, encabezadp, pie de pagina);   
 try {
             // El archivo pdf que vamos a generar
            FileOutputStream fileOutputStream = new FileOutputStream( "TescoAdmision.pdf");

            // Obtener la instancia del PdfWriter
        PdfWriter pdfw=    PdfWriter.getInstance(document, fileOutputStream);

            // Abrir el documento
            document.open();
            
  
             //obtenemos la url del paquete imagenes
            URL resUrl = this.getClass().getResource(nombreLogoEstado);
            //creamos un arreglo de imagenes Los logos para la cabecera
            Image [] image = new Image [2];
             
            // Obtenemos el logo de datojava
            image[0] = Image.getInstance(resUrl);
            image[0].scaleAbsolute(55f, 65f);
            
            resUrl = this.getClass().getResource(nombreLogoTesco);
            image[1] = Image.getInstance(resUrl);
            image[1].scaleAbsolute(55f, 55f); 
            
//            image.setAlignment(Image.ALIGN_CENTER);
//          document.add(image);
            // Crear las fuentes para el contenido y los titulos
            Font fontContenido = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.DARK_GRAY);
            Font fontTitulos = FontFactory.getFont(  FontFactory.TIMES_BOLDITALIC, 9, Font.UNDERLINE,  BaseColor.RED);

            
             PdfPCell edomex =new PdfPCell (new Paragraph("GOBIERNO DEL ESTADO DE MÉXICO",FontFactory.getFont("arial",13,Font.BOLD,BaseColor.BLACK)));
                   
           
             
            // Creacion de una tabla
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            float[] valores = new float[3];
            valores[0] = 15;
            valores[1] = 70;
            valores[2] = 15;
            table.setWidths(valores);
           
            
// Agregar la imagen anterior a una celda de la tabla
            PdfPCell cell = new PdfPCell(image[0]);
            // Propiedades de la celda
            cell.setColspan(1);
            cell.setBorderColor(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            // Agregar la celda a la tabla
            table.addCell(cell);
    
 //Reutilizamos el instanciamiento para el nombre del estado de mexico       
             cell=new PdfPCell(edomex);
             cell.setColspan(1);
             cell.setBorderColor(BaseColor.WHITE);
             cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
             table.addCell(cell);
             
 //Reutilizamos el instanciamiento para el nombre del estado de mexico       
            cell=new PdfPCell(image[1]);
            cell.setColspan(1);
            cell.setBorderColor(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            // Agregar la celda a la tabla
            table.addCell(cell);
             
           //añadimos las celdas a la tabla
            document.add(table);
 
          Paragraph paragraph12 = new Paragraph();
          //paragraph12.add(new Phrase(Chunk.NEWLINE));   
//          
//            
// document.add(paragraph12);
  //////////////////////////tabla con codigo de barras////////////////////////////////////////
//resUrl = this.getClass().getResource(nombreLogoTesco);
            image[1] =  icon1;
            image[1].scaleAbsolute(55f, 65f); 
          
  Image img; 
   //Es el tipo de clase 
   Barcode128 codeEAN = new Barcode128();
   //Seteo el tipo de codigo
   codeEAN.setCodeType(Barcode.CODE128);
   //Setep el codigo
   codeEAN.setCode(S_pre_matricula);
   //Paso el codigo a imagen
   img = codeEAN.createImageWithBarcode( pdfw.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
  
  
      
 PdfPCell texto_solicitud =new PdfPCell (new Paragraph("PRE-SOLICITUD DE EXAMEN DE ADMISIÓN\n" + "",FontFactory.getFont("arial",13,Font.BOLD,BaseColor.BLACK)));
 PdfPCell texto_mensaje =new PdfPCell (new Paragraph("\nSolicitud de admisión" ,FontFactory.getFont("arial",12,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_año =new PdfPCell (new Paragraph("\n18-19/1" ,FontFactory.getFont("arial",12,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_ciclo =new PdfPCell (new Paragraph("(Ciclo Escolar)" ,FontFactory.getFont("arial",12,Font.NORMAL,BaseColor.BLACK)));
                  
          // Creacion de una tabla
            PdfPTable tabla_titulos = new PdfPTable(4);
          tabla_titulos.setWidthPercentage(100);
            float[] valores_titulos = new float[4];
            valores_titulos[0] = 20;
            valores_titulos[1] = 25;
            valores_titulos[2] = 30;
            valores_titulos[3] = 25;
           tabla_titulos.setWidths(valores_titulos);
           
            
             PdfPCell celda_barras = new PdfPCell(texto_solicitud);
            // Indicamos cuantas columnas ocupa la celda
            celda_barras.setColspan(3);
            celda_barras.setBorderColor(BaseColor.WHITE);
            celda_barras.setHorizontalAlignment(Element.ALIGN_RIGHT);
           // Agregar la celda a la tabla
           tabla_titulos.addCell(celda_barras);
           
          celda_barras=new PdfPCell(image[1]);
            // Indicamos cuantas columnas ocupa la celda
            celda_barras.setColspan(1);
            celda_barras.setRowspan(3);
            celda_barras.setBorderColor(BaseColor.WHITE);
            celda_barras.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla_titulos.addCell(celda_barras);
          
            
// Agregar la imagen anterior a una celda de la tabla
             celda_barras = new PdfPCell(img);
            // Propiedades de la celda
            celda_barras.setColspan(1);
            celda_barras.setRowspan(3);
            celda_barras.setBorderColor(BaseColor.WHITE);
            celda_barras.setHorizontalAlignment(Element.ALIGN_LEFT);
            // Agregar la celda a la tabla
            tabla_titulos.addCell(celda_barras);
    
 //Reutilizamos el instanciamiento para el nombre del estado de mexico       
            celda_barras=new PdfPCell(texto_mensaje);
            celda_barras.setColspan(1);
            celda_barras.setRowspan(3);
            celda_barras.setBorderColor(BaseColor.WHITE);
            celda_barras.setHorizontalAlignment(Element.ALIGN_MIDDLE );
            //celda_barras.setBorder(Rectangle.TOP);     
// Agregar la celda a la tabla
             tabla_titulos.addCell(celda_barras);
         
 //Reutilizamos el instanciamiento para el nombre del estado de mexico       
           celda_barras=new PdfPCell(texto_año);
           celda_barras.setBorderColor(BaseColor.WHITE);
           celda_barras.setHorizontalAlignment(Element.ALIGN_CENTER);
        // Agregar la celda a la tabla
           tabla_titulos.addCell(celda_barras);
       
           celda_barras=new PdfPCell(texto_ciclo);
           celda_barras.setBorderColor(BaseColor.BLACK);
           celda_barras.setHorizontalAlignment(Element.ALIGN_CENTER);
           celda_barras.setBorder(Rectangle.TOP);
        // Agregar la celda a la tabla
            tabla_titulos.addCell(celda_barras);    
           
           //añadimos las celdas a la tabla
            document.add(tabla_titulos);

        //paragraph12.add(new Phrase(Chunk.NEWLINE));
/////////////////////////////////////////////

 PdfPCell texto_matricula =new PdfPCell (new Paragraph("\nPre Matricula: " + S_pre_matricula ,FontFactory.getFont("arial",12,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_f_solicitud =new PdfPCell (new Paragraph("\nFecha de solicitud: " + S_fecha_solicitud ,FontFactory.getFont("arial",12,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_cedula =new PdfPCell (new Paragraph("Cedula: " + S_cedula ,FontFactory.getFont("arial",12,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_aula =new PdfPCell (new Paragraph("Aula: sin confirmar" ,FontFactory.getFont("arial",12,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_ho_fe =new PdfPCell (new Paragraph("Fecha/Hora de examen: sin confirmar" ,FontFactory.getFont("arial",12,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_nota =new PdfPCell (new Paragraph("Nota: Presentarse con solicitud de examen de admisión,"
         + "pase CENEVAL e identificación oficial con fotografía." ,FontFactory.getFont("arial",10,Font.NORMAL,BaseColor.BLACK)));
                  
          // Creacion de una tabla
            PdfPTable tabla_datos = new PdfPTable(2);
            tabla_datos.setWidthPercentage(100);
            float[] valores_datos = new float[2];
            valores_datos[0] = 40;
            valores_datos[1] = 60;
            
           tabla_datos.setWidths(valores_datos);
           
            
             PdfPCell celda_datos = new PdfPCell(texto_matricula);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos.setColspan(1);
            celda_datos.setBorderColor(BaseColor.WHITE);
            celda_datos.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos.addCell(celda_datos);
           
          celda_datos=new PdfPCell(texto_f_solicitud);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos.setColspan(1);
            celda_datos.setBorderColor(BaseColor.WHITE);
            celda_datos.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla_datos.addCell(celda_datos);
          
            
// Agregar la imagen anterior a una celda de la tabla
             celda_datos = new PdfPCell(texto_cedula);
            // Propiedades de la celda
            celda_datos.setColspan(2);
            celda_datos.setBorderColor(BaseColor.WHITE);
            celda_datos.setHorizontalAlignment(Element.ALIGN_LEFT);
            // Agregar la celda a la tabla
            tabla_datos.addCell(celda_datos);
    
 //Reutilizamos el instanciamiento para el nombre del estado de mexico       
            celda_datos=new PdfPCell(texto_aula);
            celda_datos.setColspan(1);
            celda_datos.setBorderColor(BaseColor.WHITE);
            celda_datos.setHorizontalAlignment(Element.ALIGN_LEFT );
            //celda_datos.setBorder(Rectangle.TOP);     
// Agregar la celda a la tabla
             tabla_datos.addCell(celda_datos);
         
         celda_datos=new PdfPCell(texto_nota);
           celda_datos.setColspan(1);
           celda_datos.setRowspan(2);
           celda_datos.setBorderColor(BaseColor.WHITE);
           celda_datos.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        // Agregar la celda a la tabla
            tabla_datos.addCell(celda_datos);         
             
 //Reutilizamos el instanciamiento para el nombre del estado de mexico       
           celda_datos=new PdfPCell(texto_ho_fe);
           celda_datos.setColspan(1);
           celda_datos.setBorderColor(BaseColor.WHITE);
           celda_datos.setHorizontalAlignment(Element.ALIGN_TOP);
        // Agregar la celda a la tabla
           tabla_datos.addCell(celda_datos);
       
          
           
           //añadimos las celdas a la tabla
            document.add(tabla_datos);
  paragraph12.add(new Phrase(Chunk.NEWLINE));
  document.add(paragraph12);         
 /////////////////
 
 PdfPCell texto_ap_pa =new PdfPCell (new Paragraph("\n\n\n" + S_ap_pa,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_ap_ma =new PdfPCell (new Paragraph("\n\n\n" + S_ap_ma ,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_nombres =new PdfPCell (new Paragraph("\n\n\n" + S_nombres ,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_calle =new PdfPCell (new Paragraph("\n\n\n" + S_calle ,FontFactory.getFont("arial",10,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_colonia =new PdfPCell (new Paragraph("\n\n\n" + S_colonia ,FontFactory.getFont("arial",10,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_municipio =new PdfPCell (new Paragraph("\n\n\n" + S_municipio,FontFactory.getFont("arial",10,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_entidad=new PdfPCell (new Paragraph("\n\n\n" + S_entidad,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_CP =new PdfPCell (new Paragraph("\n\n\n" + S_CP ,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_telefono =new PdfPCell (new Paragraph("\n\n\n" + S_telefono ,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_fecha_na =new PdfPCell (new Paragraph("\n\n\n" + S_fecha_nacimiento ,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_sexo =new PdfPCell (new Paragraph("\n\n\n" + S_sexo ,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_CURP =new PdfPCell (new Paragraph("\n\n\n" + S_CURP,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_trabaja =new PdfPCell (new Paragraph("\n\n\n" + S_trabaja,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_estado_civil=new PdfPCell (new Paragraph("\n\n\n" + S_estado_civil,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_inst_procedencia =new PdfPCell (new Paragraph("\n\n\n" + S_inst_procedencia ,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_año_fin_lic =new PdfPCell (new Paragraph("\n\n\n" + S_año_fin_lic ,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_promedio =new PdfPCell (new Paragraph("\n\n\n" + S_promedio ,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_ent_fed_escuela =new PdfPCell (new Paragraph("\n\n\n" + S_ent_fed_escuela ,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_email =new PdfPCell (new Paragraph("\n\n\n" + S_email,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_carrera_cursada =new PdfPCell (new Paragraph("\n\n\n" + S_carrera_cursada ,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
 PdfPCell texto_programa_tomar =new PdfPCell (new Paragraph("" + S_programa_tomar,FontFactory.getFont("arial",11,Font.NORMAL,BaseColor.BLACK)));
                            
          // Creacion de una tabla
            PdfPTable tabla_datos_personales = new PdfPTable(4);
            tabla_datos_personales.setWidthPercentage(100);
            float[] valores_datos_personales = new float[4];
            valores_datos_personales[0] = 30;
            valores_datos_personales[1] = 30;
            valores_datos_personales[2] = 25;
            valores_datos_personales[3] = 25;
            
           tabla_datos_personales.setWidths(valores_datos_personales);
           
            
             PdfPCell celda_datos_personales = new PdfPCell(texto_ap_pa);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           
            celda_datos_personales = new PdfPCell(texto_ap_ma);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
          
            
            celda_datos_personales = new PdfPCell(texto_nombres);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(2);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
    
            celda_datos_personales = new PdfPCell(new Paragraph("Apellido Paterno"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           celda_datos_personales = new PdfPCell(new Paragraph("Apellido Materno"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
             celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
         celda_datos_personales = new PdfPCell(new Paragraph("Nombre(s)"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(2);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
             celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
//-----------------------------------------datos de domicilio-------------------------------------------         
     celda_datos_personales = new PdfPCell(texto_calle);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(2);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           
            celda_datos_personales = new PdfPCell(texto_colonia);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
          
            
            celda_datos_personales = new PdfPCell(texto_municipio);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
    
            celda_datos_personales = new PdfPCell(new Paragraph("Calle y No. Int o Ext."));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(2);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
             celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           celda_datos_personales = new PdfPCell(new Paragraph("Colonia"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
             celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
         celda_datos_personales = new PdfPCell(new Paragraph("Municipio"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
             celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);      
//-----------------------------------------datos de entidad-------------------------------------------         
     celda_datos_personales = new PdfPCell(texto_entidad);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           
            celda_datos_personales = new PdfPCell(texto_CP);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
          
            
            celda_datos_personales = new PdfPCell(texto_telefono);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
    
           celda_datos_personales = new PdfPCell(texto_fecha_na);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           
            celda_datos_personales = new PdfPCell(new Paragraph("Entidad"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           celda_datos_personales = new PdfPCell(new Paragraph("C.P"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
         celda_datos_personales = new PdfPCell(new Paragraph("Teléfono"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           celda_datos_personales = new PdfPCell(new Paragraph("Fecha de nacimiento"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);

//-----------------------------------------datos de sexo-------------------------------------------         
     celda_datos_personales = new PdfPCell(texto_sexo);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           
            celda_datos_personales = new PdfPCell(texto_CURP);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
          
            
            celda_datos_personales = new PdfPCell(texto_trabaja);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
    
           celda_datos_personales = new PdfPCell(texto_estado_civil);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           
            celda_datos_personales = new PdfPCell(new Paragraph("Sexo"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           celda_datos_personales = new PdfPCell(new Paragraph("CURP"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
         celda_datos_personales = new PdfPCell(new Paragraph("Trabaja"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           celda_datos_personales = new PdfPCell(new Paragraph("Estado civil"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
//------------------------------Institucion de procedencia------------------------------//           

            celda_datos_personales = new PdfPCell(texto_inst_procedencia);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(3);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
    
           celda_datos_personales = new PdfPCell(texto_año_fin_lic);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           
            celda_datos_personales = new PdfPCell(new Paragraph("Institución de Procedencia"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(3);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           celda_datos_personales = new PdfPCell(new Paragraph("Año de terminación"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);        
//---------------------------PROMEDIO--------------------------------//
            celda_datos_personales = new PdfPCell(texto_promedio);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           
            celda_datos_personales = new PdfPCell(texto_ent_fed_escuela);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
          
            
            celda_datos_personales = new PdfPCell(texto_email);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(2);
            celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
    
            celda_datos_personales = new PdfPCell(new Paragraph("Promedio"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           celda_datos_personales = new PdfPCell(new Paragraph("Ent. Fed. de la Esc. de Procedencia"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
         celda_datos_personales = new PdfPCell(new Paragraph("Correo electronico"));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(2);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.TOP);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
//-----------------------Maestrias a tomar----------------------------------------------
            celda_datos_personales = new PdfPCell(new Paragraph("\n\nCarrera cursada: "));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
           // celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           
            celda_datos_personales = new PdfPCell(texto_carrera_cursada);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(3);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.BOTTOM);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
          
            
            celda_datos_personales = new PdfPCell(new Paragraph("Programa a tomar: "));
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(1);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.WHITE);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
    
            celda_datos_personales = new PdfPCell(texto_programa_tomar);
            // Indicamos cuantas columnas ocupa la celda
            celda_datos_personales.setColspan(3);
            //celda_datos_personales.setRowspan(4);
            celda_datos_personales.setBorderColor(BaseColor.BLACK);
            celda_datos_personales.setBorder(Rectangle.BOTTOM);
            celda_datos_personales.setHorizontalAlignment(Element.ALIGN_LEFT);
           // Agregar la celda a la tabla
           tabla_datos_personales.addCell(celda_datos_personales);
           
           //añadimos las celdas a la tabla
            document.add(tabla_datos_personales);
 
 
 ///////////////////////
 
 
            // Agregar un titulo con su respectiva fuente
            paragraph12.add(new Phrase("Observaciones:", fontTitulos));

            // Agregar saltos de linea
            paragraph12.add(new Phrase(Chunk.NEWLINE));
            

            // Agregar contenido con su respectiva fuente
            paragraph12.add(new Phrase("NOTA:\nEste documento solo ampara que subiste tu información real, el"
                    + " Tecnológico de Estudios Superiores de Coacalco evaluara tu documentación y datos personales."
                    + "\nSi todos tus datos son veridicos se te enviara a tu correo electronico proporcionado tu pase para tu examen \n", fontContenido));

            paragraph12.add(new Phrase(Chunk.NEWLINE));
           
            document.add(paragraph12);

 
            //////////////////////////////////////////////////////
            
         
            
            
            // Cerrar el documento
            document.close();

//             Abrir el archivo
            File file = new File("TescoAdmision.pdf");
            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
//            ex.printStackTrace();
        }
        
         
    }
    //int global=0;
    Connection con=CONEXION.conectar();
    Image icon1;
private void imagen(String matricula) throws IOException, BadElementException{
      byte[] fileBytes;
       PreparedStatement pstmt;
    
     try {
            pstmt = con.prepareStatement("SELECT * FROM tbl_alumnos WHERE str_pre_matricula = ?");
        

pstmt.setString(1, matricula);

//Obtengo el resultado de la consulta
ResultSet rs = pstmt.executeQuery();


//Si encuentra algo imprime el primer campo de los registro
while (rs.next()) {
    
   S_nombres=rs.getString("str_nombres");
   S_ap_pa=rs.getString("str_apellido_paterno");
   S_ap_ma=rs.getString("str_apellido_materno");
   S_CURP=rs.getString("str_curp");
   S_sexo=rs.getString("str_sexo");
   S_fecha_nacimiento=rs.getString("str_fecha_nacimiento");
   S_telefono=rs.getString("str_telefono");
   S_email=rs.getString("str_email");
   S_estado_civil=rs.getString("str_estado_civil");
   S_trabaja=rs.getString("str_trabaja");
   S_calle=rs.getString("str_calle_n_ext_int");
   S_entidad=rs.getString("str_estado");
   S_municipio=rs.getString("str_municipio");
   S_CP=rs.getString("str_codigo_postal");
   S_colonia=rs.getString("str_colonia");
   S_año_fin_lic=rs.getString("str_año_terminacion");
   S_carrera_cursada=rs.getString("str_carrera_profesional");
   S_cedula=rs.getString("str_cedula_profesional");
   S_promedio=rs.getString("str_promedio");
   S_inst_procedencia=rs.getString("str_institucion_procedencia");
   
   
       
      
    
    
   byte[] img = rs.getBytes("str_foto_usuario");
           java.awt.Image picture = getImage(img, false);
           icon1=Image.getInstance(img);
  icon = new ImageIcon(picture);
 
    System.out.println("si se encontro");
}
System.out.println("no se encontro");
} catch (SQLException ex) {
            Logger.getLogger(PDF_PRE_EXAMEN.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
 Icon icon=null;
   private java.awt.Image getImage(byte[] bytes, boolean isThumbnail) throws IOException  {

ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
Iterator readers = ImageIO.getImageReadersByFormatName("png");
ImageReader reader = (ImageReader) readers.next();
       
Object source = bis; // File or InputStream
ImageInputStream iis = ImageIO.createImageInputStream(source);
reader.setInput(iis, true);
ImageReadParam param = reader.getDefaultReadParam();

if (isThumbnail) {

param.setSourceSubsampling(4, 4, 0, 0);
 
}
return reader.read(0, param);

}	

    private void consulta_posgrado(String posgrado) {
     PreparedStatement pstmt; 
     try {
            pstmt = con.prepareStatement("SELECT  str_posgrado FROM tbl_posgrados WHERE str_id_posgrado = ?");
        

pstmt.setString(1, posgrado);

//Obtengo el resultado de la consulta
ResultSet rs = pstmt.executeQuery();


//Si encuentra algo imprime el primer campo de los registro
while (rs.next()) {
    S_programa_tomar=rs.getString("str_posgrado");
}

} catch (SQLException ex) {
            Logger.getLogger(PDF_PRE_EXAMEN.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}    
   