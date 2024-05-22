package com.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SessionOccupee extends Session implements Serializable {
    private static final long serialVersionUID = 1L;

    private RDV rdv;

    public SessionOccupee(LocalDateTime heureDebut, LocalDateTime heureFin, RDV rdv) {
        super(heureDebut, heureFin);
        this.rdv = rdv;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}