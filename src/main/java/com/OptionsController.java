package com;

import com.models.Anamnese;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.HelloApplication.oms;
import static com.HelloApplication.utilisateurCourant;

public class OptionsController {

    @FXML
    private void handleCreateAnamnese(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("anamnese_type_selection.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
private void handleViewAnamneses(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("anamneses_display.fxml"));
        Parent root = loader.load();

        AnamnesesDisplayController controller = loader.getController();
        List<Anamnese> anamneses = HelloApplication.oms.getAnamnesesByUser(HelloApplication.utilisateurCourant);

        if (anamneses == null) {
            System.err.println("No anamneses found for the user");
            anamneses = new ArrayList<>(); 
        }

        controller.setAnamneses(anamneses);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@FXML
    private void handleCreateTest(ActionEvent event) {
        loadScene(event, "test_creation.fxml");
    }

    @FXML
    private void handleViewTests(ActionEvent event) {
        loadScene(event, "view_tests.fxml");
    }

    private void loadScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}