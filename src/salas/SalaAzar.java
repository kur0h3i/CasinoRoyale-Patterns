
package salas;

import acciones.Mesa;
import acciones.Pasillo;
import mapas.SalaJuegosAzarMapa;
import personas.Jugador;
import juegos.Bingo;
import juegos.Dados;
import juegos.Ruleta;
import juegos.Slot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Clase SalaAzar => sala que agrupa los juegos de azar del casino.
 * Implementa el patrón Singleton para garantizar una única instancia.
 * Extiende de Sala para heredar la lógica de interfaz y movimiento.
 */
public class SalaAzar extends Sala {

    /** Referencia singleton de SalaAzar (inicialización perezosa) */
    private static SalaAzar instancia;

    /**
     * Constructor privado que inicializa la sala con su mapa,
     * mesas de juego y posición de entrada.
     *
     * @param jugador jugador que ingresa a la sala (puede ser null en el momento de la creación)
     */
    private SalaAzar(Jugador jugador) {
        super(
                jugador,
                SalaJuegosAzarMapa.mapaAzar,
                new ArrayList<>(Arrays.asList(
                        new Mesa("Ruleta", 1, SalaJuegosAzarMapa.posXRuleta, SalaJuegosAzarMapa.posYRuleta),
                        new Mesa("Bingo",  1, SalaJuegosAzarMapa.posXBingo,  SalaJuegosAzarMapa.posYBingo),
                        new Mesa("Slot",   1, SalaJuegosAzarMapa.posXSlots,  SalaJuegosAzarMapa.posYSlots),
                        new Mesa("Dados",  1, SalaJuegosAzarMapa.posXDados,  SalaJuegosAzarMapa.posYDados)
                )),
                new ArrayList<>(),
                SalaJuegosAzarMapa.posXInicial,
                SalaJuegosAzarMapa.posYInicial
        );
    }

    /**
     * Obtiene la instancia única de SalaAzar, creando una si no existe.
     *
     * @return la instancia singleton de SalaAzar
     */
    public static SalaAzar getInstance() {
        if (Objects.isNull(instancia)) {
            instancia = new SalaAzar(null);
        }
        return instancia;
    }

    /**
     * Suscribe al jugador a todas las mesas y pasillos de la sala.
     * Asigna el jugador a cada mesa y establece su estrategia de juego.
     *
     * @param jugador instancia de Jugador que se suscribirá
     */
    @Override
    public void subscribe(Jugador jugador) {
        if (jugador == null) {
            return;
        }
        // Configurar cada mesa con el jugador y su estrategia
        for (Mesa mesa : mesas) {
            mesa.setJugador(jugador);
            mesa.putStrategy();
            jugador.attach(mesa);
        }
        // Suscribir pasillos (aunque aquí no hay pasillos definidos)
        for (Pasillo pasillo : pasillos) {
            jugador.attach(pasillo);
        }
    }

    /**
     * Desuscribe al jugador de todos los observables (mesas, pasillos, etc.).
     *
     * @param jugador instancia de Jugador que se desuscribirá
     */
    @Override
    public void unsubscribe(Jugador jugador) {
        if (jugador != null) {
            jugador.detachAll();
        }
    }

    /**
     * Representación textual de la sala para menús o logs.
     *
     * @return el nombre "Azar"
     */
    @Override
    public String toString() {
        return "Azar";
    }
}
