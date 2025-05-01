package recursos;

import personas.Jugador;

public class MensajesEstaticos {

    public static void playerUI(Jugador jugador) {
        System.out.println("----------------------------");
        System.out.println("Nombre :  " + jugador.getName());
        System.out.println("Dinero : " + jugador.getDinero());
        System.out.println("Fichas : " + jugador.getFichas());
        System.out.println("----------------------------");
    }

    public static void instructions() {
        System.out.println("Usa WASD para moverte, E para unirte a la mesa:");
    }

    public static void interactSave() {
        System.out.println("Usa E para salir/guardar/cargar");
    }

    public static void interactATM() {
        System.out.println("Usa E para entrar en el cajero");
    }

    public static void interactTable(String name) {
        System.out.println("Usa E para unirte a la mesa de " + name);
    }

    public static void badCommand() {
        System.out.println("Comando no válido. Intenta de nuevo.");
    }

}
