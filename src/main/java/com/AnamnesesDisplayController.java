package com;

import com.models.Anamnese;
import com.models.Question;
import com.models.QuestionEnfant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

import static com.HelloApplication.oms;
import static com.HelloApplication.utilisateurCourant;

public class AnamnesesDisplayController {

    @FXML
    private TableView<Anamnese> anamnesesTable;
    @FXML
    private TableColumn<Anamnese, String> nameColumn;
    @FXML
    private TableColumn<Anamnese, String> descriptionColumn;
    @FXML
    private TableColumn<Anamnese, Void> actionColumn;

    private ObservableList<Anamnese> anamneses = FXCollections.observableArrayList();

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        anamnesesTable.setItems(anamneses);
        addButtonToTable();
    }

    public void setAnamneses(List<Anamnese> anamneses) {
        if (anamneses != null) {
            this.anamneses.setAll(anamneses);
        } else {
            this.anamneses.clear();
        }
    }

    private void addButtonToTable() {
        actionColumn.setCellFactory(new Callback<TableColumn<Anamnese, Void>, TableCell<Anamnese, Void>>() {
            @Override
            public TableCell<Anamnese, Void> call(final TableColumn<Anamnese, Void> param) {
                final TableCell<Anamnese, Void> cell = new TableCell<Anamnese, Void>() {

                    private final Button viewButton = new Button("View Questions");
                    private final Button deleteButton = new Button("Delete");
                    private final Button modifyButton = new Button("Modify");

                    {
                        viewButton.setOnAction((event) -> {
                            Anamnese anamnese = getTableView().getItems().get(getIndex());
                            openQuestionsWindow(anamnese);
                        });

                        deleteButton.setOnAction((event) -> {
                            Anamnese anamnese = getTableView().getItems().get(getIndex());
                            anamneses.remove(anamnese);
                            
                            // Perform the deletion in the storage system
                            oms.removeAnamneseFromUser(utilisateurCourant, anamnese);
                            oms.sauvegarder(); // Save the updated state
                            
                            // Remove the anamnese from the current user's list if not already done by oms.removeAnamneseFromUser
                            if (utilisateurCourant != null && utilisateurCourant.getAnamneses() != null) {
                                utilisateurCourant.getAnamneses().remove(anamnese);
                            }
                        });

                        modifyButton.setOnAction((event) -> {
                            Anamnese anamnese = getTableView().getItems().get(getIndex());
                            openModifyAnamneseWindow(anamnese);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox buttonsBox = new HBox(viewButton, deleteButton, modifyButton);
                            buttonsBox.setSpacing(5);
                            setGraphic(buttonsBox);
                        }
                    }
                };
                return cell;
            }
        });
    }

    private void openQuestionsWindow(Anamnese anamnese) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("questions_view.fxml"));
            Parent root = loader.load();

            QuestionsViewController controller = loader.getController();
            ObservableList<QuestionEnfant> questions = FXCollections.observableArrayList();
            for (Question question : anamnese.getQuestions()) {
                if (question instanceof QuestionEnfant) {
                    questions.add((QuestionEnfant) question);
                }
            }
            controller.setQuestions(questions);

            Stage stage = new Stage();
            stage.setTitle("Questions for Anamnese: " + anamnese.getNom());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openModifyAnamneseWindow(Anamnese anamnese) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anamnese_creation.fxml"));
            Parent root = loader.load();

            AnamneseController controller = loader.getController();
            controller.setAnamnese(anamnese);

            Stage stage = new Stage();
            stage.setTitle("Modify Anamnese: " + anamnese.getNom());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        @FXML
        private void handleRevenir(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("options.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}