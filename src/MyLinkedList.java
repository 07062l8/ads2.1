/**
 * MyLinkedList is a doubly linked list implementation of the MyList interface.
 * It uses an inner class MyNode to hold data and pointers to next and previous nodes.
 * Special care is taken to update pointers to prevent loops.
 */
public class MyLinkedList<T> implements MyList<T> {

    private class MyNode {
        T data;
        MyNode next;
        MyNode prev;

        MyNode(T data) {
            this.data = data;
            next = null;
            prev = null;
        }
    }

    private MyNode head;
    private MyNode tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void addLast(T item) {
        MyNode newNode = new MyNode(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void addFirst(T item) {
        MyNode newNode = new MyNode(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        if (index == 0) {
            addFirst(item);
            return;
        }
        if (index == size) {
            addLast(item);
            return;
        }
        MyNode newNode = new MyNode(item);
        MyNode current = getNode(index);
        MyNode prevNode = current.prev;
        prevNode.next = newNode;
        newNode.prev = prevNode;
        newNode.next = current;
        current.prev = newNode;
        size++;
    }

    // Returns the node at the specified index.
    private MyNode getNode(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        MyNode current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++)
                current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--)
                current = current.prev;
        }
        return current;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T getFirst() {
        if (head == null)
            throw new RuntimeException("List is empty");
        return head.data;
    }

    @Override
    public T getLast() {
        if (tail == null)
            throw new RuntimeException("List is empty");
        return tail.data;
    }

    @Override
    public void set(int index, T item) {
        MyNode node = getNode(index);
        node.data = item;
    }

    @Override
    public void remove(int index) {
        MyNode node = getNode(index);
        removeNode(node);
    }

    // Helper method that unlinks a node from the list.
    private void removeNode(MyNode node) {
        if (node == head && node == tail) {
            head = tail = null;
        } else if (node == head) {
            head = head.next;
            if (head != null)
                head.prev = null;
        } else if (node == tail) {
            tail = tail.prev;
            if (tail != null)
                tail.next = null;
        } else {
            MyNode prevNode = node.prev;
            MyNode nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        // Break links to help garbage collection and prevent potential loops.
        node.next = node.prev = null;
        size--;
    }

    @Override
    public void removeFirst() {
        if (head == null)
            throw new RuntimeException("List is empty");
        removeNode(head);
    }

    @Override
    public void removeLast() {
        if (tail == null)
            throw new RuntimeException("List is empty");
        removeNode(tail);
    }

    /**
     * Sorts the linked list using bubble sort. This simple sort assumes that
     * elements are Comparable.
     */
    @Override
    public void sort() {
        if (size <= 1)
            return;
        boolean swapped;
        do {
            swapped = false;
            MyNode current = head;
            while (current != null && current.next != null) {
                T currData = current.data;
                T nextData = current.next.data;
                if (((Comparable<T>) currData).compareTo(nextData) > 0) {
                    // Swap data values.
                    current.data = nextData;
                    current.next.data = currData;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        MyNode current = head;
        while (current != null) {
            if (object == null ? current.data == null : object.equals(current.data))
                return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = size - 1;
        MyNode current = tail;
        while (current != null) {
            if (object == null ? current.data == null : object.equals(current.data))
                return index;
            current = current.prev;
            index--;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        MyNode current = head;
        while (current != null) {
            arr[i++] = current.data;
            current = current.next;
        }
        return arr;
    }

    @Override
    public void clear() {
        MyNode current = head;
        while (current != null) {
            MyNode next = current.next;
            current.prev = current.next = null;
            current.data = null;
            current = next;
        }
        head = tail = null;
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
            private MyNode current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }
            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        MyNode current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null)
                sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
