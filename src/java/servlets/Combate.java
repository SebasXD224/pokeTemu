package servlets;

import clasesAbstractas.pokemon;
import clasesGenerales.PokemonCatalogo;
import clasesFactory.PokemonBatallaFactory;
import dao.PokemonDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@WebServlet(name = "Batalla", urlPatterns = {"/Batalla"})
public class Combate extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Obtener el nombre del Pokémon seleccionado por el jugador desde el formulario
        String idCatalogoStr = request.getParameter("idPokemonSeleccionado");
        int idCatalogo = Integer.parseInt(idCatalogoStr);
        
        System.out.println(idCatalogo);
        
        String dbPath = getServletContext().getRealPath("/database/pokemones.db");
        int idUsuario = 1; // Reemplaza con el ID de sesión si ya lo manejas

        PokemonDAO dao = new PokemonDAO(dbPath);
        
        // 2. Obtener los datos del Pokémon del jugador desde la base de datos
        System.out.println(idCatalogo);
        pokemon miPokemon = dao.obtenerPokemonJugadorPorIdCatalogo(idUsuario, idCatalogo);

        // 3. Obtener nombre aleatorio del catálogo para el rival
        String nombreRival = dao.obtenerNombreAleatorioCatalogo();
        
        // 4. Crear instancia del Pokémon de la PC (nivel 1, sin evolución)
        pokemon rival = PokemonBatallaFactory.crearPokemon(nombreRival, 1, false);
        
        // 5. Enviar ambos pokemones a la vista de batalla.jsp
        HttpSession session = request.getSession();
        session.setAttribute("miPokemon", miPokemon);
        session.setAttribute("rival", rival);

        request.getRequestDispatcher("batalla.jsp").forward(request, response);
    }
}
