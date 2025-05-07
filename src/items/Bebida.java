
package items;

import java.io.Serial;
import java.io.Serializable;

/**
 * Implementación de Items que representa una bebida consumible.
 * Permite obtener nombre, precio, descripción y usar la bebida con un mensaje.
 */
public class Bebida implements Items, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Nombre de la bebida (por ejemplo, "Cerveza") */
    private final String nombre;
    /** Precio en euros de la bebida */
    private final Double precio;
    /** Descripción del sabor o efecto de la bebida */
    private final String descripcion;

    /**
     * Constructor de Bebida.
     *
     * @param nombre      nombre de la bebida
     * @param precio      coste en euros
     * @param descripcion descripción o efecto al consumir la bebida
     */
    public Bebida(String nombre, Double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el nombre de la bebida.
     * 
     * @return nombre como String
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio en euros.
     * 
     * @return precio como Double
     */
    @Override
    public Double getPrecio() {
        return precio;
    }

    /**
     * Obtiene la descripción de la bebida.
     * 
     * @return descripción como String
     */
    @Override
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Acción al usar la bebida: imprime mensaje al jugador describiendo el sabor.
     */
    @Override
    public void usar() {
        System.out.println("Bebes " + nombre + " y notas el sabor: " + descripcion + ".");
    }
}
