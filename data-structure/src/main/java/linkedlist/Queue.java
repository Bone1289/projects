package linkedlist;

public class Queue<T> implements Iterable<T> {
    //    private LinkedList<T> list = new LinkedList<>();
    private DoublyLinkedList<T> list = new DoublyLinkedList<>();

    public Queue() {
    }

    public Queue(T firstElem) {
        offer(firstElem);
    }

    // Return the size of the queue
    public int size() {
        return list.size();
    }

    // Peek the element at the front of the queue
    // The method throws an error is the queue is empty
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        return list.peekFirst();
    }

    // Poll an element from the front of the queue
    // The method throws an error is the queue is empty
    public T poll() {
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        return list.removeFirst();
    }

    // Returns whether or not the queue is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    public void offer(T elem) {
        list.addLast(elem);
    }

    // Return an iterator to alow the user to traverse
    // through the elements found inside the queue
    @Override
    public java.util.Iterator<T> iterator() {
        return list.iterator();
    }
}
