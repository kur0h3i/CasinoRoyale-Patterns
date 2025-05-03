
// Mesa.java
package acciones;

// Excepciones
import excep.ExcepcionJugadorSinFichas;
import juegos.StrategyJuego;
//Jugador

// ASCII
import ascii.ASCIIGeneral;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserverInteractive;
import personas.Jugador;

import java.util.Objects;

import static recursos.MensajesEstaticos.interactTable;

public class Mesa implements PullPushModelObserverInteractive {

    // Atributos Adicionales
    private Integer posX, posY;
    private Jugador jugador;

    // Constructor con el Observer
    public Mesa(String nombreMesa, int numPartcipantes, Integer posX, Integer posY) {
        this.nombreMesa = nombreMesa;
        this.numPartcipantes = numPartcipantes;

        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void interactive() throws ExcepcionJugadorSinFichas {
        jugar();
    }

    @Override
    public void update(PullPushModelObservable pullPushModelObservable, Object object) throws ExcepcionJugadorSinFichas {

        if (pullPushModelObservable instanceof Jugador) { // Se que a nivel de ciclo de vida llegaria otro objeto Observable distinto, pero por si las moscas
            Jugador jugadorTMP = (Jugador) pullPushModelObservable;

            if (Objects.equals(jugadorTMP.getPosX(), this.posX) && Objects.equals(jugadorTMP.getPosY(), this.posY)) {
                interactTable(this.nombreMesa);
                this.jugador = jugadorTMP;
                if (this.jugador.getInteract()) interactive();
            }
            else {
                jugador = null; // Se asume de que no hay ningun jugador ocupado // No se si habra multiplayer xd
            }
        }

    }

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
