/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacion.servlet;

import INSERTAR.INSERTAR_ADMISION;
import INSERTAR.INSERTAR_DOCUMENTOS;
import REPORTES.PDF_PRE_EXAMEN;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author hp
 */
@WebServlet(name = "insertar_admision", urlPatterns = {"/insertar_admision"})
@MultipartConfig(maxFileSize = 16177215) 
public class Servlet_admision_insertar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
       
            
            String nombres=null, ap_p=null, ap_m=null, curp=null, rfc=null, ine=null,
             sexo=null, fecha_na=null, telefono=null, celular=null, email=null, est_civil=null,
             trabaja=null, giro=null,domicilio=null,estado=null,municipio=null,cp=null,colonia=null,
             inst_procedencia=null,año_terminacion=null,carrera_profesional=null,cedula=null,promedio=null,entidad_escuela=null,posgrado=null;
          /////////////////7DATOS PERSONALES///////////////////
          InputStream leer_foto_usuario = null; // entrada para subir la foto o equis archivo
          Part filePart_foto_usuario = request.getPart("foto_usuario");
          leer_foto_usuario =  filePart_foto_usuario.getInputStream();
          byte[] foto_usuario = null;
          int image_usuario = (int) filePart_foto_usuario.getSize();
          foto_usuario = new byte[image_usuario];
          leer_foto_usuario.read(foto_usuario,0,image_usuario);
          nombres=request.getParameter("txt_nombre");
          ap_p=request.getParameter("txt_apellido_p");
          ap_m=request.getParameter("txt_apellido_m");
          curp=request.getParameter("txt_curp");
          rfc=request.getParameter("txt_rfc");
          ine=request.getParameter("txt_ine");
          sexo=request.getParameter("check_sexo");
          fecha_na=request.getParameter("txt_fecha");
          telefono=request.getParameter("txt_telefono");
          celular=request.getParameter("txt_celular");
          email=request.getParameter("txt_email");
          est_civil=request.getParameter("cmb_civil");
          trabaja=request.getParameter("radio_trabaja");
          giro=request.getParameter("cmb_oficios");
         //////DOMICILIO//////////7
          domicilio=request.getParameter("txt_domicilio");
          estado=request.getParameter("cmb_estado");
          municipio=request.getParameter("cmb_municipio");
          cp=request.getParameter("cmb_cp");
          colonia=request.getParameter("cmb_colonia");
          //////////INSTITUCIÓN DE PROCEDENCIA//////////////
          inst_procedencia=request.getParameter("cmb_inst_procedencia");
          año_terminacion =request.getParameter("cmb_year_terminacion");
          carrera_profesional=request.getParameter("cmb_carrera_profesional");
          cedula=request.getParameter("txt_cedula");
          promedio=request.getParameter("txt_promedio");
          entidad_escuela=request.getParameter("cmb_procedencia_escuela");
          //////////DOCUMENTOS//////////////////
          InputStream leer_comprobante_pago = null; // entrada para subir la foto o equis archivo
          Part filePart_foto_comprobante_pago = request.getPart("foto_pago");
          leer_comprobante_pago =  filePart_foto_comprobante_pago.getInputStream();
          byte[] foto_comprobante_pago = null;
          int image_comprobante_pago = (int) filePart_foto_comprobante_pago.getSize();
          foto_comprobante_pago = new byte[image_comprobante_pago];
          leer_comprobante_pago.read(foto_comprobante_pago,0,image_comprobante_pago);
         
          InputStream leer_acta_nacimiento = null; // entrada para subir la foto o equis archivo
          Part filePart_foto_acta_nacimiento = request.getPart("foto_acta");
          leer_acta_nacimiento =  filePart_foto_acta_nacimiento.getInputStream();
          byte[] foto_acta_nacimiento = null;
          int image_acta_nacimiento = (int) filePart_foto_acta_nacimiento.getSize();
          foto_acta_nacimiento = new byte[image_acta_nacimiento];
          leer_acta_nacimiento.read(foto_acta_nacimiento,0,image_acta_nacimiento);
          
          InputStream leer_foto_domicilio = null; // entrada para subir la foto o equis archivo
          Part filePart_foto_domicilio = request.getPart("foto_domicilio");
          leer_foto_domicilio =  filePart_foto_domicilio.getInputStream();
          byte[] foto_domicilio = null;
          int image_foto_domicilio = (int) filePart_foto_domicilio.getSize();
          foto_domicilio = new byte[image_foto_domicilio];
          leer_foto_domicilio.read(foto_domicilio,0,image_foto_domicilio);
          
          InputStream leer_certificado = null; // entrada para subir la foto o equis archivo
          Part filePart_foto_certificado = request.getPart("foto_certificado");
          leer_certificado =  filePart_foto_certificado.getInputStream();
          byte[] foto_certificado = null;
          int image_certificado = (int) filePart_foto_certificado.getSize();
          foto_certificado = new byte[image_certificado];
          leer_certificado.read(foto_certificado,0,image_certificado);
          
          InputStream leer_cedula = null; // entrada para subir la foto o equis archivo
          Part filePart_foto_cedula = request.getPart("foto_cedula");
          leer_cedula =  filePart_foto_cedula.getInputStream();
          byte[] foto_cedula = null;
          int image_cedula = (int) filePart_foto_cedula.getSize();
          foto_cedula = new byte[image_cedula];
          leer_cedula.read(foto_cedula,0,image_cedula);
          
          InputStream leer_foto_ingles = null; // entrada para subir la foto o equis archivo
          Part filePart_foto_ingles = request.getPart("foto_ingles");
          leer_foto_ingles =  filePart_foto_ingles.getInputStream();
          byte[] foto_comprobante_ingles = null;
          int image_comprobante_ingles = (int) filePart_foto_ingles.getSize();
          foto_comprobante_ingles = new byte[image_comprobante_ingles];
          leer_foto_ingles.read(foto_comprobante_ingles,0,image_comprobante_ingles);
          
          InputStream leer_pdf_vitae = null; // entrada para subir la foto o equis archivo
          Part filePart_pdf_vitae = request.getPart("pdf_vitae");
          leer_pdf_vitae =  filePart_pdf_vitae.getInputStream();
          byte[] pdf_vitae = null;
          int image_pdf_vitae = (int) filePart_pdf_vitae.getSize();
          pdf_vitae = new byte[image_pdf_vitae];
          leer_pdf_vitae.read(pdf_vitae,0,image_pdf_vitae);

          InputStream leer_foto_expomotivos = null; // entrada para subir la foto o equis archivo
          Part filePart_foto_expomotivos = request.getPart("foto_expomotivos");
          leer_foto_expomotivos =  filePart_foto_expomotivos.getInputStream();
          byte[] foto_expomotivos= null;
          int image_foto_expomotivos = (int) filePart_foto_expomotivos.getSize();
          foto_expomotivos = new byte[image_foto_expomotivos];
          leer_foto_expomotivos.read(foto_expomotivos,0,image_foto_expomotivos);
          
             //algunos metodos del filepart
