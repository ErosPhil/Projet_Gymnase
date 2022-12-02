package modele;

public class Accueillir {
    private String refSalle;
    private String nomSportAutorise;

    public Accueillir(String refSalle, String nomSportAutorise) {
        this.refSalle = refSalle;
        this.nomSportAutorise = nomSportAutorise;
    }

    public Accueillir() {
    }

    public String getRefSalle() {
        return refSalle;
    }

    public void setRefSalle(String refSalle) {
        this.refSalle = refSalle;
    }

    public String getNomSportAutorise() {
        return nomSportAutorise;
    }

    public void setNomSportAutorise(String nomSportAutorise) {
        this.nomSportAutorise = nomSportAutorise;
    }
}
