<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="clasesAbstractas.pokemon" %>
<%
    String mensajeFinal = (String) session.getAttribute("mensaje");
    String ganador = (String) session.getAttribute("ganador");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fin de la Batalla</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 100px; }
        .mensaje-final { font-size: 24px; color: #fff; margin-bottom: 20px; }
        .ganador { font-size: 30px; font-weight: bold; color: #28a745; }
        .boton { padding: 10px 20px; font-size: 16px; text-decoration: none; background-color: #007bff; color: white; border-radius: 5px; }
        .boton:hover { background-color: #0056b3; }
    </style>
    <link rel="stylesheet" href="./estatico/styles/final.css">
</head>
<body>

    <div class="mensaje-final"><%= mensajeFinal != null ? mensajeFinal : "La batalla ha terminado." %></div>
    <div class="ganador">🏆 Ganador: <%= ganador != null ? ganador : "Indeterminado" %> 🏆</div>

    <br><br>
    <a href="Index" class="boton">Volver al inicio</a>

</body>
</html>
