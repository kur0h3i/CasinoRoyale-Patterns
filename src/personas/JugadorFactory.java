package personas;

public class JugadorFactory {
    // Método para crear un jugador, dependiendo del tipo
    public static Jugador crearJugador(String tipo, String nombre, int edad, double dinero) {
        if (tipo == null) {
            return null;
        }

        if (tipo.equalsIgnoreCase("principal")) {
            return new JugadorPrincipal(nombre, edad, dinero);
        } else if (tipo.equalsIgnoreCase("npc")) {
            return new JugadorNPC(nombre, edad, dinero); // Asegúrate de pasar los parámetros aquí
        }

        return null;
    }
}
