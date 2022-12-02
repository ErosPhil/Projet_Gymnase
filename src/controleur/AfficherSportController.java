package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Sport;
import modele.GestionSport;

public class AfficherSportController implements Initializable {
    ObservableList<Sport> lesSports;
    @FXML
    private ListView<Sport> lvSports;
    
    Stage oStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lesSports = GestionSport.listeSports();
        lvSports.setItems(lesSports);
    }
    
    @FXML
    public void handleInserer()
    {
        // Création de la fenêtre de confirmation
        try
        {
        oStage = new Stage();
        oStage.setTitle("Insertion nouveau sport");
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenFXML_modificationSport.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        oStage.setScene(scene);
        oStage.show();
        }
        catch (IOException e)
        {
        System.out.println("Erreur chargement fenetre insert sport: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleSupprimer()
    {
        Sport monSport = lvSports.getSelectionModel().getSelectedItem();
        GestionSport.SupprimerSport(monSport.getNomSport());
        
        int indexSelectionne = lvSports.getSelectionModel().getSelectedIndex();
        lvSports.getItems().remove(indexSelectionne);
        //ObservableList<Sport> lesSports = lvSports.getItems();
        //MainApp.setMesSports(lesSports);
    }
    
}
