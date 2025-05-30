package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import dao.PokemonDAO;
import clasesAbstractas.pokemon;
import java.util.List;
import clasesGenerales.PokemonCatalogo;

@WebServlet(name = "Inicio", urlPatterns = {"/Index"})
public class Inicio extends HttpServlet {
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Suponiendo que tienes el ID del usuario (ejemplo: hardcodeado o desde sesión)
        
        String dbPath = getServletContext().getRealPath("/database/pokemones.db");
        PokemonDAO dao = new PokemonDAO(dbPath);
        List<PokemonCatalogo> catalogo = dao.obtenerCatalogo();

        request.setAttribute("catalogo", catalogo);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // Redirige a GET para refrescar
        response.sendRedirect("Index");
    }
}
