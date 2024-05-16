package com;
import com.models.Patient;
import com.models.Consultation;

import java.time.LocalTime;
import java.time.Duration;


public class RDVController {
        // when user clicks on add consultation. redirection + function returns what calendar needs
        public void scheduleConsultation(LocalTime date, LocalTime heureDebut, Duration duree, String infoSup, String nomPatient, String prenomPatient, int agePatient) {
            Consultation consultation = new Consultation(date, heureDebut, duree, infoSup, nomPatient, prenomPatient, agePatient);
            consultation.scheduleRDV();
        }
}
