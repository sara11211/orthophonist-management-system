package com;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
public class ObservationsController {

    @FXML
    private TextArea observationsText;

    // Method to set the observations text
    public void setObservations(String observations) {
        observationsText.setText(observations);
    }
}
