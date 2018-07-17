import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private boolean[] occupied;
    private int size;
    private int capacity;

    private int tail;

    public RandomizedQueue() {
        capacity = 2;
        tail = 0;
        items = (Item[]) new Object[capacity];
        occupied = new boolean[capacity];
    }                 // construct an empty randomized queue

    public boolean isEmpty() {
        return size == 0;
    }                 // is the randomized queue empty?

    public int size() {
        return size;
    }                        // return the number of items on the randomized queue

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (tail == capacity) {
            rearrange(2 * capacity);
        }
        items[tail] = item;
        occupied[tail] = true;
        tail++;
        size++;
    }           // add the item

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int random = getRandomOccupiedPosition();
        Item selected = items[random];
        items[random] = null;
        occupied[random] = false;
        size--;
        if (tail > 0 && size == capacity / 4) {
            rearrange(capacity / 2);
            tail = size;
        }
        return selected;
    }                    // remove and return a random item

    private void rearrange(int capacity) {
        Item[] itemsCopy = (Item[]) new Object[capacity];
        boolean[] occupiedCopy = new boolean[capacity];
        int oldCapacity = this.capacity;
        this.capacity = capacity;
        for (int i = 0, j = 0; i < oldCapacity; ++i) {
            if (occupied[i]) {
                itemsCopy[j] = items[i];
                occupiedCopy[j] = true;
                j++;
            }
        }
        items = itemsCopy;
        occupied = occupiedCopy;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return items[getRandomOccupiedPosition()];
    }

    private int getRandomOccupiedPosition() {
        int random;
        do {
            random = StdRandom.uniform(capacity);
        } while (!occupied[random]);
        return random;
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }         // return an independent iterator over items in random order

    private class QueueIterator implements Iterator<Item> {

        private int current = 0;
        private Item[] iteratorItems;

        public QueueIterator() {
            iteratorItems = (Item[]) new Object[size];
            for (int i = 0, j = 0; i < capacity; ++i) {
                if (occupied[i]) {
                    iteratorItems[j++] = items[i];
                }
            }
        }

        @Override
        public boolean hasNext() {
            return iteratorItems[current] != null;
        }

        @Override
        public Item next() {
            return iteratorItems[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

        String[] input = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};

        RandomizedQueue<String> queue = new RandomizedQueue<>();

        for (String anInput : input) {
            queue.enqueue(anInput);
        }
        for (String anInput : input) {
            System.out.println(queue.dequeue());
        }
    }   // unit testing (optional)
}