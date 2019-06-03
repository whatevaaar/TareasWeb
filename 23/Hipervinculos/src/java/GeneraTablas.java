
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
public class GeneraTablas extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;

    int numeroDeColumnas;
    int numeroDeFilas;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        numeroDeColumnas = obtenerNumeroDeColumnas();
        numeroDeFilas = obtenerNumeroDeFilas();
        String[] hipervinculos = obtenerHipervinculos();
        generarTabla(hipervinculos);

    }

    protected String[] obtenerHipervinculos() {
        int numeroDeHipervinculos = numeroDeColumnas * numeroDeFilas;
        String[] hipervinculos = new String[numeroDeHipervinculos];
        for (int i = 0; i < numeroDeHipervinculos; i++) {
            hipervinculos[i] = obtenerHipervinculoX(i);
        }
        return hipervinculos;
    }

    protected void generarTabla(String[] hipervinculos)
            throws ServletException, IOException {
        int contadorHipervinculo = 0;
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>\n"
                    + "    <head>\n"
                    + "        <title>Promedios</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    </head>\n"
                    + "    <body>"
                    + "<table width=\"200\" border=\"1\" class=\"table\">\n");
            for (int i = 0; i < numeroDeColumnas; i++) {
                out.println("<tr>\n");

                for (int j = 0; j < numeroDeFilas; j++) {
                    out.println("<th><a href=\"http://" + hipervinculos[contadorHipervinculo++] + "\">Link</a></th>\n");

                }
                out.println("</tr>\n");

            }
            out.println("</table>"
                    + "</body>\n"
                    + "</html>");
        }
    }

    protected String obtenerHipervinculoX(int X) {
        String valor = this.request.getParameter("hipervinculo" + X);
        return valor;
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
