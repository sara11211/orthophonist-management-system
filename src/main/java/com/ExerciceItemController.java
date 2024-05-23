package com;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class ExerciceItemController {

    @FXML
    private TextField consigneTextField;
    @FXML
    private TextField materielTextField;
    @FXML
    private Spinner<Integer> scoreSpinner;

    @FXML
    public void initialize() {
        // Initialize the spinner with values from 1 to 10
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        scoreSpinner.setValueFactory(valueFactory);
    }

    public void setConsigne(String consigne) {
        consigneTextField.setText(consigne);
    }

    public void setMateriel(String materiel) {
        materielTextField.setText(materiel);
    }

    public void setScore(int score) {
        scoreSpinner.getValueFactory().setValue(score);
    }

    public String getConsigne() {
        return consigneTextField.getText();
    }

    public String getMateriel() {
        return materielTextField.getText();
    }

    public int getScore() {
        return scoreSpinner.getValue();
    }
}
