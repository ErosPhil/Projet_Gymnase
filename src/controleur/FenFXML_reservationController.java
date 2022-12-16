package controleur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class FenFXML_reservationController implements Initializable {

    ObservableList<Association> lesAssociations;
    ObservableList<Salle> lesSallesCompatiblesAvecLAsso;
    ObservableList<Sport> lesSportsAccueillisParLaSalle;
    @FXML
    private ComboBox cmbAssociation;
    @FXML
    private ComboBox cmbSalle;
    @FXML
    private Label lblSportsPossibles;
    @FXML
    private DatePicker dateDate;
    @FXML
    private ComboBox cmbHeure;
    @FXML
    private Button btnReserver;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lesAssociations = GestionAssociation.listeAssociations();
        cmbAssociation.setItems(lesAssociations);
        cmbHeure.getItems().addAll("17:00", "18:00", "19:00","20:00","21:00");
    }
    
    @FXML
    public void handleChoixAssociation(){
        Association monAssociation = (Association)cmbAssociation.getSelectionModel().getSelectedItem();
        lesSallesCompatiblesAvecLAsso = GestionReservation.listeSallesCompatiblesAvecUneAsso(monAssociation.getRefAsso());
        cmbSalle.setItems(lesSallesCompatiblesAvecLAsso);
        cmbSalle.getSelectionModel().selectFirst();
        cmbSalle.setDisable(false);
        lblSportsPossibles.setText("");
    }
    
    @FXML
    public void handleChoixSalle(){
        Salle maSalle = (Salle)cmbSalle.getSelectionModel().getSelectedItem();
        Association monAssociation = (Association)cmbAssociation.getSelectionModel().getSelectedItem();
        lesSportsAccueillisParLaSalle = GestionReservation.listeSportsAccueillisParUneSalle(maSalle.getRefSalle(),monAssociation.getRefAsso());
        String textAAfficher = "Sports possibles : ";
        for(int i = 0; i < lesSportsAccueillisParLaSalle.size(); i++)
        {
            if(i == lesSportsAccueillisParLaSalle.size()-1)
            {
                textAAfficher += lesSportsAccueillisParLaSalle.get(i)+".";
            }
            else
            {
                textAAfficher += lesSportsAccueillisParLaSalle.get(i)+", ";
            }
        }
        lblSportsPossibles.setText(textAAfficher);
    }
    
    //Association -> Salle (en fonction des sports possibles dans la salle) -> Date & Heure (datePicker et combobox de tranches horaires 18 à 21)
    @FXML
    public void handleReserver()
    {
        Association monAssociation = (Association)cmbAssociation.getSelectionModel().getSelectedItem();
        Salle maSalle = (Salle)cmbSalle.getSelectionModel().getSelectedItem();
        LocalDate maDate = dateDate.getValue();
        LocalTime monHeure = LocalTime.parse(cmbHeure.getSelectionModel().getSelectedItem().toString() +":00");
        
        if(GestionReservation.verifReservationPossible(maSalle.getRefSalle(), maDate, monHeure, monAssociation.getRefAsso()) == true)
        {
            GestionReservation.Reserver(maSalle.getRefSalle(), maDate, monHeure, monAssociation.getRefAsso());
            Stage stage = (Stage) btnReserver.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rapport réservation");
            alert.setHeaderText("Réservation effectuée avec succès.");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Rapport erreur");
            alert.setHeaderText("Réservation impossible, une réservation pour la même salle à la même date à la même heure a déjà été effectuée.");
            alert.showAndWait();
        }
    }
    
}
