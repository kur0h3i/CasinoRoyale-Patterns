

package personas;

import ascii.ASCIIGeneral;
import excep.ExcepcionJugadorSinFichas;
import items.Items;

import java.util.List;
import java.util.Scanner;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserver;
import salas.Sala;
import salas.SalaPrincipal;

/**
 * Extension de Jugador que representa un personaje no controlado por el usuario.
 * Se utiliza para simular participantes controlados por la IA en el casino.
 */
public class JugadorNPC implements JugadorTotal {

    /** Coordenada X actual del jugador en el mapa */
    private Integer posX;
    /** Coordenada Y actual del jugador en el mapa */
    private Integer posY;
    /** Indicador de que el jugador ha pulsado la tecla de interactuar */
    private Boolean interact;

    /** Nombre del jugador */
    private String nombre;
    /** Edad del jugador */
    private Integer edad;
    /** Dinero en euros disponible */
    private Double dinero;
    /** Fichas disponibles para apostar */
    private Integer fichas;
    /** Sala actual donde se encuentra el jugador */
    private Sala salaActual;

    /**
     * Constructor de JugadorNPC.
     *
     * @param nombre nombre del NPC
     * @param edad edad del NPC
     * @param dinero saldo inicial en euros
     */
    public JugadorNPC(String nombre, int edad, double dinero) {
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;
    }

    /**
     * Muestra los datos del NPC en el juego de forma resumida.
     * Se sobreescribe para identificar al personaje como NPC.
     */
    @Override
    public void datosUsuarioEnPartida() {
        System.out.println("----------------------------");
        System.out.println("NPC: " + this.nombre);
        System.out.println("Fichas: " + this.fichas);
        System.out.println("----------------------------");
    }

    @Override
    public Integer getEdad() {
        return 0;
    }

    @Override
    public void setPosX(Integer posInitialX) {
        this.posX = posInitialX;
    }

    @Override
    public void setPosY(Integer posInitialY) {
        this.posY = posInitialY;
    }

    @Override
    public void setSala(SalaPrincipal salaPrincipal) {
        this.salaActual = salaPrincipal;
    }


}
