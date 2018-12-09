/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacion.servlet;

import INSERTAR_INSCRIPCION.INSERTAR_INSCRIPCION_PAPELES;
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
@WebServlet(name = "insertar_inscripcion", urlPatterns = {"/insertar_inscripcion"})
@MultipartConfig(maxFileSize = 16177215) 
public class Servlet_inscripcion_alumno extends HttpServlet {

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
        
         String matricula=null;
        
         matricula=request.getParameter("lblMatricula");
         
          InputStream leer_pago = null; // entrada para subir la foto o equis archivo
          Part filePart_foto_pago = request.getPart("foto_pago");
          leer_pago =  filePart_foto_pago.getInputStream();
          byte[] foto_pago = null;
          int image_pago = (int) filePart_foto_pago.getSize();
          foto_pago = new byte[image_pago];
          leer_pago.read(foto_pago,0,image_pago);
          
          InputStream leer_foto_domicilio = null; // entrada para subir la foto o equis archivo
          Part filePart_foto_domicilio = request.getPart("foto_domicilio");
          leer_foto_domicilio =  filePart_foto_domicilio.getInputStream();
          byte[] foto_comprobante_domicilio = null;
          int image_comprobante_domicilio = (int) filePart_foto_domicilio.getSize();
          foto_comprobante_domicilio = new byte[image_comprobante_domicilio];
          leer_foto_domicilio.read(foto_comprobante_domicilio,0,image_comprobante_domicilio);
          
          InputStream leer_pdf_tesco = null; // entrada para subir la foto o equis archivo
          Part filePart_pdf_tesco = request.getPart("pdf_tesco");
          leer_pdf_tesco =  filePart_pdf_tesco.getInputStream();
          byte[] pdf_tesco = null;
          int image_pdf_tesco = (int) filePart_pdf_tesco.getSize();
          pdf_tesco = new byte[image_pdf_tesco];
          leer_pdf_tesco.read(pdf_tesco,0,image_pdf_tesco);

          InputStream leer_foto_recomendacion = null; // entrada para subir la foto o equis archivo
          Part filePart_foto_recomendacion = request.getPart("foto_recomendacion");
          leer_foto_recomendacion =  filePart_foto_recomendacion.getInputStream();
          byte[] foto_recomendacion= null;
          int image_foto_recomendacion = (int) filePart_foto_recomendacion.getSize();
          foto_recomendacion = new byte[image_foto_recomendacion];
          leer_foto_recomendacion.read(foto_recomendacion,0,image_foto_recomendacion);
       
          INSERTAR_INSCRIPCION_PAPELES IIP=new INSERTAR_INSCRIPCION_PAPELES();
         
           int ca=IIP.INSERTAR(foto_pago,foto_comprobante_domicilio,pdf_tesco,foto_recomendacion,matricula);
          
          
      if(ca>0){
              out.print("<H2>SE SUBIO TU DOCUMENTACIÓN CON EXITO: "+matricula+"</H2>");
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
