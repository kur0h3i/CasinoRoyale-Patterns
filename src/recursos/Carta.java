package recursos;
// Carta.java
public class Carta {

  // Atributos
  private String valor;
  private String tipo;

  // Constructor
  public Carta(String valor, String tipo) {
    this.valor = valor;
    this.tipo = tipo;
  }

  // Metodos
  // getNumero
  public String getNumero() {
    return valor;
  }

  // getPalo
  public String getTipo() {
    return tipo;
  }

  public int getValorNumerico() {
    switch (valor) {
      case "A":
        return 14; // As vale más que las cartas numéricas
      case "K":
        return 13;
      case "Q":
        return 12;
      case "J":
        return 11;
      default:
        return Integer.parseInt(valor); // Convertir los valores numéricos directamente
    }
  }
  
  // Prioridad de los palos segun mi criterio
  private int getPrioridadPalo() {
    switch (tipo) {
      case "S": // PICAS
        return 4;
      case "H": // TREBOLES
        return 3;
      case "D": // DIAMANTES
        return 2;
      case "C": // CORZAZONES
        return 1;
      default:
        return 0;
    }
  }

  // Metodoo que se usaba en la version del BalckJack con UI
  public String getNombreArchivo() {
    // Convierte el valor y tipo a un nombre de archivo, como "A_corazones.png"
    return valor + "-" + tipo + ".png";
}

  // toString
  @Override
  public String toString() {
    return valor + " de " + tipo;
  }
}