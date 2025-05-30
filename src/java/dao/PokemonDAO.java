package dao;

import database.dbConection;
import clasesAbstractas.pokemon;
import database.dbConection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import clasesGenerales.PokemonCatalogo;
import clasesFactory.*;

public class PokemonDAO {
    private String dbPath;

    public PokemonDAO(String dbPath) {
        this.dbPath = dbPath;
    }
    
    
    static {
          try {
              Class.forName("org.sqlite.JDBC");
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }
     }
    
    public String obtenerNombreAleatorioCatalogo() {
    String nombre = null;
    String sql = "SELECT nombre FROM pokemones_catalogo ORDER BY RANDOM() LIMIT 1";

    try (Connection conn = dbConection.getConnection(dbPath);
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            nombre = rs.getString("nombre");
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombre;
    }
  
    public List<pokemon> obtenerPokemonesPorUsuario(int idUsuario) {
        List<pokemon> lista = new ArrayList<>();

        String sql = "SELECT p.* FROM pokemones p " +
                     "JOIN usuarios_pokemones up ON p.id_pokemon = up.id_pokemon " +
                     "WHERE up.id_usuario = ?";

        try (Connection conn = dbConection.getConnection(dbPath);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pokemon p = PokemonFactory.crearPokemonDesdeResultSet(rs);
                System.out.println("Procesando fila: ID = " + rs.getInt("id_pokemon"));
                if (p != null) {
                    lista.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    
    public List<PokemonCatalogo> obtenerCatalogo() {
    List<PokemonCatalogo> lista = new ArrayList<>();

    String sql = """
        SELECT id_pokemon, nombre
        FROM pokemones_catalogo
    """;

    try (Connection conn = dbConection.getConnection(dbPath);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int idCatalogo = rs.getInt("id_pokemon"); // coincidir con la columna real

                lista.add(new PokemonCatalogo(nombre, idCatalogo));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public pokemon obtenerPokemonJugadorPorIdCatalogo(int idUsuario, int idCatalogo) {
    String sql = """
        SELECT pc.nombre, p.nivel, p.evolucion
        FROM usuarios_pokemones up
        JOIN pokemones p ON up.id_pokemon = p.id_pokemon
        JOIN pokemones_catalogo pc ON p.id_catalogo = pc.id_pokemon
        WHERE up.id_usuario = ? AND pc.id_pokemon = ?
    """;

    try (Connection conn = dbConection.getConnection(dbPath);
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idUsuario);
        ps.setInt(2, idCatalogo);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                int nivel = rs.getInt("nivel");
                boolean evolucion = rs.getBoolean("evolucion");

                return PokemonBatallaFactory.crearPokemon(nombre, nivel, evolucion);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    

}
