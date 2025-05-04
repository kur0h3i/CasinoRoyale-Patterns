
// Mesa.java
package acciones;

// Excepciones
import excep.ExcepcionJugadorSinFichas;

// Strategy
import juegos.*;

//Jugador
import personas.Jugador;

// ASCII
import ascii.ASCIIGeneral;

// Observer
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserverInteractive;
import recursos.Games;

import java.util.Objects;

import static recursos.Games.SLOTS;
import static recursos.MensajesEstaticos.interactTable;


// TODO : DUDA AL PROFESOR SI HACE FALTA HACER UN FACTORY O UN SINGLETON

public class Mesa implements PullPushModelObserverInteractive {

    // Atributos
    private String nombre;
    Integer numPartcipantes;
    private Integer posX, posY;
    private Jugador jugador;
    StrategyJuego strategy;

    // Constructor [Observer + Strategy]
    public Mesa(String nombre, Integer numPartcipantes, Integer posX, Integer posY) {
        this.nombre = nombre;
        this.numPartcipantes = numPartcipantes;

        this.posX = posX;
        this.posY = posY;

        this.strategy = null;
    }

    // Getters
    public Integer getNumPartcipantes() {
        return numPartcipantes;
    }
    public Integer getPosX() {return posX;}
    public Integer getPosY() {return posY;}
    public Jugador getJugador() {return jugador;}
    public StrategyJuego getStrategy() {return strategy;}

    // Setters
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    // Strategy -> Definir las estrategia
    public void setStrategy(StrategyJuego strategy) {
        this.strategy = strategy;
    }

    public void putStrategy(){
        switch (this.nombre){
            case "Slot":
                this.setStrategy(new Slot(jugador));
                break;
            case "Ruleta":
                this.setStrategy(new Ruleta(jugador));
                break;
            case "Bingo":
                this.setStrategy(new Bingo(jugador));
                break;
            case "Dados":
                this.setStrategy(new Dados(jugador));
                break;
            case "CartaMasAlta":
                this.setStrategy(new CartaMasAlta(jugador));
                break;
        }
    }



    public void jugar() throws ExcepcionJugadorSinFichas{
        try {
            strategy.iniciarPartida();
        } catch (ExcepcionJugadorSinFichas e) {
            System.out.println("No puedes jugar porque no tienes fichas.");
            ASCIIGeneral.esperarTecla();
        }
    }

    // Observer
    @Override
    public void interactive() throws ExcepcionJugadorSinFichas {
        jugar();
    }

    @Override
    public void update(PullPushModelObservable pullPushModelObservable, Object object) throws ExcepcionJugadorSinFichas {

        if (pullPushModelObservable instanceof Jugador) { // Se que a nivel de ciclo de vida llegaria otro objeto Observable distinto, pero por si las moscas
            Jugador jugadorTMP = (Jugador) pullPushModelObservable;

            if (Objects.equals(jugadorTMP.getPosX(), this.posX) && Objects.equals(jugadorTMP.getPosY(), this.posY)) {
                interactTable(this.toString()); // TODO
                this.jugador = jugadorTMP;
                if (this.jugador.getInteract()) interactive();
            }
            else {
                jugador = null; // Se asume de que no hay ningun jugador ocupado // No se si habra multiplayer xd
            }
        }

    }
}
