// Jugador.java
package personas;

// IO
import excep.ExcepcionJugadorSinFichas;
import mapas.SalaPrincipalMapa;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jugador implements Serializable, PullPushModelObservable{


    // Atributos Adicionales
    private Integer posX = SalaPrincipalMapa.posX, posY = SalaPrincipalMapa.posY;
    private Boolean interact = false;

    // Sobrecarga Constructor
    public Jugador(String nombre, int edad, double dinero, Integer posX, Integer posY){
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
            // observer.update(this);
            try {
                observer.update(this, null); // TODO: SUGERENCIA 2
            } catch (ExcepcionJugadorSinFichas e) {
                e.printStackTrace();
            }
        }
    }
    
    // Atributos
    private static final long serialVersionUID = 1L;
    String nombre;
    int edad;
    double dinero;
    int fichas;

    // Constructor
    public Jugador(String nombre, int edad, double dinero){
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;
    }

    // Metodos

    // Setters
    public void setNombre( String nombre){
        this.nombre = nombre;
    }
    public void setEdad( int edad){
        this.edad = edad;
    }
    public void setDinero( double dinero){
        this.dinero = dinero;
    }
    public void setFichas( int fichas){
        this.fichas = fichas;
    }

    // Getters
    public String getName(){
        return this.nombre;
    }
    public int getEdad(){
        return this.edad;
    }
    public double getDinero(){
        return this.dinero;
    }
    public int getFichas(){
        return this.fichas;
    }

    // agregar / restar
    public void agregarFichas(int fichas){
        this.fichas += fichas;
    }

    public void restarFichas(int fichas){
        this.fichas -= fichas;
    }

    public void agregarDinero(int dinero){
        this.dinero += dinero;
    }

    public void restarDinero(int dinero){
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

}
