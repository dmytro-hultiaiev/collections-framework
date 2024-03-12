package list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class LinkedListTest {
    List<Integer> linkedList;

    @BeforeEach
    void setUp(){
        linkedList = new LinkedList<>();
    }

    @Test
    void addList(){
        linkedList.add(3);
        Assertions.assertEquals(linkedList.get(0), 3);
    }

    @Test
    void addAndCheckSize(){
        linkedList.add(3);
        Assertions.assertEquals(linkedList.size(), 1);
    }

    @Test
    void addByIndex(){
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(6);
        linkedList.add(2,5);

        Assertions.assertEquals(linkedList.get(1), 4);
        Assertions.assertEquals(linkedList.get(2), 5);
        Assertions.assertEquals(linkedList.get(3), 6);
    }

    @Test
    void addByZeroIndexWhenListIsEmpty(){
        linkedList.add(0,3);
        Assertions.assertEquals(linkedList.get(0), 3);
    }

    @Test
    void addByLastIndex(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(3,5);

        Assertions.assertEquals(linkedList.get(3), 5);
        Assertions.assertEquals(linkedList.size(), 4);
    }

    @Test
    void addToHead(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(0,5);

        Assertions.assertEquals(linkedList.get(0), 5);
        Assertions.assertEquals(linkedList.get(1), 1);
        Assertions.assertEquals(linkedList.size(), 4);
    }

    @Test
    void addToNegativeIndex(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.add(-3,5));
    }

    @Test
    void addToIndexGreaterThanSize(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.add(7,5));
    }

    @Test
    void of(){
        LinkedList<Integer> linkedList2 = LinkedList.of(1,2,3);
        Assertions.assertEquals(linkedList2.get(0), 1);
        Assertions.assertEquals(linkedList2.get(1), 2);
        Assertions.assertEquals(linkedList2.get(2), 3);
    }

    @Test
    void set(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.set(1,4);

        Assertions.assertEquals(linkedList.get(0), 1);
        Assertions.assertEquals(linkedList.get(1), 4);
        Assertions.assertEquals(linkedList.get(2), 3);
    }

    @Test
    void setFirstElementWhenEmptyList(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.set(0,5));
    }

    @Test
    void setInIndexEqualToSize(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.set(3,5));
    }

    @Test
    void getFromEmptyList(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(0));
    }

    @Test
    void getFromNegativeIndex(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(-2));
    }

    @Test
    void getFirstElement(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        Assertions.assertEquals(linkedList.getFirst(), 1);
    }

    @Test
    void getFirstElementInEmptyList(){
        Assertions.assertThrows(NoSuchElementException.class, () -> linkedList.getFirst());
    }

    @Test
    void getLastElement(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        Assertions.assertEquals(linkedList.getLast(), 3);
    }

    @Test
    void getLastElementInEmptyList(){
        Assertions.assertThrows(NoSuchElementException.class, () -> linkedList.getLast());
    }

    @Test
    void remove(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.remove(1);

        Assertions.assertEquals(linkedList.get(1), 3);
        Assertions.assertEquals(linkedList.size(), 2);
    }

    @Test
    void removeFirst(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.remove(0);

        Assertions.assertEquals(linkedList.get(0), 2);
    }

    @Test
    void removeLast(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.remove(2);

        Assertions.assertEquals(linkedList.size(), 2);
    }

    @Test
    void removeFromEmptyList(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(0));
    }

    @Test
    void size(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        Assertions.assertEquals(linkedList.size(), 3);
    }

    @Test
    void sizeWhenEmptyList(){
        Assertions.assertEquals(linkedList.size(), 0);
    }

    @Test
    void contains(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        Assertions.assertTrue(linkedList.contains(3));
        Assertions.assertFalse(linkedList.contains(5));
    }

    @Test
    void containsWhenEmptyList(){
        Assertions.assertFalse(linkedList.contains(5));
    }

    @Test
    void isEmpty(){
        Assertions.assertTrue(linkedList.isEmpty());
    }

    @Test
    void wrongIsEmpty(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        Assertions.assertFalse(linkedList.isEmpty());
    }

    @Test
    void clear(){
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.clear();

        Assertions.assertTrue(linkedList.isEmpty());
        Assertions.assertEquals(linkedList.size(), 0);
    }

    @Test
    void clearEmptyList(){
        linkedList.clear();
        Assertions.assertTrue(linkedList.isEmpty());
    }
}
