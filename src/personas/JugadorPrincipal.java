package personas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ascii.ASCIIGeneral;
import excep.ExcepcionJugadorSinFichas;
import items.Items;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserver;
import salas.Sala;

/**
 * Clase que representa a un jugador principal dentro del juego,
 * implementa la interfaz Jugador y extiende funcionalidades adicionales.
 * Implementa el patrón Observer para notificar cambios al entorno.
 */
public class JugadorPrincipal implements Jugador, PullPushModelObservable {

    // Atributos adicionales específicos para el Jugador Principal
    private Integer posX = null, posY = null; // Posición en el mapa
    private Boolean interact = false; // Estado de interacción
    private ArrayList<Items> items = new ArrayList<>(); // Inventario del jugador
    private Boolean inventario = false; // Estado del inventario

    // Atributos heredados de Jugador
    private String nombre;
    private Integer edad;
    private Double dinero;
    private Integer fichas;
    private Sala salaActual;

    // Observadores para el patrón Observer
    private List<PullPushModelObserver> observers = new ArrayList<>();

    // Constructor que inicializa el Jugador Principal
    public JugadorPrincipal(String nombre, Integer edad, Double dinero, Integer posX, Integer posY) {
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;
        this.posX = posX;
        this.posY = posY;
        this.fichas = 0;
    }

    public JugadorPrincipal(String nombre, Integer edad, Double dinero) {
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;
        this.fichas = 0;
    }

    // Métodos Setters y Getters
    public Integer getPosX() {
        return posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    // Método para mover al jugador a una nueva posición y notificar observadores
    public void move(Integer x, Integer y) {
        this.posX = x;
        this.posY = y;
        notifyObservers();
    }

    // Inicia la interacción del jugador y notifica a los observadores
    public void interacting() {
        this.interact = true;
        notifyObservers();
        this.interact = false;
    }

    // Métodos del patrón Observer
    @Override
    public void attach(PullPushModelObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(PullPushModelObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (PullPushModelObserver observer : this.observers) {
            try {
                observer.update(this, null);
            } catch (ExcepcionJugadorSinFichas e) {
                e.printStackTrace();
            }
        }
    }

    // Métodos Setters y Getters para atributos heredados
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setDinero(Double dinero) {
        this.dinero = dinero;
    }

    public void setFichas(Double fichas) {
        this.fichas = fichas.intValue();
    }

    public void setSala(Sala salaActual) {
        this.salaActual = salaActual;
    }

    public void setInvetario(Boolean inventario) {
        this.inventario = inventario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Integer getEdad() {
        return this.edad;
    }

    public Double getDinero() {
        return this.dinero;
    }

    public Integer getFichas() {
        return this.fichas;
    }

    public Sala getSalaActual() {
        return this.salaActual;
    }

    public Boolean getInventario() {
        return this.inventario;
    }

    public Boolean getInteract() {
        return this.interact;
    }

    @Override
    public String getName() {
        return this.nombre;
    }

    /*
     * Métodos para agregar o restar dinero y fichas al jugador
     * public void agregarFichas(Integer fichas) {
     * this.fichas += fichas;
     * }
     * 
     * public void restarFichas(Integer fichas) {
     * this.fichas -= fichas;
     * }
     * 
     * public void agregarDinero(Double dinero) {
     * this.dinero += dinero;
     * }
     * 
     * public void restarDinero(Double dinero) {
     * this.dinero -= dinero;
     * }
     */

    // Mostrar los datos del jugador en la partida
    public void datosUsuarioEnPartida() {
        System.out.println("----------------------------");
        System.out.println("Nombre :  " + this.getNombre());
        System.out.println("Fichas : " + this.getFichas());
        System.out.println("----------------------------");
    }

    // Actualiza los datos del jugador con los datos de otro jugador cargado
    public void actualizarDesde(JugadorPrincipal jugadorCargado) {
        this.nombre = jugadorCargado.getNombre();
        this.edad = jugadorCargado.getEdad();
        this.dinero = jugadorCargado.getDinero();
        this.fichas = jugadorCargado.getFichas();
    }

    // Representación del jugador como String
    @Override
    public String toString() {
        return "Jugador : "
                + this.nombre
                + "\nEdad  : "
                + this.edad
                + "\nDinero : "
                + this.dinero
                + "\nFichas : "
                + this.fichas;
    }

    // Desvincula todos los observadores de este jugador
    @Override
    public void detachAll() {
        observers.clear();
    }

    // Muestra el inventario del jugador
    public void mostrarInventario() {
        if (items.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("Inventario de " + nombre + ":");
            for (int i = 0; i < items.size(); i++) {
                Items item = items.get(i);
                System.out.println(i + 1 + " "
                        + item.getNombre()
                        + " | " + item.getDescripcion()
                        + " | Precio: " + item.getPrecio());
            }
        }
    }

    // Permite al jugador usar los items del inventario
    public void usarItems() {
        if (items.isEmpty()) {
            System.out.println("No hay items para usar.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        while (true) {
            mostrarInventario();
            System.out.print("Elige el número del item a usar (0 para salir): ");
            int opcion;
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intenta de nuevo.");
                continue;
            }
            if (opcion == 0) {
                System.out.println("Saliendo del uso de items.");
                break;
            }
            if (opcion < 1 || opcion > items.size()) {
                System.out.println("Opción fuera de rango. Intenta de nuevo.");
                continue;
            }
            Items seleccionado = items.get(opcion - 1);
            ASCIIGeneral.limpiarPantalla();
            System.out.println("Usando " + seleccionado.getNombre() + "...");
            seleccionado.usar();
            items.remove(opcion - 1);
            if (items.isEmpty()) {
                System.out.println("Ya no quedan items en el inventario.");
                break;
            }
            System.out.println(); // Línea en blanco antes de la siguiente iteración
        }
    }

    // Agrega un nuevo item al inventario del jugador
    public void agregarItem(Items item) {
        items.add(item);
        System.out.println("Se ha añadido al inventario: "
                + item.getNombre()
                + " (Precio: " + item.getPrecio() + ")");
    }

}
