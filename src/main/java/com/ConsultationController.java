package com;
import com.models.*;
import com.models.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Parent;
import java.time.Period;
import javafx.scene.layout.BorderPane;
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
import java.time.LocalTime;
import java.time.Duration;
public class ConsultationController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label ageLabel;

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

    private Patient patient;
    private LocalDate selected_day;  // Assume selected_day is passed from another controller

    private boolean visible = true;

    private boolean submitSuccess = false;

    private Consultation newConsultation;

    public void setNewConsultation(Consultation newConsultation) {
        this.newConsultation = newConsultation;
    }

    public Consultation getNewConsultation() {
        return newConsultation;
    }

    public boolean getSubmitSuccess() {
        return this.submitSuccess;
    }


    public void setSelectedDay(LocalDate day) {
        this.selected_day = day;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        setVisible(false);
        updateUI();
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
        updateUI();
    }
    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        timeHourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        timeMinuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
    }


    private void updateUI() {
        if (!visible) {
            System.out.println("Inside the condition where visible is false.");
            firstNameLabel.setVisible(false);
            lastNameLabel.setVisible(false);
            ageLabel.setVisible(false);
            firstNameField.setDisable(true);
            lastNameField.setDisable(true);
            ageField.setDisable(true);
            if (patient != null) {
                System.out.println("PATIENT CORRECTLY FETCHED ? : "+patient.getNom()+ " "+patient.getPrenom()+" "+patient.getDateNaissance().toString());
                firstNameField.setText(patient.getPrenom());
                lastNameField.setText(patient.getNom());
                int age = calculateAge(patient.getDateNaissance());
                ageField.setText(String.valueOf(age));
            }
        } else {
            firstNameField.setDisable(false);
            lastNameField.setDisable(false);
            ageField.setDisable(false);
        }
    }
    @FXML
    private void handleSubmit(ActionEvent event) {
        System.out.println("Soumettre button clicked");
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        System.out.print("FIELD CORRECT ? "+firstName+ " "+lastName);
        if(creerDossierPatient(lastName, firstName)) System.out.println("Dossier created.");
        else System.out.println("Failure creating Dossier.");
        Duration duree = Duration.ofHours(dureeHourSpinner.getValue()).plusMinutes(dureeMinuteSpinner.getValue());
        int age = Integer.parseInt(ageField.getText());
        if (age < 18) {
            if (!duree.equals(Duration.ofHours(2).plusMinutes(30))) {
                dureeInvalide();
                submitSuccess = false;
                return;
            }
        } else {
            if (!duree.equals(Duration.ofHours(1).plusMinutes(30))) {
                dureeInvalide();
                submitSuccess = false;
                return;
            }
        }
        submitSuccess = true;
        LocalTime heureDebut = LocalTime.of(timeHourSpinner.getValue(), timeMinuteSpinner.getValue());
        String infoSup = additionalInfoArea.getText();
        boolean isInfoSup = !infoSup.isEmpty();
        Consultation consultation = new Consultation(selected_day, heureDebut, duree, infoSup, isInfoSup, firstName, lastName, age);
        setNewConsultation(consultation);
        // TO ADD : Exception for when heureDebut + duree > 24h
        SessionLibre sessionLibre = new SessionLibre(selected_day.atTime(heureDebut), selected_day.atTime(heureDebut.plus(duree)));
        if (utilisateurCourant.getPlanning().planifier(sessionLibre,consultation)) {
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




    private boolean creerDossierPatient(String nom, String prenom) {
        Patient patient = new Patient(nom, prenom);
        Dossier dossier = new Dossier(patient);
        utilisateurCourant.getPatientDossierHashMap().put(dossier.getNumDossier(), patient);
        return utilisateurCourant.getPatientDossierHashMap().containsKey(dossier.getNumDossier());
    }

    public void dureeInvalide() {
        erreurText.setText("Durée de consultation invalide !");
    }

    public static int calculateAge(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now(); // Get the current date
        if (dateOfBirth != null && !dateOfBirth.isAfter(currentDate)) { // Ensure the date of birth is not null and is not in the future
            return Period.between(dateOfBirth, currentDate).getYears(); // Calculate the age
        } else {
            throw new IllegalArgumentException("Date of birth is invalid");
        }
    }

}
