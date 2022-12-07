package controleur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Pratiquer;
import modele.GestionAccueillir;
import modele.Salle;
import modele.Sport;

public class AfficherAccueillirController implements Initializable {

    ObservableList<Salle> lesSallesDansAccueillir;
    ObservableList<Sport> lesSportsAccueillisParLaSalle;
    @FXML
    private ComboBox cmbSalles;
    @FXML
    private ListView<Sport> lvSports;
    
    Stage oStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lesSallesDansAccueillir = GestionAccueillir.lesSallesDansAccueillir();
        cmbSalles.setItems(lesSallesDansAccueillir);
    }    
    
    @FXML
    public void handleChoixSalle(){
    Salle maSalle = (Salle)cmbSalles.getSelectionModel().getSelectedItem();
    lesSportsAccueillisParLaSalle = GestionAccueillir.listeAccueilsParUneSalle(maSalle.getRefSalle());
    lvSports.setItems(lesSportsAccueillisParLaSalle);
    }
    
    @FXML
    public void handleInsererAccueil()
    {
        try
        {
        oStage = new Stage();
        oStage.setTitle("Insertion nouvel accueil");
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenFXML_modificationAccueillir.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        oStage.setScene(scene);
        oStage.show();
        }
        catch (IOException e)
        {
        System.out.println("Erreur chargement fenetre insert accueil: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleSupprimerAccueil()
    {
        Salle maSalle = (Salle)cmbSalles.getSelectionModel().getSelectedItem();
        Sport monSport = lvSports.getSelectionModel().getSelectedItem();
        GestionAccueillir.SupprimerAccueil(maSalle.getRefSalle() ,monSport.getNomSport());
        
        int indexSelectionne = lvSports.getSelectionModel().getSelectedIndex();
        lvSports.getItems().remove(indexSelectionne);
        //ObservableList<Pratiquer> lesPratiques = GestionPratiquer.listePratiques();
        //MainApp.setMesPratiques(lesPratiques);
    }
}