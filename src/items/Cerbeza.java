package items;

public class Cerbeza implements Items {

    @Override
    public String getNombre() {
        return "Beer";
    }

    @Override
    public Double getPrecio() {
        return 5.99;
    }

    @Override
    public String getDescripcion() {
        return "Un elixir para el paladar.";
    }

    @Override
    public void usar() {
        System.out.println("Bebiendo ...");
    }
}
