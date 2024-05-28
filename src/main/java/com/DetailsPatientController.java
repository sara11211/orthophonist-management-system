package com;
import com.models.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import java.time.LocalTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

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
    @FXML
    private TableView<BO> bosTable;
    @FXML
    private TableColumn<BO, LocalDate> dateBOColumn;
    @FXML
    private TableColumn<BO, Void> viewBOColumn;

    @FXML
    private DatePicker dateRDVField;

    @FXML
    private Button consultationButton;

    @FXML
    private Button suiviButton;

    @FXML
    private Button atelierButton;


    @FXML
    private TextField nomObjectif;


    @FXML
    private ToggleButton courtTerme;

    @FXML
    private ToggleButton moyenTerme;

    @FXML
    private ToggleButton longTerme;


    @FXML
    private static Patient patient;

    private boolean calledForPatient;

    private ToggleGroup optionsGroup;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private Map<String, Integer> objectifsScore = new HashMap<>();

    private static boolean RDVAdded = false; // Flag to indicate if RDV was added

    // Method to check if RDV was added
    public static boolean isRDVAdded() {
        return RDVAdded;
    }

    public void setCalledForPatient(boolean calledForPatient) {
        this.calledForPatient = calledForPatient;
        if (calledForPatient) {
            optionsGroup = new ToggleGroup();
            System.out.println("CALLED FOR PATIENT !!!");
            courtTerme.setToggleGroup(optionsGroup);
            moyenTerme.setToggleGroup(optionsGroup);
            longTerme.setToggleGroup(optionsGroup);
        }
    }

    @FXML
    private void handlePlusClick() {
        if (dateRDVField.getValue() != null) {
            consultationButton.setVisible(true);
            suiviButton.setVisible(true);
            atelierButton.setVisible(true);
        }
    }
    public void setPatient(Patient patient) { this.patient = patient;
    displayPatient();}

    @FXML
    public void initialize() {
        // Initialize the ToggleGroup and set it to the RadioButtons

    }
    public void displayPatient() {
        if (patient != null) {
            firstNameLabel.setText(patient.getPrenom());
            lastNameLabel.setText(patient.getNom());
            birthdayLabel.setText(patient.getDateNaissance().toString());
            placeOfBirthLabel.setText(patient.getLieuNaissance());
            addressLabel.setText(patient.getAdresse());

            ObservableList<RDV> rdvList = FXCollections.observableArrayList(patient.getRdvs());
            ObservableList<BO> BOList = FXCollections.observableArrayList(patient.getBos());
            rdvTable.setItems(rdvList);
            bosTable.setItems(BOList);
            fetchData();

            populateChart();

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

            dateBOColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BO, LocalDate>, ObservableValue<LocalDate>>() {
                @Override
                public ObservableValue<LocalDate> call(TableColumn.CellDataFeatures<BO, LocalDate> param) {
                    BO bo = param.getValue();
                    if (bo != null) {
                        return new SimpleObjectProperty<>(bo.getDateBO());
                    } else {
                        return new SimpleObjectProperty<>(null);
                    }
                }
            });


            viewBOColumn.setCellFactory(new Callback<>() {
                @Override
                public TableCell<BO, Void> call(TableColumn<BO, Void> param) {
                    return new TableCell<>() {
                        private final Button voirBOSButton = new Button("Voir");

                        {
                            voirBOSButton.setOnAction(event -> {
                                BO bo = getTableView().getItems().get(getIndex());
                                showBO(bo);
                            });
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(voirBOSButton);
                            }
                        }
                    };
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

    @FXML
    void handleAddBO(ActionEvent event) {
        try {
            // Load the FXML file for the popup
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutBO.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Ajouter un BO");
            AjoutBOController.setPatient(patient);



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
        }    }


    @FXML
    void handleAddRDV(ActionEvent event) {
        try {
            // Load the FXML file for the popup
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutRDV.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Détails de la consultation");


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


    @FXML
    public void handleConsultationClick() {
        try {
            // Load the FXML file for the popup
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConsultationForm.fxml"));
            Parent root = fxmlLoader.load();

            ConsultationController consultationController = fxmlLoader.getController();
            System.out.println("TEST PATIENT : "+patient.getNom());
            consultationController.setPatient(patient);

            consultationController.setSelectedDay(dateRDVField.getValue());  // Pass selected_day to the ConsultationController

            //consultationController.setCalendarController(this);
            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Détails de la consultation");

            // Set the scene with the loaded FXML
            Scene scene = new Scene(root);
            popupStage.setScene(scene);

            // Make the popup modal
            popupStage.initModality(Modality.APPLICATION_MODAL);

            // Show the popup
            popupStage.showAndWait();
            if (consultationController.getSubmitSuccess()) {
                System.out.println("INSIIIIIDEDDDDDDDDDDDDDD");
                RDV newRDV = consultationController.getNewConsultation();
                patient.getRdvs().add(newRDV);

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to create new Window: " + e.getMessage());
        }
    }

    @FXML
    public void handleSuiviClick() {
        try {
            System.out.println("Seance de suivi Clicked");
            // Load the FXML file for the popup
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RDVSuiviForm.fxml"));
            Parent root = fxmlLoader.load();

            RDVSuiviController rdvSuiviController = fxmlLoader.getController();
            rdvSuiviController.setSelectedDay(dateRDVField.getValue());
            //rdvSuiviController.setCalendarController(this);

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Détails de la séance de suivi");

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
    @FXML
    public void handleAtelierClick() {
        // Code to navigate to Atelier page
        try {
            System.out.println("Atelier Clicked");
            // Load the FXML file for the popup
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AtelierForm.fxml"));
            Parent root = fxmlLoader.load();

            AtelierController atelierController = fxmlLoader.getController();
            atelierController.setSelectedDay(dateRDVField.getValue());
            //atelierController.setCalendarController(this);

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Détails de l'atelier");

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


    private void showBO(BO bo) {
        try {
            // Load the FXML file for the popup
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayBO.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Bilan orthophoniques");
            DetailsBO controller = fxmlLoader.getController();
            if (bo != null) controller.setBo(bo);
            else System.out.println("BOOO NULLLL IN SHOWBO");


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
    }

    @FXML
    void handleSubmitObjectif(ActionEvent event) {
        System.out.println("ADD OBJECTIF !");
        String selectedOption = "";
        System.out.println("handleSubmitObjectif method called.");
        System.out.println("optionsGroup is null: " + (optionsGroup == null));

        ToggleButton selectedRadioButton = (ToggleButton) optionsGroup.getSelectedToggle();

        if (selectedRadioButton != null) {
            selectedOption = selectedRadioButton.getText();
            if(nomObjectif.getText() != null) {
                Objectif objectif = new Objectif(nomObjectif.getText(), selectedOption, false);
                Map<Objectif, Integer> objectifScore = new HashMap<>();
                objectifScore.put(objectif, 0);
                if (patient.getFicheSuivi() == null) {
                    HashMap<Objectif, Integer> objscore = new HashMap<>();
                    objscore.put(objectif, 0);
                    FicheSuivi ficheSuivi = new FicheSuivi(objscore);
                    patient.setFicheSuivi(ficheSuivi);
                    System.out.println("UPDATED FICHE SUIVI");
                    for (Objectif obj : patient.getFicheSuivi().getObjectifScores().keySet()) {
                        System.out.println(obj.getNomObjectif());

                    }
                }
                else {
                    System.out.println("UP");
                    patient.getFicheSuivi().getObjectifScores().put(objectif, 0);
                }
            }
        }


    }

    private void fetchData() {
        // Replace this code with actual data fetching logic
        // Example data fetching logic:for
        for (Objectif objectif : patient.getFicheSuivi().getObjectifScores().keySet()) {
            objectifsScore.put(objectif.getNomObjectif(), patient.getFicheSuivi().getObjectifScores().get(objectif));
        }
    }

    private void populateChart() {
        // Sort the data by value in ascending order
        Map<String, Integer> sortedData = objectifsScore.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        // Create a series and add the sorted data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : sortedData.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Add the series to the chart
        lineChart.getData().add(series);
    }

}