package modele;

public class Reservation {
    private String refSalle;
    private String date;
    private String heure;
    private String refAsso;

    public Reservation(String refSalle, String date, String heure, String refAsso) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getRefAsso() {
        return refAsso;
    }

    public void setRefAsso(String refAsso) {
        this.refAsso = refAsso;
    }
}
