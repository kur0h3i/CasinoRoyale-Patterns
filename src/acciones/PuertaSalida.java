
package acciones;

// Entradas/salidas y manejo de archivos
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// Utilidades de entrada
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

// Excepciones personalizadas
import excep.ExcepcionJugadorNoEncontrado;

// Observer pull-push para interacción
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserverInteractive;

// Representación del jugador
import personas.Jugador;

// Interfaces ASCII para la puerta y utilidades generales
import ascii.ASCIIPuerta;
import ascii.ASCIIGeneral;

import static recursos.MensajesEstaticos.interactSave;

/**
 * Clase PuertaSalida => Gestiona la interacción de salida del casino.
 * Cuando el jugador se acerca a la puerta, ofrece opciones para
 * guardar, cargar partida, salir del juego o volver al casino.
 * Implementa el patrón Observer para detectar posición e interacción del
 * jugador.
 */
public class PuertaSalida implements PullPushModelObserverInteractive {

    /** Coordenada X donde se activa la puerta de salida */
    private Integer posX;
    /** Coordenada Y donde se activa la puerta de salida */
    private Integer posY;
    /** Jugador que está interactuando con la puerta */
    private Jugador jugador;
    /** Interfaz ASCII específica para la puerta de salida */
    private ASCIIPuerta interfaz;

    /**
     * Constructor de PuertaSalida.
     *
     * @param posX coordenada X de activación
     * @param posY coordenada Y de activación
     */
    public PuertaSalida(Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Update del Observer: se llama cuando el jugador se mueve.
     * Si llega a las coordenadas de la puerta, muestra la interfaz y espera
     * interacción.
     *
     * @param obs observable (debe ser Jugador)
     * @param obj datos adicionales (no usados)
     */
    @Override
    public void update(PullPushModelObservable obs, Object obj) {
        if (obs instanceof Jugador) {
            Jugador j = (Jugador) obs;
            // Verifica posición de jugador versus puerta
            if (Objects.equals(j.getPosX(), posX) && Objects.equals(j.getPosY(), posY)) {
                // Mensaje de interacción
                interactSave();
                this.jugador = j;
                // Inicializa la interfaz de la puerta para este jugador
                this.interfaz = new ASCIIPuerta(jugador);
                if (j.getInteract()) {
                    interactive();
                }
            } else {
                // Limpia referencia si el jugador se aleja
                this.jugador = null;
            }
        }
    }

    /**
     * Inicia la modalidad interactiva: muestra el menú de la puerta.
     */
    @Override
    public void interactive() {
        Scanner input = new Scanner(System.in);
        iniciarPuerta(input);
    }

    /**
     * Bucle principal del menú de salida:
     * 1: guardar partida
     * 2: cargar partida
     * 3: salir del juego
     * 4: volver al casino
     *
     * @param input Scanner para leer la opción del usuario
     */
    private void iniciarPuerta(Scanner input) {
        int opcion = 0;
        while (opcion != 4) {
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            interfaz.opcioes();
            try {
                opcion = input.nextInt();
                input.nextLine();
                switch (opcion) {
                    case 1:
                        guardarPartida();
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 2:
                        cargarPartida();
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 3:
                        salir();
                        break;
                    case 4:
                        System.out.println("Volviendo al casino...");
                        ASCIIGeneral.limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        ASCIIGeneral.esperarTecla();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Introduce un número.");
                input.nextLine(); // limpia buffer
                ASCIIGeneral.esperarTecla();
            }
        }
    }

    /**
     * Guarda el objeto Jugador en un archivo dentro de 'saves/'.
     */
    private void guardarPartida() {
        File dir = new File("saves/");
        if (!dir.exists() && !dir.mkdirs()) {
            System.out.println("Error: no se pudo crear el directorio de guardado.");
            return;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("saves/" + jugador.getName() + ".dat"))) {
            oos.writeObject(jugador);
            System.out.println("Partida guardada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar partida: " + e.getMessage());
        }
    }

    /**
     * Carga una partida existente del nombre indicado y actualiza el jugador
     * actual.
     */
    private void cargarPartida() {
        Scanner input = new Scanner(System.in);
        System.out.print("Nombre del jugador a cargar: ");
        String nombre = input.nextLine();
        File file = new File("saves/" + nombre + ".dat");
        try {
            if (!file.exists()) {
                throw new ExcepcionJugadorNoEncontrado("Jugador '" + nombre + "' no encontrado.");
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Jugador cargado = (Jugador) ois.readObject();
                System.out.println("Partida cargada: " + cargado);
                jugador.actualizarDesde(cargado);
            }
        } catch (ExcepcionJugadorNoEncontrado e) {
            System.out.println(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar partida: " + e.getMessage());
        }
    }

    /**
     * Termina la aplicación.
     */
    private void salir() {
        System.out.println("Saliendo del juego...");
        System.exit(0);
    }
}
