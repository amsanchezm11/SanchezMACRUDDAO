package es.albarregas.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * Controlador que va a gestionar los botones Canccelar e Inicio
 * @author alberto
 */
@WebServlet(name = "VolverController", urlPatterns = {"/VolverController"})
public class VolverController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Aquí manejamos la redirección de los botones Cancelar e Inicio
        // Obtenemos la sesión existente
        HttpSession sesion = request.getSession(false);
        
        // En caso de que exista eliminamos la sesión y redirigimos al usuario al index
        //if (request.getSession() != null) {
        //    sesion.invalidate();                    // BORRAR MÁS ADELANTE
        //}
        
        if (sesion.getAttribute("usuarios") != null) {
            sesion.removeAttribute("usuarios");
        }
        // Redirigimos al usuario al index    
        request.getRequestDispatcher(".").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
