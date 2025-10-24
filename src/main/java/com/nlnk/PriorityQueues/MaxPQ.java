package com.nlnk.PriorityQueues;

import java.util.Iterator;

public class MaxPQ<Key extends Comparable<Key>>
        extends BinaryHeap<Key> implements Iterable<Key> {

    public MaxPQ(int initialCapacity) {
        super(initialCapacity);
    }

    public MaxPQ(Key[] keys) {
        super(keys);
    }

    @Override
    void swim(int i) {
        while (i > 1) {
            final int parent = i / 2;

            if (keys[i].compareTo(keys[parent]) > 0) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    @Override
    void sink(int i) {
        final int m = size();

        while (2 * i <= m) {
            final int L = 2 * i;
            final int R = 2 * i + 1;

            int max = L;

            if (R <= m && keys[R].compareTo(keys[max]) > 0) {
                max = R;
            }

            if (keys[i].compareTo(keys[max]) < 0) {
                swap(i, max);
                i = max;
            } else {
                break;
            }
        }
    }

    @Override
    public Iterator<Key> iterator() {
        return new MaxPQIterator();
    }

    private class MaxPQIterator implements Iterator<Key> {
        int index = 1;

        @Override
        public boolean hasNext() {
            return index <= size;
        }

        @Override
        public Key next() {
            return keys[index++];
        }
    }
}