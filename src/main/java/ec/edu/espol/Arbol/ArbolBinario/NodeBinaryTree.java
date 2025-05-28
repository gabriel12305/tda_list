package ec.edu.espol.Arbol.ArbolBinario;

public class NodeBinaryTree<E extends Comparable<E>> {
    private E content;
    private BinaryTree<E> left; //Hijo izquierdo
    private BinaryTree<E> right;//Hijo derecho
    
    public NodeBinaryTree(E content) {
        this.content = content;
        this.left=null;
        this.right=null;
    }

    public E getContent() {
        return content;
    }

    public BinaryTree<E> getLeft() {
        return left;
    }

    public BinaryTree<E> getRight() {
        return right;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public void setLeft(BinaryTree<E> left) {
        this.left = left;
    }

    public void setRight(BinaryTree<E> right) {
        this.right = right;
    }

}
