/**
 * Clase MensajesEstaticos: centraliza mensajes de interfaz estática para el juego.
 * Proporciona métodos para mostrar información básica del jugador y prompts de interacción.
 */
package recursos;

import personas.Jugador;

public class MensajesEstaticos {

    /**
     * Muestra la interfaz de jugador con nombre, dinero y fichas.
     *
     * @param jugador instancia de Jugador cuyos datos se mostrarán
     */
    public static void playerUI(Jugador jugador) {
        System.out.println("----------------------------");
        System.out.println("Nombre  : " + jugador.getName());
        System.out.println("Dinero  : " + jugador.getDinero());
        System.out.println("Fichas  : " + jugador.getFichas());
        System.out.println("----------------------------");
    }

    /**
     * Muestra las instrucciones básicas de control.
     */
    public static void instructions() {
        System.out.println("Usa WASD para moverte, E para unirte a la mesa:");
    }

    /**
     * Prompt para interacción de guardado/carga/salida.
     */
    public static void interactSave() {
        System.out.println("Pulsa E para salir/guardar/cargar");
    }

    /**
     * Prompt para entrar al cajero automático dentro del juego.
     */
    public static void interactATM() {
        System.out.println("Pulsa E para entrar en el cajero");
    }

    /**
     * Prompt para unirse a una mesa de juego específica.
     *
     * @param name nombre de la mesa o juego
     */
    public static void interactTable(String name) {
        System.out.println("Pulsa E para unirte a la mesa de " + name);
    }

    /**
     * Muestra mensaje de comando inválido.
     */
    public static void badCommand() {
        System.out.println("Comando no válido. Intenta de nuevo.");
    }

    /**
     * Prompt para entrar al bar Fenrir.
     */
    public static void interactFenrir() {
        System.out.println("Pulsa E para entrar en el Fenrir");
    }
}
