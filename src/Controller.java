import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {

    @FXML
    private BarChart<String, Number> barChart;


    ArrayList<int[]> arrays = new ArrayList<>();

    void createArrays(int amountArrays, int sizeElements) {
        for (int i = 0; i < amountArrays; i++) {
            int[] array = randomNumbers(sizeElements);
            arrays.add(array);
        }
    }

    int[] randomNumbers(int size) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < size; i++)
            arrayList.add(i);

        Collections.shuffle(arrayList);
        int[] ints = new int[size];

        for (int i = 0; i < size; i++)
            ints[i] = arrayList.get(i);

        return ints;
    }

    void nextOrden(int orden, int[] A) {
        switch (orden) {
            case 0:
                Ordenamientos.insercion(A);
                break;
            case 1:
                Ordenamientos.seleccion(A);
                break;
            case 2:
                Ordenamientos.shell(A);
                break;
            case 3:
                Ordenamientos.quicksort(A, 0, 0);
                break;
            case 4:
                Ordenamientos.bucketSort(A, 0);
                break;
            case 5:
                // Ordenamientos.radixSort(A);
                break;
            case 6:
                Ordenamientos.sort(A);
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createArrays(6, 100);
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<>();
        int eleccionOrden = 0;
        String[] strings = {
                "Insercion",
                "Seleccion",
                "Shell",
                "QuickSort",
                "BucketSort",
                "RadixSort",
                "Arrays.sort()"
        };

        for (int[] ints: arrays) {
            long startTime = System.nanoTime();
            nextOrden(eleccionOrden, ints);
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            // TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS)
            eleccionOrden++;

            XYChart.Series seriesInsercion = new XYChart.Series();
            seriesInsercion.setName(strings[eleccionOrden]);
            seriesInsercion.getData().add(new XYChart.Data(strings[eleccionOrden], totalTime));
            seriesArrayList.add(seriesInsercion);
        }

        for (XYChart.Series series: seriesArrayList)
            barChart.getData().add(series);
    }
}
