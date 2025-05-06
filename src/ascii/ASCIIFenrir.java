package ascii;

import items.Items;
import java.util.List;

public class ASCIIFenrir {
    
    public void titulo(){
        System.out.println("_____               _   ");
        System.out.println("|  ___|__ _ __  _ __(_)_ __ ");
        System.out.println("| |_ / _ \\ '_ \\| '__| | '__|");
        System.out.println("|  _|  __/ | | | |  | | | ");
        System.out.println("|_|  \\___|_| |_|_|  |_|_|   ");
    }

    public void opcionesBar() {
        System.out.println("1) Comprar bebida (pagar con dinero)");
        System.out.println("2) Salir del bar");
        System.out.print("Selecciona una opción: ");
    }

     public void mostrarMenuBebidas(List<Items> bebidas) {
        System.out.println("----- Carta de Bebidas -----");
        for (int i = 0; i < bebidas.size(); i++) {
            Items b = bebidas.get(i);
            System.out.printf("%2d) %s – %s (€%.2f)%n",
                i + 1, b.getNombre(), b.getDescripcion(), b.getPrecio());
        }
        System.out.print("¿Qué deseas pedir? ");
    }
    
}




   


  



