package es.albarregas.controllers;

import es.albarregas.DAO.IUsuariosDAO;
import es.albarregas.DAO.UsuariosDAO;
import es.albarregas.beans.UsuarioBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alberto
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // En caso de entrar por doGet reedirigimos al usuario al index
        request.getRequestDispatcher(".").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Url para redireccionar
        String url = "";
        IUsuariosDAO adao = new UsuariosDAO();
        List<UsuarioBean> usuarios = new ArrayList<>();
        String boton = request.getParameter("boton-principal");

        switch (boton) {
            case "Agregar":
                url = "/JSP/AGREGAR/agregarVista.jsp";
                break;
            /* Tanto en los casos visualizar, modificar y eliminar vamos a pasar la lista completa de los usuarios con
                sus atributos ya que con una sola consulta a la BBDD obtengo una lista que es compatible con todas las 
                vistas que vamos a realizar en adelante */
            case "Visualizar":
                usuarios = adao.getTodosUsuarios();
                if (usuarios == null || usuarios.isEmpty()) {
                    url = "JSP/NOTIFICACIONES/sinDatosVista.jsp";
                } else {
                    request.setAttribute("listaVisualizar", usuarios);
                    url = "JSP/VISUALIZAR/visualizarVista.jsp";
                }
                break;
            /* Tanto en los casos modificar y eliminar vamos a pasar la lista completa de los usuarios con
                los atributos id, nombre y apellidos por sesión */
            case "Modificar":
            case "Eliminar":
                // Obtenemos la lista de usuarios de la bbdd y la pasamos pos sesión en caso de que haya registros
                usuarios = adao.getUsuariosNombreApellidos();
                if (usuarios == null || usuarios.isEmpty()) {
                    url = "JSP/NOTIFICACIONES/sinDatosVista.jsp";
                } else {
                    
                    // Pasamos la lista por sesión en ambos casos
                    request.getSession().setAttribute("usuarios", usuarios);
                    if (boton.equals("Modificar")) {
                        url = "JSP/MODIFICAR/modificarVista.jsp";
                    } else {
                        url = "JSP/ELIMINAR/eliminarVista.jsp";
                    }

                }
                break;
        }
        // Redirigimos al usuario a la url correspondiente
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
