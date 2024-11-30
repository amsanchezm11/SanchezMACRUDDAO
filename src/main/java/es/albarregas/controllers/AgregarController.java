package es.albarregas.controllers;

import es.albarregas.DAO.IUsuariosDAO;
import es.albarregas.DAO.UsuariosDAO;
import es.albarregas.beans.UsuarioBean;
import es.albarregas.models.Utils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alberto
 */
@WebServlet(name = "AgregarController", urlPatterns = {"/AgregarController"})
public class AgregarController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // En caso de entrar por doGet reedirigimos al usuario al index
        request.getRequestDispatcher(".").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "";
        String mensaje = Utils.comprobarCampos(request);
        IUsuariosDAO adao = new UsuariosDAO();

        if (mensaje.equals("")) {
            // Rellenamos el Bean Usuario
            UsuarioBean usuario = Utils.rellenarUsuario(request);
            // Añadimos los datos del usuaio a la Base de Datos y modificamos el mensaje
            mensaje = adao.agregarUsuario(usuario);
            // Pasamos el mensaje por petición
            request.setAttribute("mensaje", mensaje);
            // Comprobamos si el mensaje contiene la palabra error
            if (mensaje.contains("Error")) {
                 url = "JSP/AGREGAR/agregarVista.jsp";          
            }else{
               // Pasamos por petición el bean
                request.setAttribute("usuarioAgregado", usuario);
                url = "JSP/AGREGAR/finAgregarVista.jsp";
            }            
        }else{
             /* En caso de que falte algún campo en el formulario o las contraseñas coincidan almacenamos el error 
            y los pasamos por petición */
            request.setAttribute("mensaje", mensaje);
            url = "/JSP/AGREGAR/agregarVista.jsp";
        }

        // Redirigimos al usuario a la url correspondiente
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
