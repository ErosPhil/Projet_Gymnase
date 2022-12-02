package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Association;
import modele.GestionAssociation;

public class FenFXML_modificationAssociationController implements Initializable {
    @FXML
    private TextField txtRefAsso;
    @FXML
    private TextField txtVille;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtNomResponsable;
    @FXML
    private Button btnEnregistrer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String etat = MainApp.getEtat();
        if (etat.equals("Modif"))
        {
            Association monAsso = MainApp.getMonAssociationSelectionnee();
            txtRefAsso.setText(monAsso.getRefAsso());
            txtVille.setText(monAsso.getVille());
            txtAdresse.setText(monAsso.getAdresse());
            txtNomResponsable.setText(monAsso.getNomResponsable());
        }
        else
        {
            txtRefAsso.setEditable(true);
        }
    }
    @FXML
    public void handleEnregistrerAssociation()
    {
    String etat = MainApp.getEtat();
    GestionAssociation.EnregistrerAssociation(etat, txtRefAsso.getText(), txtVille.getText(), txtAdresse.getText(), txtNomResponsable.getText());
    Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
    stage.close();
    }
}
