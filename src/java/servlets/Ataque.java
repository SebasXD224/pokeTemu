/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import clasesAbstractas.pokemon;
import javax.servlet.http.HttpSession;

/**
 *
 * @author usuario
 */
@WebServlet("/Ataque")
public class Ataque extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Recuperar los Pokémon del jugador y del rival
        pokemon miPokemon = (pokemon)session.getAttribute("miPokemon");
        pokemon rival = (pokemon)session.getAttribute("rival");

        // Turno (true = jugador, false = rival)
        Boolean turnoJugador = (Boolean) session.getAttribute("turnoJugador");
        if (turnoJugador == null) turnoJugador = true; // primer turno

        String mensaje = "";
        if (turnoJugador) {
            // Es turno del jugador
            double daño = miPokemon.atacar(rival);

            if (miPokemon.getAccion().equals("AtaqueNormal")){
                mensaje = miPokemon.getNombre() + " ha atacado a " + rival.getNombre() + " e hizo " + daño + " de daño.";
            }
            if (miPokemon.getAccion().equals("AtaqueCritico")){
                mensaje = miPokemon.getNombre() + " ha hecho un Ataque Crítico a " + rival.getNombre() + " causando " + daño + " de daño.";
            }
            if (miPokemon.getAccion().equals("Defensa")){
                mensaje = miPokemon.getNombre() + " atacó a " + rival.getNombre() + ", pero este se defendió y no recibió daño.";
            }

            if (rival.getVida() <= 0) {
                mensaje += rival.getNombre() + " ha sido derrotado.";
                session.removeAttribute("turnoJugador");
                session.setAttribute("mensaje", mensaje);
                session.setAttribute("ganador", miPokemon); // NUEVO
                response.sendRedirect("final.jsp"); // REDIRECCIÓN A OTRA PÁGINA
                
                miPokemon.subirNivel(1);
                return;
            }

            if (miPokemon.getVida() <= 0) {
                mensaje += miPokemon.getNombre() + " ha sido derrotado.";
                session.removeAttribute("turnoJugador");
                session.setAttribute("mensaje", mensaje);
                session.setAttribute("ganador", rival); // NUEVO
                response.sendRedirect("final.jsp"); // REDIRECCIÓN A OTRA PÁGINA
                
                rival.subirNivel(1);
                return;
            }

            turnoJugador = false; // Cambia turno
        } else {
            // Turno del rival
            double daño = rival.atacar(miPokemon);

            if (rival.getAccion().equals("AtaqueNormal")){
                mensaje = rival.getNombre() + " ha atacado a " + miPokemon.getNombre() + " e hizo " + daño + " de daño.";
            }
            if (rival.getAccion().equals("AtaqueCritico")){
                mensaje = rival.getNombre() + " ha hecho un Ataque Crítico a " + miPokemon.getNombre() + " causando " + daño + " de daño.";
            }
            if (rival.getAccion().equals("Defensa")){
                mensaje = rival.getNombre() + " atacó a " + miPokemon.getNombre() + ", pero este se defendió y no recibió daño.";
            }
            
            if (miPokemon.getVida() <= 0) {
                mensaje += miPokemon.getNombre() + " ha sido derrotado.";
                // Terminar batalla
                session.removeAttribute("turnoJugador");
                session.setAttribute("mensaje", mensaje);
                request.getRequestDispatcher("/batalla.jsp").forward(request, response);
                return;
            }

            turnoJugador = true; // Vuelve al jugador
        }

        // Guardar todo para el JSP
        session.setAttribute("miPokemon", miPokemon);
        session.setAttribute("rival", rival);
        session.setAttribute("turnoJugador", turnoJugador);
        session.setAttribute("mensaje", mensaje);

        request.getRequestDispatcher("/batalla.jsp").forward(request, response);
    }
}

