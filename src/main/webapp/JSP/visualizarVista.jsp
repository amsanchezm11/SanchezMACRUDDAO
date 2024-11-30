<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="/INC/metas.inc"/>
        <title>Visualizar Usuarios</title>
        <link rel="icon" type="image/png" href="${logoCRUD}">
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
    </head>
    <body class="principal">
        <c:import url="/INC/cabecera.inc"/>
        <h1 class="visualizar-h1">Listado de todos los Usuarios</h1>

        <table class="tabla-visualizar">
            <thead>
            <th>Id</th>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Fecha de Nacimiento</th>
            <th>Género</th>
            <th>Usuario</th>
        </thead>
        <c:forEach var="usuario" items="${sessionScope.usuarios}">
            <tr>
                <td>${usuario.id}</td>        
                <td>${usuario.nombre}</td>
                <td>${usuario.apellidos}</td>
                <td><fmt:formatDate pattern="dd 'de' MMMMM 'de' yyyy" value="${usuario.fechaNac}" /></td>
                <td>
                     <c:set var="genero" value="${usuario.genero}" />
                <c:choose>
                    <c:when test="${genero eq 'M'}">
                        <c:out value="Mujer"/>
                    </c:when>
                    <c:when test="${genero eq 'H'}">
                        <c:out value="Hombre"/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="Otro"/>
                    </c:otherwise>
                </c:choose>
                </td>
                <td>${usuario.username}</td>
            </tr>
        </c:forEach>
    </table>    
    <a href="VolverController" class="enlace-acciones">Inicio</a>
    <c:import url="/INC/pie.inc"/>
</body>
</html>
