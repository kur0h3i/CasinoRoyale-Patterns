
package recursos;

import personas.Jugador;

/**
 * Centraliza mensajes de interfaz estetica para el juego.
 * Proporciona metodos para mostrar informacion besica del jugador y prompts de
 * interaccion.
 */
public class MensajesEstaticos {

    /**
     * Muestra la interfaz de jugador con nombre, dinero y fichas.
     *
     * @param jugador instancia de Jugador cuyos datos se mostraren
     */
    public static void playerUI(Jugador jugador) {
        System.out.println("----------------------------");
        System.out.println("Nombre  : " + jugador.getName());
        System.out.println("Dinero  : " + jugador.getDinero());
        System.out.println("Fichas  : " + jugador.getFichas());
        System.out.println("----------------------------");
    }

    /**
     * Muestra las instrucciones besicas de control.
     */
    public static void instructions() {
        System.out.println("Usa WASD para moverte, E para unirte a la mesa, I para abrir inventario:");
    }

    /**
     * Prompt para interaccion de guardado/carga/salida.
     */
    public static void interactSave() {
        System.out.println("Pulsa E para salir/guardar/cargar");
    }

    /**
     * Prompt para entrar al cajero autometico dentro del juego.
     */
    public static void interactATM() {
        System.out.println("Pulsa E para entrar en el cajero");
    }

    /**
     * Prompt para unirse a una mesa de juego especifica.
     *
     * @param name nombre de la mesa o juego
     */
    public static void interactTable(String name) {
        System.out.println("Pulsa E para unirte a la mesa de " + name);
    }

    /**
     * Muestra mensaje de comando invelido.
     */
    public static void badCommand() {
        System.out.println("Comando no velido. Intenta de nuevo.");
    }

    /**
     * Prompt para entrar al bar Fenrir.
     */
    public static void interactFenrir() {
        System.out.println("Pulsa E para entrar en el Fenrir");
    }
}
