package com;

import com.models.Exercice;
import com.models.Question;
import com.models.TestExercice;
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

    public void setTest(TestExercice testQuestionnaire) {
        testNameLabel.setText(testQuestionnaire.getNom());
        testDescriptionLabel.setText(testQuestionnaire.getDescription());
        for (Exercice question : testQuestionnaire.getExercices()) {
            questionsListView.getItems().add(question.getConsigne());
        }
    }
}
