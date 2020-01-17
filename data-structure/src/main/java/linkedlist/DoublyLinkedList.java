package linkedlist;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private class Node<T> {
        private T data;
        private Node<T> next, prev;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T elem) {
        addLast(elem);
    }

    public void addLast(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }
        size++;
        System.out.println(toString());
    }

    public void addFirst(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            head.prev = new Node<T>(elem, null, head);
            head = head.prev;
        }
        size++;
        System.out.println(toString());
    }

    public T peekFirst() {
        checkIfEmpty();
        return head.data;
    }

    public T peekLast() {
        checkIfEmpty();
        return tail.data;
    }

    public T removeFirst() {
        checkIfEmpty();

        T data = head.data;
        head = head.next;
        --size;

        if (isEmpty()) {
            tail = null;
        } else {
            head.prev = null;
        }

        System.out.println(toString());

        return data;
    }

    public T removeLast() {
        checkIfEmpty();

        T data = tail.data;
        tail = tail.prev;
        --size;

        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }

        System.out.println(toString());

        return data;
    }

    public boolean remove(Object object) {
        Node<T> trav;

        for (trav = head; trav != null; trav = trav.next) {
            if ((object == null && trav.data == null) || (object != null && object.equals(trav.data))) {
                remove(trav);
                return true;
            }
        }
        return false;
    }

    private T remove(Node<T> node) {
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();

        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;

        node.data = null;
        node.prev = node.next = null;
        --size;

        System.out.println(toString());
        return data;
    }


    private void checkIfEmpty() {
        if (isEmpty()) throw new RuntimeException("Empty List");
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        int i;

        Node<T> trav;

        if (index < size / 2) {
            for (i = 0, trav = head; i != index; i++) {
                trav = trav.next;
            }
        } else {
            for (i = size - 1, trav = tail; i != index; i--) {
                trav = trav.prev;
            }
        }
        return remove(trav);
    }

    public int indexOf(Object obj) {
        int index = 0;

        Node<T> trav;
        for (trav = head; trav != null; trav = trav.next, index++) {
            if ((obj == null && trav.data == null) || (obj != null && obj.equals(trav.data))) {
                return index;
            }
        }
        return -1;
    }

    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> trav = head;
        while (trav != null) {
            if (trav.next != null) {
                sb.append(trav.data + ",");
            } else {
                sb.append(trav.data);
            }
            trav = trav.next;
        }
        sb.append("]");

        return sb.toString();
    }
}
