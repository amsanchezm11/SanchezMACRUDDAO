<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="/INC/metas.inc"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error 404</title>
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
    </head>
    <body class="body-error-404">
        <c:import url="/INC/cabecera.inc"/>
        <a href="VolverController" class="enlace-error">Men&uacute; Principal</a>
        <c:import url="/INC/pie.inc"/>
    </body>
</html>
