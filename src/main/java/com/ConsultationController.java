package com;
import com.models.Consultation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import static com.HelloApplication.utilisateurCourant;
import com.models.*;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import java.time.LocalTime;
import java.time.Duration;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class ConsultationController {

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
    private Spinner<Integer> durationHourSpinner;

    @FXML
    private Spinner<Integer> durationMinuteSpinner;

    @FXML
    private TextArea additionalInfoArea;

    private LocalDate selected_day;  // Assume selected_day is passed from another controller
    private CalendarController calendarController;

    @FXML
    private void initialize() {
        // Initialize spinners with default values and ranges
        timeHourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        timeMinuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        durationHourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        durationMinuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        // Logic to handle button click
        System.out.println("Soumettre button clicked");
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int age = Integer.parseInt(ageField.getText());
        LocalTime heureDebut = LocalTime.of(timeHourSpinner.getValue(), timeMinuteSpinner.getValue());
        Duration duree = Duration.ofHours(durationHourSpinner.getValue()).plusMinutes(durationMinuteSpinner.getValue());
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

        // Add your logic here, such as saving data or navigating to another scene
    }

    public void setCalendarController(CalendarController calendarController) {
        this.calendarController = calendarController;
    }

    // Method to set the selected_day from another controller
    public void setSelectedDay(LocalDate day) {
        this.selected_day = day;
    }
}
