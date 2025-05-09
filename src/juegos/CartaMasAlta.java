
package juegos;

// Interfaces ASCII para limpiar pantalla y mostrar opciones de CartaMasAlta
import ascii.ASCIIGeneral;
import ascii.ASCIICartaMasAlta;

import java.io.Serial;
import java.io.Serializable;
// Utilidades de entrada y manejo de excepciones de usuario
import java.util.InputMismatchException;
import java.util.Scanner;

// Recursos de baraja y cartas
import recursos.Baraja;
import recursos.Carta;

// Representacion del jugador
import personas.Jugador;

// Excepcion para falta de fichas
import excep.ExcepcionJugadorSinFichas;

/**
 * Estrategia de juego que implementa StrategyJuego.
 * El jugador apuesta fichas, se reparte una carta al jugador y otra a la IA,
 * y quien tenga el valor mes alto gana la apuesta.
 */
public class CartaMasAlta implements StrategyJuego, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Mazo de cartas para repartir */
    private Baraja baraja;
    /** Interfaz ASCII especifica para CartaMasAlta */
    private ASCIICartaMasAlta interfaz;
    /** Jugador que participa en la partida */
    private Jugador jugador;
    /** Apuesta actual en fichas */
    private Integer apuesta;

    /**
     * Constructor: inicializa baraja, interfaz y asigna el jugador.
     * 
     * @param jugador objeto Jugador que jugare la partida
     */
    public CartaMasAlta(Jugador jugador) {
        this.jugador = jugador;
        this.baraja = new Baraja();
        this.interfaz = new ASCIICartaMasAlta(jugador);
    }

    /**
     * Solicita al usuario la apuesta en fichas.
     * Valida que sea un entero positivo y no supere sus fichas disponibles.
     * En caso de entrada invelida o apuesta no velida, repite la peticion.
     * 
     * @param input Scanner para leer la entrada del usuario
     * @return cantidad de fichas apostadas
     */
    public Integer definirApuesta(Scanner input) {
        System.out.println("¿Cuentas fichas deseas apostar?");
        System.out.println("Tienes " + jugador.getFichas() + " fichas disponibles.");
        Integer apuestaLocal = 0;
        try {
            apuestaLocal = input.nextInt();
            input.nextLine(); // limpiar buffer
            if (apuestaLocal <= 0 || apuestaLocal > jugador.getFichas()) {
                System.out.println("Apuesta no velida. Intenta de nuevo.");
                return definirApuesta(input);
            }
            // Descontar fichas del jugador
            jugador.restarFichas(apuestaLocal);
        } catch (InputMismatchException e) {
            System.out.println("Entrada invelida. Intenta de nuevo.");
            input.nextLine(); // limpiar buffer en caso de texto
            return definirApuesta(input);
        }
        return apuestaLocal;
    }

    /**
     * Comprueba que el jugador tenga al menos una ficha antes de iniciar partida.
     * 
     * @throws ExcepcionJugadorSinFichas si el jugador no tiene fichas suficientes
     */
    public void comprobarFichas() throws ExcepcionJugadorSinFichas {
        if (jugador.getFichas() <= 0) {
            throw new ExcepcionJugadorSinFichas("Jugador sin fichas");
        }
    }

    /**
     * Flujo principal de la partida CartaMesAlta.
     * Muestra menu para apostar, ver hoja de referencia o salir.
     * Lanza excepcion si el jugador no tiene fichas.
     * 
     * @throws ExcepcionJugadorSinFichas si no hay fichas para jugar
     */
    @Override
    public void iniciarPartida() throws ExcepcionJugadorSinFichas {
        Scanner input = new Scanner(System.in);
        // Verificar que haya fichas para jugar
        comprobarFichas();
        boolean continuar = true;
        while (continuar) {
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            interfaz.opciones();
            try {
                Integer opcion = input.nextInt();
                input.nextLine();
                switch (opcion) {
                    case 1:
                        // Apostar y jugar una ronda
                        ASCIIGeneral.limpiarPantalla();
                        interfaz.titulo();
                        apuesta = definirApuesta(input);
                        jugarRonda(apuesta);
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 2:
                        // Mostrar hoja de trucos (valores de cartas)
                        ASCIIGeneral.limpiarPantalla();
                        interfaz.titulo();
                        interfaz.cheatsheet();
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 3:
                        // Salir del juego
                        continuar = false;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion no velida. Intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada invelida. Intenta de nuevo.");
                input.nextLine();
            }
        }
    }

    /**
     * Ejecuta una ronda: reparte una carta al jugador y otra a la IA,
     * compara valores y asigna fichas segun el resultado.
     * 
     * @param apuesta cantidad de fichas apostadas por el jugador
     */
    private void jugarRonda(Integer apuesta) {
        // Barajar antes de repartir
        baraja.mezclar();
        Carta cartaJugador = baraja.repartir();
        Carta cartaIA = baraja.repartir();
        System.out.println("Tu carta: " + cartaJugador);
        System.out.println("Carta de la IA: " + cartaIA);
        int valorJugador = cartaJugador.getValorNumerico();
        int valorIA = cartaIA.getValorNumerico();
        if (valorJugador > valorIA) {
            System.out.println("¡Has ganado esta ronda con " + cartaJugador + "!");
            // Gana doble la apuesta
            jugador.agregarFichas(apuesta * 2);
        } else if (valorJugador < valorIA) {
            System.out.println("La IA gana esta ronda con " + cartaIA + ".");
        } else {
            // Empate: devuelve la apuesta
            System.out.println("¡Empate! Ambas cartas son iguales.");
            jugador.agregarFichas(apuesta);
        }
    }

    /**
     * Representacion textual de la estrategia para mostrar en menus.
     * 
     * @return "CartaMasAlta"
     */
    @Override
    public String toString() {
        return "CartaMasAlta";
    }
}
