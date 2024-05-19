package com;
import com.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import java.util.HashSet;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.HelloApplication.utilisateurCourant;

public class AtelierController {
    @FXML
    private TextField patientIdField;

    @FXML
    private ListView<String> patientListView;

    @FXML
    private HBox addedPatientsContainer;

    @FXML
    private TextField thematiqueField;

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

    @FXML
    private LocalDate selected_day;

    private CalendarController calendarController;
    @FXML
    private void addPatient() {
        String patientId = patientIdField.getText().trim();
        if (!patientId.isEmpty() && !patientListView.getItems().contains(patientId)) {
            Button removeButton = new Button("x");
            removeButton.getStyleClass().add("remove-button");
            removeButton.setOnAction(event -> removePatient(patientId, removeButton));

            Region spacer = new Region();
            spacer.setMinWidth(5);

            addedPatientsContainer.getChildren().addAll(new PatientEntry(patientId, removeButton), spacer);
            patientIdField.clear();
        }
    }

    private void removePatient(String patientId, Button removeButton) {
        addedPatientsContainer.getChildren().removeIf(node -> node instanceof PatientEntry
                && ((PatientEntry) node).getPatientId().equals(patientId));

        // Optionally, remove from the ListView as well
        patientListView.getItems().remove(patientId);
    }

    // Optional: Helper class for patient entries with remove buttons
    private static class PatientEntry extends HBox {
        private final String patientId;

        public PatientEntry(String patientId, Button removeButton) {
            this.patientId = patientId;
            getChildren().addAll(new TextField(patientId), removeButton);
        }

        public String getPatientId() {
            return patientId;
        }
    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        // Logic to handle button click
        System.out.println("Soumettre button clicked");
        String thematique = thematiqueField.getText();
        LocalTime heureDebut = LocalTime.of(timeHourSpinner.getValue(), timeMinuteSpinner.getValue());
        Duration duree = Duration.ofHours(durationHourSpinner.getValue()).plusMinutes(durationMinuteSpinner.getValue());
        String infoSup = additionalInfoArea.getText();
        boolean isInfoSup = !infoSup.isEmpty();
        Atelier atelier = new Atelier(selected_day, heureDebut, duree, infoSup, isInfoSup, thematique, convertListViewToHashSet(patientListView));
        // TO ADD : Exception for when heureDebut + duree > 24h
        SessionLibre sessionLibre = new SessionLibre(selected_day.atTime(heureDebut), selected_day.atTime(heureDebut.plus(duree)));
        if (utilisateurCourant.getPlanning().planifier(sessionLibre,atelier)) {
            Color color = Color.BLUE;
            calendarController.colorStrip(selected_day, heureDebut, heureDebut.plus(duree), color);
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

        // Add your logic here, such as saving data or navigating to another scene
    }
    public void setSelectedDay(LocalDate day) {
        this.selected_day = day;
    }

    public void setCalendarController(CalendarController calendarController) {
        this.calendarController = calendarController;
    }

    public static HashSet<Long> convertListViewToHashSet(ListView<String> listView) {
        ObservableList<String> items = listView.getItems();
        HashSet<Long> hashSet = new HashSet<>();

        for (String item : items) {
            try {
                Long value = Long.parseLong(item);
                hashSet.add(value);
            } catch (NumberFormatException e) {
                // Handle if the string is not a valid Long
                System.err.println("Invalid format for Long: " + item);
            }
        }

        return hashSet;
    }
}