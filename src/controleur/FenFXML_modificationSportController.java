package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Sport;
import modele.GestionSport;

public class FenFXML_modificationSportController implements Initializable {
    
    @FXML
    private TextField txtNomSport;
    @FXML
    private Button btnEnregistrer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void handleAjouterSport()
    {
    GestionSport.AjouterSport(txtNomSport.getText());
    Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
    stage.close();
    }
}
