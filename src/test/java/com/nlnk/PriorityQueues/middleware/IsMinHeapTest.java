package com.nlnk.PriorityQueues.middleware;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IsMinHeapTest {
    private final IsMinHeap<Integer> solution = new IsMinHeap<>();

    // --- Valid Heap Structures ---

    @Test
    void test01_trivialEmptyHeap() {
        // Case 1: Trivial Empty Heap (N=0)
        assertTrue(solution.isMinHeap(new Integer[]{}));
    }

    @Test
    void test02_trivialSizeOneHeap() {
        // Case 2: Trivial Size 1 Heap (N=1)
        assertTrue(solution.isMinHeap(new Integer[]{5}));
    }

    @Test
    void test03_perfectlyBalancedHeap() {
        // Case 3: Perfectly Balanced/Full Heap (N=7)
        // [1, 3, 5, 6, 8, 9, 10]
        assertTrue(solution.isMinHeap(new Integer[]{1, 3, 5, 6, 8, 9, 10}));
    }

    @Test
    void test04_validIncompleteHeap() {
        // Case 4: Valid incomplete heap (N=6)
        // Internal nodes: 2 (i=0), 5 (i=1), 8 (i=2)
        // Checks boundary of checking internal nodes
        assertTrue(solution.isMinHeap(new Integer[]{2, 5, 8, 10, 6, 9}));
    }

    @Test
    void test05_heapWithDuplicates() {
        // Case 5: Confirms P <= C property is handled correctly with duplicates
        // [1, 1, 2, 3, 3, 4, 4]
        assertTrue(solution.isMinHeap(new Integer[]{1, 1, 2, 3, 3, 4, 4}));
    }

    @Test
    void test06_validWithEqualParentAndChild() {
        // Case 12: Parent equals child (P=C) is valid for min-heap (P <= C)
        // [5, 5, 5]
        assertTrue(solution.isMinHeap(new Integer[]{5, 5, 5}));
    }

    @Test
    void test07_validNodeWithOnlyLeftChild() {
        // Case 9: Tests the last internal node (8 at i=2) correctly handling only a left child (12 at i=5)
        // [1, 5, 8, 9, 10, 12]
        assertTrue(solution.isMinHeap(new Integer[]{1, 5, 8, 9, 10, 12}));
    }


    // --- Invalid Heap Structures (Violations) ---

    @Test
    void test08_violationAtTheRoot() {
        // Case 6: Root (15 at i=0) is greater than its child (5 at i=1)
        assertFalse(solution.isMinHeap(new Integer[]{15, 5, 10, 2}));
    }

    @Test
    void test09_violationAtMidLevel() {
        // Case 7: Mid-level violation. Node 10 (i=1) > Child 5 (i=2)
        // [1, 10, 5, 12, 14, 15, 16]
        assertTrue(solution.isMinHeap(new Integer[]{1, 10, 5, 12, 14, 15, 16}));
    }

    @Test
    void test10_violationByOnlyLeftChild() {
        // Case 8: The last internal node (8 at i=2) is greater than its only child (4 at i=5)
        // This checks the boundary where 2i+1 is outside N
        assertFalse(solution.isMinHeap(new Integer[]{1, 5, 8, 9, 10, 4}));
    }

    @Test
    void test11_violationByRightChild() {
        // Case 10: Node 3 (i=1) is greater than its right child 2 (i=4).
        // [1, 3, 5, 10, 2, 6, 7]
        assertFalse(solution.isMinHeap(new Integer[]{1, 3, 5, 10, 2, 6, 7}));
    }

    @Test
    void test12_strictMaxHeapFails() {
        // Case 11: Ensures the algorithm rejects a complete max-heap structure
        // [10, 8, 9, 6, 3, 5, 1]
        assertFalse(solution.isMinHeap(new Integer[]{10, 8, 9, 6, 3, 5, 1}));
    }
}