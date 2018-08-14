import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // size of the deque
    private int n;
    // top of deque
    private Node head;
    private Node tail;

    public Deque() {
        head = createSentinel();
        tail = createSentinel();
        head.next = tail;
        tail.prev = head;
        n = 0;
    }                           // construct an empty deque

    private Node createSentinel() {
        Node sentinel = new Node();
        sentinel.item = null;
        sentinel.next = null;
        sentinel.prev = null;
        return sentinel;
    }

    public boolean isEmpty() {
        return n == 0;
    }                 // is the deque empty?

    public int size() {
        return n;
    }                        // return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Add null item to head");
        Node node = new Node();
        node.item = item;
        Node oldFirst = head.next;
        head.next = node;
        node.next = oldFirst;
        oldFirst.prev = node;
        node.prev = head;
        n++;
    }          // add the item to the front

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Add null item to tail");
        Node node = new Node();
        node.item = item;
        Node oldLast = tail.prev;
        tail.prev = node;
        node.prev = oldLast;
        oldLast.next = node;
        node.next = tail;
        n++;
    }           // add the item to the end

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Remove head empty");
        Node first = head.next;
        head.next = head.next.next;
        head.next.prev = head;
        first.next = null;
        first.prev = null;
        n--;
        return first.item;
    }                // remove and return the item from the front

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Remove tail empty");
        Node last = tail.prev;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        last.next = null;
        last.prev = null;
        n--;
        return last.item;
    }                 // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }         // return an iterator over items in order from front to end

    public static void main(String[] args) {
    }   // unit testing (optional)

    private class DequeIterator implements Iterator<Item> {

        private Node current = head.next;

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
}