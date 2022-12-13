//Association -> Salle (en fonction des sports possibles dans la salle) -> Date & Heure (datePicker et combobox de tranches horaires 18 à 22)
package controleur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.*;

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
    
    //ObservableList<String>lesHoraires;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //lesHoraires.addAll("17:00:00", "18:00:00", "19:00:00","20:00:00","21:00:00");
        lesAssociations = GestionAssociation.listeAssociations();
        cmbAssociation.setItems(lesAssociations);
        //cmbHeure.setItems(lesHoraires);
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

    @FXML
    public void handleReserver()
    {
        Association monAssociation = (Association)cmbAssociation.getSelectionModel().getSelectedItem();
        Salle maSalle = (Salle)cmbSalle.getSelectionModel().getSelectedItem();
        String maDate = dateDate.getValue().toString();
        String monHeure = cmbHeure.getSelectionModel().getSelectedItem().toString();
        GestionReservation.Reserver(maSalle.getRefSalle(),maDate,monHeure,monAssociation.getRefAsso());
        Stage stage = (Stage) btnReserver.getScene().getWindow();
        stage.close();
    }
    
}
