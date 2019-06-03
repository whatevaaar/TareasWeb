
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResuelveEcuaciones extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;
    int terminoA;
    int terminoB;
    int terminoC;

    double solucion1;
    double solucion2;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        inicializarVariables();
        resolverEcuacion();
    }

    protected void redirigirSolucion() {
        request.setAttribute("solucion1", this.solucion1);
        request.setAttribute("solucion2", this.solucion2);
        try {
            request.getRequestDispatcher("Solucion.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }
    
    protected void redirigirSinSolucion() {
        try {
            request.getRequestDispatcher("SinSolucion.html").forward(request, response);
        } catch (Exception e) {
        }
    }

    protected void resolverEcuacion() {
        double valorRaiz = resolverRaiz( this.terminoA,  this.terminoB,  this.terminoC);
        if (valorRaiz <0 ){
            redirigirSinSolucion();
        }
        this.solucion1 = (-this.terminoB + valorRaiz) / (2 * this.terminoA);
        this.solucion2 = (-this.terminoB + valorRaiz) / (2 * this.terminoA);
        redirigirSolucion();
        
    }

    protected double resolverRaiz(int terminoA, int terminoB, int terminoC) {
        return Math.pow(terminoB, 2) - (4 * (terminoA * terminoC)); 
    }

    protected void inicializarVariables() {
        this.terminoA = obtenerValorDeParametro("terminoA");
        this.terminoB = obtenerValorDeParametro("terminoB");
        this.terminoC = obtenerValorDeParametro("terminoC");
    }

    protected int obtenerValorDeParametro(String parametro) {
        String valor = this.request.getParameter(parametro);
        return Integer.parseInt(valor);
    }

}
