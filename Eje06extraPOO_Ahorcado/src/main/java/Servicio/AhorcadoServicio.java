/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Entidad.Ahorcado;
import java.util.Arrays;
import java.util.Scanner;
import java.applet.Applet;
import java.applet.AudioClip;

/*Metodo crearJuego(): le pide la palabra al usuario y cantidad de jugadas máxima. Con la palabra del usuario, pone la longitud de la 
palabra, como la longitud del vector. Después ingresa la palabra en el vector, letra por letra, quedando cada letra de la palabra en 
un índice del vector. Y también, guarda en cantidad de jugadas máximas, el valor que ingresó el usuario y encontradas en 0.
- Método longitud(): muestra la longitud de la palabra que se debe encontrar. Nota: buscar como se usa el vector.length.
- Método buscar(letra): este método recibe una letra dada por el usuario y busca si la letra ingresada es parte de la palabra o no. 
También informará si la letra estaba o no.
- Método encontradas(letra): que reciba una letra ingresada por el usuario y muestre cuantas letras han sido encontradas y cuantas 
le faltan. Este método además deberá devolver true si la letra estaba y false si la letra no estaba, ya que, cada vez que se busque una 
letra que no esté, se le restará uno a sus oportunidades.
- Método intentos(): para mostrar cuantas oportunidades le queda al jugador.
- Método juego(): el método juego se encargará de llamar todos los métodos previamente mencionados e informará cuando el usuario 
descubra toda la palabra o se quede sin intentos. Este método se llamará en el main.
 */
public class AhorcadoServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void juego(Ahorcado juego) {
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");
        System.out.println("Bienvenid@ al AHORCADO");
        crearJuego(juego);
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");
        System.out.println("QUE COMIENCE EL JUEGO......");
        longitud(juego);
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");
        for (int i = 0; i < juego.getVector1().length; i++) {
            System.out.print(" _");
        }
        System.out.println("");
        buscar(juego);
    }

    public void crearJuego(Ahorcado juego) {
        //System.out.println("Ingrese la palabra para jugar: ");
        String [] vectorAlmacen = {"objeto", "arrays", "identificadores", "atributo", "modificador"};
        juego.setVector2(vectorAlmacen);
        juego.setVector1(vectorAlmacen[(int)(Math.random()*(5-0+1))].toLowerCase().split(""));
        juego.setCantidadVidas(juego.getVector1().length);
        juego.setLetrasEncontradas(0);
    }

    public void longitud(Ahorcado juego) {
        System.out.println("Usted deberá adivinar una palabra que contiene " + (juego.getVector1().length) + " letras.");
    }

    public void buscar(Ahorcado juego) {       

        String[] vectorOriginal = juego.getVector1();
        String[] vectorAhorcado = new String[juego.getVector1().length];

        for (int i = 0; i < juego.getVector1().length; i++) {
            vectorAhorcado[i] = "_";
        }
        int errores = 0;
        do {
            System.out.println("Ingrese una letra: ");
            String letra = leer.next();
            boolean bandera = false;            
            
            for (int i = 0; i < juego.getVector1().length; i++) {
                if (letra.equalsIgnoreCase(vectorOriginal[i])) {
                    vectorAhorcado[i] = letra;
                    bandera = true;
                    juego.setLetrasEncontradas(juego.getLetrasEncontradas() + 1);
                }
            }
            juego.setCantidadVidas(juego.getCantidadVidas() - 1);
            
            if (bandera) {
                System.out.println("La letra se ha encontrado! :)");
            }else{
                System.out.println("La letra no se ha encontrado! :(");
                errores++;
            }
            
            System.out.println("---------------------------------");
            for (int i = 0; i < juego.getVector1().length; i++) {
                System.out.print(" "+vectorAhorcado[i]);
            }
            System.out.println("");
            System.out.println("---------------------------------");
            encontradas(juego);
            intentos(juego);
            System.out.println("Usted ha cometido " + errores + " errores.");
            
        } while (juego.getCantidadVidas() != 0 && juego.getLetrasEncontradas() != juego.getVector1().length);
        if (Arrays.equals(vectorOriginal, vectorAhorcado)) {
            System.out.println("FELICIDADES HAS GANADO! ƪ(ړײ)‎ƪ​​ ");
        }else{
            System.out.println("""
                               Has fallado
                                 +---+
                                 |   |
                                 O   |
                                /|\\  |
                                / \\  |
                                     |
                               ========='''] """);
        }
    }

    public void encontradas(Ahorcado juego) {
        System.out.println("Usted ha encontrado " + juego.getLetrasEncontradas() + " cantidad de letras.");
    }

    public void intentos(Ahorcado juego) {
        System.out.println("A usted le quedan " + juego.getCantidadVidas() + " intentos.");
    }
}
