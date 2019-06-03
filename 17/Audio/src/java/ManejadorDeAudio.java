/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nexus
 */
public class ManejadorDeAudio extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/");
        String nombre = obtenerNombre(request);
        String ruta = path + "audioFiles/" + nombre;
        try {
            PrintWriter out = response.getWriter();
            out.print("<html>\n"
                    + "<head>\n"
                    + "<meta charset=utf-8 />\n"
                    + "<title>Audio</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + " <audio controls>\n"
                    + "  <source src=\""+ ruta +".mp3\" type=\"audio/mpeg\">\n"
                    + "</audio> "
                    + "</script> \n"
                    + "</body>\n"
                    + "</html>");
        } catch (Exception e) {
        }

    }

    protected String obtenerNombre(HttpServletRequest request) {
        return (String) request.getParameter("nombreArchivo");
    }
}
