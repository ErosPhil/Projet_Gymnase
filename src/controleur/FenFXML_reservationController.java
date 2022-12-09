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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.*;

public class FenFXML_reservationController implements Initializable {

    ObservableList<Association> lesAssociations;
    ObservableList<Salle> lesSalles;
    @FXML
    private ComboBox cmbAssociation;
    @FXML
    private ComboBox cmbSalle;
    @FXML
    private DatePicker dateDate;
    @FXML
    private TextField txtHeure;
    @FXML
    private Button btnReserver;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lesAssociations = GestionAssociation.listeAssociations();
        cmbAssociation.setItems(lesAssociations);
        lesSalles = GestionSalle.listeSalles();
        cmbSalle.setItems(lesSalles);
    }
    
    @FXML
    public void handleReserver()
    {
        Association monAssociation = (Association)cmbAssociation.getSelectionModel().getSelectedItem();
        Salle maSalle = (Salle)cmbSalle.getSelectionModel().getSelectedItem();
        String maDate = dateDate.getValue().toString();
        String monHeure = txtHeure.getText();
           GestionReservation.Reserver(maSalle.getRefSalle(),maDate,monHeure,monAssociation.getRefAsso());
        Stage stage = (Stage) btnReserver.getScene().getWindow();
        stage.close();
    }
    
}
