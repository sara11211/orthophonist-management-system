package com;
import com.models.*;
import com.models.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Parent;
import java.time.Period;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;

import static com.HelloApplication.oms;
import static com.HelloApplication.utilisateurCourant;
import javafx.stage.Modality;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.Callback;

import java.time.LocalTime;
import java.time.Duration;
public class AjoutBOController {
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

    @FXML
    private TextArea projetTherap;


    @FXML
    private TextField troubleNom;

    @FXML
    private ComboBox<String> categorieTrouble;

    @FXML
    private ListView<String> listTroubles;

    private ObservableList<Test> tests;
    private ObservableList<Anamnese> anamneses = FXCollections.observableArrayList();

    public static Patient patient;

    private BOPremier bo;
    private HashSet<Trouble> troubles;

    @FXML
    public void initialize() {
        testNameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        testDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        anamnesesTable.setItems(anamneses);
        loadAnamneses();
        addButtonToTable();
        loadTests();
        addButtonsToTable();
        bo = new BOPremier();
    }

    private void loadAnamneses() {
        if (utilisateurCourant != null && utilisateurCourant.getAnamneses() != null) {
            anamneses = FXCollections.observableArrayList(utilisateurCourant.getAnamneses());
        } else {
            System.out.println("NO ANAMNESES FOR USER");
            anamneses = FXCollections.observableArrayList();
        }
        anamnesesTable.setItems(anamneses);
    }


    private void loadTests() {
        if (utilisateurCourant != null && utilisateurCourant.getTests() != null) {
            tests = FXCollections.observableArrayList(utilisateurCourant.getTests());
        } else {
            tests = FXCollections.observableArrayList();
        }
        testsTable.setItems(tests);
    }

    private void addButtonsToTable() {
        // View Button
        TableColumn<Test, Void> viewCol = new TableColumn<>("View");

        viewCol.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("View");

            {
                btn.setOnAction(event -> {
                    Test data = getTableView().getItems().get(getIndex());
                    handleViewTest(data);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });

        testsTable.getColumns().add(viewCol);

        // Delete Button
        TableColumn<Test, Void> addTest = new TableColumn<>("Ajouter");

        addTest.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("+");

            {
                btn.setOnAction(event -> {
                    Test data = getTableView().getItems().get(getIndex());
                    handleAddTest(data);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });

        testsTable.getColumns().add(addTest);





    }

    private void handleViewTest(Test selectedTest) {
        if (selectedTest != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/test_details.fxml"));
                Parent root = loader.load();

                TestDetailController controller = loader.getController();
                controller.setTest((TestQuestionnaire) selectedTest);

                Stage stage = new Stage();
                stage.setTitle("Test Details");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleAddTest(Test test) {
        if (bo.getEpreuvesCliniques() != null) {
            bo.getEpreuvesCliniques().getTests().add(test);
        }
        else {
            EpreuveClinique epreuves = new EpreuveClinique("");
            bo.setEpreuvesCliniques(epreuves);
            bo.getEpreuvesCliniques().getTests().add(test);
        }

    }

    private void addButtonToTable() {
        actionColumn.setCellFactory(new Callback<TableColumn<Anamnese, Void>, TableCell<Anamnese, Void>>() {
            @Override
            public TableCell<Anamnese, Void> call(final TableColumn<Anamnese, Void> param) {
                final TableCell<Anamnese, Void> cell = new TableCell<Anamnese, Void>() {

                    private final Button viewButton = new Button("View Questions");
                    private final Button addAnamnese = new Button("Ajouter");

                    {
                        viewButton.setOnAction((event) -> {
                            Anamnese anamnese = getTableView().getItems().get(getIndex());
                            openQuestionsWindow(anamnese);
                        });

                        addAnamnese.setOnAction((event) -> {
                            Anamnese anamnese = getTableView().getItems().get(getIndex());
                            handleAddAnamnese(anamnese);

                        });


                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox buttonsBox = new HBox(viewButton, addAnamnese);
                            buttonsBox.setSpacing(5);
                            setGraphic(buttonsBox);
                        }
                    }
                };
                return cell;
            }
        });
    }

    private void handleAddAnamnese(Anamnese anamnese) {
        if (patient.getBoPremier() !=null) {
            bo.setAnamnese(null);
            System.out.println("Il ne s'agit pas du premier BO du patient !");
            return;
        }
        bo.setAnamnese(anamnese);

    }

    private void openQuestionsWindow(Anamnese anamnese) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("questions_view.fxml"));
            Parent root = loader.load();

            QuestionsViewController controller = loader.getController();
            ObservableList<Question> questions = FXCollections.observableArrayList(anamnese.getQuestions());
            controller.setQuestions(questions);

            Stage stage = new Stage();
            stage.setTitle("Questions for Anamnese: " + anamnese.getNom());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddTrouble(ActionEvent event) {
        String text = troubleNom.getText();
        String selectedOption = categorieTrouble.getValue();
        Trouble trouble = new Trouble(text, selectedOption);
        if (troubles == null) troubles = new HashSet<>();
        troubles.add(trouble);

        if (text != null && !text.isEmpty() && selectedOption != null) {
            if (listTroubles == null) listTroubles = new ListView<String>();
            listTroubles.getItems().add(text + " - " + selectedOption);
            troubleNom.clear();
            categorieTrouble.getSelectionModel().clearSelection();
        }
    }
    @FXML
    public void handleSubmit(ActionEvent event) {
        // handle adding bo to the list of bos

        Diagnostic diagnostic = new Diagnostic(troubles);
        bo.setDateBO(LocalDate.now());
        bo.setProjetTherap(projetTherap.getText());
        bo.setDiagnostic(diagnostic);
        if (bo.getAnamnese() != null) {
            patient.setBoPremier(bo);
        }
        else {
            BO boNew = new BO(LocalDate.now(), bo.getEpreuvesCliniques(), diagnostic, projetTherap.getText());
            patient.getBos().add(boNew);
        }

        System.out.println("Added BO successfully!!");
        System.out.println("------- BO ---------");
        System.out.println("Projet therap : "+bo.getProjetTherap());
        //System.out.println("Anamnese nom : "+bo.getAnamnese().getNom());
        for (Test test : bo.getEpreuvesCliniques().getTests()) {
            System.out.println("TEST nom : " + test.getNom());
        }


    }

    public static void setPatient(Patient patient) {
        AjoutBOController.patient = patient;
    }
}
