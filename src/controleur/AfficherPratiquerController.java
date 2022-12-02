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
    ObservableList<Pratiquer> lesPratiques;
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
    
}
