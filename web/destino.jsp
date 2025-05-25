<%-- 
    Document   : destino
    Created on : 25 may. 2025, 11:15:30
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            String texto = request.getParameter("texto");
        %>
        <p><%= texto%></p>
    </body>
</html>
