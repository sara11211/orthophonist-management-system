package com;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.models.UtilisateurNonTrouveException;
import com.models.Orthophoniste;

import java.io.IOException;

import static com.HelloApplication.oms;
import static com.HelloApplication.utilisateurCourant;

public class ConnexionController {

    @FXML
    private Label erreurText;
    @FXML
    private TextField adresseEmail;
    @FXML
    private PasswordField motDePasse;
    @FXML
    private Label userName;
    private Stage stage;
    private Scene scene;

    @FXML
    void signupButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signup.fxml"));
        scene = new Scene(fxmlLoader.load());

        Button button = (Button) event.getSource();
        stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void seConnecterButton(ActionEvent event) throws UtilisateurNonTrouveException, IOException {
        String userEmail = adresseEmail.getText();
        String userMdp = motDePasse.getText();

        if (oms.isExist(userEmail, userMdp)) {
            Orthophoniste user = oms.findUser(userEmail, userMdp);
            if (user != null) {
                utilisateurCourant = oms.findUser(userEmail, userMdp);

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("first_page.fxml"));
                Parent root = fxmlLoader.load();

                // Get the controller instance and set the userName
                ConnexionController controller = fxmlLoader.getController();
                controller.userName.setText(utilisateurCourant.getPrenom() + " " + utilisateurCourant.getNom());

                scene = new Scene(root);

                Button button = (Button) event.getSource();
                stage = (Stage) button.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                connexionInvalid();
            }
        } else {
            connexionInvalid();
        }
    }

    public void connexionInvalid() {
        erreurText.setText("E-mail ou Mot de passe non valide !");
    }

    @FXML
    private void handleTestAnamnese(ActionEvent event) {
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
}
