package ec.edu.espol.Grafo.ListaAdyacencia;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;




public class Graph<V,E> {
    private LinkedList<Vertex<V,E>> listavertices;
    private boolean isDirected;
    private Comparator<V> cmp;
    
    public Graph(Comparator<V> cmp, boolean isDirected){
        this.cmp=cmp;
        this.isDirected=isDirected;
        this.listavertices= new LinkedList<>();
    }

    public boolean addVertex(V content){
        if(content==null || isExist(content)){
            return false;
        }
        listavertices.add(new Vertex<V,E>(content));
        return true;
    }

    public boolean isExist(V content){
            for(int i=0; i<this.listavertices.size();i++){
                if(this.cmp.compare(this.listavertices.get(i).getContent(), content)==0){
                    return true;
                };
            }
            return false;
        }
    
        private Vertex<V,E> findVertex(V content){
            for(int i=0; i<this.listavertices.size();i++){
                if(this.cmp.compare(this.listavertices.get(i).getContent(), content)==0){
                    return listavertices.get(i);
                };
            }
            return null;
        }
    
        public boolean connect(V content1, V content2, int weight, E data){
            if(content1==null || content2==null){
                return false;
            }else{
                Vertex<V,E> current1= findVertex(content1);
                Vertex<V,E> current2= findVertex(content2);
                if(current1==null || current2==null){
                    return false;
                }else{
                    current1.getEdges().add(new Edge<>(current1, current2, weight,data));
                    if(!isDirected){
                        current2.getEdges().add(new Edge<>(current2, current1, weight,data));
                    }
                    return true;
                }
            }
        }

        public LinkedList<Vertex<V, E>> getListavertices() {
            return listavertices;
        }

        public void setListavertices(LinkedList<Vertex<V, E>> listavertices) {
            this.listavertices = listavertices;
        }

        public boolean isDirected() {
            return isDirected;
        }

        public void setDirected(boolean isDirected) {
            this.isDirected = isDirected;
        }

        public Comparator<V> getCmp() {
            return cmp;
        }

        public void setCmp(Comparator<V> cmp) {
            this.cmp = cmp;
        }
    
        

        public void dijkstra(Graph<V, E> grafo, Vertex<V, E> inicio) {
            if (grafo == null || inicio == null || !isExist(inicio.getContent())) {
                System.out.println("Grafo o vértice inicial inválido.");
                return;
            }

            Map<Vertex<V, E>, Integer> distancias = new HashMap<>();
            Map<Vertex<V, E>, Vertex<V, E>> previos = new HashMap<>();
            // Cola de prioridad para seleccionar el vértice con la menor distancia
            PriorityQueue<Vertex<V, E>> cola = new PriorityQueue<>(Comparator.comparingInt(distancias::get));

            // Inicializamos las distancias: infinito para todos excepto el inicio
            for (Vertex<V, E> vertice : grafo.getListavertices()) {
                distancias.put(vertice, vertice.equals(inicio) ? 0 : Integer.MAX_VALUE);
                previos.put(vertice, null); // Sin camino previo al inicio
            }

            // Agregamos el vértice inicial a la cola
            cola.add(inicio);

            // Procesamos los vértices en la cola de prioridad
            while (!cola.isEmpty()) {
                Vertex<V, E> actual = cola.poll();//C

                // Relajamos las distancias de los vecinos
                for (Edge<E, V> arista : actual.getEdges()) {//E
                    Vertex<V, E> vecino = arista.getTarget();//E
                    int nuevaDistancia = distancias.get(actual) + arista.getWeight();//4+3=7
                    if (nuevaDistancia < distancias.get(vecino)) {//7<8
                        // Actualizamos la distancia mínima
                        distancias.put(vecino, nuevaDistancia);
                        // Actualizamos el vértice previo
                        previos.put(vecino, actual);
                        if (!cola.contains(vecino)) {
                            cola.add(vecino);
                        }
                    }
                }
            }

            // Imprimimos las distancias y caminos
            System.out.println("Distancias desde el vértice inicial:");
            for (Map.Entry<Vertex<V, E>, Integer> entrada : distancias.entrySet()) {
                System.out.println("Vértice: " + entrada.getKey().getContent() + ", Distancia: " + entrada.getValue());
            }

            System.out.println("Caminos más cortos desde el vértice inicial:");
            for (Vertex<V, E> vertice : grafo.getListavertices()) {
                if (!vertice.equals(inicio)) {
                    System.out.print("Hasta " + vertice.getContent() + ": ");
                    imprimirCamino(vertice, previos);
                    System.out.println();
                }
            }
        }

