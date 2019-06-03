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
public class GeneraFormularios extends HttpServlet {

    int numeroDeParametros;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        numeroDeParametros = conseguirNumeroDeParametros(request);
        generarFormulario(response, numeroDeParametros);
    }

    protected void generarFormulario(HttpServletResponse response, int numeroDeParametros) {
        try {
            PrintWriter out = response.getWriter();
            out.print("<form action=\"MuestraParametros\" method=\"post\">\n");
            out.print("<input id=\"numero\" name=\"numeroDeParametros\" type=\"hidden\" value=\""+ numeroDeParametros +"\">");
            for (int i = 0; i < numeroDeParametros; i++) {
                out.print("Parámetro " + i + ":<br>\n"
                        + "<input type=\"text\" name=\"parametro_" + i + "\"><br>");
            }
            out.print("<input type=\"submit\" value=\"Click!\">\n"
                    + "        </form> ");

        } catch (Exception e) {
        }
    }

    protected int conseguirNumeroDeParametros(HttpServletRequest request) {
        String cadenaNumeroDeParametros = (String) request.getParameter("numeroDeParametros");
        return Integer.parseInt(cadenaNumeroDeParametros);
    }
}
