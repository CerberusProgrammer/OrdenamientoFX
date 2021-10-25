import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class Ordenamientos {

    public static void insercion(int[] A) {
        int p, j;
        int aux;

        for (p = 1; p < A.length; p++) {
            aux = A[p];
            j = p - 1;

            while ((j >= 0) && (aux < A[j])) {
                A[j + 1] = A[j];
                j--;
            }

            A[j + 1] = aux;
        }
    }

    public static void seleccion(int[] A) {
        int i, j, menor, pos, tmp;
        for (i = 0; i < A.length - 1; i++) {
            menor = A[i];
            pos = i;
            for (j = i + 1; j < A.length; j++) {
                if (A[j] < menor) {
                    menor = A[j];
                    pos = j;
                }
            }
            if (pos != i) {
                tmp = A[i];
                A[i] = A[pos];
                A[pos] = tmp;
            }
        }
    }

    public static void shell(int[] A) {
        int salto, aux, i;
        boolean cambios;

        for (salto = A.length / 2; salto != 0; salto /= 2) {
            cambios = true;
            while (cambios) {
                cambios = false;
                for (i = salto; i < A.length; i++) {
                    if (A[i - salto] > A[i]) {
                        aux = A[i];
                        A[i] = A[i - salto];
                        A[i - salto] = aux;
                        cambios = true;
                    }
                }
            }
        }
    }

    public static void quicksort(int[] A, int izq, int der) {
        int pivote = A[izq];
        int i = izq;
        int j = der;
        int aux;

        while (i < j) {
            while (A[i] <= pivote && i < j) i++;
            while (A[j] > pivote) j--;
            if (i < j) {
                aux = A[i];
                A[i] = A[j];
                A[j] = aux;
            }
        }

        A[izq] = A[j];
        A[j] = pivote;

        if (izq < j - 1)
            quicksort(A, izq, j - 1);
        if (j + 1 < der)
            quicksort(A, j + 1, der);
    }

    static void bucketSort(int arr[], int n) {
        if (n <= 0)
            return;

        @SuppressWarnings("unchecked")
        Vector<Integer>[] buckets = new Vector[n];

        for (int i = 0; i < n; i++)
            buckets[i] = new Vector<Integer>();

        for (int i = 0; i < n; i++) {
            float idx = arr[i] * n;
            buckets[(int) idx].add(arr[i]);
        }

        for (int i = 0; i < n; i++)
            Collections.sort(buckets[i]);

        int index = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < buckets[i].size(); j++)
                arr[index++] = buckets[i].get(j);
    }

    static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i])
                max = arr[i];
        }

        for (int s = 1; max / s > 0; s *= 10) {
            int[] countingArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

            for (int i = 0; i < arr.length; i++)
                countingArray[(arr[i] / s) % 10]++;

            for (int i = 1; i < 10; i++)
                countingArray[i] += countingArray[i - 1];

            int[] outputArray = {0, 0, 0, 0, 0, 0, 0, 0};
            for (int i = arr.length - 1; i >= 0; i--)
                outputArray[--countingArray[(arr[i] / s) % 10]] = arr[i];

            for (int i = 0; i < arr.length; i++)
                arr[i] = outputArray[i];
        }
    }

    static void sort(int[] A) {
        Arrays.sort(A);
    }
}
