package ec.edu.espol;

public interface List <E> extends Iterable<E>{
    public boolean addFirst (E e); //Insertar un elemento al comienzo

    public boolean addLast (E e);//Insertar un elemento al final

    public E removeFirst();//Remueve el emeneto al inicio

    public E removeLast();//Remueve el elemento al final

    public int size();

    public boolean isEmpty();

    public void clear();

    public void add(int index, E element);

    public E remove(int index);//remueve y retorna el elemento

    public E get(int index); //retorna el elemento ubicado en el indice

    public E set(int index, E element); // Setea el elemento
}
