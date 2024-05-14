package com;

import com.models.UtilisateurNonTrouveException;
import com.models.Orthophoniste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
                }
            }
            else {
                System.out.println("La fonction seConnecterButton n'a pas trouvé l'utilisateur!");
            }
        }
    }

