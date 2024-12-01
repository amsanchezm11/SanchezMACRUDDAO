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
 * Controlador que va a gestionar la modificación de un usuario que está en la
 * bbdd
 *
 * @author alberto
 */
@WebServlet(name = "ModificarController", urlPatterns = {"/ModificarController"})
public class ModificarController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // En caso de entrar por doGet reedirigimos al usuario al index
        request.getRequestDispatcher(".").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*
        * - URL para redireccionar
        * - Mensaje para mostrar
        * - Objeto DAO de dónde vamos a sacar la información de la BBDD
        * - Valor del botón que ha pulsado el usuario
         */
        String url = "";
        String mensaje = "";
        IUsuariosDAO adao = new UsuariosDAO();
        String boton = request.getParameter("botonModificar");

        switch (boton) {
            case "Realizar":
                String[] modificarUsuario = request.getParameterValues("modificarUsuario");
                /* Comprobamos que el parametro que nos entra es nulo o que el array de valores es 0. En caso de que
                   se de una de esas dos opciones significará que el usuario no ha seleccionado ningun usuario */
                if (modificarUsuario == null || modificarUsuario.length == 0) {
                    mensaje = "Por favor, eliga un usuario para modificar.";
                    request.setAttribute("mensaje", mensaje);
                    url = "/JSP/MODIFICAR/modificarVista.jsp";
                } else {
                    int id = Integer.parseInt(modificarUsuario[0]);
                    UsuarioBean usuarioBD = adao.getUsuarioPorId(id);
                    request.getSession().setAttribute("usuarioObtenido", usuarioBD);
                    url = "/JSP/MODIFICAR/modificarUsuarioVista.jsp";
                }
                break;
            case "Actualizar":
                // Verificamos si los campos del formulario están correctos y lo almacenamos en el mensaje
                mensaje = Utils.comprobarCampos(request);
                // Si todo está "OK" en el formulario
                if (mensaje.equals("")) {
                    // Rellenamos un nuevo bean Usuario con los datos del formulario
                    UsuarioBean usuarioModificado = Utils.rellenarUsuario(request);
                    // Encriptamos la contraseña para poder comparar los ususarios
                    usuarioModificado.setPassword1(usuarioModificado.getPassword1Encriptado());
                    // Obtenemos el bean del usuario original desde la sesión
                    UsuarioBean usuarioObtenido = (UsuarioBean) request.getSession().getAttribute("usuarioObtenido");

                    if (usuarioModificado.equals(usuarioObtenido)) {
                        // Si son iguales notificamos al usuario que no hubo cambios en los datos del usuario
                        mensaje = "No se realizaron cambios en la información del usuario";
                        request.setAttribute("mensaje", mensaje);
                        url = "/JSP/MODIFICAR/finModificarVista.jsp";
                    } else {
                        // Si los beans son distintos hacemos la sentencia en la bbdd
                        mensaje = adao.modificarUsuario(usuarioModificado);
                        if (mensaje.equals("")) {
                            mensaje = "Se actualizó la información del usuario " + usuarioObtenido.getNombre() + " " + usuarioObtenido.getApellidos();
                            request.setAttribute("mensaje", mensaje);
                            // Redireccionamos al usuario al final de la vista de modificar
                            url = "/JSP/MODIFICAR/finModificarVista.jsp";
                        } else {
                            url = "JSP/MODIFICAR/modificarUsuarioVista.jsp";
                        }
                    }
                } else {
                    /* En caso de que falte algún campo en el formulario o las contraseñas coincidan almacenamos el error 
                    y los pasamos por petición */
                    request.setAttribute("mensaje", mensaje);
                    url = "/JSP/MODIFICAR/modificarUsuarioVista.jsp";
                }
                request.setAttribute("mensaje", mensaje);
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
