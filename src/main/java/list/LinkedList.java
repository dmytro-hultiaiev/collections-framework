package list;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

public class LinkedList<T> implements List<T> {
    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }
    private Node<T> first;
    private Node<T> last;
    private int size;

    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> list = new LinkedList<T>();
        Stream.of(elements).forEach(list::add);
        return list;
    }

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if(first == null){
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        Node<T> newNode = new Node<>(element);
        if(first == null){
            first = last = newNode;
        } else if(index == 0){
            newNode.next = first;
            first = newNode;
        } else if (index == size) {
            last.next = newNode;
            last = newNode;
        } else {
            Node<T> prev = getNodeByIndex(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    private Node<T> getNodeByIndex(int index){
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        Node<T> node = getNodeByIndex(index);
        node.element = element;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return getNodeByIndex(index).element;
    }

    @Override
    public T getFirst() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return first.element;
    }

    @Override
    public T getLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return last.element;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T element;
        if(index == 0){
            element = first.element;
            first = first.next;
            if(first == null){
                last = null;
            }
        } else {
            Node<T> prev = getNodeByIndex(index - 1);
            element = prev.next.element;
            prev.next = prev.next.next;
            if(index == size - 1){
                last = prev;
            }
        }
        size--;
        return element;
    }

    @Override
    public boolean contains(T element) {
        Node<T> current = first;
        for(int i = 0; i < size; i++){
            if(current.element.equals(element)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }
}
