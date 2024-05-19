package com;

import com.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.util.ArrayList;

import static com.HelloApplication.oms;
import static com.HelloApplication.utilisateurCourant;

import com.models.Proposition;

public class TestCreationController {
    @FXML
    private TextField testNameField;
    @FXML
    private TextArea testDescriptionField;
    @FXML
    private VBox questionsContainer;
    @FXML
    private Button addQuestionButton;
    @FXML
    private Button saveTestButton;
    @FXML
    private Label errorLabel;

    private Test test;

    @FXML
    public void initialize() {
        addQuestionButton.setOnAction(this::handleAddQuestion);
        saveTestButton.setOnAction(this::handleSaveTest);
    }

    @FXML
    private void handleAddQuestion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("question_item_test.fxml"));
            Parent questionNode = loader.load();
            QuestionTestItemController controller = loader.getController();
            controller.setParentController(this);
            questionsContainer.getChildren().add(questionNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSaveTest(ActionEvent event) {
        String name = testNameField.getText();
        String description = testDescriptionField.getText();

        if (name.isEmpty() || description.isEmpty()) {
            errorLabel.setText("Test name and description cannot be empty!");
            return;
        }

        if (test == null) {
            test = new Test(name, description, new ArrayList<>());
        } else {
            test.setNom(name);
            test.setCapacite(description);
            test.getQuestions().clear();
        }

        for (Node questionNode : questionsContainer.getChildren()) {
            if (questionNode instanceof HBox) {
                HBox hbox = (HBox) questionNode;
                TextField questionField = (TextField) hbox.lookup("#questionTextField");
                ComboBox<String> questionTypeComboBox = (ComboBox<String>) hbox.lookup("#questionTypeComboBox");
                VBox propositionsContainer = (VBox) hbox.lookup("#propositionsContainer");
                String questionText = questionField.getText();
                String questionType = questionTypeComboBox.getValue();

                if (!questionText.isEmpty() && questionType != null) {
                    if (questionType.equals("Multiple Choice (Multiple Answers)") || questionType.equals("Multiple Choice (Single Answer)")) {
                        ArrayList<Proposition> propositions = new ArrayList<>();
                        for (Node propositionNode : propositionsContainer.getChildren()) {
                            if (propositionNode instanceof TextField) {
                                TextField propositionField = (TextField) propositionNode;
                                String propositionText = propositionField.getText();
                                if (!propositionText.isEmpty()) {
                                    propositions.add(new Proposition(propositionText));
                                }
                            }
                        }

                        if (questionType.equals("Multiple Choice (Multiple Answers)")) {
                            test.getQuestions().add(new QCM(questionText, propositions.toArray(new Proposition[0])));
                        } else {
                            test.getQuestions().add(new QCU(questionText, propositions.toArray(new Proposition[0])));
                        }
                    } else if (questionType.equals("Free Text")) {
                        test.getQuestions().add(new QuestionLibre(questionText, 0)); // Add Free Text question
                    }
                }
            }
        }

        if (utilisateurCourant == null) {
            errorLabel.setText("No current user found!");
            return;
        }

        if (utilisateurCourant.getTests() == null) {
            utilisateurCourant.setTests(new ArrayList<>());
        }

        if (!utilisateurCourant.getTests().contains(test)) {
            utilisateurCourant.getTests().add(test);
        }
        oms.sauvegarder();

        System.out.println("Test saved with " + test.getQuestions().size() + " questions.");

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

    public void setTest(Test test) {
        this.test = test;
        testNameField.setText(test.getNom());
        testDescriptionField.setText(test.getCapacite());
        for (Question question : test.getQuestions()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("question_item_test.fxml"));
                Parent questionNode = loader.load();
                QuestionTestItemController controller = loader.getController();
                controller.setQuestion(question);
                questionsContainer.getChildren().add(questionNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
