package ec.edu.espol.Arbol.ArbolBinario;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



public class BinaryTree<E extends Comparable<E>>{
    /*
    * Cada nodo tiene máximo 2 hijos
    * Un arból puede estar vacío, pero si no lo está consta de tres partes:
    * 1. Nodo raiz
    * 2. Dos subarboles: Izquierdo
    * 3. Dos subarboles: Derecho
    * 
    */
    private NodeBinaryTree <E> root;

    public BinaryTree() {
        this.root=null;
    }

    public BinaryTree(E e) {
        this.root= new NodeBinaryTree<>(e);
    }

    public NodeBinaryTree<E> getRaiz() {
        return root;
    }

    public void setRaiz(NodeBinaryTree<E> root) {
        this.root = root;
    }

    public boolean isEmpty(){
        return root==null;
    }

    public boolean isLeaf(){//Cuando es una hoja | Se debe validar cuando esta vacío que retorne false.
        return (isEmpty())? false: (this.root.getLeft()==null && this.root.getRight()==null);
    }

    
    /*
    RECORRIDOS DE UN ARBOL BINARIO - Aplican Recursividad 
    - Preorden -> Raiz - subarbol izq - subarbol der
    - Enorden ->  subarbol izq - raiz - subarbol der
    - PostOrden -> subarbol izq - subarbol der - raiz
     */

    public void RecorrerEnPreOrden(){
        //1. Imprimir la raiz
        //2. Recorrer EnPreOrden el hijo Izquierdo
        //3. Recorrer EnPreOrden el hijo derecho

        if(!this.isEmpty()){//Validar que el árbol no esté vacío
            System.out.println(this.root.getContent());
            if(this.root.getLeft()!=null){//Si tiene hijos que sea recursivo, pero antes verificar la exiistencia de que existe un hijo.
                this.root.getLeft().RecorrerEnPreOrden();
            }
            if(this.root.getRight()!=null){
                this.root.getRight().RecorrerEnPreOrden();
            }
        }
    }

    public void RecorrerEnPostOrden(){ 
        //1. Recorrer EnPreOrden el hijo Izquierdo
        //2. Recorrer EnPreOrden el hijo derecho
        //3. Imprimir la raiz

        if(!this.isEmpty()){//Validar que el árbol no esté vacío
            
            if(this.root.getLeft()!=null){//Si tiene hijos que sea recursivo, pero antes verificar la exiistencia de que existe un hijo.
                this.root.getLeft().RecorrerEnPostOrden();
            }
            if(this.root.getRight()!=null){
                this.root.getRight().RecorrerEnPostOrden();
            }
            System.out.println(this.root.getContent());
        }
    }

    public void RecorrerEnOrden(){ 
        //1. Recorrer EnPreOrden el hijo Izquierdo
        //2. Imprimir la raiz 
        //3. Recorrer EnPreOrden el hijo derecho

        if(!this.isEmpty()){//Validar que el árbol no esté vacío
            
            if(this.root.getLeft()!=null){//Si tiene hijos que sea recursivo, pero antes verificar la exiistencia de que existe un hijo.
                this.root.getLeft().RecorrerEnOrden();
            }
            System.out.println(this.root.getContent());
            if(this.root.getRight()!=null){
                this.root.getRight().RecorrerEnOrden();
            }
            
        }
    }

    public int countLeavesRecursive(){
        if(this.isEmpty()){
            return 0;
        }
        if(this.isLeaf()){
            return 1;
        }else{
            int leftLeaves=0;
            int rightLeaves=0;
            if(this.root.getLeft()!=null){
                leftLeaves= this.root.getLeft().countLeavesRecursive();
            }if(this.root.getRight()!=null){
                rightLeaves= this.root.getRight().countLeavesRecursive();
            }            
            return leftLeaves+rightLeaves;
        }
    }

    public int countLeavesIterative(){
        int leaves=0; //contador
        Stack<BinaryTree<E>> s = new Stack<>(); //TDApila

        if(!this.isEmpty()){
            s.push(this);
            while(!s.isEmpty()){
                BinaryTree<E> tree= s.pop();
                if(tree.isLeaf()){//Si es hoja, se incrementa
                    leaves++;
                }
                if(tree.getRaiz().getLeft()!=null){
                    s.push(tree.getRaiz().getLeft());
                }
                if(tree.getRaiz().getRight()!=null){
                    s.push(tree.getRaiz().getRight());
                }
            }
        }
        return leaves;
    }


