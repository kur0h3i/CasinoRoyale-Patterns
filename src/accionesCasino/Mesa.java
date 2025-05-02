
// Mesa.java
package accionesCasino;

// Excepciones
import excep.ExcepcionJugadorSinFichas;
import juegos.StrategyJuego;
//Jugador
import juegos.Dados;

// ASCII
import ascii.ASCIIGeneral;

public class Mesa {
    
    // Atributos
    String nombreMesa;
    int numPartcipantes;
    int [][] posicionInteractuar;
    StrategyJuego strategy;

    // Constructor
    public Mesa(String nombreMesa, int numPartcipantes, int [][] posicionInteractuar){
        this.nombreMesa = nombreMesa;
        this.numPartcipantes = numPartcipantes;
        this.posicionInteractuar = posicionInteractuar;
    }



    // Getters
    /*public Juego getJuego() {
        return juego;
    }*/
    public String getNombreMesa() {
        return nombreMesa;
    }
    public int getNumPartcipantes() {
        return numPartcipantes;
    }
    public int[][] getPosicionInteractuar() {
        return posicionInteractuar;
    }

    public StrategyJuego getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyJuego strategy) {
        this.strategy = strategy;
    }

    // Jugar
    public void jugar() throws ExcepcionJugadorSinFichas{
        try {
            strategy.iniciarPartida();
        } catch (ExcepcionJugadorSinFichas e) {
            System.out.println("No puedes jugar porque no tienes fichas.");
            ASCIIGeneral.esperarTecla();
        }
    }


}