        private void imprimirCamino(Vertex<V, E> destino, Map<Vertex<V, E>, Vertex<V, E>> previos) {
            if (previos.get(destino) != null) {
                imprimirCamino(previos.get(destino), previos); 
            }
            System.out.print(destino.getContent() + " "); 
        }
        
        public List<String> getContactos(String nombre, Graph<String, String> grafoComunicaciones, Graph<String, String> grafoTransferencias) {
            // Buscar el vértice de la persona en el grafo de comunicaciones
            Vertex<String, String> persona = grafoComunicaciones.findVertex(nombre);
            Vertex<String, String> personaT = grafoTransferencias.findVertex(nombre);
            if (persona == null) {
                return new ArrayList<>(); // Si no existe, devolver lista vacía
            }
        
            // Mapas para almacenar la frecuencia, el dinero enviado y recibido
            Map<String, Integer> frecuenciaComunicaciones = new HashMap<>();
            Map<String, Integer> dineroEnviado = new HashMap<>();
            Map<String, Integer> dineroRecibido = new HashMap<>();
        
            // Calcular frecuencia de comunicaciones
            for (Edge<String, String> edge : persona.getEdges()) {
                String contacto = edge.getTarget().getContent();
                frecuenciaComunicaciones.put(contacto, frecuenciaComunicaciones.getOrDefault(contacto, 0) + 1);
            }
            // Calcular dinero enviado
            for (Edge<String, String> edge : personaT.getEdges()) {
                String contacto = edge.getTarget().getContent();
                dineroEnviado.put(contacto, 
                    dineroEnviado.getOrDefault(contacto, 0) + edge.getWeight());
            }
            // Calcular dinero recibido (recorrer todos los vértices del grafo de transferencias)
            for (Vertex<String, String> vertice : grafoTransferencias.getListavertices()) {
                for (Edge<String, String> edge : vertice.getEdges()) {
                    if (edge.getTarget().getContent().equals(nombre)) {
                        String contacto = vertice.getContent();
                        dineroRecibido.put(contacto, 
                            dineroRecibido.getOrDefault(contacto, 0) + edge.getWeight());
                    }
                }
            }
            // Crear una lista de contactos con sus detalles
            List<String> contactos = new ArrayList<>();
            for (String contacto : frecuenciaComunicaciones.keySet()) {
                
                int frecuencia = frecuenciaComunicaciones.get(contacto);
                int enviado = dineroEnviado.getOrDefault(contacto, 0);
                int recibido = dineroRecibido.getOrDefault(contacto, 0);
        
                contactos.add(contacto + " - Frecuencia: " + frecuencia + ", Enviado: " + enviado + ", Recibido: " + recibido);
            }
            // Ordenar la lista por frecuencia de mayor a menor
            contactos.sort((c1, c2) -> {
                int freq1 = Integer.parseInt(c1.split(",")[0].split(": ")[1]);
                int freq2 = Integer.parseInt(c2.split(",")[0].split(": ")[1]);
                return Integer.compare(freq2, freq1); // Orden descendente
            });
            return contactos;
        }
        
        public List<String> detectarInteraccionesSospechosas(Graph<String, String> grafoComunicaciones, Graph<String, String> grafoTransferencias, int n) {
            List<String> interaccionesSospechosas = new ArrayList<>();

            // Recorrer todos los pares de vértices en el grafo de comunicaciones
            for (Vertex<String, String> origen : grafoComunicaciones.getListavertices()) {
                for (Edge<String, String> edge : origen.getEdges()) {
                    String destino = edge.getTarget().getContent();
                    int frecuencia = (int) origen.getEdges().stream().filter(e -> e.getTarget().getContent().equals(destino)).count();

                    // Verificar si la frecuencia cumple con el mínimo requerido
                    if (frecuencia >= n) {
                        // Verificar si hay un camino de transferencia entre origen y destino
                        if (hayCaminoDeTransferencia(origen.getContent(), destino, grafoTransferencias)) {
                            interaccionesSospechosas.add("Origen: " + origen.getContent() + ", Destino: " + destino);
                        }
                    }
                }
            }
            return interaccionesSospechosas;
        }

