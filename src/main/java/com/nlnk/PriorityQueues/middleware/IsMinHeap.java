package com.nlnk.PriorityQueues.middleware;

public class IsMinHeap<Key extends Comparable<Key>> {
    public boolean isMinHeap(Key[] arr) {
        for (Key val : arr) {
            if (val == null) {
                return false;
            }
        }

        return minHeapPos(arr, 0);
    }

    private boolean minHeapPos(Key[] arr, int i) {
        final int end = arr.length - 1;

        if (i > end) {
            return true;
        }

        final int L = 2 * i + 1;
        final int R = 2 * i + 2;

        if (L <= end && arr[i].compareTo(arr[L]) > 0) return false;
        if (R <= end && arr[i].compareTo(arr[R]) > 0) return false;

        return minHeapPos(arr, L) && minHeapPos(arr, R);
    }
}