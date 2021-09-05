package org.example;

import java.util.*;

public class HeapSort {
    private static int comparisons = 0;
    private static int swaps = 0;

    /**
     * SORT ASC
     */
    private static void buildHeapAsc(int[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heapifyAsc(arr, i, arr.length - 1);
        }
    }

    private static void heapifyAsc(int[] arr, int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max;
        if (left <= size && arr[left] > arr[i]) {
            max = left;
        } else {
            max = i;
        }

        if (right <= size && arr[right] > arr[max]) {
            max = right;
        }

        if (max != i) {
            exchange(arr, i, max);
            heapifyAsc(arr, max, size);
        }

        comparisons += 5;
    }

    private static void exchange(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;

        swaps++;
    }

    public static String sortAscReturnStat(int[] arr) {
        final long startTime = System.nanoTime();
        sortAsc(arr);
        final long endTime = System.nanoTime();

        return "===== Heap sort ASC =====" +
                "\nExecution time: " + (endTime - startTime) + "ns" +
                "\nComparisons: " + comparisons +
                "\nSwaps " + swaps +
                "\nSorted array: " + Arrays.toString(arr);
    }

    public static int[] sortAsc(int[] arr) {
        buildHeapAsc(arr);
        int sizeOfHeap = arr.length - 1;
        for (int i = sizeOfHeap; i > 0; i--) {
            exchange(arr, 0, i);
            sizeOfHeap = sizeOfHeap - 1;
            heapifyAsc(arr, 0, sizeOfHeap);
        }
        return arr;
    }

    public static int sortAscSwaps(int[] arr) {
        buildHeapAsc(arr);
        int sizeOfHeap = arr.length - 1;
        for (int i = sizeOfHeap; i > 0; i--) {
            exchange(arr, 0, i);
            sizeOfHeap = sizeOfHeap - 1;
            heapifyAsc(arr, 0, sizeOfHeap);
        }
        return swaps;
    }

    /**
     * SORT DESC
     */
    private static void buildHeap(int[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heapifyDesc(arr, i, arr.length - 1);
        }
    }

    private static void heapifyDesc(int[] arr, int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int min = i;

        if (left < size && arr[left] < arr[min]) {
            min = left;
        }

        if (right < size && arr[right] < arr[min]) {
            min = right;
        }

        if (min != i) {
            exchange(arr, i, min);
            heapifyDesc(arr, min, size);
        }

        comparisons += 5;
    }

    public static String sortDescReturnStat(int[] arr) {
        final long startTime = System.nanoTime();
        sortDesc(arr);
        final long endTime = System.nanoTime();

        return "===== Heap sort DESC =====" +
                "\nExecution time: " + (endTime - startTime) + "ns" +
                "\nComparisons: " + comparisons +
                "\nSwaps " + swaps +
                "\nSorted array: " + Arrays.toString(arr);
    }

    public static int[] sortDesc(int[] arr) {
        buildHeap(arr);
        int sizeOfHeap = arr.length - 1;
        for (int i = sizeOfHeap; i > 0; i--) {
            exchange(arr, 0, i);
            sizeOfHeap = sizeOfHeap - 1;
            heapifyDesc(arr, 0, sizeOfHeap);
        }
        return arr;
    }
}
