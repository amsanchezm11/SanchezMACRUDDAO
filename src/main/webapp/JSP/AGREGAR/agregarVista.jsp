<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="/INC/metas.inc"/>
        <title>Agregar Usuario</title>
        <link rel="icon" type="image/png" href="${logoCRUD}">
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
    </head>
    <body class="principal">
        <c:import url="/INC/cabecera.inc"/>

        <form action="${contexto}/AgregarController" method="post" class="formulario-agregar">
            <h1 class="h1-principal">A&ntilde;adir nuevo usuario</h1>
            <div class="container-inputs">
                <div>
                    <label>Nombre:</label>
                    <input type="text" name="nombre" value="${param.nombre}">
                </div>
                <div>
                    <label>Apellidos:</label>
                    <input type="text" name="apellidos" value="${param.apellidos}">
                </div>
                <div>
                    <label>Fecha Nacimiento:</label>
                    <input type="date" name="fechaNac" value="${param.fechaNac}">
                </div>
                <div>
                    <label>G&eacute;nero:</label>
                    <select name="genero">
                        <option value="Mujer" ${param.genero == null || param.genero == "Mujer" ? "selected" : ""}>Mujer</option>
                        <option value="Hombre" ${param.genero == "Hombre" ? "selected" : ""}>Hombre</option>
                        <option value="Otro" ${param.genero == "Otro" ? "selected" : ""}>Otro</option>
                    </select>
                </div>
                <div>
                    <label>Usuario:</label>
                    <input type="text" name="username" value="${param.username}">
                </div>
                <div>
                    <label>Contrase&ntilde;a</label>
                    <input type="password" name="password1" value="${param.password1}">
                </div>
                <div>
                    <label>Repite Contrase&ntilde;a:</label>
                    <input type="password" name="password2" value="${param.password2}">
                </div>
                <div>
                    <p class="aviso">${requestScope.mensaje}</p>
                </div>
            </div>
            <div class="formulario-botones">
                <input type="submit" name="agregarUsuario" value="Nuevo" class="boton">
                <a href="VolverController" class="enlace-acciones">Cancelar</a>
            </div>
        </form>

        <c:import url="/INC/pie.inc"/>
    </body>
</html>
