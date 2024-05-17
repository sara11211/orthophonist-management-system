package com;

import com.models.Anamnese;
import com.models.Question;

import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AnamneseController {
    @FXML
    private TextField anamneseNameField;
    @FXML
    private TextArea anamneseDescriptionField;
    @FXML
    private VBox questionsContainer;
    @FXML
    private Button addQuestionButton;
    @FXML
    private Button saveAnamneseButton;
    @FXML
    private Label errorLabel;

    private Anamnese anamnese;

    @FXML
    public void initialize() {
        addQuestionButton.setOnAction(this::handleAddQuestion);
        saveAnamneseButton.setOnAction(this::handleSaveQuiz);
    }

    @FXML
    private void handleAddQuestion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("question_item.fxml"));
            Parent questionNode = loader.load();
            questionsContainer.getChildren().add(questionNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSaveQuiz(ActionEvent event) {
        String name = anamneseNameField.getText();
        String description = anamneseDescriptionField.getText();

        if (name.isEmpty() || description.isEmpty()) {
            errorLabel.setText("anamnese name and description cannot be empty!");
            return;
        }

        anamnese = new Anamnese(name, description);

        for (Node questionNode : questionsContainer.getChildren()) {
            if (questionNode instanceof HBox) { 
              HBox hbox = (HBox) questionNode;
              TextField questionField = (TextField) hbox.lookup("#questionTextField");
              String questionText = questionField.getText();
              if (!questionText.isEmpty()) {
                anamnese.addQuestion(new Question(questionText));
              }
            }
          }

        System.out.println("Anamnese saved with " + anamnese.getQuestions().size() + " questions.");
    }
}