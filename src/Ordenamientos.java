public class Ordenamientos {

    public static int[] insercion(int[] A){
        int p, j;
        int aux;

        for (p = 1; p < A.length; p++){
            aux = A[p];
            j = p - 1;

            while ((j >= 0) && (aux < A[j])){
                A[j + 1] = A[j];
                j--;
            }

            A[j + 1] = aux;
        }

        return A;
    }


}
