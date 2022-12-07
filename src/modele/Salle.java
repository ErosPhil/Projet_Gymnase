package modele;

public class Salle {
    private String refSalle;
    private float surface;
    private String typeRevetement;

    public Salle() {
    }

    public Salle(String refSalle, float surface, String typeRevetement) {
        this.refSalle = refSalle;
        this.surface = surface;
        this.typeRevetement = typeRevetement;
    }

    public String getRefSalle() {
        return refSalle;
    }

    public void setRefSalle(String refSalle) {
        this.refSalle = refSalle;
    }

    public float getSurface() {
        return surface;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

    public String getTypeRevetement() {
        return typeRevetement;
    }

    public void setTypeRevetement(String typeRevetement) {
        this.typeRevetement = typeRevetement;
    }
    
    @Override
    public String toString() {
        return refSalle;
    }
}
