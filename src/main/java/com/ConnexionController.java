package com;

import com.models.UtilisateurNonTrouveException;
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

import static com.HelloApplication.oms;
import static com.HelloApplication.utilisateurCourant;

public class ConnexionController {
    
        @FXML
        private Label erreurText;
        @FXML
        private TextField adresseEmail;
        @FXML
        private PasswordField motDePasse;

        private Stage stage;
        private Scene scene;
        private Parent root;

        @FXML 
        void signupButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signup.fxml"));
        scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

        Button button = (Button) event.getSource();
        stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        }
    
        @FXML
        void seConnecterButton(ActionEvent event) throws UtilisateurNonTrouveException, IOException {

            String userEmail = adresseEmail.getText();
            String userMdp = motDePasse.getText();

            if (oms.isExist(userEmail, userMdp))
            {
                Orthophoniste user = oms.findUser(userEmail,userMdp);
                if (user != null)
                {
                    System.out.println("Connection valide");
                    utilisateurCourant = oms.findUser(userEmail,userMdp);
                }
                else
                {
                    System.out.println("Mot de passe incorrect");
                    connexionInvalid();
                }
            }
            else {
                System.out.println("La fonction seConnecterButton n'a pas trouvé l'utilisateur!");
                connexionInvalid();
            }
        }

        public void connexionInvalid() {
            erreurText.setText("Invalid e-mail or password ! ");
        }
    }


