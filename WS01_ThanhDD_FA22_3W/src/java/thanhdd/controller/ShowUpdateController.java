/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhdd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thanhdd.registration.RegistrationDAO;
import thanhdd.registration.RegistrationDTO;

/**
 *
 * @author 15tha
 */
@WebServlet(name = "ShowUpdateController", urlPatterns = {"/ShowUpdateController"})
public class ShowUpdateController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update</title>");
            out.println("</head>");
            out.println("<body>");
            String username = request.getParameter("userName");
            try {
                RegistrationDTO regist = new RegistrationDAO().search(username).get(0);
                out.println("username : <input type='text' disable value='" + regist.getUsername() + "'>");
                out.println("<br>");
                out.println("password : <input type='text' disable value='" + regist.getPassword()+ "'>");
                out.println("<br>");
                out.println("last name: <input type='text' disable value='" + regist.getLastName()+ "'>");
                out.println("<br>");
                out.println("role : <label for='admin'>admin</label> <input id='admin' type='radio' name='role' disable  value='0' " 
                        + (regist.isRole() ? "checked" : "") + ">");
                
                out.println("<label for='user'>user</label> : <input  id='user' type='radio' name='role' disable value='1'"
                        + (regist.isRole() ? "checked" : "")+ ">");
                out.println("<br>");
                out.print("<input type=\"submit\" name=\"btnAction\" value=\"updateController\">");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            out.println("</body>");
            out.println("</html>");
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
