package ec.edu.espol.Mapa;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Mapa {
    
        public static void main(String[] args) {
            /* 
                Implementaciones
             * Vectoers de Bits
             * Listas enlazadas y ordenadas
             * Dispersión o Hashing - Mejor de los casos, el tiempo de cada operación es constante; En el peor de los casos es lineal
                  
             Cada fila se lo llama cubeta
            
             Requiere una función de hash o función de dispersión h(x):
            > Aritmetica modular: El indice se obtiene: clave % cantidad de cubetas
    
            > Mitad del cuadrado: Elevar la clave al cuadrado , toma los digitos de una posición determinadas 
                Si b=100 y la clave es = 256
                256'2= 65536 
                           <-
                La nueva clave es 63
                Ya que es de 100, se coje dos y es desde derecha a izquierda
    
            > Truncamiento: Tomar directamente d digitos de clave
                b=1000  y clave= 72588495 toma siempre 1,2,5 <-
                                    -  --
                indice= 598 
    
            > Plegamiento: Divide la clave en varios fragmentos y a cada trozo se le aplica una operación
                ejemplo: David -> suma los codigos en ascii % cantidad de cubeta= indice
    
            TRATAMIENTO DE COLISIONES
    
            >Hashing Abierto = Cada cubeta almacena una colección de elementos 
                h(n)=m
                m es el indice (clave)
    
            > Hashing Cerrado = Cada elemento va en una cubeta
                              = Si ha colision se aplica redispersion
                                Aplicar otra funcion hash de reserva 
                                Y se intenta de nuevo
    
            
            > Rehash Lineal => hi(x) = (h(x) + i) % B
            > Rehash Cuadrático => hi(x) = (h(x) + i'2) % B
            > Rehash aleatorio =>  hi(x) = (h(x) + k) % B ; siendo k un valor aleatorio
            > Rehash con doble función => hi(x) = (h(x) + i*h'(x)) % B
    
    
             */
    
            Map <Integer, String> map1= new HashMap<>();
            Map <Integer, String> map2= new LinkedHashMap<>();
            Map <Integer, String> map3= new TreeMap<>(); // Claves con orden natural | también
            
            Set<Integer> keySet= map1.keySet(); //Da un conjunto de todas las claves;
            map2.put(1, "Hola");
            map3.put(1, "Hola");
            keySet.add(5);
            
            /*
             * Insertar = map.put(key, value);
             * Recuperar el valor asociado auna clave = map.get(key);
             * 
             */
    }
}
