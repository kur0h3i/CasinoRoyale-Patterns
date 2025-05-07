
package salas;

import acciones.Mesa;
import acciones.Pasillo;
import juegos.CartaMasAlta;
import mapas.SalaCartasMapa;
import personas.Jugador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Clase SalaCartas => Sala especializada en juegos de carta dentro del casino.
 * Implementa el patrón Singleton para garantizar una única instancia.
 * Extiende de Sala para heredar la lógica de interfaz ASCII, movimiento e interacción.
 */
public class SalaCartas extends Sala {

    /** Instancia única de SalaCartas (Singleton lazy) */
    private static SalaCartas instancia;

    /**
     * Constructor privado: inicializa el mapa, la mesa de juego y la posición de entrada.
     * @param jugador jugador que ingresa a la sala (puede ser null en la creación inicial)
     */
    private SalaCartas(Jugador jugador) {
        super(
                jugador,
                SalaCartasMapa.mapaCartas,
                // Lista de mesas disponibles en la sala de cartas
                new ArrayList<>(Arrays.asList(
                        new Mesa(
                                "CartaMasAlta",
                                1,
                                SalaCartasMapa.posXCartaMas,
                                SalaCartasMapa.posYCartaMas
                        )
                )),
                // No hay pasillos definidos internamente (se agregan externamente)
                new ArrayList<>(),
                // Coordenadas de entrada desde el pasillo
                SalaCartasMapa.posXInicial,
                SalaCartasMapa.posYInicial
        );
    }

    /**
     * Provee la instancia singleton de SalaCartas, creando la primera vez.
     * @return instancia única de SalaCartas
     */
    public static SalaCartas getInstance() {
        if (Objects.isNull(instancia)) {
            instancia = new SalaCartas(null);
        }
        return instancia;
    }

    /**
     * Suscribe al jugador a la mesa de Carta Más Alta y a posibles pasillos.
     * Asigna el jugador a la mesa, configura su estrategia y lo registra como observador.
     * @param jugador instancia de Jugador que se suscribirá (ignorado si es null)
     */
    @Override
    public void subscribe(Jugador jugador) {
        if (jugador == null) {
            return;
        }
        // Configurar la única mesa de juego en esta sala
        for (Mesa mesa : mesas) {
            mesa.setJugador(jugador);
            mesa.putStrategy();
            jugador.attach(mesa);
        }
        // Suscribir a futuros pasillos (si se agregan)
        for (Pasillo pasillo : pasillos) {
            jugador.attach(pasillo);
        }
    }

    /**
     * Desuscribe al jugador de todas las interacciones (mesas y pasillos).
     * @param jugador instancia de Jugador que se desuscribirá
     */
    @Override
    public void unsubscribe(Jugador jugador) {
        if (jugador != null) {
            jugador.detachAll();
        }
    }

    /**
     * Identificador textual de la sala para menús y logs.
     * @return cadena "Cartas"
     */
    @Override
    public String toString() {
        return "Cartas";
    }
}
