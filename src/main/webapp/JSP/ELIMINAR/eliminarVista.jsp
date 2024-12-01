<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    <c:import url="/INC/metas.inc"/>
    <title>Eliminar Usuarios</title>
    <link rel="icon" type="image/png" href="${logoCRUD}">
    <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
</head>
<body class="principal">
    <c:import url="/INC/cabecera.inc"/>
    <h1 class="visualizar-h1">Elige uno o m&aacute;s usuarios para eliminar</h1>

        <form action="${contexto}/EliminarController" method="post" class="formulario-modificar">
            <table class="tabla-visualizar">
                <thead>
                <th>Elige</th>
                <th>Apellidos, nombre</th>           
                </thead>
                <c:forEach var="usuario" items="${sessionScope.usuarios}">
                    <c:set var="fechaNac" value="${usuario.fechaNac}" />
                    <tr>
                        <td><input type="checkbox" name="eliminarUsuario" value="${usuario.id}"> </td>        
                        <td>${usuario.apellidos}, ${usuario.nombre}</td>           
                    </tr>
                </c:forEach>
            </table>
            <div>
                <p class="aviso">${requestScope.mensaje}</p>
            </div>

            <div class="modificar-botones">
                <input type="submit" name="botonEliminar" value="Realizar" class="boton">
                <a href="VolverController" class="enlace-acciones">Cancelar</a>
            </div>
        </form>
    
    <c:import url="/INC/pie.inc"/>
</body>
</html>
