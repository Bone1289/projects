package linkedlist;

import java.util.EmptyStackException;

public class Stack<T> implements Iterable<T> {
    //    private LinkedList<T> list = new LinkedList<>();
    private DoublyLinkedList<T> list = new DoublyLinkedList<>();

    public Stack() {
    }

    public Stack(T firstElem) {
        push(firstElem);
    }

    public void push(T elem) {
        list.addLast(elem);
    }

    public T pop(T elem) {
        if (isEmpty()) throw new EmptyStackException();
        return list.removeLast();
    }

    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return list.peekLast();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    public java.util.Iterator<T> iterator() {
        return list.iterator();
    }
}
