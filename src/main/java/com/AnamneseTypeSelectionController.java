package com;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AnamneseTypeSelectionController {

    @FXML
    private void handleCreateEnfantAnamnese(ActionEvent event) {
        loadAnamneseCreationScene(event, "enfant");
    }

    @FXML
    private void handleCreateAdulteAnamnese(ActionEvent event) {
        loadAnamneseCreationScene(event, "adulte");
    }

    private void loadAnamneseCreationScene(ActionEvent event, String type) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anamnese_creation.fxml"));
            Parent root = loader.load();

            AnamneseController controller = loader.getController();
            controller.setAnamneseType(type);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
