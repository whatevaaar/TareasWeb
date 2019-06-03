import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Kramer extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;
    int x1;
    int y1;
    int s1;

    int x2;
    int y2;
    int s2;

    int xSol;
    int ySol;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        inicializarVariables();
        resolverEcuacion();
        redirigir();
    }

    protected void redirigir() {
        request.setAttribute("xSol", this.xSol);
        request.setAttribute("ySol", this.ySol);
        try {
            request.getRequestDispatcher("Solucion.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

    protected int calcularDeterminante(int a0, int a1, int b0, int b1) {
        return (a0 * b1) - (a1 * b0);
    }

    protected void resolverEcuacion() {
        int determinanteDelSistema = calcularDeterminante(x1, y1, x2, y2);
        int determinanteX = calcularDeterminante(s1, y1, s2, y2);
        int determinanteY = calcularDeterminante(x1, s1, x2, s2);
        if (determinanteDelSistema != 0) {
            this.xSol = determinanteX / determinanteDelSistema;
            this.ySol = determinanteY / determinanteDelSistema;
        }
    }

    protected void inicializarVariables() {
        this.x1 = obtenerValorDeParametro("x1");
        this.y1 = obtenerValorDeParametro("y1");
        this.s1 = obtenerValorDeParametro("s1");

        this.x2 = obtenerValorDeParametro("x2");
        this.y2 = obtenerValorDeParametro("y2");
        this.s2 = obtenerValorDeParametro("s2");
    }

    protected int obtenerValorDeParametro(String parametro) {
        String valor = this.request.getParameter(parametro);
        return Integer.parseInt(valor);
    }

}
