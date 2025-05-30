<%@ page import="java.util.List" %>
<%@ page import="clasesGenerales.PokemonCatalogo" %>

<link rel="stylesheet" href="./estatico/styles/index.css">

<section class="chooseCards">
<%
    List<PokemonCatalogo> catalogo = (List<PokemonCatalogo>) request.getAttribute("catalogo");
    
    if (catalogo != null && !catalogo.isEmpty()) {
%>
    <h1 class="logo">PokeTemu</h1>
    <!-- Formulario para selección y batalla -->
    <form class="chooseCards-form" action="Batalla" method="post" id="formCombate">
        <div class="cards">
<%
        for (PokemonCatalogo poke : catalogo) {
            String nombreClase = poke.getNombre().toLowerCase();
%>
            <div class="chooseCards">
                <div class="card-Data">
                    <img class="imgPokemon" src="./estatico/resources/images/<%= nombreClase %>.png" alt="<%= nombreClase %>">
                    <p class="card-Data-PokeName"><%= poke.getNombre() %></p>
                    <p class="card-Data-Level">ID: <%= poke.getIdCatalogo() %></p>

                    <!-- Todos los pokémon se pueden seleccionar -->
                    <input type="radio" name="idPokemonSeleccionado" value="<%= poke.getIdCatalogo() %>" required>
                </div>
            </div>
<%
        }
%>
        </div>

        <!-- Botón para iniciar juego -->
        <div style="text-align: center; margin-top: 20px;">
            <button type="submit">Iniciar Juego</button>
        </div>
    </form>
<%
    } else {
%>
    <p>Error al cargar el catálogo.</p>
    <p><%=catalogo%></p>
<%
    }
%>
</section>
