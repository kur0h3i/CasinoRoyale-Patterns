package personas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear un objeto Scanner para leer desde el teclado
        Scanner scanner = new Scanner(System.in);

        // Solicitar el nombre del jugador 1
        System.out.print("Introduce el nombre del jugador principal: ");
        String nombreJugador1 = scanner.nextLine();

        // Solicitar las fichas del jugador 1
        System.out.print("Introduce el número de fichas del jugador principal: ");
        int fichasJugador1 = scanner.nextInt();

        // Crear un jugador principal con los datos introducidos
        Jugador jugador1 = JugadorFactory.crearJugador("principal", nombreJugador1, 25, 1000.0);
        jugador1.setFichas(fichasJugador1);  // Asignar las fichas al jugador

        // Mostrar los datos del jugador 1
        jugador1.datosUsuarioEnPartida();

        // Crear un NPC (puedes ajustar su nombre y datos si lo deseas)
        Jugador jugador2 = JugadorFactory.crearJugador("npc", "Goblin", 100, 500.0);
        jugador2.datosUsuarioEnPartida();

        // Cerrar el scanner
        scanner.close();
    }
}
