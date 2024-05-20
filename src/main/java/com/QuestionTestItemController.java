package com;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import com.models.Question;

public class QuestionTestItemController implements Initializable {

    @FXML
    private TextField questionTextField;
    @FXML
    private ComboBox<String> questionTypeComboBox;
    @FXML
    private VBox propositionsContainer;
    @FXML
    private Button addPropositionButton;

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

    @FXML
    private void handleQuestionTypeChange() {
        String selectedType = questionTypeComboBox.getValue();
        if (selectedType != null && (selectedType.contains("Multiple Choice"))) {
            addPropositionButton.setVisible(true);
        } else {
            addPropositionButton.setVisible(false);
            propositionsContainer.getChildren().clear();
        }
    }

    @FXML
    private void handleAddProposition() {
        TextField propositionField = new TextField();
        propositionField.setPromptText("Proposition");
        propositionsContainer.getChildren().add(propositionField);
    }

    public VBox getPropositionsContainer() {
        return propositionsContainer;
    }
}