<%@ page import="clasesAbstractas.pokemon" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pokemon miPokemon = (pokemon) session.getAttribute("miPokemon");
    pokemon rival = (pokemon) session.getAttribute("rival");
    Boolean turnoJugador = (Boolean) session.getAttribute("turnoJugador");
    String mensaje = (String) session.getAttribute("mensaje");
    if (turnoJugador == null) turnoJugador = true;
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Batalla Pokémon</title>
    
    <link rel="stylesheet" href="./estatico/styles/batalla.css">
</head>
<body>
    <h1>¡Batalla Pokémon!</h1>

    <div class="contenedor">
        <div class="pokemon-box">
            <h2>Tu Pokémon: <%= miPokemon.getNombre() %></h2>
            <img class="imgPokemon" src="./estatico/resources/images/<%= miPokemon.getNombre().toLowerCase() %>.png" alt="<%= miPokemon.getNombre().toLowerCase() %>">
            <p>Vida: <%= miPokemon.getVida() %></p>
            <p>Nivel: <%= miPokemon.getNivel() %></p>
            <p>Ataque: <%= miPokemon.getAtaque() %></p>
            <p>Defensa: <%= miPokemon.getResistencia() %></p>
            <p>Evolucionado: <%= miPokemon.isEvolucion() ? "Sí" : "No" %></p>
        </div>

        <div class="pokemon-box">
            <h2>Rival: <%= rival.getNombre() %></h2>
            <img class="imgPokemon" src="./estatico/resources/images/<%= rival.getNombre().toLowerCase() %>.png" alt="<%= rival.getNombre().toLowerCase() %>">
            <p>Vida: <%= rival.getVida() %></p>
            <p>Nivel: <%= rival.getNivel() %></p>
            <p>Ataque: <%= rival.getAtaque() %></p>
            <p>Defensa: <%= rival.getResistencia() %></p>
            <p>Evolucionado: <%= rival.isEvolucion() ? "Sí" : "No" %></p>
        </div>
    </div>

    <%-- Botón de atacar solo si es tu turno --%>
    <form action="Ataque" method="post">
        <% if (turnoJugador) { %>
            <input type="submit" value="" class="btn-atacar" />
        <% } else { %>
            <p>Esperando al rival...</p>
        <% } %>
    </form>

    <div class="log">
        <p><%= mensaje != null ? mensaje : "La batalla está por comenzar..." %></p>
    </div>
    
    <% if (!turnoJugador) { %>
    <script>
        // Espera 2 segundos y recarga automáticamente
        setTimeout(() => {
            location.reload();
        }, 2000);
    </script>
    <% } %>
</body>
</html>
