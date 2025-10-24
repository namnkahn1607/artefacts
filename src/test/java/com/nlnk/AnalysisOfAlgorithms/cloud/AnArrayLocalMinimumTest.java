package com.nlnk.AnalysisOfAlgorithms.cloud;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnArrayLocalMinimumTest {
    private int findLocalMinimum(int[] arr) {
        if (arr == null || arr.length == 0) {
            fail("Input array should not be empty or null based on problem constraints.");
            return -1; // Should not be reached
        }
        return AnArrayLocalMinimum.aLocalMinimum(arr);
    }

    // --- I. Base Cases (N=1 or N=2) ---

    @Test
    void test01_arraySizeOne() {
        int[] arr = {5};
        // Expected index 0
        assertEquals(0, findLocalMinimum(arr));
    }

    @Test
    void test02_arraySizeTwo_MinAtL() {
        int[] arr = {5, 10};
        // Expected index 0
        assertEquals(0, findLocalMinimum(arr));
    }

    @Test
    void test03_arraySizeTwo_MinAtR() {
        int[] arr = {10, 5};
        // Expected index 1
        assertEquals(1, findLocalMinimum(arr));
    }

    // --- II. Edges as Local Minima (N > 2) ---

    @Test
    void test04_minAtStart_Increasing() {
        int[] arr = {1, 5, 10, 15, 20};
        // Expected index 0
        assertEquals(0, findLocalMinimum(arr));
    }

    @Test
    void test05_minAtEnd_Decreasing() {
        int[] arr = {20, 15, 10, 5, 1};
        // Expected index 4
        assertEquals(4, findLocalMinimum(arr));
    }

    // --- III. Midpoint Logic and Fall-Through ---

    @Test
    void test06_recurseLeft_LocalMinAtM_Minus_1() {
        // M=2 (Value 20). arr[M-1] (5) < arr[M+1] (15). Should go left. Finds 5 at index 1.
        int[] arr = {10, 5, 20, 15, 25};
        assertEquals(1, findLocalMinimum(arr));
    }

    @Test
    void test07_localMinAtMidpoint() {
        // M=2 (Value 5). arr[M-1] (20) and arr[M+1] (25). Returns M immediately.
        int[] arr = {10, 20, 5, 25, 15};
        assertEquals(2, findLocalMinimum(arr));
    }

    @Test
    void test08_recurseRight_LocalMinAtM_Plus_1() {
        // M=2 (Value 15). arr[M-1] (20) > arr[M+1] (5). Should go right. Finds 5 at index 3.
        int[] arr = {25, 20, 15, 5, 10};
        assertEquals(3, findLocalMinimum(arr));
    }

    // --- IV. Worst Case Scenario & Multiple Local Minima ---

    @Test
    void test09_decreasingStaircase_long() {
        // [20, 18, 16, 14, 12, 10, 8, 6, 4, 2, 1]. Min is at index 10.
        int[] arr = {20, 18, 16, 14, 12, 10, 8, 6, 4, 2, 1};
        assertEquals(10, findLocalMinimum(arr));
    }

    @Test
    void test10_increasingStaircase_long() {
        // [1, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20]. Min is at index 0.
        int[] arr = {1, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        assertEquals(0, findLocalMinimum(arr));
    }

    @Test
    void test11_multipleLocalMinima_RecurseLeftPath() {
        // Local mins are 5 (idx 2) and 15 (idx 4).
        // Initial M=3 (30). arr[M-1](5) < arr[M+1](15). Go Left. Should find 5.
        int[] arr = {30, 10, 5, 30, 15, 40, 25};
        assertEquals(2, findLocalMinimum(arr));
    }

    @Test
    void test12_multipleLocalMinima_RecurseRightPath() {
        // Local mins are 10 (idx 1), 20 (idx 3) and 5 (idx 5).
        int[] arr = {60, 10, 50, 20, 40, 5, 25};
        List<Integer> expects = new ArrayList<>(List.of(1, 3, 5));
        assertTrue(expects.contains(findLocalMinimum(arr)));
    }
}