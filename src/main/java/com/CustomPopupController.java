package com;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CustomPopupController {

    @FXML
    private Label contactInfoLabel;

    @FXML
    private void handleOk() {
        Stage stage = (Stage) contactInfoLabel.getScene().getWindow();
        stage.close();
    }

    public void setContactInfo(String contactInfo) {
        contactInfoLabel.setText(contactInfo);
    }
}
