package com.nlnk.PriorityQueues;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class PriorityQueueTest {
    @Nested
    @DisplayName("MaxPQ Tests")
    class MaxPQTests {
        private MaxPQ<Integer> maxPQ;

        @BeforeEach
        void setUp() {
            maxPQ = new MaxPQ<>(10);
        }

        @Test
        @DisplayName("Should create empty MaxPQ")
        void testCreateEmpty() {
            assertTrue(maxPQ.isEmpty());
            assertEquals(0, maxPQ.size());
            assertNull(maxPQ.peek());
            assertNull(maxPQ.remove());
        }

        @Test
        @DisplayName("Should insert single element")
        void testInsertSingle() {
            maxPQ.insert(5);
            assertFalse(maxPQ.isEmpty());
            assertEquals(1, maxPQ.size());
            assertEquals(5, maxPQ.peek());
        }

        @Test
        @DisplayName("Should maintain max heap property after insertions")
        void testMaxHeapProperty() {
            maxPQ.insert(3);
            maxPQ.insert(7);
            maxPQ.insert(1);
            maxPQ.insert(9);
            maxPQ.insert(5);

            assertEquals(9, maxPQ.remove());
            assertEquals(7, maxPQ.remove());
            assertEquals(5, maxPQ.remove());
            assertEquals(3, maxPQ.remove());
            assertEquals(1, maxPQ.remove());
            assertTrue(maxPQ.isEmpty());
        }

        @Test
        @DisplayName("Should handle duplicate values")
        void testDuplicates() {
            maxPQ.insert(5);
            maxPQ.insert(5);
            maxPQ.insert(5);

            assertEquals(3, maxPQ.size());
            assertEquals(5, maxPQ.remove());
            assertEquals(5, maxPQ.remove());
            assertEquals(5, maxPQ.remove());
            assertTrue(maxPQ.isEmpty());
        }

        @Test
        @DisplayName("Should grow capacity when needed")
        void testDynamicGrowth() {
            MaxPQ<Integer> smallPQ = new MaxPQ<>(2);

            for (int i = 1; i <= 10; i++) {
                smallPQ.insert(i);
            }

            assertEquals(10, smallPQ.size());

            // Should extract in descending order
            for (int i = 10; i >= 1; i--) {
                assertEquals(i, smallPQ.remove());
            }
        }

        @Test
        @DisplayName("Should shrink capacity when size reduces")
        void testDynamicShrink() {
            MaxPQ<Integer> pq = new MaxPQ<>(100);

            for (int i = 0; i < 100; i++) {
                pq.insert(i);
            }

            assertEquals(100, pq.size());

            // Remove most elements to trigger shrinking
            for (int i = 0; i < 75; i++) {
                pq.remove();
            }

            assertEquals(25, pq.size());
            assertFalse(pq.isEmpty());
        }

        @Test
        @DisplayName("Should handle alternating insert and remove")
        void testAlternatingOperations() {
            maxPQ.insert(5);
            assertEquals(5, maxPQ.remove());
            assertTrue(maxPQ.isEmpty());

            maxPQ.insert(10);
            maxPQ.insert(3);
            assertEquals(10, maxPQ.remove());
            maxPQ.insert(7);
            assertEquals(7, maxPQ.remove());
            assertEquals(3, maxPQ.remove());
            assertTrue(maxPQ.isEmpty());
        }

        @Test
        @DisplayName("Should construct from array")
        void testArrayConstructor() {
            Integer[] arr = {3, 7, 1, 9, 5, 2};
            MaxPQ<Integer> pq = new MaxPQ<>(arr);

            assertEquals(6, pq.size());
            assertEquals(9, pq.remove());
            assertEquals(7, pq.remove());
        }

        @Test
        @DisplayName("Should throw exception for null array")
        void testNullArrayConstructor() {
            assertThrows(IllegalArgumentException.class, () -> new MaxPQ<>(null));
        }

        @Test
        @DisplayName("Should throw exception for empty array")
        void testEmptyArrayConstructor() {
            assertThrows(IllegalArgumentException.class, () -> new MaxPQ<>(new Integer[0]));
        }

        @Test
        @DisplayName("Should insert array of elements")
        void testInsertArray() {
            Integer[] arr = {4, 2, 8, 1};
            maxPQ.insert(arr);

            assertEquals(4, maxPQ.size());
            assertEquals(8, maxPQ.peek());
        }

        @Test
        @DisplayName("Should convert to array")
        void testToArray() {
            maxPQ.insert(5);
            maxPQ.insert(3);
            maxPQ.insert(7);

            Integer[] arr = maxPQ.toArray(new Integer[0]);
            assertEquals(3, arr.length);
            assertTrue(Arrays.asList(arr).contains(5));
            assertTrue(Arrays.asList(arr).contains(3));
            assertTrue(Arrays.asList(arr).contains(7));
        }

        @Test
        @DisplayName("Should clear all elements")
        void testClear() {
            maxPQ.insert(1);
            maxPQ.insert(2);
            maxPQ.insert(3);

            maxPQ.clear();
            assertNull(maxPQ.peek());
        }

        @Test
        @DisplayName("Should handle remove on empty queue")
        void testRemoveEmpty() {
            assertNull(maxPQ.remove());
            assertTrue(maxPQ.isEmpty());
        }

        @Test
        @DisplayName("Should handle peek on empty queue")
        void testPeekEmpty() {
            assertNull(maxPQ.peek());
        }

        @Test
        @DisplayName("Should iterate over elements")
        void testIterator() {
            maxPQ.insert(5);
            maxPQ.insert(3);
            maxPQ.insert(7);

            List<Integer> elements = new ArrayList<>();
            for (Integer val : maxPQ) {
                if (val != null) {
                    elements.add(val);
                }
            }

            assertEquals(3, elements.size());
            assertTrue(elements.contains(5));
            assertTrue(elements.contains(3));
            assertTrue(elements.contains(7));
        }

        @Test
        @DisplayName("Should work with strings")
        void testWithStrings() {
            MaxPQ<String> stringPQ = new MaxPQ<>(5);
            stringPQ.insert("apple");
            stringPQ.insert("zebra");
            stringPQ.insert("banana");

            assertEquals("zebra", stringPQ.remove());
            assertEquals("banana", stringPQ.remove());
            assertEquals("apple", stringPQ.remove());
        }

        @Test
        @DisplayName("Should handle large random dataset")
        void testLargeRandomDataset() {
            MaxPQ<Integer> largePQ = new MaxPQ<>(1000);
            Random rand = new Random(42);
            List<Integer> values = new ArrayList<>();

            for (int i = 0; i < 1000; i++) {
                int val = rand.nextInt(10000);
                values.add(val);
                largePQ.insert(val);
            }

            values.sort(Comparator.reverseOrder()); // Sort descending

            for (int expected : values) {
                assertEquals(expected, largePQ.remove());
            }
            assertTrue(largePQ.isEmpty());
        }

        @Test
        @DisplayName("Should maintain heap property after single remove")
        void testHeapPropertyAfterRemove() {
            maxPQ.insert(10);
            maxPQ.insert(8);
            maxPQ.insert(9);
            maxPQ.insert(5);
            maxPQ.insert(7);

            maxPQ.remove(); // Remove 10

            // Remaining should still be in max heap order
            assertEquals(9, maxPQ.remove());
            assertEquals(8, maxPQ.remove());
        }
    }

    @Nested
    @DisplayName("MinPQ Tests")
    class MinPQTests {
        private MinPQ<Integer> minPQ;

        @BeforeEach
        void setUp() {
            minPQ = new MinPQ<>(10);
        }

        @Test
        @DisplayName("Should create empty MinPQ")
        void testCreateEmpty() {
            assertTrue(minPQ.isEmpty());
            assertEquals(0, minPQ.size());
            assertNull(minPQ.peek());
            assertNull(minPQ.remove());
        }

        @Test
        @DisplayName("Should insert single element")
        void testInsertSingle() {
            minPQ.insert(5);
            assertFalse(minPQ.isEmpty());
            assertEquals(1, minPQ.size());
            assertEquals(5, minPQ.peek());
        }

        @Test
        @DisplayName("Should maintain min heap property after insertions")
        void testMinHeapProperty() {
            minPQ.insert(3);
            minPQ.insert(7);
            minPQ.insert(1);
            minPQ.insert(9);
            minPQ.insert(5);

            assertEquals(1, minPQ.remove());
            assertEquals(3, minPQ.remove());
            assertEquals(5, minPQ.remove());
            assertEquals(7, minPQ.remove());
            assertEquals(9, minPQ.remove());
            assertTrue(minPQ.isEmpty());
        }

        @Test
        @DisplayName("Should handle duplicate values")
        void testDuplicates() {
            minPQ.insert(5);
            minPQ.insert(5);
            minPQ.insert(5);

            assertEquals(3, minPQ.size());
            assertEquals(5, minPQ.remove());
            assertEquals(5, minPQ.remove());
            assertEquals(5, minPQ.remove());
            assertTrue(minPQ.isEmpty());
        }

        @Test
        @DisplayName("Should grow capacity when needed")
        void testDynamicGrowth() {
            MinPQ<Integer> smallPQ = new MinPQ<>(2);

            for (int i = 10; i >= 1; i--) {
                smallPQ.insert(i);
            }

            assertEquals(10, smallPQ.size());

            // Should extract in ascending order
            for (int i = 1; i <= 10; i++) {
                assertEquals(i, smallPQ.remove());
            }
        }

        @Test
        @DisplayName("Should shrink capacity when size reduces")
        void testDynamicShrink() {
            MinPQ<Integer> pq = new MinPQ<>(100);

            for (int i = 0; i < 100; i++) {
                pq.insert(i);
            }

            assertEquals(100, pq.size());

            // Remove most elements to trigger shrinking
            for (int i = 0; i < 75; i++) {
                pq.remove();
            }

            assertEquals(25, pq.size());
            assertFalse(pq.isEmpty());
        }

        @Test
        @DisplayName("Should handle alternating insert and remove")
        void testAlternatingOperations() {
            minPQ.insert(5);
            assertEquals(5, minPQ.remove());
            assertTrue(minPQ.isEmpty());

            minPQ.insert(10);
            minPQ.insert(3);
            assertEquals(3, minPQ.remove());
            minPQ.insert(7);
            assertEquals(7, minPQ.remove());
            assertEquals(10, minPQ.remove());
            assertTrue(minPQ.isEmpty());
        }

        @Test
        @DisplayName("Should construct from array")
        void testArrayConstructor() {
            Integer[] arr = {3, 7, 1, 9, 5, 2};
            MinPQ<Integer> pq = new MinPQ<>(arr);

            assertEquals(6, pq.size());
            assertEquals(1, pq.remove());
            assertEquals(2, pq.remove());
        }

        @Test
        @DisplayName("Should throw exception for null array")
        void testNullArrayConstructor() {
            assertThrows(IllegalArgumentException.class, () -> new MinPQ<>(null));
        }

        @Test
        @DisplayName("Should throw exception for empty array")
        void testEmptyArrayConstructor() {
            assertThrows(IllegalArgumentException.class, () -> new MinPQ<>(new Integer[0]));
        }

        @Test
        @DisplayName("Should insert array of elements")
        void testInsertArray() {
            Integer[] arr = {4, 2, 8, 1};
            minPQ.insert(arr);

            assertEquals(4, minPQ.size());
            assertEquals(1, minPQ.peek());
        }

        @Test
        @DisplayName("Should convert to array")
        void testToArray() {
            minPQ.insert(5);
            minPQ.insert(3);
            minPQ.insert(7);

            Integer[] arr = minPQ.toArray(new Integer[0]);
            assertEquals(3, arr.length);
            assertTrue(Arrays.asList(arr).contains(5));
            assertTrue(Arrays.asList(arr).contains(3));
            assertTrue(Arrays.asList(arr).contains(7));
        }

        @Test
        @DisplayName("Should clear all elements")
        void testClear() {
            minPQ.insert(1);
            minPQ.insert(2);
            minPQ.insert(3);

            minPQ.clear();
            assertNull(minPQ.peek());
        }

        @Test
        @DisplayName("Should handle remove on empty queue")
        void testRemoveEmpty() {
            assertNull(minPQ.remove());
            assertTrue(minPQ.isEmpty());
        }

        @Test
        @DisplayName("Should handle peek on empty queue")
        void testPeekEmpty() {
            assertNull(minPQ.peek());
        }

        @Test
        @DisplayName("Should iterate over elements")
        void testIterator() {
            minPQ.insert(5);
            minPQ.insert(3);
            minPQ.insert(7);

            List<Integer> elements = new ArrayList<>();
            for (Integer val : minPQ) {
                if (val != null) {
                    elements.add(val);
                }
            }

            assertEquals(3, elements.size());
            assertTrue(elements.contains(5));
            assertTrue(elements.contains(3));
            assertTrue(elements.contains(7));
        }

        @Test
        @DisplayName("Should work with strings")
        void testWithStrings() {
            MinPQ<String> stringPQ = new MinPQ<>(5);
            stringPQ.insert("zebra");
            stringPQ.insert("apple");
            stringPQ.insert("banana");

            assertEquals("apple", stringPQ.remove());
            assertEquals("banana", stringPQ.remove());
            assertEquals("zebra", stringPQ.remove());
        }

        @Test
        @DisplayName("Should handle large random dataset")
        void testLargeRandomDataset() {
            MinPQ<Integer> largePQ = new MinPQ<>(1000);
            Random rand = new Random(42);
            List<Integer> values = new ArrayList<>();

            for (int i = 0; i < 1000; i++) {
                int val = rand.nextInt(10000);
                values.add(val);
                largePQ.insert(val);
            }

            values.sort(Integer::compareTo); // Sort ascending

            for (int expected : values) {
                assertEquals(expected, largePQ.remove());
            }
            assertTrue(largePQ.isEmpty());
        }

        @Test
        @DisplayName("Should maintain heap property after single remove")
        void testHeapPropertyAfterRemove() {
            minPQ.insert(5);
            minPQ.insert(7);
            minPQ.insert(6);
            minPQ.insert(10);
            minPQ.insert(8);

            minPQ.remove(); // Remove 5

            // Remaining should still be in min heap order
            assertEquals(6, minPQ.remove());
            assertEquals(7, minPQ.remove());
        }

        @Test
        @DisplayName("Should handle negative numbers")
        void testNegativeNumbers() {
            minPQ.insert(-5);
            minPQ.insert(10);
            minPQ.insert(-15);
            minPQ.insert(0);

            assertEquals(-15, minPQ.remove());
            assertEquals(-5, minPQ.remove());
            assertEquals(0, minPQ.remove());
            assertEquals(10, minPQ.remove());
        }
    }

    @Nested
    @DisplayName("Edge Case Tests")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle single element lifecycle")
        void testSingleElementLifecycle() {
            MaxPQ<Integer> pq = new MaxPQ<>(1);
            pq.insert(42);
            assertEquals(42, pq.peek());
            assertEquals(42, pq.remove());
            assertTrue(pq.isEmpty());
            assertNull(pq.peek());
        }

        @Test
        @DisplayName("Should handle capacity of 1 with multiple operations")
        void testMinimalCapacity() {
            MaxPQ<Integer> pq = new MaxPQ<>(1);
            pq.insert(1);
            assertEquals(1, pq.remove());
            pq.insert(2);
            pq.insert(3); // Should trigger growth
            assertEquals(3, pq.remove());
            assertEquals(2, pq.remove());
        }

        @Test
        @DisplayName("Should handle all same values")
        void testAllSameValues() {
            MinPQ<Integer> pq = new MinPQ<>(10);
            for (int i = 0; i < 10; i++) {
                pq.insert(5);
            }

            for (int i = 0; i < 10; i++) {
                assertEquals(5, pq.remove());
            }
        }

        @Test
        @DisplayName("Should handle sorted ascending input for MaxPQ")
        void testSortedAscendingMaxPQ() {
            MaxPQ<Integer> pq = new MaxPQ<>(10);
            for (int i = 1; i <= 10; i++) {
                pq.insert(i);
            }

            for (int i = 10; i >= 1; i--) {
                assertEquals(i, pq.remove());
            }
        }

        @Test
        @DisplayName("Should handle sorted descending input for MinPQ")
        void testSortedDescendingMinPQ() {
            MinPQ<Integer> pq = new MinPQ<>(10);
            for (int i = 10; i >= 1; i--) {
                pq.insert(i);
            }

            for (int i = 1; i <= 10; i++) {
                assertEquals(i, pq.remove());
            }
        }

        @Test
        @DisplayName("Should handle zigzag pattern")
        void testZigzagPattern() {
            MaxPQ<Integer> pq = new MaxPQ<>(10);
            pq.insert(5);
            pq.insert(10);
            pq.insert(3);
            pq.insert(15);
            pq.insert(1);
            pq.insert(20);

            assertEquals(20, pq.remove());
            pq.insert(12);
            assertEquals(15, pq.remove());
            assertEquals(12, pq.remove());
        }
    }
}