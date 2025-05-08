
package acciones;

// Importaciones de interfaces ASCII, excepciones y utilidades
import ascii.ASCIIFenrir;
import ascii.ASCIIGeneral;
import excep.ExcepcionJugadorSinDinero;
import items.Bebida;
import items.Items;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserverInteractive;
import personas.Jugador;
import static recursos.MensajesEstaticos.interactFenrir;

/**
 * Representa un bar donde el jugador puede comprar bebidas
 * usando
 * dinero.
 * Implementa el patron Observer (Pull-Push) para reaccionar a la posicion e
 * interaccion del jugador.
 */
public class Fenrir implements PullPushModelObserverInteractive, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Coordenada X del bar Fenrir en la sala */
    private Integer posX;
    /** Coordenada Y del bar Fenrir en la sala */
    private Integer posY;
    /** Jugador que interactua actualmente (null si ninguno este cerca) */
    private Jugador jugador;
    /** Lista de bebidas disponibles en el bar */
    private List<Items> bebidasDisponibles = new ArrayList<>();
    /** Interfaz ASCII especifica del bar para mostrar menus y mensajes */
    private ASCIIFenrir interfaz;

    /**
     * Constructor de Fenrir: inicializa la carta de bebidas y la posicion en sala.
     *
     * @param posX coordenada X del bar
     * @param posY coordenada Y del bar
     */
    public Fenrir(Integer posX, Integer posY) {
        // Inicializa carta de bebidas con nombre, precio y descripcion
        bebidasDisponibles.add(new Bebida("Cerveza", 2.50, "Bien fria"));
        bebidasDisponibles.add(new Bebida("Copa de vino", 3.00, "Tinto de la casa"));
        bebidasDisponibles.add(new Bebida("Redbull", 1.59, "Te da alas"));
        bebidasDisponibles.add(new Bebida("Cacaolat", 1.59, "Chocolate"));
        bebidasDisponibles.add(new Bebida("FireBall", 1.59, "Canela y fuego"));

        this.posX = posX;
        this.posY = posY;
        this.interfaz = new ASCIIFenrir();
    }

    /**
     * Metodo llamado por el observable (Jugador) cuando cambia de estado.
     * Si el jugador este en la posicion del bar, muestra mensaje de interaccion y
     * espera input.
     *
     * @param obs observable (debe ser instancia de Jugador)
     * @param obj objeto adicional (no usado)
     */
    @Override
    public void update(PullPushModelObservable obs, Object obj) {
        if (obs instanceof Jugador) {
            Jugador j = (Jugador) obs;
            // Comprueba si el jugador este en la misma coordenada que el bar
            if (Objects.equals(j.getPosX(), posX) && Objects.equals(j.getPosY(), posY)) {
                this.jugador = j;
                interactFenrir();
                // Si el jugador pulsa la tecla de interactuar, abre menu
                if (j.getInteract()) {
                    interactive();
                }
            } else {
                // Si se aleja, limpiamos la referencia al jugador
                this.jugador = null;
            }
        }
    }

    /**
     * Inicia la interaccion con el bar: crea Scanner y llama al bucle de menu.
     */
    @Override
    public void interactive() {
        Scanner input = new Scanner(System.in);
        iniciarBar(input);
    }

    /**
     * Bucle principal de opciones: comprar bebida o salir del bar.
     *
     * @param input Scanner para leer la opcion del usuario
     */
    public void iniciarBar(Scanner input) {
        int opcion = 0;
        while (opcion != 2) {
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo(); // Muestra titulo ASCII del bar
            interfaz.opcionesBar(); // Muestra opciones del bar (1: comprar, 2: salir)
            try {
                opcion = input.nextInt();
                input.nextLine();
                switch (opcion) {
                    case 1:
                        comprarBebida(input);
                        break;
                    case 2:
                        System.out.println("Saliendo del bar...");
                        ASCIIGeneral.limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opcion no velida. Elige 1 o 2.");
                        ASCIIGeneral.esperarTecla();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no velida. Usa numeros.");
                input.nextLine(); // limpia buffer
                ASCIIGeneral.esperarTecla();
            }
        }
    }

    /**
     * Muestra la carta de bebidas y procesa la seleccion del usuario.
     *
     * @param input Scanner para leer el indice de bebida
     */
    private void comprarBebida(Scanner input) {
        interfaz.carta();
        try {
            Integer id = input.nextInt();
            input.nextLine();
            // Valida seleccion dentro del rango
            if (id < 1 || id > bebidasDisponibles.size()) {
                System.out.println("Seleccion invelida.");
                ASCIIGeneral.esperarTecla();
                return;
            }
            Items bebida = bebidasDisponibles.get(id - 1);
            procesarCompraDinero(bebida);
        } catch (InputMismatchException e) {
            System.out.println("Debe introducir un numero.");
            input.nextLine();
            ASCIIGeneral.esperarTecla();
        }
    }

    /**
     * Comprueba si el jugador tiene suficiente dinero y, en caso afirmativo,
     * descuenta el precio y anade el item al inventario.
     *
     * @param bebida objeto Items con nombre y precio
     */
    private void procesarCompraDinero(Items bebida) {
        try {
            Double precio = bebida.getPrecio();
            if (jugador.getDinero() < precio) {
                throw new ExcepcionJugadorSinDinero("Fondos insuficientes");
            }
            jugador.restarDinero(precio); // Descuenta dinero del jugador
            jugador.agregarItem(bebida); // Anade bebida al inventario del jugador
            System.out.println("Has comprado " + bebida.getNombre() + " por €" + precio);
        } catch (ExcepcionJugadorSinDinero e) {
            System.out.println("No tienes suficiente dinero para " + bebida.getNombre());
        }
        ASCIIGeneral.esperarTecla();
    }
}
