
package personas;

/**
 * Clase de febrica para instanciar diferentes tipos de jugadores.
 * Permite crear tanto al jugador principal como NPC basendose en un identificador de tipo.
 */
public class JugadorFactory {

    /**
     * Crea un nuevo Jugador segun el tipo especificado.
     *
     * @param tipo     "principal" para jugador principal, "npc" para jugador NPC
     * @param nombre   nombre del jugador a crear
     * @param edad     edad del jugador
     * @param dinero   saldo inicial en euros
     * @return instancia de JugadorPrincipal o JugadorNPC, o null si el tipo es nulo o desconocido
     */
    public static JugadorTotal crearJugador(String tipo, String nombre, Integer edad, Double dinero) {
        if (tipo == null) {
            return null;
        }
        switch (tipo.toLowerCase()) {
            case "principal":
                // Crea el jugador principal con posicion inicial por defecto
                return new Jugador(nombre, edad, dinero);
            case "npc":
                // Crea un jugador NPC (sin posicion inicial)
                return new JugadorNPC(nombre, edad, dinero);
            default:
                // Tipo no reconocido
                return null;
        }
    }
}
