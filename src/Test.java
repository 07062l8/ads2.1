/**
 * Test program to demonstrate all functionality.
 */
public class Test {
    public static void main(String[] args) {
        // Test MyArrayList
        MyList<Integer> arrayList = new MyArrayList<>();
        arrayList.add(5);
        arrayList.add(1);
        arrayList.add(3);
        arrayList.addFirst(7);
        arrayList.addLast(9);
        System.out.println("MyArrayList: " + arrayList);
        arrayList.remove(2);
        System.out.println("After remove index 2: " + arrayList);
        arrayList.sort();
        System.out.println("After sort: " + arrayList);

        // Test MyLinkedList
        MyList<String> linkedList = new MyLinkedList<>();
        linkedList.add("banana");
        linkedList.add("apple");
        linkedList.add("cherry");
        linkedList.addFirst("date");
        linkedList.addLast("elderberry");
        System.out.println("MyLinkedList: " + linkedList);
        linkedList.remove(1);
        System.out.println("After remove index 1: " + linkedList);
        linkedList.sort();
        System.out.println("After sort: " + linkedList);

        // Test MyStack using MyArrayList internally
        MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("MyStack: " + stack);
        System.out.println("Stack peek: " + stack.peek());
        System.out.println("Stack pop: " + stack.pop());
        System.out.println("After pop, MyStack: " + stack);

        // Test MyQueue using MyLinkedList internally
        MyQueue<String> queue = new MyQueue<>();
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        System.out.println("MyQueue: " + queue);
        System.out.println("Queue peek: " + queue.peek());
        System.out.println("Queue dequeue: " + queue.dequeue());
        System.out.println("After dequeue, MyQueue: " + queue);

        // Test MyMinHeap using MyArrayList internally
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        heap.insert(45);
        heap.insert(20);
        heap.insert(14);
        heap.insert(12);
        heap.insert(31);
        heap.insert(7);
        System.out.println("MyMinHeap: " + heap);
        System.out.println("Min element: " + heap.peek());
        System.out.println("Removed min: " + heap.removeMin());
        System.out.println("After removal, MyMinHeap: " + heap);
    }
}

