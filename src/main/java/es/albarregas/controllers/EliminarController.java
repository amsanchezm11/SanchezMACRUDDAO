package es.albarregas.controllers;

//import es.albarregas.connections.Conexion;
import es.albarregas.DAO.IUsuariosDAO;
import es.albarregas.DAO.UsuariosDAO;
import es.albarregas.beans.UsuarioBean;
import es.albarregas.models.Utils;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * Controlador que va a gestionar la eliminación de uno o varios usuarios en la
 * bbdd
 *
 * @author alberto
 */
@WebServlet(name = "EliminarController", urlPatterns = {"/EliminarController"})
public class EliminarController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // En caso de entrar por doGet reedirigimos al usuario al index
        request.getRequestDispatcher(".").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recuperamos la sesión
        HttpSession sesion = request.getSession();
        String url = "";
        String mensaje = "";
        String boton = request.getParameter("botonEliminar");
        IUsuariosDAO adao = new UsuariosDAO();

        switch (boton) {
            case "Realizar":
                String[] eliminarUsuario = request.getParameterValues("eliminarUsuario");
                // Comprobamos si el usuario ha seleccionado algun usuario o usuarios para eliminar
                if (eliminarUsuario == null || eliminarUsuario.length == 0) {
                    mensaje = "No se ha elegido ningún usuario.";
                    request.setAttribute("mensaje", mensaje);
                    url = "/JSP/ELIMINAR/eliminarVista.jsp";
                } else {
                    List<UsuarioBean> lista = adao.getTodosUsuarios();
                    //List<UsuarioBean> usuariosBorrar = new ArrayList<>();

                    //List<UsuarioBean> usuariosBorrar = Utils.rellenarListaBorrar2(eliminarUsuario);
                    List<UsuarioBean> usuariosBorrar = Utils.rellenarListaBorrar(eliminarUsuario, lista);

                    //for (UsuarioBean usuario : lista) {
                    //    for (String id : eliminarUsuario) {
                    //        if (usuario.getId() == Short.parseShort(id)) {
                    //            usuariosBorrar.add(usuario);
                    //        }
                    //    }
                    //}
                    request.getSession().setAttribute("listaBorrar", usuariosBorrar);
                    url = "/JSP/CONFIRMACION/confirmarEliminarUsuario.jsp";
                }

                break;
            case "Eliminar":
                
                // Obtenemos el usuario o usuarios que se van a eliminar
                List<UsuarioBean> listaBorrar = (List<UsuarioBean>) request.getSession().getAttribute("listaBorrar");
                mensaje = adao.eliminarUsuarios(listaBorrar);               
                request.setAttribute("mensaje", mensaje);
                url = "JSP/ELIMINAR/finEliminarVista.jsp";
                break;
        }
        // Redirigimos al usuario a la url correspondiente
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}