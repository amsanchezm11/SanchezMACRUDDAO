<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="/INC/metas.inc"/>
        <title>Usuario Creado</title>
        <link rel="icon" type="image/png" href="${logoCRUD}">
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
    </head>
    <body class="principal">
        <c:import url="/INC/cabecera.inc"/>

        <div class="informacion-usuario">
            <h1>Informaci&oacute;n de un nuevo usuario</h1>
            <p class="info-p">${requestScope.mensajeMYSQL}</p>
            <p>Nombre: <b>${requestScope.usuarioAgregado.nombre}</b></p>
            <p>Apellidos: <b>${requestScope.usuarioAgregado.apellidos}</b></p>
            <p>Fecha de nacimiento: <b><fmt:formatDate pattern="dd 'de' MMMMM 'de' yyyy" value="${requestScope.usuarioAgregado.fechaNac}" /></b></p>
            <p>G&eacute;nero: 
                <c:set var="genero" value="${requestScope.usuarioAgregado.genero}" />
                <c:choose>
                    <c:when test="${genero eq 'M'}">
                        <b><c:out value="Mujer"/></b>
                    </c:when>
                    <c:when test="${genero eq 'H'}">
                        <b><c:out value="Hombre"/></b>
                    </c:when>
                    <c:otherwise>
                        <b><c:out value="Otro"/></b>
                    </c:otherwise>
                </c:choose>
            </p>
            <p>username: <b>${requestScope.usuarioAgregado.username}</b></p>
            <p>Contrase&ntilde;a: <b>${requestScope.password}</b></p>
          
        </div>
        <div class="formulario-botones">
            <a href="VolverController" class="enlace-acciones inicio-agregar">Inicio</a>
        </div>

        <c:import url="/INC/pie.inc"/>
    </body>
</html>
