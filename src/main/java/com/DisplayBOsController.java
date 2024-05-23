package com;

import com.models.Anamnese;
import com.models.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DisplayBOsController {

    @FXML
    private TableView<Test> testsTable;

    @FXML
    private TableColumn<Test, String> testNameColumn;

    @FXML
    private TableColumn<Test, String> testDescriptionColumn;

    @FXML
    private TableView<Anamnese> anamnesesTable;
    @FXML
    private TableColumn<Anamnese, String> nameColumn;
    @FXML
    private TableColumn<Anamnese, String> descriptionColumn;
    @FXML
    private TableColumn<Anamnese, Void> actionColumn;

    private ObservableList<Test> tests;
    private ObservableList<Anamnese> anamneses = FXCollections.observableArrayList();


}
