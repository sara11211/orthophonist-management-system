package com;

import com.models.Anamnese;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AnamnesesDisplayController {

    @FXML
    private TableView<Anamnese> anamnesesTable;
    @FXML
    private TableColumn<Anamnese, String> nameColumn;
    @FXML
    private TableColumn<Anamnese, String> descriptionColumn;

    private ObservableList<Anamnese> anamneses = FXCollections.observableArrayList();

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        anamnesesTable.setItems(anamneses);
    }

    public void setAnamneses(List<Anamnese> anamneses) {
        if (anamneses != null) {
            this.anamneses.setAll(anamneses);
        } else {
            this.anamneses.clear(); 
        }
    }
}
