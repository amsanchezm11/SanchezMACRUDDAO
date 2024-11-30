package es.albarregas.beans;

import es.albarregas.models.Utils;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class UsuarioBean implements Serializable {

    public enum Genero {
        Hombre, Mujer, Otro
    }

    // Atributos
    private short id;
    private String nombre;
    private String apellidos;
    private Date fechaNac;
    private Genero genero;
    private String username;
    private String password1;

    // Getters y Setters
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    // Getters y Setters(Personalizados)
    
    public String getPassword1Encriptado(){
        return Utils.encriptarPassword(password1);
    }
    
    
    // Getter Genero a String
    public String getGeneroToString() {
             
        switch (genero) {
            case Hombre:
                return "H";                
            case Mujer:
                return "M";
            case Otro:
                return "O";
        }
        return null;
    }

    // Setter String a Genero
    public void setStringToGenero(String texto) {
        switch (texto) {
            case "H":
                this.genero = Genero.Hombre;
                break;
            case "M":
                this.genero = Genero.Mujer;
                break;
            case "O":
                this.genero = Genero.Otro;
                break;
        }
    }

    // HashCode y Equals
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.nombre);
        hash = 41 * hash + Objects.hashCode(this.apellidos);
        hash = 41 * hash + Objects.hashCode(this.fechaNac);
        hash = 41 * hash + Objects.hashCode(this.genero);
        hash = 41 * hash + Objects.hashCode(this.username);
        hash = 41 * hash + Objects.hashCode(this.password1);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioBean other = (UsuarioBean) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellidos, other.apellidos)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password1, other.password1)) {
            return false;
        }
        if (!Objects.equals(this.fechaNac, other.fechaNac)) {
            return false;
        }
        return this.genero == other.genero;
    }

}
