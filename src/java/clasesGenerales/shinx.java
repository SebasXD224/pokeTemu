/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesGenerales;
import clasesAbstractas.pokemon;

/**
 *
 * @author usuario
 */
public class shinx extends pokemon{
    public shinx(String nombre, int nivel, boolean evolucion) {
        super(nombre, nivel, evolucion);
    }
    @Override
    protected void inicializarAtributosPorDefecto() {
        // Puedes ajustar estos valores según nivel o dejar fijos
        this.numVidas = 3;
        this.vida = 100;
        this.ataque = (evolucion ? (1 + nivel) * 1.5 : (1 + nivel));
        this.resistencia = (evolucion ? (1 + (nivel * 0.5)) * 1.2 : (1 + (nivel * 0.5)));
        this.probDefensa = 5;
        this.probCritico = 5;
    }
    
    @Override
    public void evolucionar(){
        evolucion = true;
    }
}
