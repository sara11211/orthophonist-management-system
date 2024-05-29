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

import static com.HelloApplication.utilisateurCourant;
import static com.HelloApplication.oms;

public class UpdateUserInfoController {

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
    private void initialize() {
        Orthophoniste currentUser = utilisateurCourant;
        if (currentUser != null) {
            email.setText(currentUser.getAdresseEmail());
            motDePasse.setText(currentUser.getMotDePasse());
            firstName.setText(currentUser.getPrenom());
            familyName.setText(currentUser.getNom());
            telephone.setText(currentUser.getNumTel());
            address.setText(currentUser.getAdresse());
        }
    }

    @FXML
    void saveChanges(ActionEvent event) throws IOException {
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

        // Update OMS with new details
        Orthophoniste updatedUser = utilisateurCourant;
        String oldEmail = updatedUser.getAdresseEmail();

        updatedUser.setAdresseEmail(userEmail);
        updatedUser.setMotDePasse(userMotDePasse);
        updatedUser.setNom(userFamilyName);
        updatedUser.setPrenom(userFirstName);
        updatedUser.setNumTel(userTelephone);
        updatedUser.setAdresse(userAdresse);

        // Remove old entry if email changed
        if (!oldEmail.equals(userEmail)) {
            oms.getOrthophonistes().remove(new Orthophoniste(oldEmail, utilisateurCourant.getMotDePasse()));
        }
        oms.getOrthophonistes().put(updatedUser, userMotDePasse);
        utilisateurCourant = updatedUser;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("first_page.fxml"));
        Parent root = fxmlLoader.load();
        FirstPageController controller = fxmlLoader.getController();
        controller.setUserName(utilisateurCourant.getPrenom() + " " + utilisateurCourant.getNom());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }
}
