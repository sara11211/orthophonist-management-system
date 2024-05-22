package com;

import com.models.RDV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AddObservationController {
    @FXML
    private TextArea observationText;

    private RDV rdv;

    // Method to set the observations text

    @FXML
    void handleObservation(ActionEvent event) {
        String observation = observationText.getText();
        rdv.addObservation(observation);

    }

    public void setRDV(RDV rdv) {
        this.rdv = rdv;
    }
}
