package com;
import com.models.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import com.models.Objectif;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.attribute.FileAttribute;
import java.util.HashMap;

public class AddObjectifController {
    @FXML
    private TableView<Objectif> tableView;

    @FXML
    private TableColumn<Objectif, String> nomObjectifColumn;

    @FXML
    private TableColumn<Objectif, String> typeObjectifColumn;



    private HashMap<Objectif, Integer> objectifScores;

    @FXML
    private TableColumn<Objectif, Void> addActionColumn;
    private Patient patient;

    private RDVSuivi rdvSuivi;

    public void setPatient(Patient patient) {
        this.patient = patient;
        if (patient.getFicheSuivi().getObjectifScores() != null) {
            objectifScores = patient.getFicheSuivi().getObjectifScores();
            updateUI();

        } else {
            tableView.setItems(FXCollections.emptyObservableList());
        }
    }

    public void setRdvSuivi(RDVSuivi rdvSuivi) {
        this.rdvSuivi = rdvSuivi;
    }
    private void updateUI() {
        nomObjectifColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNomObjectif()));
        typeObjectifColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTypeObjectif()));

        if (objectifScores != null) {
            System.out.println("INSIDE SETTING UP TABLEVIEWWW");
            tableView.setItems(getObjectifData());

            addActionColumn.setCellFactory(param -> new TableCell<>() {
                private final Spinner<Integer> spinner = new Spinner<>(0, 5, 0);
                private final Button addButton = new Button("+");

                {
                    addButton.setOnAction(event -> {
                        Objectif objectif = getTableView().getItems().get(getIndex());
                        int score = spinner.getValue();
                        System.out.println("SCORE : " + score);
                        handleAddObjectif(objectif, score);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        setGraphic(new HBox(spinner, addButton));
                    }
                }
            });
        }
    }


    @FXML
    public void initialize() {
        // Set up the columns





    }

    private ObservableList<Objectif> getObjectifData() {
        ObservableList<Objectif> data = FXCollections.observableArrayList();
        System.out.println("inside get object data");
        for (Objectif objectif : objectifScores.keySet()) {
            System.out.println(objectif.getNomObjectif());
            data.add(objectif);
        }
        return data;
    }
    private void handleAddObjectif(Objectif objectif, int score) {
        if (objectif != null) {
            if (rdvSuivi.getFicheSuivi() == null) {
                HashMap<Objectif, Integer> mapObj = new HashMap<>();
                mapObj.put(objectif, score);
                FicheSuivi ficheSuivi = new FicheSuivi(mapObj);
                rdvSuivi.setFicheSuivi(ficheSuivi);
                if (patient.getFicheSuivi().getObjectifScores().containsKey(objectif)) {
                    Integer currentValue = objectifScores.getOrDefault(objectif, 0);
                    int newValue = (currentValue + score) / 2;
                    patient.getFicheSuivi().getObjectifScores().put(objectif, newValue);
                }
            } else {
                rdvSuivi.getFicheSuivi().getObjectifScores().put(objectif, score);
                if (patient.getFicheSuivi().getObjectifScores().containsKey(objectif)) {
                    Integer currentValue = objectifScores.getOrDefault(objectif, 0);
                    int newValue = (currentValue + score) / 2;
                    patient.getFicheSuivi().getObjectifScores().put(objectif, newValue);
                }
            }
        }
    }

}