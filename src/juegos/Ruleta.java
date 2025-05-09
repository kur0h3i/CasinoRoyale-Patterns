
package juegos;

// Interfaces ASCII para limpieza de pantalla y la ruleta
import ascii.ASCIIGeneral;
import ascii.ASCIIRuleta;

import java.io.Serial;
import java.io.Serializable;
// Utilidades de entrada y aleatoriedad
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// Excepcion personalizada para falta de fichas
import excep.ExcepcionJugadorSinFichas;

// Representacion del jugador
import personas.Jugador;

/**
 * Estrategia de juego que implementa StrategyJuego.
 * El jugador apuesta fichas y puede elegir entre varias opciones de apuesta:
 * - Color (Rojo/Negro)
 * - Par/Impar
 * - Numero exacto (0-36)
 * - Docena (1-12, 13-24, 25-36)
 * - Mitad (1-18, 19-36)
 * Luego se gira la ruleta y se determina el resultado, ajustando fichas segun
 * el tipo de apuesta.
 */
public class Ruleta implements StrategyJuego, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Apuesta realizada por el jugador */
    private Integer apuesta;
    /** Jugador participante en la partida */
    private Jugador jugador;
    /** Interfaz ASCII especifica para mostrar la ruleta */
    private ASCIIRuleta interfaz;

    /**
     * Constructor: inicializa la interfaz y asigna el jugador.
     * 
     * @param jugador instancia de Jugador que jugare
     */
    public Ruleta(Jugador jugador) {
        this.jugador = jugador;
        this.interfaz = new ASCIIRuleta(jugador);
    }

    /**
     * Comprueba que el jugador tenga al menos una ficha antes de iniciar partida.
     * 
     * @throws ExcepcionJugadorSinFichas si no hay fichas suficientes
     */
    private void comprobarFichas() throws ExcepcionJugadorSinFichas {
        if (jugador.getFichas() <= 0) {
            throw new ExcepcionJugadorSinFichas("Jugador sin fichas");
        }
    }

    /**
     * Flujo principal de la partida de Ruleta.
     * Muestra el menu inicial y gestiona la interaccion del usuario.
     * 
     * @throws ExcepcionJugadorSinFichas si el jugador no tiene fichas para apostar
     */
    @Override
    public void iniciarPartida() throws ExcepcionJugadorSinFichas {
        Scanner input = new Scanner(System.in);
        // Validar fichas antes de comenzar
        comprobarFichas();
        Integer opcion = 0;
        while (opcion != 4) {
            // Mostrar interfaz principal de ruleta
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            interfaz.interfazRuleta();
            interfaz.opciones();
            try {
                opcion = input.nextInt();
                input.nextLine(); // limpiar buffer
                switch (opcion) {
                    case 1:
                        // Definir apuesta y elegir tipo
                        ASCIIGeneral.limpiarPantalla();
                        interfaz.titulo();
                        apuesta = definirApuesta(input);
                        opcionesDeApuesta(input);
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 2:
                        // Girar ruleta sin apostar
                        ASCIIGeneral.limpiarPantalla();
                        interfaz.titulo();
                        Integer resultado = tirarRuleta();
                        System.out.println("Resultado: " + resultado);
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 3:
                        // Mostrar hoja de trucos
                        ASCIIGeneral.limpiarPantalla();
                        interfaz.titulo();
                        interfaz.cheetsheet();
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 4:
                        // Salir de ruleta
                        System.out.println("Saliendo de la ruleta...");
                        ASCIIGeneral.limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opcion no velida. Intenta de nuevo.");
                        ASCIIGeneral.esperarTecla();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada invelida. Introduce un numero.");
                input.nextLine();
                ASCIIGeneral.esperarTecla();
            }
        }
    }

    /**
     * Solicita al usuario la cantidad de fichas a apostar.
     * Valida que sea positiva y no supere las disponibles.
     * 
     * @param input Scanner para leer la entrada del usuario
     * @return numero de fichas apostadas
     */
    private Integer definirApuesta(Scanner input) {
        System.out.println("¿Cuentas fichas deseas apostar?");
        System.out.println("Tienes " + jugador.getFichas() + " fichas disponibles.");
        Integer ap = 0;
        try {
            ap = input.nextInt();
            input.nextLine();
            if (ap <= 0 || ap > jugador.getFichas()) {
                System.out.println("Apuesta no velida. Intenta de nuevo.");
                return definirApuesta(input);
            }
            // Descontar fichas
            jugador.restarFichas(ap);
        } catch (InputMismatchException e) {
            System.out.println("Entrada invelida. Intenta de nuevo.");
            input.nextLine();
            return definirApuesta(input);
        }
        return ap;
    }

    /**
     * Muestra el submenu de tipos de apuesta y procesa la seleccion.
     * 
     * @param input Scanner para leer la entrada del usuario
     */
    private void opcionesDeApuesta(Scanner input) {
        Integer opcion = 0;
        while (opcion != 6) {
            ASCIIGeneral.limpiarPantalla();
            interfaz.interfazPartida();
            System.out.print("Elige una apuesta (1-6): ");
            try {
                opcion = input.nextInt();
                input.nextLine();
                switch (opcion) {
                    case 1:
                        apostarPorColor(input);
                        break;
                    case 2:
                        apostarParImpar(input);
                        break;
                    case 3:
                        apostarPorNumero(input);
                        break;
                    case 4:
                        apostarPorDocena(input);
                        break;
                    case 5:
                        apostarMitad(input);
                        break;
                    case 6:
                        System.out.println("Volviendo al menu principal...");
                        break;
                    default:
                        System.out.println("Opcion no velida.");
                }
                if (opcion >= 1 && opcion <= 5)
                    ASCIIGeneral.esperarTecla();
            } catch (InputMismatchException e) {
                System.out.println("Entrada invelida. Intenta de nuevo.");
                input.nextLine();
                ASCIIGeneral.esperarTecla();
            }
        }
    }

    /**
     * Gira la ruleta y retorna un numero entre 0 y 36.
     * 
     * @return resultado de la ruleta
     */
    private Integer tirarRuleta() {
        Random rand = new Random();
        Integer res = rand.nextInt(37);
        System.out.println("La ruleta gira... Numero: " + res);
        return res;
    }

    /**
     * Apuesta por color (Rojo/Negro).
     * 
     * @param input Scanner para leer la eleccion del usuario
     */
    private void apostarPorColor(Scanner input) {
        System.out.print("Elige color: 1=Rojo, 2=Negro: ");
        Integer color = input.nextInt();
        Integer res = tirarRuleta();
        Boolean esRojo = esNumeroRojo(res);
        if ((color == 1 && esRojo) || (color == 2 && !esRojo)) {
            System.out.println("¡Ganaste! Premiacion 1:1.");
            jugador.agregarFichas(apuesta * 2);
        } else {
            System.out.println("Perdiste la apuesta por color.");
        }
    }

    /**
     * Apuesta por par o impar.
     * 
     * @param input Scanner para leer la eleccion del usuario
     */
    private void apostarParImpar(Scanner input) {
        System.out.print("Elige: 1=Par, 2=Impar: ");
        Integer elec = input.nextInt();
        Integer res = tirarRuleta();
        Boolean esPar = (res != 0 && res % 2 == 0);
        if ((elec == 1 && esPar) || (elec == 2 && !esPar)) {
            System.out.println("¡Ganaste! Premiacion 1:1.");
            jugador.agregarFichas(apuesta * 2);
        } else {
            System.out.println("Perdiste la apuesta par/impar.");
        }
    }

    /**
     * Apuesta por un numero exacto (0-36).
     * 
     * @param input Scanner para leer el numero apostado
     */
    private void apostarPorNumero(Scanner input) {
        System.out.print("Elige un numero (0-36): ");
        Integer num = input.nextInt();
        if (num < 0 || num > 36) {
            System.out.println("Numero invelido.");
            return;
        }
        Integer res = tirarRuleta();
        if (res == num) {
            System.out.println("¡Ganaste! Premiacion 35:1.");
            jugador.agregarFichas(apuesta * 36);
        } else {
            System.out.println("Perdiste la apuesta por numero.");
        }
    }

    /**
     * Apuesta por docena (1-12, 13-24, 25-36).
     * 
     * @param input Scanner para leer la eleccion del usuario
     */
    private void apostarPorDocena(Scanner input) {
        System.out.print("Elige docena: 1=(1-12), 2=(13-24), 3=(25-36): ");
        Integer doc = input.nextInt();
        if (doc < 1 || doc > 3) {
            System.out.println("Docena invelida.");
            return;
        }
        Integer res = tirarRuleta();
        Boolean acierto = (doc == 1 && res <= 12) || (doc == 2 && res <= 24 && res >= 13)
                || (doc == 3 && res <= 36 && res >= 25);
        if (acierto) {
            System.out.println("¡Ganaste! Premiacion 2:1.");
            jugador.agregarFichas(apuesta * 3);
        } else {
            System.out.println("Perdiste la apuesta por docena.");
        }
    }

    /**
     * Apuesta por mitad (1-18 o 19-36).
     * 
     * @param input Scanner para leer la eleccion del usuario
     */
    private void apostarMitad(Scanner input) {
        System.out.print("Elige mitad: 1=(1-18), 2=(19-36): ");
        Integer mitad = input.nextInt();
        if (mitad != 1 && mitad != 2) {
            System.out.println("Mitad invelida.");
            return;
        }
        Integer res = tirarRuleta();
        Boolean acierto = (mitad == 1 && res <= 18 && res >= 1) || (mitad == 2 && res <= 36 && res >= 19);
        if (acierto) {
            System.out.println("¡Ganaste! Premiacion 1:1.");
            jugador.agregarFichas(apuesta * 2);
        } else {
            System.out.println("Perdiste la apuesta por mitad.");
        }
    }

    /**
     * Determina si un numero de ruleta es rojo segun la lista estendar.
     * 
     * @param numero numero de la ruleta
     * @return true si es rojo, false si es negro o 0
     */
    private Boolean esNumeroRojo(Integer numero) {
        Integer[] rojos = { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };
        for (Integer r : rojos) {
            if (numero == r)
                return true;
        }
        return false;
    }

    /**
     * Representacion textual de la estrategia para mostrar en menus.
     * 
     * @return "Ruleta"
     */
    @Override
    public String toString() {
        return "Ruleta";
    }
}
