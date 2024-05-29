package com;

import static com.HelloApplication.utilisateurCourant;

import java.net.URL;

import com.models.BO;
import com.models.Diagnostic;
import com.models.Patient;
import com.models.Trouble;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.*;
import java.util.stream.Collectors;
public class FirstPageController implements Initializable {
    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;
    @FXML
    private Label userName;

    @FXML
    private PieChart pieChart;
    private Map<String, Integer> troubleNomCount = new HashMap<>();

    public void setUserName(String name) {
        userName.setText(name);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HashMap<String, Integer> troubleCount = new HashMap<>();
        HashMap<String, Integer> troubleNomCount = new HashMap<>();
        String css = getClass().getResource("linechart.css").toExternalForm();
        lineChart.getStylesheets().add(css);

        // Traverse through orthophonistes and collect information
        for (Patient patient : utilisateurCourant.getPatientDossierHashMap().values()) {
            for (BO bo : patient.getBos()) {
                Diagnostic diagnostic = bo.getDiagnostic();
                if (diagnostic != null) {
                    HashSet<Trouble> troubles = diagnostic.getTroubles();
                    if (troubles != null) {
                        for (Trouble trouble : troubles) {
                            if (trouble != null) {
                                String troubleType = trouble.getTypeTrouble();
                                troubleCount.put(troubleType, troubleCount.getOrDefault(troubleType, 0) + 1);
                            }
                        }
                    }
                }
            }
        }

        for (String troubleType : troubleCount.keySet()) {
            pieChart.getData().add(new PieChart.Data(troubleType, troubleCount.get(troubleType)));
        }
        pieChart.setLabelsVisible(false);

        fetchData();

        populateChart();
    }

    private void fetchData() {
        for (Patient patient : utilisateurCourant.getPatientDossierHashMap().values()) {
            for (BO bo : patient.getBos()) {
                Diagnostic diagnostic = bo.getDiagnostic();
                if (diagnostic != null) {
                    HashSet<Trouble> troubles = diagnostic.getTroubles();
                    if (troubles != null) {
                        for (Trouble trouble : troubles) {
                            if (trouble != null) {
                                String troubleNom = trouble.getNomTrouble();
                                troubleNomCount.put(troubleNom, troubleNomCount.getOrDefault(troubleNom, 0) + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    private void populateChart() {
        Map<String, Integer> sortedData = troubleNomCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : sortedData.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        lineChart.getData().add(series);
        lineChart.setLegendVisible(false);
    }
}