    public NodeBinaryTree<E> iterativeSearch(E target) {
        Stack<BinaryTree<E>> stack = new Stack<>();
    
        if (!this.isEmpty() && target != null) {
            stack.push(this); 
            while (!stack.isEmpty()) {
                BinaryTree<E> currentTree = stack.pop(); 
                NodeBinaryTree<E> currentNode = currentTree.getRaiz(); 
                
                if (currentNode.getContent().equals(target)) {
                    return currentNode; 
                }
                if (currentNode.getRight() != null) {
                    stack.push(currentNode.getRight());
                }
                if (currentNode.getLeft() != null) {
                    stack.push(currentNode.getLeft());
                }
            }
            return null;
        }
        return null;
    }
    
    
    public NodeBinaryTree<E> recursiveSearch(E target){
        if(target==null || this.getRaiz()==null){
            return null;
        }

        if(this.getRaiz().getContent().equals(target)){
            return this.getRaiz();
        }

        if (this.getRaiz().getLeft() != null) {
            NodeBinaryTree<E> leftResult = this.getRaiz().getLeft().recursiveSearch(target);
            if (leftResult != null) { 
                return leftResult;
            }
        }

        if (this.getRaiz().getRight() != null) {
            NodeBinaryTree<E> rightResult = this.getRaiz().getRight().recursiveSearch(target);
            if (rightResult != null) { // Si se encuentra en el subárbol derecho, retornar el nodo
                return rightResult;
            }
        }
        return null;
    }
    
    
    public E iterativeGetMin(){
        if(this.getRaiz()==null){
            return null;
        }

        E minimovalor=this.getRaiz().getContent();
        Stack<BinaryTree<E>> stack= new Stack<>();
        stack.push(this);

        while(!stack.isEmpty()){
            BinaryTree<E> currentNode= stack.pop();
            if(currentNode.isLeaf()){
                if(currentNode.getRaiz().getContent().compareTo(minimovalor)<0){
                    minimovalor=currentNode.getRaiz().getContent();
                }
            }
            else if(currentNode.getRaiz().getLeft()!=null){
                stack.push(currentNode.getRaiz().getLeft());
            }

            else if(currentNode.getRaiz().getRight()!=null){
                stack.push(currentNode.getRaiz().getRight());
            }
        }
        return minimovalor;
    }
    
    
    public E recursiveGetMin(){
        if(this.getRaiz()==null){
            return null;
        }
        E minimo=this.getRaiz().getContent();
        if(this.getRaiz().getLeft()!=null){
            E currentLeft= this.getRaiz().getLeft().recursiveGetMin();
            if(currentLeft!=null && currentLeft.compareTo(minimo)<0){
                minimo=currentLeft;
            }
        }
        if(this.getRaiz().getRight()!=null){
            E currentRight= this.getRaiz().getRight().recursiveGetMin();
            if(currentRight!=null && currentRight.compareTo(minimo)<0){
                minimo=currentRight;
            }
        }
        return minimo;
    }
    
    public int iterativeCountDescendants(){
        if(this.root==null || this.isLeaf()){
            return 0;
        }
        Stack<BinaryTree<E>> stack = new Stack<>();
        stack.push(this);
        int count=0;
        while(!stack.isEmpty()){
            BinaryTree<E> currentNode= stack.pop();
            
            if(currentNode.getRaiz().getLeft()!=null){
                count++;
                stack.push(currentNode.getRaiz().getLeft());
            }
            if(currentNode.getRaiz().getRight()!=null){
                count++;
                stack.push(currentNode.getRaiz().getRight());
            }
        }
        return count;
    }

    public int recursiveCountDescendants(){
        if(this.getRaiz()==null || this.isLeaf()){
            return 0;
        }
        int countLeft=0;
        int countRight=0;
        if(this.getRaiz().getLeft()!=null){
            countLeft=1+this.getRaiz().getLeft().recursiveCountDescendants();
        }
        if(this.getRaiz().getRight()!=null){
            countRight=1+this.getRaiz().getRight().recursiveCountDescendants();
        }
        return countLeft+countRight;
    }

