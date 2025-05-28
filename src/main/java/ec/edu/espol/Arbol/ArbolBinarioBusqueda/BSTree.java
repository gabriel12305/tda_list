package ec.edu.espol.Arbol.ArbolBinarioBusqueda;

import java.util.Comparator;

public class BSTree <K,E>{
    private BSTNode<K,E> raiz;
    private Comparator<K> cmp;

    public BSTNode<K, E> getRaiz() {
        return raiz;
    }

    public Comparator<K> getCmp() {
        return cmp;
    }

    public void setRaiz(BSTNode<K, E> raiz) {
        this.raiz = raiz;
    }

    public void setCmp(Comparator<K> cmp) {
        this.cmp = cmp;
    }

    public BSTree(Comparator<K> cmp) {
        this.raiz= null;
        this.cmp = cmp;
    }

    public BSTree (Comparator<K> cmp, E content, K key){
        this.raiz= new BSTNode<K,E>(content, key);
        this.cmp=cmp;
    }

    public void leerRInOrder(){
        if(this.getRaiz()!=null){
            if(this.getRaiz().getLeft()!=null){
                this.getRaiz().getLeft().leerRInOrder();
            }
            System.out.println(this.getRaiz().getContent());
            if(this.getRaiz().getRight()!=null){
                this.getRaiz().getRight().leerRInOrder();
            }
        }
    }

    public void RecorrerEnPreOrden(){
        if(!this.isEmpty()){
            System.out.println(this.raiz.getContent());
            if(this.raiz.getLeft()!=null){
                this.raiz.getLeft().RecorrerEnPreOrden();
            }
            if(this.raiz.getRight()!=null){
                this.raiz.getRight().RecorrerEnPreOrden();
            }
        }
    }

    public boolean isEmpty(){
        return raiz==null;
    }

    /*  INSERTAR PIDIENDO UNA BSTREE
    public void insert(K key, E content){
        this.raiz= insertRecursive(key, content, this);    
    }

    private BSTNode<K,E> insertRecursive(K key, E content, BSTree<K,E> arbol){
        if(arbol.getRaiz()==null){
            return new BSTNode<K,E>(content, key);
        }
        if(cmp.compare(key, arbol.getRaiz().getKey())>0){
            if(arbol.getRaiz().getRight()==null){
                arbol.getRaiz().setRight(new BSTree<K,E>(cmp, content, key));
            }
            arbol.getRaiz().getRight().insert(key, content);
        }
        if(cmp.compare(key, arbol.getRaiz().getKey())<0){
            if(arbol.getRaiz().getLeft()==null){
                arbol.getRaiz().setLeft(new BSTree<K,E>(cmp, content, key));
            }
            arbol.getRaiz().getLeft().insert(key, content);
        }
        return raiz;
    }
*/
    public void insertRecursiveP(K key, E content){
        if(this.getRaiz()==null){
            this.raiz= new BSTNode<K,E>(content, key);
        }
        if(cmp.compare(key, this.getRaiz().getKey())>0){
            if(this.getRaiz().getRight()==null){
                this.getRaiz().setRight(new BSTree<K,E>(cmp, content, key));
            }
            this.getRaiz().getRight().insertRecursiveP(key, content);
        }
        if(cmp.compare(key, this.getRaiz().getKey())<0){
            if(this.getRaiz().getLeft()==null){
                this.getRaiz().setLeft(new BSTree<K,E>(cmp, content, key));
            }
            this.getRaiz().getLeft().insertRecursiveP(key, content);
        }
    }
    
    public BSTNode<K,E> searchNode(K key){
        if(this.getRaiz()==null || key==null){
            return null;
        }
        if(this.cmp.compare(key, this.getRaiz().getKey())==0){
            return this.getRaiz();
        }if(this.cmp.compare(key, this.getRaiz().getKey())<0){
            if(this.getRaiz().getLeft()!=null){
                return this.getRaiz().getLeft().searchNode(key);
            }
        }if(this.cmp.compare(key, this.getRaiz().getKey())>0){
            if(this.getRaiz().getRight()!=null){
                return this.getRaiz().getRight().searchNode(key);
            }
        }
        return null;
    }

