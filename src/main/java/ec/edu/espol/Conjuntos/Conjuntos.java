package ec.edu.espol.Conjuntos;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
public class Conjuntos {
    /*Operaciones Elementales
     Unión() ||
     Intersección() &&
     Diferencia()
     esMiembro()
     Vacia()

     Formas de representar un conjunto
     Usando vectores de bits
        Usa solo con un conjunto de enteros positivos
        1: Si el indice es un miembro del grupo
        0: Si el indice no es un miembro del grupo
        La union e interseccion se halla recorriendo en un for y indenzando el conjunto 
        C[i]= A[i] && B[i]


     Usando listas ordenadas
        No hay desperdicio de espacio
        Representa cualquier tipo

    Se lo puede hacer en 
    Interface set - No admiten duplicados
    TreeSet - Elementos ordenados
    HashSet - Elementos no ordenados 
     */

    Set<String>  set1= new HashSet<>(); // No hay garantía del orden de iteración
    Set<String>  set2= new TreeSet<>(); // Orden natural - Sino tiene orden lanza excepcion
    Set<String>  set3= new LinkedHashSet<>(); // Orden de inserción 

    //Todos se los guarda en un nuevo set
    // Union  => a.addAll(b);
    // Interseccion => a.retainAll(b); // Conserva solo los elementos que estan en a y también están en 'b'
    // Diferencia => a.removeAll(b);
}
