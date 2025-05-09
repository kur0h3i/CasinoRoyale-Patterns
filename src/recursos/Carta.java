
package recursos;

import java.io.Serial;
import java.io.Serializable;

/**
 * Representa una carta de la baraja con un valor y un palo.
 * Proporciona metodos para obtener su representacion, valor numerico y nombre
 * de archivo.
 */
public class Carta implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  /**
   * Valor de la carta ("A", "2", ..., "10", "J", "Q", "K")
   */
  private String valor;
  /**
   * Palo de la carta ("C", "D", "H", "S")
   */
  private String tipo;

  /**
   * Constructor de Carta.
   *
   * @param valor cadena que representa el valor
   * @param tipo  cadena que representa el palo
   */
  public Carta(String valor, String tipo) {
    this.valor = valor;
    this.tipo = tipo;
  }

  /**
   * Obtiene el valor textual de la carta.
   *
   * @return valor de la carta
   */
  public String getNumero() {
    return valor;
  }

  /**
   * Obtiene el palo de la carta.
   *
   * @return tipo (palo) de la carta
   */
  public String getTipo() {
    return tipo;
  }

  /**
   * Calcula el valor numerico de la carta para comparaciones:
   * A=14, K=13, Q=12, J=11, 2-10 segun su numero.
   *
   * @return valor numerico entero
   */
  public Integer getValorNumerico() {
    switch (valor) {
      case "A":
        return 14;
      case "K":
        return 13;
      case "Q":
        return 12;
      case "J":
        return 11;
      default:
        return Integer.parseInt(valor);
    }
  }

  /**
   * Determina la prioridad del palo segun criterio interno:
   * S (Picas)=4, H (Treboles)=3, D (Diamantes)=2, C (Corazones)=1.
   *
   * @return prioridad del palo
   */
  private Integer getPrioridadPalo() {
    switch (tipo) {
      case "S":
        return 4;
      case "H":
        return 3;
      case "D":
        return 2;
      case "C":
        return 1;
      default:
        return 0;
    }
  }

  /**
   * Genera un nombre de archivo para la carta (p.ej. "A-C.png").
   *
   * @return nombre de archivo con extension
   */
  public String getNombreArchivo() {
    return valor + "-" + tipo + ".png";
  }

  /**
   * Representacion textual de la carta (p.ej. "A de C").
   *
   * @return cadena con valor y palo
   */
  @Override
  public String toString() {
    return valor + " de " + tipo;
  }
}