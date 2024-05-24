package com;

import com.models.Exercice;
import com.models.Question;
import com.models.QuestionAdulte;
import com.models.QuestionEnfant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExercicesViewController {

    @FXML
    private TableView<Exercice> exercicesTable;
    @FXML
    private TableColumn<Exercice, String> consigneTextColumn;
    @FXML
    private TableColumn<Exercice, String> materielTextColumn;

    @FXML
    private TableColumn<Exercice, String> scoreTextColumn;

    private ObservableList<Exercice> exercices = FXCollections.observableArrayList();

    public void initialize() {
        consigneTextColumn.setCellValueFactory(new PropertyValueFactory<>("consigne"));
        materielTextColumn.setCellValueFactory(new PropertyValueFactory<>("materiel"));
        scoreTextColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        exercicesTable.setItems(exercices);
    }

    public void setExercices(ObservableList<Exercice> questions) {
        this.exercices.setAll(questions);
    }
}