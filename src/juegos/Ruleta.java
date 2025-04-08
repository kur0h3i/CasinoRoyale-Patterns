// Ruelta.java
package juegos;

// Util
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// Jugador
import personas.Jugador;

// Excepcion
import excep.ExcepcionJugadorSinFichas;

// ASCII
import ascii.ASCIIRuleta;
import ascii.ASCIIGeneral;

public class Ruleta extends Juego {

    // Atributos
    private int apuesta; 
    private Jugador jugador; // Jugador participando en la partida
    ASCIIRuleta interfaz;

    // Constructor
    public Ruleta(Jugador jugador) {
        super(jugador);
        this.jugador = jugador;
        this.interfaz = new ASCIIRuleta(jugador);
    }


    // Metodos
    @Override
    public void iniciarPartida() throws ExcepcionJugadorSinFichas{
        Scanner input = new Scanner(System.in); 
        comprobarfichas();
        menuPartida(input);       
    }
    

    // Menu partida
    private void menuPartida(Scanner input){
        int opcion = 0;
        while (opcion != 4) {
            // Monstrar Interfaz
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            interfaz.interfazRuleta();
            interfaz.opciones();

            try {
                opcion = input.nextInt();
                input.nextLine(); // Limpiar el buffer

                // Opciones del menú principal
                switch (opcion) {
                    case 1:
                        ASCIIGeneral.limpiarPantalla();
                        interfaz.titulo();
                        apuesta = definirApuesta(input); 
                        opcionesDeApuesta(input);
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 2:
                        ASCIIGeneral.limpiarPantalla();
                        interfaz.titulo();
                        tirarRuleta();
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 3:
                        ASCIIGeneral.limpiarPantalla();
                        interfaz.titulo();
                        interfaz.cheetsheet();
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 4:
                        System.out.println("Saliendo del juego...");
                        ASCIIGeneral.limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intenta de nuevo.");
                // Limpiar Buffer
                input.nextLine(); 
            }
        }
    }
    

    public int tirarRuleta() {
        Random random = new Random();
        int resultado = random.nextInt(37);
        System.out.println("La ruleta gira... El número es: " + resultado);
        return resultado;
    }

    public void opcionesDeApuesta(Scanner input) {
        int opcion = 0;
        ASCIIGeneral.limpiarPantalla();
        while (opcion != 6) {
            //interaz partida
            interfaz.interfazPartida();
            System.out.print("Elige una opción: ");
            try {
                opcion = input.nextInt();

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
                        System.out.println("Saliendo de las opciones de apuesta...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intenta de nuevo.");
                input.nextLine();
            }
        }
    }

    // Saber si un numero es rojo
    private boolean esNumeroRojo(int numero) {
        int[] rojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        for (int rojo : rojos) {
            if (numero == rojo) {
                return true;
            }
        }
        return false;
    }

    // Apuesta por Color
    public void apostarPorColor(Scanner input) {
        System.out.println("Elige un color: 1 para Rojo, 2 para Negro:");
        int color = input.nextInt();
    
        int resultado = tirarRuleta();
        boolean esRojo = esNumeroRojo(resultado);
    
        if ((color == 1 && esRojo) || (color == 2 && !esRojo)) {
            System.out.println("¡Felicidades! Ganaste x1 tu apuesta.");
            jugador.agregarFichas(apuesta * 2); // Ganancia: la apuesta inicial más el premio
        } else {
            System.out.println("Lo siento, perdiste.");
        }
    }
    
    // Apuesta Por Par/Impar
    public void apostarParImpar(Scanner input) {
        System.out.println("Elige: 1 para Par, 2 para Impar:");
        int eleccion = input.nextInt();
    
        int resultado = tirarRuleta();
    
        boolean esPar = (resultado != 0 && resultado % 2 == 0);
        if ((eleccion == 1 && esPar) || (eleccion == 2 && !esPar)) {
            System.out.println("¡Felicidades! Ganaste x1 tu apuesta.");
            jugador.agregarFichas(apuesta * 2);
        } else {
            System.out.println("Lo siento, perdiste.");
        }
    }
    
    // Apuesta por un numero
    public void apostarPorNumero(Scanner input) {
        System.out.println("Elige un número entre 0 y 36:");
        int numeroApostado = input.nextInt();
    
        if (numeroApostado < 0 || numeroApostado > 36) {
            System.out.println("Número inválido. Intenta de nuevo.");
            return;
        }
    
        int resultado = tirarRuleta();
    
        if (numeroApostado == resultado) {
            System.out.println("¡Felicidades! Ganaste x35 tu apuesta!");
            jugador.agregarFichas(apuesta * 36);
        } else {
            System.out.println("Lo siento, perdiste.");
        }
    }

    // Apuesta por Docena
    public void apostarPorDocena(Scanner input) {
    
        System.out.println("Elige una docena: 1 (1-12), 2 (13-24), o 3 (25-36):");
        int docena = input.nextInt();
    
        if (docena < 1 || docena > 3) {
            System.out.println("Docena inválida. Intenta de nuevo.");
            return;
        }
    
        int resultado = tirarRuleta();
    
        if ((docena == 1 && resultado >= 1 && resultado <= 12) ||
            (docena == 2 && resultado >= 13 && resultado <= 24) ||
            (docena == 3 && resultado >= 25 && resultado <= 36)) {
            System.out.println("¡Felicidades! Ganaste x2 tu apuesta.");
            jugador.agregarFichas(apuesta * 3); // Ganancia: apuesta inicial más premio
        } else {
            System.out.println("Lo siento, perdiste.");
        }
    }
    
    
    // Apuesta por Mitad
    public void apostarMitad(Scanner input) {
    
        System.out.println("Elige una mitad: 1 (1-18) o 2 (19-36):");
        int mitad = input.nextInt();
    
        if (mitad != 1 && mitad != 2) {
            System.out.println("Mitad inválida. Intenta de nuevo.");
            return;
        }
    
        int resultado = tirarRuleta();
    
        if ((mitad == 1 && resultado >= 1 && resultado <= 18) ||
            (mitad == 2 && resultado >= 19 && resultado <= 36)) {
            System.out.println("¡Felicidades! Ganaste x1 tu apuesta.");
            jugador.agregarFichas(apuesta * 2); // Ganancia: apuesta inicial más premio
        } else {
            System.out.println("Lo siento, perdiste.");
        }
    }
}
