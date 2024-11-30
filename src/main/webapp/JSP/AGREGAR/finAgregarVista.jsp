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
            <p class="info-p">${requestScope.mensaje}</p>
            <p>Nombre: <b>${requestScope.usuarioAgregado.nombre}</b></p>
            <p>Apellidos: <b>${requestScope.usuarioAgregado.apellidos}</b></p>
            <p>Fecha de nacimiento: <b><fmt:formatDate pattern="dd 'de' MMMMM 'de' yyyy" value="${requestScope.usuarioAgregado.fechaNac}" /></b></p>
            <p>G&eacute;nero: <b>${requestScope.usuarioAgregado.genero}</b></p>
            <p>username: <b>${requestScope.usuarioAgregado.username}</b></p>
            <p>Contrase&ntilde;a: <b>${requestScope.usuarioAgregado.password1}</b></p>        
        </div>
        <div class="formulario-botones">
            <a href="VolverController" class="enlace-acciones inicio-agregar">Inicio</a>
        </div>

        <c:import url="/INC/pie.inc"/>
    </body>
</html>
