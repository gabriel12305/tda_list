package ec.edu.espol.Arbol.Huffman;

public class NodeTree {
    private HuffmanInfo data;
    private HuffmanTree left;
    private HuffmanTree right;

    public NodeTree(HuffmanInfo data2){
        this.data=data2;
        this.left=null;
        this.right=null;
    }

    public HuffmanInfo getData() {
        return data;
    }

    public HuffmanTree getLeft() {
        return left;
    }

    public HuffmanTree getRight() {
        return right;
    }

    public void setData(HuffmanInfo data) {
        this.data = data;
    }

    public void setLeft(HuffmanTree left) {
        this.left = left;
    }

    public void setRight(HuffmanTree right) {
        this.right = right;
    }

    
    
}
