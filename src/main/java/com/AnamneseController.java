package com;

import com.models.Anamnese;
import com.models.QuestionEnfant;
import com.models.QuestionEnfant.CategorieEnfant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.HelloApplication.oms;
import static com.HelloApplication.utilisateurCourant;

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
        saveAnamneseButton.setOnAction(this::handleSaveAnamnese);
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
    private void handleSaveAnamnese(ActionEvent event) {
        String name = anamneseNameField.getText();
        String description = anamneseDescriptionField.getText();

        if (name.isEmpty() || description.isEmpty()) {
            errorLabel.setText("Anamnese name and description cannot be empty!");
            return;
        }

        anamnese = new Anamnese(name, description);

        for (Node questionNode : questionsContainer.getChildren()) {
            if (questionNode instanceof HBox) {
                HBox hbox = (HBox) questionNode;
                TextField questionField = (TextField) hbox.lookup("#questionTextField");
                ComboBox<String> categoryComboBox = (ComboBox<String>) hbox.lookup("#categoryComboBox");
                String questionText = questionField.getText();
                String selectedCategory = categoryComboBox.getValue();

                if (!questionText.isEmpty() && selectedCategory != null) {
                    CategorieEnfant categorie = CategorieEnfant.valueOf(selectedCategory);
                    anamnese.addQuestion(new QuestionEnfant(questionText, categorie));
                }
            }
        }

        if (utilisateurCourant == null) {
            errorLabel.setText("No current user found!");
            return;
        }
        if (utilisateurCourant.getAnamneses() == null) {
            utilisateurCourant.setAnamneses(new ArrayList<>()); 
        }

        oms.addAnamneseToUser(utilisateurCourant, anamnese);
        oms.sauvegarder(); 

        System.out.println("Anamnese saved with " + anamnese.getQuestions().size() + " questions.");

        try {
            Parent root = FXMLLoader.load(getClass().getResource("options.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
