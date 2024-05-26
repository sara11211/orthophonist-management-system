package com;

import com.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailsBO implements Initializable {

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
    private ObservableList<Test> tests = FXCollections.observableArrayList();
    private ObservableList<Trouble> troubles = FXCollections.observableArrayList();

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setBo(BO bo) {
        this.bo = bo;
        if (bo != null) {
            updateUI();
        }
    }

    private void updateUI() {
        loadTests();
        addButtonsToTestTable();
        loadTroubles();
        loadProjetTherap();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        nomTroubleColumn.setCellValueFactory(new PropertyValueFactory<>("nomTrouble"));
        categorieTroubleColumn.setCellValueFactory(new PropertyValueFactory<>("typeTrouble"));
        loadTests();
        addButtonsToTestTable();
        loadTroubles();
        troublesTable.setItems(troubles);
        loadProjetTherap();
    }

    private void loadProjetTherap() {
        if (bo != null) {
            projetTherap.setText(bo.getProjetTherap());
        }
    }

    private void loadTroubles() {
        if (bo != null && bo.getDiagnostic() != null && bo.getDiagnostic().getTroubles() != null) {
            troubles = FXCollections.observableArrayList(bo.getDiagnostic().getTroubles());
        } else {
            troubles = FXCollections.observableArrayList();
        }
        troublesTable.setItems(troubles);
    }

    private void loadTests() {
        if (bo != null && bo.getEpreuvesCliniques() != null && bo.getEpreuvesCliniques().getTests() != null) {
            tests = FXCollections.observableArrayList(bo.getEpreuvesCliniques().getTests());
        } else {
            tests = FXCollections.observableArrayList();
        }
        testsTable.setItems(tests);
    }

    private void addButtonsToTestTable() {
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
                if (selectedTest instanceof TestQuestionnaire) {
                    controller.setTest((TestQuestionnaire) selectedTest);
                } else {
                    controller.setTestExercice((TestExercice) selectedTest);
                }

                Stage stage = new Stage();
                stage.setTitle("Test Details");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
