
package acciones;

// Mapas de entrada para salas especificas
import mapas.SalaCartasMapa;
import mapas.SalaJuegosAzarMapa;

// Representacion de salas y jugadores
import salas.Sala;
import personas.Jugador;

// Observer pull-push
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserver;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Representa un corredor que traslada al jugador de una sala a
 * otra
 * al entrar en las coordenadas definidas. Implementa el patron Observer para
 * reaccionar
 * al movimiento del jugador (Pull-Push), desconectarlo de la sala actual y
 * cargar la siguiente.
 */
public class Pasillo implements PullPushModelObserver, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Coordenada X donde se activa el pasillo */
    private final Integer posX;
    /** Coordenada Y donde se activa el pasillo */
    private final Integer posY;
    /** Sala destino a la que se traslada el jugador */
    private final Sala salaSiguiente;

    /**
     * Constructor de Pasillo.
     *
     * @param posX          coordenada X de activacion del pasillo
     * @param posY          coordenada Y de activacion del pasillo
     * @param salaSiguiente sala a la que se transportare al jugador
     */
    public Pasillo(Integer posX, Integer posY, Sala salaSiguiente) {
        this.posX = posX;
        this.posY = posY;
        this.salaSiguiente = salaSiguiente;
    }

    /**
     * Metodo update del Observer: se invoca cuando el jugador se mueve.
     * Si el jugador llega a las coordenadas del pasillo, se procede a:
     * 1. Desconectarlo de todos los observables de la sala actual.
     * 2. Reubicarlo en la entrada apropiada de la siguiente sala.
     * 3. Actualizar su salaActual y adjuntarlo como observador.
     * 4. Iniciar la interfaz de la nueva sala.
     *
     * @param obs objeto observado (debe ser Jugador)
     * @param obj datos adicionales (no usado)
     */
    @Override
    public void update(PullPushModelObservable obs, Object obj) {
        if (!(obs instanceof Jugador)) {
            return; // Solo procesar notificaciones de Jugador
        }
        Jugador jugadorTMP = (Jugador) obs;
        // Comprueba si el jugador este en la posicion del pasillo
        if (Objects.equals(jugadorTMP.getPosX(), this.posX)
                && Objects.equals(jugadorTMP.getPosY(), this.posY)) {

            // Desconecta al jugador de todos los observables de la sala actual
            jugadorTMP.detachAll();

            // Establece la posicion de entrada segun la sala previa
            String salaActual = jugadorTMP.getSalaActual().toString();
            if ("Cartas".equals(salaActual)) {
                jugadorTMP.setPosX(SalaCartasMapa.posXEntrada);
                jugadorTMP.setPosY(SalaCartasMapa.posYEntrada);
            } else if ("Azar".equals(salaActual)) {
                jugadorTMP.setPosX(SalaJuegosAzarMapa.posXEntrada);
                jugadorTMP.setPosY(SalaJuegosAzarMapa.posYEntrada);
            } else {
                // Entrada generica de la salaDestino
                jugadorTMP.setPosX(this.salaSiguiente.getPosInitialX());
                jugadorTMP.setPosY(this.salaSiguiente.getPosInitialY());
            }

            // Actualiza la referencia de sala actual del jugador
            jugadorTMP.setSala(this.salaSiguiente);
            // Adjunta al jugador como observador de la nueva sala
            this.salaSiguiente.setJugador(jugadorTMP);
            // Inicializa la interfaz grefica o ASCII de la sala
            this.salaSiguiente.iniciarInterfaz();
        }
    }
}
