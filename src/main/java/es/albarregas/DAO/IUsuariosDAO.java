package es.albarregas.DAO;

import es.albarregas.beans.UsuarioBean;
import java.util.List;

/**
 *
 * @author alberto
 */
public interface IUsuariosDAO {
  
    /**
     * Método para obtener todos los usuarios de la bbdd con todos sus atributos
     *
     * @return Lista de usuarios con id, nombre y apellidos
     */
    public List<UsuarioBean> getTodosUsuarios();
    
    /**
     * Método para obtener el id,nombre y apellidos de los usuarios
     *
     * @return Lista de usuarios con id, nombre y apellidos
     */
    public List<UsuarioBean> getUsuariosNombreApellidos();

     /**
     * Método para agregar un usuario en la Base de Datos
     *
     * @param usuario UsuarioBean dónde se van a extraer los datos que se han introducido en el formulario
     * de agregar usuario
     * @return Devuelve un String que servirá para informar si el username ya existe o no en Base de Datos o si ha ocurrido
     * algún problema en la Base de Datos
     */
    public String agregarUsuario(UsuarioBean usuario);
    
    /**
     * Método para modificar un usuario en la Base de Datos
     *
     * @param usuario UsuarioBean dónde se van a extraer los nuevos datos que se han introducido en el formulario
     * de modificar usuario y se usarán para modificar los datos del usuario en la Base de Datos
     * @return Devuelve un String que servirá para informar si el username ya existe o no en Base de Datos o si ha ocurrido
     * algún problema en la Base de Datos
     */
    public String modificarUsuario(UsuarioBean usuario);
    
    /**
     * Método para cerrar la conexión
     */
    public void closeConnection();
}
