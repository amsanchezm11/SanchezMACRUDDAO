package es.albarregas.DAO;

import es.albarregas.beans.UsuarioBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alberto
 */
public class UsuariosDAO implements IUsuariosDAO {

    @Override
    public List<UsuarioBean> getTodosUsuarios() {

        List<UsuarioBean> lista = null;
        UsuarioBean user = null;
        String consulta = "SELECT * FROM usuarios";

        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);

            lista = new ArrayList<>();
            while (resultado.next()) {

                user = new UsuarioBean();
                user.setId(resultado.getShort("id"));
                user.setNombre(resultado.getString("nombre"));
                user.setApellidos(resultado.getString("apellidos"));
                user.setFechaNac(resultado.getDate("fechaNacimiento"));
                user.setStringToGenero(resultado.getString("genero"));
                user.setUsername(resultado.getString("username"));
                user.setPassword1(resultado.getString("password"));
                lista.add(user);

            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1146) {
                // Capturamos el error 1146 y lo imprimimos por consola             
                System.out.print("La tabla no existe");
            } else {
                // Capturamos cualquiera del resto de errores que puedan ocurrir y lo imprimimos por consola 
                System.out.print("Ha ocurrido un error al acceder a la tabla");
            }
        } finally {
            // Cerramos conexión
            this.closeConnection();
        }
        return lista;
    }

    @Override
    public List<UsuarioBean> getUsuariosNombreApellidos() {
        List<UsuarioBean> lista = null;
        UsuarioBean user = null;
        String consulta = "SELECT id,nombre,apellidos FROM usuarios";

        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);
            lista = new ArrayList<>();

            while (resultado.next()) {

                user = new UsuarioBean();
                user.setId(resultado.getShort("id"));
                user.setNombre(resultado.getString("nombre"));
                user.setApellidos(resultado.getString("apellidos"));
                lista.add(user);

            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1146) {
                // Capturamos el error 1146 y lo imprimimos por consola             
                System.out.print("La tabla no existe");
            } else {
                // Capturamos cualquiera del resto de errores que puedan ocurrir y lo imprimimos por consola 
                System.out.print("Ha ocurrido un error al acceder a la tabla");
            }
        } finally {
            // Cerramos conexión
            this.closeConnection();
        }

        return lista;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

    @Override
    public String agregarUsuario(UsuarioBean usuario) {

        String mensaje = "";

        try {
            PreparedStatement preparada = null;
            // Preparamos una consulta para realizar el insert correspondiente en la bbdd
            String consulta = "INSERT INTO usuarios (nombre, apellidos, fechaNacimiento, genero, username, password) VALUES (?, ?, ?, ?, ?, md5(?))";
            preparada = ConnectionFactory.getConnection().prepareStatement(consulta);

            preparada.setString(1, usuario.getNombre());
            preparada.setString(2, usuario.getApellidos());
            // Convertimos Date a SQL Date
            preparada.setDate(3, new java.sql.Date(usuario.getFechaNac().getTime()));
            preparada.setString(4, usuario.getGeneroToString());
            preparada.setString(5, usuario.getUsername());
            // La contraseña se inserta ya cifrada a través de la consulta SQL
            preparada.setString(6, usuario.getPassword1());

            // Ejecutamos el insert
            preparada.executeUpdate();
            /* Personalizamos el mensaje y lo retornamos(A modo de confirmar que todo ha ido bien
                               a la hora de insertar los datos en la bbdd)                              
             */
            return mensaje = "Los datos introducidos en la base de datos son:";
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1146:
                    // Capturamos el error 1146 y lo almacenamos en el mensaje
                    return mensaje = "Error. La tabla no existe";
                case 1062:
                    // Capturamos el error 1062 y lo almacenamos en mensaje
                    mensaje = "Error. Ya existe un usuario con el username " + usuario.getUsername();
                    break;
                default:
                    return mensaje = "Error. Ha ocurrido un error al insertar los datos";
            }
        } finally {
            // Cerramos conexión a la Base de Datos
            this.closeConnection();
        }
        return mensaje;
    }

    @Override
    public String modificarUsuario(UsuarioBean usuarioModificado) {

        String mensaje = "";
        PreparedStatement preparada = null;
        try {

            String consulta = "UPDATE usuarios SET nombre = ?, apellidos = ?, fechaNacimiento = ?, genero = ?, username = ?, password = ? WHERE id = ?";
            // Preparamos la sentencia con los datos del usuario modificado
            preparada = ConnectionFactory.getConnection().prepareStatement(consulta);

            preparada.setString(1, usuarioModificado.getNombre());
            preparada.setString(2, usuarioModificado.getApellidos());
            preparada.setDate(3, new java.sql.Date(usuarioModificado.getFechaNac().getTime()));
            preparada.setString(4, usuarioModificado.getGeneroToString());
            preparada.setString(5, usuarioModificado.getUsername());
            preparada.setString(6, usuarioModificado.getPassword1());
            preparada.setShort(7, usuarioModificado.getId());
            // Ejecutamos la actualización y obtenemos las filas afectadas
            int filasActualizadas = preparada.executeUpdate();
            // Si hubo cambios, notificamos al usuario que el usuario que ha elegido se ha modificado
            if (filasActualizadas > 0) {
                // El mensaje irá vacío ya que en el Controlador se modificará el mensaje con el nombre anterior del usuario
                return mensaje = "";
            } else {
                return mensaje = "No se pudo actualizar el usuario. Intente de nuevo.";
            }
        } catch (SQLException e) {
            // Capturamos el error de que el username que ha elegido ya existe en la bbdd
            if (e.getErrorCode() == 1062) {
                return mensaje = "Ya existe un usuario con el username " + usuarioModificado.getUsername();
            }
        } finally {
            // Cerramos conexión a la Base de Datos
            this.closeConnection();
        }
        return mensaje;
    }

    @Override
    public String eliminarUsuarios(List<UsuarioBean> lista) {
        String mensaje = "";
        PreparedStatement preparada = null;
        int resultados = 0;
        try {
            String sentencia = "DELETE FROM usuarios WHERE id = ?";

            for (UsuarioBean usuario : lista) {
                preparada = ConnectionFactory.getConnection().prepareStatement(sentencia);
                preparada.setInt(1, usuario.getId());
                resultados += preparada.executeUpdate();
            }
            // Modificamos el mensaje para notificar el nº de registros que se han eliminado
            mensaje = "Se han eliminado " + resultados + (resultados == 1 ? " registro" : " registros");

        } catch (SQLException ex) {
            mensaje = "Error. Ha ocurrido un problema en la Base de Datos";
        }

        return mensaje;
    }

    @Override
    public UsuarioBean getUsuarioPorId(int id) {
        
        UsuarioBean user = null;
        String consulta = "SELECT * FROM usuarios WHERE id = ?";
          try {
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(consulta);
            preparada.setInt(1, id);
            ResultSet resultado = preparada.executeQuery();         

            if (resultado.next()) {

                user = new UsuarioBean();
                user.setId(resultado.getShort("id"));
                user.setNombre(resultado.getString("nombre"));
                user.setApellidos(resultado.getString("apellidos"));
                user.setFechaNac(resultado.getDate("fechaNacimiento"));
                user.setStringToGenero(resultado.getString("genero"));
                user.setUsername(resultado.getString("username"));
                user.setPassword1(resultado.getString("password"));
                
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1146) {
                // Capturamos el error 1146 y lo imprimimos por consola             
                System.out.print("La tabla no existe");
            } else {
                // Capturamos cualquiera del resto de errores que puedan ocurrir y lo imprimimos por consola 
                System.out.print("Ha ocurrido un error al acceder a la tabla");
            }
        } finally {
            // Cerramos conexión
            this.closeConnection();
        }
          return user;
    }

}
