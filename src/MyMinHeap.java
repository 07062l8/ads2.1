/**
 * MyMinHeap implements a minimum heap (priority queue) where the smallest element is always at the root.
 * This implementation uses a dynamic array (MyArrayList) for storing the heap.
 * Elements must implement Comparable.
 */
public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> list; // Underlying storage

    public MyMinHeap() {
        list = new MyArrayList<>();
    }

    // Inserts an element into the heap and restores the heap property.
    public void insert(T item) {
        list.add(item);
        bubbleUp(list.size() - 1);
    }

    // Returns the minimum element (at the root) without removing it.
    public T peek() {
        if (isEmpty())
            throw new RuntimeException("Heap is empty");
        return list.get(0);
    }

    // Removes and returns the minimum element and re-heapifies.
    public T removeMin() {
        if (isEmpty())
            throw new RuntimeException("Heap is empty");
        T min = list.get(0);
        T last = list.get(list.size() - 1);
        list.set(0, last);
        list.removeLast();
        bubbleDown(0);
        return min;
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    // Heap helper methods.

    // Moves the element at index up to restore the heap property.
    private void bubbleUp(int index) {
        int parentIndex;
        while (index > 0) {
            parentIndex = (index - 1) / 2;
            T current = list.get(index);
            T parent = list.get(parentIndex);
            if (current.compareTo(parent) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Moves the element at index down to restore the heap property.
    private void bubbleDown(int index) {
        int leftChild, rightChild, smallest;
        int size = list.size();
        while (true) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            smallest = index;
            if (leftChild < size && list.get(leftChild).compareTo(list.get(smallest)) < 0)
                smallest = leftChild;
            if (rightChild < size && list.get(rightChild).compareTo(list.get(smallest)) < 0)
                smallest = rightChild;
            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    // Swaps elements at positions i and j.
    private void swap(int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}

