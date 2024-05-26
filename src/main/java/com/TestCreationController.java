package com;

import com.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static com.HelloApplication.oms;
import static com.HelloApplication.utilisateurCourant;

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

    private TestQuestionnaire testQuestionnaire;

     private Map<QuestionTestItemController, Question> controllerQuestionMap = new HashMap<>();

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

        if (testQuestionnaire == null) {
            testQuestionnaire = new TestQuestionnaire(name, description, new HashSet<>());
        } else {
            testQuestionnaire.setNom(name);
            testQuestionnaire.setDescription(description);

            testQuestionnaire.getQuestions().clear();
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
                    HashSet<Proposition> propositions = new HashSet<>();
                    if (questionType.equals("Multiple Choice (Multiple Answers)") || questionType.equals("Multiple Choice (Single Answer)")) {
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
                            testQuestionnaire.getQuestions().add(new QCM(questionText, propositions.toArray(new Proposition[0])));
                        } else {
                            testQuestionnaire.getQuestions().add(new QCU(questionText, propositions.toArray(new Proposition[0])));
                        }
                    } else if (questionType.equals("Free Text")) {
                        testQuestionnaire.getQuestions().add(new QuestionLibre(questionText, 0)); // Add Free Text question
                    }
                }
            }
        }
        testQuestionnaire.setDateTest(LocalDate.now());
        if (utilisateurCourant == null) {
            errorLabel.setText("No current user found!");
            return;
        }

        if (!utilisateurCourant.getTests().contains(testQuestionnaire)) {
            utilisateurCourant.getTests().add(testQuestionnaire);
        } else {
            // Replace the existing test
            utilisateurCourant.getTests().remove(testQuestionnaire);
            utilisateurCourant.getTests().add(testQuestionnaire);
        }
        oms.sauvegarder();

        System.out.println("Test saved with " + testQuestionnaire.getQuestions().size() + " questions.");

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

    public void setTest(TestQuestionnaire testQuestionnaire) {
        this.testQuestionnaire = testQuestionnaire;
        testNameField.setText(testQuestionnaire.getNom());
        testDescriptionField.setText(testQuestionnaire.getDescription());
        for (Question question : testQuestionnaire.getQuestions()) {
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
