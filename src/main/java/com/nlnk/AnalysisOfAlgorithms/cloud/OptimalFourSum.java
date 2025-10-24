package com.nlnk.AnalysisOfAlgorithms.cloud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 14. Fast 4-sum. Develop an optimal solution {@link OptimalFourSum} to the 4-sum problem.
 */
public class OptimalFourSum {
    public static List<List<Integer>> fourSum(int[] arr, int target) {
        Arrays.sort(arr);

        final int size = arr.length;
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < size - 3; ++i) {
            if (i != 0 && arr[i] == arr[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < size - 2; ++j) {
                if (j != i + 1 && arr[j] == arr[j - 1]) {
                    continue;
                }

                final int key = target - arr[i] - arr[j];
                int L = j + 1;
                int R = size - 1;

                while (L < R) {
                    final int sum = arr[L] + arr[R];

                    if (sum > key) {
                        --R;
                    } else if (sum < key) {
                        ++L;
                    } else {
                        ans.add(Arrays.asList(arr[i], arr[j], arr[L], arr[R]));
                        ++L; --R;

                        while (L < R && arr[L] == arr[L - 1]) ++L;
                        while (R > L && arr[R] == arr[R + 1]) ++R;
                    }
                }
            }
        }

        return ans;
    }
}