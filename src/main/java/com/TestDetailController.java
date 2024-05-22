package com;

import com.models.Question;
import com.models.TestQuestionnaire;
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

    public void setTest(TestQuestionnaire testQuestionnaire) {
        testNameLabel.setText(testQuestionnaire.getNom());
        testDescriptionLabel.setText(testQuestionnaire.getDescription());
        for (Question question : testQuestionnaire.getQuestions()) {
            questionsListView.getItems().add(question.getEnonce());
        }
    }
}
