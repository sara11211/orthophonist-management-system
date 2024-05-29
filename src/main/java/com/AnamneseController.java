package com;

import com.models.Anamnese;
import com.models.Question;
import com.models.QuestionAdulte;
import com.models.QuestionEnfant;
import com.models.QuestionAdulte.CategorieAdulte;
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

    private Anamnese anamnese;
    private String anamneseType;

    @FXML
    public void initialize() {
        addQuestionButton.setOnAction(this::handleAddQuestion);
        saveAnamneseButton.setOnAction(this::handleSaveAnamnese);
    }

    public void setAnamneseType(String type) {
        this.anamneseType = type;
    }

    @FXML
    private void handleAddQuestion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("question_item.fxml"));
            Parent questionNode = loader.load();
            QuestionItemController controller = loader.getController();

            if ("enfant".equals(anamneseType)) {
                controller.setCategories(CategorieEnfant.values());
            } else if ("adulte".equals(anamneseType)) {
                controller.setCategories(CategorieAdulte.values());
            }

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
            return;
        }

        if (anamnese == null) {
            anamnese = new Anamnese(name, description, anamneseType); // Pass type to the constructor
        } else {
            anamnese.setNom(name);
            anamnese.setDescription(description);
            anamnese.getQuestions();
        }

        for (Node questionNode : questionsContainer.getChildren()) {
            if (questionNode instanceof HBox) {
                HBox hbox = (HBox) questionNode;
                TextField questionField = (TextField) hbox.lookup("#questionTextField");
                ComboBox<String> categoryComboBox = (ComboBox<String>) hbox.lookup("#categoryComboBox");
                String questionText = questionField.getText();
                String selectedCategory = categoryComboBox.getValue();

                if (!questionText.isEmpty() && selectedCategory != null) {
                    if ("enfant".equals(anamneseType)) {
                        CategorieEnfant categorie = CategorieEnfant.valueOf(selectedCategory);
                        anamnese.addQuestion(new QuestionEnfant(questionText, categorie));
                    } else if ("adulte".equals(anamneseType)) {
                        CategorieAdulte categorie = CategorieAdulte.valueOf(selectedCategory);
                        anamnese.addQuestion(new QuestionAdulte(questionText, categorie));
                    }
                }
            }
        }

        if (utilisateurCourant == null) {
            return;
        }
        if (utilisateurCourant.getAnamneses() == null) {
            utilisateurCourant.setAnamneses(new ArrayList<>());
        }

        if (!utilisateurCourant.getAnamneses().contains(anamnese)) {
            oms.addAnamneseToUser(utilisateurCourant, anamnese);
        }
        oms.sauvegarder();


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

    public void setAnamnese(Anamnese anamnese) {
        this.anamnese = anamnese;
        anamneseNameField.setText(anamnese.getNom());
        anamneseDescriptionField.setText(anamnese.getDescription());

        questionsContainer.getChildren().clear();
        for (Question question : anamnese.getQuestions()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("question_item.fxml"));
                Parent questionNode = loader.load();
                QuestionItemController controller = loader.getController();

                controller.setQuestionText(question.getEnonce());
                if (question instanceof QuestionEnfant) {
                    controller.setSelectedCategory(((QuestionEnfant) question).getCategorie().name());
                } else if (question instanceof QuestionAdulte) {
                    controller.setSelectedCategory(((QuestionAdulte) question).getCategorie().name());
                }

                questionsContainer.getChildren().add(questionNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
