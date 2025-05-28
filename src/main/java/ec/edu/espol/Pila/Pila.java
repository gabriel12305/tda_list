package ec.edu.espol.Pila;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Pila {
    public static void main(String[] args) {
        Stack<Integer> pila1= new Stack<>();//No es recomendable, al menos que nos aseguremos que no usemos otros métodos fuera de pila
        Deque<String> pila2= new ArrayDeque<>();//Deque Estático
        Deque<String> pila3= new LinkedList<>();//Deque Dinámico
        
        pila1.push(1);
        pila2.push("3");
        pila3.push("3");
        
        //Push - push(elemento) agrega y aumenta el tamaño
        //Peek - .peek() retorna el tope y disminuye el tamaño, si la pila esta vacia lanza error que se conoce como Subdesbordamiento
        //Pop -  .pop() Saca el ultimo elemento (osea lo elimina de la pila) y lo retorna, reduce el size y si esta vacia lanza error que se conoce como Subdesbordamiento
        //IsEmpty - .isEmpty() boolean
        // Size - .size() Retorna un int

        /*
         * Cuando se usa el iterador en Stack, itera normalmente una lista
         * Cuando se usa el iterador en Deque, si se mantiene el orden que tiene una pila
         * 
         * Pila:
         *  5 - Último elemento guardado / tope
         *  4  
         *  3  
         *  2 
         *  1 - Primer elemento guardado
         * Stack= 1 2 3 4 5
         * Deque= 5 4 3 2 1 
         */

    }
}
