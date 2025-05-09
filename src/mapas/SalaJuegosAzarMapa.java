
package mapas;

public class SalaJuegosAzarMapa {

    /**
     * Define la representacion y posiciones clave de la sala de juegos de azar en el casino.
     * Contiene un mapa de caracteres que modela el plano de la sala, junto con coordenadas
     * esteticas para la posicion inicial del jugador, la entrada desde pasillos y la ubicacion de mesas especificas.
     */
    public static final Character[][] mapaAzar = {
            { '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', 'D', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#', 'A', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R', 'U', 'L', 'E', 'T', 'A', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#', 'D', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#', 'O', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', 'S', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#' },
            { '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', 'S', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', 'L', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', 'T', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', 'S', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', ' ', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', 'B', 'I', 'N', 'G', 'O', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' }
    };

    /** Coordenada X inicial donde aparece el jugador al entrar en la sala */
    public static final Integer posXInicial = 3;
    /** Coordenada Y inicial donde aparece el jugador al entrar en la sala */
    public static final Integer posYInicial = 1;

    /** Coordenada X de la entrada desde el pasillo hacia la sala */
    public static final Integer posXEntrada = 3;
    /** Coordenada Y de la entrada desde el pasillo hacia la sala */
    public static final Integer posYEntrada = 14;

    /** Coordenada X de la mesa de Ruleta dentro del mapa */
    public static final Integer posXRuleta = 9;
    /** Coordenada Y de la mesa de Ruleta dentro del mapa */
    public static final Integer posYRuleta = 4;

    /** Coordenada X de la mequina Slot dentro del mapa */
    public static final Integer posXSlots = 2;
    /** Coordenada Y de la mequina Slot dentro del mapa */
    public static final Integer posYSlots = 11;

    /** Coordenada X de la mesa de Bingo dentro del mapa */
    public static final Integer posXBingo = 15;
    /** Coordenada Y de la mesa de Bingo dentro del mapa */
    public static final Integer posYBingo = 13;

    /** Coordenada X de la mesa de Dados dentro del mapa */
    public static final Integer posXDados = 24;
    /** Coordenada Y de la mesa de Dados dentro del mapa */
    public static final Integer posYDados = 7;
}
