package com;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.HelloApplication.utilisateurCourant;

public class SideBarController {

    @FXML
    private Button testAnamneseButton;

    @FXML
    private Button patientFilesButton;

    @FXML
    private Button appointmentsButton;

    @FXML
    private Button statisticsButton;

    @FXML
    public void handleTestAnamnese(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("options.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handlePatientFiles() {
        System.out.println("Navigating to Patient Files");
        // Implement navigation logic
    }

    @FXML
    public void handleAppointments(ActionEvent event) {
        System.out.println("Navigating to Appointments");
        // Implement navigation logic
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleStatistics() {
        System.out.println("Navigating to Statistics");
        // Implement navigation logic
    }

    @FXML
    public void handleTestingDossier(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SearchDossier.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleSignOut(ActionEvent event) {
        utilisateurCourant = null;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.setScene(scene);
        System.out.println("Déconnection effectuée!");
        currentStage.show();
    }

    @FXML
    void goToUpdateUserInfoPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("update_user_info.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleMoreClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("custom_popup.fxml"));
            Parent root = loader.load();

            CustomPopupController controller = loader.getController();
            controller.setContactInfo("If you want help, contact here: [your contact info or support link]");

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Help Information");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
void handleLogoClick(MouseEvent event) {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("first_page.fxml"));
        Parent root = fxmlLoader.load();

        // Get the controller instance and set the userName
        FirstPageController controller = fxmlLoader.getController();
        controller.setUserName(utilisateurCourant.getPrenom() + " " + utilisateurCourant.getNom());

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
