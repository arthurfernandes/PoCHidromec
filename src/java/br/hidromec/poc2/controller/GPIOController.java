/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.hidromec.poc2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author arthurfernandes
 * 
 * <p>This class represents the controller for the GPIO<p>
 * The user requests a specified gpio (1-8) to be ON/OFF.
 */
@WebServlet(name = "GPIOController", urlPatterns = {"/Poc2/GPIOController"})
public class GPIOController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            final String gpio = request.getParameter("gpio");
            final String onOff = request.getParameter("set");
            
            /*Check if the request contains all parameters*/
            if(gpio != null && onOff != null){
                /*Get gpioIndex*/
                int gpioIndex;
                try{
                    gpioIndex = Integer.parseInt(gpio);
                }
                catch(NumberFormatException e){
                    out.print("failed");
                    return;
                }
                
                /*Get on/off as boolean - On means true, Off means false*/
                final boolean setUnsetBoolean;
                switch (onOff) {
                    case "true":
                        setUnsetBoolean = true;
                        break;
                    case "false":
                        setUnsetBoolean = false;
                        break;
                    default:
                        out.print("failed");
                        return;
                }
                
                /*Do GPIO communication*/
                out.print("success");
            }
            else{
                out.print("failed");
            }
        }
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
