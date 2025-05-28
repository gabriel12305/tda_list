package ec.edu.espol.Arbol.Arbol;

import java.util.LinkedList;

public class NodeTree<E> {
    private E content;
    private LinkedList<Tree<E>> children;

    public NodeTree(E content) {
        this.content = content;
        this.children= new LinkedList<>();
    }

    public E getContent() {
        return content;
    }

    public LinkedList<Tree<E>> getChildren() {
        return children;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public void setChildren(LinkedList<Tree<E>> children) {
        this.children = children;
    }

}
