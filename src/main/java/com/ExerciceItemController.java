package com;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ExerciceItemController {

    @FXML
    private TextField consigneTextField;
    @FXML
    private TextField materielTextField;
    @FXML
    private TextField scoreTextField;

    public void setConsigne(String consigne) {
        consigneTextField.setText(consigne);
    }

    public void setMateriel(String materiel) {
        materielTextField.setText(materiel);
    }

    public void setScore(int score) {
        scoreTextField.setText(String.valueOf(score));
    }

    public String getConsigne() {
        return consigneTextField.getText();
    }

    public String getMateriel() {
        return materielTextField.getText();
    }

    public int getScore() {
        try {
            return Integer.parseInt(scoreTextField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
