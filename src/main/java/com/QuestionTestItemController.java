package com;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import com.models.Question;

public class QuestionTestItemController implements Initializable {

    @FXML
    private TextField questionTextField;
    @FXML
    private ComboBox<String> questionTypeComboBox;
    @FXML
    private VBox propositionsContainer;
    @FXML
    private Button addPropositionButton;

    private Spinner<Integer> scoreSpinner;
    private ToggleGroup toggleGroup;
    private List<RadioButton> radioButtons;

    private TestCreationController parentController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionTypeComboBox.getItems().addAll(
            "Multiple Choice (Multiple Answers)",
            "Multiple Choice (Single Answer)",
            "Free Text"
        );

        // Initialize Spinner programmatically
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        scoreSpinner = new Spinner<>();
        scoreSpinner.setEditable(true);
        scoreSpinner.setValueFactory(valueFactory);

        // Add Spinner to the layout
        HBox hbox = new HBox();
        hbox.getChildren().addAll(scoreSpinner);
        ((HBox) questionTextField.getParent()).getChildren().add(hbox);

        toggleGroup = new ToggleGroup();
        radioButtons = new ArrayList<>();
    }

    public void setParentController(TestCreationController parentController) {
        this.parentController = parentController;
    }

    public void setQuestion(Question question) {
        questionTextField.setText(question.getEnonce());
        scoreSpinner.getValueFactory().setValue(question.getScore());
    }

    @FXML
    private void handleQuestionTypeChange() {
        String selectedType = questionTypeComboBox.getValue();
        if (selectedType != null && (selectedType.contains("Multiple Choice") || selectedType.equals("Free Text"))) {
            propositionsContainer.getChildren().clear();
            if (selectedType.equals("Free Text")) {
                TextField answerField = new TextField();
                answerField.setPromptText("Answer");
                propositionsContainer.getChildren().add(answerField);
            } else {
                addPropositionButton.setVisible(true);
            }
        } else {
            addPropositionButton.setVisible(false);
            propositionsContainer.getChildren().clear();
        }
    }

    @FXML
    private void handleAddProposition() {
        RadioButton propositionRadioButton = new RadioButton();
        propositionRadioButton.setToggleGroup(toggleGroup);
        propositionsContainer.getChildren().add(propositionRadioButton);
        radioButtons.add(propositionRadioButton);

        TextField propositionField = new TextField();
        propositionField.setPromptText("Proposition");
        propositionsContainer.getChildren().add(propositionField);
    }

    public VBox getPropositionsContainer() {
        return propositionsContainer;
    }

    public List<RadioButton> getRadioButtons() {
        return radioButtons;
    }
}