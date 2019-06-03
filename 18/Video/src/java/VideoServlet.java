/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
public class VideoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreDeArchivo = generarNombreDeArchivo(request);
        reproducirVideo(nombreDeArchivo, response);
    }

    protected String generarNombreDeArchivo(HttpServletRequest request) {
        String archivo = obtenerNombre(request);
        String nombre = obtenerNombre(request);
        String nombreDeArchivo = "Video/" + nombre + ".mp4";
        return nombreDeArchivo;
    }

    protected void reproducirVideo(String nombreDeArchivo, HttpServletResponse response) {
        ServletContext ct = getServletContext();
        InputStream input = ct.getResourceAsStream(nombreDeArchivo);
        response.setContentType("video/mp4");

        response.setHeader("Content-Disposition", "inline; filename=\""
                + nombreDeArchivo + "\"");

        try {
            OutputStream output = response.getOutputStream();
            byte[] buffer = new byte[2096];
            int read = 0;
            while ((read = input.read(buffer)) != -1) 
                output.write(buffer, 0, read);
            input.close();
            output.flush();
            output.close();
        } catch (Exception e) {
        }

    }

    protected String obtenerNombre(HttpServletRequest request) {
        return (String) request.getParameter("nombreVideo");
    }

}
