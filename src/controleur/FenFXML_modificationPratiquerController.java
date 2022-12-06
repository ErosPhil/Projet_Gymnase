package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import modele.Pratiquer;
import modele.GestionPratiquer;
import modele.Association;
import modele.GestionAssociation;
import modele.Sport;

public class FenFXML_modificationPratiquerController implements Initializable {
    
    ObservableList<Association> lesAssociations;
    ObservableList<Sport> lesSportsNonPratiquesParLAssociation;
    @FXML
    private ComboBox cmbAssociation;
    @FXML
    private ComboBox cmbSport;
    @FXML
    private Button btnEnregistrer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lesAssociations = GestionAssociation.listeAssociations();
        cmbAssociation.setItems(lesAssociations);
    }    
    
    @FXML
    public void handleChoixAssociation(){
    Association monAssociation = (Association)cmbAssociation.getSelectionModel().getSelectedItem();
    lesSportsNonPratiquesParLAssociation = GestionPratiquer.listeSportsNonPratiquesParUneAssociation(monAssociation.getRefAsso());
    cmbSport.setItems(lesSportsNonPratiquesParLAssociation);
    }
    
    @FXML
    public void handleAjouterSport()
    {
        GestionPratiquer.AjouterPratique(cmbAssociation.getValue().toString() ,cmbSport.getValue().toString());
        Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
        stage.close();
    }
}
