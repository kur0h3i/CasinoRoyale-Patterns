
// Mesa.java
package accionesCasino;

// Excepciones
import excep.ExcepcionJugadorSinFichas;

//Jugador
import juegos.Juego;

// ASCII
import ascii.ASCIIGeneral;

public class Mesa {
    
    // Atributos 
    Juego juego;
    String nombreMesa;
    int numPartcipantes;
    int [][] posicionInteractuar;

    // Constructor
    public Mesa(Juego juego, String nombreMesa, int numPartcipantes, int [][] posicionInteractuar){
        this.juego = juego;
        this.nombreMesa = nombreMesa;
        this.numPartcipantes = numPartcipantes;
        this.posicionInteractuar = posicionInteractuar;
    }



    // Getters
    public Juego getJuego() {
        return juego;
    }
    public String getNombreMesa() {
        return nombreMesa;
    }
    public int getNumPartcipantes() {
        return numPartcipantes;
    }
    public int[][] getPosicionInteractuar() {
        return posicionInteractuar;
    }

    public void setJuego(Juego nuevoJuego) {
        this.juego = nuevoJuego;
    }

    // Jugar
    public void jugar() throws ExcepcionJugadorSinFichas{
        try {
            juego.iniciarPartida(); 
        } catch (ExcepcionJugadorSinFichas e) {
            System.out.println("No puedes jugar porque no tienes fichas.");
            ASCIIGeneral.esperarTecla();
        }
    }


}