    public NodeBinaryTree<E> iterativeFindParent(NodeBinaryTree<E> nodo){
        if (nodo == null || this.getRaiz() == null || this.getRaiz().getContent() == nodo.getContent()) {
            return null;
        }
        
        Stack<BinaryTree<E>> stack=new Stack<>();
        stack.push(this);
        while(!stack.isEmpty()){
            BinaryTree<E> current=stack.pop();
            if(current.getRaiz().getLeft()!=null){
                  
                if(current.getRaiz().getLeft().getRaiz().getContent()==nodo.getContent()){
                    return current.getRaiz();
                }  
                stack.push(current.getRaiz().getLeft());
            }

            if(current.getRaiz().getRight()!=null){
                stack.push(current.getRaiz().getRight());
                if(current.getRaiz().getRight().getRaiz().getContent()==nodo.getContent()){
                    return current.getRaiz();
                }
            }
        }
        return null;
    }

    public NodeBinaryTree<E> recursiveFindParent(NodeBinaryTree<E> nodo){
        if(nodo == null || this.getRaiz() == null){
            return null;
        }
        
        if(this.getRaiz().getLeft()!=null){//1
            if(this.getRaiz().getLeft().getRaiz().getContent().equals(nodo.getContent())){
                return this.getRaiz();
            }
            this.getRaiz().getLeft().recursiveFindParent(nodo);
        }

        if(this.getRaiz().getRight()!=null){
            if(this.getRaiz().getRight().getRaiz().getContent().equals(nodo.getContent())){
                return this.getRaiz();
            }
            this.getRaiz().getRight().recursiveFindParent(nodo);
        }
        return null;        
    }


    public int iterativeCountLevels() {
        if (this.getRaiz() == null) {
            return 0; 
        }
    
        Queue<BinaryTree<E>> queue = new LinkedList<>(); 
        queue.add(this); 
        int levels = 0; 
    
        while (!queue.isEmpty()) { 
            int nodesAtCurrentLevel = queue.size(); 
            levels++;
    
            for (int i = 0; i < nodesAtCurrentLevel; i++) {
                BinaryTree<E> currentNode = queue.poll(); 
                
                if (currentNode.getRaiz().getLeft() != null) {
                    queue.add(currentNode.getRaiz().getLeft());
                }
                if (currentNode.getRaiz().getRight() != null) {
                    queue.add(currentNode.getRaiz().getRight());
                }
            }
        }
    
        return levels; 
    }

    public int recursiveCountLevels(){
        if(this.getRaiz()==null){
            return 0;
        }
        if(this.isLeaf()){
            return 1;
        }else{
            int countLeft=0;
            int countRight=0;
            if(this.getRaiz().getLeft()!=null){
                countRight=this.getRaiz().getLeft().recursiveCountLevels();
            }

            if(this.getRaiz().getRight()!=null){
                countLeft=this.getRaiz().getRight().recursiveCountLevels();
            }
            if(countRight>countLeft){
                return countRight+1;
            }
            if (countLeft>countRight){
                return countLeft+1;
            }
            return countLeft+1;
        }
    }

    public boolean iterativeIsLefty(){
        if(this.getRaiz()==null || this.isLeaf()){
            return true; 
        }

        Stack<BinaryTree<E>> stack= new Stack<>();
        stack.push(this);
        int countRight=0;
        int countLeft=0;
        while(!stack.isEmpty()){
            BinaryTree<E> currentNode= stack.pop();
            if(currentNode.getRaiz().getLeft()!=null){
                stack.push(currentNode.getRaiz().getLeft());
                countLeft++;
            }
            if(currentNode.getRaiz().getRight()!=null){
                stack.push(currentNode.getRaiz().getRight());
                countRight++;
            }
        }
        return (countLeft>((countRight+countLeft)/2))?true:false;
    }

    public boolean recursiveIsLefty(){
        if(this.getRaiz()==null){
            return false;
        }
        int countRight=0;
        int countLeft=0;

        if(this.getRaiz().getLeft()!=null){
            countLeft++;
            this.getRaiz().getLeft().recursiveIsLefty();
            
        }
        if(this.getRaiz().getRight()!=null){
            countRight++;
            this.getRaiz().getRight().recursiveIsLefty();
        }

        return (countLeft>((countLeft+countRight)/2))? true:false;
    }

