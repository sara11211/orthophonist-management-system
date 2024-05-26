package com;
import com.models.Test;
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

    @FXML
    private ListView<String> exercicesListView;

    public void setTest(Test test) {
        testNameLabel.setText(test.getNom());
        testDescriptionLabel.setText(test.getDescription());
    }
    public void setTest(TestQuestionnaire testQuestionnaire) {
        testNameLabel.setText(testQuestionnaire.getNom());
        testDescriptionLabel.setText(testQuestionnaire.getDescription());
        for (Question question : testQuestionnaire.getQuestions()) {
            questionsListView.getItems().add(question.getEnonce());
        }
    }

    public void setTestExercice(TestExercice testExercice) {
        testNameLabel.setText(testExercice.getNom());
        testDescriptionLabel.setText(testExercice.getDescription());
        for (Exercice exercice : testExercice.getExercices()) {
            String item = String.format("Consigne: %s, Materiel: %s, Score: %d",
                                        exercice.getConsigne(), exercice.getMateriel(), exercice.getScore());
            exercicesListView.getItems().add(item);
        }
    }
}
