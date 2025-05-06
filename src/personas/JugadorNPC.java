package personas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import excep.ExcepcionJugadorSinFichas;
import items.Items;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserver;
import salas.Sala;

/**
 * Clase que representa a un NPC dentro del juego, implementa la interfaz
 * Jugador
 * y extiende funcionalidades específicas para un personaje no jugador.
 * Implementa el patrón Observer para notificar cambios al entorno.
 */
public class JugadorNPC implements Jugador, PullPushModelObservable {

    // Atributos adicionales específicos para el NPC
    private Integer posX = null, posY = null; // Posición en el mapa
    private Boolean interact = false; // Estado de interacción (si está interactuando con algo)
    private ArrayList<Items> items = new ArrayList<>(); // Inventario del NPC (puede ser vacío o limitado)
    private Boolean inventario = false; // Estado del inventario del NPC

    // Atributos heredados de Jugador
    private String nombre;
    private Integer edad;
    private Double dinero;
    private Integer fichas;
    private Sala salaActual;

    // Observadores para el patrón Observer
    private List<PullPushModelObserver> observers = new ArrayList<>();

    // Instancia de Random para generar comportamientos aleatorios
    private Random random = new Random();

    /**
     * Constructor que inicializa el Jugador NPC con los atributos necesarios.
     * 
     * @param nombre Nombre del NPC.
     * @param edad   Edad del NPC.
     * @param dinero Cantidad de dinero del NPC.
     * @param posX   Posición en el eje X del NPC.
     * @param posY   Posición en el eje Y del NPC.
     */
    public JugadorNPC(String nombre, Integer edad, Double dinero, Integer posX, Integer posY) {
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;
        this.posX = posX;
        this.posY = posY;
        this.fichas = 0;
    }

    public JugadorNPC(String nombre, Integer edad, Double dinero) {
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;
        this.fichas = 0;
    }

    /**
     * Métodos Setters y Getters para los atributos del NPC.
     */
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

    public Boolean getInteract() {
        return interact;
    }

    /**
     * Implementación del método mover de la interfaz Jugador
     * Método para mover al NPC a una nueva posición en el mapa.
     * Notifica a los observadores sobre el cambio de posición.
     * 
     * @param x Nueva coordenada X.
     * @param y Nueva coordenada Y.
     */
    @Override
    public void move(Integer x, Integer y) {
        this.posX = x;
        this.posY = y;
        notifyObservers(); // Notifica a los observadores sobre el cambio de posición
    }

    /**
     * Inicia la interacción del NPC y notifica a los observadores.
     * El comportamiento de interacción del NPC podría ser diferente al del jugador
     * principal.
     */
    public void interacting() {
        this.interact = true;
        notifyObservers(); // Notifica a los observadores que el NPC está interactuando
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

    public void setFichas(Integer fichas) {
        this.fichas = fichas;
    }

    public void setSala(Sala salaActual) {
        this.salaActual = salaActual;
    }

    public void setInvetario(Boolean inventario) {
        this.inventario = inventario;
    }

    public String getName() {
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

    /**
     * Métodos para agregar o restar dinero y fichas al NPC.
     */
    public void agregarFichas(Integer fichas) {
        this.fichas += fichas;
    }

    public void restarFichas(Integer fichas) {
        this.fichas -= fichas;
    }

    public void agregarDinero(Double dinero) {
        this.dinero += dinero;
    }

    public void restarDinero(Double dinero) {
        this.dinero -= dinero;
    }

    /**
     * Muestra los datos del NPC en la partida (nombre, fichas).
     */
    public void datosUsuarioEnPartida() {
        System.out.println("----------------------------");
        System.out.println("Nombre :  " + this.getName());
        System.out.println("Fichas : " + this.getFichas());
        System.out.println("----------------------------");
    }

    /**
     * Actualiza los datos del NPC con los datos de otro NPC cargado.
     * 
     * @param jugadorCargado El NPC cuyo estado se utilizará para actualizar
     *                       este NPC.
     */
    public void actualizarDesde(JugadorNPC jugadorCargado) {
        this.nombre = jugadorCargado.getName();
        this.edad = jugadorCargado.getEdad();
        this.dinero = jugadorCargado.getDinero();
        this.fichas = jugadorCargado.getFichas();
    }

    /**
     * Implementación del método getNombre de la interfaz Jugador.
     * 
     * @return El nombre del jugador.
     */

    /**
     * Implementación del método setFichas de la interfaz Jugador que acepta un
     * Double.
     * 
     * @param fichas La cantidad de fichas a establecer.
     */
    @Override
    public void setFichas(Double fichas) {
        this.fichas = fichas.intValue();
    }

    /**
     * Representación del NPC como String.
     * 
     * @return Cadena que describe los datos del NPC.
     */
    @Override
    public String toString() {
        return "NPC : "
                + this.nombre
                + "\nEdad  : "
                + this.edad
                + "\nDinero : "
                + this.dinero
                + "\nFichas : "
                + this.fichas;
    }

    /**
     * Desvincula todos los observadores de este NPC.
     */
    @Override
    public void detachAll() {
        observers.clear();
    }

    /**
     * Muestra el inventario del NPC en consola.
     */
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

    /**
     * Permite al NPC tomar decisiones aleatorias de movimiento e interacción.
     * El NPC tomará decisiones aleatorias basadas en probabilidades definidas.
     */
    public void tomarDecisionesAleatorias() {
        int decision = random.nextInt(4); // Generar número aleatorio entre 0 y 3
        switch (decision) {
            case 0:
                moverAleatoriamente();
                break;
            case 1:
                interacting();
                break;
            case 2:
                usarItemAleatorio();
                break;
            case 3:
                System.out.println(nombre + " está descansando.");
                break;
        }
    }

    /**
     * El NPC se mueve aleatoriamente en una dirección.
     */
    private void moverAleatoriamente() {
        int direccion = random.nextInt(4); // 0 = arriba, 1 = abajo, 2 = izquierda, 3 = derecha
        switch (direccion) {
            case 0:
                setPosY(posY - 1); // Mueve hacia arriba
                break;
            case 1:
                setPosY(posY + 1); // Mueve hacia abajo
                break;
            case 2:
                setPosX(posX - 1); // Mueve hacia la izquierda
                break;
            case 3:
                setPosX(posX + 1); // Mueve hacia la derecha
                break;
        }
        System.out.println(nombre + " se ha movido aleatoriamente a la posición: (" + posX + ", " + posY + ")");
    }

    /**
     * El NPC usa un item aleatorio de su inventario.
     */
    private void usarItemAleatorio() {
        if (!items.isEmpty()) {
            int itemIndex = random.nextInt(items.size()); // Selecciona un item aleatorio
            Items item = items.get(itemIndex);
            System.out.println(nombre + " está usando el item: " + item.getNombre());
            item.usar();
            items.remove(itemIndex); // Elimina el item del inventario después de usarlo
        } else {
            System.out.println(nombre + " no tiene items para usar.");
        }
    }

    /**
     * Agrega un nuevo item al inventario del NPC.
     * 
     * @param item El item a agregar al inventario.
     */
    public void agregarItem(Items item) {
        items.add(item);
        System.out.println("Se ha añadido al inventario: "
                + item.getNombre()
                + " (Precio: " + item.getPrecio() + ")");
    }
}
