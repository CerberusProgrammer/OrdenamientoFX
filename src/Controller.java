import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {

    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private TabPane tabPane;

    ArrayList<int[]> arrays = new ArrayList<>();

    void createArrays(int amountArrays, int sizeElements, int multiplier) {
        for (int i = 0; i < amountArrays; i++) {
            int[] array = randomNumbers(sizeElements * multiplier);
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
        int eleccionOrden = 0;
        int size = 100;
        String[] strings = {"Insercion", "Seleccion", "Shell", "Quick", "Bucket", "Radix", "Sort"};

        for (int i = 0; i < 5; i++) {
            arrays.clear();
            createArrays(strings.length, size, 1);
            ArrayList<XYChart.Series> series = new ArrayList<>();

            for (int[] ints: arrays) {
                long startTime = System.nanoTime();
                nextOrden(eleccionOrden, ints);
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;

                XYChart.Series seriesInsercion = new XYChart.Series();
                seriesInsercion.setName(strings[eleccionOrden] + " (" + totalTime + " ns)");
                seriesInsercion.getData().add(new XYChart.Data(strings[eleccionOrden], totalTime));
                series.add(seriesInsercion);
                eleccionOrden++;
            }

            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Metodos de Ordenacion");

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Tiempo (ns)");

            BarChart<String, Number> barChart1 = new BarChart<>(xAxis, yAxis);
            barChart1.setMaxSize(1280, 690);
            barChart1.setMinSize(1280, 690);

            for (XYChart.Series s: series)
                barChart1.getData().add(s);

            Tab tab = new Tab();
            tab.setText("Cantidad: " + size);
            Pane pane = new Pane();
            pane.setMaxSize(1280, 690);
            pane.setMinSize(1280, 690);
            pane.getChildren().add(barChart1);
            tab.setContent(pane);
            tabPane.getTabs().add(tab);

            System.out.println(size);
            size *= 5;
            eleccionOrden = 0;
        }
    }
}
