package com.nlnk.AnalysisOfAlgorithms.cloud;

/**
 * 18. Local minimum in an array. Write a program that, given an array a[] of n
 * distinct integers, finds a local minimum: an index i such that both
 * {@code a[i] < a[i-1]} and {@code a[i] < a[i+1]} (assuming the neighboring entry is in bounds).
 * Your program should use ~ 2 lg n compares in the worst case.
 */
public class AnArrayLocalMinimum {
    private static int search(int[] arr, int L, int R) {
        if (R - L + 1 == 1) return L;
        if (R - L + 1 == 2) return (arr[L] < arr[R]) ? L : R;

        final int M = L + (R - L) / 2;

        if (arr[M] < arr[M - 1] && arr[M] < arr[M + 1])
            return M;

        if (arr[M - 1] < arr[M + 1])
            return search(arr, L, M - 1);

        return search(arr, M + 1, R);
    }

    public static int aLocalMinimum(int[] arr) {
        return search(arr, 0, arr.length - 1);
    }
}