package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import modele.*;

//Occupation : 
public class FenFXML_occupationController implements Initializable {
    
    ObservableList<Salle> lesSalles;
    ObservableList<Reservation> lesReservations;
    @FXML
    private ComboBox cmbSalle;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private ListView lvOccupation;
    @FXML
    private Button btnAfficher;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lesSalles = GestionSalle.listeSalles();
        cmbSalle.setItems(lesSalles);
    }    
    
    @FXML
    public void handleBoutonAfficher(){
        
    }
}
