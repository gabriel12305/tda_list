package ec.edu.espol.Grafo.ListaAdyacencia;

public class Edge<E,V> {
    private Vertex<V,E> source;
    private Vertex<V,E> target;
    private int weight;
    private E data;

    public Edge(Vertex<V,E> source,Vertex<V,E> target, int weight, E data){
        this.source=source;
        this.target=target;
        this.weight=weight;
        this.data=data;
    }

    public Edge(Vertex<V, E> source, Vertex<V, E> target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        this.data=null;
    }

    public Edge(Vertex<V, E> source, Vertex<V, E> target, E data) {
        this.source = source;
        this.target = target;
        this.data = data;
        this.weight=-1;
    }

    public Edge(Vertex<V, E> source, Vertex<V, E> target) {
        this.source = source;
        this.target = target;
        this.data=null;
        this.weight=-1;
    }

    public Vertex<V, E> getSource() {
        return source;
    }

    public Vertex<V, E> getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    public E getData() {
        return data;
    }

    public void setSource(Vertex<V, E> source) {
        this.source = source;
    }

    public void setTarget(Vertex<V, E> target) {
        this.target = target;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setData(E data) {
        this.data = data;
    }

    
}
