<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="/INC/metas.inc"/>
        <title>Usuario Modificado</title>
        <link rel="icon" type="image/png" href="${logoCRUD}">
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
    </head>
    <body class="principal">
        <c:import url="/INC/cabecera.inc"/>               
        <div class="container-notificacion">
            <h1>Informaci&oacute;n de la actualizaci&oacute;n del usuario</h1>
            <c:set var="mensaje" value="${requestScope.mensaje}" />
            <c:choose>
                <c:when test="${fn:startsWith(mensaje, 'Se')}">
                    <h2 class="confirmacion">${requestScope.mensaje}</h2>
                </c:when>
                <c:otherwise>
                    <h2 class="aviso">${requestScope.mensaje}</h2>
                </c:otherwise>
            </c:choose>
            <div class="boton-modificar">
                <a href="VolverController" class="enlace-acciones">Inicio</a>
            </div>
        </div>  
        <c:import url="/INC/pie.inc"/>
    </body>
</html>