    public boolean iterativeIsIdentical(BinaryTree<E> tree){
        if(tree.getRaiz()==null || this.getRaiz()==null){
            return false;
        }
        Stack<BinaryTree<E>> stack1= new Stack<>();
        Stack<BinaryTree<E>> stack2= new Stack<>();
        stack1.push(this);
        stack2.push(tree);
        while(!stack1.isEmpty()){
            BinaryTree<E> currrentthis= stack1.pop();
            BinaryTree<E> currenttree= stack2.pop();
            if(!currrentthis.getRaiz().getContent().equals(currenttree.getRaiz().getContent())){
                return false;
            }

            if(currrentthis.getRaiz().getLeft()!=null && currenttree.getRaiz().getLeft()!=null){
                stack1.push(currrentthis.getRaiz().getLeft());
                stack2.push(currenttree.getRaiz().getLeft());
            }else if(currrentthis.getRaiz().getLeft()!=null || currenttree.getRaiz().getLeft()!=null){
                return false;
            }

            if(currrentthis.getRaiz().getRight()!=null && currenttree.getRaiz().getRight()!=null){
                stack1.push(currrentthis.getRaiz().getRight());
                stack2.push(currenttree.getRaiz().getRight());
            }else if(currrentthis.getRaiz().getRight()!=null || currenttree.getRaiz().getRight()!=null){
                return false;
            }
        }
        return true;
    }
    
    public boolean recursiveIsIdentical(BinaryTree<E> tree){
        if (this.getRaiz() == null && tree.getRaiz() == null) {
            return true;
        }
        if (this.getRaiz() == null || tree.getRaiz() == null) {
            return false;
        }
        if(!this.getRaiz().getContent().equals(tree.getRaiz().getContent())){
            return false;
        }
        boolean answerRight = true, answerLeft = true;
        if(this.getRaiz().getLeft()!=null || tree.getRaiz().getLeft()!=null){
            answerLeft=this.getRaiz().getLeft().recursiveIsIdentical(tree.getRaiz().getLeft());
        }
        if(this.getRaiz().getRight()!=null || tree.getRaiz().getRight()!=null){
            answerRight=this.getRaiz().getRight().recursiveIsIdentical(tree.getRaiz().getRight());
        }
        return answerLeft && answerRight;
    }

    public String iterativeLargestValueOfEachLevel(){
        if(this.getRaiz()==null){
            return null;
        }
        StringBuilder sc= new StringBuilder();
        Queue<BinaryTree<E>> queue= new LinkedList<>();
        queue.add(this);
        while(!queue.isEmpty()){
            int currentCount=queue.size();
            E value=null;
            for(int i=0; i<currentCount; i++){
                BinaryTree<E> currentNode= queue.poll();
                if(value==null || currentNode.getRaiz().getContent().compareTo(value)>0){
                    value=currentNode.getRaiz().getContent();
                }
                if(currentNode.getRaiz().getLeft()!=null){
                    queue.add(currentNode.getRaiz().getLeft());
                }
                if(currentNode.getRaiz().getRight()!=null){
                    queue.add(currentNode.getRaiz().getRight());
                }
            }
            sc.append(value);
        }
        return sc.toString();
    }

    
    public String recursiveLargestValueOfEachLevel() {
        if (this.getRaiz() == null) {
            return null; 
        }
    
        Queue<BinaryTree<E>> queue = new LinkedList<>();
        queue.add(this);
        StringBuilder sc = new StringBuilder();
        
        processLevel(queue, sc);
    
        return sc.toString().trim(); 
    }
    
   
    private void processLevel(Queue<BinaryTree<E>> queue, StringBuilder sc) {
        if (queue.isEmpty()) {
            return;
        }

        int currentCount = queue.size(); 
        E maxValue = null; 
    
        for (int i = 0; i < currentCount; i++) {
            BinaryTree<E> currentNode = queue.poll();
    
            if (maxValue == null || currentNode.getRaiz().getContent().compareTo(maxValue) > 0) {
                maxValue = currentNode.getRaiz().getContent();
            }
            if (currentNode.getRaiz().getLeft() != null) {
                queue.add(currentNode.getRaiz().getLeft());
            }
            if (currentNode.getRaiz().getRight() != null) {
                queue.add(currentNode.getRaiz().getRight());
            }
        }
        sc.append(maxValue).append(" ");
        processLevel(queue, sc);
    }
    
