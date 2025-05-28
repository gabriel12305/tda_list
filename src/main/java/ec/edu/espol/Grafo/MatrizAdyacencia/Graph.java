package ec.edu.espol.Grafo.MatrizAdyacencia;

import java.util.Comparator;

public class Graph<V,E> {
    private int effectiveSize=0;
    private int capacity=100;
    private boolean isDirected;
    private V[] vertices;
    private int [][] adjacencyMatrix;
    private Comparator<V> cmp;
    private E[][] dataMatrix;

    @SuppressWarnings("unchecked")
    public Graph(Comparator<V> cmp, boolean isDirected){
        this.cmp=cmp;
        this.isDirected=isDirected;
        vertices= (V[]) new Object[capacity];
        this.adjacencyMatrix= new int [capacity][capacity];
        this.dataMatrix= (E[][]) new Object[capacity][capacity];
        initAdjacencyMatrix();
    }

    private void initAdjacencyMatrix(){
        for(int i=0; i<capacity; i++){
            for( int j=0; j<capacity; j++){
                adjacencyMatrix[i][j]=-1;
                dataMatrix[i][j]=null;
            }
        }
    }

    public boolean isFull(){
        return effectiveSize==capacity;
    }

    public boolean addVertix(V content){
        if(content==null || isExist(content)){
            return false;
        }
        if(isFull()){
            addCapacity();
        }
        vertices[effectiveSize]=content;
        effectiveSize++;
        return true;
    }

    public boolean isExist(V content){
        for(int i=0; i<effectiveSize;i++){
            V currentContent= vertices[i];
            if(this.cmp.compare(currentContent, content)==0){
                return true;
            }
        }
        return false;
    }

    private int findVertex(V content){
        for(int i=0; i<effectiveSize;i++){
            V currentContent= vertices[i];
            if(this.cmp.compare(currentContent, content)==0){
                return i;
            }
        }
        return -1;
    }
    
    private void addCapacity(){
        int newCapacity= capacity+capacity/2;
        @SuppressWarnings("unchecked")
        V[] newV= (V[]) new Object[newCapacity]; 
        int[][] newM= new int[newCapacity][newCapacity];
        for(int i=0; i<newCapacity; i++){
            newV[i]= vertices[i];
            for(int j=0; j<newCapacity; j++){
                newM[i][j]= adjacencyMatrix[i][j];
            }
        }
        vertices=newV;
        adjacencyMatrix=newM;
        capacity=newCapacity;
    }


    public void connectV(V one, V two, E data, int peso){
        int index= findVertex(one);
        int index2= findVertex(two);
        if(peso==0){
            if(data!=null){
                if(isDirected){
                    adjacencyMatrix[index][index2]=1;
                    dataMatrix[index][index2]=data;
                }else{
                    adjacencyMatrix[index][index2]=1;
                    dataMatrix[index][index2]=data;
                    adjacencyMatrix[index2][index]=1;
                    dataMatrix[index2][index]=data;
                }
            }else{
                if(isDirected){
                    adjacencyMatrix[index][index2]=1;
                }else{
                    adjacencyMatrix[index][index2]=1;
                    adjacencyMatrix[index2][index]=1;
                }
            }
        }else{
            if(data!=null){
                if(isDirected){
                    adjacencyMatrix[index][index2]=peso;
                    dataMatrix[index][index2]=data;
                }else{
                    adjacencyMatrix[index][index2]=peso;
                    dataMatrix[index][index2]=data;
                    adjacencyMatrix[index2][index]=peso;
                    dataMatrix[index2][index]=data;
                }
            }else{
                if(isDirected){
                    adjacencyMatrix[index][index2]=peso;
                }else{
                    adjacencyMatrix[index][index2]=peso;
                    adjacencyMatrix[index2][index]=peso;
                }
            }
        }
        
    }

    public int[][] marshallAlgoritmo(Graph<V,E> grafo){
        int [][] A0= initMatriz(grafo);

        for(int k=0; k<grafo.effectiveSize; k++){
            for(int i=0; i<grafo.effectiveSize; i++){
                for(int j=0; j<grafo.effectiveSize; j++){
                    A0[i][j]= min(A0[i][j], A0[i][k]+A0[k][j]);
                }
            }
        }
        return A0;
    }

    public int min(int value1, int value2){
        if(value1<value2){
            return value1;
        }
        return value2;
    }

    public int[][] initMatriz(Graph<V,E> graph){
        int[][] matrix= new int[graph.effectiveSize][graph.effectiveSize];
        for(int i=0; i<graph.effectiveSize; i++){
            for( int j=0; j<graph.effectiveSize; j++){
                if(graph.adjacencyMatrix[i][j]==-1){//SI HALLA UN -1 LO CAMBIAPOR INFIITO
                    matrix[i][j]=Integer.MAX_VALUE;
                }else{
                    matrix[i][j]=graph.adjacencyMatrix[i][j];// copia tal cual
                }

            }
        }
        return matrix;
    }


    public boolean[][] algoritmoW(Graph<V,E> grafo){
        boolean[][] mA0= initMatrizMo(grafo);
        for(int k=0; k<grafo.effectiveSize; k++){
            for(int i=0; i<grafo.effectiveSize; i++){
                for(int j=0; j<grafo.effectiveSize; j++){
                    mA0[i][j]= (mA0[i][j] || (mA0[i][k] && mA0[k][j]));
                }
            }
        }
        return mA0;
    }

    public boolean[][] initMatrizMo(Graph<V,E> graph){
        boolean[][] matrix= new boolean[graph.effectiveSize][graph.effectiveSize];
        for(int i=0; i<graph.effectiveSize; i++){
            for( int j=0; j<graph.effectiveSize; j++){
                if(graph.adjacencyMatrix[i][j]==-1){
                    matrix[i][j]=false;
                }else{
                    matrix[i][j]=true;
                }
            }
        }
        return matrix;
    }

    
    public class Main {
        public static void main(String[] args) {
            Comparator<String> stringComparator = String::compareTo;

            // Crear un grafo no dirigido
            Graph<String, String> graph = new Graph<>(stringComparator, false);

            // Agregar vértices
            System.out.println("Agregando vértices...");
            graph.addVertix("A");
            graph.addVertix("B");
            graph.addVertix("C");
            graph.addVertix("D");

            // Intentar agregar un vértice existente
            System.out.println("Intentando agregar vértice existente (A): " + graph.addVertix("A"));

            // Establecer conexiones
            System.out.println("Conectando vértices...");
            graph.connectV("A", "B","",0);
            graph.connectV("A", "C","",0);
            graph.connectV("B", "D","",0);

            // Intentar conectar vértices inexistentes
            System.out.println("Intentando conectar vértices inexistentes...");
            try {
                graph.connectV("X", "Y","",0);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error: Uno o ambos vértices no existen en el grafo.");
            }

        
        }

        
    }

}
