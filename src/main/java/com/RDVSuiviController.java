
package com;

import com.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import com.models.Consultation;
import javafx.fxml.Initializable;
import java.net.URL;

import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;
import static com.HelloApplication.utilisateurCourant;





public class RDVSuiviController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField numDossierField;

    @FXML
    private CheckBox presenceCheckBox;

    @FXML
    private CheckBox onlineCheckBox;


    @FXML
    private Spinner<Integer> timeHourSpinner;

    @FXML
    private Spinner<Integer> timeMinuteSpinner;

    @FXML
    private Spinner<Integer> dureeHourSpinner;

    @FXML
    private Spinner<Integer> dureeMinuteSpinner;


    @FXML
    private TextArea additionalInfoArea;

    private LocalDate selected_day;
    private CalendarController calendarController;

    @FXML
    private RadioButton enPresentielle;

    @FXML
    private Label erreurText;

    @FXML
    private RadioButton enLigne;

    @FXML
    private ToggleGroup toggleGroup;

    private RDVSuivi rdvSuivi;
    private Patient patient;
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        toggleGroup = new ToggleGroup(); toggleGroup = new ToggleGroup();
        enPresentielle.setToggleGroup(toggleGroup);
        enLigne.setToggleGroup(toggleGroup);
        timeHourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        timeMinuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
    }




    @FXML
    private void handleSubmit(ActionEvent event) {
        Long numeroDossier = Long.parseLong(numDossierField.getText());
        LocalTime heureDebut = LocalTime.of(timeHourSpinner.getValue(), timeMinuteSpinner.getValue());
        Duration duree = Duration.ofHours(dureeHourSpinner.getValue()).plusMinutes(dureeMinuteSpinner.getValue());
        if (!duree.equals(Duration.ofHours(1))) {
            dureeInvalide();
            return;
        }
        String infoSup = additionalInfoArea.getText();
        boolean isInfoSup = !infoSup.isEmpty(); boolean isPresentiel;
        isPresentiel = enPresentielle.isSelected();
        rdvSuivi = new RDVSuivi(selected_day, heureDebut, duree, infoSup, isInfoSup, numeroDossier, isPresentiel);
        SessionLibre sessionLibre = new SessionLibre(selected_day.atTime(heureDebut), selected_day.atTime(heureDebut.plus(duree)));
        if (utilisateurCourant.getPlanning() == null) utilisateurCourant.setPlanning(new Planning(utilisateurCourant.getNom(), LocalDate.now(), LocalDate.now().plusMonths(12) ));
        if (utilisateurCourant.getPlanning().planifier(sessionLibre,rdvSuivi)) {
            patient = utilisateurCourant.getPatientDossierHashMap().get(numeroDossier);
            patient.getRdvs().add(rdvSuivi);
            for (RDV rdvPlanned :  utilisateurCourant.getPlanning().getRDVSPlannified(selected_day)) {
                System.out.println("----- Rendez-vous plannifié -----");
                System.out.println("Date du rendez-vous  : "+rdvPlanned.getDate());
                System.out.println("Heure du rendez-vous  : "+rdvPlanned.getHeureDebut());
                System.out.println("Heure de fin du rendez-vous  : "+rdvPlanned.getHeureDebut().plus(rdvPlanned.getDuree()));
                if (rdvPlanned.getIsInfoSup()) System.out.println("Informations supplémentaires : "+rdvPlanned.getInfoSup());
                if (rdvPlanned instanceof Consultation) {
                    System.out.println("Type du rendez-vous  : Consulation");
                    System.out.println("Nom et prénom du patient  : " + ((Consultation) rdvPlanned).getNomPatient() + " " + ((Consultation) rdvPlanned).getPrenomPatient());
                    System.out.println("Age du patient : " + ((Consultation) rdvPlanned).getAgePatient());
                }
                if (rdvPlanned instanceof Atelier) {
                    System.out.println("Type du rendez-vous  : Consulation");
                    System.out.println("Thématique de l'atelier : " + ((Atelier) rdvPlanned).getThematique());
                    System.out.print("Liste des numéros de dossier des patients : ");
                    for (Long numDossier : ((Atelier) rdvPlanned).getListeNumDossier()) {
                        System.out.print(numDossier+" ");
                    }
                }
                if (rdvPlanned instanceof RDVSuivi) {
                    System.out.println("Type du rendez-vous  : Seance de suivi");
                    if (((RDVSuivi) rdvPlanned).getIsPresentiel()) System.out.println("Ce rendez-vous est en présentiel");
                    else System.out.println("Ce rendez-vous est en ligne");
                    System.out.println("Ce rendez-vous est pour le patient N°"+((RDVSuivi) rdvPlanned).getNumDossier());
                }
                System.out.println("---------------------------------");
            }

        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddObjectifs.fxml"));
            Parent root = fxmlLoader.load();
            Stage popupStage = new Stage();
            popupStage.setTitle("Objectifs");
            AddObjectifController controller = fxmlLoader.getController();
            controller.setRdvSuivi(rdvSuivi);
            controller.setPatient(patient);
            Scene scene = new Scene(root);
            popupStage.setScene(scene);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to create new Window: " + e.getMessage());
        }
    }

    public void setSelectedDay(LocalDate day) {
        this.selected_day = day;
    }

    public void setCalendarController(CalendarController calendarController) {
        this.calendarController = calendarController;
    }

    public void dureeInvalide() {
        erreurText.setText("Durée invalide !");
    }

}
