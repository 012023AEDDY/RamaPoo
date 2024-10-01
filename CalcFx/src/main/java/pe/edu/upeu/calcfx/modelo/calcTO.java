package pe.edu.upeu.calcfx.modelo;// esta clase va llamar o "importar" en la INTERFACE

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//genera los get and sseter
@AllArgsConstructor// depende de los atributos que tengamos
@NoArgsConstructor// constructor sin parametros de entrada
@Entity//indicará el id y tambien hará autoincremento de los id en base de datos
@Table(name = "calculadora")// para sobre nombre y no poner calTO sino calculadora
public class calcTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)//solito va incrementar
     @Id
    Long id;
    String num1;
    String num2;
    char operador;
    String resultado;


    @Override
    public String toString() {
        return "calcTO{" +
                "num1='" + num1 + '\'' +
                ", num2='" + num2 + '\'' +
                ", operador=" + operador +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}
