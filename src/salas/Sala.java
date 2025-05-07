
package salas;

import acciones.Mesa;
import acciones.Pasillo;
import ascii.ASCIIGeneral;
import patterns.template.NewStageTemplate;
import personas.Jugador;
import patterns.observer.Subscription;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static recursos.MensajesEstaticos.*;

/**
 * Clase abstracta para representar una sala en el casino.
 * Gestiona el mapa ASCII, la posicion inicial del jugador, mesas y pasillos.
 * Implementa Subscription para suscripcion del jugador a eventos de movimiento e interaccion.
 */
public abstract class Sala extends NewStageTemplate implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    /** Mapa ASCII bidimensional de la sala */
    private Character[][] mapa;
    /** Jugador actualmente en la sala */
    private Jugador jugador;
    /** Mesas de juego disponibles en esta sala */
    protected ArrayList<Mesa> mesas;
    /** Pasillos que conectan con otras salas */
    protected ArrayList<Pasillo> pasillos;
    /** Coordenada X donde aparece el jugador al entrar */
    private final Integer posInitialX;
    /** Coordenada Y donde aparece el jugador al entrar */
    private final Integer posInitialY;

    /**
     * Constructor principal.
     * @param jugador jugador que entra en la sala
     * @param mapa representacion ASCII de la sala
     */
    protected Sala(Jugador jugador, Character[][] mapa) {
        this(jugador, mapa, new ArrayList<>(), new ArrayList<>(), 0, 0);
    }

    /**
     * Constructor con listado de mesas y pasillos.
     */
    protected Sala(Jugador jugador,
                   Character[][] mapa,
                   ArrayList<Mesa> mesas,
                   ArrayList<Pasillo> pasillos) {
        this(jugador, mapa, mesas, pasillos, 0, 0);
    }

    /**
     * Constructor completo con posicion inicial.
     */
    protected Sala(Jugador jugador,
                   Character[][] mapa,
                   ArrayList<Mesa> mesas,
                   ArrayList<Pasillo> pasillos,
                   Integer posInitialX,
                   Integer posInitialY) {
        this.jugador = jugador;
        this.mapa = mapa;
        this.mesas = mesas;
        this.pasillos = pasillos;
        this.posInitialX = posInitialX;
        this.posInitialY = posInitialY;
        // Si el jugador no tiene posicion, asignarle la inicial
        if (jugador != null && (jugador.getPosX() == null || jugador.getPosY() == null)) {
            jugador.setPosX(posInitialX);
            jugador.setPosY(posInitialY);
        }
    }

    /**
     * Dibuja la UI principal: datos del jugador, mapa y ayuda de controles.
     * @param jugador jugador actual
     */
    protected void interfazPrincipal(Jugador jugador) {
        // Mostrar panel de jugador
        playerUI(jugador);
        // Mostrar mapa ASCII con la posicion del jugador
        mostrarMapa();
        // Mostrar instrucciones de movimiento e interaccion
        instructions();
    }

    /**
     * Mueve al jugador, verifica colision con paredes ('#') y notifica observadores.
     */
    protected void moverJugador(int dx, int dy) {
        int newX = jugador.getPosX() + dx;
        int newY = jugador.getPosY() + dy;
        // Limitar dentro de los bordes y suelo transitable
        if (newX >= 0 && newX < mapa[0].length
                && newY >= 0 && newY < mapa.length
                && mapa[newY][newX] == ' ') {
            jugador.move(newX, newY);
        }
    }

    /**
     * Dibuja el mapa en consola, marcando la posicion del jugador con 'P'.
     */
    private void mostrarMapa() {
        for (int y = 0; y < mapa.length; y++) {
            for (int x = 0; x < mapa[y].length; x++) {
                if (y == jugador.getPosY() && x == jugador.getPosX()) {
                    System.out.print("P ");
                } else {
                    System.out.print(mapa[y][x] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Procesa la entrada de teclado (WASD, E, I) para mover o interactuar.
     */
    protected void entradaTerminal(Scanner scanner) {
        boolean valid = false;
        while (!valid) {
            try {
                String input = scanner.nextLine().trim().toLowerCase();
                valid = true;
                switch (input) {
                    case "w": moverJugador(0, -1); break;
                    case "s": moverJugador(0, 1); break;
                    case "a": moverJugador(-1, 0); break;
                    case "d": moverJugador(1, 0); break;
                    case "e":
                        // Interactuar con mesa o pasillo
                        if (jugador.getInventario()) {
                            jugador.usarItems();
                        } else {
                            jugador.interacting();
                        }
                        ASCIIGeneral.esperarTecla();
                        break;
                    case "i":
                        // Abrir inventario
                        ASCIIGeneral.limpiarPantalla();
                        jugador.setInvetario(true);
                        jugador.usarItems();
                        ASCIIGeneral.esperarTecla();
                        jugador.setInvetario(false);
                        break;
                    default:
                        valid = false;
                        badCommand();
                }
            } catch (InputMismatchException ex) {
                System.out.println("Entrada no velida. Por favor, usa WASD, E o I.");
                valid = false;
            }
        }
    }

    /**
     * Bucle principal que mantiene la interfaz viva hasta cerrar la aplicacion.
     */
    @Override
    public void iniciarInterfaz() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ASCIIGeneral.limpiarPantalla();
            interfazPrincipal(jugador);
            entradaTerminal(scanner);
        }
        // scanner.close(); // Normalmente no se cierra para no inhabilitar System.in
    }

    @Override
    public void enterNewStage(Jugador jugador) {
        // Adjunta al jugador como observador de la nueva sala
        this.setJugador(jugador);
        // Inicializa la interfaz grefica o ASCII de la sala
        this.iniciarInterfaz();
    }

    /** Establece el jugador conectado y se suscribe a los eventos de movimiento. */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        subscribe(jugador);
    }

    /** @return coordenada X inicial */
    public Integer getPosInitialX() {
        return posInitialX;
    }

    /** @return coordenada e Y inicial */
    public Integer getPosInitialY() {
        return posInitialY;
    }

    /** @return lista de pasillos conectados */
    public ArrayList<Pasillo> getPasillos() {
        return pasillos;
    }
}