import java.util.HashMap;

// Java program to perform TimSort. 
class Modified_GFG_TimSort { 

    static int MIN_MERGE = 32; 
    static HashMap<String, Integer> frequencyMap = new HashMap<>();

    // Method to increment the frequency count for a given operation
    static void incrementFrequency(String operation) {
        frequencyMap.put(operation, frequencyMap.getOrDefault(operation, 0) + 1);
    }

    public static int minRunLength(int n) { 
        assert n >= 0; 
        int r = 0; 
        while (n >= MIN_MERGE) { 
            r |= (n & 1); 
            n >>= 1; 
        } 
        return n + r; 
    } 

    // This function sorts array from left index to 
    // right index which is of size at most RUN 
    public static void insertionSort(int[] arr, int left, int right) {
        incrementFrequency("Insertion Sort Call"); 
        for (int i = left + 1; i <= right; i++) { 
            int temp = arr[i]; 
            int j = i - 1; 
            while (j >= left && arr[j] > temp) { 
                arr[j + 1] = arr[j]; 
                j--; 
                incrementFrequency("Insertion Sort Comparison");
            } 
            arr[j + 1] = temp; 
            incrementFrequency("Insertion Sort Assignment");
        } 
    } 

    // Merge function merges the sorted runs 
    public static void merge(int[] arr, int l, int m, int r) { 
        incrementFrequency("Merge Call");
        int len1 = m - l + 1, len2 = r - m; 
        int[] left = new int[len1]; 
        int[] right = new int[len2]; 
        for (int x = 0; x < len1; x++) { 
            left[x] = arr[l + x]; 
            incrementFrequency("Copy to Left Array");
        } 
        for (int x = 0; x < len2; x++) { 
            right[x] = arr[m + 1 + x]; 
            incrementFrequency("Copy to Right Array");
        } 

        int i = 0; 
        int j = 0; 
        int k = l; 

        // After comparing, we merge those two arrays 
        while (i < len1 && j < len2) { 
            incrementFrequency("Merge Comparison");
            if (left[i] <= right[j]) { 
                arr[k] = left[i]; 
                i++; 
            } else { 
                arr[k] = right[j]; 
                j++; 
            } 
            k++; 
            incrementFrequency("Merge Assignment");
        } 

        // Copy remaining elements of left, if any 
        while (i < len1) { 
            arr[k] = left[i]; 
            k++; 
            i++; 
            incrementFrequency("Copy Remaining Left");
        } 

        // Copy remaining elements of right, if any 
        while (j < len2) { 
            arr[k] = right[j]; 
            k++; 
            j++; 
            incrementFrequency("Copy Remaining Right");
        } 
    } 

    // Iterative Timsort function to sort the array[0...n-1] 
    public static void timSort(int[] arr, int n) { 
        int minRun = minRunLength(MIN_MERGE); 

        // Sort individual subarrays of size RUN 
        for (int i = 0; i < n; i += minRun) { 
            insertionSort(arr, i, Math.min((i + MIN_MERGE - 1), (n - 1))); 
        } 

        // Start merging from size RUN (or 32). 
        for (int size = minRun; size < n; size = 2 * size) { 
            for (int left = 0; left < n; left += 2 * size) { 
                int mid = left + size - 1; 
                int right = Math.min((left + 2 * size - 1), (n - 1)); 

                // Merge subarray arr[left.....mid] & arr[mid+1....right] 
                if (mid < right) 
                    merge(arr, left, mid, right); 
            } 
        } 
    } 

    // Utility function to print the Array 
    public static void printArray(int[] arr, int n) { 
        for (int i = 0; i < n; i++) { 
            System.out.print(arr[i] + " "); 
        } 
        System.out.print("\n"); 
    } 

    // Driver code 
    public static void main(String[] args) { 
        int[] arr = { -2, 7, 15, -14, 0, 15, 0, 7, 
                    -7, -4, -13, 5, 8, -14, 12 }; 
        int n = arr.length; 
        System.out.println("Given Array is"); 
        printArray(arr, n); 

        timSort(arr, n); 

        System.out.println("After Sorting Array is"); 
        printArray(arr, n); 

        // Calculate total frequency count
        int totalFrequencyCount = 0;
        for (Integer count : frequencyMap.values()) {
            totalFrequencyCount += count; 
        }
        System.out.println("\nTotal Frequency Count: " + totalFrequencyCount);
    } 
} 