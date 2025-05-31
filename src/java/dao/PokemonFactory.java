package dao;

import clasesAbstractas.pokemon;
import clasesGenerales.*;
// importa tus clases concretas

import java.sql.ResultSet;
import java.sql.SQLException;

public class PokemonFactory {
    public static pokemon crearPokemonDesdeResultSet(ResultSet rs) {
        try {
            String nombre = rs.getString("nombre");
            int nivel = rs.getInt("nivel");
            boolean evolucion = rs.getBoolean("evolucion");

            // Crear el Pokémon específico usando los datos de la BD
            return switch (nombre.toLowerCase()) {
                case "pikachu" -> new pikachu(nombre, nivel, evolucion);
                case "charmander" -> new charmander(nombre, nivel, evolucion);
                case "bulbasaur" -> new bulbasaur(nombre, nivel, evolucion);
                case "abra" -> new abra(nombre, nivel, evolucion);
                case "chikorita" -> new chikorita(nombre, nivel, evolucion);
                case "totodile" -> new totodile(nombre, nivel, evolucion);
                case "wynaut" -> new wynaut(nombre, nivel, evolucion);
                case "squirtle" -> new abra(nombre, nivel, evolucion);
                case "shinx" -> new shinx(nombre, nivel, evolucion);
                case "cyndaquil" -> new cyndaquil(nombre, nivel, evolucion);
                        
                default -> null; // o lanzar excepción si prefieres
            };

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