    public int iterativeCountNodesWithOnlyChild(){
        if(this.isLeaf() || this.getRaiz()==null){
            return 0;
        }
        Stack<BinaryTree<E>> stack= new Stack<>();
        stack.push(this);
        int count=0;

        while(!stack.isEmpty()){
            BinaryTree<E> current= stack.pop();

            if(current.getRaiz().getLeft()!=null || current.getRaiz().getLeft()!=null){
                count++;
            }

            if(current.getRaiz().getLeft()!=null){
                stack.push(current.getRaiz().getLeft());
            }

            if(current.getRaiz().getRight()!=null){
                stack.push(current.getRaiz().getRight());
            }
        }

        return count;
    }


    public static BinaryTree<String> buildExpressionTree(String expression){
        Stack <BinaryTree<String>> stack= new Stack<>();
        String [] tokens= expression.split(" ");
        for(int i=0; i<tokens.length; i++){
            String token= tokens[i];
            if( isOrperand(token)){
                stack.push(new BinaryTree<>(token));
            }else{
                BinaryTree<String> padre= new BinaryTree<>(token); 
                if(!stack.isEmpty()){
                    padre.getRaiz().setRight(stack.pop());
                }
                if(!stack.isEmpty()){
                    padre.getRaiz().setLeft(stack.pop());
                }
                stack.push(padre);
            }   
        }
        return stack.pop();
    } 

    private static boolean isOrperand(String token){
        return (!isOperador(token));
    }

