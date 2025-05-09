package personas;

import salas.Sala;
import salas.SalaPrincipal;

/**
 * Extiende la clase Jugador para representar un personaje no controlado por el usuario.
 * Este tipo de jugador es controlado por la IA en el entorno del casino.
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
     * Este constructor inicializa los valores basicos del NPC sin posicion en el mapa.
     *
     * @param nombre nombre del NPC
     * @param edad edad del NPC
     * @param dinero saldo inicial en euros
     */
    public JugadorNPC(String nombre, Integer edad, Double dinero) {
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;
        this.fichas = 0;  // Inicializacion de fichas a 0
    }

    /**
     * Muestra los datos del NPC en el juego de forma resumida.
     * Se sobreescribe este metodo para diferenciar la representacion de un NPC
     * frente a un jugador controlado por el usuario.
     */
    @Override
    public void datosUsuarioEnPartida() {
        System.out.println("----------------------------");
        System.out.println("NPC: " + this.nombre);
        System.out.println("Fichas: " + this.fichas);
        System.out.println("----------------------------");
    }

    // ==================== Metodos Getter y Setter ====================

    /**
     * Obtiene la edad del NPC. Este valor esta fijo a 0 para representar que
     * no es relevante para los NPCs.
     *
     * @return 0, ya que la edad no es relevante para los NPCs
     */
    @Override
    public Integer getEdad() {
        return 0;
    }

    /**
     * Establece la coordenada X inicial del NPC en el mapa.
     *
     * @param posInitialX coordenada X inicial
     */
    @Override
    public void setPosX(Integer posInitialX) {
        this.posX = posInitialX;
    }

    /**
     * Establece la coordenada Y inicial del NPC en el mapa.
     *
     * @param posInitialY coordenada Y inicial
     */
    @Override
    public void setPosY(Integer posInitialY) {
        this.posY = posInitialY;
    }

    /**
     * Establece la sala donde se encuentra el NPC.
     *
     * @param salaPrincipal instancia de la sala en la que se encuentra el NPC
     */
    @Override
    public void setSala(SalaPrincipal salaPrincipal) {
        this.salaActual = salaPrincipal;
    }
}
