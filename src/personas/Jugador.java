// Jugador.java
package personas;

// IO
import java.io.Serializable;

public class Jugador implements Serializable{
    
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
