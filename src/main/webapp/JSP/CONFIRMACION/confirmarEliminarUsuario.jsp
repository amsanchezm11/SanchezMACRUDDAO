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
                <c:if test="${requestScope.listaBorrar != null}">
                    <c:set var="usuarioIds" value="${requestScope.listaBorrar}" />
                    <c:forEach var="usuarioId" items="${usuarioIds}">
                        <c:forEach var="usuario" items="${sessionScope.usuarios}">
                            <!-- Compara el id del usuario con el id seleccionado -->
                            <c:if test="${usuario.id == usuarioId}">
                                <tr>
                                    <td>${usuario.id}</td>        
                                    <td>${usuario.nombre}</td>
                                    <td>${usuario.apellidos}</td>
                                    <td><fmt:formatDate pattern="dd 'de' MMMMM 'de' yyyy" value="${usuario.fechaNac}" /></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${usuario.genero eq 'M'}">
                                                <c:out value="Mujer"/>
                                            </c:when>
                                            <c:when test="${usuario.genero eq 'H'}">
                                                <c:out value="Hombre"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="Otro"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${usuario.username}</td>
                                </tr>
                                <!-- Input de tipo hidden para enviar el ID al servlet -->
                                <input type="hidden" name="modificarUsuario" value="${usuario.id}" />
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </c:if>

            </table>

            <div class="formulario-botones modificar-botones">
                <input type="submit" name="botonEliminar" value="Eliminar" class="boton">
                <a href="VolverController" class="enlace-acciones">Cancelar</a>
            </div>
        </form>




        <c:import url="/INC/pie.inc"/>
    </body>
</html>
