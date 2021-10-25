import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {

    @FXML
    private BarChart<String, Number> barChart;

    int[] array1;
    int[] array2;
    int[] array3;
    int[] array4;
    int[] array5;
    int[] array6;
    int[] array7;
    int[] array8;
    int[] array9;
    int[] array10;

    void createArrays() {
        int size = 100;

    }

    void randomNumbers(int size) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int[] array = {1,2, 3,4};

        long startTime = System.nanoTime();
        Ordenamientos.insercion(array);
        long endTime = System.nanoTime();

        XYChart.Series seriesInsercion = new XYChart.Series();
        seriesInsercion.setName("Insercion");
        seriesInsercion.getData().add(new XYChart.Data("Insercion",
                TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS)));

        barChart.getXAxis().setLabel("Metodo");
        barChart.getYAxis().setLabel("Tiempo (ms)");
        barChart.getData().addAll(seriesInsercion);
    }
}
