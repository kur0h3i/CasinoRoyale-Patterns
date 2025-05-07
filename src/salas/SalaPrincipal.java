
package salas;

import acciones.Cajero;
import acciones.Fenrir;
import acciones.Pasillo;
import acciones.PuertaSalida;
import acciones.Mesa;
import ascii.ASCIIGeneral;
import personas.Jugador;
import mapas.SalaPrincipalMapa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static recursos.MensajesEstaticos.*;

/**
 * SalaPrincipal => Sala principal del casino donde aparecen el cajero, puerta de salida y el bar Fenrir.
 * Aplica el patrón Singleton para garantizar una única instancia. Extiende de Sala para
 * heredar la lógica de interfaz ASCII, movimiento e interacción.
 *
 * En esta sala se configuran además los observadores que gestionan las acciones:
 *  - Cajero automático (ATM)
 *  - Puerta de salida (guardar/cargar/salir)
 *  - Bar Fenrir (comprar bebidas)
 *
 * Uso:
 *  SalaPrincipal sala = SalaPrincipal.getInstance(jugador);
 *  sala.iniciarInterfaz();
 *
 * @see Sala
 */
public class SalaPrincipal extends Sala {

    /** Instancia Singleton de SalaPrincipal */
    private static SalaPrincipal instancia;

    /** Cajero automático disponible en la sala */
    private final Cajero cajero = new Cajero(2, 7);
    /** Puerta de salida para guardar, cargar o salir del casino */
    private final PuertaSalida puertaSalida = new PuertaSalida(4, 0);
    /** Bar Fenrir donde comprar bebidas */
    private final Fenrir fenrir = new Fenrir(12, 5);

    /**
     * Obtiene la instancia de SalaPrincipal, creando la primera vez.
     * @param jugador jugador que ingresa a la sala (puede ser null)
     * @return instancia única de SalaPrincipal
     */
    public static SalaPrincipal getInstance(Jugador jugador) {
        if (Objects.isNull(instancia)) {
            instancia = new SalaPrincipal(jugador);
        }
        return instancia;
    }

    /**
     * Obtiene la instancia existente de SalaPrincipal sin cambiar el jugador.
     * @return instancia única de SalaPrincipal
     */
    public static SalaPrincipal getInstance() {
        return getInstance(null);
    }

    /**
     * Constructor privado de SalaPrincipal.
     * Inicializa el mapa ASCII y la posición de entrada.
     * Suscribe al jugador a los observadores internos.
     *
     * @param jugador jugador que ocupa la sala (null inicializa sin asignar)
     */
    private SalaPrincipal(Jugador jugador) {
        super(
                jugador,
                SalaPrincipalMapa.mapaSalaPrincipal,
                new ArrayList<Mesa>(),   // Sin mesas de juego en sala principal
                new ArrayList<Pasillo>(), // Pasillos definidos externamente
                SalaPrincipalMapa.posXInicial,
                SalaPrincipalMapa.posYInicial
        );
        subscribe(jugador);
    }

    /**
     * Suscribe al jugador a los elementos interactivos de la sala:
     * cajero, puerta de salida y Fenrir.
     * @param jugador jugador a suscribir (ignorado si null)
     */
    @Override
    public void subscribe(Jugador jugador) {
        if (Objects.isNull(jugador)) return;
        // No hay mesas en esta sala, pero sí observadores adicionales
        jugador.attach(cajero);
        jugador.attach(puertaSalida);
        jugador.attach(fenrir);
        // Suscribir también pasillos heredados si se configuran
        for (Pasillo pasillo : getPasillos()) {
            jugador.attach(pasillo);
        }
    }

    /**
     * Desuscribe al jugador de todos los observables conectados.
     * @param jugador jugador a desuscribir (ignorado si null)
     */
    @Override
    public void unsubscribe(Jugador jugador) {
        if (Objects.isNull(jugador)) return;
        jugador.detachAll();
    }

    /**
     * Retorna el nombre de la sala para menús o logs.
     * @return "Principal"
     */
    @Override
    public String toString() {
        return "Principal";
    }
}