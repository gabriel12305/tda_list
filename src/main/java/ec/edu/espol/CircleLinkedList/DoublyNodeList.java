package ec.edu.espol.CircleLinkedList;

public class DoublyNodeList<E> {
    private E content;
    private DoublyNodeList<E> next;
    private DoublyNodeList<E> previous;

    public DoublyNodeList(E content){
        this.content=content;
        this.next=this;
        this.previous=this;
    }

    public E getContent() {
        return content;
    }

    public DoublyNodeList<E> getNext() {
        return next;
    }

    public DoublyNodeList<E> getPrevious() {
        return previous;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public void setNext(DoublyNodeList<E> next) {
        this.next = next;
    }

    public void setPrevious(DoublyNodeList<E> previous) {
        this.previous = previous;
    }
}