    private static boolean isOperador(String token){
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    public static int evaluationExpressionTree(BinaryTree<String> expressiontree){
        if(expressiontree.isEmpty()){
            return 0;
        }else if(expressiontree.isLeaf()){
            return Integer.parseInt(expressiontree.getRaiz().getContent());
        }else{
            Integer leftValue=0;
            Integer rightValue=0;
            if(expressiontree.getRaiz().getLeft()!=null){
                leftValue= evaluationExpressionTree(expressiontree.getRaiz().getLeft());
            }
            if(expressiontree.getRaiz().getRight()!=null){
                rightValue=evaluationExpressionTree(expressiontree.getRaiz().getRight());
            }
            String operador= expressiontree.getRaiz().getContent();
            return value(leftValue, rightValue, operador);
        }
    }

    private static Integer value(Integer leftValue, Integer rightValue, String operador){
        switch (operador) {
            case "+":
                return leftValue+rightValue;
            case "-":
                return leftValue-rightValue;
            case "*":
                return leftValue*rightValue;
            default:
            return leftValue/rightValue;
        }
    }

    public static void main(String[] args) {
       // Crear un árbol con números enteros
        BinaryTree<Integer> tree = new BinaryTree<>(10);
        tree.getRaiz().setLeft(new BinaryTree<>(5));
        tree.getRaiz().setRight(new BinaryTree<>(20));

        System.out.println("Mínimo iterativo: " + tree.iterativeGetMin());
        System.out.println("Mínimo recursivo: " + tree.recursiveGetMin());
        // Crear un árbol con cadenas de texto
        BinaryTree<String> stringTree = new BinaryTree<>("mango");
        stringTree.getRaiz().setLeft(new BinaryTree<>("apple"));
        stringTree.getRaiz().setRight(new BinaryTree<>("orange"));

        System.out.println("Mínimo iterativo (String): " + stringTree.iterativeGetMin());
        System.out.println("Mínimo Recursivo (String): " + stringTree.recursiveGetMin());

        System.out.println("Contar descendientes Iterativo "+tree.iterativeCountDescendants());
        System.out.println("Contar descendientes Recursivo "+tree.recursiveCountDescendants());


        NodeBinaryTree<Integer> prueba = new NodeBinaryTree<Integer>(5);
        System.out.println("El padre del nodo es: (Iterativo) " + tree.iterativeFindParent(prueba).getContent());
        System.out.println("El padre del nodo es: (Recursivo) " + tree.recursiveFindParent(prueba).getContent());
        
        //NIVELES
        BinaryTree<Integer> tree2 = new BinaryTree<>(1); // Raíz

        // Nivel 2
        tree2.getRaiz().setLeft(new BinaryTree<>(2));
        tree2.getRaiz().setRight(new BinaryTree<>(3));

        // Nivel 3
        tree2.getRaiz().getLeft().getRaiz().setLeft(new BinaryTree<>(4));
        tree2.getRaiz().getLeft().getRaiz().setRight(new BinaryTree<>(5));
        tree2.getRaiz().getRight().getRaiz().setLeft(new BinaryTree<>(6));
        tree2.getRaiz().getRight().getRaiz().setRight(new BinaryTree<>(7));

        // Nivel 4
        tree2.getRaiz().getLeft().getRaiz().getLeft().getRaiz().setLeft(new BinaryTree<>(8));
        tree2.getRaiz().getLeft().getRaiz().getLeft().getRaiz().setRight(new BinaryTree<>(9));
        tree2.getRaiz().getLeft().getRaiz().getRight().getRaiz().setLeft(new BinaryTree<>(10));
        tree2.getRaiz().getLeft().getRaiz().getRight().getRaiz().setRight(new BinaryTree<>(11));

        tree2.getRaiz().getRight().getRaiz().getLeft().getRaiz().setLeft(new BinaryTree<>(12));
        tree2.getRaiz().getRight().getRaiz().getLeft().getRaiz().setRight(new BinaryTree<>(13));
        tree2.getRaiz().getRight().getRaiz().getRight().getRaiz().setLeft(new BinaryTree<>(14));
        tree2.getRaiz().getRight().getRaiz().getRight().getRaiz().setRight(new BinaryTree<>(15));


        System.out.println("Cuantos niveles tiene por Iterativo: "+tree2.iterativeCountLevels());
        System.out.println("Cuantos niveles tiene por Recursivo: "+tree2.recursiveCountLevels());
        
        // ES ZURDO
        BinaryTree<Integer> tree3 = new BinaryTree<>(1); // Raíz con valor 1
        tree3.getRaiz().setLeft(new BinaryTree<>(2)); // Hijo izquierdo de la raíz
        tree3.getRaiz().setRight(new BinaryTree<>(3)); // Hijo derecho de la raíz

        // Subárbol izquierdo de tree3
        tree3.getRaiz().getLeft().getRaiz().setLeft(new BinaryTree<>(4));
        tree3.getRaiz().getLeft().getRaiz().setRight(new BinaryTree<>(5));

        // Subárbol derecho de tree3
        tree3.getRaiz().getRight().getRaiz().setLeft(new BinaryTree<>(6));
        tree3.getRaiz().getRight().getRaiz().setRight(new BinaryTree<>(7));

        // Probar si tree3 es zurdo
        System.out.println("¿Es el árbol tree3 zurdo? " + tree3.iterativeIsLefty());
        System.out.println("¿Es el árbol tree3 zurdo? " + tree3.recursiveIsLefty());
        // Crear otro árbol binario (tree4)
        BinaryTree<Integer> tree4 = new BinaryTree<>(10); // Raíz con valor 10
        tree4.getRaiz().setLeft(new BinaryTree<>(20)); // Hijo izquierdo de la raíz
        tree4.getRaiz().getLeft().getRaiz().setLeft(new BinaryTree<>(40)); // Hijo izquierdo de 20
        tree4.getRaiz().getLeft().getRaiz().setRight(new BinaryTree<>(50)); // Hijo derecho de 20
        tree4.getRaiz().getLeft().getRaiz().getLeft().getRaiz().setLeft(new BinaryTree<>(80)); // Hijo izquierdo de 40

        // Probar si tree4 es zurdo
        System.out.println("¿Es el árbol tree4 zurdo? " + tree4.iterativeIsLefty());
        System.out.println("¿Es el árbol tree4 zurdo? " + tree4.recursiveIsLefty());


        // ES IDENTICAL
        BinaryTree<Integer> tree5 = new BinaryTree<>(1); 
        tree5.getRaiz().setLeft(new BinaryTree<>(2)); 
        tree5.getRaiz().setRight(new BinaryTree<>(3)); 
        tree5.getRaiz().getLeft().getRaiz().setLeft(new BinaryTree<>(4));
        tree5.getRaiz().getLeft().getRaiz().setRight(new BinaryTree<>(5));
        tree5.getRaiz().getRight().getRaiz().setLeft(new BinaryTree<>(6));
        tree5.getRaiz().getRight().getRaiz().setRight(new BinaryTree<>(7));

        BinaryTree<Integer> tree6 = new BinaryTree<>(1); 
        tree6.getRaiz().setLeft(new BinaryTree<>(2)); 
        tree6.getRaiz().setRight(new BinaryTree<>(3)); 
        tree6.getRaiz().getLeft().getRaiz().setLeft(new BinaryTree<>(4));
        tree6.getRaiz().getLeft().getRaiz().setRight(new BinaryTree<>(5));
        tree6.getRaiz().getRight().getRaiz().setLeft(new BinaryTree<>(6));
        tree6.getRaiz().getRight().getRaiz().setRight(new BinaryTree<>(7));

        System.out.println("¿Son tree5 y tree6 idénticos? (Iterativo)" + tree5.iterativeIsIdentical(tree6));
        System.out.println("¿Son tree5 y tree6 idénticos (Recursivo)? " + tree5.recursiveIsIdentical(tree6));
       
        BinaryTree<Integer> tree7 = new BinaryTree<>(1);
        tree7.getRaiz().setLeft(new BinaryTree<>(2)); 
        tree7.getRaiz().setRight(new BinaryTree<>(3)); 
        tree7.getRaiz().getLeft().getRaiz().setLeft(new BinaryTree<>(8));
        tree7.getRaiz().getLeft().getRaiz().setRight(new BinaryTree<>(5));
        tree7.getRaiz().getRight().getRaiz().setLeft(new BinaryTree<>(6));
        tree7.getRaiz().getRight().getRaiz().setRight(new BinaryTree<>(7));

        System.out.println("¿Son tree5 y tree7 idénticos (Iterativo)? " + tree5.iterativeIsIdentical(tree7));
        System.out.println("¿Son tree5 y tree7 idénticos (Recursivo)? " + tree5.recursiveIsIdentical(tree7));
        

        //LARGESRVALUE

    
        BinaryTree<Integer> tree8 = new BinaryTree<>(15);
        tree8.getRaiz().setLeft(new BinaryTree<>(10));
        tree8.getRaiz().setRight(new BinaryTree<>(20));
        tree8.getRaiz().getLeft().getRaiz().setLeft(new BinaryTree<>(8));
        tree8.getRaiz().getLeft().getRaiz().setRight(new BinaryTree<>(12));
        tree8.getRaiz().getRight().getRaiz().setLeft(new BinaryTree<>(17));
        tree8.getRaiz().getRight().getRaiz().setRight(new BinaryTree<>(25));
        System.out.println("Los valores mas largo (Iterativo): " + tree8.iterativeLargestValueOfEachLevel());
        System.out.println("Los valores mas largo (Recursivo): " + tree8.recursiveLargestValueOfEachLevel());
        
        // COUNT NODES WITH ONLY CHILD

        BinaryTree<String> tree9 = new BinaryTree<>("Two");
        tree9.getRaiz().setLeft(new BinaryTree<>("Seven"));
        tree9.getRaiz().setRight(new BinaryTree<>("Five"));
        tree9.getRaiz().getLeft().getRaiz().setRight(new BinaryTree<>("Six"));
        tree9.getRaiz().getRight().getRaiz().setRight(new BinaryTree<>("Nine"));
        tree9.getRaiz().getLeft().getRaiz().getRight().getRaiz().setLeft(new BinaryTree<>("One"));
        tree9.getRaiz().getLeft().getRaiz().getRight().getRaiz().setRight(new BinaryTree<>("Three"));
        tree9.getRaiz().getRight().getRaiz().getRight().getRaiz().setLeft(new BinaryTree<>("Four"));
    
        // Llamar al método para contar nodos con un solo hijo

        System.out.println("Número de nodos con un solo hijo: (Iterativo) " + tree8.iterativeCountNodesWithOnlyChild());
        //Arbol de expresion
        BinaryTree<String> tree11= buildExpressionTree("4 8 * 3 2 * +");
        tree11.RecorrerEnPostOrden();
        Integer result=evaluationExpressionTree(tree11);
        System.out.println("El valor es: " + result);
    }
}

    

    

