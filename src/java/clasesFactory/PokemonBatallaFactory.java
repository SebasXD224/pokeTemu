package clasesFactory;

import clasesAbstractas.pokemon;
import clasesGenerales.*;
import database.dbConection;
import java.sql.Connection;

public class PokemonBatallaFactory {

    public static pokemon crearPokemon(String nombre, int nivel, boolean evolucion) {
        return switch (nombre.toLowerCase()) {
            case "pikachu" -> new pikachu(nombre, nivel, evolucion);
            case "charmander" -> new charmander(nombre, nivel, evolucion);
            case "bulbasaur" -> new bulbasaur(nombre, nivel, evolucion);
            case "abra" -> new abra(nombre, nivel, evolucion);
            case "chikorita" -> new chikorita(nombre, nivel, evolucion);
            case "totodile" -> new totodile(nombre, nivel, evolucion);
            case "wynaut" -> new wynaut(nombre, nivel, evolucion);
            case "squirtle" -> new squirtle(nombre, nivel, evolucion);
            case "shinx" -> new shinx(nombre, nivel, evolucion);
            case "cyndaquil" -> new cyndaquil(nombre, nivel, evolucion);
            default -> null; // o puedes lanzar una excepción
        };
    }
    public static pokemon crearPokemonParaPC(String nombre) {
    // Ejemplo: nivel 1, vida 100, daño 20, sin evolución
        return crearPokemon(nombre, 5, false);
    }
    
    
}
