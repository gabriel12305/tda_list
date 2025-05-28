package ec.edu.espol.Grafo.ListaAdyacencia;

import java.util.LinkedList;

public class Vertex<V,E> {
    private LinkedList<Edge<E,V>> edges;
    private V content;
    private boolean isVisited;

    public Vertex(V content){
        this.content=content;
        this.edges= new LinkedList<>();
    }

    public LinkedList<Edge<E, V>> getEdges() {
        return edges;
    }

    public void setEdges(LinkedList<Edge<E, V>> edges) {
        this.edges = edges;
    }

    public V getContent() {
        return content;
    }

    public void setContent(V content) {
        this.content = content;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }
    
}
