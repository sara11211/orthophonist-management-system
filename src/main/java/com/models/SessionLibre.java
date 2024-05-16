package com.models;
import java.io.Serializable;
import java.time.LocalDateTime;
public class SessionLibre extends Session implements Serializable {
    public SessionLibre(LocalDateTime heureDebut, LocalDateTime heureFin) {
        super(heureDebut, heureFin);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}