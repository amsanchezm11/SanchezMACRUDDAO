<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="estilo" value="/CSS/style.css" scope="application" />
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application" />
<c:url var="logoCRUD" value="/IMG/CRUD1.png" scope="application" />
<!DOCTYPE html>
<html>
    <head>
        <c:import url="/INC/metas.inc"/>
        <title>Men&uacute; Principal</title>
        <link rel="icon" type="image/png" href="${logoCRUD}">
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
    </head>
    <body class="principal">
        <c:import url="/INC/cabecera.inc"/>
        <h1 class="h1-principal">Operaciones b&aacute;sicas sobre una tabla de la base de datos</h1> 
        <form action="${contexto}/FrontController" method="post" class="formulario-index">
            
            <div class="container-botones">
                <button type="submit" name="boton-principal" value="Agregar" class="boton-principal">(C) A&ntilde;adir un nuevo usuario</button>
                <button type="submit" name="boton-principal" value="Visualizar" class="boton-principal">(R) Visualizar todos los usuarios</button>
                <button type="submit" name="boton-principal" value="Modificar" class="boton-principal">(U) Modificar la informaci&oacute;n de un usuario</button>
                <button type="submit" name="boton-principal" value="Eliminar" class="boton-principal">(D) Eliminar uno o varios usuarios</button>
            </div>
            <div>
                <p class="aviso">${requestScope.mensaje}</p>
            </div>
        </form>
        <c:import url="/INC/pie.inc"/>
    </body>
</html>
