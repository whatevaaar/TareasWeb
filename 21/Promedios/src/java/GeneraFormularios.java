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
        int numeroDeParametros = obtenerNumeroDeParametros();
        generarFormulario(numeroDeParametros);

    }

    protected void generarFormulario(int numeroDeParametros)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>\n"
                    + "    <head>\n"
                    + "        <title>Promedios</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "      <form action=\"ServletPromedios\" method=\"post\">\n"
                    + " <input type=\"hidden\" name=\"numeroDeParametros\""
                    + "value=\"" + numeroDeParametros + "\"/>");
            for (int i = 0; i < numeroDeParametros; i++) {
                out.println("Valor: <input type=\"text\" name=\"valor" + i + "\"/>\n");
            }

            out.println("            <button type =\"submit\">Ir</button>\n"
                    + "    </body>\n"
                    + "</html>");
        }
    }

    protected int obtenerNumeroDeParametros() {
        String valor = this.request.getParameter("numeroDeParametros");
        return Integer.parseInt(valor);
    }
}
