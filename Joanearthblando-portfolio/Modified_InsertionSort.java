public class Modified_InsertionSort {
    static int FrequencyCount = 0; // Counter for operations

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                FrequencyCount++; // Count comparison
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    FrequencyCount++; // Count assignment
                    j = j - 1;
                } else {
                    break; // Exit the loop if no more comparisons are needed
                }
            }
            arr[j + 1] = key;
            FrequencyCount++; // Count assignment for placing the key
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        insertionSort(arr);
        System.out.println("Sorted array is:");
        printArray(arr);
        System.out.println("Total Frequency Count: " + FrequencyCount);
    }
}