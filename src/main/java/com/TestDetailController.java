package com;

import com.models.Question;
import com.models.Test;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TestDetailController {

    @FXML
    private Label testNameLabel;

    @FXML
    private Label testDescriptionLabel;

    @FXML
    private ListView<String> questionsListView;

    public void setTest(Test test) {
        testNameLabel.setText(test.getNom());
        testDescriptionLabel.setText(test.getCapacite());
        for (Question question : test.getQuestions()) {
            questionsListView.getItems().add(question.getEnonce());
        }
    }
}
