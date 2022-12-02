package modele;

public class Sport {
    private String nomSport;

    public Sport() {
    }

    public Sport(String nomSport) {
        this.nomSport = nomSport;
    }

    public String getNomSport() {
        return nomSport;
    }

    public void setNomSport(String nomSport) {
        this.nomSport = nomSport;
    }
    
    @Override
    public String toString() {
        return nomSport;
    }
}
