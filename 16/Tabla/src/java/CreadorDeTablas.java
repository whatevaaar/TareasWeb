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
public class CreadorDeTablas extends HttpServlet {

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            out.print("<html>\n"
                    + "<head>\n"
                    + "<meta charset=utf-8 />\n"
                    + "<title>Draw a line</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + " <table>");
            for (int i = 0; i < obtenerValorM(request); i++) {
                out.print("  <tr>\n");
                for (int j = 0; j < obtenerValorN(request); j++)
                    out.print("    <td>celda</td>\n");
                
            }
            out.print("</script> \n" +
"                         </body>\n" +
"                        </html>");
            }catch (Exception e) {
        }

        }
    
     protected int obtenerValorN(HttpServletRequest request) {
        String cadenaNumeroDeParametros = (String) request.getParameter("valorN");
        return Integer.parseInt(cadenaNumeroDeParametros);
    }
     
      protected int obtenerValorM(HttpServletRequest request) {
        String cadenaNumeroDeParametros = (String) request.getParameter("valorM");
        return Integer.parseInt(cadenaNumeroDeParametros);
    }
     
}
