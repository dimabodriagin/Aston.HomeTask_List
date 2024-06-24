package Lists;

public class MyLinkedList<E> implements MyList<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    private void linkLast(E e) {
        Node<E> prevLast = last;
        Node<E> node = new Node<>(e, prevLast, null);
        last = node;
        if (prevLast == null) {
            first = node;
        } else {
            prevLast.next = node;
        }
        ++size;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);

        if (index == size) {
            linkLast(element);
            return;
        }
        linkBefore(element, getNode(index));
    }

    private void linkBefore(E element, Node<E> nextNode) {
        Node<E> prevNode = nextNode.prev;
        Node<E> newNode = new Node<>(element, prevNode, nextNode);
        nextNode.prev = newNode;
        if (prevNode == null) {
            first = newNode;
        } else {
            prevNode.next = newNode;
        }
        ++size;
    }

    private Node<E> getNode(int index) {
        Node<E> node;
        if (index < size / 2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }
        node = last;
        for (int i = size - 1; i > index; i--) {
            node = node.prev;
        }
        return node;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException("Индекс выходит за границы массива: "
                    + index);
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        return unlink(getNode(index));
    }

    private E unlink(Node<E> node) {
        E element = node.item;
        Node<E> next = node.next;
        Node<E> prev = node.prev;


        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.item = null;
        --size;
        return element;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return getNode(index).item;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> node = getNode(index);
        E oldItem = node.item;
        node.item = element;
        return oldItem;
    }

    @Override
    public MyList<E> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex);

        MyLinkedList<E> list = new MyLinkedList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            list.add(getNode(i).item);
        }
        return list;
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
        return size;
    }
}
