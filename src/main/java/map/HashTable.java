package map;

public class HashTable<K, V> implements Map<K, V> {
    static class Node<K,V>{
        K key;
        V value;
        Node<K,V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private Node<K,V>[] table;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable(int initCapacity){
        if(initCapacity <= 0){
            throw new IllegalArgumentException();
        }
        table = new Node[initCapacity];
    }

    public HashTable(){
        this(DEFAULT_CAPACITY);
    }

    public static int calculateIndex(Object key, int tableCapacity) {
        long hash = key.hashCode() ^ (key.hashCode() >> 16);
        return (int) (hash & (tableCapacity - 1));
    }

    private void checkNull(K key, V value){
        if(key == null || value == null){
            throw new NullPointerException();
        }
    }

    private void resizeIfNeed(){
        if(((float) size / table.length) >= LOAD_FACTOR){
            resizeTable(table.length * 2);
        }
    }

    private V putOnTable(Node<K, V>[] currentTable, K key, V value){
        checkNull(key, value);
        Node<K,V> node = new Node<>(key, value);
        int index = calculateIndex(key, table.length);

        if(table[index] == null){
            table[index] = node;
        } else {
            Node<K,V> current = table[index];
            while(current.next != null){
                if(current.key.equals(key)){
                    V prevValue = current.value;
                    current.value = value;
                    return prevValue;
                }
                current = current.next;
            }
            if(current.key.equals(key)){
                V prevValue = current.value;
                current.value = value;
                return prevValue;
            }
            current.next = node;
        }

        size++;
        return null;
    }

    @Override
    public V put(K key, V value) {
        resizeIfNeed();
        return putOnTable(table, key, value);
    }

    @Override
    public V get(K key) {
        int index = calculateIndex(key, table.length);
        Node<K,V> current = table[index];

        while(current != null){
            if(current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = calculateIndex(key, table.length);
        return table[index] != null;
    }

    @Override
    public boolean containsValue(V value) {
        for(Node<K,V> element : table){
            Node<K,V> current = element;
            while(current != null){
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size; // todo:
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V remove(K key) {
        int index = calculateIndex(key, table.length);
        Node<K,V> current = table[index];

        if(current != null){
            if(current.key.equals(key)){
                V value = current.value;
                table[index] = current.next;
                size--;
                return value;
            }
        }
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                V value = current.next.value;
                current.next = current.next.next;
                size--;
                return value;
            }
            current = current.next;
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int n = table.length;
        for (int i = 0; i < n; i++) {
            stringBuilder.append(i).append(": ");
            Node<K,V> current = table[i];
            if (current != null) {
                while (current.next != null) {
                    stringBuilder.append(current.key).append("=").append(current.value).append(" -> ");
                    current = current.next;
                }
                stringBuilder.append(current.key).append("=").append(current.value).append("\n");
            } else {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public void resizeTable(int newCapacity) {
        @SuppressWarnings("unchecked")
        Node<K,V>[] newTable = new Node[newCapacity];
        size = 0;
        for(Node<K,V> elem : table){
            Node<K, V> current = elem;
            while(current != null){
                putOnTable(newTable, current.key, current.value);
                current = current.next;
            }
        }
        table = newTable;
    }
}