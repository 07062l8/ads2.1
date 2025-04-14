/**
 * MyArrayList is a dynamic-array implementation of the MyList interface.
 * It holds elements in an Object[] and resizes automatically.
 */
public class MyArrayList<T> implements MyList<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // Ensure the backing array has enough capacity.
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length * 2, minCapacity);
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    // Adds an element at the end.
    @Override
    public void add(T item) {
        addLast(item);
    }

    // Equivalent to push at the end.
    @Override
    public void addLast(T item) {
        ensureCapacity(size + 1);
        elements[size] = item;
        size++;
    }

    // Adds an element at the beginning.
    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    // Inserts an element at a specified index.
    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = item;
        size++;
    }

    // Returns the element at the index.
    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return (T) elements[index];
    }

    @Override
    public T getFirst() {
        if (size == 0)
            throw new RuntimeException("List is empty");
        return (T) elements[0];
    }

    @Override
    public T getLast() {
        if (size == 0)
            throw new RuntimeException("List is empty");
        return (T) elements[size - 1];
    }

    // Replaces the element at the specified index.
    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        elements[index] = item;
    }

    // Removes the element at the specified index.
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        elements[size - 1] = null; // Help garbage collection.
        size--;
    }

    @Override
    public void removeFirst() {
        if (size == 0)
            throw new RuntimeException("List is empty");
        remove(0);
    }

    @Override
    public void removeLast() {
        if (size == 0)
            throw new RuntimeException("List is empty");
        elements[size - 1] = null;
        size--;
    }

    /**
     * Sorts the list using the insertion sort algorithm.
     * Assumes that the elements are Comparable.
     */
    @Override
    public void sort() {
        for (int i = 1; i < size; i++) {
            T key = (T) elements[i];
            int j = i - 1;
            while (j >= 0) {
                T current = (T) elements[j];
                if (((Comparable<T>) current).compareTo(key) > 0) {
                    elements[j + 1] = elements[j];
                    j--;
                } else {
                    break;
                }
            }
            elements[j + 1] = key;
        }
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object == null ? elements[i] == null : object.equals(elements[i]))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (object == null ? elements[i] == null : object.equals(elements[i]))
                return i;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(elements, 0, array, 0, size);
        return array;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            elements[i] = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    // Implements Iterable using an anonymous inner class.
    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private int current = 0;
            @Override
            public boolean hasNext() {
                return current < size;
            }
            @Override
            public T next() {
                return get(current++);
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i != size - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
