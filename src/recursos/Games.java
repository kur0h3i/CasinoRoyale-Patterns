
package recursos;

/**
 * Define los nombres de las estrategias de juego disponibles en el casino.
 * Cada constante representa un juego y almacena su identificador de cadena.
 */
public enum Games {

    /** Tragaperras */
    SLOTS("Slots"),
    /** Ruleta */
    RULETA("Ruleta"),
    /** Bingo */
    BINGO("Bingo"),
    /** Dados */
    DADOS("Dados"),
    /** Carta más alta */
    CARTAMASALTA("CartaMasAlta");

    /** Identificador de cadena asociado al juego */
    private final String identifier;

    /**
     * Constructor de la constante.
     *
     * @param identifier cadena que identifica la estrategia de juego
     */
    Games(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Obtiene el identificador de cadena del juego.
     *
     * @return el nombre de la estrategia de juego
     */
    public String getIdentifier() {
        return identifier;
    }
}
