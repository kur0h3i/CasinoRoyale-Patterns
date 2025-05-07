
package items;

/**
 * Define el contrato para todos los objetos jugables o consumibles.
 * Contiene metodos para obtener propiedades besicas y ejecutar la accion del
 * item.
 */
public interface Items {

    /**
     * Obtiene el nombre del item.
     * 
     * @return nombre como String
     */
    String getNombre();

    /**
     * Obtiene el precio en euros del item.
     * 
     * @return precio como Double
     */
    Double getPrecio();

    /**
     * Obtiene la descripcion o efecto del item.
     * 
     * @return descripcion como String
     */
    String getDescripcion();

    /**
     * Accion principal al usar el item: define el comportamiento especifico.
     */
    void usar();
}
