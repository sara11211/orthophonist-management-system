package com;

import com.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.scene.control.CheckBox;
import com.models.Consultation;
import javafx.fxml.Initializable;
import java.net.URL;

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
    private TextArea additionalInfoArea;

    private LocalDate selected_day;
    private CalendarController calendarController;

    @FXML
    private RadioButton enPresentielle;


    @FXML
    private RadioButton enLigne;

    @FXML
    private ToggleGroup toggleGroup;


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
        System.out.println("Soumettre button clicked");
        Long numeroDossier = Long.parseLong(numDossierField.getText());
        LocalTime heureDebut = LocalTime.of(timeHourSpinner.getValue(), timeMinuteSpinner.getValue());
        Duration duree = Duration.ofHours(1);
        String infoSup = additionalInfoArea.getText();
        boolean isInfoSup = !infoSup.isEmpty(); boolean isPresentiel;
        isPresentiel = enPresentielle.isSelected();
        RDVSuivi rdvSuivi = new RDVSuivi(selected_day, heureDebut, duree, infoSup, isInfoSup, numeroDossier, isPresentiel);
        // TO ADD : Exception for when heureDebut + duree > 24h
        SessionLibre sessionLibre = new SessionLibre(selected_day.atTime(heureDebut), selected_day.atTime(heureDebut.plus(duree)));
        if (utilisateurCourant.getPlanning().planifier(sessionLibre,rdvSuivi)) {
            Patient patient = utilisateurCourant.getPatientDossierHashMap().get(numeroDossier);
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
    }

    public void setSelectedDay(LocalDate day) {
        this.selected_day = day;
    }

    public void setCalendarController(CalendarController calendarController) {
        this.calendarController = calendarController;
    }
}
