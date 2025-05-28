package ec.edu.espol.Arbol.Arbol;

public class Tree<E> {
    /*
    * Arbol | Estructura de datos no lineales.
    * P tiene enlace con M → P es el padre de M (Quien tiene el enlace siempre es el padre, quien conoce a los nodos es el padre)
    * Un hijo no sabe quien es su padre.
    * Todos los nodos solo tienen un padre. Menos el primer nodo que se llama Raiz
    * Los nodos Hojas son los nodos que no tienen hijos.
    */

    /*
    TERMINOLOGÍAS
    -> Camino: Secuencia de nodos conectados dentro de un arbol.
    -> Longitud del camino: # de nodos - 1 en un camino. 
    -> Altura del árbol: Es el nivel más alto.
        Un árbol con un solo nodo tiene altura 1
    -> Nivel (o profundidad) de un nodo: longitud de camino + 1 ó # de nodos en un camino
    -> Grado de un nodo: es el numero de hijos del nodo
    -> Grado de un árbol: máxima aridad de sus nodos.
        En un árbol el nodo con más hijos tiene 4 hijos, entoces el grado del árbol es 4.
    */

    /*Metodos que se pueden implementar:
    - public void Arbol_Vaciar(); Vaciar o inicializar el arbol
    - public void Arbol_Eliminar(); Eliminar un arbol
    - public boolean isEmpty();
    - public void PreOrden(); Recorrer un árbol
    - public void EnOrden(); Recorrer un árbol
    - public void PostOrden(); Recorrer un árbol
     */

    private NodeTree<E> root;// RAÍZ

    public NodeTree<E> getRoot() {
        return root;
    }

    public void setRoot(NodeTree<E> root) {
        this.root = root;
    }

    public Tree() {
        this.root=null;
    }

    public Tree(NodeTree<E> root) {
        this.root = root;
    } 

    
    

}
