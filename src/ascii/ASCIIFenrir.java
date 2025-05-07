package ascii;

import items.Items;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class ASCIIFenrir implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public void titulo(){
        System.out.println("_____               _   ");
        System.out.println("|  ___|__ _ __  _ __(_)_ __ ");
        System.out.println("| |_ / _ \\ '_ \\| '__| | '__|");
        System.out.println("|  _|  __/ | | | |  | | | ");
        System.out.println("|_|  \\___|_| |_|_|  |_|_|   ");
    }

    public void opcionesBar() {
        System.out.println("1. Comprar bebida");
        System.out.println("2. Salir del bar");
        System.out.print("Selecciona una opción: ");
    }

    public void mostrarMenuBebidas(List<Items> bebidas) {
        System.out.println("------------- Carta de Bebidas ----------------");
        
        System.out.println("--------------------------");

        System.out.print("¿Qué deseas pedir? ");
    }

    public void carta() {
        System.out.println("_____________________________ CARTA _____________________________");
        System.out.println("| Num                    | Bebida           | Precio             |");
        System.out.println("|------------------------|------------------|--------------------|");
        System.out.println("| 1                      | Cerveza          | 2.50€              |");
        System.out.println("| 2                      | Copa de Vino     | 3.00€              |");
        System.out.println("| 3                      | Redbull          | 1.59€              |");
        System.out.println("| 4                      | Cacaolat         | 1.10€              |");
        System.out.println("| 5                      | Bifrutas         | 1.42€              |");
        System.out.println("|________________________|__________________|____________________|");
    }
    
}




   


  



