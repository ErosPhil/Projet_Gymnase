package controleur;

import java.net.URL;
import java.util.Date;
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

import java.time.LocalDate;
import java.time.LocalTime;

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
        Salle maSalle = (Salle)cmbSalle.getSelectionModel().getSelectedItem();
        LocalDate maDateFrom = dateFrom.getValue();
        LocalDate maDateTo = dateTo.getValue();
        lesReservations = GestionOccupation.listeReservationsPourUneSalleEntre2Dates(maSalle.getRefSalle(), maDateFrom, maDateTo);
        lvOccupation.getItems().add("La salle "+maSalle.getRefSalle()+" a été réservée :");
        for(int i = 0; i < lesReservations.size(); i++)
        {
            Reservation laReservation = lesReservations.get(i);
            LocalDate dateR = laReservation.getDate();
            LocalTime heureR = laReservation.getHeure();
            String asso = laReservation.getRefAsso();
            String uneOccupation = "le "+dateR+" à "+heureR+" par l'association"+asso;
            lvOccupation.getItems().add(uneOccupation);
        }
    }
}
