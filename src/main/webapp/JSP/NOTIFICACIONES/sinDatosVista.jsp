<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="/INC/metas.inc"/>
        <title>Sin Datos</title>
        <link rel="icon" type="image/png" href="${logoCRUD}">
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
    </head>
    <body class="principal">
        <c:import url="/INC/cabecera.inc"/>
        <div class="container-notificacion">
            <h2 class="aviso">No existen datos almacenados</h2>
            <a href="VolverController" class="enlace-acciones">Inicio</a>
        </div>        
        <c:import url="/INC/pie.inc"/>
    </body>
</html>
