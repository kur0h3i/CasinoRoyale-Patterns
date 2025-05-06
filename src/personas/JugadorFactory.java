package personas;

public class JugadorFactory {
    // Método para crear un jugador, dependiendo del tipo
    public static Jugador crearJugador(String tipo, String nombre, Integer edad, Double dinero) {
        if (tipo == null) {
            return null;
        }

        Jugador jugador = null;

        if (tipo.equalsIgnoreCase("principal")) {
            jugador = new JugadorPrincipal(nombre, edad, dinero);
        } else if (tipo.equalsIgnoreCase("npc")) {
            jugador = new JugadorNPC(nombre, edad, dinero);
        }

        // Envolver al jugador con el decorador de fondos
        if (jugador != null) {
            jugador = new FondosDecorator(jugador); // Aquí envolvemos al jugador con el decorador
        }

        return jugador;
    }
}
