package personas;

public class FondosDecorator extends JugadorDecorator {

    public FondosDecorator(Jugador jugador) {
        super(jugador);
    }

    // Método para agregar fichas
    public void agregarFichas(Double fichas) {
        Integer fichasActuales = jugadorDecorado.getFichas();
        jugadorDecorado.setFichas(fichasActuales + fichas);
        System.out.println("Se han añadido " + fichas + " fichas.");
    }

    // Método para restar fichas
    public void restarFichas(Double fichas) {
        Integer fichasActuales = jugadorDecorado.getFichas();
        if (fichasActuales >= fichas) {
            jugadorDecorado.setFichas(fichasActuales - fichas);
            System.out.println("Se han restado " + fichas + " fichas.");
        } else {
            System.out.println("No tienes suficientes fichas.");
        }
    }

    // Método para agregar dinero
    public void agregarDinero(Double dinero) {
        Double dineroActual = jugadorDecorado.getDinero();
        jugadorDecorado.setDinero(dineroActual + dinero);
        System.out.println("Se han añadido " + dinero + " dinero.");
    }

    // Método para restar dinero
    public void restarDinero(Double dinero) {
        Double dineroActual = jugadorDecorado.getDinero();
        if (dineroActual >= dinero) {
            jugadorDecorado.setDinero(dineroActual - dinero);
            System.out.println("Se han restado " + dinero + " dinero.");
        } else {
            System.out.println("No tienes suficiente dinero.");
        }
    }

    @Override
    public void datosUsuarioEnPartida() {
        super.datosUsuarioEnPartida();
        // Mostrar las actualizaciones de fichas y dinero
        System.out.println("Fichas: " + jugadorDecorado.getFichas());
        System.out.println("Dinero: " + jugadorDecorado.getDinero());
    }

    @Override
    public String getName() {
        return jugadorDecorado.getName();
    }

}
