package com.nlnk.AnalysisOfAlgorithms.cloud;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OptimalFourSumTest {
    private void assertQuadrupletsEqual(
            List<List<Integer>> expected, List<List<Integer>> actual
    ) {
        // 1. Check size first for quick failure
        assertEquals(expected.size(), actual.size());

        // 2. Sort all inner lists and the outer list for canonical comparison
        expected.forEach(Collections::sort);
        actual.forEach(Collections::sort);

        expected.sort((a, b) -> {
            for (int i = 0; i < 4; i++) {
                int cmp = a.get(i).compareTo(b.get(i));
                if (cmp != 0) return cmp;
            }

            return 0;
        });

        actual.sort((a, b) -> {
            for (int i = 0; i < 4; i++) {
                int cmp = a.get(i).compareTo(b.get(i));
                if (cmp != 0) return cmp;
            }

            return 0;
        });

        assertEquals(expected, actual);
    }

    // --- Standard Cases ---

    @Test
    void test01_standardCase_multipleSolutions() {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> expected = new ArrayList<>(List.of(
                Arrays.asList(-2, -1, 1, 2),
                Arrays.asList(-2, 0, 0, 2),
                Arrays.asList(-1, 0, 0, 1)
        ));
        assertQuadrupletsEqual(expected, OptimalFourSum.fourSum(nums, target));
    }

    // --- Edge Cases and Constraints ---

    @Test
    void test02_arrayTooSmall() {
        // Must have at least 4 elements
        int[] nums = {1, 2, 3};
        int target = 6;
        assertTrue(OptimalFourSum.fourSum(nums, target).isEmpty());
    }

    @Test
    void test03_noOptimalFourSum_largeTarget() {
        // Target is mathematically impossible to reach
        int[] nums = {1, 2, 3, 4, 5};
        int target = 100;
        assertTrue(OptimalFourSum.fourSum(nums, target).isEmpty());
    }

    @Test
    void test04_noSolution_targetExceededBySmallestFour() {
        // Tests algorithm's early pruning logic (if implemented)
        int[] nums = {10, 10, 10, 10, 1, 2};
        int target = 5;
        assertTrue(OptimalFourSum.fourSum(nums, target).isEmpty());
    }

    @Test
    void test05_minimumArraySize_exactlyFour() {
        // Edge case: array size is exactly 4
        int[] nums = {2, 3, 4, 5};
        int target = 14;
        List<List<Integer>> expected = new ArrayList<>(List.of(Arrays.asList(2, 3, 4, 5)));
        assertQuadrupletsEqual(expected, OptimalFourSum.fourSum(nums, target));
    }

    // --- Duplicate Handling Cases (Most Important) ---

    @Test
    void test06_allDuplicates_singleSolution() {
        // Critical test: ensures only one unique set is found
        int[] nums = {2, 2, 2, 2, 2, 2, 2};
        int target = 8;
        List<List<Integer>> expected = new ArrayList<>(List.of(Arrays.asList(2, 2, 2, 2)));
        assertQuadrupletsEqual(expected, OptimalFourSum.fourSum(nums, target));
    }

    @Test
    void test07_allZeroes() {
        // Simple case for zeroes and duplicates
        int[] nums = {0, 0, 0, 0};
        int target = 0;
        List<List<Integer>> expected = new ArrayList<>(List.of(Arrays.asList(0, 0, 0, 0)));
        assertQuadrupletsEqual(expected, OptimalFourSum.fourSum(nums, target));
    }

    @Test
    void test08_negativeNumbers_uniqueSolutions() {
        // Tests handling of a large negative target and negative-only components.
        int[] nums = {-5, -4, -3, -2, -1, 0, 1};
        int target = -10;
        List<List<Integer>> expected = new ArrayList<>(List.of(
                Arrays.asList(-5, -4, -2, 1),
                Arrays.asList(-5, -4, -1, 0),
                Arrays.asList(-5, -3, -2, 0),
                Arrays.asList(-4, -3, -2, -1)
        ));
        assertQuadrupletsEqual(expected, OptimalFourSum.fourSum(nums, target));
    }

    @Test
    void test09_complexDuplicates() {
        // Comprehensive test for duplicate skipping in all nested loops
        int[] nums = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4};
        int target = 4;
        List<List<Integer>> expected = new ArrayList<>(List.of(
                Arrays.asList(-1, 0, 1, 4), // -1 + 0 + 1 + 4 = 4
                Arrays.asList(-1, 0, 2, 3), // -1 + 0 + 2 + 3 = 4
                Arrays.asList(-1, 1, 1, 3), // -1 + 1 + 1 + 3 = 4
                Arrays.asList(-1, 1, 2, 2), // -1 + 1 + 2 + 2 = 4
                Arrays.asList(0, 0, 1, 3),  // 0 + 0 + 1 + 3 = 4
                Arrays.asList(0, 0, 2, 2),  // 0 + 0 + 2 + 2 = 4
                Arrays.asList(0, 1, 1, 2)   // 0 + 1 + 1 + 2 = 4
        ));
        assertQuadrupletsEqual(expected, OptimalFourSum.fourSum(nums, target));
    }
}