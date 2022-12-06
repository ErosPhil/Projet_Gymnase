package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class FenFXML_NoeudRacineController implements Initializable {

    Stage deuxiemeStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    // Association
    @FXML
    public void handleAfficherAssociations()
    {
        try
        {
            deuxiemeStage = new Stage();
            deuxiemeStage.setTitle("Liste des associations");
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/AfficherAssociation.fxml"));
            
            AnchorPane rootLayout = (AnchorPane)loader.load();
            Scene scene = new Scene(rootLayout);
            deuxiemeStage.setScene(scene);
            deuxiemeStage.show();
        }
        catch(IOException e)
        {
            System.out.println("Erreur chargement fenetre afficher associations : " + e.getMessage());
        }
    }
    
    @FXML
    public void handleInsererAssociation()
    {
        MainApp.setEtat("Insert");
        // Création de la fenêtre de confirmation
        try
        {
        deuxiemeStage = new Stage();
        deuxiemeStage.setTitle("Insertion nouvelle association");
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenFXML_modificationAssociation.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        deuxiemeStage.setScene(scene);
        deuxiemeStage.show();
        }
        catch (IOException e)
        {
        System.out.println("Erreur chargement fenetre insert association: " + e.getMessage());
        }
    }
    
    
    // Sport
    @FXML
    public void handleAfficherSports()
    {
        try
        {
            deuxiemeStage = new Stage();
            deuxiemeStage.setTitle("Liste des sports");
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/AfficherSport.fxml"));
            
            AnchorPane rootLayout = (AnchorPane)loader.load();
            Scene scene = new Scene(rootLayout);
            deuxiemeStage.setScene(scene);
            deuxiemeStage.show();
        }
        catch(IOException e)
        {
            System.out.println("Erreur chargement fenetre afficher sports : " + e.getMessage());
        }
    }
    
    @FXML
    public void handleInsererSport()
    {
        // Création de la fenêtre de confirmation
        try
        {
        deuxiemeStage = new Stage();
        deuxiemeStage.setTitle("Insertion nouveau sport");
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenFXML_modificationSport.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        deuxiemeStage.setScene(scene);
        deuxiemeStage.show();
        }
        catch (IOException e)
        {
        System.out.println("Erreur chargement fenetre insert sport: " + e.getMessage());
        }
    }
    
    // Pratiquer
    @FXML
    public void handleAfficherPratiques()
    {
        try
        {
            deuxiemeStage = new Stage();
            deuxiemeStage.setTitle("Liste des pratiques");
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/AfficherPratiquer.fxml"));
            AnchorPane rootLayout = (AnchorPane)loader.load();
            Scene scene = new Scene(rootLayout);
            deuxiemeStage.setScene(scene);
            deuxiemeStage.show();
        }
        catch(IOException e)
        {
            System.out.println("Erreur chargement fenetre afficher pratiques : " + e.getMessage());
        }
    }
    
    @FXML
    public void handleInsererPratique()
    {
        // Création de la fenêtre de confirmation
        try
        {
        deuxiemeStage = new Stage();
        deuxiemeStage.setTitle("Insertion nouvelle pratique");
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenFXML_modificationPratiquer.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        deuxiemeStage.setScene(scene);
        deuxiemeStage.show();
        }
        catch (IOException e)
        {
        System.out.println("Erreur chargement fenetre insert sport: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleFermer() // Methode événement sur le menu "Fermer"
    {
        System.exit(0);
    }
}
