
package acciones;

// Excepciones de juego
import excep.ExcepcionJugadorSinFichas;

// Estrategias de juego (Strategy)
import juegos.*;

// Representacion del jugador
import personas.Jugador;

// Utilidades ASCII para limpiar pantalla y pausar
import ascii.ASCIIGeneral;

// Observer pull-push
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserverInteractive;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import static recursos.MensajesEstaticos.interactTable;

/**
 * Representa una mesa de juego en el casino.
 * Combina el patron Observer (Pull-Push) para detectar la posicion e
 * interaccion del jugador
 * y el patron Strategy para seleccionar dinemicamente el juego segun el tipo de
 * mesa.
 */
public class Mesa implements PullPushModelObserverInteractive, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Nombre identificador de la mesa ("Slot", "Ruleta", "Bingo", "Dados",
     * "CartaMasAlta")
     */
    private String nombre;
    /** Numero de participantes permitido en la mesa */
    private Integer numParticipantes;
    /** Coordenada X de la mesa en el mapa del casino */
    private Integer posX;
    /** Coordenada Y de la mesa en el mapa del casino */
    private Integer posY;
    /** Referencia al jugador que este interactuando (null si ninguno) */
    private Jugador jugador;
    /** Estrategia de juego actual (StrategyJuego) */
    private StrategyJuego strategy;

    /**
     * Constructor de Mesa.
     * 
     * @param nombre           identificador de la mesa ("Slot", "Ruleta", etc.)
     * @param numParticipantes numero de jugadores que participan (no usado
     *                         internamente)
     * @param posX             coordenada X donde se ubica la mesa
     * @param posY             coordenada Y donde se ubica la mesa
     */
    public Mesa(String nombre, Integer numParticipantes, Integer posX, Integer posY) {
        this.nombre = nombre;
        this.numParticipantes = numParticipantes;
        this.posX = posX;
        this.posY = posY;
        this.strategy = null; // La estrategia se asigna al interactuar
    }

    // ===================== Getters =====================
    public Integer getNumParticipantes() {
        return numParticipantes;
    }

    public Integer getPosX() {
        return posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public StrategyJuego getStrategy() {
        return strategy;
    }

    // ===================== Setters =====================
    /**
     * Asigna el jugador que interactua con la mesa.
     * 
     * @param jugador instancia de Jugador
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    /**
     * Define la estrategia de juego (Strategy) para esta mesa.
     * 
     * @param strategy implementacion de StrategyJuego
     */
    public void setStrategy(StrategyJuego strategy) {
        this.strategy = strategy;
    }

    /**
     * Selecciona e instancia la estrategia apropiada segun el nombre de la mesa.
     * Debe llamarse despues de asignar el jugador para poder pasarle la referencia.
     */
    public void putStrategy() {
        switch (this.nombre) {
            case "Slot":
                setStrategy(new Slot(jugador));
                break;
            case "Ruleta":
                setStrategy(new Ruleta(jugador));
                break;
            case "Bingo":
                setStrategy(new Bingo(jugador));
                break;
            case "Dados":
                setStrategy(new Dados(jugador));
                break;
            case "CartaMasAlta":
                setStrategy(new CartaMasAlta(jugador));
                break;
            default:
                // Nombre de mesa no reconocido
                throw new IllegalArgumentException("Juego no soportado: " + nombre);
        }
    }

    /**
     * Inicia la partida usando la estrategia actual.
     * Captura excepcion si el jugador no tiene fichas suficientes.
     * 
     * @throws ExcepcionJugadorSinFichas si no hay fichas
     */
    public void jugar() throws ExcepcionJugadorSinFichas {
        try {
            strategy.iniciarPartida();
        } catch (ExcepcionJugadorSinFichas e) {
            System.out.println("No puedes jugar porque no tienes fichas.");
            ASCIIGeneral.esperarTecla();
        }
    }

    // ===================== Observer Methods =====================

    /**
     * Al interactuar, lanza el metodo jugar().
     */
    @Override
    public void interactive() throws ExcepcionJugadorSinFichas {
        jugar();
    }

    /**
     * Metodo update del Observer: se notifica cuando el jugador se mueve o
     * interactua.
     * Si el jugador este en la posicion de la mesa, asigna jugador, muestra mensaje
     * y, si pulsa interactuar, arranca el juego.
     *
     * @param obs observable (normalmente un Jugador)
     * @param obj objeto adicional (no usado)
     */
    @Override
    public void update(PullPushModelObservable obs, Object obj) throws ExcepcionJugadorSinFichas {
        if (obs instanceof Jugador) {
            Jugador j = (Jugador) obs;
            if (Objects.equals(j.getPosX(), posX) && Objects.equals(j.getPosY(), posY)) {
                interactTable(this.toString());
                this.jugador = j;
                putStrategy();
                if (j.getInteract()) {
                    interactive();
                }
            } else {
                this.jugador = null; // Limpia la referencia al alejarse
            }
        }
    }

    /**
     * Representacion por defecto de la mesa (se muestra en el mensaje de
     * interaccion).
     */
    @Override
    public String toString() {
        return nombre + " (" + numParticipantes + " plazas)";
    }
}
