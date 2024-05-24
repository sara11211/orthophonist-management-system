package com;

import com.models.OMS;
import com.models.Orthophoniste;
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

import java.io.IOException;

public class InscriptionController {

    @FXML
    private Label erreurText;
    @FXML
    private TextField email;
    @FXML
    private PasswordField motDePasse;
    @FXML
    private TextField firstName;
    @FXML
    private TextField familyName;
    @FXML
    private TextField telephone;
    @FXML
    private TextField address;

    @FXML
    void seConnecterButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void inscriptionButton(ActionEvent event) {
        String userEmail = email.getText();
        String userMotDePasse = motDePasse.getText();
        String userFamilyName = familyName.getText();
        String userFirstName = firstName.getText();
        String userTelephone = telephone.getText();
        String userAdresse = address.getText();

        if (userEmail.isEmpty() || userMotDePasse.isEmpty() || userAdresse.isEmpty() || userFamilyName.isEmpty() || userFirstName.isEmpty() || userTelephone.isEmpty()) {
            erreurText.setText("Veuillez remplir tous les champs.");
            return;
        }

        if (!isValidEmail(userEmail)) {
            erreurText.setText("Format d'e-mail invalide.");
            return;
        }

        if (!isValidPassword(userMotDePasse)) {
            erreurText.setText("Le mot de passe doit contenir au moins 8 caractères.");
            return;
        }

        if (HelloApplication.oms.isExist(userEmail, userMotDePasse)) {
            inscriptionInvalide();
        } else {
            Orthophoniste newUser = new Orthophoniste(userEmail, userMotDePasse);
            newUser.setPrenom(userFirstName);
            newUser.setNom(userFamilyName);
            newUser.setNumTel(userTelephone);
            newUser.setAdresse(userAdresse);

            HelloApplication.oms.getOrthophonistes().put(newUser, userMotDePasse);
            HelloApplication.utilisateurCourant = newUser;
            HelloApplication.oms.sauvegarder();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                erreurText.setText("Erreur de chargement de la page de connexion.");
            }
        }
    }

    private void inscriptionInvalide() {
        erreurText.setText("E-mail déjà utilisé !");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }
}
