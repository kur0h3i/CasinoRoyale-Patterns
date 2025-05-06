package items;

public class RedBull implements Items{
    @Override
    public String getNombre() {
        return "RedBull";
    }

    @Override
    public Double getPrecio() {
        return 1.59;
    }

    @Override
    public String getDescripcion() {
        return "Redbull te da alas";
    }

    @Override
    public void usar() {
        System.out.println("Volando ...");
    }
}
