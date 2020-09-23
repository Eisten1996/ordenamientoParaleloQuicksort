package pe.edu.unmsm.sistemas;

/**
 * @author Eisten Flores
 * @project ordenamiento
 */
public class QuickSort {
    /* This function takes last element as pivot,
       places the pivot element at its correct
       position in sorted array, and places all
       smaller (smaller than pivot) to left of
       pivot and all greater elements to right
       of pivot */
    int partition(int arr[], int ini, int fin) {
        int pivot = arr[fin];
        int i = (ini - 1); // index of smaller element
        for (int j = ini; j < fin; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[fin] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[fin];
        arr[fin] = temp;

        return i + 1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      ini  --> Starting index,
      fin  --> Ending index */
    void sort(int arr[], int ini, int fin) {
        if (ini < fin) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, ini, fin);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, ini, pi - 1);
            sort(arr, pi + 1, fin);
        }
    }
}
