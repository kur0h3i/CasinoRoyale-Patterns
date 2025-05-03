// Cajero.java
package acciones;

// Excepciones
import excep.ExcepcionJugadorSinDinero;
import excep.ExcepcionJugadorSinFichas;

// Util
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

// ASCII
import ascii.ASCIICajero;
import ascii.ASCIIGeneral;

// Jugador
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserverInteractive;
import personas.Jugador;

import static recursos.MensajesEstaticos.interactATM;


public class Cajero implements PullPushModelObserverInteractive {

    // Atributos Adicionales
    private Integer posX, posY;

    // Constructor con el Observer Implementado
    public Cajero(Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
    }

    // Atributos Cajero
    Jugador jugador;
    ASCIICajero interfaz;

    // Metodos
    // Iniciar Cajero
    public void iniciarCajero(Scanner input){
        
        int opcion = 0;
        while (opcion != 3) {
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            interfaz.opcioes();
            try {
                opcion = input.nextInt();
                input.nextLine(); 
                
                // Opciones del menú principal
                switch (opcion) {
                    case 1:
                        try{
                            dineroFichas(definirValor(input));     
                        } 
                        catch (ExcepcionJugadorSinDinero e){
                            System.out.println("No puedes cambiar porque no tienes dinero.");
                            ASCIIGeneral.esperarTecla();
                        }
                        
                        break;
                    case 2:
                        try{   
                            fichasDinero(definirValor(input));
                        }catch (ExcepcionJugadorSinFichas e){
                            System.out.println("No puedes cambiar porque no tienes fichas.");
                            ASCIIGeneral.esperarTecla();
                        }
                        break;
                    case 3:
                        System.out.println("Saliendo del juego...");
                        ASCIIGeneral.limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intenta de nuevo.");
                // Limpiar Buffer
                input.nextLine(); 
            }
        }
    }

    // Comprobar Fichas con excepcion
    public void comprobarfichas() throws ExcepcionJugadorSinFichas{
        if (jugador.getFichas() <= 0) {
            throw new ExcepcionJugadorSinFichas("Jugador sin fichas");
        }
    }

    // Comprobar Dinero con excepcion
    public void comprobarDinero() throws ExcepcionJugadorSinDinero{
        if (jugador.getDinero() <= 0){
            throw new ExcepcionJugadorSinDinero("Jugador sin dinero");
        }

    }

    // Fichas ---> Dinero
    public void fichasDinero(int valor) throws ExcepcionJugadorSinFichas {
        comprobarfichas();
        System.out.println("Cambiando " + valor + " fichas por dinero...");
        jugador.restarFichas(valor);
        jugador.agregarDinero(valor);
    }

    // Dinero ---> Fichas
    public void dineroFichas(int valor) throws ExcepcionJugadorSinDinero {
        comprobarDinero();
        System.out.println("Cambiando " + valor + " dinero por fichas...");
        jugador.restarDinero(valor);
        jugador.agregarFichas(valor);
    }

    // Definir Valor
    public int definirValor(Scanner input) {
        int valor = -1;
        while (valor <= 0) { // Asegurar que el valor sea mayor que cero
            System.out.println("¿Cuál es el valor que quieres intercambiar?");
            try {
                valor = input.nextInt();
                if (valor <= 0) {
                    System.out.println("El valor debe ser mayor que cero. Intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intenta de nuevo.");
                input.nextLine(); // Limpiar el buffer
            }
        }
        return valor;
    }

    @Override
    public void update(PullPushModelObservable pullPushModelObservable, Object object) {

        if (pullPushModelObservable instanceof Jugador) { // Se que a nivel de ciclo de vida llegaria otro objeto Observable distinto, pero por si las moscas
            Jugador jugadorTMP = (Jugador) pullPushModelObservable;

            if (Objects.equals(jugadorTMP.getPosX(), this.posX) && Objects.equals(jugadorTMP.getPosY(), this.posY)) {
                interactATM();
                this.jugador = jugadorTMP;
                if (this.jugador.getInteract()) interactive();
            }
            else {
                jugador = null; // Se asume de que no hay ningun jugador ocupado // No se si habra multiplayer xd
            }
        }

    }

    @Override
    public void interactive() {
        Scanner input = new Scanner(System.in);
        interfaz = new ASCIICajero(jugador);
        iniciarCajero(input);
    }
}

