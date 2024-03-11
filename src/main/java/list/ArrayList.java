package list;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

public class ArrayList<T> implements List<T> {
    private Object[] elements;
    private static final int DEFAULT_CAPACITY = 5;
    private int size;

    public ArrayList(int initCapacity) {
        if(initCapacity <= 0){
            throw new IllegalArgumentException();
        }
        elements = new Object[initCapacity];
    }

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public static <T> List<T> of(T... elements) {
        List<T> list = new ArrayList<T>();
        Stream.of(elements).forEach(list::add);
        return list;
    }

    private void resizeArray(){
        if(size == elements.length){
            Object[] newArray = new Object[(int)(elements.length * 1.5)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    @Override
    public void add(T element) {
        resizeArray();
        elements[size] = element;
        size++;
    }

    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        resizeArray();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    @Override
    public T getFirst() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return get(0);
    }

    @Override
    public T getLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return get(size - 1);
    }

    @Override
    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        elements[index] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removeElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removeElement;
    }

    @Override
    public boolean contains(T element) {
        for(int i = 0; i < size; i++){
            if(elements[i].equals(element)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }
}
