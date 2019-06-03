/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nexus
 */
public class CreadorDeCanvas extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int numeroDeCirculos = conseguirNumeroDeCirculos(request);
        try {
            PrintWriter out = response.getWriter();
            out.print("<html>\n"
                    + "<head>\n"
                    + "<meta charset=utf-8 />\n"
                    + "<title>Figuras</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<canvas id=\"DemoCanvas\" width=\"500\" height=\"200\"></canvas>  \n"
                    + "<script>  \n"
                    + "var canvas = document.getElementById('DemoCanvas');\n"
                    + "if (canvas.getContext) \n"
                    + " {\n"
                    + "  var context = canvas.getContext('2d');\n");
            for (int i = 0; i < numeroDeCirculos; i++) {
                out.print("   context.moveTo(100," + generarRandom() + ");\n"
                        + "  context.arc(" + generarRandom() * 100 + "," + generarRandom() + ", "
                        + generarRandom() + ", " +"0," + "2 * Math.PI);\n"
                        + "  context.stroke();\n"
                );
            }
            out.print("}</script> \n"
                    + "</body>\n"
                    + "</html>");
        } catch (Exception e) {
        }

    }

    protected int generarRandom() {
        return ThreadLocalRandom.current().nextInt(1, 10);
    }

    protected int conseguirNumeroDeCirculos(HttpServletRequest request) {
        String cadenaNumeroDeParametros = (String) request.getParameter("numeroDeCirculos");
        return Integer.parseInt(cadenaNumeroDeParametros);
    }

}
