
package mapas;

public class SalaJuegosAzarMapa {

    /**
     * Clase SalaJuegosAzarMapa => Define la representación y posiciones clave de la sala de juegos de azar en el casino.
     * Contiene un mapa de caracteres que modela el plano de la sala, junto con coordenadas
     * estáticas para la posición inicial del jugador, la entrada desde pasillos y la ubicación de mesas específicas.
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
    public static final int posXInicial = 3;
    /** Coordenada Y inicial donde aparece el jugador al entrar en la sala */
    public static final int posYInicial = 1;

    /** Coordenada X de la entrada desde el pasillo hacia la sala */
    public static final int posXEntrada = 3;
    /** Coordenada Y de la entrada desde el pasillo hacia la sala */
    public static final int posYEntrada = 14;

    /** Coordenada X de la mesa de Ruleta dentro del mapa */
    public static final int posXRuleta = 9;
    /** Coordenada Y de la mesa de Ruleta dentro del mapa */
    public static final int posYRuleta = 4;

    /** Coordenada X de la máquina Slot dentro del mapa */
    public static final int posXSlots = 2;
    /** Coordenada Y de la máquina Slot dentro del mapa */
    public static final int posYSlots = 11;

    /** Coordenada X de la mesa de Bingo dentro del mapa */
    public static final int posXBingo = 15;
    /** Coordenada Y de la mesa de Bingo dentro del mapa */
    public static final int posYBingo = 13;

    /** Coordenada X de la mesa de Dados dentro del mapa */
    public static final int posXDados = 24;
    /** Coordenada Y de la mesa de Dados dentro del mapa */
    public static final int posYDados = 7;
}
