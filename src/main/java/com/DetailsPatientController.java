package com;

import com.models.Consultation;
import com.models.Patient;
import com.models.RDV;
import com.models.RDVSuivi;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import com.models.Atelier;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import static com.HelloApplication.utilisateurCourant;
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
    private TableColumn<RDV, Void> additionalInfoColumn;
    @FXML
    private TableColumn<RDV, Void> observationColumn;

    private Patient patient;


    public void displayPatient(Patient patient) {
        if (patient != null) {
            firstNameLabel.setText(patient.getPrenom());
            lastNameLabel.setText(patient.getNom());
            birthdayLabel.setText(patient.getDateNaissance().toString());
            placeOfBirthLabel.setText(patient.getLieuNaissance());
            addressLabel.setText(patient.getAdresse());

            ObservableList<RDV> rdvList = FXCollections.observableArrayList(patient.getRdvs());
            rdvTable.setItems(rdvList);

            // Ensure the RDV class has a getDate() method returning a LocalDate
            dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RDV, LocalDate>, ObservableValue<LocalDate>>() {
                @Override
                public ObservableValue<LocalDate> call(TableColumn.CellDataFeatures<RDV, LocalDate> param) {
                    RDV rdv = param.getValue();
                    if (rdv != null) {
                        return new SimpleObjectProperty<>(rdv.getDate());
                    } else {
                        return new SimpleObjectProperty<>(null);
                    }
                }
            });


        hourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RDV, LocalTime>, ObservableValue<LocalTime>>() {
            @Override
            public ObservableValue<LocalTime> call(TableColumn.CellDataFeatures<RDV, LocalTime> param) {
                RDV rdv = param.getValue();
                if (rdv != null) {
                    return new SimpleObjectProperty<>(rdv.getHeureDebut());
                } else {
                    return new SimpleObjectProperty<>(null);
                }
            }
        });


        typeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RDV, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RDV, String> param) {
                 RDV rdv = param.getValue();
                 if (rdv != null) {
                     if (rdv instanceof RDVSuivi) return new SimpleStringProperty("Séance de suivi");
                     if (rdv instanceof Consultation) return new SimpleStringProperty("Consultation");
                     else return new SimpleStringProperty("Atelier");
                 } else {
                     return new SimpleStringProperty("");
                 }
             }
            });
            additionalInfoColumn.setCellFactory(new Callback<>() {
                @Override
                public TableCell<RDV, Void> call(TableColumn<RDV, Void> param) {
                    return new TableCell<>() {
                        private final Button infoButton = new Button("Info");

                        {
                            infoButton.setOnAction(event -> {
                                RDV rdv = getTableView().getItems().get(getIndex());
                                showAdditionalInfoPopup(rdv);
                            });
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(infoButton);
                            }
                        }
                    };
                }
            });


            observationColumn.setCellFactory(new Callback<>() {
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
        try {
            // Load the FXML file for the popup
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Observations.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Observations");
            ObservationsController controller = fxmlLoader.getController();
            controller.setObservations(getObservations(rdv));


            // Set the scene with the loaded FXML
            Scene scene = new Scene(root);
            popupStage.setScene(scene);

            // Make the popup modal
            popupStage.initModality(Modality.APPLICATION_MODAL);

            // Show the popup
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to create new Window: " + e.getMessage());
        }

    }



    private void showAdditionalInfoPopup(RDV rdv) {
        try {
            // Load the FXML file for the popup
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InfoSups.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Observations");
            InfoSupsController controller = fxmlLoader.getController();
            controller.setInfoSupField(rdv);


            // Set the scene with the loaded FXML
            Scene scene = new Scene(root);
            popupStage.setScene(scene);

            // Make the popup modal
            popupStage.initModality(Modality.APPLICATION_MODAL);

            // Show the popup
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to create new Window: " + e.getMessage());
        }

    }
    private String getObservations(RDV rdv) {
        String res = "";
        for (String observation : rdv.getObservations()) {
            res = res + observation;
            res = res + "                    ---                   "      ;
        }
       return res;
    }
    private void addObservation(RDV rdv) {
        System.out.println("ADD OBSERVATION !");
        try {
            // Load the FXML file for the popup
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddObservation.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Observations");
            AddObservationController controller = fxmlLoader.getController();
            controller.setRDV(rdv);


            // Set the scene with the loaded FXML
            Scene scene = new Scene(root);
            popupStage.setScene(scene);

            // Make the popup modal
            popupStage.initModality(Modality.APPLICATION_MODAL);

            // Show the popup
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to create new Window: " + e.getMessage());
        }
        // Implement method to add a new observation
    }
}
