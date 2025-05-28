package ec.edu.espol.Arbol.ArbolBinarioBusqueda;

public class BSTNode<K,E> {
    private E content;
    private K key;
    private BSTree<K,E> left;
    private BSTree<K,E> right;

    public BSTNode (E content, K key){
        this.content=content;
        this.key=key;
        this.left=null;
        this.right=null;
    }
    public E getContent() {
        return content;
    }
    public K getKey() {
        return key;
    }
    public BSTree<K, E> getLeft() {
        return left;
    }
    public BSTree<K, E> getRight() {
        return right;
    }
    public void setContent(E content) {
        this.content = content;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public void setLeft(BSTree<K, E> left) {
        this.left = left;
    }
    public void setRight(BSTree<K, E> right) {
        this.right = right;
    }
}