//             System.out.println(filePart.getName());
//            System.out.println(filePart.getSize());
//            System.out.println(filePart.getContentType());
            
           ////////////////////POSGRADO///////////////////
            posgrado=request.getParameter("cmb_posgrado_tomar");
            /////////////////////////////////////////////
            
            
         INSERTAR_ADMISION IA=new INSERTAR_ADMISION();
         int ca=IA.insertar(foto_usuario,nombres, ap_p, ap_m, curp, rfc, ine, sexo, fecha_na, telefono, celular, email, est_civil, trabaja, giro,
                            domicilio,estado,municipio,cp,colonia,
                            año_terminacion,carrera_profesional,cedula,promedio,inst_procedencia,posgrado);
         
         if(ca>0){
            INSERTAR_DOCUMENTOS ID=new INSERTAR_DOCUMENTOS();
             ID.insertar_documentos(foto_comprobante_pago,foto_acta_nacimiento,foto_domicilio,
                     foto_certificado,foto_cedula,foto_comprobante_ingles,pdf_vitae,foto_expomotivos,IA.matricula);
             PDF_PRE_EXAMEN PPE=new PDF_PRE_EXAMEN();
             PPE.PDF(IA.matricula, entidad_escuela,posgrado);//MODIFICAR ESTE METODO PARA LLENARLO CON DATOS DEL USUARIO
           out.print("<H2>REGISTRADO CON EXITO</H2>");
           response.sendRedirect("/proyecto_posgrado_tesco/");
         }else{
            out.print("<H2>HAY UN ERROR VUELVE A INTENTAR REGRESANDO A LA PAGINA ANTERIOR</H2>"); 
         }
        }catch(Exception e){
            System.err.println("un error");
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
