package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import modele.*;
import java.util.ArrayList;

//Statistiques : pourcentage de chaque asso et horaire pour chaque salle
public class FenFXML_statistiquesController implements Initializable {
    
    ObservableList<Salle> lesSalles;
    ObservableList<String> listeRefAssoUtilisantLaSalle;
    ObservableList<ArrayList> lesIterationsHoraires;
    @FXML
    private ComboBox cmbSalles;
    @FXML
    private ListView lvStatAsso;
    @FXML
    private ListView lvStatHoraire;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lesSalles = GestionSalle.listeSalles();
        cmbSalles.setItems(lesSalles);
    }    
    
    @FXML
    public void handleChoixSalle(){
        lvStatAsso.getItems().clear();
        lvStatHoraire.getItems().clear();
        Salle maSalle = (Salle)cmbSalles.getSelectionModel().getSelectedItem();
        
        listeRefAssoUtilisantLaSalle = GestionStatistiques.listeRefAssoUtilisantUneSalle(maSalle.getRefSalle());
        float nbTotalReservationsPourLaSalle = GestionStatistiques.nombreTotalReservationsPourUneSalle(maSalle.getRefSalle());
        //System.out.println("Total Salle : " + nbTotalReservationsPourLaSalle);
        
        for(int i = 0; i < listeRefAssoUtilisantLaSalle.size(); i++)
        {
            //System.out.println(listeRefAssoUtilisantLaSalle.get(i));
            float nbTotalReservationsDeLAsso = GestionStatistiques.nombreTotalReservationsDUneAssoPourUneSalle(listeRefAssoUtilisantLaSalle.get(i), maSalle.getRefSalle());
            //System.out.println("Total Asso Salle: " + nbTotalReservationsDeLAsso);
            float pourcentageAsso = (nbTotalReservationsDeLAsso / nbTotalReservationsPourLaSalle)*100;
            //System.out.println("Pourcentage: " + pourcentageAsso + " %");
            lvStatAsso.getItems().add(listeRefAssoUtilisantLaSalle.get(i) + " = " + Math.round(pourcentageAsso) + " %");
        }
        
        lesIterationsHoraires = GestionStatistiques.iterationsHoraires(maSalle.getRefSalle());
        for(int i = 0; i < lesIterationsHoraires.size(); i++)
        {
            ArrayList list = lesIterationsHoraires.get(i);
            String heure = list.get(0).toString();
            Float nbIter = Float.parseFloat(list.get(1).toString());
            float pourcentageHoraire = (nbIter / nbTotalReservationsPourLaSalle)*100;
            lvStatHoraire.getItems().add(heure.substring(0,5) + " = " + Math.round(pourcentageHoraire) + " %");
        }
        
    }
}
