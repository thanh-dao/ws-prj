/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhdd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thanhdd.registration.RegistrationDTO;

/**
 *
 * @author 15tha
 */
@WebServlet(name = "ShowSearchController", urlPatterns = {"/ShowSearchController"})
public class ShowSearchController extends HttpServlet {

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
            out.println("<title>Servlet ShowSearchController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowSearchController at " + request.getContextPath() + "</h1>");
            String searchValue = request.getParameter("searchValue");
            out.println("Your search values is : " + searchValue);
            List<RegistrationDTO> list = (List<RegistrationDTO>) request.getAttribute("searchedItems");
            if (list.size() > 0) {
                out.println("<table border=\"1\" style='border-collapse: collapse  '>\n"
                        + "            <thead>\n"
                        + "                <tr>\n"
                        + "                    <th>No.</th>\n"
                        + "                    <th>Username</th>\n"
                        + "                    <th>Password</th>\n"
                        + "                    <th>Last Name</th>\n"
                        + "                    <th>Roles</th>\n"
                        + "                    <th colspan='2'>Action</th>\n"
                        + "                </tr>\n"
                        + "            </thead>\n"
                        + "            <tbody>\n"
                );
                int count = 0;
                for (int i = 0; i < list.size(); i++) {
                    RegistrationDTO r = list.get(i);
                    out.println("<tr>\n");
                    out.println("<td>" + i + "</td>\n");
                    out.println("<td>" + r.getUsername() + "</td>\n");
                    out.println("<td>" + r.getPassword() + "</td>\n");
                    out.println("<td>" + r.getLastName() + "</td>\n");
                    out.println("<td>" + r.isRole() + "</td>\n");
                    out.println(
                        "<td>" 
                        + "<a href='ShowUpdateController?userName=" + r.getUsername() 
                                + "&lastSearchValue=" + searchValue +
                                "'> Update </a>"
                        + "</td>"
                    );
                    out.println("<td>" + "<a href=ConfirmDeleteController?userName=" + r.getUsername() + "> Delete </a>");
                    out.println("</tr>\n");
                }
                out.println(
                          "</tbody>"
                        + "</table>");
            } else {
                out.println("Not found!!!");
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
