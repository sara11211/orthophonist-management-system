package com;

import com.models.Patient;
import com.models.RDV;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
public class DetailsPatientController {

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label placeOfBirthLabel;
    @FXML
    private Label addressLabel;

    @FXML
    private TableView<RDV> rdvTable;
    @FXML
    private TableColumn<RDV, LocalDate> dateColumn;
    @FXML
    private TableColumn<RDV, LocalTime> hourColumn;
    @FXML
    private TableColumn<RDV, String> typeColumn;
    @FXML
    private TableColumn<RDV, String> additionalInfoColumn;
    @FXML
    private TableColumn<RDV, String> observationColumn;
    @FXML
    private TableColumn<RDV, Void> actionColumn;
    private Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void displayPatient(Patient patient) {
        if (patient != null) {
            firstNameLabel.setText(patient.getPrenom());
            lastNameLabel.setText(patient.getNom());
            birthdayLabel.setText(patient.getDateNaissance().toString());
            placeOfBirthLabel.setText(patient.getLieuNaissance());
            addressLabel.setText(patient.getAdresse());

            ObservableList<RDV> rdvList = FXCollections.observableArrayList(patient.getRdvs());
            rdvTable.setItems(rdvList);

            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

         /*   // Custom cell value factories for other columns
            hourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RDV, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<RDV, String> param) {
                    RDV rdv = param.getValue();
                    if (rdv != null) {
                        String heure = rdv.getHeureDebut().toString();
                        return new SimpleStringProperty(heure);
                    } else {
                        return new SimpleStringProperty("");
                    }
                }
            });
*/
            typeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RDV, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<RDV, String> param) {
                    RDV rdv = param.getValue();
                    if (rdv != null) {
                        return new SimpleStringProperty(rdv.getClass().getSimpleName());
                    } else {
                        return new SimpleStringProperty("");
                    }
                }
            });

            additionalInfoColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RDV, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<RDV, String> param) {
                    RDV rdv = param.getValue();
                    if (rdv != null) {
                        return new SimpleStringProperty(rdv.getInfoSup());
                    } else {
                        return new SimpleStringProperty("");
                    }
                }
            });

            observationColumn.setCellValueFactory(new PropertyValueFactory<>("observation"));

            // Set custom cell factory for action buttons
            actionColumn.setCellFactory(new Callback<>() {
                @Override
                public TableCell<RDV, Void> call(final TableColumn<RDV, Void> param) {
                    return new TableCell<>() {
                        private final Button viewButton = new Button("View Observations");
                        private final Button addButton = new Button("+");

                        {
                            viewButton.setOnAction(event -> {
                                RDV rdv = getTableView().getItems().get(getIndex());
                                showObservations(rdv);
                            });
                            addButton.setOnAction(event -> {
                                RDV rdv = getTableView().getItems().get(getIndex());
                                addObservation(rdv);
                            });
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                HBox buttons = new HBox(viewButton, addButton);
                                buttons.setSpacing(5);
                                setGraphic(buttons);
                            }
                        }
                    };
                }
            });
        }
    }

    private void showObservations(RDV rdv) {
        System.out.println("SHOW OBSERVATION !");
        // Implement method to display observations
    }

    private void addObservation(RDV rdv) {
        System.out.println("ADD OBSERVATION !");
        // Implement method to add a new observation
    }
}
