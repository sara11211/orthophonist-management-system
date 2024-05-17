package com;

import com.models.QuestionEnfant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class QuestionsViewController {

    @FXML
    private TableView<QuestionEnfant> questionsTable;
    @FXML
    private TableColumn<QuestionEnfant, String> questionColumn;
    @FXML
    private TableColumn<QuestionEnfant, QuestionEnfant.CategorieEnfant> categoryColumn;

    private ObservableList<QuestionEnfant> questions = FXCollections.observableArrayList();

    public void initialize() {
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("enonce"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        questionsTable.setItems(questions);
    }

    public void setQuestions(ObservableList<QuestionEnfant> questions) {
        this.questions.setAll(questions);
    }
}
