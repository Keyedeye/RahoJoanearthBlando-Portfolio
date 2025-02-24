import java.util.Arrays;

public class MergeSortWithCounter {

    private int counter = 0;

    public void merge(int[] arr, int l, int m, int r) {
        counter++; // Increment counter for merge function call

        int n1 = m - l + 1;
        counter++; // Increment counter for calculation

        int n2 = r - m;
        counter++; // Increment counter for calculation

        int[] L = Arrays.copyOfRange(arr, l, m + 1); 
        counter++; // Increment counter for array copy

        int[] R = Arrays.copyOfRange(arr, m + 1, r + 1); 
        counter++; // Increment counter for array copy

        int i = 0, j = 0, k = l;
        counter++; // Increment counter for initialization

        while (i < n1 && j < n2) {
            counter++; // Increment counter for loop condition check

            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
                counter += 2; // Increment counter for assignment and increment
            } else {
                arr[k++] = R[j++];
                counter += 2; // Increment counter for assignment and increment
            }
        }

        while (i < n1) {
            counter++; // Increment counter for loop condition check
            arr[k++] = L[i++];
            counter += 2; // Increment counter for assignment and increment
        }

        while (j < n2) {
            counter++; // Increment counter for loop condition check
            arr[k++] = R[j++];
            counter += 2; // Increment counter for assignment and increment
        }
    }

    public void sort(int[] arr, int l, int r) {
        counter++; // Increment counter for sort function call

        if (l < r) {
            counter++; // Increment counter for condition check

            int m = l + (r - l) / 2;
            counter++; // Increment counter for calculation

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};

        MergeSortWithCounter sorter = new MergeSortWithCounter();
        sorter.sort(arr, 0, arr.length - 1);

        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.println("Instruction Count: " + sorter.counter);
    }
}