package com;

import com.models.Exercice;
import com.models.TestExercice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import static com.HelloApplication.oms;
import static com.HelloApplication.utilisateurCourant;

public class TestExerciceController {

    @FXML
    private TextField testNameField;
    @FXML
    private TextArea testDescriptionField;
    @FXML
    private VBox exercicesContainer;
    @FXML
    private Button addExerciceButton;
    @FXML
    private Button saveTestButton;

    private TestExercice testExercice;

    @FXML
    public void initialize() {
        addExerciceButton.setOnAction(this::handleAddExercice);
        saveTestButton.setOnAction(this::handleSaveTest);
    }

    @FXML
    private void handleAddExercice(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("exercice_item.fxml"));
            Parent exerciceNode = loader.load();
            ExerciceItemController controller = loader.getController();
            exerciceNode.setUserData(controller);
            exercicesContainer.getChildren().add(exerciceNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSaveTest(ActionEvent event) {
        String name = testNameField.getText();
        String description = testDescriptionField.getText();

        if (name.isEmpty() || description.isEmpty()) {
            return;
        }

        if (testExercice == null) {
            testExercice = new TestExercice(name, description);
        } else {
            testExercice.setNom(name);
            testExercice.setDescription(description);
            testExercice.getExercices();
        }

        for (Node exerciceNode : exercicesContainer.getChildren()) {
            ExerciceItemController controller = (ExerciceItemController) exerciceNode.getUserData();
            if (controller == null) {
                System.out.println("Controller is null for one of the exercise nodes");
                continue;
            }
            String consigne = controller.getConsigne();
            String materiel = controller.getMateriel();
            int score = controller.getScore();
            if (!consigne.isEmpty() && !materiel.isEmpty() && score > 0) {
                testExercice.addExercice(new Exercice(consigne, materiel, score));
            }
        }

        if (utilisateurCourant == null) {
            return;
        }
        if (utilisateurCourant.getTests() == null) {
            utilisateurCourant.setTests(new HashSet<>());
        }

        if (!utilisateurCourant.getTests().contains(testExercice)) {
            utilisateurCourant.addTest(testExercice);
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

    public void setTestExercice(TestExercice testExercice) {
        this.testExercice = testExercice;
        testNameField.setText(testExercice.getNom());
        testDescriptionField.setText(testExercice.getDescription());

        exercicesContainer.getChildren().clear();
        for (Exercice exercice : testExercice.getExercices()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("exercice_item.fxml"));
                Parent exerciceNode = loader.load();
                ExerciceItemController controller = loader.getController();
                controller.setConsigne(exercice.getConsigne());
                controller.setMateriel(exercice.getMateriel());
                controller.setScore(exercice.getScore());
                exercicesContainer.getChildren().add(exerciceNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
