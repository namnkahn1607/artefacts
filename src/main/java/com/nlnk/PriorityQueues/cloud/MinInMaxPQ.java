package com.nlnk.PriorityQueues.cloud;

import com.nlnk.PriorityQueues.MaxPQ;

/**
 * 27. Add a min() method to {@link MaxPQ}. Your implementation should use
 * constant time and constant extra space.
 */
public class MinInMaxPQ<Key extends Comparable<Key>> extends MaxPQ<Key> {
    private Key minimumVal = null;

    public MinInMaxPQ(Key[] keys) {
        super(keys.length);

        for (Key key : keys) {
            insert(key);
        }
    }

    public MinInMaxPQ(int initialCapacity) {
        super(initialCapacity);
    }

    public Key min() {
        return minimumVal;
    }

    @Override
    public void insert(Key val) {
        if (minimumVal == null || minimumVal.compareTo(val) > 0) {
            minimumVal = val;
        }

        super.insert(val);
    }

    @Override
    public Key remove() {
        if (size() == 1) {
            minimumVal = null;
        }

        return super.remove();
    }
}