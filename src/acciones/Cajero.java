package acciones;

// Excepciones
import ascii.ASCIICajero;
import ascii.ASCIIGeneral;
import excep.ExcepcionJugadorSinDinero;
import excep.ExcepcionJugadorSinFichas;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserverInteractive;
import personas.FondosDecorator;
import personas.Jugador;
import static recursos.MensajesEstaticos.interactATM;

public class Cajero implements PullPushModelObserverInteractive {

    // Coordenadas en sala
    private Integer posX, posY;

    // El jugador y la interfaz ASCII
    private Jugador jugador;
    private ASCIICajero interfaz;

    public Cajero(Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void update(PullPushModelObservable obs, Object obj) {
        if (obs instanceof Jugador) {
            Jugador j = (Jugador) obs;
            if (Objects.equals(j.getPosX(), posX) && Objects.equals(j.getPosY(), posY)) {
                interactATM();
                this.jugador = j;
                if (j.getInteract())
                    interactive();
            } else {
                this.jugador = null;
            }
        }
    }

    @Override
    public void interactive() {
        Scanner input = new Scanner(System.in);
        interfaz = new ASCIICajero(jugador);
        iniciarCajero(input);
    }

    private void iniciarCajero(Scanner input) {
        int opcion = 0;
        while (opcion != 3) {
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            interfaz.opcioes();
            try {
                opcion = input.nextInt();
                input.nextLine();
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
                        System.out.println("Opción no válida. Elige 1, 2 o 3.");
                        ASCIIGeneral.esperarTecla();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Usa un número.");
                input.nextLine(); // limpiar buffer
                ASCIIGeneral.esperarTecla();
            }
        }
    }

    // Métodos de actualización de fondos
    private void cambiarDineroAFichas(Scanner input) {
        try {
            comprobarDinero();
            double cantidad = definirImporteDinero(input);
            if (jugador instanceof FondosDecorator) {
                FondosDecorator jugadorConFondos = (FondosDecorator) jugador;
                jugadorConFondos.restarDinero(cantidad); // Restamos dinero
                jugadorConFondos.agregarFichas(cantidad); // Añadimos fichas
                System.out.println("Operación completada.");
            }
        } catch (ExcepcionJugadorSinDinero e) {
            System.out.println(e.getMessage());
        }
        ASCIIGeneral.esperarTecla();
    }

    private void cambiarFichasADinero(Scanner input) {
        try {
            comprobarFichas();
            double cantidad = definirCantidadFichas(input);
            if (jugador instanceof FondosDecorator) {
                FondosDecorator jugadorConFondos = (FondosDecorator) jugador;
                jugadorConFondos.restarFichas(cantidad); // Restamos fichas
                jugadorConFondos.agregarDinero(cantidad); // Añadimos dinero
                System.out.println("Operación completada.");
            }
        } catch (ExcepcionJugadorSinFichas e) {
            System.out.println(e.getMessage());
        }
        ASCIIGeneral.esperarTecla();
    }

    // Solo acepta ints > 0
    private int definirCantidadFichas(Scanner input) {
        int v = -1;
        while (v <= 0) {
            System.out.print("¿Cuántas fichas quieres cambiar? ");
            try {
                v = input.nextInt();
                if (v <= 0) {
                    System.out.println("Debe ser mayor que cero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Usa un número entero.");
                input.nextLine();
            }
        }
        input.nextLine();
        return v;
    }

    // Solo acepta floats > 0
    private float definirImporteDinero(Scanner input) {
        float v = -1f;
        while (v <= 0) {
            System.out.print("¿Cuánto dinero (€) quieres cambiar? ");
            try {
                v = input.nextFloat();
                if (v <= 0) {
                    System.out.println("Debe ser mayor que cero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Usa un número (p.ej. 3.50).");
                input.nextLine();
            }
        }
        input.nextLine();
        return v;
    }

    private void comprobarFichas() throws ExcepcionJugadorSinFichas {
        if (jugador.getFichas() <= 0) {
            throw new ExcepcionJugadorSinFichas("Jugador sin fichas");
        }
    }

    private void comprobarDinero() throws ExcepcionJugadorSinDinero {
        if (jugador.getDinero() <= 0.0) {
            throw new ExcepcionJugadorSinDinero("Jugador sin dinero");
        }
    }
}
