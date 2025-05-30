package clasesGenerales;

public class PokemonCatalogo {
    private String nombre;
    private int idCatalogo;
    
    public PokemonCatalogo() {
        // Constructor vacío
    }
    
    public PokemonCatalogo(String nombre) {
        this.nombre = nombre;
    }
    
    public PokemonCatalogo(String nombre, int idCatalogo) {
        this.idCatalogo = idCatalogo;
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
