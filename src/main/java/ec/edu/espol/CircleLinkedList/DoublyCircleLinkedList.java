package ec.edu.espol.CircleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ec.edu.espol.List;

public class DoublyCircleLinkedList<E> implements List<E> {
    private DoublyNodeList<E> last;

    public DoublyNodeList<E> header(){
        return last.getNext();
    }

    public DoublyNodeList<E> getLast() {
        return last;
    }

    public void setLast(DoublyNodeList<E> last) {
        this.last = last;
    }
    
    @Override
    public boolean addFirst(E e) {
        if(e==null){
            throw new IllegalArgumentException();
        }
        DoublyNodeList<E> newN= new DoublyNodeList<E>(e);
        if(isEmpty()){
            last=newN;
            return  true;
        }

        newN.setNext(header());
        newN.setPrevious(last);
        header().setPrevious(newN);
        last.setNext(newN);
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if(e==null){
            throw new IllegalArgumentException();
        }
        DoublyNodeList<E> newN= new DoublyNodeList<E>(e);
        if(isEmpty()){
            last=newN;
            return  true;
        }
        newN.setPrevious(last);
        newN.setNext(header());
        last.setNext(newN);
        header().setPrevious(newN);
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
        header().getNext().setPrevious(last);
        return elementold;
    }

    @Override
    public E removeLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        E elementold= last.getContent();
        if(size()==1){
            last=null;
            return elementold;
        }
        header().setPrevious(last.getPrevious());
        last.getPrevious().setNext(header());
        last=last.getPrevious();
        return elementold;
    }

    @Override
    public int size() {
        if(isEmpty()){
            return 0;
        }
        int cont=0;
        for(DoublyNodeList<E> current= header(); current!=last; current=current.getNext()){
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
        if(index<0 || index>size()){
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
        DoublyNodeList<E> current=header();
        for(int i=0; i<index; i++){
            current= current.getNext();
        }
        DoublyNodeList<E> newN= new DoublyNodeList<E>(element);
        newN.setNext(current);
        newN.setPrevious(current.getPrevious());
        current.getPrevious().setNext(newN);
        current.setPrevious(newN);        
    }

    @Override
    public E remove(int index) {
        if(index<0 || index>=size()){
            throw new IndexOutOfBoundsException();
        }
        if(index==0){
            return removeFirst();
        }
        if(index==size()-1){
            return removeLast();
        }

        DoublyNodeList<E> current= header();
        for(int i=0; i<index; i++){
            current=current.getNext();
        }
        E elementold= current.getContent();
        current.getPrevious().setNext(current.getNext());
        current.getNext().setPrevious(current.getPrevious());
        return elementold;
    }

    @Override
    public E get(int index) {
        if(index<0||index>=size()){
            throw new IndexOutOfBoundsException();
        }
        DoublyNodeList<E> current= header();
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
        DoublyNodeList<E> current= header();
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
            DoublyNodeList<E> current = header();
            int cont=0;
            @Override
            public boolean hasNext(){
                return cont<size();
            }
            @Override
            public E next(){
                E element= current.getContent();
                current=current.getNext();
                cont++;
                return element;
            }
        };
        return it;
    }
}
