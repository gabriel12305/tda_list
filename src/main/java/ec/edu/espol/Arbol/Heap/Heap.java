package ec.edu.espol.Arbol.Heap;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Heap<E> {
    private Comparator<E> f;
    private E[] arreglo;
    private int cappacity=100;
    private int effectiveSize;
    private boolean isMax; //Si es ascendente o descedente

    // Desencolar: Extraer el elemento mas grande / pequeño del heap (raíz)
    // Encolar: Insertar un valor al hep y ubicarlo en la posicion correcta

    //HeapSort: Dado un Heap permite generar un arreglo/lista ordenado
    // Ajustar: Restablece lapropiedad de orden en un heap que se ha dañado
    // Construir Heap: Dado un arreglo que no represena un heap y convertirlo en un heap 


    @SuppressWarnings("unchecked")
    public Heap(Comparator<E> f, boolean isMax, Class<E> type) {
        this.f = f;
        this.isMax = isMax; 
        this.arreglo =(E[]) Array.newInstance(type, cappacity);
        this.effectiveSize = 0; 
    }

    private int getIzq(int i) {
        return (i*2)+1;
    }
    
    private int getDer(int i) {
        return (i*2)+2;
    }

    private int getPadre(int pos) {
        return (pos - 1) / 2;
    }
    

    private boolean isValidIndex(int pos) {
        return pos < effectiveSize;
    }
    
    private int comparar(E a, E b) {
        return isMax ? f.compare(a, b) : f.compare(b, a);
    }
    
    private void intercambiar(int i, int j) {
        E temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
    
    public void ajustar(int posRaiz) {
        int posIzq = getIzq(posRaiz); 
        int posDer = getDer(posRaiz); 
        int posMayor = posRaiz;       
    
        if (isValidIndex(posIzq) && comparar(arreglo[posIzq], arreglo[posMayor]) > 0) {
            posMayor = posIzq; 
        }
        if (isValidIndex(posDer) && comparar(arreglo[posDer], arreglo[posMayor]) > 0) {
            posMayor = posDer; 
        }
        if (posMayor != posRaiz) {
            intercambiar(posRaiz, posMayor);
            ajustar(posMayor); 
        }
    }

    public void construir(){
        int i;
        for(i=this.effectiveSize/2 -1; i>=0; i--){
            ajustar(i);
        }
    }

    public E desencolar(){
        E max;
        if(!isEmpty()){
            max=this.arreglo[0];
            intercambiar(0, this.effectiveSize-1);
            this.effectiveSize--;
            ajustar(0);
            return max;
        }
        return null;
    }
    
    public boolean isEmpty() {
        return effectiveSize == 0;
    }


    public void encolar(E nuevoValor) {
        if (effectiveSize >= cappacity) {
            expandirCapacidad();
        }
        arreglo[effectiveSize] = nuevoValor;
        subir(effectiveSize);
        effectiveSize++;
    }

    private void subir(int pos) {
        int posPadre = getPadre(pos);
        while (pos > 0 && comparar(arreglo[pos], arreglo[posPadre]) > 0) {
            intercambiar(pos, posPadre); 
            pos = posPadre; 
            posPadre = getPadre(pos); 
        }
    }
    
    private void expandirCapacidad() {
        cappacity=cappacity+cappacity/2;
        arreglo = Arrays.copyOf(arreglo, cappacity);
    }
    

    public static void imprimirHeap(Heap<Integer> heap) {
        for (int i = 0; i < heap.effectiveSize; i++) {
            System.out.print(heap.arreglo[i] + " ");
        }
        System.out.println();
    }

    public static <E> E[] HeapSort(E[] arreglo, Comparator<E> comparador) {
        @SuppressWarnings("unchecked")
        Heap<E> heap = new Heap<>(comparador, true, (Class<E>) arreglo[0].getClass());
        for (int i = 0; i < arreglo.length; i++) {
            heap.encolar(arreglo[i]);
        }
        @SuppressWarnings("unchecked")
        E[] newA = (E[]) Array.newInstance((Class<E>) arreglo[0].getClass(), arreglo.length); 
        int cont = 0;
        while (!heap.isEmpty()) {
            newA[cont] = heap.desencolar();
            cont++; 
        }
        return newA;
    }
    

    public static void main(String[] args) {
        Comparator<Integer> comparador = Integer::compareTo;
        Heap<Integer> heapMax = new Heap<>(comparador, true, Integer.class);
        System.out.println("Encolando valores...");
        heapMax.encolar(50);
        heapMax.encolar(30);
        heapMax.encolar(20);
        heapMax.encolar(60);
        heapMax.encolar(80);

        System.out.println("Heap después de encolar: ");
        imprimirHeap(heapMax);

        System.out.println("\nDesencolando valores...");
        while (!heapMax.isEmpty()) {
            System.out.println("Elemento extraído: " + heapMax.desencolar());
        }


        Integer[] arregloDesordenado = {50, 20, 60, 10, 40, 70, 30};
        Integer[] arregloOrdenado = HeapSort(arregloDesordenado, comparador);

        // Imprimir el arreglo ordenado
        System.out.println("\nArreglo ordenado:");
        System.out.println(Arrays.toString(arregloOrdenado));
    }
}
