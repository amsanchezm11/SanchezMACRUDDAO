<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="/INC/metas.inc"/>
        <title>Modificar Usuario</title>
        <link rel="icon" type="image/png" href="${logoCRUD}">
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
    </head>
    <body class="principal">
        <c:import url="/INC/cabecera.inc"/>

        <form action="${contexto}/ModificarController" method="post" class="formulario-agregar">
            <h1 class="h1-principal">Actualizar la informaci&oacute;n de un usuario</h1>
            <div class="container-inputs">
                <input type="text" name="id" value="${sessionScope.usuarioObtenido.id}" hidden>
                <div>
                    <label>Nombre:</label>
                    <input type="text" name="nombre" value="${sessionScope.usuarioObtenido.nombre}">
                </div>
                <div>
                    <label>Apellidos:</label>
                    <input type="text" name="apellidos" value="${sessionScope.usuarioObtenido.apellidos}">
                </div>
                <div>
                    <label>Fecha Nacimiento:</label>                  
                    <input type="date" name="fechaNac" value="${sessionScope.usuarioObtenido.fechaNac}">
                </div>
                <div>
                    <label>G&eacute;nero:</label>
                    <select name="genero">
                        <option value="M" ${sessionScope.usuarioObtenido.genero == "M" ? "selected" : ""}>Mujer</option>
                        <option value="H" ${sessionScope.usuarioObtenido.genero == "H" ? "selected" : ""}>Hombre</option>
                        <option value="O" ${sessionScope.usuarioObtenido.genero == "O" ? "selected" : ""}>Otro</option>
                    </select>
                </div>
                <div>
                    <label>Usuario:</label>
                    <input type="text" name="username" value="${sessionScope.usuarioObtenido.username}">
                </div>
                <div>
                    <label>Contrase&ntilde;a</label>
                    <input type="password" name="password1" value="">
                </div>
                <div>
                    <label>Repite Contrase&ntilde;a:</label>
                    <input type="password" name="password2" value="">
                </div>
                <div>
                    <p class="aviso">${requestScope.mensaje}</p>
                </div>
            </div>
            <div class="formulario-botones">
                <input type="submit" name="botonModificar" value="Actualizar" class="boton">
                <a href="VolverController" class="enlace-acciones">Cancelar</a>
            </div>
        </form>

        <c:import url="/INC/pie.inc"/>
    </body>
</html>
