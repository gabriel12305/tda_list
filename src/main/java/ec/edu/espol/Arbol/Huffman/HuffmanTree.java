package ec.edu.espol.Arbol.Huffman;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

@SuppressWarnings("hiding")
public class HuffmanTree {
    private NodeTree root;

    public HuffmanTree(NodeTree root){
        this.root=root;
    }

    public HuffmanTree(HuffmanInfo data){
        this.root= new NodeTree(data);
    }
    public HuffmanTree(){
        this.root= null;
    }

    public NodeTree getRoot() {
        return root;
    }

    public void setRoot(NodeTree root) {
        this.root = root;
    }

    public void buildTree(Queue<NodeTree> nodes){
        while(nodes.size()>1){
            
            NodeTree left= nodes.poll();
            left.getData().setCode(0);
            NodeTree right= nodes.poll();
            right.getData().setCode(1);
            
            int frequency= left.getData().getFrequency()+right.getData().getFrequency();
            String concat= left.getData().getContent().toString()+right.getData().getContent().toString();
            HuffmanInfo data= new HuffmanInfo(frequency, concat);
            NodeTree newNode= new NodeTree(data);
            newNode.setRight(new HuffmanTree(right));
            newNode.setLeft(new HuffmanTree(left));
            nodes.add(newNode);
        }
        this.root=nodes.poll();
    }

    public HashMap<String,String> buildCode(){
        
        HashMap<String, String> mapa= new HashMap<>();
        addMap(mapa, this, "");
        return mapa;
    }

    private void addMap(HashMap<String, String> mapa, HuffmanTree arbol, String recorrido){
        if (arbol == null) {
            return;
        }
        if(arbol.isLeaf()){
            String key=arbol.getRoot().getData().getContent();
            mapa.put(key, recorrido);
        }else{
            if(arbol.getRoot().getLeft()!=null){
                addMap(mapa, arbol.getRoot().getLeft(), recorrido+ String.valueOf(arbol.getRoot().getData().getCode()));
            }
            if(arbol.getRoot().getRight()!=null){
                addMap(mapa, arbol.getRoot().getRight(), recorrido+String.valueOf(arbol.getRoot().getData().getCode()));
            }
        }
    }
    
    public boolean isLeaf(){
        return (isEmpty())? false: (this.root.getLeft()==null && this.root.getRight()==null);
    }

    public boolean isEmpty(){
        return root==null;
    }

    public void RecorrerEnOrden(){ 
        if(!this.isEmpty()){
            if(this.root.getLeft()!=null){
                this.root.getLeft().RecorrerEnOrden();
            }
            System.out.println(this.root.getData().getContent());
            if(this.root.getRight()!=null){
                this.root.getRight().RecorrerEnOrden();
            }
        }
    }

    public static void main(String[] args) {
        Queue<NodeTree> cola= new PriorityQueue<>();
        NodeTree node1= new NodeTree(new HuffmanInfo(3, "A"));
        NodeTree node2= new NodeTree(new HuffmanInfo(1, "B"));
        NodeTree node3= new NodeTree(new HuffmanInfo(2, "C"));
        NodeTree node4= new NodeTree(new HuffmanInfo(3, "D"));
        cola.add(node1);
        cola.add(node2);
        cola.add(node3);
        cola.add(node4);

        HuffmanTree tree1= new HuffmanTree();
        tree1.buildTree(cola);
        tree1.RecorrerEnOrden();

    }
}
