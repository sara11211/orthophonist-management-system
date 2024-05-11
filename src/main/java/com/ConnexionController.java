package com;

import com.models.OMS;
import com.models.UtilisateurNonTrouveException;
import com.models.Orthophoniste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

        private Stage stage;
        private Scene scene;
        private Parent root;
    
        @FXML
        private Label erreurText;
        @FXML
        private TextField email;
        @FXML
        private PasswordField motDePasse;
    
        // @FXML
        // void nvCompteButton(ActionEvent event) {
        //     FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Inscription.fxml"));
        //     scene = null;
        //     try {
        //         scene = new Scene(fxmlLoader.load());
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //         System.out.println("Couldn't load FXML file");
        //     }
    
        //     Button button = (Button) event.getSource();
        //     stage = (Stage) button.getScene().getWindow();
        //     stage.setScene(scene);
        //     stage.show();
        // }
    
        @FXML
        void seConnecterButton(ActionEvent event) throws UtilisateurNonTrouveException, IOException {
            String userEmail = email.getText();
            String userMdp = motDePasse.getText();
            if (oms.isExist(userEmail))
            {
                Orthophoniste user = oms.findUser(userEmail,userMdp);
                if (user != null)
                {
                    System.out.println("Connection valide");
                    utilisateurCourant = oms.findUser(userEmail,userMdp);
    
                    // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Calendar.fxml"));
                    // scene = null;
                    // try {
                    //     scene = new Scene(fxmlLoader.load());
                    // } catch (IOException e) {
                    //     e.printStackTrace();
                    //     System.out.println("Couldn't load FXML file");
                    // }
    
                    // Button button = (Button) event.getSource();
                    // stage = (Stage) button.getScene().getWindow();
                    // stage.setScene(scene);
                    // stage.show();
                }
                else
                {
                    System.out.println("mdp incorrect");
                    connexionInvalid();
                }
            }
            else {
                System.out.println("seConnecterButton func n'a pas trouvé l'utilisateur!");
                connexionInvalid();
            }
        }
    
        public void connexionInvalid() {
            erreurText.setText("Pseudo ou mot de passe invalide!");
        }
    }

