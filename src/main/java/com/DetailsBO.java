package com;
import com.models.*;
import com.models.BO;
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
import java.util.ResourceBundle;
import static com.HelloApplication.utilisateurCourant;
import javafx.stage.Modality;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.Callback;

import java.time.LocalTime;
import java.time.Duration;
public class DetailsBO implements  Initializable {

    @FXML
    private TableView<Anamnese> anamneseTable;
    @FXML
    private TableColumn<Anamnese, String> countdownAnamneseColumn;

    @FXML
    private TableColumn<Anamnese, Void> viewAnamneseColumn;

    @FXML
    private TableView<Test> testsTable;

    @FXML
    private TableColumn<Test, String> countdownTestColumn;


    @FXML
    private TableView<Trouble> troublesTable;

    @FXML
    private TableColumn<Trouble, String> nomTroubleColumn;
    @FXML
    private TableColumn<Trouble, String> categorieTroubleColumn;

    @FXML
    private TextArea projetTherap;

    private Patient patient;
    private BO bo;
    private Anamnese anamneseFromController;
  //  private ObservableList<Anamnese> anamnese = FXCollections.observableArrayList();
    private ObservableList<Test> tests = FXCollections.observableArrayList();
    private ObservableList<Trouble> troubles = FXCollections.observableArrayList();

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setBo(BO bo) {
        System.out.println("setBo called with: " + bo);
        this.bo = bo;
        if (bo != null) {
            System.out.println("BO is not null inside setBo");
            updateUI();
        } else {
            System.out.println("BO is null inside setBo");
        }
    }

  /*  public void setAnamneseFromController(Anamnese anamneseFromController) {
        this.anamneseFromController = anamneseFromController;
    }
*/

    private void updateUI() {
        loadTests();
        addButtonsToTestTable();
        loadTroubles();
        loadProjetTherap();

    }
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        //countdownAnamneseColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        nomTroubleColumn.setCellValueFactory(new PropertyValueFactory<>("nomTrouble"));
        categorieTroubleColumn.setCellValueFactory(new PropertyValueFactory<>("typeTrouble"));
      //  anamneseTable.setItems(anamnese);
       // loadAnamnese();
        //addButtonToAnamneseTable();
        loadTests();
        addButtonsToTestTable();
        loadTroubles();
        troublesTable.setItems(troubles);
        loadProjetTherap();
    }

    private void loadProjetTherap() {
        if (bo != null) {
            projetTherap.setText(bo.getProjetTherap());
        } else {
            System.out.println("BO null");
        }
    }

  /*  private void loadAnamnese() {
        if (bo != null) {
            anamnese = FXCollections.observableArrayList(anamneseFromController);
        } else {
            System.out.println("Pas d'anamnèses");
            anamnese = FXCollections.observableArrayList();
        }
        anamneseTable.setItems(anamnese);
    }*/

    private void loadTroubles() {
        if (bo != null) {
            troubles = FXCollections.observableArrayList(bo.getDiagnostic().getTroubles());
        } else {
            troubles = FXCollections.observableArrayList();
            System.out.println("BO null");
        }
        troublesTable.setItems(troubles);
    }
    private void loadTests() {
        if (bo != null) {
            tests = FXCollections.observableArrayList(bo.getEpreuvesCliniques().getTests());
        } else {
            tests = FXCollections.observableArrayList();
        }
        testsTable.setItems(tests);
    }


    private void addButtonsToTestTable() {
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






    }

    private void handleViewTest(Test selectedTest) {
        if (selectedTest != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/test_details.fxml"));
                Parent root = loader.load();

                TestDetailController controller = loader.getController();
                controller.setTest(selectedTest);

                Stage stage = new Stage();
                stage.setTitle("Test Details");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
/*

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
*/
/*
    private void addButtonToAnamneseTable() {

        viewAnamneseColumn.setCellFactory(new Callback<TableColumn<Anamnese, Void>, TableCell<Anamnese, Void>>() {
            @Override
            public TableCell<Anamnese, Void> call(final TableColumn<Anamnese, Void> param) {
                final TableCell<Anamnese, Void> cell = new TableCell<Anamnese, Void>() {

                    private final Button viewButton = new Button("View Questions");


                    {
                        viewButton.setOnAction((event) -> {
                            Anamnese anamnese = getTableView().getItems().get(getIndex());
                            openQuestionsWindow(anamnese);
                        });


                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox buttonsBox = new HBox(viewButton);
                            buttonsBox.setSpacing(5);
                            setGraphic(buttonsBox);
                        }
                    }
                };
                return cell;
            }
        });
    }
*/
}
