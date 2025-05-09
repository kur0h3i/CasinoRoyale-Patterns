
package acciones;

// Entradas/salidas y manejo de archivos
import java.io.*;

// Utilidades de entrada
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

// Excepciones personalizadas
import excep.ExcepcionJugadorNoEncontrado;

// Observer pull-push para interaccion
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserverInteractive;

// Representacion del jugador
import personas.Jugador;

// Interfaces ASCII para la puerta y utilidades generales
import ascii.ASCIIPuerta;
import ascii.ASCIIGeneral;

import static recursos.MensajesEstaticos.interactSave;

/**
 * Gestiona la interaccion de salida del casino.
 * Cuando el jugador se acerca a la puerta, ofrece opciones para
 * guardar, cargar partida, salir del juego o volver al casino.
 * Implementa el patron Observer para detectar posicion e interaccion del
 * jugador.
 */
public class PuertaSalida implements PullPushModelObserverInteractive, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Coordenada X donde se activa la puerta de salida */
    private Integer posX;
    /** Coordenada Y donde se activa la puerta de salida */
    private Integer posY;
    /** Jugador que este interactuando con la puerta */
    private Jugador jugador;
    /** Interfaz ASCII especifica para la puerta de salida */
    private ASCIIPuerta interfaz;

    /**
     * Constructor de PuertaSalida.
     *
     * @param posX coordenada X de activacion
     * @param posY coordenada Y de activacion
     */
    public PuertaSalida(Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Update del Observer: se llama cuando el jugador se mueve.
     * Si llega a las coordenadas de la puerta, muestra la interfaz y espera
     * interaccion.
     *
     * @param obs observable (debe ser Jugador)
     * @param obj datos adicionales (no usados)
     */
    @Override
    public void update(PullPushModelObservable obs, Object obj) {
        if (obs instanceof Jugador) {
            Jugador j = (Jugador) obs;
            // Verifica posicion de jugador versus puerta
            if (Objects.equals(j.getPosX(), posX) && Objects.equals(j.getPosY(), posY)) {
                // Mensaje de interaccion
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
     * Inicia la modalidad interactiva: muestra el menu de la puerta.
     */
    @Override
    public void interactive() {
        Scanner input = new Scanner(System.in);
        iniciarPuerta(input);
    }

    /**
     * Bucle principal del menu de salida:
     * 1: guardar partida
     * 2: cargar partida
     * 3: salir del juego
     * 4: volver al casino
     *
     * @param input Scanner para leer la opcion del usuario
     */
    private void iniciarPuerta(Scanner input) {
        Integer opcion = 0;
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
                        System.out.println("Opcion no velida.");
                        ASCIIGeneral.esperarTecla();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no velida. Introduce un numero.");
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
     * Termina la aplicacion.
     */
    private void salir() {
        System.out.println("Saliendo del juego...");
        System.exit(0);
    }
}
