package ec.edu.espol.ArrayList;
import java.util.Iterator;

import ec.edu.espol.List;

public class ArrayList <E> implements List<E>{
    private E[] elements;
    private int capacity=100;
    private int effectiveSize;
    //HOLA MILENA DEBERIA DORMIR
    @SuppressWarnings("unchecked")
    public ArrayList(){
        elements= (E[]) (new Object [capacity]);
        effectiveSize=0;
    }
     //HAY QUE COGERLE EL GOLPE A GIT O ME DARA TOCK
    @Override
    public boolean addFirst(E e) {//O(n)
        if(e==null){
            return false;
        }else if(isEmpty()){
            elements[effectiveSize++]=e;
            return true;
        }else if(isFull()){
            addCapacity();
        }
        for(int i=effectiveSize-1; i>=0; i--){
            elements[i+1]=elements[i];
        }
        elements[0]=e;
        effectiveSize++;
        return true;
    }

    private boolean isFull(){
        return effectiveSize==capacity;
    }

    private void addCapacity(){
        @SuppressWarnings("unchecked")
        E[] tmp= (E[]) (new Object[capacity*2]);
        for(int i=0; i<capacity; i++){
            tmp[i]= elements[i];
        }
        elements= tmp;
        capacity=capacity*2;
    }

    @Override
    public boolean addLast(E e) {//O(1)
        if(e==null){
            return false;
        }else if(isFull()){
            addCapacity();
        }
        elements[effectiveSize]=e;
        effectiveSize++;
        return true;
    }

    @Override
    public E removeFirst() {//O(n)
        if( isEmpty()){
            return null;
        }
        E elementr= elements[0];
        for(int i=1; i<effectiveSize; i++){
            elements[i-1]=elements[i];
        }
        effectiveSize--;
        return elementr;
    }

    @Override
    public E removeLast() {//O(1)
        if(isEmpty()){
            return null;
        }
        E elementLast= elements[effectiveSize-1];
        elements[effectiveSize-1]=null;
        effectiveSize--;
        return elementLast;
    }

    @Override
    public int size() {//O(1)
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {//O(1)
        return effectiveSize==0;
    }

    @Override
    public void clear() {//O(n)
        for(int i=0; i<effectiveSize; i++){
            elements[i]=null;
        }
        effectiveSize=0;
    }

    @Override
    public void add(int index, E element) {//O(n)
        if(element==null || index<0 || index> effectiveSize){
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        else if (isFull()) {
            addCapacity();
        }
        for(int i=effectiveSize-1; i>=index;i--){
            elements[i+1]=elements[i];
        }
        elements[index]=element;
        effectiveSize++;
    }

    @Override
    public E remove(int index) {//O(n)
        if(index<0 || index>=effectiveSize){
            throw new IndexOutOfBoundsException("Index: "+ index);
        }
        E elementremove= elements[index];
        for(int i=index; i<effectiveSize-1; i++){
            elements[i]=elements[i+1];
        }
        elements[effectiveSize-1]=null;
        effectiveSize--;
        return elementremove;
    }

    @Override
    public E get(int index) {//O(1)
        if(index<0 || index>=effectiveSize){
            throw new IndexOutOfBoundsException("Index: "+index);
        }
        return elements[index];
    }

    @Override
    public E set(int index, E element) {//O(1)
        if(index<0 || index>=effectiveSize){
            throw new IndexOutOfBoundsException("Index: "+ index);
        }
        E elementbefore= elements[index];
        elements[index]=element;
        return elementbefore;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            int cont=0;
            @Override
            public boolean hasNext() {//O(1)
                return cont<effectiveSize;
            }

            @Override
            public E next() {//O(1)
                return elements[cont++];
            }
        };
        return it;
    }
    
}
