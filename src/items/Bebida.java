package items;

public class Bebida implements Items {
    private String nombre;
    private double precio;           
    private String descripcion;

    public Bebida(String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public Double getPrecio() {
        return precio;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public void usar() {
        System.out.println("Bebes " + nombre + " y notas el sabor: " + descripcion);
    }
}
