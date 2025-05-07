
package personas;

/**
 * Clase de fábrica para instanciar diferentes tipos de jugadores.
 * Permite crear tanto al jugador principal como NPC basándose en un identificador de tipo.
 */
public class JugadorFactory {

    /**
     * Crea un nuevo Jugador según el tipo especificado.
     *
     * @param tipo     "principal" para jugador principal, "npc" para jugador NPC
     * @param nombre   nombre del jugador a crear
     * @param edad     edad del jugador
     * @param dinero   saldo inicial en euros
     * @return instancia de JugadorPrincipal o JugadorNPC, o null si el tipo es nulo o desconocido
     */
    public static Jugador crearJugador(String tipo, String nombre, int edad, double dinero) {
        if (tipo == null) {
            return null;
        }
        switch (tipo.toLowerCase()) {
            case "principal":
                // Crea el jugador principal con posición inicial por defecto
                return new JugadorPrincipal(nombre, edad, dinero);
            case "npc":
                // Crea un jugador NPC (sin posición inicial)
                return new JugadorNPC(nombre, edad, dinero);
            default:
                // Tipo no reconocido
                return null;
        }
    }
}
