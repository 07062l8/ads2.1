/**
 * MyStack represents a Last-In-First-Out (LIFO) structure.
 * It is implemented over MyArrayList so that push/pop on the end are efficient.
 */
public class MyStack<T> {
    private MyArrayList<T> list;

    public MyStack() {
        list = new MyArrayList<>();
    }

    // Pushes an item onto the stack.
    public void push(T item) {
        list.addLast(item);
    }

    // Pops (removes) and returns the top item of the stack.
    public T pop() {
        if (isEmpty())
            throw new RuntimeException("Stack is empty");
        T item = list.getLast();
        list.removeLast();
        return item;
    }

    // Returns the top item of the stack without removing it.
    public T peek() {
        if (isEmpty())
            throw new RuntimeException("Stack is empty");
        return list.getLast();
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

