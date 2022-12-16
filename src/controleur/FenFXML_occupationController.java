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
import java.time.format.DateTimeFormatter;

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
    private ListView lvOccupations;
    @FXML
    private Button btnAfficher;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lesSalles = GestionSalle.listeSalles();
        cmbSalle.setItems(lesSalles);
    }    
    
    @FXML
    public void handleBoutonAfficher(){
        lvOccupations.getItems().clear();
        Salle maSalle = (Salle)cmbSalle.getSelectionModel().getSelectedItem();
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        LocalDate maDateFrom = dateFrom.getValue();
        String dateFromFormatted = dateFormatter.format(maDateFrom);
        
        LocalDate maDateTo = dateTo.getValue();
        String dateToFormatted = dateFormatter.format(maDateTo);
        
        lesReservations = GestionOccupation.listeReservationsPourUneSalleEntre2Dates(maSalle.getRefSalle(), maDateFrom, maDateTo);
        if(lesReservations.size() == 0){
            lvOccupations.getItems().add("La salle n'a pas été réservée durant la période du "+dateFromFormatted+ " au "+dateToFormatted);
        }
        else{
            lvOccupations.getItems().add("La salle "+maSalle.getRefSalle()+" a été réservée :");
        }
        
        for(int i = 0; i < lesReservations.size(); i++)
        {
            Reservation laReservation = lesReservations.get(i);
            
            LocalDate dateR = laReservation.getDate();
            //<>dateFormatter déjà utilisé plus haut<>
            String dateRFormatted = dateFormatter.format(dateR);
            
            LocalTime heureR = laReservation.getHeure();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
            String heureRFormatted = timeFormatter.format(heureR);
            
            String asso = laReservation.getRefAsso();
            String uneOccupation = "le "+dateRFormatted+" à "+heureRFormatted+"h par l'association "+asso;
            lvOccupations.getItems().add(uneOccupation);
        }
    }
}
