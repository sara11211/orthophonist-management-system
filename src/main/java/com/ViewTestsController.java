package com;

import com.models.Test;
import com.models.TestExercice;
import com.models.TestQuestionnaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;

import static com.HelloApplication.utilisateurCourant;
import static com.HelloApplication.oms;

public class ViewTestsController {

    @FXML
    private TableView<Test> testsTable;

    @FXML
    private TableColumn<Test, String> testNameColumn;

    @FXML
    private TableColumn<Test, String> testDescriptionColumn;

    private ObservableList<Test> tests;

    @FXML
    public void initialize() {
        testNameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        testDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadTests();
        addButtonsToTable();
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
        TableColumn<Test, Void> deleteCol = new TableColumn<>("Delete");

        deleteCol.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Delete");

            {
                btn.setOnAction(event -> {
                    Test data = getTableView().getItems().get(getIndex());
                    handleDeleteTest(data);
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

        testsTable.getColumns().add(deleteCol);

        // Modify Button
        TableColumn<Test, Void> modifyCol = new TableColumn<>("Modify");

        modifyCol.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Modify");

            {
                btn.setOnAction(event -> {
                    Test data = getTableView().getItems().get(getIndex());
                    handleModifyTest(data);
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

        testsTable.getColumns().add(modifyCol);
    }

    private void handleViewTest(Test selectedTest) {
        if (selectedTest != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/test_details.fxml"));
                Parent root = loader.load();

                if (selectedTest instanceof TestQuestionnaire) {
                    TestDetailController controller = loader.getController();
                    controller.setTest((TestQuestionnaire) selectedTest);
                } else if (selectedTest instanceof TestExercice) {
                    TestExerciceController controller = loader.getController();
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

    private void handleDeleteTest(Test selectedTest) {
        if (selectedTest != null) {
            utilisateurCourant.getTests().remove(selectedTest);
            oms.sauvegarder();
            tests.remove(selectedTest);
        }
    }

    private void handleModifyTest(Test selectedTest) {
        if (selectedTest != null) {
            try {
                FXMLLoader loader;
                if (selectedTest instanceof TestQuestionnaire) {
                    loader = new FXMLLoader(getClass().getResource("/com/test_creation.fxml"));
                } else if (selectedTest instanceof TestExercice) {
                    loader = new FXMLLoader(getClass().getResource("/com/test_exercice_creation.fxml"));
                    return;
                } else {
                    return;
                }

                Parent root = loader.load();

                if (selectedTest instanceof TestQuestionnaire) {
                    TestCreationController controller = loader.getController();
                    controller.setTest((TestQuestionnaire) selectedTest);
                }

                if (selectedTest instanceof TestExercice) {
                    TestExerciceController controller = loader.getController();
                    controller.setTestExercice((TestExercice) selectedTest);
                }

                Scene currentScene = testsTable.getScene();
                currentScene.setRoot(root);
                testsTable.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
