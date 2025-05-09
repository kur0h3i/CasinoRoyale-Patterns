
package personas;

import ascii.ASCIIGeneral;
import excep.ExcepcionJugadorSinFichas;
import items.Items;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserver;
import salas.Sala;
import salas.SalaPrincipal;

/**
 * Representa al usuario en el casino, con posicion, fondos,
 * fichas, inventario de items y suscripcion al patron Observer para recibir
 * notificaciones de cambios de estado.
 * Implementa Serializable para persistencia y PullPushModelObservable para
 * notificar a observadores cuando se mueve o interactua.
 */
public class Jugador implements JugadorTotal, Serializable, PullPushModelObservable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Coordenada X actual del jugador en el mapa */
    private Integer posX;
    /** Coordenada Y actual del jugador en el mapa */
    private Integer posY;
    /** Indicador de que el jugador ha pulsado la tecla de interactuar */
    private Boolean interact;
    /** Lista de items en el inventario del jugador */
    private final List<Items> items;
    /** Indicador de si el inventario este abierto */
    private Boolean inventario;

    /** Nombre del jugador */
    private String nombre;
    /** Edad del jugador */
    private Integer edad;
    /** Dinero en euros disponible */
    private Double dinero;
    /** Fichas disponibles para apostar */
    private Integer fichas;
    /** Sala actual donde se encuentra el jugador */
    private Sala salaActual;

    /** Observadores suscritos al jugador */
    private final List<PullPushModelObserver> observers;

    /**
     * Constructor completo con posicion inicial.
     *
     * @param nombre nombre del jugador
     * @param edad edad del jugador
     * @param dinero saldo inicial en euros
     * @param posX coordenada X en el mapa
     * @param posY coordenada Y en el mapa
     */
    public Jugador(String nombre, Integer edad, Double dinero, Integer posX, Integer posY) {
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;
        this.fichas = 0;
        this.posX = posX;
        this.posY = posY;
        this.interact = false;
        this.items = new ArrayList<>();
        this.inventario = false;
        this.observers = new ArrayList<>();
    }

    /**
     * Constructor besico sin posicion: asigna 0 fichas.
     *
     * @param nombre nombre del jugador
     * @param edad edad del jugador
     * @param dinero saldo inicial en euros
     */
    public Jugador(String nombre, Integer edad, Double dinero) {
        this(nombre, edad, dinero, null, null);
    }

    // ==================== Observer Pattern ====================

    @Override
    public void attach(PullPushModelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(PullPushModelObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void detachAll() {
        observers.clear();
    }

    @Override
    public void notifyObservers() {
        for (PullPushModelObserver obs : new ArrayList<>(observers)) {
            try {
                obs.update(this, null);
            } catch (ExcepcionJugadorSinFichas e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mueve la posicion del jugador y notifica a los observadores.
     *
     * @param x nueva coordenada X
     * @param y nueva coordenada Y
     */
    public void move(Integer x, Integer y) {
        this.posX = x;
        this.posY = y;
        notifyObservers();
    }

    /**
     * Marca la interaccion (press) y notifica, luego resetea el estado.
     */
    public void interacting() {
        this.interact = true;
        notifyObservers();
        this.interact = false;
    }

    // ==================== Getters & Setters ====================

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

    @Override
    public void setSala(SalaPrincipal salaPrincipal) {
        salaActual = salaPrincipal;
    }

    public Boolean getInteract() {
        return interact;
    }

    public String getName() { return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getDinero() {
        return dinero;
    }

    public void setDinero(Double dinero) {
        this.dinero = dinero;
    }

    public Integer getFichas() {
        return fichas;
    }

    public void setFichas(Integer fichas) {
        this.fichas = fichas;
    }

    public Sala getSalaActual() {
        return salaActual;
    }

    public void setSala(Sala salaActual) {
        this.salaActual = salaActual;
    }

    public Boolean getInventario() {
        return inventario;
    }

    public void setInvetario(Boolean inventario) {
        this.inventario = inventario;
    }

    // ==================== Fondos y Fichas ====================

    /**
     * Incrementa las fichas del jugador.
     *
     * @param fichas cantidad de fichas a agregar
     */
    public void agregarFichas(Integer fichas) {
        this.fichas += fichas;
    }

    /**
     * Decrementa las fichas del jugador.
     *
     * @param fichas cantidad de fichas a restar
     */
    public void restarFichas(Integer fichas) {
        this.fichas -= fichas;
    }

    /**
     * Incrementa el dinero del jugador.
     *
     * @param dinero cantidad en euros a agregar
     */
    public void agregarDinero(Double dinero) {
        this.dinero += dinero;
    }

    /**
     * Decrementa el dinero del jugador.
     *
     * @param dinero cantidad en euros a restar
     */
    public void restarDinero(Double dinero) {
        this.dinero -= dinero;
    }

    // ==================== Inventario y Items ====================

    /**
     * Anade un item al inventario y muestra mensaje.
     *
     * @param item objeto Items a agregar
     */
    public void agregarItem(Items item) {
        items.add(item);
        System.out.println("Se ha anadido al inventario: " + item.getNombre() +
                " (Precio: " + item.getPrecio() + ")");
    }

    /**
     * Muestra el listado de items en el inventario.
     */
    public void mostrarInventario() {
        if (items.isEmpty()) {
            System.out.println("El inventario este vacio.");
        } else {
            System.out.println("Inventario de " + nombre + ":");
            for (Integer i = 0; i < items.size(); i++) {
                Items item = items.get(i);
                System.out.println((i + 1) + ". " + item.getNombre() +
                        " | " + item.getDescripcion() +
                        " | Precio: " + item.getPrecio());
            }
        }
    }

    /**
     * Permite al jugador usar un item del inventario, invocando su metodo usar.
     * Elimina el item tras su uso.
     */
    public void usarItems() {
        if (items.isEmpty()) {
            System.out.println("No hay items para usar.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        while (true) {
            mostrarInventario();
            System.out.print("Elige el numero del item a usar (0 para salir): ");
            Integer opcion;
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invelida. Intenta de nuevo.");
                continue;
            }
            if (opcion == 0) {
                System.out.println("Saliendo del uso de items.");
                break;
            }
            if (opcion < 1 || opcion > items.size()) {
                System.out.println("Opcion fuera de rango. Intenta de nuevo.");
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
            System.out.println();
        }
    }

    // ==================== Informacion de Usuario ====================

    /**
     * Inicializa el jugador cuando se carga los datos de una partida ya guardada
     */
    public void initPlayer() {
        this.interact = false;
        this.inventario = false;
        this.detachAll();
    }

    /**
     * Imprime los datos besicos del jugador (nombre y fichas).
     */
    public void datosUsuarioEnPartida() {
        System.out.println("----------------------------");
        System.out.println("Nombre : " + nombre);
        System.out.println("Fichas : " + fichas);
        System.out.println("----------------------------");
    }

    /**
     * Actualiza los campos del jugador con los datos de otro instanciado (p.ej. al cargar).
     *
     * @param jugadorCargado Jugador con datos a copiar
     */
    public void actualizarDesde(Jugador jugadorCargado) {
        this.nombre = jugadorCargado.getName();
        this.edad = jugadorCargado.getEdad();
        this.dinero = jugadorCargado.getDinero();
        this.fichas = jugadorCargado.getFichas();
    }

    /**
     * Representacion textual del jugador: muestra nombre, edad, dinero y fichas.
     */
    @Override
    public String toString() {
        return "Jugador : " + nombre +
                "\nEdad    : " + edad +
                "\nDinero  : " + dinero +
                "\nFichas  : " + fichas;
    }
}