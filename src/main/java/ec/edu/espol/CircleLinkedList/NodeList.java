package ec.edu.espol.CircleLinkedList;

public class NodeList<E> {
    private E content;
    private NodeList<E> next;

    public NodeList(E content){
        this.content=content;
        this.next=this;
    }

    public E getContent() {
        return content;
    }

    public NodeList<E> getNext() {
        return next;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public void setNext(NodeList<E> next) {
        this.next = next;
    }
}
