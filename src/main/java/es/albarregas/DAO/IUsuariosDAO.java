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
     * Método para obtener el id,nombre y apellidos de los usuarios
     *
     * @param id Id del usuario que se va a modificar en la Base de Datos
     * @return Usuario de la Base de Datos con el id indicado en el parámetro
     */
    public UsuarioBean getUsuarioPorId(int id);
    
    /**
     * Método para agregar un usuario en la Base de Datos
     *
     * @param usuario UsuarioBean dónde se van a extraer los datos que se han
     * introducido en el formulario de agregar usuario
     * @return Devuelve un String que servirá para informar si el username ya
     * existe o no en Base de Datos o si ha ocurrido algún problema en la Base
     * de Datos
     */
    public String agregarUsuario(UsuarioBean usuario);

    /**
     * Método para modificar un usuario en la Base de Datos
     *
     * @param usuario UsuarioBean dónde se van a extraer los nuevos datos que se
     * han introducido en el formulario de modificar usuario y se usarán para
     * modificar los datos del usuario en la Base de Datos
     * @return Devuelve un String que servirá para informar si el username ya
     * existe o no en Base de Datos o si ha ocurrido algún problema en la Base
     * de Datos
     */
    public String modificarUsuario(UsuarioBean usuario);

    /**
     * Método para eliminar uno o varios usuarios de la Base de Datos
     *
     * @param lista Lista de usuarios que se van a eliminar
     * @return Devuelve un String que servirá para informar si ha ocurrido algún
     * problema en la Base de Datos
     */
    public String eliminarUsuarios(List<UsuarioBean>lista);

    /**
     * Método para cerrar la conexión
     */
    public void closeConnection();
}
