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
public class MuestraParametros extends HttpServlet {

    int numeroDeParametros;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        numeroDeParametros = conseguirNumeroDeParametros(request);
        mostrarParametros( response,  request,  numeroDeParametros);
    }

    protected int conseguirNumeroDeParametros(HttpServletRequest request) {
        String cadenaNumeroDeParametros = (String) request.getParameter("numeroDeParametros");
        return Integer.parseInt(cadenaNumeroDeParametros);
    }

    protected String conseguirParametroNumeroI(HttpServletRequest request, int i) {
        return (String) request.getParameter("parametro_" + i);
    }
    
    protected void mostrarParametros(HttpServletResponse response, HttpServletRequest request, int numeroDeParametros) {
        try {
            PrintWriter out = response.getWriter();
            for (int i = 0; i < numeroDeParametros; i++) {
                out.print("Parámetro " + i + ":<br>\n"+ conseguirParametroNumeroI(request, i));
            }

        } catch (Exception e) {
        }
    }
}