    public BSTNode<K,E> deleteNode(K key){
        if(this.getRaiz() == null || key == null){
            return null;
        }
        if(this.getCmp().compare(key, this.getRaiz().getKey()) == 0){
            BSTNode<K,E> delete = this.getRaiz();
            if(this.getRaiz().getLeft() != null && this.getRaiz().getRight() == null){
                this.setRaiz(this.getRaiz().getLeft().getRaiz());
            }
            else if(this.getRaiz().getLeft() == null && this.getRaiz().getRight() != null){
                this.setRaiz(this.getRaiz().getRight().getRaiz());
            }
            else if(this.getRaiz().getLeft()==null && this.getRaiz().getRight()==null){
                this.setRaiz(null);
            }
            else{
                BSTree<K,E> largestOfLeft = reemplace(this.getRaiz().getLeft()); // Encontrar el nodo más grande
                BSTree<K,E> parentOfLargest = searchFindParent(this.getRaiz().getLeft(), largestOfLeft.getRaiz().getKey());// Encontrar al padre del nodo más grande en el subárbol izquierdo
                if (parentOfLargest != null) {
                    parentOfLargest.getRaiz().setRight(null); // Eliminar el nodo más grande de su padre
                }
                // Reemplazar el nodo a eliminar por el nodo más grande del subárbol izquierdo
                this.getRaiz().setKey(largestOfLeft.getRaiz().getKey());
                this.getRaiz().setContent(largestOfLeft.getRaiz().getContent());              
            }
    
            return delete;
        }
        if(this.getCmp().compare(key, this.getRaiz().getKey()) < 0){
            if(this.getRaiz().getLeft() != null){
                return this.getRaiz().getLeft().deleteNode(key);
            }
        }
        if(this.getCmp().compare(key, this.getRaiz().getKey()) > 0){
            if(this.getRaiz().getRight() != null){
                return this.getRaiz().getRight().deleteNode(key);
            }
        }
        return null;
    }

    private BSTree<K,E> reemplace(BSTree<K,E> arbol){
        // Se recorre hacia la derecha del subárbol izquierdo hasta encontrar el nodo más grande
        if(arbol.getRaiz().getRight() == null){
            return arbol;
        }
        return reemplace(arbol.getRaiz().getRight());
    }

    private BSTree<K,E> searchFindParent(BSTree<K,E> arbol, K key){
        if (arbol == null || arbol.getRaiz() == null) {
            return null;
        }
        if (arbol.getRaiz().getRight() != null && this.getCmp().compare(key, arbol.getRaiz().getRight().getRaiz().getKey()) == 0) {
            return arbol;
        }
        return searchFindParent(arbol.getRaiz().getRight(), key);
    }
    /* 
    public void TreeAVL(){

    }

    private void isIzqIzq(){

    }

    private void isDerDer(){

    }*/
    public static void main(String[] args) {
        Comparator<Integer> naturalComparator = Integer::compareTo;
        BSTree<Integer, Integer> tree1= new BSTree<>(naturalComparator,3,3);
        tree1.insertRecursiveP(5, 5);
        tree1.insertRecursiveP(6, 6);
        tree1.insertRecursiveP(4, 4);
        //tree1.leerRInOrder();
        tree1.RecorrerEnPreOrden();
        BSTNode<Integer, Integer> result= tree1.searchNode(4);
        System.out.println("El contenido que se esta buscando es: "+result.getContent());
        tree1.deleteNode(5);
        tree1.RecorrerEnPreOrden();

        //HOLAAA
        BSTree<Integer, Integer> tree = new BSTree<>(naturalComparator);

        // Insertar los nodos en el árbol
        tree.insertRecursiveP(34, 34);
        tree.insertRecursiveP(18, 18);
        tree.insertRecursiveP(90, 90);
        tree.insertRecursiveP(6, 6);
        tree.insertRecursiveP(25, 25);
        tree.insertRecursiveP(100, 100);
        tree.insertRecursiveP(20, 20);
        tree.insertRecursiveP(28, 28);
    
        // Mostrar el árbol en PreOrden para verificar la estructura
        System.out.println("Árbol en PreOrden:");
        tree.RecorrerEnPreOrden();
        tree.deleteNode(90);
        System.out.println("---");
        tree.RecorrerEnPreOrden();
    }
}
