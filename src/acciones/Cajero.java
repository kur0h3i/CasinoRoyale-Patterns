
package acciones;

// Imports de excepciones y utilidades
import ascii.ASCIICajero;
import ascii.ASCIIGeneral;
import excep.ExcepcionJugadorSinDinero;
import excep.ExcepcionJugadorSinFichas;

import java.io.Serial;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserverInteractive;
import personas.Jugador;
import static recursos.MensajesEstaticos.interactATM;

/**
 * Permite a un jugador convertir dinero en fichas y viceversa
 * mediante una interfaz ASCII.
 * Implementa el patron Observer (Pull-Push) para reaccionar a la posicion e
 * interaccion del jugador.
 */
public class Cajero implements PullPushModelObserverInteractive, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Coordenada X del cajero en la sala */
    private Integer posX;
    /** Coordenada Y del cajero en la sala */
    private Integer posY;
    /** Referencia al jugador que interactua actualmente */
    private Jugador jugador;
    /** Interfaz ASCII especifica del cajero para mostrar menus y mensajes */
    private ASCIICajero interfaz;

    /**
     * Constructor del cajero con sus coordenadas en la sala.
     * 
     * @param posX coordenada X
     * @param posY coordenada Y
     */
    public Cajero(Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Metodo llamado por el observable (Jugador) cuando cambia de estado.
     * Si el jugador esta en las mismas coordenadas que el cajero, se inicia la
     * interaccion.
     * 
     * @param obs el observable que notifica (debe ser instancia de Jugador)
     * @param obj objeto adicional (no usado)
     */
    @Override
    public void update(PullPushModelObservable obs, Object obj) {
        if (obs instanceof Jugador) {
            Jugador j = (Jugador) obs;
            // Comprueba si el jugador este en la posicion del cajero
            if (Objects.equals(j.getPosX(), posX) && Objects.equals(j.getPosY(), posY)) {
                interactATM();
                this.jugador = j;
                // Si el jugador ha pulsado la tecla de interactuar, iniciar menu
                if (j.getInteract()) {
                    interactive();
                }
            } else {
                // Si se aleja, dejamos de referenciar al jugador
                this.jugador = null;
            }
        }
    }

    /**
     * Inicia el modo interactivo del cajero, mostrando la interfaz y capturando
     * input.
     */
    @Override
    public void interactive() {
        Scanner input = new Scanner(System.in);
        interfaz = new ASCIICajero(jugador);
        iniciarCajero(input);
    }

    /**
     * Bucle principal de opciones: cambiar dinero a fichas, fichas a dinero o
     * salir.
     * 
     * @param input Scanner para leer la eleccion del usuario
     */
    private void iniciarCajero(Scanner input) {
        int opcion = 0;
        while (opcion != 3) {
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo(); // Muestra titulo ASCII
            interfaz.opcioes(); // Muestra opciones de menu
            try {
                opcion = input.nextInt();
                input.nextLine(); // Limpiar buffer de entrada
                switch (opcion) {
                    case 1:
                        cambiarDineroAFichas(input);
                        break;
                    case 2:
                        cambiarFichasADinero(input);
                        break;
                    case 3:
                        System.out.println("Saliendo del cajero...");
                        ASCIIGeneral.limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opcion no velida. Elige 1, 2 o 3.");
                        ASCIIGeneral.esperarTecla();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no velida. Usa un numero.");
                input.nextLine(); // limpiar buffer en caso de texto
                ASCIIGeneral.esperarTecla();
            }
        }
    }

    /**
     * Solicita al usuario un importe en euros, comprueba fondos y realiza el cambio
     * a fichas.
     * 
     * @param input Scanner para leer el importe
     */
    private void cambiarDineroAFichas(Scanner input) {
        float importe = definirImporteDinero(input);
        try {
            comprobarDinero();
            System.out.printf("Cambiando %.2f€ por fichas…%n", importe);
            // Restar dinero del jugador (usa Double) y redondeo natural de float a int
            jugador.restarDinero((double) importe);
            // Anade fichas (parte entera del importe)
            jugador.agregarFichas((int) importe);
            System.out.println("Operacion completada.");
        } catch (ExcepcionJugadorSinDinero e) {
            System.out.println("No tienes suficiente dinero.");
        }
        ASCIIGeneral.esperarTecla();
    }

    /**
     * Solicita al usuario cantidad de fichas, comprueba existencia y realiza el
     * cambio a dinero.
     * 
     * @param input Scanner para leer la cantidad de fichas
     */
    private void cambiarFichasADinero(Scanner input) {
        int cantidad = definirCantidadFichas(input);
        try {
            comprobarFichas();
            System.out.printf("Cambiando %d fichas por dinero…%n", cantidad);
            jugador.restarFichas(cantidad); // Quita fichas al jugador
            jugador.agregarDinero((double) cantidad); // Anade dinero en euros
            System.out.println("Operacion completada.");
        } catch (ExcepcionJugadorSinFichas e) {
            System.out.println("No tienes suficientes fichas.");
        }
        ASCIIGeneral.esperarTecla();
    }

    /**
     * Pide al usuario un numero de fichas > 0 (Repite hasta input velido).
     * 
     * @param input Scanner de entrada
     * @return cantidad de fichas
     */
    private int definirCantidadFichas(Scanner input) {
        int v = -1;
        while (v <= 0) {
            System.out.print("¿Cuentas fichas quieres cambiar? ");
            try {
                v = input.nextInt();
                if (v <= 0) {
                    System.out.println("Debe ser mayor que cero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no velida. Usa un numero entero.");
                input.nextLine(); // limpiar buffer
            }
        }
        input.nextLine(); // limpiar salto de linea restante
        return v;
    }

    /**
     * Pide al usuario un importe en euros > 0 (Repite hasta input velido).
     * 
     * @param input Scanner de entrada
     * @return importe en euros
     */
    private float definirImporteDinero(Scanner input) {
        float v = -1f;
        while (v <= 0) {
            System.out.print("¿Cuento dinero (€) quieres cambiar? ");
            try {
                v = input.nextFloat();
                if (v <= 0) {
                    System.out.println("Debe ser mayor que cero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no velida. Usa un numero (p.ej. 3.50).");
                input.nextLine();
            }
        }
        input.nextLine();
        return v;
    }

    /**
     * Comprueba que el jugador tiene fichas suficientes, lanza excepcion si no.
     * 
     * @throws ExcepcionJugadorSinFichas si fichas <= 0
     */
    private void comprobarFichas() throws ExcepcionJugadorSinFichas {
        if (jugador.getFichas() <= 0) {
            throw new ExcepcionJugadorSinFichas("Jugador sin fichas");
        }
    }

    /**
     * Comprueba que el jugador tiene dinero suficiente, lanza excepcion si no.
     * 
     * @throws ExcepcionJugadorSinDinero si dinero <= 0.0
     */
    private void comprobarDinero() throws ExcepcionJugadorSinDinero {
        if (jugador.getDinero() <= 0.0) {
            throw new ExcepcionJugadorSinDinero("Jugador sin dinero");
        }
    }
}
