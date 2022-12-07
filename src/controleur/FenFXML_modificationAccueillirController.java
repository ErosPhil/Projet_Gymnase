package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import modele.Accueillir;
import modele.GestionAccueillir;
import modele.Salle;
import modele.GestionSalle;
import modele.Sport;

public class FenFXML_modificationAccueillirController implements Initializable {
    
    ObservableList<Salle> lesSalles;
    ObservableList<Sport> lesSportsPasAccueillisParLaSalle;
    @FXML
    private ComboBox cmbSalle;
    @FXML
    private ComboBox cmbSport;
    @FXML
    private Button btnEnregistrer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lesSalles = GestionSalle.listeSalles();
        cmbSalle.setItems(lesSalles);
    }
    
    @FXML
    public void handleChoixSalle(){
    Salle maSalle = (Salle)cmbSalle.getSelectionModel().getSelectedItem();
    lesSportsPasAccueillisParLaSalle = GestionAccueillir.listeSportsPasAccueillisParUneSalle(maSalle.getRefSalle());
    cmbSport.setItems(lesSportsPasAccueillisParLaSalle);
    }
    
    @FXML
    public void handleAjouterAccueil()
    {
        GestionAccueillir.AjouterAccueil(cmbSalle.getValue().toString() ,cmbSport.getValue().toString());
        Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
        stage.close();
    }
    
}
