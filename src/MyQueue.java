/**
 * MyQueue represents a First-In-First-Out (FIFO) structure.
 * A doubly linked list (MyLinkedList) is used so that enqueue and dequeue operations are efficient.
 */
public class MyQueue<T> {
    private MyLinkedList<T> list;

    public MyQueue() {
        list = new MyLinkedList<>();
    }

    // Enqueues an item at the end of the queue.
    public void enqueue(T item) {
        list.addLast(item);
    }

    // Dequeues (removes) and returns the item at the front.
    public T dequeue() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");
        T item = list.getFirst();
        list.removeFirst();
        return item;
    }

    // Returns the front item without removing it.
    public T peek() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");
        return list.getFirst();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
