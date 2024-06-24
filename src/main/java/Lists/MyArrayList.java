package Lists;

import java.util.Collection;

public class MyArrayList<E> implements MyList<E> {

    private int capacity = 10;
    private Object[] elementArray;
    private int size;

    public MyArrayList() {
        this.elementArray = new Object[capacity];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Размер массива отрицательный: "
                    + initialCapacity);
        }
        this.elementArray = new Object[initialCapacity];
    }

    @Override
    public boolean add(E e) {
        this.elementArray[size] = e;
        if (++size == this.capacity) {
            this.grow();
        }
        return true;
    }

    private void grow() {
        int newCapacity = capacity * 3 / 2 + 1;
        Object[] newElementArray = new Object[newCapacity];

        System.arraycopy(elementArray, 0, newElementArray, 0, size);
        capacity = newCapacity;
        elementArray = newElementArray;
    }

    @Override
    public void add(int index, E element) {
        System.arraycopy(elementArray, index, elementArray, index + 1, size - index);
        elementArray[index] = element;
        if (++size == this.capacity) {
            this.grow();
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedElement = (E) this.elementArray[index];
        if ((size = size - 1) > index) {
            System.arraycopy(elementArray, index + 1,
                    elementArray, index,
                    size - index);
        }
        elementArray[size] = null;
        return removedElement;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) this.elementArray[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException("Индекс выходит за границы массива: "
                    + index);
        }
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = (E) this.elementArray[index];
        this.elementArray[index] = element;
        return oldElement;
    }

    @Override
    public MyList<E> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex);
        MyArrayList<E> subList = new MyArrayList<>();
        if (fromIndex != toIndex) {
            System.arraycopy(this.elementArray, fromIndex, subList.elementArray, 0, toIndex - fromIndex);
            subList.size = toIndex - fromIndex;
        }
        return subList;
    }

    private void subListRangeCheck(int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            throw new IllegalArgumentException("Наименьший индекс подлиста отрицательный: " +
                    fromIndex);
        }
        if (toIndex > size) {
            throw new IllegalArgumentException("Наибольший индекс подлиста больше размеров массива: " +
                    toIndex);
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
        }
    }

    @Override
    public int size() {
        return this.size;
    }
}
