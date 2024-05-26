package com;

import static com.HelloApplication.utilisateurCourant;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

import com.models.BO;
import com.models.Diagnostic;
import com.models.Patient;
import com.models.Trouble;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class FirstPageController implements Initializable {

    @FXML
    private Label userName;

    @FXML
    private PieChart pieChart;

    public void setUserName(String name) {
        userName.setText(name);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HashMap<String, Integer> troubleCount = new HashMap<>();

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

        // Create PieChart.Data objects
        for (String troubleType : troubleCount.keySet()) {
            pieChart.getData().add(new PieChart.Data(troubleType, troubleCount.get(troubleType)));
        }
    }
}
