package ec.edu.espol.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ec.edu.espol.List;

public class DoublyLinkedList<E> implements List<E> {
    DoublyNodeList<E> header;
    DoublyNodeList<E> last;
    public DoublyNodeList<E> getHeader() {
        return header;
    }
    public DoublyNodeList<E> getLast() {
        return last;
    }
    public void setHeader(DoublyNodeList<E> header) {
        this.header = header;
    }
    public void setLast(DoublyNodeList<E> last) {
        this.last = last;
    }

    @Override
    public boolean addFirst(E e) {//O(1)
        if(e==null){
            throw new IllegalArgumentException();
        }
        DoublyNodeList<E> newN= new DoublyNodeList<E>(e);
        if(isEmpty()){
                header=newN;
                last=newN;
                return true;
        }
        newN.setNext(header);
        header.setPrevious(newN);
        header=newN;
        return true;
    }

    @Override
    public boolean addLast(E e) {//O(1)
        if(e==null){
            throw new IllegalArgumentException();
        }
        DoublyNodeList<E> newN= new DoublyNodeList<E>(e);
        if(isEmpty()){
            header=newN;
            last=newN;
            return true;
        }
        last.setNext(newN);
        newN.setPrevious(last);
        last=newN;
        return true;
    }

    @Override
    public E removeFirst() {//O(1)
        if(header==null){
            throw new NoSuchElementException();
        }
        E element= header.getContent();
        header.getNext().setPrevious(null);
        header=header.getNext();
        if(header==null){
            last=null;
        }
        return element;
    }

    @Override
    public E removeLast() {//O(1)
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        E element= last.getContent();
        if(header==last){
            header=null;
            last=null;
            return element;
        }
        last.getPrevious().setNext(null);
        last=last.getPrevious();
        return element;
    }

    @Override
    public int size() {//O(n)
        int cont=0;
        for(DoublyNodeList<E> current= header; current!=null; current=current.getNext()){
            cont++;
        }
        return cont;
    }
    @Override
    public boolean isEmpty() {//O(1)
        return header==null;
    }
    @Override
    public void clear() {//O(1)
        header=null;
        last=null;
    }
    @Override
    public void add(int index, E element) {//O(n)
        if(index<0|| index>size()){
            throw new IndexOutOfBoundsException();
        }
        if(element==null){
            throw new IllegalArgumentException();
        }
        if(index==0){
            addFirst(element);
            return;
        }
        if(index==size()){
            addLast(element);
            return;
        }
        DoublyNodeList<E> current=header;
        for(int i=0; i<index; i++){
            current=current.getNext();
        }

        DoublyNodeList<E> newN= new DoublyNodeList<E>(element);

        newN.setPrevious(current.getPrevious());
        newN.setNext(current);
        current.getPrevious().setNext(newN);
        current.setPrevious(newN);
        return;
    }

    @Override
    public E remove(int index) {//O(n)
        if(index<0 || index>=size()){
            throw new IndexOutOfBoundsException();
        }
        if(index==0){
            return removeFirst();
        }
        if(index==size()-1){
            return removeLast();
        }
        DoublyNodeList<E> current=header;
        for(int i=0; i<index; i++){
            current=current.getNext();
        }
        E element = current.getContent();
        current.getPrevious().setNext(current.getNext());
        current.getNext().setPrevious(current.getPrevious());
        return element;
    }

    @Override
    public E get(int index) {//O(n)
        if(index<0 || index>=size()){
            throw new IndexOutOfBoundsException();
        }
        DoublyNodeList<E> current= header;
        for(int i=0; i<index; i++){
            current=current.getNext();
        }
        return current.getContent();
    }
    @Override
    public E set(int index, E element) {//O(n)
        if(index<0 || index>=size()){
            throw new IndexOutOfBoundsException();
        }
        if(element==null){
            throw new IllegalArgumentException();
        }
        DoublyNodeList<E> current= header;
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
            DoublyNodeList<E> current= header;
            @Override
            public boolean hasNext(){//O(1)
                return current!=null;
            }
            @Override
            public E next(){//O(1)
                E element= current.getContent();
                current=current.getNext();
                return element;
            }
        };
        return it;
   }

    
}
