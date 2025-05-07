
package items;

/**
 * Interfaz Items => Define el contrato para todos los objetos jugables o
 * consumibles.
 * Contiene métodos para obtener propiedades básicas y ejecutar la acción del
 * ítem.
 */
public interface Items {

    /**
     * Obtiene el nombre del ítem.
     * 
     * @return nombre como String
     */
    String getNombre();

    /**
     * Obtiene el precio en euros del ítem.
     * 
     * @return precio como Double
     */
    Double getPrecio();

    /**
     * Obtiene la descripción o efecto del ítem.
     * 
     * @return descripción como String
     */
    String getDescripcion();

    /**
     * Acción principal al usar el ítem: define el comportamiento específico.
     */
    void usar();
}
