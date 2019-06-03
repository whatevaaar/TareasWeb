/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nexus
 */
public class GeneraHipervinculos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            out.print("<form action=\"GeneraHipervinculos\" method=\"post\">\n");
            out.print("Hipervinculo: <input id=\"enlace\" name=\"enlace\" type=\"text\"\n>");
            out.print("<input type=\"submit\" value=\"Click!\">\n</form> ");

        } catch (Exception e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String enlace = (String) request.getParameter("enlace");
        PrintWriter out = response.getWriter();
        out.println("<a href='http://www." + enlace + ".com'>Ir</a>");

    }

}
