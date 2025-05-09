
package salas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import excep.ExcepcionJugadorMenorEdad;
import excep.ExcepcionJugadorNoEncontrado;
import personas.Jugador;
import personas.JugadorFactory;
import personas.JugadorTotal;

/**
 * Punto de entrada para registrar o cargar un jugador antes de iniciar el casino.
 * Muestra arte ASCII, informacion de bienvenida y gestiona la creacion o carga de perfiles.
 * Verifica que el jugador sea mayor de edad antes de continuar.
 *
 * Uso:
 *   SalaRegistro registro = new SalaRegistro();
 *   Jugador jugador = SalaRegistro.jugador;
 */
public class SalaRegistro {

    /**
     * Jugador seleccionado o creado durante el registro.
     */
    public static JugadorTotal jugador;

    /**
     * Constructor: muestra arte ASCII, informacion del casino,
     * inicia el flujo de registro y verifica la mayoria de edad.
     */
    public SalaRegistro() {
        asciiArt();
        informacionCasino();
        jugador = iniciarJugador();
        try {
            verificarMayorEdad();
        } catch (ExcepcionJugadorMenorEdad e) {
            System.out.println(e.getMessage());
            System.out.println("Registro cancelado.");
            System.exit(0);
        }
    }

    /**
     * Muestra arte ASCII de bienvenida.
     */
    public void asciiArt() {
        System.out.println(
                "   ______           _                  ____                    __   \n" +
                        "  / ____/___ ______(_)___  ____       / __ \\____  __  ______ _/ /__ \n" +
                        " / /   / __ `/ ___/ / __ \\/ __ \\     / /_/ / __ \\/ / / / __ `/ / _ \\\n" +
                        "/ /___/ /_/ (__  ) / / / / /_/ /    / _, _/ /_/ / /_/ / /_/ / /  __/\n" +
                        "\\____/\\__,_/____/_/_/ /_/\\____/    /_/ |_|\\____/\\__, /\\__,_/_/\\___/ \n" +
                        "                                               /____/               "
        );
    }

    /**
     * Muestra la descripcion y reglas besicas del casino.
     */
    public void informacionCasino() {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("Bienvenido a Casino Royale: pon a prueba tu suerte y habilidad para ganar fichas.");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Inicia el flujo de registro: cargar o crear un jugador.
     *
     * @return instancia de Jugador velido
     */
    public JugadorTotal iniciarJugador() {
        Scanner input = new Scanner(System.in);
        JugadorTotal sel = null;
        Integer opcion;
        do {
            System.out.println("1. Iniciar sesion de socio");
            System.out.println("2. Registrar nuevo socio");
            System.out.println("3. Salir");
            System.out.print("Elige una opcion (1-3): ");
            while (!input.hasNextInt()) {
                System.out.print("Por favor ingresa un numero (1-3): ");
                input.next();
            }
            opcion = input.nextInt();
            input.nextLine();
            switch (opcion) {
                case 1:
                    sel = cargarJugador();
                    break;
                case 2:
                    sel = crearJugador();
                    break;
                case 3:
                    System.out.println("Gracias por visitarnos. ¡Hasta pronto!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no velida.");
            }
        } while (sel == null);
        return sel;
    }

    /**
     * Carga un jugador desde un archivo de guardado.
     *
     * @return Jugador cargado o null si falla
     */
    public Jugador cargarJugador() {
        Scanner input = new Scanner(System.in);
        System.out.print("Nombre del jugador a cargar: ");
        String nombre = input.nextLine().trim();
        File archivo = new File("saves/" + nombre + ".dat");
        try {
            if (!archivo.exists()) {
                throw new ExcepcionJugadorNoEncontrado(
                        "El jugador '" + nombre + "' no existe.");
            }
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(archivo))) {
                Jugador cargado = (Jugador) ois.readObject();
                cargado.initPlayer();
                System.out.println("Partida cargada exitosamente.\n" + cargado);
                return cargado;
            }
        } catch (ExcepcionJugadorNoEncontrado e) {
            System.out.println(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar partida: " + e.getMessage());
        }
        return null;
    }

    /**
     * Solicita datos y crea un nuevo jugador.<br>
     * Verifica nombre y edad mediante confirmacion.
     *
     * @return Jugador recien creado
     */
    public JugadorTotal crearJugador() {
        Scanner input = new Scanner(System.in);
        String nombre;
        Integer edad;
        Character confirma;
        do {
            System.out.print("Tu nombre: ");
            nombre = input.nextLine().trim();
            System.out.print("Tu edad: ");
            while (!input.hasNextInt()) {
                System.out.print("Edad invelida. Ingresa un numero: ");
                input.next();
            }
            edad = input.nextInt();
            input.nextLine();
            System.out.print("¿Confirmas nombre '" + nombre + "' y edad " + edad + "? (S/N): ");
            confirma = input.nextLine().trim().toUpperCase().charAt(0);
        } while (confirma != 'S');
        // saldo inicial fijo
        return JugadorFactory.crearJugador("principal", nombre, edad, 100.0);
    }

    /**
     * Verifica que el jugador sea mayor de 18 anos.
     *
     * @return
     * @throws ExcepcionJugadorMenorEdad si no cumple la edad minima
     */
    public Boolean verificarMayorEdad() throws ExcepcionJugadorMenorEdad {
        if (jugador.getEdad() < 18) {
            throw new ExcepcionJugadorMenorEdad(
                    "Lo sentimos, menores de edad no pueden entrar al casino.");
        } else {
            return Boolean.TRUE;
        }

    }

    /**
     * Identificador textual de esta sala de registro.
     *
     * @return "Registro"
     */
    @Override
    public String toString() {
        return "Registro";
    }
}
