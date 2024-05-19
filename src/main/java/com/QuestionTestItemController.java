package com;

import com.models.Question;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionTestItemController implements Initializable {

    @FXML
    private TextField questionTextField;
    @FXML
    private ComboBox<String> questionTypeComboBox;

    private TestCreationController parentController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionTypeComboBox.getItems().addAll(
            "Multiple Choice (Multiple Answers)",
            "Multiple Choice (Single Answer)",
            "Free Text"
        );
    }

    public void setParentController(TestCreationController parentController) {
        this.parentController = parentController;
    }

    public void setQuestion(Question question) {
        questionTextField.setText(question.getEnonce());
        // Logic to set question type based on the instance of the question
    }
}
