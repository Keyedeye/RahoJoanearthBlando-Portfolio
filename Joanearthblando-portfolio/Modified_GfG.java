// Java program for Merge Sort
import java.io.*;
import java.util.HashMap;

class Modified_GfG {
    static HashMap<String, Integer> frequencyMap = new HashMap<>();
     
    // Method to increment the frequency count for a given operation
    static void incrementFrequency(String operation) {
        frequencyMap.put(operation, frequencyMap.getOrDefault(operation, 0) + 1);
    }
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    static void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1; // Size of left subarray
        int n2 = r - m;     // Size of right subarray

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i]; // Copy left subarray
            incrementFrequency("Copy L");
        }
            for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j]; // Copy right subarray
            incrementFrequency("Copy R");
        }
        // Merge the temp arrays
        int i = 0, j = 0; // Initial indices of first and second subarrays
        int k = l;        // Initial index of merged subarray

        // Merge the two subarrays
        while (i < n1 && j < n2) {
            incrementFrequency("Comparison");
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                incrementFrequency("Assign L");
                i++;
            } else {
                arr[k] = R[j];
                incrementFrequency("Assign R");
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            incrementFrequency("Assign L (remaining)");
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            incrementFrequency("Assign R (remaining)");
        j++;
        k++;
        }
    }

    // Main function that sorts arr[l..r] using merge()
    static void sort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    // A utility function to print array of size n
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver code
    public static void main(String args[]) {
        int arr[] = { 12, 11, 13, 5, 6, 7 };

        System.out.println("Given array is");
        printArray(arr);

        sort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array is");
        printArray(arr);

        int totalFrequencyCount = 0;
        for (Integer count : frequencyMap.values()) {
            totalFrequencyCount += count; 
        }
 
        System.out.println("\nFrequency counts of operations:");
        for (String operation : frequencyMap.keySet()) {
        System.out.println(operation + ": " + frequencyMap.get(operation));
        }
        
        System.out.println("\nTotal frequency count of operations: " + totalFrequencyCount);
    }
} 