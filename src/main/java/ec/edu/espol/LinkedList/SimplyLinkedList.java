
package ec.edu.espol.LinkedList;
import java.util.Iterator;
// CHAOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
//AAAAAAAA
import ec.edu.espol.List;
public class SimplyLinkedList<E> implements List<E> {
    
    private NodeList<E> header;
    private NodeList<E> last;


    public NodeList<E> getHeader() {
        return header;
    }
    public NodeList<E> getLast() {
        return last;
    }
    public void setHeader(NodeList<E> header) {
        this.header = header;
    }
    public void setLast(NodeList<E> last) {
        this.last = last;
    }

    @Override
    public boolean addFirst(E e) {//O(1)

        if(e!=null){
            NodeList<E> newNode= new NodeList<>(e);
            newNode.setNext(header);
            setHeader(newNode);
            if (last == null) { // La lista estaba vacía
                setLast(newNode);// El nuevo nodo es ahora el último
            }
            return true;
        }else{
            return false;
        }        
    }
    @Override
    public boolean addLast(E e) {//O(1)
        if(e!=null){
            NodeList<E> newNode= new NodeList<E>(e);
            newNode.setNext(null);
            if(header==null){
                setHeader(newNode);
                setLast(newNode);
            }else{
                last.setNext(newNode);
                setLast(newNode);
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    public E removeFirst() {//O(1)
        if(size()==0){
            return null;
        }
        E content= header.getContent();
        header= header.getNext();
        if(header==null){
            last=null;
        }
        return content;
    }

    @Override
    public E removeLast() {//O(n)
        if(header==null){
            return null;
        }
        if(header==last){
            E content= last.getContent();
            header=null;
            last=null;
            return content;
        }

        NodeList<E> current = header;
        while (current.getNext() != last) { 
            current = current.getNext();
        }

        E content = last.getContent(); 
        last = current; 
        last.setNext(null); 
        return content;
    }

    @Override
    public int size() {//O(n)
        int size=0;
        for(NodeList<E> n=header; n!=null ;n=n.getNext()){
            size++;
        }
        return size;
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
        if (element == null) {
            
            throw new IllegalArgumentException("Element cannot be null.");
        }
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        NodeList<E> newNode= new NodeList<E>(element);
        if(index==0){
            addFirst(element);
        }
        if(index==size()){
            addLast(element);
        }
        NodeList<E> current = header;
        for(int cont=0; cont<index-1; cont++){
            if(current!=null){
                current=current.getNext();
            }

        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
    }
    @Override
    public E remove(int index) {//O(n)
        if(index<0 || index>=size()){
            throw new IndexOutOfBoundsException();
        }
        if(index==0){
            removeFirst();
        }
        if(index==size()-1){
            removeLast();
        }
        NodeList<E> current = header;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        E content=current.getNext().getContent();
        current.setNext(current.getNext().getNext());

        return content;
    }
    @Override
    public E get(int index) {//O(n)
        if(index<0 || index>=size()){
            throw new IndexOutOfBoundsException();
        }
        NodeList<E> current= header;
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
        if (element == null) { 
            throw new IllegalArgumentException("Element cannot be null.");
        }
        NodeList<E> current=header;
        for(int i=0; i<index; i++){
            current=current.getNext();
        }
        E elementPre= current.getContent();
        current.setContent(element);
        return elementPre;
    }

    @Override
    public Iterator<E> iterator(){
        Iterator<E> it= new Iterator<>(){
            
            NodeList<E> current= header;
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

    public static void main(String[] args) {
        System.out.println("Milenaaaaaaaaa tienes que dormirrrrrrrrrrrrrrrr");
        System.out.println("DUERMEEEEEEEEEEEEEEEEEEEEEEEEEE");
        System.out.println("HAZME CASO COMO NUNCA");
        System.out.println("MIELANA NO TE HARA CASO");
    }
}
