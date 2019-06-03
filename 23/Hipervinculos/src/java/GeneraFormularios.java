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

    HttpServletRequest request;
    HttpServletResponse response;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        int numeroDeColumnas = obtenerNumeroDeColumnas();
        int numeroDeFilas = obtenerNumeroDeFilas();
        generarFormulario(numeroDeColumnas, numeroDeFilas);

    }

    protected void generarFormulario(int numeroDeColumnas, int numeroDeFilas)
            throws ServletException, IOException {
        int numeroDeParametros = numeroDeColumnas * numeroDeFilas;
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>\n"
                    + "    <head>\n"
                    + "        <title>Promedios</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "      <form action=\"GeneraTablas\" method=\"post\">\n"
                    + " <input type=\"hidden\" name=\"numeroDeFilas\" value=\"" + numeroDeFilas + "\"/>"
                    + " <input type=\"hidden\" name=\"numeroDeColumnas\" value=\"" + numeroDeColumnas + "\"/>");
            for (int i = 0; i < numeroDeParametros; i++) {
                out.println("Hipervínculo: <input type=\"text\" name=\"hipervinculo" + i + "\"/><br/>");
            }

            out.println("            <button type =\"submit\">Ir</button>\n"
                    + "    </body>\n"
                    + "</html>");
        }
    }

    protected int obtenerNumeroDeColumnas() {
        String valor = this.request.getParameter("numeroDeColumnas");
        return Integer.parseInt(valor);
    }

    protected int obtenerNumeroDeFilas() {
        String valor = this.request.getParameter("numeroDeFilas");
        return Integer.parseInt(valor);
    }
}
