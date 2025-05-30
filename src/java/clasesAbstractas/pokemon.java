/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesAbstractas;
/**
 *
 * @author usuario
 */
public abstract class pokemon {
    protected String nombre; 
    protected int numVidas;
    protected double vida;
    protected int nivel; 
    protected double ataque;
    protected double resistencia;
    protected float probDefensa; 
    protected float probCritico; 
    protected boolean evolucion;
    public String accion;
    
    protected abstract void subirNivel(); 
    protected abstract void evolucionar(); 
    
    public pokemon(String nombre, int nivel, boolean evolucion) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.evolucion = evolucion;

        // Inicializa atributos temporales con valores por defecto,
        // que puedes definir en cada clase concreta llamando a este método
        inicializarAtributosPorDefecto();
    }

    // Método abstracto que cada subclase debe implementar para asignar sus stats base
    protected abstract void inicializarAtributosPorDefecto();
    
        public double atacar(pokemon contrincante){
        int aleatorio = 5;//(int)(Math.random() * 100) + 1;
        double daño = ataque;

        if (aleatorio <= probCritico) {
            daño = ataque * 1.8;
            registrarUltimaAccion("AtaqueCritico");
        } else {
            registrarUltimaAccion("AtaqueNormal");
        }

        int probabilidad = (int)(Math.random() * 100) + 1;

        if (probabilidad <= probDefensa) {
            registrarUltimaAccion("Defensa");
            daño = 0;
        }

        double dañoReal = contrincante.recibirAtaque(daño);
        return Math.round(dañoReal * 100.0) / 100.0;  // Redondeado a 2 decimales
    }
    
    public double recibirAtaque(double daño){
        daño -= (resistencia / 2);
        if (daño < 0) daño = 0;
        vida -= daño;
        if (vida < 0) vida = 0;

        vida = Math.round(vida * 100.0) / 100.0;
        return daño;
    }
    
    public String registrarUltimaAccion(String accionNueva){
        accion = accionNueva;
        
        return accion;
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getNumVidas() {
        return numVidas;
    }

    public double getVida() {
        return vida;
    }

    public int getNivel() {
        return nivel;
    }

    public double getAtaque() {
        return ataque;
    }

    public double getResistencia() {
        return resistencia;
    }

    public float getProbDefensa() {
        return probDefensa;
    }

    public float getProbCritico() {
        return probCritico;
    }

    public boolean isEvolucion() {
        return evolucion;
    }

    public String getAccion(){
        return accion;
    }
    
    
    //Setters
    public void setAccion(String accion){
        this.accion = accion;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumVidas(int numVidas) {
        this.numVidas = numVidas;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }

    public void setResistencia(double resistencia) {
        this.resistencia = resistencia;
    }

    public void setProbDefensa(float probDefensa) {
        this.probDefensa = probDefensa;
    }

    public void setProbCritico(float probCritico) {
        this.probCritico = probCritico;
    }

    public void setEvolucion(boolean evolucion) {
        this.evolucion = evolucion;
    }

}
