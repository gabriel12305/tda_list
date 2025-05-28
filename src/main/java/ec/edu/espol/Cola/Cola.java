package ec.edu.espol.Cola;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Cola {
    public static void main(String[] args) {
        Queue<String> cola1= new ArrayDeque<>();//ArrayDeque sirve para instanciar cola y pila
        Queue<String> cola2= new LinkedList<>();
        cola1.offer("Hola");
        cola2.peek();
        
        /* 
         * Métodos
         *                  1           2
         * Insertar -->   add(e)  ||  offer(e)
         * Remove    ->  remove() ||   poll()
         * Examinar  -> element() ||   peek()
         * 
         * 1 Cuando algo falla lanza una excepción
         * 2 Si algo falla no lanzan excepción y la unica forma de saber que fallo, es porque retornan un valor espcial, tipo false.
         */

         /*
          * Tipos de cola por prioridad
          Ascendente: Se remueve al más pequeño (Menor prioridad)
          Descendente: Se remueve al más grande (Mayor prioridad)
          */

        Queue<String> cola3= new PriorityQueue<>(); // Cola de prioridad
        cola3.offer("Gabo");
        //Por defecto, la priority queue utiliza el orden natural.
    }
}
