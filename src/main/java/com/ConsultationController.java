package com;
import com.models.Consultation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static com.HelloApplication.utilisateurCourant;
import com.models.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import java.time.LocalTime;
import java.time.Duration;
public class ConsultationController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField ageField;

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

    @FXML
    private Label erreurText;

    private LocalDate selected_day;  // Assume selected_day is passed from another controller
    private CalendarController calendarController;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        timeHourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        timeMinuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        System.out.println("Soumettre button clicked");
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        if(creerDossierPatient(lastName, firstName)) System.out.println("Dossier created.");
        else System.out.println("Failure creating Dossier.");
        Duration duree = Duration.ofHours(dureeHourSpinner.getValue()).plusMinutes(dureeMinuteSpinner.getValue());

        int age = Integer.parseInt(ageField.getText());
        if (age < 18) {
            if (!duree.equals(Duration.ofHours(2).plusMinutes(30))) {
                dureeInvalide();
                return;
            }
        } else {
            if (!duree.equals(Duration.ofHours(1).plusMinutes(30))) {
                dureeInvalide();
                return;
            }
        }
        LocalTime heureDebut = LocalTime.of(timeHourSpinner.getValue(), timeMinuteSpinner.getValue());
        String infoSup = additionalInfoArea.getText();
        boolean isInfoSup = !infoSup.isEmpty();
        Consultation consultation = new Consultation(selected_day, heureDebut, duree, infoSup, isInfoSup, firstName, lastName, age);
        // TO ADD : Exception for when heureDebut + duree > 24h
        SessionLibre sessionLibre = new SessionLibre(selected_day.atTime(heureDebut), selected_day.atTime(heureDebut.plus(duree)));
        if (utilisateurCourant.getPlanning().planifier(sessionLibre,consultation)) {
            Color color = Color.RED;
            calendarController.colorStrip(selected_day, heureDebut, heureDebut.plus(duree), color);
            for (RDV rdvPlanned :  utilisateurCourant.getPlanning().getRDVSPlannified(selected_day)) {
                System.out.println("----- Rendez-vous plannifié -----");
                System.out.println("Date du rendez-vous  : "+rdvPlanned.getDate());
                System.out.println("Heure du rendez-vous  : "+rdvPlanned.getHeureDebut());
                System.out.println("Heure de fin du rendez-vous  : "+rdvPlanned.getHeureDebut().plus(rdvPlanned.getDuree()));
                if (rdvPlanned.getIsInfoSup()) System.out.println("Informations supplémentaires : "+rdvPlanned.getInfoSup());
                System.out.println("Type du rendez-vous  : Consulation");
                System.out.println("Nom et prénom du patient  : "+((Consultation)rdvPlanned).getNomPatient()+" "+((Consultation)rdvPlanned).getPrenomPatient());
                System.out.println("Age du patient : "+ ((Consultation)rdvPlanned).getAgePatient());
                System.out.println("---------------------------------");
            }


        }

    }
    @FXML
    private void handleFirstRDV(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreerPatientPage.fxml"));
            Parent root = loader.load();
            CreerPatientController controller = loader.getController();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Création d'un patient !");
            Scene scene = new Scene(root, 600, 400); // Adjust width and height as needed
            popupStage.setScene(scene);
            popupStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setCalendarController(CalendarController calendarController) {
        this.calendarController = calendarController;
    }

    public void setSelectedDay(LocalDate day) {
        this.selected_day = day;
    }

    private boolean creerDossierPatient(String nom, String prenom) {
        Patient patient = new Patient(nom, prenom);
        Dossier dossier = new Dossier(patient);
        utilisateurCourant.getPatientDossierHashMap().put(dossier.getNumDossier(), patient);
        return utilisateurCourant.getPatientDossierHashMap().containsKey(dossier.getNumDossier());
    }

    public void dureeInvalide() {
        erreurText.setText("Durée de consultation invalide !");
    }
}