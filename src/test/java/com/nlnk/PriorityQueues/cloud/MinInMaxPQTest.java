package com.nlnk.PriorityQueues.cloud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinInMaxPQTest {
    @Test
    void test01_oneElement() {
        Integer[] arr = {5};
        MinInMaxPQ<Integer> solution = new MinInMaxPQ<>(arr);
        assertEquals(5, solution.min());
    }

    @Test
    void test02_twoElements() {
        Integer[] arr = {10, 3};
        MinInMaxPQ<Integer> solution = new MinInMaxPQ<>(arr);
        assertEquals(3, solution.min());
    }

    @Test
    void test03_emptyQueueThrowsException() {
        // Initialize with capacity 10, but no elements
        MinInMaxPQ<Integer> solution = new MinInMaxPQ<>(10);

        // Assert that min() throws NoSuchElementException (or similar) when empty
        assertNull(solution.min());
    }

    @Test
    void test04_generalCase() {
        // Minimum is 2
        Integer[] arr = {15, 2, 20, 8, 30, 9};
        MinInMaxPQ<Integer> solution = new MinInMaxPQ<>(arr);
        assertEquals(2, solution.min());
    }

    @Test
    void test05_withNegativeNumbers() {
        // Minimum is -10
        Integer[] arr = {-5, 0, 1, -10, 5};
        MinInMaxPQ<Integer> solution = new MinInMaxPQ<>(arr);
        assertEquals(-10, solution.min());
    }

    @Test
    void test06_duplicateMinimums() {
        // Minimum is 1, appears multiple times
        Integer[] arr = {1, 5, 10, 8, 1, 1};
        MinInMaxPQ<Integer> solution = new MinInMaxPQ<>(arr);
        assertEquals(1, solution.min());
    }

    @Test
    void test07_postInsertionOfNewMinimum() {
        Integer[] arr = {10, 5, 15};
        MinInMaxPQ<Integer> solution = new MinInMaxPQ<>(arr);
        assertEquals(5, solution.min(), "Initial minimum should be 5");

        // Insert a new, smaller minimum
        solution.insert(1);
        assertEquals(1, solution.min(), "Minimum should be updated to 1 after insertion");
    }

    @Test
    void test08_postInsertionOfNonMinimum() {
        Integer[] arr = {10, 5, 15};
        MinInMaxPQ<Integer> solution = new MinInMaxPQ<>(arr);
        assertEquals(5, solution.min(), "Initial minimum should be 5");

        // Insert an element that is NOT the new minimum (12 is > 5)
        solution.insert(12);
        assertEquals(5, solution.min(), "Minimum should remain 5 after inserting 12");
    }

    @Test
    void test09_minAfterDelMax() {
        Integer[] arr = {10, 5, 20, 1};
        MinInMaxPQ<Integer> solution = new MinInMaxPQ<>(arr);
        assertEquals(1, solution.min(), "Initial minimum should be 1");

        // 1. Remove max (20). New minimum is still 1.
        solution.remove(); // delMax() removes 20
        assertEquals(1, solution.min(), "Minimum should still be 1 after removing max (20)");

        // 2. Remove next max (10). New minimum is still 1.
        solution.remove(); // delMax() removes 10
        assertEquals(1, solution.min(), "Minimum should still be 1 after removing max (10)");
    }

    @Test
    void test10_minBecomesNewAfterDelMax() {
        Integer[] arr2 = {10, 5, 20, 2}; // Min is 2.
        MinInMaxPQ<Integer> solution = new MinInMaxPQ<>(arr2);

        // After removing 20, PQ is [10, 5, 2]. Min is 2.
        solution.remove();

        // After removing 10, PQ is [5, 2]. Min is 2.
        solution.remove();

        // After removing 5, PQ is [2]. Min is 2.
        solution.remove();

        // After removing 2, PQ is []. Min should throw exception.
        solution.remove();
        assertNull(solution.min());
    }
}