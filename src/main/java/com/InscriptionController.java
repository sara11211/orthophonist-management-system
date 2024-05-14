package com;

import com.models.OMS;
import com.models.Orthophoniste;
import javafx.css.PseudoClass;
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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;


public class InscriptionController {

    @FXML
    private Label erreurText;
    @FXML
    private TextField email;
    @FXML
    private PasswordField motDePasse;

    @FXML
    void seConnecterButton(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = null;
        try
        {
            scene = new Scene(fxmlLoader.load());
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

        Button button = (Button)event.getSource();
        Stage stage = (Stage)button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void inscriptionButton(ActionEvent event) {

        String userEmail = email.getText();
        String userMotDePasse = motDePasse.getText();

        if (HelloApplication.oms.isExist(userEmail, userMotDePasse))
        {
            System.out.println("Pseudo existe deja!");
            inscriptionInvalide();
        }
        else
        {
            HelloApplication.utilisateurCourant = new Orthophoniste(userEmail,userMotDePasse);
            HelloApplication.oms.getOrthophonistes().put(HelloApplication.utilisateurCourant,userMotDePasse);
            System.out.println("ajouter user : "+userEmail+"  mdp : "+userMotDePasse);
        }
    }

    @FXML
    void inscriptionInvalide()
    {
        erreurText.setText("E-mail already used by another account !");
    }
}

