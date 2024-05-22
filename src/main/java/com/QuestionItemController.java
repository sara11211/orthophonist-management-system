package com;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class QuestionItemController {

    @FXML
    private TextField questionTextField;

    @FXML
    private ComboBox<String> categoryComboBox;

    public void setCategories(Enum<?>[] categories) {
        for (Enum<?> category : categories) {
            categoryComboBox.getItems().add(category.name());
        }
    }

    public void setQuestionText(String text) {
        questionTextField.setText(text);
    }

    public void setSelectedCategory(String category) {
        categoryComboBox.setValue(category);
    }
}