package modele;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private String refSalle;
    private LocalDate date;
    private LocalTime heure;
    private String refAsso;

    public Reservation(String refSalle, LocalDate date, LocalTime heure, String refAsso) {
        this.refSalle = refSalle;
        this.date = date;
        this.heure = heure;
        this.refAsso = refAsso;
    }

    public Reservation() {
    }

    public String getRefSalle() {
        return refSalle;
    }

    public void setRefSalle(String refSalle) {
        this.refSalle = refSalle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public String getRefAsso() {
        return refAsso;
    }

    public void setRefAsso(String refAsso) {
        this.refAsso = refAsso;
    }
}
