package com;

import com.models.Question;
import com.models.QuestionAdulte;
import com.models.QuestionEnfant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class QuestionsViewController {

    @FXML
    private TableView<Question> questionsTable;
    @FXML
    private TableColumn<Question, String> questionTextColumn;
    @FXML
    private TableColumn<Question, String> categoryColumn;

    private ObservableList<Question> questions = FXCollections.observableArrayList();

    public void initialize() {
        questionTextColumn.setCellValueFactory(new PropertyValueFactory<>("enonce"));
        categoryColumn.setCellValueFactory(param -> {
            if (param.getValue() instanceof QuestionEnfant) {
                return ((QuestionEnfant) param.getValue()).categorieProperty();
            } else if (param.getValue() instanceof QuestionAdulte) {
                return ((QuestionAdulte) param.getValue()).categorieProperty();
            }
            return null;
        });

        questionsTable.setItems(questions);
    }

    public void setQuestions(ObservableList<Question> questions) {
        this.questions.setAll(questions);
    }
}
