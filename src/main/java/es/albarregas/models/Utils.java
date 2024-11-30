package es.albarregas.models;

import es.albarregas.beans.UsuarioBean;
import es.albarregas.beans.UsuarioBean.Genero;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

/**
 *
 * Modelo que se va a utilizar para todas las funciones que se van a realizar en
 * la aplicación.
 *
 * @author alberto
 */
public class Utils {

    /**
     * Encripta una contraseña utilizando el algoritmo MD5.
     *
     * @param password La contraseña a encriptar.
     * @return La contraseña encriptada en formato hexadecimal.
     * @throws RuntimeException Si el algoritmo MD5 no está disponible.
     */
    public static String encriptarPassword(String password) {
        try {
            // Crear una instancia de MessageDigest para Utils
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Realizar la encriptación de la contraseña en bytes
            byte[] messageDigest = md.digest(password.getBytes());

            // Convertir el array de bytes en una cadena hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                // Convierte cada byte en un valor hexadecimal y lo añade a la cadena
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Devuelve el valor en formato hexadecimal
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejo de excepciones si el algoritmo Utils no está disponible
            throw new RuntimeException("Error en la encriptación MD5", e);
        }
    }

    /**
     * Comprueba los campos que se han introducido en el formulario de agregar
     * usuario. Primero comprueba que ningún campo esté vacío y en caso de que
     * no haya campos vacíos comprueba que las contraselas coincidan.
     *
     * @param request Contiene los datos del formulario enviados por el usuario.
     * @return El mensaje de error que va a notificar al usuario si algún campo
     * está vacío o si las contraseñas no coinciden.
     */
    public static String comprobarCampos(HttpServletRequest request) {

        String mensaje = "";

        if (request.getParameter("nombre").isEmpty()
                || request.getParameter("apellidos").isEmpty()
                || request.getParameter("fechaNac").isEmpty()
                || request.getParameter("genero").isEmpty()
                || request.getParameter("username").isEmpty()
                || request.getParameter("password1").isEmpty()
                || request.getParameter("password2").isEmpty()) {
            mensaje = "Por favor, complete los campos vac&iacute;os";
        } else {
            if (!request.getParameter("password2").equals(request.getParameter("password1"))) {
                mensaje = "Las contraseñas no coinciden";
            }
        }

        return mensaje;
    }

    /**
     * Método que rellena un objeto UsuarioBean utilizando los parámetros de una
     * solicitud HTTP. Los valores de los parámetros se asignan automáticamente
     * a las propiedades del objeto, incluyendo la conversión de fechas y
     * enums.
     * 
     * @param request la solicitud HTTP que contiene los parámetros para poblar
     * el UsuarioBean.
     * @return un objeto UsuarioBean con los valores de los parámetros del
     * request, o null si ocurre un error durante la población del bean.
     *
     * @throws NullPointerException si request es null.
     *
     */
    public static UsuarioBean rellenarUsuario(HttpServletRequest request) {

        UsuarioBean usuario = new UsuarioBean();
        // Convertimos la fecha y los enums
        DateConverter converter = new DateConverter();
        converter.setPattern("yyyy-MM-dd");
        ConvertUtils.register(converter, Date.class);
        ConvertUtils.register(new EnumConverter(), Genero.class);

        try {
            BeanUtils.populate(usuario, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return usuario;
    }

}