        private boolean hayCaminoDeTransferencia(String origen, String destino, Graph<String, String> grafoTransferencias) {
            Set<String> visitados = new HashSet<>();
            Queue<String> cola = new LinkedList<>();
            cola.add(origen);

            while (!cola.isEmpty()) {
                String actual = cola.poll();
                if (actual.equals(destino)) {
                    return true;
                }

                visitados.add(actual);

                Vertex<String, String> verticeActual = grafoTransferencias.findVertex(actual);
                if (verticeActual != null) {
                    for (Edge<String, String> edge : verticeActual.getEdges()) {
                        String vecino = edge.getTarget().getContent();
                        if (!visitados.contains(vecino)) {
                            cola.add(vecino);
                        }
                    }
                }
            }

            return false;
        }

        public void dfs(V start) {
            Vertex<V, E> inicio = findVertex(start);
            if (inicio == null) {
                System.out.println("El vértice inicial no existe.");
                return;
            }
            Set<Vertex<V, E>> visitados = new HashSet<>();
            dfsHelper(inicio, visitados);
        }
        
        private void dfsHelper(Vertex<V, E> actual, Set<Vertex<V, E>> visitados) {
            visitados.add(actual);
            System.out.print(actual.getContent() + " ");
            for (Edge<E, V> edge : actual.getEdges()) {
                Vertex<V, E> vecino = edge.getTarget();
                if (!visitados.contains(vecino)) {
                    dfsHelper(vecino, visitados);
                }
            }
        }
        
        public void bfs(V start) {
            Vertex<V, E> inicio = findVertex(start);
            if (inicio == null) {
                System.out.println("El vértice inicial no existe.");
                return;
            }
            Set<Vertex<V, E>> visitados = new HashSet<>();
            Queue<Vertex<V, E>> cola = new LinkedList<>();
            cola.add(inicio);
            visitados.add(inicio);
        
            while (!cola.isEmpty()) {
                Vertex<V, E> actual = cola.poll();
                System.out.print(actual.getContent() + " ");
                for (Edge<E, V> edge : actual.getEdges()) {
                    Vertex<V, E> vecino = edge.getTarget();
                    if (!visitados.contains(vecino)) {
                        cola.add(vecino);
                        visitados.add(vecino);
                    }
                }
            }
        }
        
        public int calcularTotalKilometros() {
            int total = 0;
            Set<String> edgesContados = new HashSet<>();
            for (Vertex<V, E> vertex : listavertices) {
                for (Edge<E, V> edge : vertex.getEdges()) {
                    String id = vertex.getContent() + "-" + edge.getTarget().getContent();
                    String idReverso = edge.getTarget().getContent() + "-" + vertex.getContent();
                    if (!edgesContados.contains(id) && !edgesContados.contains(idReverso)) {
                        total += edge.getWeight();
                        edgesContados.add(id);
                    }
                }
            }
            return total;
        }
        

        public static void main(String[] args) {
            // Crear el grafo
            Graph<String, String> grafo = new Graph<>(String::compareTo, false);
            System.out.println("Taller 8 - Milena Pazmiño");
            // Agregar vértices
            grafo.addVertex("Home");
            grafo.addVertex("A");
            grafo.addVertex("B");
            grafo.addVertex("C");
            grafo.addVertex("D");
            grafo.addVertex("E");
            grafo.addVertex("F");
            grafo.addVertex("Universidad");
    
            // Agregar aristas con los pesos dados
            grafo.connect("Home", "A", 3, null);
            grafo.connect("Home", "B", 2, null);
            grafo.connect("Home", "C", 5, null);
            grafo.connect("A", "D", 3, null);
            grafo.connect("B", "D", 1, null);
            grafo.connect("B", "E", 6, null);
            grafo.connect("C", "E", 2, null);
            grafo.connect("D", "F", 4, null);
            grafo.connect("E", "F", 1, null);
            grafo.connect("E", "Universidad", 4, null);
            grafo.connect("F", "Universidad", 2, null);
    
            // Recorrido en profundidad desde "Home"
            System.out.println("Recorrido en profundidad desde 'Home':");
            grafo.dfs("Home");
    
            // Recorrido en amplitud desde "Home"
            System.out.println("\n\nRecorrido en anchura desde 'Home':");
            grafo.bfs("Home");
    
            // Calcular el total de kilómetros
            int totalKilometros = grafo.calcularTotalKilometros();
            System.out.println("\n\nTotal de kilómetros de calles en la ciudad: " + totalKilometros + " km");
        }
    }




        



