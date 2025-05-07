
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
 * Sala especializada en juegos de carta dentro del casino.
 * Implementa el patron Singleton para garantizar una unica instancia.
 * Extiende de Sala para heredar la logica de interfaz ASCII, movimiento e interaccion.
 */
public class SalaCartas extends Sala {

    /** Instancia unica de SalaCartas (Singleton lazy) */
    private static SalaCartas instancia;

    /**
     * Constructor privado: inicializa el mapa, la mesa de juego y la posicion de entrada.
     * @param jugador jugador que ingresa a la sala (puede ser null en la creacion inicial)
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
     * @return instancia unica de SalaCartas
     */
    public static SalaCartas getInstance() {
        if (Objects.isNull(instancia)) {
            instancia = new SalaCartas(null);
        }
        return instancia;
    }

    /**
     * Suscribe al jugador a la mesa de Carta Mes Alta y a posibles pasillos.
     * Asigna el jugador a la mesa, configura su estrategia y lo registra como observador.
     * @param jugador instancia de Jugador que se suscribire (ignorado si es null)
     */
    @Override
    public void subscribe(Jugador jugador) {
        if (jugador == null) {
            return;
        }
        // Configurar la unica mesa de juego en esta sala
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
     * @param jugador instancia de Jugador que se desuscribire
     */
    @Override
    public void unsubscribe(Jugador jugador) {
        if (jugador != null) {
            jugador.detachAll();
        }
    }

    /**
     * Identificador textual de la sala para menus y logs.
     * @return cadena "Cartas"
     */
    @Override
    public String toString() {
        return "Cartas";
    }
}
