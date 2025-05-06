// Jugador.java
package personas;

// IO
import excep.ExcepcionJugadorSinFichas;
import items.Items;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserver;
import salas.Sala;
public class Jugador implements Serializable, PullPushModelObservable{

    // Atributos Adicionales
    private Integer posX = null, posY = null;
    private Boolean interact = false;
    private ArrayList<Items> items = new ArrayList<>();

    // Sobrecarga Constructor
    public Jugador(String nombre, Integer edad, Double dinero, Integer posX, Integer posY){
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;

        this.posX = posX;
        this.posY = posY;
    }

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

    public void move(Integer x, Integer y) {
        this.posX = x;
        this.posY = y;
        notifyObservers();
    }

    public void interacting() {
        this.interact = true;
        notifyObservers();
        this.interact = false;
    }

    private List<PullPushModelObserver> observers = new ArrayList<>();

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
        for (PullPushModelObserver observer : this.observers) { // for each
            try {
                observer.update(this, null); // TODO: SUGERENCIA 2
                if (this.observers.isEmpty()) return;
            } catch (ExcepcionJugadorSinFichas e) {
                e.printStackTrace();
            }
        }
    }

    // Atributos
    private static final long serialVersionUID = 1L;
    String nombre;
    Integer edad;
    Double dinero;
    Integer fichas;
    Sala salaActual;

    // Constructor
    public Jugador(String nombre, Integer edad, Double dinero){
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;
        this.fichas = 0;
    }

    // Metodos

    // Setters
    public void setNombre( String nombre){
        this.nombre = nombre;
    }
    public void setEdad( Integer edad){
        this.edad = edad;
    }
    public void setDinero( Double dinero){
        this.dinero = dinero;
    }
    public void setFichas( Integer fichas){
        this.fichas = fichas;
    }
    public void setSala(Sala salaActual){ this.salaActual = salaActual; }

    // Getters
    public String getName(){
        return this.nombre;
    }
    public Integer getEdad(){
        return this.edad;
    }
    public Double getDinero(){
        return this.dinero;
    }
    public Integer getFichas(){
        return this.fichas;
    }
    public Sala getSalaActual(){return this.salaActual;}

    // agregar / restar
    public void agregarFichas(Integer fichas){
        this.fichas += fichas;
    }

    public void restarFichas(Integer fichas){
        this.fichas -= fichas;
    }

    public void agregarDinero(Double dinero){
        this.dinero += dinero;
    }

    public void restarDinero(Double dinero){
        this.dinero -= dinero;
    }

    // Mostrar Datos de usuario
    public void datosUsuarioEnPartida(){
        System.out.println("----------------------------");
        System.out.println("Nombre :  " + this.getName());
        System.out.println("Fichas : " + this.getFichas());
        System.out.println("----------------------------");
    }

    public void actualizarDesde(Jugador jugadorCargado) {
        this.nombre = jugadorCargado.getName();
        this.edad = jugadorCargado.getEdad();
        this.dinero = jugadorCargado.getDinero();
        this.fichas = jugadorCargado.getFichas();

    }

    // to String
    @Override
    public String toString(){
        return "Jugador : "
                + this.nombre
                + "\nEdad  : "
                + this.edad
                + "\nDinero : "
                + this.dinero
                + "\nFichas : "
                + this.fichas;
    }

    @Override
    public void detachAll() {
        observers.clear();
    }

    // Mostrar inventario jugador
    public void mostrarInventario() {
        if (items.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("Inventario de " + nombre + ":");
            for (Items item : items) {
                System.out.println("- " 
                    + item.getNombre() 
                    + " | " + item.getDescripcion() 
                    + " | Precio: " + item.getPrecio());
            }
        }
    }


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
            System.out.println("Usando " + seleccionado.getNombre() + "...");
            seleccionado.usar();
            items.remove(opcion - 1);
            if (items.isEmpty()) {
                System.out.println("Ya no quedan items en el inventario.");
                break;
            }
            System.out.println(); // línea en blanco antes de la siguiente iteración
        }
    }

    public void agregarItem(Items item) {
        items.add(item);
        System.out.println("Se ha añadido al inventario: " 
            + item.getNombre() 
            + " (Precio: " + item.getPrecio() + ")");
    }
}
