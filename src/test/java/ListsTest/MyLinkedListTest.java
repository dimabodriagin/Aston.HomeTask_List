package ListsTest;

import Lists.MyArrayList;
import Lists.MyLinkedList;
import Lists.MyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyLinkedListTest {

    @Test
    void add_withOneArgument() {
        MyList<String> list = new MyLinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add("Hello with " + i);
        }

        System.out.println(list);
    }

    @Test
    void add_withMultipleArguments() {
        MyList<String> list = new MyLinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(0, "Hello with " + i);
        }

        System.out.println(list);
    }

    @Test
    void remove_positiveResult() {
        MyList<String> myList = new MyLinkedList<>();
        myList.add("Hello");
        myList.add("My");
        myList.add("World");

        String actual = myList.remove(1);
        String expected = "My";

        Assertions.assertEquals(actual, expected);
    }

    @Test
    void get_positiveResult() {
        MyList<String> myList = new MyLinkedList<>();
        myList.add("Hello");
        myList.add("My");

        String actual = myList.get(0);
        String expected = "Hello";

        Assertions.assertEquals(actual, expected);
    }

    @Test
    void set_positiveResult() {
        MyList<String> myList = new MyLinkedList<>();
        myList.add("Hello");
        myList.add("My");
        myList.add("World");

        String actual = myList.set(1, "Our");
        String expected = "My";

        Assertions.assertEquals(actual, expected);
    }

    @Test
    void size_positiveResult() {
        MyList<String> myList = new MyArrayList<>();
        myList.add("Hello");
        myList.add("My");
        myList.add("World");

        int actual = myList.size();
        int expected = 3;

        Assertions.assertEquals(actual, expected);
    }

    @Test
    void subList_positiveResult() {
        MyList<String> myList = new MyLinkedList<>();
        myList.add("Hello");
        myList.add("My");
        myList.add("World");

        MyList<String> actual = myList.subList(1, 2);

        System.out.println(actual);
    }
}
