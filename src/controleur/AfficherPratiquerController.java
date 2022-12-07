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
import modele.GestionPratiquer;
import modele.Sport;
import modele.Association;

public class AfficherPratiquerController implements Initializable {
    
    ObservableList<Association> lesAssociationsDansPratiquer;
    ObservableList<Sport> lesSportsPratiquesParLAssociation;
    @FXML
    private ComboBox cmbAssociations;
    @FXML
    private ListView<Sport> lvSports;
    
    Stage oStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lesAssociationsDansPratiquer = GestionPratiquer.lesAssociationsDansPratiquer();
        cmbAssociations.setItems(lesAssociationsDansPratiquer);
    }    
    
    @FXML
    public void handleChoixAssociation(){ //se déclanche lors du choix d'une association du combobox
    Association monAssociation = (Association)cmbAssociations.getSelectionModel().getSelectedItem();
    lesSportsPratiquesParLAssociation = GestionPratiquer.listePratiquesPourUneAssociation(monAssociation.getRefAsso());
    lvSports.setItems(lesSportsPratiquesParLAssociation);
    }
    
    @FXML
    public void handleInsererPratique()
    {
        try
        {
        oStage = new Stage();
        oStage.setTitle("Insertion nouvelle pratique");
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenFXML_modificationPratiquer.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        oStage.setScene(scene);
        oStage.show();
        }
        catch (IOException e)
        {
        System.out.println("Erreur chargement fenetre insert sport: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleSupprimerPratique()
    {
        Association monAssociation = (Association)cmbAssociations.getSelectionModel().getSelectedItem();
        Sport monSport = lvSports.getSelectionModel().getSelectedItem();
        GestionPratiquer.SupprimerPratique(monAssociation.getRefAsso() ,monSport.getNomSport());
        
        int indexSelectionne = lvSports.getSelectionModel().getSelectedIndex();
        lvSports.getItems().remove(indexSelectionne);
        //ObservableList<Pratiquer> lesPratiques = GestionPratiquer.listePratiques();
        //MainApp.setMesPratiques(lesPratiques);
    }
}