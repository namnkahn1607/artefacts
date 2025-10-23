package com.nlnk.PriorityQueues.middleware;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IsMinHeapTest {
    private final IsMinHeap<Integer> solution = new IsMinHeap<>();

    @Test
    void test1() {
        assertTrue(solution.isMinHeap(new Integer[]{1, 3, 5, 6, 8, 9, 10}));
    }
}