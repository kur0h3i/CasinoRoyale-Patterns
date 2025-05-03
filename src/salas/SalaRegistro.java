// Salaregisttro
package salas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import excep.ExcepcionJugadorMenorEdad;
import excep.ExcepcionJugadorNoEncontrado;
import personas.Jugador;

public class SalaRegistro {

    // Atributos
    public static Jugador jugador;

    // Constructor
    public SalaRegistro() {
        // ASCII ART
        asciiArt();
        // Información sobre el Casino
        informacionCasino();
        // Crear al jugador
        jugador = iniciarJugador();
        
        // Verificar si es mayor de edad
        try { 
            mayorEdad();
        } catch (ExcepcionJugadorMenorEdad e) {
            // Manejar la excepción y terminar el registro
            System.out.println(e.getMessage());
            System.out.println("Registro cancelado");
            System.exit(0); // Finalizar el programa
        }
    }

    // ASCII ART
    public void asciiArt() {
        System.out.println(
            "   ______           _                  ____                    __   \n" +
            "  / ____/___ ______(_)___  ____       / __ \\____  __  ______ _/ /__ \n" +
            " / /   / __ `/ ___/ / __ \\/ __ \\     / /_/ / __ \\/ / / / __ `/ / _ \\\n" +
            "/ /___/ /_/ (__  ) / / / / /_/ /    / _, _/ /_/ / /_/ / /_/ / /  __/\n" +
            "\\____/\\__,_/____/_/_/ /_/\\____/    /_/ |_|\\____/\\__, /\\__,_/_/\\___/ \n" +
            "                                               /____/               \n"
        );
    }

    // Información sobre el Casino
    public void informacionCasino() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Bienvenidos a Casino Royale, un emocionante juego de casino dodne tendras que usar tu mayor ingenio en el juego para poder hacer una gran cantidad de dinero.");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    // Crea o usa un usuario ya existente
    public Jugador iniciarJugador() {
        Scanner input = new Scanner(System.in);
        Integer opcion;
        Jugador jugadorSeleccionado = null;

        do {
            System.out.println("Buenos días, Sr.");
            System.out.println("1. Iniciar Sesión Socio");
            System.out.println("2. Registrar un nuevo Socio");
            System.out.println("3. Salir");

            while (!input.hasNextInt()) {
                System.out.println("Por favor, introduce una opción válida (1-3):");
                input.next();
            }

            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1:
                    jugadorSeleccionado = cargarJugador();
                    break;
                case 2:
                    jugadorSeleccionado = crearJugador();
                    break;
                case 3: // DEBUG MOMENTANEO
                    System.out.println("Adiós, espero que se lo haya pasado bien");
                    return new Jugador("Lediff el travieso", 23, 1000.0);
                default:
                    System.out.println("Opción no válida, por favor elige 1, 2 o 3.");
            }
        } while (jugadorSeleccionado == null);

        return jugadorSeleccionado;
    }

    // Iniciar Sesion
    public Jugador cargarJugador() {
        Scanner input = new Scanner(System.in);
        System.out.println("Nombre del jugador que quieres cargar: ");
        String nombreJugador = input.nextLine();

        File archivo = new File("saves/" + nombreJugador + ".dat");

        try {
            if (!archivo.exists()) {
                throw new ExcepcionJugadorNoEncontrado("El jugador con nombre '" + nombreJugador + "' no existe.");
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                Jugador jugadorCargado = (Jugador) ois.readObject();
                System.out.println("Partida cargada exitosamente:");
                System.out.println(jugadorCargado);

                return jugadorCargado;
            }
        } catch (ExcepcionJugadorNoEncontrado e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar los datos del jugador: " + e.getMessage());
        }

        return null; // Si ocurre un error, devuelve null
    }


    // Crear Jugador
    public Jugador crearJugador() {
        Scanner input = new Scanner(System.in);
        // Atributos Jugador
        String nombre = " ";
        Integer edad = 0;
        Character confirmacion = ' ';
        Double dinero = 100.0;

        // Crear al jugador
        System.out.println("Cuéntame un poco sobre ti:");
        do {
            // Nombre
            System.out.println("¿Cuál es tu nombre?");
            nombre = input.nextLine();
            System.out.println("Con que te llamas " + nombre);

            // Edad
            System.out.println(nombre + ", ¿cuántos años tienes?");
            while (!input.hasNextInt()) {
                System.out.println("Por favor, ingresa una edad válida.");
                input.next();
            }
            edad = input.nextInt();
            input.nextLine();

            // Confirmación de los datos
            System.out.println("Te llamas " + nombre + " y tienes " + edad + " años, ¿es correcto? S/n");
            confirmacion = input.nextLine().charAt(0);

        } while (confirmacion != 'S' && confirmacion != 's');

        // Crear el jugador con los datos confirmados
        return new Jugador(nombre, edad, dinero);
    }

    // Verificar si el jugador es mayor de edad
    public Boolean mayorEdad() throws ExcepcionJugadorMenorEdad {
        if (jugador.getEdad() >= 18) {
            return true;
        } else {
            throw new ExcepcionJugadorMenorEdad("Lo sentimos, pero los menores de edad no pueden entrar al casino.");
        }
    }
}
