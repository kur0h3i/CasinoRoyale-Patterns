package acciones;

import ascii.ASCIIFenrir;
import ascii.ASCIIGeneral;
import excep.ExcepcionJugadorSinDinero;
import items.Bebida;
import items.Items;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserverInteractive;
import personas.Jugador;
import static recursos.MensajesEstaticos.interactFenrir;

public class Fenrir implements PullPushModelObserverInteractive {

    private Integer posX, posY;
    private Jugador jugador;
    private List<Items> bebidasDisponibles = new ArrayList<>();
    private ASCIIFenrir interfaz;

    public Fenrir(Integer posX, Integer posY) {
        // Inicializa la carta de bebidas
        bebidasDisponibles.add(new Bebida("Cerveza", 2.50, "Bien fría"));
        bebidasDisponibles.add(new Bebida("Refresco",1.50, "Dulce y burbujeante"));
        bebidasDisponibles.add(new Bebida("Copa de vino", 3.00, "Tinto de la casa"));
        bebidasDisponibles.add(new Bebida("Redbull", 1.59, "Te da alas"));

        // posiciones
        this.posX = posX;
        this.posY = posY;

        interfaz = new ASCIIFenrir();
    }

    public void iniciarBar(Scanner input) {
        int opcion = 0;
        while (opcion != 2) {
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            interfaz.opcionesBar();
            try {
                opcion = input.nextInt(); 
                input.nextLine();
                switch (opcion) {
                    case 1:
                        comprarBebidaConDinero(input);
                        break;
                    case 2:
                        System.out.println("Saliendo del bar...");
                        ASCIIGeneral.limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opción no válida. Elige 1 o 2.");
                        ASCIIGeneral.esperarTecla();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Usa números.");
                input.nextLine();  // limpiar buffer
                ASCIIGeneral.esperarTecla();
            }
        }
    }

    private void comprarBebidaConDinero(Scanner input) {
        interfaz.mostrarMenuBebidas(bebidasDisponibles);
        try {
            Integer id = input.nextInt();
            input.nextLine();
            if (id < 1 || id > bebidasDisponibles.size()) {
                System.out.println("Selección inválida.");
                ASCIIGeneral.esperarTecla();
                return;
            }
            Items bebida = bebidasDisponibles.get(id - 1);
            procesarCompraDinero(bebida);
        } catch (InputMismatchException e) {
            System.out.println("Debe introducir un número.");
            input.nextLine();
            ASCIIGeneral.esperarTecla();
        }
    }

    private void procesarCompraDinero(Items bebida) {
        try {
            Double precio = bebida.getPrecio();
            if (jugador.getDinero() < precio) {
                throw new ExcepcionJugadorSinDinero("No tienes dinero");
            }
            jugador.restarDinero(precio);
            jugador.agregarItem(bebida);
            System.out.println("Has comprado " + bebida.getNombre() + " pagando con €" + precio);
        } catch (ExcepcionJugadorSinDinero e) {
            System.out.println("No tienes suficiente dinero para " + bebida.getNombre());
        }
        ASCIIGeneral.esperarTecla();
    }

     @Override
    public void update(PullPushModelObservable obs, Object obj) {
        if (obs instanceof Jugador) {
            Jugador j = (Jugador) obs;
            if (Objects.equals(j.getPosX(), posX) && Objects.equals(j.getPosY(), posY)) {
                this.jugador = j;
                interactFenrir();
                if (j.getInteract()) interactive();
            } else {
                this.jugador = null;
            }
        }
    }

    @Override
    public void interactive() {
        Scanner input = new Scanner(System.in);
        iniciarBar(input);
    }
}
