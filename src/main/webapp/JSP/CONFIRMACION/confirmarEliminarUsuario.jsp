<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="/INC/metas.inc"/>
        <title>Confirmar Eliminar</title>
        <link rel="icon" type="image/png" href="${logoCRUD}">
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
    </head>
    <body class="principal">
        <c:import url="/INC/cabecera.inc"/>
        <h1 class="visualizar-h1">Usuarios que se van a eliminar</h1>

        <form action="EliminarController" method="post" class="formulario-modificar">
            <table class="tabla-visualizar">
                <thead>
                <th>Id</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Fecha de Nacimiento</th>
                <th>Género</th>
                <th>Usuario</th>
                </thead>
                <c:forEach var="usuarioPrincipal" items="${sessionScope.usuarios}">
                    <c:forEach var="usuarioBorrar" items="${sessionScope.listaBorrar}">
                        <c:if test="${usuarioPrincipal.id eq usuarioBorrar.id}">
                            <tr>
                                <td>${usuarioBorrar.id}</td>
                                <td>${usuarioBorrar.nombre}</td>
                                <td>${usuarioBorrar.apellidos}</td>
                                <td>
                                    <fmt:formatDate pattern="dd 'de' MMMMM 'de' yyyy" value="${usuarioBorrar.fechaNac}" />
                                </td>
                                <td>${usuarioBorrar.genero}</td>
                                <td>${usuarioBorrar.username}</td>
                            </tr>
                            <input type="hidden" name="modificarUsuario" value="${usuarioBorrar.id}" />
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </table>

            <div class="formulario-botones modificar-botones">
                <input type="submit" name="botonEliminar" value="Eliminar" class="boton">
                <a href="VolverController" class="enlace-acciones">Cancelar</a>
            </div>
        </form>

        <c:import url="/INC/pie.inc"/>
    </body>
</html>
