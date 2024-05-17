package com;

import com.models.QuestionEnfant.CategorieEnfant;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionItemController implements Initializable {
    @FXML
    private ComboBox<String> categoryComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryComboBox.getItems().addAll(
            CategorieEnfant.STRUCTURE_FAMILIALE.name(),
            CategorieEnfant.DYNAMIQUE_FAMILIALE.name(),
            CategorieEnfant.ANTECEDENTS_FAMILIAUX.name(),
            CategorieEnfant.CONDITIONS_NATALES.name(),
            CategorieEnfant.DEVELOPPEMENT_PSYCHOMOTEUR.name(),
            CategorieEnfant.DEVELOPPEMENT_LANGAGIER.name(),
            CategorieEnfant.CARACTERE_ET_COMPORTEMENT.name()
        );
    }
}
