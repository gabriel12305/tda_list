package ec.edu.espol.CircleLinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import ec.edu.espol.List;

public class SimpleCircleLinkedList<E> implements List<E> {
    private NodeList<E> last;
    
    public NodeList<E> getLast() {
        return last;
    }

    public void setLast(NodeList<E> last) {
        this.last = last;
    }

    public NodeList<E> header(){
        return last.getNext();
    }

    @Override
    public boolean addFirst(E e) {
        if(e==null){
            throw new IllegalArgumentException();
        }
        NodeList<E> newN= new NodeList<E>(e);
        if(isEmpty()){
            this.last=newN;
            return true;
        }
        newN.setNext(last.getNext());
        last.setNext(newN);
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if(e==null){
            throw new IllegalArgumentException();
        }
        NodeList<E> newN= new NodeList<E>(e);
        if(isEmpty()){
            last=newN;
            return true;
        }
        newN.setNext(last.getNext());
        last.setNext(newN);
        last=newN;
        return true;        
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        E elementold= header().getContent();
        if(size()==1){
            last=null;
            return elementold;
        }
        last.setNext(header().getNext());
        return elementold;
    }

    @Override
    public E removeLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }   
        if(size()==1){
            E elementold= last.getContent();
            last=null;
            return elementold;
        }
        NodeList<E> current= header();
        while (current.getNext() != last) {
            current = current.getNext();
        }
        E elementold= current.getNext().getContent();
        current.setNext(header());
        last=current;
        return elementold;        
    }

    @Override
    public int size() {
        if(isEmpty()){
            return 0;
        }
        int cont=0;
        for(NodeList<E> current= header(); current!=last; current=current.getNext()){
            cont++;
        }
        return cont+1;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public void clear() {
        last=null;
    }

    @Override
    public void add(int index, E element) {
        if(index<0||index>size()){
            throw new IndexOutOfBoundsException();
        }
        if(index==0){
            addFirst(element);
            return;
        }
        if(index==size()){
            addLast(element);
            return;
        }
        NodeList<E> newN= new NodeList<E>(element);
        NodeList<E> current= last.getNext();
        for(int i=0; i<index-1; i++){
            current= current.getNext();
        }
        newN.setNext(current.getNext());
        current.setNext(newN);
    }

    @Override
    public E remove(int index) {
        if(index<0||index>=size()){
            throw new IndexOutOfBoundsException();
        }
        if(index==0){
            return removeFirst();
        }
        if(index==size()-1){
            return removeLast();
        }
        NodeList<E> current= header();
        for(int i=0; i<index-1; i++){
            current=current.getNext();
        }
        E elementold= current.getNext().getContent();
        current.setNext(current.getNext().getNext());
        return elementold;
    }

    @Override
    public E get(int index) {
        if(index<0||index>=size()){
            throw new IndexOutOfBoundsException();
        }
        NodeList<E> current= header();
        for(int i=0; i<index; i++){
            current=current.getNext();
        }
        return current.getContent();
    }

    @Override
    public E set(int index, E element) {
        if(index<0||index>=size()){
            throw new IndexOutOfBoundsException();
        }
        NodeList<E> current= header();
        for(int i=0; i<index; i++){
            current=current.getNext();
        }
        E elementold= current.getContent();
        current.setContent(element);
        return elementold;
    }

    @Override
    public Iterator<E> iterator(){
        Iterator<E> it= new Iterator<>(){
            int cont=0;
            NodeList<E> current= header();
            @Override 
            public boolean hasNext(){
                return cont< size();
            }
            @Override
            public E next(){
                E element = current.getContent(); 
                current = current.getNext(); 
                cont++;
                return element;
            }
        };
        return it;
    }

}
