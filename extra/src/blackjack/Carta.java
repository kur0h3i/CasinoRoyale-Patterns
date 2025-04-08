package blackjack;
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