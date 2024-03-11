package list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class ArrayListTest {
    List<Integer> arrayList;

    @BeforeEach
    void setUp(){
        arrayList = new ArrayList<>();
    }

    @Test
    void createUseWrongCapacity(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> arrayList = new ArrayList<>(-2));
    }

    @Test
    void add(){
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        Assertions.assertEquals(arrayList.get(0), 1);
        Assertions.assertEquals(arrayList.get(1), 2);
        Assertions.assertEquals(arrayList.get(2), 3);
    }

    @Test
    void addInIndex(){
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(1, 5);

        Assertions.assertEquals(arrayList.get(1), 5);
        Assertions.assertEquals(arrayList.get(3), 3);
    }

    @Test
    void addInLastIndex(){
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(2, 5);

        Assertions.assertEquals(arrayList.get(2), 5);
        Assertions.assertEquals(arrayList.get(3), 3);
    }

    @Test
    void addInWrongIndex(){
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(10,15));
    }

    @Test
    void addInNegativeIndex(){
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(-3,15));
    }

    @Test
    void emptyArrayList(){
        Assertions.assertEquals(arrayList.size(), 0);
    }

    @Test
    void size(){
        arrayList.add(10);
        arrayList.add(15);
        arrayList.add(20);
        arrayList.add(25);
        arrayList.add(30);
        arrayList.add(35);

        Assertions.assertEquals(arrayList.size(), 6);
    }

    @Test
    void getByWrongIndex(){
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(6));
    }

    @Test
    void getFirstElement(){
        arrayList.add(7);
        arrayList.add(2);
        arrayList.add(5);

        Assertions.assertEquals(arrayList.getFirst(), 7);
    }

    @Test
    void getFirstOfEmptyList(){
        Assertions.assertThrows(NoSuchElementException.class, () -> arrayList.getFirst());
    }

    @Test
    void getLastElement(){
        arrayList.add(7);
        arrayList.add(2);
        arrayList.add(5);

        Assertions.assertEquals(arrayList.getLast(), 5);
    }

    @Test
    void getLastOfEmptyList(){
        Assertions.assertThrows(NoSuchElementException.class, () -> arrayList.getLast());
    }

    @Test
    void set(){
        arrayList.add(7);
        arrayList.add(2);
        arrayList.add(5);
        arrayList.set(1,3);

        Assertions.assertEquals(arrayList.get(1), 3);
        Assertions.assertEquals(arrayList.get(2), 5);
    }

    @Test
    void setInWrongIndex(){
        arrayList.add(7);
        arrayList.add(2);
        arrayList.add(5);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(-2,6));
    }

    @Test
    void setInEmptyList(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(0,6));
    }

    @Test
    void remove(){
        arrayList.add(7);
        arrayList.add(2);
        arrayList.add(5);
        arrayList.remove(1);

        Assertions.assertEquals(arrayList.get(1), 5);
    }

    @Test
    void removeByWrongIndex(){
        arrayList.add(7);
        arrayList.add(2);
        arrayList.add(5);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(-5));
    }

    @Test
    void removeByIndexEqualToSize(){
        arrayList.add(7);
        arrayList.add(2);
        arrayList.add(5);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(3));
    }

    @Test
    void containsInEmptyList(){
        Assertions.assertFalse(arrayList.contains(3));
    }

    @Test
    void contains(){
        arrayList.add(7);
        arrayList.add(2);
        arrayList.add(5);

        Assertions.assertTrue(arrayList.contains(5));
    }

    @Test
    void containsWrongElement(){
        arrayList.add(7);
        arrayList.add(2);
        arrayList.add(5);

        Assertions.assertFalse(arrayList.contains(4));
    }

    @Test
    void isEmpty(){
        Assertions.assertTrue(arrayList.isEmpty());
    }

    @Test
    void wrongIsEmpty(){
        arrayList.add(7);
        arrayList.add(2);
        arrayList.add(5);

        Assertions.assertFalse(arrayList.isEmpty());
    }

    @Test
    void clear(){
        arrayList.add(7);
        arrayList.add(2);
        arrayList.add(5);
        arrayList.clear();

        Assertions.assertTrue(arrayList.isEmpty());
    }
}
