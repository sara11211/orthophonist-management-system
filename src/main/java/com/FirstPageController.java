package com;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FirstPageController {

    @FXML
    private Label userName;

    public void setUserName(String name) {
        userName.setText(name);
    }
}
