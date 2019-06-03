/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.IntStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nexus
 */
public class ServletPromedios extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;
    int promedio;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        int numeroDeParametros = obtenerNumeroDeParametros();
        calcularPromedio(numeroDeParametros);
        redirigir();
    }

    protected void calcularPromedio(int numeroDeParametros) {
        int[] valores = obtenerValores( numeroDeParametros);
        int sumaDeValores = IntStream.of(valores).sum();
        this.promedio = sumaDeValores/numeroDeParametros;
    }
    
    protected void redirigir(){
        request.setAttribute("promedio", this.promedio);
        try{
        request.getRequestDispatcher("Solucion.jsp").forward(request, response);
                }
        catch(Exception e){}
    }

    protected int[] obtenerValores(int numeroDeParametros) {
        int[] valores = new int[numeroDeParametros];
        for (int i = 0; i < numeroDeParametros; i++) {
            valores[i] = obtenerParametroX(i);
        }
        return valores;
    }

    protected int obtenerParametroX(int x) {
        String valor = this.request.getParameter("valor" + x);
        return Integer.parseInt(valor);
    }

    protected int obtenerNumeroDeParametros() {
        String valor = this.request.getParameter("numeroDeParametros");
        return Integer.parseInt(valor);
    }
}
