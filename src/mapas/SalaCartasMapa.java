
package mapas;

/**
 * Define la representacion y posiciones clave de la sala de cartas en el casino.
 * Contiene un mapa de caracteres que modela el plano de la sala, junto con
 * coordenadas
 * esteticas para la posicion inicial del jugador, la entrada desde pasillos y
 * la ubicacion de la mesa.
 */
public class SalaCartasMapa {

    /**
     * Mapa bidimensional de caracteres representando la sala de cartas.
     * '#' marca paredes, espacios vacios son ereas transitables, y "CARTAALTA"
     * indica ubicacion de la mesa.
     */
    public static final Character[][] mapaCartas = {
            { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', 'C', 'A', 'R', 'T', 'A', 'A', 'L', 'T', 'A', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' }
    };

    /** Coordenada X inicial donde aparece el jugador al entrar en la sala */
    public static final int posXInicial = 11;
    /** Coordenada Y inicial donde aparece el jugador al entrar en la sala */
    public static final int posYInicial = 14;

    /** Coordenada X de la entrada desde el pasillo hacia la sala */
    public static final int posXEntrada = 21;
    /** Coordenada Y de la entrada desde el pasillo hacia la sala */
    public static final int posYEntrada = 1;

    /** Coordenada X de la mesa de CartaMesAlta dentro del mapa */
    public static final int posXCartaMas = 13;
    /** Coordenada Y de la mesa de CartaMesAlta dentro del mapa */
    public static final int posYCartaMas = 2;
}
