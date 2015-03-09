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
 * <p>This class represents the controller for all Messages sent by the user.</p>
 * A message contains:
 * Label.
 * Message.
 * Encoding.
 * 
 * The encoding of a message is either ASCII or HEXA.
 *     
 */
@WebServlet(name = "ReceivedMessageController", urlPatterns = {"/Poc2/ReceivedMessageController"})
public class ReceivedMessageController extends HttpServlet {

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

			    final String label = request.getParameter("label");
			    final String message = request.getParameter("message");
			    final String encoding = request.getParameter("encoding");

			    SerialComm serial_port = new SerialComm(); 


			    /*Test to check if the request contains all parameters*/
			    if(label != null && message!= null && encoding != null){
                                if(label.trim().equals("")){
                		    out.print("failed to trim label");
				    return;
                                }
	    

                                if(message.trim().equals("")){
                                    out.print("failed to trim message");
                                    return;
                                }

                                if(encoding.equals("ascii")){
                                    /*Message in ascii*/
                                    serial_port.write(message);
                                }                          
                                else if(encoding.equals("hexa")){
                                    /*Message in hexa*/
                                    /*Validation*/

                                    int messageInHexa;
                                    try{
                                        messageInHexa = Integer.parseInt(message,16);                                        
                                        serial_port.write(messageInHexa);
                                    }
                                    catch(NumberFormatException e){
                                        out.print("failed to send message in hexa");
                                        return;
                                    }
                                }   
                                else{
                                    out.print("unown econding");
                                    return;
                                }

                                out.print("label: "+label+"\nmessage: "+message+"\nencoding: "+encoding);
                            }
                            else{
                                out.print("Void label, message or encoding");
			    } 
		    } catch (NullPointerException e){
			    System.err.println(e.toString());
			    System.out.print("Null Pointer!\nPerhaps the port is not initilized");

			    // Case where the string is null
			    //if(SerialComm.serialPort != null)
			    //  serial_port.write(btnId + "\n");
		    } catch (Exception e) {
			    // TODO Auto-generated catch block
			    System.err.println(e.toString());
			    System.out.print("Unmapped Error");
		    } finally{
			    // TODO[bramigk]: check if the port is open and close port 
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
