package com.nlnk.PriorityQueues;

import java.util.Arrays;

public abstract class BinaryHeap<Key extends Comparable<Key>> {
    protected Key[] keys;
    protected int size;
    protected int capacity;

    abstract void swim(int i);

    abstract void sink(int i);

    @SuppressWarnings("unchecked")
    protected BinaryHeap(int initialCapacity) {
        this.keys = (Key[]) new Comparable[1 + initialCapacity];
        this.keys[0] = null;
        this.size = 0;
        this.capacity = initialCapacity;
    }

    @SuppressWarnings("unchecked")
    protected BinaryHeap(Key[] keys) {
        if (keys == null || keys.length == 0) {
            throw new IllegalArgumentException("argument passing to MaxPQ is null");
        }

        this.keys = (Key[]) new Comparable[keys.length + 1];
        this.keys[0] = null;
        this.size = keys.length;
        this.capacity = keys.length;

        System.arraycopy(keys, 0, this.keys, 1, keys.length);

        for (int i = size / 2; i >= 1; --i) {
            sink(i);
        }
    }

    protected void swap(int i, int j) {
        Key tmp = keys[i];
        keys[i] = keys[j];
        keys[j] = tmp;
    }

    public void insert(Key val) {
        if (size + 1 > capacity) {
            grow();
        }

        size += 1;
        keys[size] = val;
        swim(size);
    }

    public void insert(Key[] vals) {
        for (Key val : vals) {
            insert(val);
        }
    }

    public Key remove() {
        if (isEmpty()) {
            return null;
        }

        Key ans = keys[1];
        keys[1] = keys[size];
        keys[size] = null;
        size -= 1;

        if (size > 0) {
            sink(1);
        }

        if (size <= capacity / 4 && capacity > 1) {
            shrink();
        }

        return ans;
    }

    private void grow() {
        resize(2 * capacity);
    }

    private void shrink() {
        resize(Math.max(2, capacity / 2));
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Key[] newKeys = (Key[]) new Comparable[1 + newCapacity];
        newKeys[0] = null;

        System.arraycopy(keys, 1, newKeys, 1, size);
        this.capacity = newCapacity;
        this.keys = newKeys;
    }

    public Key peek() {
        if (isEmpty()) {
            return null;
        }

        return keys[1];
    }

    @SuppressWarnings("unchecked")
    public Key[] toArray(Key[] a) {
        if (a.length < size) {
            return (Key[]) Arrays.copyOfRange(keys, 1, 1 + size, a.getClass());
        }

        System.arraycopy(keys, 1, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    public void clear() {
        Arrays.fill(keys, null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}