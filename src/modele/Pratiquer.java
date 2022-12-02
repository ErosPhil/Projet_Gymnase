package modele;

public class Pratiquer {
    private String refAsso;
    private String nomSport;

    public Pratiquer(String refAsso, String nomSport) {
        this.refAsso = refAsso;
        this.nomSport = nomSport;
    }

    public Pratiquer() {
    }

    public String getRefAsso() {
        return refAsso;
    }

    public void setRefAsso(String refAsso) {
        this.refAsso = refAsso;
    }

    public String getNomSport() {
        return nomSport;
    }

    public void setNomSport(String nomSport) {
        this.nomSport = nomSport;
    }
}
