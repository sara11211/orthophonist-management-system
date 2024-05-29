package com;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.Node;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import com.models.Proposition;
import com.models.QCM;
import com.models.QCU;
import com.models.Question;
import com.models.QuestionLibre;

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
    private List<CheckBox> checkBoxes;

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
        checkBoxes = new ArrayList<>();
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
        propositionsContainer.getChildren().clear();
        if (selectedType != null) {
            if (selectedType.equals("Free Text")) {
                TextField answerField = new TextField();
                answerField.setPromptText("Answer");
                propositionsContainer.getChildren().add(answerField);
                addPropositionButton.setVisible(false);
            } else {
                addPropositionButton.setVisible(true);
            }
        } else {
            addPropositionButton.setVisible(false);
        }
    }

    @FXML
    private void handleAddProposition() {
        String selectedType = questionTypeComboBox.getValue();
        if (selectedType.equals("Multiple Choice (Multiple Answers)")) {
            CheckBox propositionCheckBox = new CheckBox();
            propositionsContainer.getChildren().add(propositionCheckBox);
            checkBoxes.add(propositionCheckBox);

            TextField propositionField = new TextField();
            propositionField.setPromptText("Proposition");
            propositionsContainer.getChildren().add(propositionField);
        } else if (selectedType.equals("Multiple Choice (Single Answer)")) {
            RadioButton propositionRadioButton = new RadioButton();
            propositionRadioButton.setToggleGroup(toggleGroup);
            propositionsContainer.getChildren().add(propositionRadioButton);
            radioButtons.add(propositionRadioButton);

            TextField propositionField = new TextField();
            propositionField.setPromptText("Proposition");
            propositionsContainer.getChildren().add(propositionField);
        }
    }

    public Question getQuestion() {
        String questionText = questionTextField.getText();
        String questionType = questionTypeComboBox.getValue();
        int score = scoreSpinner.getValue();
        
        if (questionText.isEmpty() || questionType == null) {
            return null;
        }

        if (questionType.equals("Free Text")) {
            return new QuestionLibre(questionText, score);
        } else {
            HashSet<Proposition> propositions = new HashSet<>();
            for (Node node : propositionsContainer.getChildren()) {
                if (node instanceof TextField) {
                    TextField propositionField = (TextField) node;
                    String propositionText = propositionField.getText();
                    if (!propositionText.isEmpty()) {
                        propositions.add(new Proposition(propositionText));
                    }
                }
            }

            if (questionType.equals("Multiple Choice (Multiple Answers)")) {
                return new QCM(questionText, score, propositions.toArray(new Proposition[0]));
            } else if (questionType.equals("Multiple Choice (Single Answer)")) {
                return new QCU(questionText, score, propositions.toArray(new Proposition[0]));
            }
        }
        return null;
    }

    public VBox getPropositionsContainer() {
        return propositionsContainer;
    }

    public List<RadioButton> getRadioButtons() {
        return radioButtons;
    }

    public List<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }
}
