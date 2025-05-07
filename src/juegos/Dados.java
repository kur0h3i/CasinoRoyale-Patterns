
package juegos;

// Interfaces ASCII para limpiar pantalla y mostrar resultados de dados
import ascii.ASCIIGeneral;
import ascii.ASCIIDados;

// Utilidades de entrada y control de errores
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// Excepción personalizada para falta de fichas
import excep.ExcepcionJugadorSinFichas;

// Representación del jugador
import personas.Jugador;

/**
 * Clase Dados => Estrategia de juego que implementa StrategyJuego.
 * El jugador apuesta fichas y lanza dos dados. Según la suma:
 * - 7 u 11: gana dobla la apuesta.
 * - 2, 3 o 12: pierde la apuesta.
 * - Otro valor: establece ese número como Punto y sigue lanzando hasta sacar
 * Punto (gana) o 7 (pierde).
 */
public class Dados implements StrategyJuego {

    /** Apuesta actual en fichas */
    private Integer apuesta;
    /** Jugador que participa en la partida */
    private Jugador jugador;
    /** Interfaz ASCII específica para Dados */
    private ASCIIDados interfaz;

    /**
     * Constructor: asigna el jugador e inicializa la interfaz.
     * 
     * @param jugador objeto Jugador que jugará la partida
     */
    public Dados(Jugador jugador) {
        this.jugador = jugador;
        this.interfaz = new ASCIIDados(jugador);
    }

    /**
     * Solicita al usuario la apuesta en fichas.
     * Valida que sea un entero positivo y no supere sus fichas disponibles.
     * En caso de entrada inválida, repite la petición.
     * 
     * @param input Scanner para leer la entrada del usuario
     * @return cantidad de fichas apostadas
     */
    public Integer definirApuesta(Scanner input) {
        System.out.println("¿Cuántas fichas deseas apostar?");
        System.out.println("Tienes " + jugador.getFichas() + " fichas disponibles.");
        Integer apuestaLocal = 0;
        try {
            apuestaLocal = input.nextInt();
            input.nextLine(); // limpiar buffer
            if (apuestaLocal <= 0 || apuestaLocal > jugador.getFichas()) {
                System.out.println("Apuesta no válida. Intenta de nuevo.");
                return definirApuesta(input);
            }
            // Descontar fichas del jugador
            jugador.restarFichas(apuestaLocal);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Intenta de nuevo.");
            input.nextLine();
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
     * Flujo principal de la partida de Dados.
     * Muestra menú para apostar, ver hoja de trucos o salir.
     * Lanza excepción si el jugador no tiene fichas.
     * 
     * @throws ExcepcionJugadorSinFichas si no hay fichas para jugar
     */
    @Override
    public void iniciarPartida() throws ExcepcionJugadorSinFichas {
        Scanner input = new Scanner(System.in);
        // Verificar que haya fichas disponibles
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
                        // Apostar y jugar una ronda de dados
                        apuesta = definirApuesta(input);
                        jugarDados(input);
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 2:
                        // Mostrar hoja de trucos de resultados posibles
                        interfaz.cheatsheet();
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 3:
                        // Salir del juego de dados
                        continuar = false;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Intenta de nuevo.");
                input.nextLine();
            }
        }
    }

    /**
     * Ejecuta una ronda: lanza dados y evalúa suma inicial.
     * 
     * @param input Scanner para leer pausas de usuario
     */
    private void jugarDados(Scanner input) {
        Random rand = new Random();
        Integer resultado = tirarDados(rand);
        if (resultado == 7 || resultado == 11) {
            System.out.println("¡Has sacado " + resultado + "! ¡Ganaste!");
            jugador.agregarFichas(apuesta * 2);
        } else if (resultado == 2 || resultado == 3 || resultado == 12) {
            System.out.println("¡Has sacado " + resultado + "! ¡Perdiste!");
        } else {
            System.out.println("Has sacado " + resultado + ". Este es tu Punto.");
            jugarPunto(input, resultado, rand);
        }
    }

    /**
     * Procede al juego de Punto: el jugador lanza hasta repetir Punto (gana)
     * o sacar 7 (pierde).
     * 
     * @param input  Scanner para leer pausas de usuario
     * @param punto  valor definido como Punto
     * @param random instancia de Random para generar tiradas
     */
    private void jugarPunto(Scanner input, Integer punto, Random random) {
        System.out.println("Tira los dados nuevamente para intentar obtener tu Punto: " + punto + ".");
        System.out.println("Si sacas un 7 antes del Punto, pierdes.");
        while (true) {
            System.out.println("Presiona Enter para tirar los dados...");
            input.nextLine();
            Integer resultado = tirarDados(random);
            if (resultado.equals(punto)) {
                System.out.println("¡Has sacado tu Punto! ¡Ganaste!");
                jugador.agregarFichas(apuesta * 2);
                break;
            } else if (resultado.equals(7)) {
                System.out.println("¡Has sacado un 7! ¡Perdiste!");
                break;
            } else {
                System.out.println("Has sacado " + resultado + ". Sigue intentando.");
            }
        }
    }

    /**
     * Lanza dos dados, muestra resultado y retorna la suma.
     * 
     * @param random instancia de Random
     * @return suma de ambos dados (2–12)
     */
    private Integer tirarDados(Random random) {
        Integer d1 = random.nextInt(6) + 1;
        Integer d2 = random.nextInt(6) + 1;
        Integer suma = d1 + d2;
        interfaz.mostrarResultadoDados(d1, d2, suma);
        return suma;
    }

    /**
     * Representación textual de la estrategia para mostrar en menús.
     * 
     * @return "Dados"
     */
    @Override
    public String toString() {
        return "Dados";
    }
}
